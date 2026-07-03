package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.NotNull;

public record FaturaRequest(
        @NotNull
        Long cartaoId) {
}
