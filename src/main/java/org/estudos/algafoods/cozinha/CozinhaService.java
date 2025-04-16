package org.estudos.algafoods.cozinha;

import org.estudos.algafoods.restaurante.RestauranteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    private final RestauranteService restauranteService;

    public CozinhaService(CozinhaRepository cozinhaRepository, RestauranteService restauranteService) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteService = restauranteService;
    }

    public CozinhaDto create(CozinhaDto cozinhaDto) {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(cozinhaDto.nome());
        cozinha.setRestaurantes(restauranteService.create(cozinhaDto.restaurantes()));
        return CozinhaConverter.toCozinhaDto(cozinhaRepository.save(cozinha));
    }

    public Optional<CozinhaDto> detail(Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cozinha não encontrada"));
        return Optional.of(CozinhaConverter.toCozinhaDto(cozinha));
    }
}
