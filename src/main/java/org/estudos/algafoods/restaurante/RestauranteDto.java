package org.estudos.algafoods.restaurante;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.estudos.algafoods.cozinha.CozinhaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RestauranteDto(
        Long id,
        String nome,
        BigDecimal taxaFrete,
        Boolean ativo,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataAtualizacao,
        CozinhaDto cozinha
) { }
