package com.universidadesapi.universidadesapi.controller;

import com.universidadesapi.universidadesapi.entity.Estado;
import com.universidadesapi.universidadesapi.repository.EstadoRepository;
import com.universidadesapi.universidadesapi.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @GetMapping("/")
    public List<Estado> getAll(){
        return estadoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getOne(@PathVariable Long id){
        return estadoService.getOne(id);
    }


    @PostMapping("/")
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

}
