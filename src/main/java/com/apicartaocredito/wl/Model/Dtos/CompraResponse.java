package com.apicartaocredito.wl.Model.Dtos;

import com.apicartaocredito.wl.Model.CompraEntity;
import com.apicartaocredito.wl.enums.StatusCompra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraResponse(Long id,
                             String nomeCliente,
                             String descricao,
                             BigDecimal valor,
                             Integer parcelas,
                             BigDecimal valorParcela,
                             LocalDateTime dataCompra,
                             StatusCompra statusCompra) {



    public CompraResponse(CompraEntity compra) {
        this(
                compra.getId(),
                compra.getNomeCliente(),
                compra.getDescricao(),
                compra.getValor(),
                compra.getParcelas(),
                compra.getValorParcelas(),
                compra.getDataCompra(),
                compra.getStatusCompra()
        );
    }
 }
