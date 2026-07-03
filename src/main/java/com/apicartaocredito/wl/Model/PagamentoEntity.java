package com.apicartaocredito.wl.Model;


import com.apicartaocredito.wl.enums.FormaDePagamento;
import com.apicartaocredito.wl.enums.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorPago;
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @JsonBackReference("fatura_pagamentos")
    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private FaturaEntity fatura;


}
