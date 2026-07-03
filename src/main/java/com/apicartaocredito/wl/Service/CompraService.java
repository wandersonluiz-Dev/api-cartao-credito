package com.apicartaocredito.wl.Service;


import com.apicartaocredito.wl.Model.Dtos.CompraRequest;
import com.apicartaocredito.wl.Model.Dtos.CompraResponse;
import com.apicartaocredito.wl.Exception.*;
import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.Model.CompraEntity;
import com.apicartaocredito.wl.Repository.ICartaoCreditoRepository;
import com.apicartaocredito.wl.Repository.ICompraRepository;
import com.apicartaocredito.wl.enums.StatusCartao;
import com.apicartaocredito.wl.enums.StatusCompra;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final ICartaoCreditoRepository cartaoCreditoRepository;
    private final ICompraRepository compraRepository;

    @Transactional
    public CompraResponse realizarCompra(CompraRequest request) {

        CartaoCreditoEntity cartaoCredito = cartaoCreditoRepository.findById(request.cartaoId())
                .orElseThrow(() -> new CartaoNaoEncontradoException(request.cartaoId()));


        if (cartaoCredito.getStatusCartao() == StatusCartao.BLOQUEADO) {
            throw new CartaoBloqueadoException(request.cartaoId());
        }

        if(cartaoCredito.getStatusCartao() == StatusCartao.CANCELADO) {
            throw new CartaoCanceladoException(request.cartaoId());
        }

        if (request.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorCompraInvalidoException();
        }

        if (request.valor().compareTo(cartaoCredito.getLimiteDisponivel()) > 0) {
            throw new LimiteInsuficienteException();
        }

        if (request.parcelas() <= 0 || request.parcelas() > 12) {
            throw new ValorParcelaInvalidaException();
        }

        BigDecimal valorParcelas = request.valor()
                        .divide(BigDecimal.valueOf(request.parcelas()), 2, RoundingMode.HALF_UP);

        cartaoCredito.setLimiteDisponivel(cartaoCredito
                .getLimiteDisponivel().
                subtract(request.valor()));

        cartaoCreditoRepository.save(cartaoCredito);


        CompraEntity compra = new CompraEntity();

        compra.setDescricao(request.descricao());
        compra.setNomeCliente(request.nomeCliente());
        compra.setValor(request.valor());
        compra.setParcelas(request.parcelas());
        compra.setValorParcelas(valorParcelas);
        compra.setDataCompra(LocalDateTime.now());
        compra.setStatusCompra(StatusCompra.APROVADA);
        compra.setCartao(cartaoCredito);

        compraRepository.save(compra);

        return new CompraResponse(compra);

    }

    public CompraResponse buscarCompra(Long id) {

       CompraEntity compra = compraRepository.findById(id)
               .orElseThrow(() -> new CompraNaoEncontradaException(id));

       return new CompraResponse(compra);
    }

    public List<CompraResponse> listarCompras() {

        return compraRepository.findAll()
                .stream()
                .map(CompraResponse::new)
                .toList();
    }

    public List<CompraResponse> listarComprasPorCartao(Long cartaoId) {

        CartaoCreditoEntity cartao = cartaoCreditoRepository.findById(cartaoId)
                .orElseThrow(() -> new CartaoNaoEncontradoException(cartaoId));

        return compraRepository.findByCartao(cartao)
                .stream()
                .map(CompraResponse::new)
                .toList();

    }

    public CompraResponse cancelarCompra(Long id) {

        CompraEntity compra = compraRepository.findById(id)
                .orElseThrow(() -> new CompraNaoEncontradaException(id));

        if(compra.getStatusCompra() == StatusCompra.CANCELADA) {
            throw new CompraCanceladaException(id);
        }

        CartaoCreditoEntity cartaoCredito = compra.getCartao();

        cartaoCredito.setLimiteDisponivel(cartaoCredito
                .getLimiteDisponivel()
                .add(compra.getValor()));

        compra.setStatusCompra(StatusCompra.CANCELADA);

        cartaoCreditoRepository.save(cartaoCredito);

        compraRepository.save(compra);

        return new CompraResponse(compra);
    }

}