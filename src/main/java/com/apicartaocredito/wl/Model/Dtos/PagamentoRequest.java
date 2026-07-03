package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagamentoRequest(
        @NotNull
        Long faturaId,
        @NotNull
        @Positive
        BigDecimal valorPago) {
}
