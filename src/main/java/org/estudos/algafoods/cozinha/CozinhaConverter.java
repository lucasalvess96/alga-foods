package org.estudos.algafoods.cozinha;

import org.estudos.algafoods.restaurante.RestauranteConverter;

public class CozinhaConverter {

    private CozinhaConverter() {
    }

    public static CozinhaDto toCozinhaDto(Cozinha cozinha) {
        return new CozinhaDto(
                cozinha.getId(),
                cozinha.getNome(),
                cozinha.getRestaurantes().stream()
                        .map(RestauranteConverter::toRestauranteDto)
                        .toList()
        );
    }
}
