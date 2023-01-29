package com.universidadesapi.universidadesapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "estados")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "estado",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Municipio> municipios;



}