package com.apicartaocredito.wl.Exception;

public class FaturaAtrasadaException extends RuntimeException {

    public FaturaAtrasadaException(Long id) {
        super("Fatura com id " + id + " ATRASADA");
    }
}
