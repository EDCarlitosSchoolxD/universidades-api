package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Municipio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstadoSlug(String slug);

    List<Municipio> findByEstadoId(Long id);

    Optional<Municipio> findBySlug(String sluString);

}
