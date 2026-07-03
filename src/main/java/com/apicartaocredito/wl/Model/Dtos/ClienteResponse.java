package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.ClienteEntity;
import com.apicartaocredito.wl.enums.StatusCliente;

public record ClienteResponse(Long id,
                              String nome,
                              String cpf,
                              String email,
                              String telefone,
                              StatusCliente status) {

    public ClienteResponse(ClienteEntity clienteSalvo) {
        this(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getCpf(),
                clienteSalvo.getEmail(),
                clienteSalvo.getTelefone(),
                clienteSalvo.getStatusCliente());
    }


}
