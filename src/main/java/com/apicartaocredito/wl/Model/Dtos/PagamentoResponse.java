package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.PagamentoEntity;
import com.apicartaocredito.wl.enums.StatusCompra;
import com.apicartaocredito.wl.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponse(
        Long id,
        Long faturaId,
        BigDecimal valorPago,
        LocalDateTime dataPagamento,
        StatusPagamento status
) {


     public PagamentoResponse(PagamentoEntity pagamento) {
         this(
                 pagamento.getId(),
                 pagamento.getFatura().getId(),
                 pagamento.getValorPago(),
                 pagamento.getDataPagamento(),
                 pagamento.getStatusPagamento()
         );

    }
}
