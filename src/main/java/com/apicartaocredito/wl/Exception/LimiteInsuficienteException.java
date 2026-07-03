package com.apicartaocredito.wl.Exception;

public class LimiteInsuficienteException extends RuntimeException {

    public LimiteInsuficienteException() {
        super("Limite Insuficiente: " );
    }
}
