package com.queijos_finos.main.repository;

import com.queijos_finos.main.model.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.queijos_finos.main.model.Fornecedor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Query("select f from Fornecedor f where f.nome=:nome")
    Fornecedor findByNome(String nome);

    Page<Fornecedor> findAll(Pageable pageable);

    List<Fornecedor> findByNomeContainingIgnoreCase(String query);
    
    @Modifying
    @Query(value = "DELETE FROM fornecedor_has_propriedade WHERE fornecedor_id_fornecedor = :forId", nativeQuery = true)
    void deleteFornecedorPropriedadeRelacionamento(Long forId);
}
