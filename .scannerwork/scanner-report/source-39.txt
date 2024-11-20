package com.queijos_finos.main.repository;

import com.queijos_finos.main.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findById(Long id);

    Page<Curso> findAll(Pageable pageable);

    List<Curso> findByNomeContainingIgnoreCase(String nome);
    
    @Modifying
    @Query(value = "DELETE FROM propriedade_has_curso WHERE curso_id_curso = :cursoId", nativeQuery = true)
    void deleteCursoPropriedadeRelacionamento(Long cursoId);
    
   
}





