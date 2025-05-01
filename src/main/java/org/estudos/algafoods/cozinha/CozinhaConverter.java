package org.estudos.algafoods.cozinha;

public class CozinhaConverter {

    private CozinhaConverter() {
    }

    public static CozinhaDto toCozinhaDto(Cozinha cozinha) {
        return new CozinhaDto(
                cozinha.getId(),
                cozinha.getNome()
        );
    }

    public static Cozinha toCozinhaEntity(CozinhaDto cozinhaDto) {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(cozinhaDto.id());
        cozinha.setNome(cozinhaDto.nome());
        return cozinha;
    }
}
