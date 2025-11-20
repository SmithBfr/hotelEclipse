package com.desafio.cadastro_hotel.entity;

import com.desafio.cadastro_hotel.enums.StatusReservations;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "tb_reservations")
public class Reservations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Rooms room;

    private String checkin;

    private String checkout;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReservations status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public StatusReservations getStatus() {
        return status;
    }

    public void setStatus(StatusReservations status) {
        this.status = status;
    }


    public Reservations(){

    }

    public Reservations(Long id, Customers customer, Rooms room, String checkin, String checkout, StatusReservations status) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations that = (Reservations) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


