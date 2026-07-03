package com.apicartaocredito.wl.Model.Dtos;

import jakarta.validation.constraints.*;


public record RegisterRequest(
                              @NotBlank
                              @Email
                              String email,

                               @NotBlank
                               String senha) {
}
