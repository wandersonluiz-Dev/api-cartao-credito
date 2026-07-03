package com.apicartaocredito.wl.Model;

import com.apicartaocredito.wl.enums.StatusCartao;
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
@Table(name = "cartao_credito")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoCreditoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    private String nomeTitular;

    @Column(unique = true)
    private String cvv;

    private LocalDate validade;

    private BigDecimal limiteTotal;

    private BigDecimal limiteDisponivel;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    public String getNumeroMascarado() {

        return "**** **** **** " + numero.substring(numero.length() - 4);
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @JsonManagedReference("cartao-compras")
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompraEntity> compras;

    @JsonManagedReference("cartao-faturas")
    @OneToMany(mappedBy = "cartaoCredito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FaturaEntity> faturas;

}
