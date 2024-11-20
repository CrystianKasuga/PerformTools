package com.queijos_finos.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.queijos_finos.main.model.Usuarios;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    @Query("select u from Usuarios u where u.email=:email")
    Usuarios findByEmail(String email);

    Page<Usuarios> findAll(Pageable pageable);

    List<Usuarios> findByNomeContainingIgnoreCase(String query);
}
