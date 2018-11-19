package com.example.flights;

public class FlightSearch {
    private Airport from;
    private Airport to;
    private String date;
    private boolean roundTrip;

    public FlightSearch() {
    }

    public FlightSearch(Airport from, Airport to, String date, boolean roundTrip) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.roundTrip = roundTrip;
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

    public boolean isRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }
}
