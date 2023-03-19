package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Universidad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {


    Universidad findBySlug(String slug);

    List<Universidad> findByMunicipioSlug(String slug);

    List<Universidad> findByMunicipioId(Long id);


}