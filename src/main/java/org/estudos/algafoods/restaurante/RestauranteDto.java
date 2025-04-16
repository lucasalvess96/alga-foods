package org.estudos.algafoods.restaurante;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RestauranteDto(Long id, String nome, BigDecimal taxaFrete, Boolean ativo, LocalDateTime dataCadastro,
                             LocalDateTime dataAtualizacao, Long cozinhaId) { }
