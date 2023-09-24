package com.example.crud.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUser(
        @NotBlank
        String name,
        @NotBlank
        String location,
        @NotNull
        Integer nif) {
}
