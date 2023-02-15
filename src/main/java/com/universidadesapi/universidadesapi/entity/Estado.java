package com.universidadesapi.universidadesapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @NotNull
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 10, max = 255)
    private String nombre;



    @OneToMany(mappedBy = "estado",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    @ToString.Exclude
    private List<Municipio> municipios;


    @OneToOne
    @JoinColumn(name = "id_image")
    private Image image;



}