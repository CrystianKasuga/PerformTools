package com.queijos_finos.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.queijos_finos.main.controller.ContratosController;
import com.queijos_finos.main.model.Contrato;
import com.queijos_finos.main.model.Usuarios;


public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    @Query("SELECT DISTINCT contrato FROM Contrato contrato JOIN FETCH contrato.propriedade propriedade WHERE propriedade.nomePropriedade = :nome")
    Page<Contrato> findByNomProp(Pageable pageable, String nome);


    Page<Contrato> findBynome(Pageable pageable, String nome);

    Page<Contrato> findAll(Pageable pageable);
}
