package com.metasconsultoria.repository;

import com.metasconsultoria.entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityRepository {

    private CityRepository() {}

    public static void insetInto(Connection conn, City city) throws SQLException {
        String sql = "INSERT INTO User (name, state) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, city.getName());
            ps.setString(2, city.getState());

            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM City WHERE cod_city = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, City city) throws SQLException {
        String sql = "UPDATE City SET name = ?, state = ? WHERE cod_city = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, city.getName());
            ps.setString(2, city.getState());
            ps.setInt(3, city.getIdCity());

            ps.executeUpdate();
        }
    }
}
