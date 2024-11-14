package com.metasconsultoria.entities;


public class City {

    private int idCity;
    private String name;
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
