package org.estudos.algafoods.restaurante;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.estudos.algafoods.cozinha.CozinhaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Representa um restaurante no sistema")
public record RestauranteDto(
        @Schema(description = "ID do restaurante", example = "1")
        Long id,

        @Schema(description = "Nome do restaurante", example = "Restaurante Italiano")
        String nome,

        @Schema(description = "Taxa de frete do restaurante", example = "10.50")
        BigDecimal taxaFrete,

        @Schema(description = "Indica se o restaurante está ativo", example = "true")
        Boolean ativo,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(description = "Data de cadastro do restaurante", example = "01/01/2023 10:00:00")
        LocalDateTime dataCadastro,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(description = "Data da última atualização do restaurante", example = "02/01/2023 11:30:00")
        LocalDateTime dataAtualizacao,

        @Schema(description = "Cozinha do restaurante")
        CozinhaDto cozinha
) { }
