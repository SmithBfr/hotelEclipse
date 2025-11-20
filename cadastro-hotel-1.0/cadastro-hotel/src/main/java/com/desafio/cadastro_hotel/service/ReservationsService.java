package com.desafio.cadastro_hotel.service;

import com.desafio.cadastro_hotel.entity.Customers;
import com.desafio.cadastro_hotel.entity.Reservations;
import com.desafio.cadastro_hotel.entity.Rooms;
import com.desafio.cadastro_hotel.exceptions.ResourceNotFoundException;
import com.desafio.cadastro_hotel.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository repository;

    @CacheEvict(value = {"reservationsAll", "reservationsById"}, allEntries = true)
    public Reservations create(Reservations obj){
        return repository.save(obj);

    }

    @CacheEvict(value = {"reservationsAll", "reservationsById"}, allEntries = true)
    public void delete(Long id){
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Reservation with id " + id + " not found");
        repository.deleteById(id);
    }

    @Cacheable(value = "reservationsById", key = "#id")
    public Reservations getId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with id " + id + " not found"));
    }

    @Cacheable(value = "reservationsAll")
    public List<Reservations> getAll(){
        return repository.findAll();

    }

    @CacheEvict(value = {"reservationsAll", "reservationsById"}, allEntries = true)
    public Reservations update(Reservations obj){
        Reservations newObj = repository.findById(obj.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        updateReservations(newObj, obj);
        return repository.save(newObj);
    }

    private void updateReservations(Reservations newObj, Reservations obj) {
        newObj.setCustomer(obj.getCustomer());
        newObj.setRoom(obj.getRoom());
        newObj.setCheckin(obj.getCheckin());
        newObj.setCheckout(obj.getCheckout());
        newObj.setStatus(obj.getStatus());
    }


    public List<Reservations> findByInterval(LocalDate start, LocalDate end) {
        return repository.findByInterval(start, end);
    }

    public List<Rooms> findOccupiedRooms(LocalDate now) {
        return repository.findOccupiedRooms(now);
    }


}
