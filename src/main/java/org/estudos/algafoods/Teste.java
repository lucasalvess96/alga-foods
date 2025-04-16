package org.estudos.algafoods;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {

    @GetMapping("/teste")
    public String hello() {
        return "Hello World";
    }
}
