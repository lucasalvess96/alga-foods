package org.estudos.algafoods.cozinha;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Representa uma cozinha no sistema")
public record CozinhaDto(

        @Schema(description = "ID da cozinha", example = "1")
        Long id,

        @Schema(description = "Nome da cozinha", example = "Italiana")
        @NotBlank(message = "O campo não pode ser nulo")
        @Size(min = 2, max = 800, message = "O tamanho deve ser entre 2 e 800 caracteres")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "O campo NOME deve conter apenas letras")
        String nome
) { }
