package com.apicartaocredito.wl.Exception;

public class ValorParcelaInvalidaException extends RuntimeException {
    public ValorParcelaInvalidaException() {
        super("Valor da parcela é inválido");
    }
}
