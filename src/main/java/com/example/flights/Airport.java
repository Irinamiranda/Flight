package com.example.flights;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airport {
    @Id
    private String code;

    private String name;
    private String location;
    private Integer capacity;

    public Airport() {
    }

    public Airport(String code, String name, String location, Integer capacity) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
