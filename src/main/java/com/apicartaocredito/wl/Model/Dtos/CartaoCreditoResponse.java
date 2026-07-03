package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.enums.StatusCartao;

import java.math.BigDecimal;

public record CartaoCreditoResponse( Long id,
                                     String nomeTitular,
                                     StatusCartao statusCartao,
                                     String numero,
                                     BigDecimal limiteTotal,
                                     BigDecimal limiteDisponivel) {

    public CartaoCreditoResponse(CartaoCreditoEntity cartaoCredito) {
        this(
                cartaoCredito.getId(),
                cartaoCredito.getNomeTitular(),
                cartaoCredito.getStatusCartao(),
                cartaoCredito.getNumeroMascarado(),
                cartaoCredito.getLimiteTotal(),
                cartaoCredito.getLimiteDisponivel()
        );

    }
}