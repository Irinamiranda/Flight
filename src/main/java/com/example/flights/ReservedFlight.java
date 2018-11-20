package com.example.flights;

import javax.persistence.*;

@Entity
public class ReservedFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Flight flight;

    int passengers;
    String ticketClass;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    Reservation reservation;

    public ReservedFlight() {
    }

    public ReservedFlight(Flight flight, int passengers, String ticketClass, Reservation reservation) {
        this.flight = flight;
        this.passengers = passengers;
        this.ticketClass = ticketClass;
        this.reservation = reservation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
