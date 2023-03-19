package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.Abstracs.ContainImage;
import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    public MunicipioRepository municipioRepository;
    @Autowired
    public ImageService imageService;

    @Autowired
    public StringUtils stringUtils;

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

        municipio.setSlug(stringUtils.slug(municipio.getNombre()));
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

        if(municipio.getImage() != null && municipio.getImage().getId() != null){
            Image image = imageService.updateImage(municipio.getImage(),"municipios");

            if(image !=null){
                image.setEncode(null);
                findResult.setImage(image);
            }else{
                return ResponseEntity.badRequest().build();
            }
            
        }

        findResult.setNombre(municipio.getNombre());
        findResult.setSlug(stringUtils.slug(municipio.getNombre()));
        findResult.setEstado(municipio.getEstado());

        Municipio result = municipioRepository.save(findResult);

        return ResponseEntity.ok(result);
    }


    public ResponseEntity<Municipio> delete(@PathVariable Long id){
        Optional<Municipio> optMunicipio = municipioRepository.findById(id);

        if(optMunicipio.isEmpty())return ResponseEntity.notFound().build();


        Municipio municipioSearch = optMunicipio.get();
        boolean imageDelete = imageService.deleteImage(municipioSearch.getImage());

        if(!imageDelete)return ResponseEntity.badRequest().build();

        List<Universidad> universidades = new ArrayList<Universidad>();
        municipioSearch.getUniversidades().stream().map(universidad -> universidad).forEach(uni ->{
            universidades.add(uni);
        });
        ContainImage[] arrayUniversidades = new ContainImage[universidades.size()];
        arrayUniversidades = universidades.toArray(arrayUniversidades);


        List<Carrera> carreras = new ArrayList<Carrera>();
        for(Universidad universidad: universidades){

            universidad.getCarreras().stream().map(carrera -> carrera).forEach(carrera ->{
                carreras.add(carrera);
            });;
        }
        ContainImage[] arrayCarreras = new ContainImage[carreras.size()];
        arrayCarreras = carreras.toArray(arrayCarreras);

        imageService.deleteAllImage(arrayUniversidades);
        imageService.deleteAllImage(arrayCarreras);

        municipioRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
