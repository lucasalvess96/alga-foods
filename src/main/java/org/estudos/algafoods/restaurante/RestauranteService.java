package org.estudos.algafoods.restaurante;

import org.estudos.algafoods.cozinha.Cozinha;
import org.estudos.algafoods.cozinha.CozinhaService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.estudos.algafoods.restaurante.RestauranteConverter.toRestauranteDto;
import static org.estudos.algafoods.restaurante.RestauranteConverter.toRestauranteEntity;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public RestauranteDto create(RestauranteDto dto) {
        Cozinha cozinha = cozinhaService.detail(dto.cozinha().id());
        Restaurante restaurante = restauranteRepository.save(toRestauranteEntity(dto, cozinha));
        return toRestauranteDto(restaurante);
    }

    public List<RestauranteDto> listar() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteConverter::toRestauranteDto)
                .toList();
    }
}
