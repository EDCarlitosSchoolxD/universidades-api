package com.universidadesapi.universidadesapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "carreras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Carrera{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 200,nullable = false)
    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")

    private String objetivo;

    @Column(columnDefinition = "TEXT")
    private String aprendizaje;


    @Column(columnDefinition = "TEXT")
    private String trabajo;


    @Column(columnDefinition = "TEXT")
    private String perfilIngreso;

    @Column(columnDefinition = "TEXT")
    private String perfilEgreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_plan_estudio")
    private Image planEstudio;


    private Long likes = 0L;

    @ManyToOne
    @JoinColumn(name = "id_universidad")
    private  Universidad universidad;
}