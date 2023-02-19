package com.universidadesapi.universidadesapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.universidadesapi.universidadesapi.Abstracs.ContainImage;
import com.universidadesapi.universidadesapi.Interfaces.ContainImageInterface;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "municipios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Municipio extends ContainImage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 200)
    @NotNull
    @NotBlank(message = "El nombre del municipio es necesario")
    @Size(min = 10,max = 255)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_state")
    private Estado estado;


    @OneToMany(mappedBy = "municipio",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Universidad> universidades;


}