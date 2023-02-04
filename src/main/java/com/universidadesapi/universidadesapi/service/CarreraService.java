package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    CarreraRepository carreraRepository;


    public List<Carrera> getAll(){
        return carreraRepository.findAll();
    }

    public ResponseEntity<Carrera> getOne(Long id){
        Optional<Carrera> find = carreraRepository.findById(id);

        if(find.isEmpty())return ResponseEntity.notFound().build();

        return ResponseEntity.ok(find.get());
    }


    public ResponseEntity<Carrera> save(Carrera carrera){
        if(carrera.getId() != null)return  ResponseEntity.badRequest().build();

        Carrera result = carreraRepository.save(carrera);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Carrera> update(Carrera carrera, Long id){
        if(carrera.getId() != null){
            if(!carrera.getId().equals(id))return ResponseEntity.badRequest().build();
        }

        Optional<Carrera> find = carreraRepository.findById(id);
        if(find.isEmpty())return ResponseEntity.notFound().build();

        carrera.setId(find.get().getId());
        carrera.setUniversidad(find.get().getUniversidad());

        Carrera result = carreraRepository.save(carrera);
        return ResponseEntity.ok(result);
    }



    public ResponseEntity<Carrera> delete(Long id){
        if(!carreraRepository.existsById(id))return ResponseEntity.notFound().build();

        carreraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
