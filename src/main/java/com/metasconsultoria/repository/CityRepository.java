package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    private CityRepository() {}

    public static void insetInto(City city) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        
        String sql = "INSERT INTO User (name, state) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, city.getName());
            ps.setString(2, city.getState());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void deleteById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM City WHERE cod_city = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void updateData(City city) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "UPDATE City SET name = ?, state = ? WHERE cod_city = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, city.getName());
            ps.setString(2, city.getState());
            ps.setInt(3, city.getIdCity());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static List<City> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                City city = City.builder()
                        .idCity(rs.getInt("cod_city"))
                        .name(rs.getString("name"))
                        .state(rs.getString("state"))
                        .build();

                cities.add(city);
            }

            rs.close();
            ps.close();
        }

        conn.close();
        return cities;
    }

    public static City selectById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        City city = null;
        String sql = "SELECT * FROM City WHERE cod_city = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    city = City.builder()
                            .idCity(rs.getInt("cod_city"))
                            .name(rs.getString("name"))
                            .state(rs.getString("state"))
                            .build();
                }

                rs.close();
            }

            ps.close();
        }

        conn.close();
        return city;
    }

    public static City selectCityByNameAndState(String name, String state) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        City city = null;
        String sql = "SELECT * FROM City WHERE name = ? AND state = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, state);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    city = City.builder()
                            .idCity(rs.getInt("cod_city"))
                            .name(rs.getString("name"))
                            .state(rs.getString("state"))
                            .build();
                }

                rs.close();
            }

            ps.close();
        }

        conn.close();
        return city;
    }
}
