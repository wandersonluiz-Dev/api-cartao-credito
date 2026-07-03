package com.apicartaocredito.wl.Exception;

public class ValorCompraInvalidoException extends RuntimeException {
    public ValorCompraInvalidoException() {

        super("Valor da compra inválido");
    }
}
