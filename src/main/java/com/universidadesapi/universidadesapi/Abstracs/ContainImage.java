package com.universidadesapi.universidadesapi.Abstracs;

import com.universidadesapi.universidadesapi.Interfaces.ContainImageInterface;
import com.universidadesapi.universidadesapi.entity.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class ContainImage implements ContainImageInterface {
    

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image")
    protected Image image;

}
