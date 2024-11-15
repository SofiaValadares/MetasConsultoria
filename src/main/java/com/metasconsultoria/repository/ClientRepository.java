package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private ClientRepository() {}

    public static void insetInto(Connection conn, Client client) throws SQLException {
        String sql = "INSERT INTO Client (cod_user, fk_city) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, client.getIdUser());
            ps.setInt(2, client.getIdCity());

            ps.executeUpdate();
        }
    }

    public static void deletById(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Client WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, Client client) throws SQLException {
        String sql = "UPDATE Client SET fk_city = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, client.getIdCity());
            ps.setInt(2, client.getIdUser());

            ps.executeUpdate();
        }
    }

    public static List<Client> selectAll(Connection conn) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = Client.builder()
                        .idUser(rs.getInt("cod_user"))
                        .idCity(rs.getInt("fk_city"))
                        .build();

                clients.add(client);
            }

        }

        return clients;
    }

    public static Client selectById(Connection conn, int id) throws SQLException {
        Client client = null;
        String sql = "SELECT * FROM Client WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    client = Client.builder()
                            .idUser(rs.getInt("cod_user"))
                            .idCity(rs.getInt("fk_city"))
                            .build();
                }
            }
        }

        return client;
    }
}
