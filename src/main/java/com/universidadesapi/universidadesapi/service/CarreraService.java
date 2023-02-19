package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.entity.Image;
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
    @Autowired
    ImageService imageService;


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

        Image image = imageService.saveImage(carrera.getImage(),"carreras");
        if(image !=null){
            image.setEncode(null);
            carrera.setImage(image);
        }



        Carrera result = carreraRepository.save(carrera);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Carrera> update(Carrera carrera, Long id){
        if(carrera.getId() != null){
            if(carrera.getId() !=id)return ResponseEntity.badRequest().build();
        }

        Optional<Carrera> find = carreraRepository.findById(id);
        if(find.isEmpty())return ResponseEntity.notFound().build();

        Carrera findResult = find.get();

        if(carrera.getImage() != null && carrera.getImage().getId() != null){
            Image image = imageService.updateImage(carrera.getImage(),"carreras");

            if(image !=null){
                image.setEncode(null);
                findResult.setImage(image);
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        } 


        carrera.setId(findResult.getId());
        carrera.setUniversidad(findResult.getUniversidad());

        Carrera result = carreraRepository.save(carrera);
        return ResponseEntity.ok(result);
    }



    public ResponseEntity<Carrera> delete(Long id){
        Optional<Carrera> optCarrera = carreraRepository.findById(id);
        if(!optCarrera.isPresent())return ResponseEntity.notFound().build();

        Carrera carrera = optCarrera.get();
        boolean deleteImage = imageService.deleteImage(carrera.getImage());

        if(!deleteImage)return ResponseEntity.badRequest().build();


        carreraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
