package org.estudos.algafoods.cozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContainingIgnoreCase(String nome);
}
