package com.metasconsultoria.entities;

import java.sql.Connection;

public class Client extends User {
    private int idCity;

    public Client() {
    }

    public Client(String name, String password, String email, int idCity) {
        super(name, password, email);
        this.idCity = idCity;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
}
