package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.repository.MunicipioRepository;
import com.universidadesapi.universidadesapi.service.MunicipioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipios")
@CrossOrigin("*")
public class MunicipioController {

    @Autowired
    public MunicipioService municipioService;

    @Autowired
    public MunicipioRepository municipioRepository;


    @GetMapping("/")
    public List<Municipio> getAll(){
        return municipioService.getAll();
    }


    @PostMapping("/")
    public ResponseEntity<Municipio> save(@Valid @RequestBody Municipio municipio){
        return municipioService.save(municipio);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Municipio> getOne(@PathVariable Long id){
        return municipioService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> update(@Valid @RequestBody Municipio municipio, @PathVariable Long id){
        return municipioService.update(municipio,id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Municipio> deletE(@PathVariable Long id){
        return municipioService.delete((id));
    }


    @GetMapping("/slug/{slug}")
    public ResponseEntity<Municipio> findBySlug(@PathVariable String slug){
        Optional<Municipio> optionalMunicipio = municipioRepository.findBySlug(slug);
        if(!optionalMunicipio.isPresent())return ResponseEntity.notFound().build();

        Municipio resultado = (Municipio) optionalMunicipio.get();
        return ResponseEntity.ok(resultado);
    }



    

    @GetMapping("/estado/{id}")
    public ResponseEntity<List<Municipio>> findByEstadoId(@PathVariable Long id){
        return ResponseEntity.ok(municipioRepository.findByEstadoId(id));
    }

}
