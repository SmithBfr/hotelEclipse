package com.desafio.cadastro_hotel.controller;

import com.desafio.cadastro_hotel.entity.Customers;
import com.desafio.cadastro_hotel.entity.Rooms;
import com.desafio.cadastro_hotel.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
public class RoomsController {

    @Autowired
    private RoomsService service;

    @PostMapping
    public ResponseEntity<Rooms> create(@RequestBody Rooms obj){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rooms> getId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getId(id));
    }

    @GetMapping
    public ResponseEntity<List<Rooms>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Rooms> update(@PathVariable Long id, @RequestBody Rooms obj){
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }

}
