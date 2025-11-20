package com.desafio.cadastro_hotel.controller;

import com.desafio.cadastro_hotel.entity.Customers;
import com.desafio.cadastro_hotel.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomersController {

    @Autowired
    private CustomersService service;

    @PostMapping
    public ResponseEntity<Customers> create(@RequestBody Customers obj){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customers> getId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customers>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customers> update(@PathVariable Long id, @RequestBody Customers obj){
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }
}
