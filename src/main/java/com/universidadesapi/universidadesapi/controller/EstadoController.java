package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Estado;
import com.universidadesapi.universidadesapi.repository.EstadoRepository;
import com.universidadesapi.universidadesapi.service.EstadoService;
import com.universidadesapi.universidadesapi.service.GCPStorage;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin(origins = "*")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @Autowired
    EstadoRepository estadoRepository;


    @Autowired GCPStorage gcpStorage;

    @GetMapping("/")
    public List<Estado> getAll(){
        return estadoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getOne(@PathVariable Long id){
        return estadoService.getOne(id);
    }


    @PostMapping(value = "/",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Estado> save(@Valid @RequestBody Estado estado){
        return estadoService.save(estado);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@Valid @RequestBody Estado estado, @PathVariable Long id){
        return  estadoService.update(estado,id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> delete(@PathVariable Long id){
        return estadoService.delete(id);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Estado> getBySlug(@PathVariable String slug){
        Optional<Estado> optionalEstado = estadoRepository.findBySlug(slug);
        if(!optionalEstado.isPresent())return ResponseEntity.notFound().build();

        Estado resultado = (Estado) optionalEstado.get();
        return ResponseEntity.ok(resultado);
    }

    
}
