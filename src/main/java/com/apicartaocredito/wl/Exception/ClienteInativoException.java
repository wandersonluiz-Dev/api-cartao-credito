package com.apicartaocredito.wl.Exception;

public class ClienteInativoException extends RuntimeException {

    public ClienteInativoException(Long id) {

        super("Cliente inativo com id " + id);
    }

}
