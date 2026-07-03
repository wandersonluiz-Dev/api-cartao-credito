package com.apicartaocredito.wl.Controller;

import com.apicartaocredito.wl.Model.Dtos.FaturaRequest;
import com.apicartaocredito.wl.Model.Dtos.FaturaResponse;
import com.apicartaocredito.wl.Service.FaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faturas")
@RequiredArgsConstructor
public class FaturaController {

    private final FaturaService faturaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FaturaResponse gerarFatura(@Valid @RequestBody FaturaRequest request) {

        return faturaService.gerarFatura(request);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FaturaResponse buscarFatura(@PathVariable Long id) {

        return faturaService.buscarFatura(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FaturaResponse> listarFaturas() {

        return faturaService.listarFaturas();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cartao/{cartaoId}")
    public List<FaturaResponse> listarFaturaPorCartao(@PathVariable Long cartaoId) {

        return faturaService.listarFaturaPorCartao(cartaoId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/fechar")
    public FaturaResponse fecharFatura(@PathVariable Long id) {

        return faturaService.fecharFatura(id);
    }
}
