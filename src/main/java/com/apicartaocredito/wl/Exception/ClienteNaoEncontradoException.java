package com.apicartaocredito.wl.Exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(Long id) {
        super("Cliente do id " + id + " não encontrado");
    }
}
