package com.universidadesapi.universidadesapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "municipios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 200)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_state")
    private Estado estado;


    @OneToMany(mappedBy = "municipio",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Universidad> universidades;

}