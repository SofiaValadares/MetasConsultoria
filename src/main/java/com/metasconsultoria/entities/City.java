package com.metasconsultoria.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City {
    public static final String TABLE = "City";
    public static final String COD_CITY = "cod_city";
    public static final String NAME = "name";
    public static final String STATE = "state";

    private int idCity;
    private String name;
    private String state;

    public City() {

    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

}
