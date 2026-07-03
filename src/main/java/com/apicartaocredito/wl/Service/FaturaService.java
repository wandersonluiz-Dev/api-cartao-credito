package com.apicartaocredito.wl.Service;


import com.apicartaocredito.wl.Model.Dtos.FaturaRequest;
import com.apicartaocredito.wl.Model.Dtos.FaturaResponse;
import com.apicartaocredito.wl.Exception.*;
import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.Model.CompraEntity;
import com.apicartaocredito.wl.Model.FaturaEntity;
import com.apicartaocredito.wl.Repository.ICartaoCreditoRepository;
import com.apicartaocredito.wl.Repository.ICompraRepository;
import com.apicartaocredito.wl.Repository.IFaturaRepository;
import com.apicartaocredito.wl.enums.StatusCartao;
import com.apicartaocredito.wl.enums.StatusCompra;
import com.apicartaocredito.wl.enums.StatusFatura;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaturaService {

    private final ICartaoCreditoRepository cartaoCreditoRepository;
    private final IFaturaRepository faturaRepository;
    private final ICompraRepository compraRepository;

    public FaturaResponse gerarFatura(FaturaRequest request) {

        CartaoCreditoEntity cartao = cartaoCreditoRepository.findById(request.cartaoId())
                .orElseThrow(()-> new CartaoNaoEncontradoException(request.cartaoId()));

        if(cartao.getStatusCartao() == StatusCartao.CANCELADO) {
            throw new CartaoCanceladoException(request.cartaoId());
        }

        List<CompraEntity> compras = compraRepository
                .findByCartaoIdAndStatusCompra(request.cartaoId(), StatusCompra.APROVADA);

        BigDecimal valorTotal = compras.stream()
                .map(CompraEntity::getValorParcelas)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorMinimo = valorTotal.multiply(BigDecimal.valueOf(0.15));

        FaturaEntity fatura = new FaturaEntity();

        fatura.setCartaoCredito(cartao);
        fatura.setNomeCliente(cartao.getNomeTitular());
        fatura.setMesReferencia(LocalDate.now().getMonth().toString());
        fatura.setValorTotal(valorTotal);
        fatura.setValorMinimo(valorMinimo);
        fatura.setVencimento(LocalDate.now().plusDays(10));
        fatura.setStatusFatura(StatusFatura.ABERTA);

        faturaRepository.save(fatura);

        return new FaturaResponse(fatura);

    }

    public FaturaResponse buscarFatura(Long id) {

        FaturaEntity fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new FaturaNaoEncontradaExcepiton(id));

        return new FaturaResponse(fatura);
    }

    public List<FaturaResponse> listarFaturas() {

        List<FaturaEntity> faturas = faturaRepository.findAll();

        return faturas.stream()
                .map(FaturaResponse::new)
                .toList();
    }

    public List<FaturaResponse> listarFaturaPorCartao(Long cartaoId) {

        cartaoCreditoRepository.findById(cartaoId)
                .orElseThrow(()-> new CartaoNaoEncontradoException(cartaoId));

        return faturaRepository.findByCartaoCreditoId(cartaoId)
                .stream()
                .map(FaturaResponse::new)
                .toList();

    }

    public FaturaResponse fecharFatura(Long id) {

        FaturaEntity fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new FaturaNaoEncontradaExcepiton(id));

        if (fatura.getStatusFatura() == StatusFatura.FECHADA) {
            throw new FaturaFechadaException(id);
        }

        if (fatura.getStatusFatura() == StatusFatura.PAGA) {
            throw new FaturaPagaException(id);
        }

        fatura.setStatusFatura(StatusFatura.FECHADA);

        faturaRepository.save(fatura);

        return new FaturaResponse(fatura);
    }
}