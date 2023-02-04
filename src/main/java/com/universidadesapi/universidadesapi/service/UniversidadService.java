package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversidadService {


    @Autowired
    UniversidadRepository universidadRepository;

    public List<Universidad> getAll(){
        return  universidadRepository.findAll();
    }

    public ResponseEntity<Universidad> save(Universidad universidad){
        if(universidad.getId() != null)return ResponseEntity.badRequest().build();

        Universidad result =universidadRepository.save(universidad);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Universidad> getOne(Long id){

        Optional<Universidad> find = universidadRepository.findById(id);

        if(find.isEmpty())return ResponseEntity.notFound().build();
        Universidad result = find.get();

        return ResponseEntity.ok(result);
    }


    public ResponseEntity<Universidad> update(Universidad universidad, Long id){
        if(universidad.getId() != null){
            if (universidad.getId() != id)return ResponseEntity.badRequest().build();
        }

        Optional<Universidad> find = universidadRepository.findById(id);

        if(find.isEmpty())return ResponseEntity.notFound().build();


        Universidad findSave = find.get();
        universidad.setMunicipio(findSave.getMunicipio());
        universidad.setCarreras(findSave.getCarreras());

        return ResponseEntity.ok(universidadRepository.save(universidad));
    }

    public ResponseEntity<Universidad> delete(Long id){
        if(!universidadRepository.existsById(id))return ResponseEntity.notFound().build();

        universidadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
