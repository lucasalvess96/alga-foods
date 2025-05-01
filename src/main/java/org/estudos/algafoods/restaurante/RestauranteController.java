package org.estudos.algafoods.restaurante;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
}
