package com.apicartaocredito.wl.Exception;

public class FaturaFechadaException extends RuntimeException {
    public FaturaFechadaException(Long id) {
        super("Fatura com id " + id + " FECHADA");
    }
}
