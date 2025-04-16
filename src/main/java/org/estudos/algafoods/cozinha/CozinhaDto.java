package org.estudos.algafoods.cozinha;

import org.estudos.algafoods.restaurante.RestauranteDto;

import java.util.List;

public record CozinhaDto(Long id, String nome, List<RestauranteDto> restaurantes) { }
