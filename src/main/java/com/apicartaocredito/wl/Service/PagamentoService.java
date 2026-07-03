package com.apicartaocredito.wl.Service;

import com.apicartaocredito.wl.Model.Dtos.PagamentoRequest;
import com.apicartaocredito.wl.Model.Dtos.PagamentoResponse;
import com.apicartaocredito.wl.Exception.*;
import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.Model.FaturaEntity;
import com.apicartaocredito.wl.Model.PagamentoEntity;
import com.apicartaocredito.wl.Repository.ICartaoCreditoRepository;
import com.apicartaocredito.wl.Repository.IFaturaRepository;
import com.apicartaocredito.wl.Repository.IPagamentoRepository;
import com.apicartaocredito.wl.enums.StatusFatura;
import com.apicartaocredito.wl.enums.StatusPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final IFaturaRepository faturaRepository;
    private final ICartaoCreditoRepository cartaoCreditoRepository;
    private final IPagamentoRepository pagamentoRepository;

    @Transactional
    public PagamentoResponse realizarPagamento(PagamentoRequest request) {

        FaturaEntity fatura = faturaRepository.findById(request.faturaId())
                .orElseThrow(() -> new FaturaNaoEncontradaExcepiton(request.faturaId()));

         if (fatura.getStatusFatura() == StatusFatura.PAGA) {
            throw new FaturaPagaException(request.faturaId());
        }

        if (fatura.getStatusFatura() != StatusFatura.FECHADA) {
            throw new FaturaNaoFechadaException(request.faturaId());
        }

        if (request.valorPago().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorPagamentoInvalidoException();
        }

        if (request.valorPago().compareTo(fatura.getValorTotal()) > 0) {
            throw new ValorPagamentoMaiorInvalidoException(request.valorPago(), fatura.getValorTotal());
        }

        if (request.valorPago().compareTo(fatura.getValorMinimo()) < 0) {
            throw new ValorPagamentoInvalidoException(request.valorPago(), fatura.getValorMinimo());
        }

        PagamentoEntity pagamento = new PagamentoEntity();

        pagamento.setValorPago(request.valorPago());
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setStatusPagamento(StatusPagamento.PROCESSADO);

        pagamento.setFatura(fatura);

        fatura.setStatusFatura(StatusFatura.PAGA);

        CartaoCreditoEntity cartao = fatura.getCartaoCredito();

        cartao.setLimiteDisponivel(cartao
                .getLimiteDisponivel()
                .add(request.valorPago()));

        cartaoCreditoRepository.save(cartao);

        faturaRepository.save(fatura);

      pagamentoRepository.save(pagamento);

        return new PagamentoResponse(pagamento);

    }

    public PagamentoResponse buscarPagamento(Long id) {

        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(()-> new PagamentoNaoEncontradoException(id));

        return new PagamentoResponse(pagamento);
    }

    public List<PagamentoResponse> listarPagamentos() {

        List<PagamentoEntity> pagamentos = pagamentoRepository.findAll();

        return pagamentos.stream()
                .map(PagamentoResponse::new)
                .toList();

    }

    public List<PagamentoResponse> listarPagamentosPorFatura(Long faturaId) {

        FaturaEntity fatura = faturaRepository.findById(faturaId)
                .orElseThrow(() -> new FaturaNaoEncontradaExcepiton(faturaId));

        List<PagamentoEntity> pagamentos = pagamentoRepository.findByFatura(fatura);

        return pagamentos.stream()
                .map(PagamentoResponse::new)
                .toList();

    }

}
