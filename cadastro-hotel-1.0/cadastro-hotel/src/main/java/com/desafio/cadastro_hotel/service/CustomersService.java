package com.desafio.cadastro_hotel.service;

import com.desafio.cadastro_hotel.entity.Customers;
import com.desafio.cadastro_hotel.exceptions.ResourceNotFoundException;
import com.desafio.cadastro_hotel.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository repository;


    public Customers create(Customers obj){
        return repository.save(obj);

    }


    @Cacheable(value = "customers", key = "#id")
    public Customers findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    @Cacheable(value = "customersAll")
    public List<Customers> getAll(){
        return repository.findAll();

    }

    @CachePut(value = "customers", key = "#id")
    public Customers update(Customers obj){
        Customers newObj = repository.findById(obj.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + obj.getId()));
        updateCustomer(newObj, obj);
        return repository.save(newObj);
    }

    private void updateCustomer(Customers newObj, Customers obj) {
        newObj.setName(obj.getName());
        newObj.setPhone(obj.getPhone());
        newObj.setEmail(obj.getEmail());
        newObj.setCreate_at(obj.getCreate_at());
    }

    @CacheEvict(value = "customers", key = "#id")
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Customer with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}


