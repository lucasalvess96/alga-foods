package org.estudos.algafoods.cozinha;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("cozinha")
@CrossOrigin
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CozinhaDto> createCozinha(@RequestBody @Valid CozinhaDto cozinha) {
        return ResponseEntity.created(URI.create("/create/")).body(cozinhaService.create(cozinha));
    }
}
