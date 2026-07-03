package com.apicartaocredito.wl.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClienteNaoEncontradoException.class, CartaoNaoEncontradoException.class,
            FaturaNaoEncontradaExcepiton.class, CompraNaoEncontradaException.class, PagamentoNaoEncontradoException.class})

    public ResponseEntity<ErroResponse> handleNOtFound(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(HttpStatus
                        .NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler({ValorCompraInvalidoException.class, ValorPagamentoInvalidoException.class, ValorPagamentoMaiorInvalidoException.class,
            ValorParcelaInvalidaException.class})

    public ResponseEntity<ErroResponse> handleBadRequest(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler({CartaoBloqueadoException.class, CartaoCanceladoException.class, ClienteInativoException.class, CompraCanceladaException.class,
            CpfJaCadastradoException.class, FaturaFechadaException.class, FaturaAtrasadaException.class, FaturaPagaException.class
            , LimiteInsuficienteException.class, FaturaNaoFechadaException.class, EmailJaCadastradoEception.class, TelefoneJaCadastradoException.class})

    public ResponseEntity<ErroResponse> handleComflit(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }
}
