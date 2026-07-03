package com.apicartaocredito.wl.Exception;

public class FaturaNaoEncontradaExcepiton extends RuntimeException {
    public FaturaNaoEncontradaExcepiton(Long id) {
        super("Fatura com id " + id + " não encontrada");
    }
}
