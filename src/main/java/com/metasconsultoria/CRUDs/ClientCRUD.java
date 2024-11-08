package com.metasconsultoria.CRUDs;

import com.metasconsultoria.entities.City;
import com.metasconsultoria.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientCRUD {

    public static void createClient(Connection conn, Client client) throws SQLException {
        String sql = "INSERT INTO Client (name, password, email, city_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPassword());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getCity().getIdCity());
            stmt.executeUpdate();
        }
    }

    public static Client getClientById(Connection conn, int id) throws SQLException {
        Client client = null;
        String sql = "SELECT c.cod_user, c.name, c.email, c.password, c.city_id, ci.name AS city_name " +
                "FROM Client c " +
                "JOIN City ci ON c.city_id = ci.cod_city " +
                "WHERE c.cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client();
                    client.setIdUser(rs.getInt("cod_user"));
                    client.setName(rs.getString("name"));
                    client.setEmail(rs.getString("email"));
                    client.setPassword(rs.getString("password"));

                    City city = new City();
                    city.setIdCity(rs.getInt("city_id"));
                    city.setName(rs.getString("city_name"));
                    client.setCity(city);
                }
            }
        }
        return client;
    }

    public static List<Client> getAllClients(Connection conn) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT c.cod_user, c.name, c.email, c.password, c.city_id, ci.name AS city_name " +
                "FROM Client c " +
                "JOIN City ci ON c.city_id = ci.cod_city";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setIdUser(rs.getInt("cod_user"));
                client.setName(rs.getString("name"));
                client.setEmail(rs.getString("email"));
                client.setPassword(rs.getString("password"));

                City city = new City();
                city.setIdCity(rs.getInt("city_id"));
                city.setName(rs.getString("city_name"));
                client.setCity(city);

                clients.add(client);
            }
        }
        return clients;
    }


    public static void updateClient(Connection conn, Client client) throws SQLException {
        String sql = "UPDATE Client SET name = ?, password = ?, email = ?, city_id = ? WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPassword());
            stmt.setString(3, client.getEmail());
            stmt.setInt(4, client.getCity().getIdCity());
            stmt.setInt(5, client.getIdUser());
            stmt.executeUpdate();
        }
    }

    public static void deleteClient(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Client WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
