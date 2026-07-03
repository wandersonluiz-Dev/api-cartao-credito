package com.apicartaocredito.wl.Repository;

import com.apicartaocredito.wl.Model.CartaoCreditoEntity;
import com.apicartaocredito.wl.Model.CompraEntity;
import com.apicartaocredito.wl.enums.StatusCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompraRepository extends JpaRepository<CompraEntity, Long> {

    List<CompraEntity> findByCartao(CartaoCreditoEntity cartao);

    List<CompraEntity> findByCartaoIdAndStatusCompra(Long cartaoId, StatusCompra statusCompra);
}
