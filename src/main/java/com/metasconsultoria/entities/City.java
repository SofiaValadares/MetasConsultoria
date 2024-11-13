package com.metasconsultoria.entities;

import com.metasconsultoria.annotation.*;

@Table(name = "City")
public class City {

    @PrimaryKey
    @Column(name = "cod_city")
    private int idCity;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    public City() {

    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
