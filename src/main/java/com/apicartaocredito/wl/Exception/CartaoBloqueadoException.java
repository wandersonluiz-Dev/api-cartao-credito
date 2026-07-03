package com.apicartaocredito.wl.Exception;

public class CartaoBloqueadoException extends RuntimeException {
    public CartaoBloqueadoException(Long cartaoCreditoId) {

        super("Cartão com id " + cartaoCreditoId + " está bloqueado.");
    }
}
