package com.universidadesapi.universidadesapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "universidades")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")


public class Universidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull
    @NotBlank
    @Size(min = 10,max = 255)
    private String nombre;

    @NotNull
    @NotBlank
    @Size(min = 10,max = 255)
    private String phone;

    @NotNull
    private Long likes = 0L;
    @NotNull
    private Double latitud;


    @NotNull
    private Double longitud;
    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;


    @OneToMany(mappedBy = "universidad",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Carrera> carreras;


}