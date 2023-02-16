package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.entity.Estado;
import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.repository.EstadoRepository;
import com.universidadesapi.universidadesapi.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ImageService imageService;

    public List<Estado> getAll(){
        return  estadoRepository.findAll();
    }

    public ResponseEntity<Estado> save(Estado estado){
        if(estado.getId() != null) ResponseEntity.badRequest().build();


        Image image = imageService.saveImage(estado.getImage());
        if(image != null){
            image.setEncode(null);
            estado.setImage(image);
        }
        

        

        Estado resultado = estadoRepository.save(estado);
        return ResponseEntity.ok(resultado);
    }

    public ResponseEntity<Estado> getOne(Long id){
        Optional<Estado> optionalEstado = estadoRepository.findById(id);
        if(!optionalEstado.isPresent())return ResponseEntity.notFound().build();

        Estado resultado = (Estado) optionalEstado.get();
        return ResponseEntity.ok(resultado);
    }



    public ResponseEntity<Estado> update(Estado estado, Long id){
        if(estado.getId() != null) {
            if (estado.getId() != id)return ResponseEntity.badRequest().build();
        }

        Optional<Estado> optSearch = estadoRepository.findById(id);
        if(!optSearch.isPresent())return ResponseEntity.notFound().build();

        Estado resultadoSearch = optSearch.get();
        resultadoSearch.setNombre(estado.getNombre());


        if(estado.getImage() != null && estado.getImage().getId() != null){
            Image image = imageService.updateImage(estado.getImage());

            if(image !=null){
                image.setEncode(null);
                resultadoSearch.setImage(image);
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        }

        

        Estado resultado = estadoRepository.save(resultadoSearch);
        return ResponseEntity.ok(resultado);

    }


    public ResponseEntity<Estado> delete(Long id){

        Optional<Estado> optEstado = estadoRepository.findById(id);
        if(!optEstado.isPresent())return ResponseEntity.notFound().build();



        boolean imageDelete = imageService.deleteImage(optEstado.get().getImage());

        if(!imageDelete)return ResponseEntity.badRequest().build();

        estadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

