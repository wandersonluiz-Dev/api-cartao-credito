package com.apicartaocredito.wl.Exception;

public class CompraNaoEncontradaException extends RuntimeException {
    public CompraNaoEncontradaException(Long id) {
        super("Compra com id " + id + " não encontrada");
    }
}
