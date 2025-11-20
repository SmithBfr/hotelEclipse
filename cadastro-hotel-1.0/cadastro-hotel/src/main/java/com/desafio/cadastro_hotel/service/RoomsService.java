package com.desafio.cadastro_hotel.service;

import com.desafio.cadastro_hotel.entity.Rooms;
import com.desafio.cadastro_hotel.exceptions.ResourceNotFoundException;
import com.desafio.cadastro_hotel.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository repository;

    @CacheEvict(value = {"roomsAll", "rooms"}, allEntries = true)
    public Rooms create(Rooms obj) {
        return repository.save(obj);

    }

    @CacheEvict(value = {"roomsAll", "rooms"}, allEntries = true)
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Room with id " + id + " not found");
        repository.deleteById(id);
    }

    @Cacheable(value = "rooms", key = "#id")
    public Rooms getId(Long id) {
        return repository.findById(id).get();
    }

    @Cacheable(value = "roomsAll")
    public List<Rooms> getAll(){
        return repository.findAll();
    }

    @CachePut(value = "rooms", key = "#id")
    @CacheEvict(value = "roomsAll", allEntries = true)
    public Rooms update(Rooms obj) {
        Rooms newObj = repository.findById(obj.getId()).get();
        updateRoom(newObj, obj);
        return repository.save(newObj);
    }

    private void updateRoom(Rooms newObj, Rooms obj) {
        newObj.setRoom_number(obj.getRoom_number());
        newObj.setType(obj.getType());
        newObj.setPrice_per_night(obj.getPrice_per_night());
    }
}
