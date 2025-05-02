package org.estudos.algafoods.cozinha;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cozinha")
@CrossOrigin
public class CozinhaController implements CozinhaSwagger {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @PostMapping("/create")
    @Transactional
    @Override
    public ResponseEntity<CozinhaDto> create(@RequestBody @Valid CozinhaDto cozinha) {
        CozinhaDto created = cozinhaService.create(cozinha);
        return ResponseEntity.created(URI.create("/cozinha/create/" + created.id())).body(created);
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<List<CozinhaDto>> list() {
        return ResponseEntity.ok(cozinhaService.listar());
    }

    @GetMapping("/pagination")
    @Override
    public ResponseEntity<Page<CozinhaDto>> pagination(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(cozinhaService.pagination(pageable));
    }

    @GetMapping("/detail/{id}")
    @Override
    public ResponseEntity<CozinhaDto> detail(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.detail(id));
    }

    @PutMapping("/update/{id}")
    @Transactional
    @Override
    public ResponseEntity<CozinhaDto> update(@PathVariable Long id, @RequestBody @Valid CozinhaDto personCreateDto) {
        return ResponseEntity.ok(cozinhaService.update(id, personCreateDto));
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<CozinhaDto>> searchList(@RequestParam String nome) {
        return ResponseEntity.ok(cozinhaService.search(nome));
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<Cozinha> delete(@PathVariable Long id) {
        cozinhaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
