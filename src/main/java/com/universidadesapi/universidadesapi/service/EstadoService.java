package com.universidadesapi.universidadesapi.service;

import com.universidadesapi.universidadesapi.Abstracs.ContainImage;
import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.entity.Estado;
import com.universidadesapi.universidadesapi.entity.Image;
import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.EstadoRepository;
import com.universidadesapi.universidadesapi.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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


        Image image = imageService.saveImage(estado.getImage(),"estados");
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

        List<Municipio> municipios = optEstado.get().getMunicipios();
        ContainImage[] arrayMunicipios = new ContainImage[municipios.size()];
        arrayMunicipios = municipios.toArray(arrayMunicipios);
        
        //1.- Creo una Lista de universidades
        List<Universidad> universidades = new ArrayList<Universidad>();
        //2.-  Por cada municipio tomo sus universidades con un map
        //3.-  Con el forEach agrego la universidad a la Lista de universidades
        for(Municipio municipios2:municipios){
            municipios2.getUniversidades().stream().map(uni -> uni).forEach(uni -> {
                universidades.add(uni);
            });
        }
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

        imageService.deleteAllImage(arrayMunicipios);
        imageService.deleteAllImage(arrayUniversidades);
        imageService.deleteAllImage(arrayCarreras);

        if(!imageDelete)return ResponseEntity.badRequest().build();

        estadoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}

