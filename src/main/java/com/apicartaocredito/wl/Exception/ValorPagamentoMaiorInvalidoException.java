package com.apicartaocredito.wl.Exception;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ValorPagamentoMaiorInvalidoException extends RuntimeException {
    public ValorPagamentoMaiorInvalidoException(@NotNull @Positive BigDecimal  valorInformado, BigDecimal valorTotal) {
        super("Valor de pagamento inválido. Valor informado: R$ " + valorInformado + ", valor total da fatura: R$ " + valorTotal);
    }
}
