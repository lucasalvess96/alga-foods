package org.estudos.algafoods.cozinha;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.estudos.algafoods.cozinha.CozinhaConverter.toCozinhaDto;
import static org.estudos.algafoods.cozinha.CozinhaConverter.toCozinhaEntity;

@Service
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public CozinhaDto create(CozinhaDto cozinhaDto) {
        Cozinha cozinhaEntity = toCozinhaEntity(cozinhaDto);
        return toCozinhaDto(cozinhaRepository.save(cozinhaEntity));
    }

    public List<CozinhaDto> listar() {
        return cozinhaRepository.findAll().stream()
                .map(CozinhaConverter::toCozinhaDto)
                .toList();
    }

    public Cozinha detail(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntityNotFoundException("Cozinha não encontrada com ID: " + cozinhaId));
    }
}
