package com.metasconsultoria.entities;

import com.metasconsultoria.annotation.*;

@Table(name = "Client")
public class Client  {
    @PrimaryKey
    @Column(name = "cod_user")
    @ForeignKey(table = "User", column = "cod_user")
    private int idUser;

    @Column(name = "fk_city")
    @ForeignKey(table = "City", column = "cod_city")
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
