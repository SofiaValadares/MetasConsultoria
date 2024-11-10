package com.metasconsultoria.entities;

import com.metasconsultoria.annotation.*;

@Table(name = "Collaborator")
public class Collaborator  {
    @PrimaryKey
    @Column(name = "cod_user")
    @ForeignKey(table = "User", column = "cod_user")
    private int idUser;

    @Column(name = "city")
    private String city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private int number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "phone1")
    private String phoneNumber1;

    @Column(name = "phone2")
    private String phoneNumber2;

    @Column(name = "supervised_by")
    private int supervisedBy;

    public Collaborator() {
    }

    public Collaborator(int idUser, String city, String neighborhood, String street, int number, String complement, String phoneNumber1, String phoneNumber2) {
        this.idUser = idUser;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}
