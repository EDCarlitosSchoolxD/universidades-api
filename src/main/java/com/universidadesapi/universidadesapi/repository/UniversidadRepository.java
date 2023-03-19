package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Universidad;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {


    Universidad findBySlug(String slug);


    List<Universidad> findByMunicipioSlug(String slug);

    List<Universidad> findByMunicipioId(Long id);

    // @Query("SELECT u.* as universidades"+
    // "FROM Universidades u"+
    // "INNER JOIN Municipios m ON u.municipio_id = m.id"+
    // "INNER JOIN Estados e ON m.id_state = e.id"+
    // "WHERE u.nombre ILIKE '%?1%' OR"+
    // "e.nombre ILIKE '%?1%'"+
    // "OR  m.nombre ILIKE '%?1%';")
    // List<Universidad> findAll(String palabraClave);

    List<Universidad> findByNombreContainingIgnoreCase(String str);


}