package com.apicartaocredito.wl.Model;

import com.apicartaocredito.wl.enums.StatusFatura;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "faturas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FaturaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private String numeroCartao;
    private String mesReferencia;
    private BigDecimal valorTotal;
    private BigDecimal valorMinimo;
    private LocalDate vencimento;

    @Enumerated(EnumType.STRING)
    private StatusFatura statusFatura;

    @JsonBackReference("cartao-faturas")
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoCreditoEntity cartaoCredito;

    @OneToMany(mappedBy = "fatura",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompraEntity> compras;

    @JsonManagedReference("fatura_pagamentos")
    @OneToMany(mappedBy = "fatura")
    private List<PagamentoEntity> pagamentos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

}