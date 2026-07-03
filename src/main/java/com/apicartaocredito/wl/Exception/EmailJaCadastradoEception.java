package com.apicartaocredito.wl.Exception;

public class EmailJaCadastradoEception extends RuntimeException {

    public EmailJaCadastradoEception() {
        super("Email já cadastrado");
    }
}
