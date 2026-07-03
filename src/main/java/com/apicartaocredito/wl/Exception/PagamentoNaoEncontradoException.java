package com.apicartaocredito.wl.Exception;

public class PagamentoNaoEncontradoException extends RuntimeException {
    public PagamentoNaoEncontradoException(Long id) {

        super("Pagamento não encontrado " + id);
    }
}
