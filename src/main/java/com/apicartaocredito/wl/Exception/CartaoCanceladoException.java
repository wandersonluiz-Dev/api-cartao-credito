package com.apicartaocredito.wl.Exception;

public class CartaoCanceladoException extends RuntimeException {

    public CartaoCanceladoException(Long id) {

        super("Cartão de crédito id " + id + " está cancelado e não pode realizar operações.");
    }
}
