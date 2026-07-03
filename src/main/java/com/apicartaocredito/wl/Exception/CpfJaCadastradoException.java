package com.apicartaocredito.wl.Exception;

public class CpfJaCadastradoException extends RuntimeException {
    public CpfJaCadastradoException() {

        super("CPF já cadastrado! ");
    }

}
