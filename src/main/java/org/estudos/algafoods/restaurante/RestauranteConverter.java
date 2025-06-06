package org.estudos.algafoods.restaurante;

import org.estudos.algafoods.cozinha.CozinhaDto;

import java.time.LocalDateTime;

import static org.estudos.algafoods.cozinha.CozinhaConverter.toCozinhaDto;
import static org.estudos.algafoods.cozinha.CozinhaConverter.toCozinhaEntity;

public class RestauranteConverter {

    private RestauranteConverter() {
    }

    public static RestauranteDto toRestauranteDto(Restaurante restaurante) {
        return new RestauranteDto(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTaxaFrete(),
                restaurante.getAtivo(),
                restaurante.getDataCadastro(),
                restaurante.getDataAtualizacao(),
                toCozinhaDto(restaurante.getCozinha())
        );
    }

    public static Restaurante toRestauranteEntity(RestauranteDto dto, CozinhaDto cozinha) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(dto.id());
        restaurante.setNome(dto.nome());
        restaurante.setTaxaFrete(dto.taxaFrete());
        restaurante.setAtivo(dto.ativo());
        restaurante.setDataCadastro(LocalDateTime.now());
        restaurante.setDataAtualizacao(LocalDateTime.now());
        restaurante.setCozinha(toCozinhaEntity(cozinha));
        return restaurante;
    }
}
