package com.apicartaocredito.wl.Controller;

import com.apicartaocredito.wl.Model.Dtos.AtualizarDadosClientes;
import com.apicartaocredito.wl.Model.Dtos.ClienteRequest;
import com.apicartaocredito.wl.Model.Dtos.ClienteResponse;
import com.apicartaocredito.wl.Service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        return clienteService.criarCliente(clienteRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarClientes();

    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarDadosCliente(@PathVariable Long id, @Valid @RequestBody AtualizarDadosClientes dados ) {

        var clienteResponse = clienteService.atualizarDadosCliente(id, dados);
        return ResponseEntity.ok(clienteResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ClienteResponse desativarCliente(@PathVariable Long id) {
        return clienteService.desativarCliente(id);
    }
 }
