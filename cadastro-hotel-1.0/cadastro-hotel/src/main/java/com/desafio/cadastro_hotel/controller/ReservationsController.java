package com.desafio.cadastro_hotel.controller;

import com.desafio.cadastro_hotel.entity.Customers;
import com.desafio.cadastro_hotel.entity.Reservations;
import com.desafio.cadastro_hotel.entity.Rooms;
import com.desafio.cadastro_hotel.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsService service;

    @PostMapping
    public ResponseEntity<Reservations> create(@RequestBody Reservations obj) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reservations> getId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    @GetMapping
    public ResponseEntity<List<Reservations>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservations> update(@PathVariable Long id, @RequestBody Reservations obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(service.update(obj));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Reservations>> findByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(service.findByInterval(start, end));
    }


    @GetMapping("/occupied-rooms")
    public ResponseEntity<List<Rooms>> occupiedRooms() {
        return ResponseEntity.ok(service.findOccupiedRooms(LocalDate.now()));
    }



}

