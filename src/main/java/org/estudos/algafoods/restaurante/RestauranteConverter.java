package org.estudos.algafoods.restaurante;

import org.estudos.algafoods.cozinha.Cozinha;

import java.time.LocalDateTime;

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
                restaurante.getCozinha() != null ? restaurante.getCozinha().getId() : null
        );
    }

    public static Restaurante toRestauranteEntity(RestauranteDto dto, Cozinha cozinha) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(dto.id());
        restaurante.setNome(dto.nome());
        restaurante.setTaxaFrete(dto.taxaFrete());
        restaurante.setAtivo(dto.ativo());
        restaurante.setDataCadastro(LocalDateTime.now());
        restaurante.setDataAtualizacao(LocalDateTime.now());
        restaurante.setCozinha(cozinha);
        return restaurante;
    }
}
