package org.estudos.algafoods.cozinha;

import org.estudos.algafoods.excecao.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CozinhaDto> pagination(Pageable pageable) {
        return cozinhaRepository.findAll(pageable)
                .map(CozinhaConverter::toCozinhaDto);
    }

    public CozinhaDto detail(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .map(CozinhaConverter::toCozinhaDto)
                .orElseThrow(() -> new ResourceNotFoundException("Informações não encontradas para: " + cozinhaId));
    }

    public CozinhaDto update(Long cozinhaId, CozinhaDto cozinhaDto) {
        if (!cozinhaRepository.existsById(cozinhaId)) {
            cozinhaRepository.findById(cozinhaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Informações não encontradas para: " + cozinhaId));
        }

        Cozinha cozinhaAtualizada = toCozinhaEntity(cozinhaDto);
        cozinhaAtualizada.setId(cozinhaId);

        return toCozinhaDto(cozinhaRepository.save(cozinhaAtualizada));
    }

    public List<CozinhaDto> search(String nome) {
        return cozinhaRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(cozinha -> new CozinhaDto(cozinha.getId(), cozinha.getNome())).toList();
    }

    public void delete(Long id) {
        cozinhaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        cozinhaRepository.deleteById(id);
    }
}
