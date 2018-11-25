package com.example.flights;

import javax.validation.constraints.NotNull;

public class FlightSearch {
    @NotNull
    private Airport from;

    @NotNull
    private Airport to;

    private String date;
    private String dateReturn;
    private String direction;

    public FlightSearch() {
    }

    public FlightSearch(Airport from, Airport to, String date, String dateReturn, String direction) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.dateReturn = dateReturn;
        this.direction = direction;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
