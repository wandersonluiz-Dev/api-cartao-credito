package com.apicartaocredito.wl.Repository;

import com.apicartaocredito.wl.Model.FaturaEntity;
import com.apicartaocredito.wl.Model.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    List<PagamentoEntity> findByFatura(FaturaEntity fatura);
}
