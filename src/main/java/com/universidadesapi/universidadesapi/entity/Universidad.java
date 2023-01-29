package com.universidadesapi.universidadesapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "universidades")
public class Universidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    private String phone;

    private Long likes;

    private Double latitud;

    private Double longitud;
    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;


    @OneToMany(mappedBy = "universidad",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Carrera> carreras;


}