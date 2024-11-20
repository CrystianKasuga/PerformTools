package com.queijos_finos.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.queijos_finos.main.model.Tecnologias;

public interface TecnologiaRepository extends JpaRepository<Tecnologias, Long> {
    List<Tecnologias> findByNome(String nome);

    @Query(value = "SELECT t FROM Tecnologias t ORDER BY t.id DESC LIMIT 1")
    Tecnologias findFirstByOrderByIdDesc();
}
