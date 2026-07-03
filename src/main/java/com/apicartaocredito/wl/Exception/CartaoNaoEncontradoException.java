package com.apicartaocredito.wl.Exception;

public class CartaoNaoEncontradoException extends RuntimeException {

    public CartaoNaoEncontradoException(Long id) {

        super("Cartão do id " + id + " Não encontrado");
    }
}
