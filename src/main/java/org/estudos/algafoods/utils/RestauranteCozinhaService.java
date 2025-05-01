package org.estudos.algafoods.utils;

import org.estudos.algafoods.cozinha.CozinhaRepository;
import org.estudos.algafoods.restaurante.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class RestauranteCozinhaService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    public RestauranteCozinhaService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

}
