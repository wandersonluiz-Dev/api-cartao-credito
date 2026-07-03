package com.apicartaocredito.wl.Exception;

public class FaturaNaoFechadaException extends RuntimeException {
    public FaturaNaoFechadaException(Long id) {
        super("Fatura id " + id + " não está fechada e não pode ser paga.");
    }
}
