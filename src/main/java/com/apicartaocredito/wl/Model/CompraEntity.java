package com.apicartaocredito.wl.Model;

import com.apicartaocredito.wl.enums.StatusCompra;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataCompra;
    private String nomeCliente;
    private int parcelas;
    private BigDecimal valorParcelas;

    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    @JsonBackReference("cartao-compras")
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoCreditoEntity cartao;


    @ManyToOne
    @JoinColumn(name = "fatura_id")
    private FaturaEntity fatura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
}
