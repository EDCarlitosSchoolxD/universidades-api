package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Carrera;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    List<Carrera> findByUniversidadId(Long id);
    

}