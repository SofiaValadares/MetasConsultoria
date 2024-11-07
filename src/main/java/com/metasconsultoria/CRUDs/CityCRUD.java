package com.metasconsultoria.CRUDs;

import com.metasconsultoria.entities.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityCRUD {

    public static void createCity(Connection conn, City city) {
        String sql = "INSERT INTO City (name, state) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getState());
            stmt.executeUpdate();
            System.out.println("Cidade criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static City getCityById(Connection conn, int id) {
        City city = null;
        String sql = "SELECT * FROM City WHERE cod_city = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    city = new City();
                    city.setIdCity(rs.getInt("cod_city"));
                    city.setName(rs.getString("name"));
                    city.setState(rs.getString("state"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public static List<City> getCitiesByState(Connection conn, String state) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City WHERE state = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, state);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setIdCity(rs.getInt("cod_city"));
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

    public static List<City> getAllCities(Connection conn) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM City";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt("cod_city"));
                city.setName(rs.getString("name"));
                city.setState(rs.getString("state"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static List<City> getCitiesWithPublicProjects(Connection conn) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT DISTINCT c.cod_city, c.name, c.state " +
                "FROM City c " +
                "JOIN Project p ON c.cod_city = p.fk_city " +
                "WHERE p.public = 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt("cod_city"));
                city.setName(rs.getString("name"));
                city.setState(rs.getString("state"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void updateCity(Connection conn, City city) {
        String sql = "UPDATE City SET name = ?, state = ? WHERE cod_city = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getState());
            stmt.setInt(3, city.getIdCity());
            stmt.executeUpdate();
            System.out.println("Cidade atualizada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCity(Connection conn, int id) {
        String sql = "DELETE FROM City WHERE cod_city = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cidade deletada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
