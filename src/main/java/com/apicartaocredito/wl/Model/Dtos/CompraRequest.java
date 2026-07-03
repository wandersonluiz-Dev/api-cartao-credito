package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CompraRequest(
        @NotNull
        Long cartaoId,
        @NotBlank
        String nomeCliente,
        @NotBlank
        String descricao,
        @NotNull
        @Positive
        BigDecimal valor,
        @NotNull
        Integer parcelas) {
}
