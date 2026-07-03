package com.apicartaocredito.wl.Service;

import com.apicartaocredito.wl.Model.Dtos.AtualizarDadosClientes;
import com.apicartaocredito.wl.Model.Dtos.ClienteRequest;
import com.apicartaocredito.wl.Model.Dtos.ClienteResponse;
import com.apicartaocredito.wl.Exception.*;
import com.apicartaocredito.wl.Model.ClienteEntity;
import com.apicartaocredito.wl.Repository.IClienteRepository;
import com.apicartaocredito.wl.enums.StatusCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteResponse criarCliente (ClienteRequest request) {

        if(clienteRepository.existsByCpf(request.cpf())) {
            throw new CpfJaCadastradoException();
        }

        if(clienteRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoEception();
        }

        if (clienteRepository.existsByTelefone(request.telefone())) {
            throw new TelefoneJaCadastradoException();
        }

        ClienteEntity cliente = new ClienteEntity();

        cliente.setNome(request.nome());
        cliente.setCpf(request.cpf());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());
        cliente.setAtivo(true);
        cliente.setSalario(request.salario());
        cliente.setStatusCliente(StatusCliente.ATIVO);


        ClienteEntity clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponse(clienteSalvo);

    }

    public ClienteResponse buscarPorId(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

                return new ClienteResponse(cliente);
    }

    public List<ClienteResponse> listarClientes() {

        List<ClienteEntity> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(ClienteResponse::new)
                .toList();
    }

   public ClienteResponse atualizarDadosCliente(Long id, AtualizarDadosClientes dados) {

        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        if (dados.nome() != null) {
            cliente.setNome(dados.nome());
        }

        if (dados.telefone() != null){
            cliente.setTelefone(dados.telefone());
        }

        if (dados.email() != null) {
            cliente.setEmail(dados.email());
        }

        clienteRepository.save(cliente);

        return new ClienteResponse(cliente);

   }

public ClienteResponse desativarCliente(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

    if (!cliente.getAtivo()) {
        throw new ClienteInativoException(id);
    }
        cliente.setAtivo(false);

        cliente.setStatusCliente(StatusCliente.INATIVO);

        clienteRepository.save(cliente);

        return new ClienteResponse(cliente);
}
}



