package com.metasconsultoria.entities;


public class Collaborator extends User {
    public static final String TABLE = "Collaborator";
    public static final String COD_COLLABORATOR = "cod_collaborator";
    public static final String CITY = "city";
    public static final String NEIGHBORHOOD = "neighborhoob";
    public static final String  STREET = "street";
    public static final String NUMBER = "house_number";
    public static final String COMPLEMENT = "complement";
    public static final String PHONE1 = "phone1";
    public static final String PHONE2 = "phone2";

    private String city;
    private String neighborhood;
    private String street;
    private int number;
    private String complement;
    private String phoneNumber1;
    private String phoneNumber2;

    public Collaborator() {
    }

    public Collaborator(String name, String password, String email, String city, String neighborhood, String street, int number, String complement, String phoneNumber1, String phoneNumber2) {
        super(name, password, email);
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
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
