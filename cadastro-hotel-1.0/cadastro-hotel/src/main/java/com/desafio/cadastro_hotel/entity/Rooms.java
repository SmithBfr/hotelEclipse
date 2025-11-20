package com.desafio.cadastro_hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_rooms")
public class Rooms implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room_number;

    private String type;

    private Double price_per_night;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(Double price_per_night) {
        this.price_per_night = price_per_night;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rooms rooms = (Rooms) o;
        return Objects.equals(id, rooms.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}