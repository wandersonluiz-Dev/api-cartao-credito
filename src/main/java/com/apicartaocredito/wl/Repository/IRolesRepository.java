package com.apicartaocredito.wl.Repository;

import com.apicartaocredito.wl.Model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByAuthority(String role);

}
