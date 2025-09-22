package org.estudos.algafoods.restaurante;

import org.estudos.algafoods.cozinha.CozinhaDto;
import org.estudos.algafoods.cozinha.CozinhaService;
import org.estudos.algafoods.excecao.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        CozinhaDto cozinha = cozinhaService.detail(dto.cozinha().id());
        Restaurante restaurante = restauranteRepository.save(toRestauranteEntity(dto, cozinha));
        return toRestauranteDto(restaurante);
    }

    public List<RestauranteDto> listar() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteConverter::toRestauranteDto)
                .toList();
    }

    public Page<RestauranteDto> pagination(Pageable pageable) {
        return restauranteRepository.findAll(pageable)
                .map(RestauranteConverter::toRestauranteDto);
    }

    public RestauranteDto detail(Long cozinhaId) {
        return restauranteRepository.findById(cozinhaId)
                .map(RestauranteConverter::toRestauranteDto)
                .orElseThrow(() -> new ResourceNotFoundException("Informações não encontradas para: " + cozinhaId));
    }

    public List<RestauranteDto> search(String nome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(RestauranteConverter::toRestauranteDto).toList();
    }
}
