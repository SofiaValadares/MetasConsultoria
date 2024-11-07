package com.metasconsultoria.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class City {
    private int idCity;
    private String name;
    private String state;

    public City() {

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


    public static List<City> getCities(Connection conn) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setIdCity(rs.getInt("cod_city")); // Corrigido para a coluna correta
                    city.setName(rs.getString("name"));
                    city.setState(rs.getString("state"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

}
