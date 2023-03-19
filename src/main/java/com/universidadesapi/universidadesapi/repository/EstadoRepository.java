package com.universidadesapi.universidadesapi.repository;

import com.universidadesapi.universidadesapi.entity.Estado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findBySlug(String sluString);

}