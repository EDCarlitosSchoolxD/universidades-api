package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {


    Universidad findBySlug(String slug);

}