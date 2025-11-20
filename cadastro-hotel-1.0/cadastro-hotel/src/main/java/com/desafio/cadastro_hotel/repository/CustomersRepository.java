package com.desafio.cadastro_hotel.repository;

import com.desafio.cadastro_hotel.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
}
