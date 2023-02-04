package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Universidad;
import com.universidadesapi.universidadesapi.service.UniversidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universidades")
public class UniversidadController {

    @Autowired
    public UniversidadService universidadService;


    @GetMapping("/")
    public List<Universidad> getAll(){
        return universidadService.getAll();
    }

    @PostMapping("/")
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
}
