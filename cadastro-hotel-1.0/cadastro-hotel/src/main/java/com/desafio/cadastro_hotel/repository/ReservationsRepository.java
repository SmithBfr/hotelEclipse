package com.desafio.cadastro_hotel.repository;

import com.desafio.cadastro_hotel.entity.Reservations;
import com.desafio.cadastro_hotel.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {


    @Query("SELECT r FROM Reservations r WHERE r.checkin <= :end AND r.checkout >= :start")
    List<Reservations> findByInterval(LocalDate start, LocalDate end);

    @Query("SELECT DISTINCT r.room FROM Reservations r WHERE r.status = 'OPEN' AND :now BETWEEN r.checkin AND r.checkout")
    List<Rooms> findOccupiedRooms(LocalDate now);
}
