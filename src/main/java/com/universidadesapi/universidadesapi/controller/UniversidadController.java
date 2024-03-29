package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.repository.UniversidadRepository;
import com.universidadesapi.universidadesapi.service.UniversidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/universidades")
@CrossOrigin("*")
public class UniversidadController {

    @Autowired
    UniversidadService universidadService;

    @Autowired
    UniversidadRepository universidadRepository;


    @GetMapping("/slug/{slug}")
    public Universidad findBySlug(@PathVariable String slug){
        return universidadService.findBySlug(slug);
    }

    @GetMapping("/")
    public List<Universidad> getAll(){
        return universidadService.getAll();
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Universidad> save(@Valid @RequestBody Universidad universidad){
        return universidadService.save(universidad);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Universidad> getOne(@PathVariable Long id){
        return universidadService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Universidad> update(@Valid @RequestBody Universidad universidad, @PathVariable Long id){
        return  universidadService.update(universidad,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Universidad> delete(@PathVariable Long id){
        return universidadService.delete(id);
    }


   @GetMapping("/municipio/{id}")
   public ResponseEntity<List<Universidad>> findByMunicipioId(@PathVariable Long id){
    return ResponseEntity.ok(universidadRepository.findByMunicipioId(id));
   }


   @GetMapping("/buscador/{text}")
   public ResponseEntity<List<Universidad>> tln(@PathVariable String text){
    return ResponseEntity.ok(universidadRepository.findByNombreContainingIgnoreCase(text));
   }

    
}
