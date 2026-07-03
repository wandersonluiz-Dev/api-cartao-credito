package com.apicartaocredito.wl.Controller;

import com.apicartaocredito.wl.Model.Dtos.CompraRequest;
import com.apicartaocredito.wl.Model.Dtos.CompraResponse;
import com.apicartaocredito.wl.Service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompraResponse realizarCompra(@Valid @RequestBody CompraRequest request) {

        return compraService.realizarCompra(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompraResponse BuscarCompra(@PathVariable Long id) {

        return compraService.buscarCompra(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompraResponse> listarCompras() {

        return compraService.listarCompras();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cartao/{cartaoId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CompraResponse> listarComprasPorCartao (@PathVariable Long cartaoId) {

        return compraService.listarComprasPorCartao(cartaoId);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/cancelar")
    @ResponseStatus(HttpStatus.OK)
    public CompraResponse cancelarCompra(@PathVariable Long id) {

        return compraService.cancelarCompra(id);
    }
}
