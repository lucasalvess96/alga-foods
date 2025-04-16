package org.estudos.algafoods.restaurante;

import org.estudos.algafoods.cozinha.Cozinha;
import org.estudos.algafoods.cozinha.CozinhaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public List<Restaurante> create(List<RestauranteDto> dtos) {
        List<Restaurante> restaurantes = dtos.stream()
                .map(dto -> {
                    Cozinha cozinha = cozinhaService.detail(dto.cozinhaId());
                    return RestauranteConverter.toRestauranteEntity(dto, cozinha);
                })
                .toList();

        return restauranteRepository.saveAll(restaurantes);
    }
}
