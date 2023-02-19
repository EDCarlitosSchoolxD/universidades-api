package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    public MunicipioRepository municipioRepository;
    @Autowired
    public ImageService imageService;


    public List<Municipio> getAll(){
        return municipioRepository.findAll();
    }

    public ResponseEntity<Municipio> save(Municipio municipio){
        if(municipio.getId() != null)return ResponseEntity.badRequest().build();

        Image image = imageService.saveImage(municipio.getImage(),"municipios");
        if(image !=null){
            image.setEncode(null);
            municipio.setImage(image);
        }

        Municipio result = municipioRepository.save(municipio);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Municipio> getOne(Long id){
        Optional<Municipio> find = municipioRepository.findById(id);

        if(!find.isPresent())return ResponseEntity.notFound().build();

        return ResponseEntity.ok(find.get());
    }

    public ResponseEntity<Municipio> update(Municipio municipio, Long id){

        if(municipio.getId() != null){
            if(municipio.getId() != id) return ResponseEntity.badRequest().build();
        }

        Optional<Municipio> find = municipioRepository.findById(id);

        if(!find.isPresent())return ResponseEntity.notFound().build();

        Municipio findResult = find.get();
        findResult.setNombre(municipio.getNombre());
        findResult.setEstado(municipio.getEstado());

        Municipio result = municipioRepository.save(findResult);

        return ResponseEntity.ok(result);
    }


    public ResponseEntity<Municipio> delete(@PathVariable Long id){
        if(!municipioRepository.existsById(id))return ResponseEntity.notFound().build();

        municipioRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
