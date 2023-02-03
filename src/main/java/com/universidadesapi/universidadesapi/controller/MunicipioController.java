package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Municipio;
import com.universidadesapi.universidadesapi.repository.MunicipioRepository;
import com.universidadesapi.universidadesapi.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    public MunicipioService municipioService;


    @GetMapping("/")
    public List<Municipio> getAll(){
        return municipioService.getAll();
    }


    @PostMapping("/")
    public ResponseEntity<Municipio> save(@RequestBody Municipio municipio){
        return municipioService.save(municipio);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Municipio> getOne(@PathVariable Long id){
        return municipioService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> update(@RequestBody Municipio municipio, @PathVariable Long id){
        return municipioService.update(municipio,id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Municipio> deletE(@PathVariable Long id){
        return municipioService.delete((id));
    }
}
