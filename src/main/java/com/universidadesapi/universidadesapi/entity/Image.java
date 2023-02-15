package com.universidadesapi.universidadesapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;


    @NotNull
    @NotBlank
    @Getter @Setter
    private String ruta;

    @NotNull
    @NotBlank
    @Getter @Setter

    private String tipo;


    @NotBlank
    @NotNull
    @Getter @Setter
    private String nombre;



    @Transient
    @Getter @Setter
    @JsonInclude(value = Include.NON_NULL)
    private String encode;


    @OneToOne(mappedBy = "image",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Estado estado;


    
}
