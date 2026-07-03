package com.apicartaocredito.wl.Exception;

public class FaturaPagaException extends RuntimeException {
    public FaturaPagaException(Long id) {
        super("Fatura com id " + id + " PAGA");
    }
}
