package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.FaturaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturaResponse(Long id,
                             String numeroCartao,
                             String nomeCliente,
                             String mesReferencia,
                             BigDecimal valorTotal,
                             BigDecimal valorMinimo,
                             LocalDate vencimento) {


    public FaturaResponse(FaturaEntity fatura) {

        this(
                fatura.getId(),
                fatura.getCartaoCredito().getNumeroMascarado(),
                fatura.getNomeCliente(),
                fatura.getMesReferencia(),
                fatura.getValorTotal(),
                fatura.getValorMinimo(),
                fatura.getVencimento()

        );
    }
}
