package com.apicartaocredito.wl.Exception;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ValorPagamentoInvalidoException extends RuntimeException {
    public ValorPagamentoInvalidoException() {
        super("Valor de pagamento inválido");
    }

    public ValorPagamentoInvalidoException(@NotNull @Positive BigDecimal valorInformado, BigDecimal valorMinimo) {
        super("Valor de pagamento inválido. Valor informado: " + valorInformado + ", Valor minimo da fatura: " + valorMinimo);
    }

}
