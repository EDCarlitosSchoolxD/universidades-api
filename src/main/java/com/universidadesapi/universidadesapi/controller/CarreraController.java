package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Carrera;
import com.universidadesapi.universidadesapi.service.CarreraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin("*")
public class CarreraController {

    @Autowired
    CarreraService carreraService;


    // @GetMapping("/")
    // public List<Carrera> getAll(){
    //     return carreraService.getAll();
    // }


    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getOne(@PathVariable Long id){
        return carreraService.getOne(id );
    }

    @PostMapping("/")
    public ResponseEntity<Carrera> save(@Valid @RequestBody Carrera carrera){
        return carreraService.save(carrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> update(@Valid @RequestBody Carrera carrera, @PathVariable Long id){
        return carreraService.update(carrera,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carrera> delete(@PathVariable Long id){
        return carreraService.delete(id);
    }

    @GetMapping("/t")
    public Carrera t(){
        return new Carrera();
    }

    @GetMapping("/uni/{id}")
    public List<Carrera> findByUniId(@PathVariable Long id){
        return carreraService.findByUniId(id);
    }
}
