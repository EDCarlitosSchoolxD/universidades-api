package com.universidadesapi.universidadesapi.entity;

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

    @ManyToOne
    @JoinColumn(name = "id_state")
    private Estado estado;


    @OneToMany(mappedBy = "municipio",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Universidad> universidades;

}