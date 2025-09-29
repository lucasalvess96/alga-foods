package org.estudos.algafoods.restaurante;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("restaurante")
@CrossOrigin
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<RestauranteDto> create(@RequestBody @Valid RestauranteDto restauranteDto) {
        return ResponseEntity.created(URI.create("/create/")).body(restauranteService.create(restauranteDto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RestauranteDto>> listarCozinhas() {
        return ResponseEntity.ok(restauranteService.listar());
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<RestauranteDto>> pagination(Pageable pageable) {
        return ResponseEntity.ok(restauranteService.pagination(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<RestauranteDto> detail(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.detail(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestauranteDto>> searchList(@RequestParam String nome) {
        return ResponseEntity.ok(restauranteService.search(nome));
    }

    @GetMapping("/search/pagination")
    public ResponseEntity<Page<RestauranteDto>> searchPagination(@RequestParam String nome, Pageable pageable) {
        return ResponseEntity.ok(restauranteService.searchPagination(nome, pageable));
    }
}
