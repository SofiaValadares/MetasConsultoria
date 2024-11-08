package com.metasconsultoria.entities;

import java.sql.Connection;

public class Client extends User {
    private City city;

    public Client() {
    }

    public Client(String name, String password, String email, City city) {
        super(name, password, email);
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
