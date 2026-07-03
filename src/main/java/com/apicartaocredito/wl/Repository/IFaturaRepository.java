package com.apicartaocredito.wl.Repository;

import com.apicartaocredito.wl.Model.FaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFaturaRepository extends JpaRepository<FaturaEntity, Long> {

    List<FaturaEntity> findByCartaoCreditoId(Long cartaoId);
}
