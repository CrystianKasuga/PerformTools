package com.queijos_finos.main.repository;

import com.queijos_finos.main.model.Amostra;
import com.queijos_finos.main.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long> {

}