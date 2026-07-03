package com.apicartaocredito.wl.Controller;


import com.apicartaocredito.wl.Model.Dtos.CartaoCreditoResponse;
import com.apicartaocredito.wl.Model.Dtos.EmitirCartaoRequest;
import com.apicartaocredito.wl.Model.Dtos.EmitirCartaoResponse;
import com.apicartaocredito.wl.Service.CartaoCreditoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoCreditoController {

    private final CartaoCreditoService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmitirCartaoResponse emitirCartao(@Valid @RequestBody EmitirCartaoRequest request) {

        return service.emitirCartao(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CartaoCreditoResponse buscarCartaoPorId(@PathVariable Long id) {

        return service.buscarCartaoPorId(id);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CartaoCreditoResponse> listarCartoes() {

        return service.listarCartoes();

    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}/bloquear")
    @ResponseStatus(HttpStatus.OK)
    public CartaoCreditoResponse bloquearCartaoPorId (@PathVariable Long id) {

        return service.bloquearCartaoPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/cancelar")
    @ResponseStatus(HttpStatus.OK)
    public CartaoCreditoResponse cancelarCartaoPorid(@PathVariable Long id) {

        return service.cancelarCartaoPorId(id);
    }
}
