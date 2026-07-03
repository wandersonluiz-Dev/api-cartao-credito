package com.apicartaocredito.wl.Controller;


import com.apicartaocredito.wl.Model.Dtos.PagamentoRequest;
import com.apicartaocredito.wl.Model.Dtos.PagamentoResponse;
import com.apicartaocredito.wl.Service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponse realizarPagamento(@Valid @RequestBody PagamentoRequest request) {

        return pagamentoService.realizarPagamento(request);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PagamentoResponse buscarPagamento(@PathVariable Long id) {

        return pagamentoService.buscarPagamento(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PagamentoResponse> listarPagamentos() {

        return pagamentoService.listarPagamentos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fatura/{faturaId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PagamentoResponse> listarPagamentosPorFatura (@PathVariable Long faturaId) {

        return pagamentoService.listarPagamentosPorFatura(faturaId);
    }
}
