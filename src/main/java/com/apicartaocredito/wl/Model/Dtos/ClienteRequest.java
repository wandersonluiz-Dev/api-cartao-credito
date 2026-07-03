package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ClienteRequest(

        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 digitos numéricos")
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        @Positive
        BigDecimal salario) {
}
