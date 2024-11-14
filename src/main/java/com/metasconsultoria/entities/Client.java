package com.metasconsultoria.entities;


public class Client  {
    private int idUser;
    private int idCity;

    public Client() {
    }

    public Client(int idUser, int idCity) {
        this.idUser = idUser;
        this.idCity = idCity;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
}
