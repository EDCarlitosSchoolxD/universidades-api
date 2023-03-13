package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.Abstracs.ContainImage;
import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversidadService {


    @Autowired
    UniversidadRepository universidadRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    StringUtils stringUtils;

    public List<Universidad> getAll(){
        return  universidadRepository.findAll();
    }

    public Universidad findBySlug(String sluString){
        return findBySlug(sluString);
    }

    public ResponseEntity<Universidad> save(Universidad universidad){
        if(universidad.getId() != null)return ResponseEntity.badRequest().build();


        Image image = universidad.getImage();
        if(image !=null){
            image = imageService.saveImage(universidad.getImage(),"universidades");
            image.setEncode(null);
            universidad.setImage(image);
        }

        universidad.setSlug(stringUtils.slug(universidad.getNombre()));
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
        Universidad findResult = find.get();

        if(universidad.getImage() != null && universidad.getImage().getId() != null){
            Image image = imageService.updateImage(universidad.getImage(),"universidades");

            if(image !=null){
                image.setEncode(null);
                findResult.setImage(image);
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        }



        Universidad findSave = find.get();
        universidad.setId(findSave.getId());
        universidad.setSlug(stringUtils.slug(universidad.getNombre()));
        universidad.setMunicipio(findSave.getMunicipio());
        universidad.setCarreras(findSave.getCarreras());

        return ResponseEntity.ok(universidadRepository.save(universidad));
    }

    public ResponseEntity<Universidad> delete(Long id){

        Optional<Universidad> optUniversidad = universidadRepository.findById(id);
        if(!optUniversidad.isPresent())return ResponseEntity.notFound().build();

        Universidad universidadSearch = optUniversidad.get();
        boolean deleteImage = imageService.deleteImage(universidadSearch.getImage());
        if(!deleteImage)return ResponseEntity.badRequest().build();

        //Eliminamos todas las imagenes de las todas las carreras de esta universidad
        List<Carrera> carreras = new ArrayList<Carrera>();
        universidadSearch.getCarreras().stream().map(carrera -> carrera).forEach(carrera ->{
            carreras.add(carrera);
        });
        ContainImage[] arrayCarreras = new ContainImage[carreras.size()];
        arrayCarreras = carreras.toArray(arrayCarreras);


        imageService.deleteAllImage(arrayCarreras);

       
        universidadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
