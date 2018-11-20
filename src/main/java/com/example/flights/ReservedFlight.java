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

    public ReservedFlight() {
    }

    public ReservedFlight(Flight flight, int passengers, String ticketClass) {
        this.flight = flight;
        this.passengers = passengers;
        this.ticketClass = ticketClass;
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
}
