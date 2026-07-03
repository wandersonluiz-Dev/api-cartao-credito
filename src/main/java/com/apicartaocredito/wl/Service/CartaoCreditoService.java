package com.apicartaocredito.wl.Service;

import com.apicartaocredito.wl.Model.Dtos.CartaoCreditoResponse;
import com.apicartaocredito.wl.Model.Dtos.EmitirCartaoResponse;
import com.apicartaocredito.wl.Model.Dtos.EmitirCartaoRequest;
import com.apicartaocredito.wl.Exception.CartaoBloqueadoException;
import com.apicartaocredito.wl.Exception.CartaoNaoEncontradoException;
import com.apicartaocredito.wl.Exception.ClienteInativoException;
import com.apicartaocredito.wl.Exception.ClienteNaoEncontradoException;
import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.Model.ClienteEntity;
import com.apicartaocredito.wl.Repository.ICartaoCreditoRepository;
import com.apicartaocredito.wl.Repository.IClienteRepository;
import com.apicartaocredito.wl.enums.StatusCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CartaoCreditoService {

    private final IClienteRepository clienteRepository;
    private final ICartaoCreditoRepository cartaoCreditoRepository;

    public EmitirCartaoResponse emitirCartao(EmitirCartaoRequest request) {

        ClienteEntity cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException(request.clienteId()));

       if(!cliente.getAtivo()) {
           throw new ClienteInativoException(request.clienteId());
       }

       BigDecimal limite = calcularLimite(cliente.getSalario());

        CartaoCreditoEntity cartaoCredito =  new CartaoCreditoEntity();

        cartaoCredito.setCliente(cliente);
        cartaoCredito.setNomeTitular(cliente.getNome());
        cartaoCredito.setLimiteTotal(limite);
        cartaoCredito.setLimiteDisponivel(limite);
        cartaoCredito.setValidade(LocalDate.now().plusYears(5));
        cartaoCredito.setNumero(gerarNumerocartao());
        cartaoCredito.setCvv(gerarCvv());
        cartaoCredito.setStatusCartao(StatusCartao.ATIVO);

        CartaoCreditoEntity cartaoSalvo = cartaoCreditoRepository.save(cartaoCredito);

        return new EmitirCartaoResponse(cartaoSalvo);

    }

        private String gerarNumerocartao() {

        Long numero = ThreadLocalRandom.current()
                .nextLong(1000000000000000L,
                         9999999999999999L);

        return String.valueOf(numero);
    }


    private String gerarCvv() {
        Integer cvv = ThreadLocalRandom.current()
                .nextInt(100, 1000);

        return String.valueOf(cvv);
    }


    private BigDecimal calcularLimite(BigDecimal salario) {

        if(salario.compareTo(BigDecimal.valueOf(1500)) <= 0) {
            return BigDecimal.valueOf(500);
    }

        if (salario.compareTo(BigDecimal.valueOf(3000)) <= 0) {
        return BigDecimal.valueOf(1500);
    }

        if(salario.compareTo(BigDecimal.valueOf(6000)) <= 0) {
        return BigDecimal.valueOf(3000);
    }


        return BigDecimal.valueOf(8000);
    }


    public CartaoCreditoResponse buscarCartaoPorId(Long id) {

        CartaoCreditoEntity cartaoCredito = cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new CartaoNaoEncontradoException(id));

        return new CartaoCreditoResponse(cartaoCredito);
    }

    public List<CartaoCreditoResponse> listarCartoes() {

        List<CartaoCreditoEntity> cartoes = cartaoCreditoRepository.findAll();

        return cartoes.stream()
                .map(CartaoCreditoResponse::new)
                .toList();

    }

    public CartaoCreditoResponse bloquearCartaoPorId(Long id) {

       CartaoCreditoEntity cartaoCredito = cartaoCreditoRepository.findById(id)
               .orElseThrow(() -> new CartaoNaoEncontradoException(id));


        if (cartaoCredito.getStatusCartao() == StatusCartao.BLOQUEADO) {
            throw new CartaoBloqueadoException(id);
        }

        cartaoCredito.setStatusCartao(StatusCartao.BLOQUEADO);

        cartaoCreditoRepository.save(cartaoCredito);

        return new CartaoCreditoResponse(cartaoCredito);

    }

    public CartaoCreditoResponse cancelarCartaoPorId(Long id) {

        CartaoCreditoEntity cartaoCredito = cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new CartaoNaoEncontradoException(id));

        cartaoCredito.setStatusCartao(StatusCartao.CANCELADO);

        cartaoCreditoRepository.save(cartaoCredito);

        return new CartaoCreditoResponse(cartaoCredito);
    }

}
