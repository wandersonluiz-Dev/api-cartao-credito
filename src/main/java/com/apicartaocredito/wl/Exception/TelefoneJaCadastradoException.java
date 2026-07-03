package com.apicartaocredito.wl.Exception;

public class TelefoneJaCadastradoException extends RuntimeException {
    public TelefoneJaCadastradoException() {
        super("Telefone já cadastrado");
    }
}
