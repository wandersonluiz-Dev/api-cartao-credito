package com.apicartaocredito.wl.Repository;

import com.apicartaocredito.wl.Model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);

}
