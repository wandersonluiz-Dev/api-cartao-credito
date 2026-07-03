package com.apicartaocredito.wl.Exception;

public class CompraCanceladaException extends RuntimeException {
    public CompraCanceladaException(Long id) {
        super("Compra do " + id + " cancelada");
    }
}
