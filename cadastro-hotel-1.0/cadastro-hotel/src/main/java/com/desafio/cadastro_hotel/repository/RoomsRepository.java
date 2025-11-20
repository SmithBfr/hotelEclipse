package com.desafio.cadastro_hotel.repository;

import com.desafio.cadastro_hotel.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
}
