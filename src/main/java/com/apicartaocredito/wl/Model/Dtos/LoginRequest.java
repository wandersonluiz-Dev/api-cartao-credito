package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
         String email,
        @NotBlank
        String senha
) {
}
