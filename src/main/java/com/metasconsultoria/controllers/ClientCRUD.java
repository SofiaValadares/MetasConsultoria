package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Client;
import com.metasconsultoria.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientCRUD {
    public static final String COD_USER = "c.cod_user";
    private static final String NAME = "name";
    private static final String EMAIL = "u.email";
    private static final String PASSWORD = "u.password";
    private static final String FK_CITY = "c.fk_city";

    private ClientCRUD() {}

    public static void createClient(Connection conn, Client client) throws SQLException {
        UserCRUD.createUser(conn, client);
        String sql = SQLString.insertInto(Client.TABLE,
                                          Arrays.asList(Client.COD_CLIENTE, Client.FK_CITY));

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, client.getIdUser());
            stmt.setInt(2, client.getIdCity());
            stmt.executeUpdate();
        }
    }

    public static Client getClientById(Connection conn, int id) throws SQLException {
        Client client = null;
        String sql = "SELECT c.cod_user, u.name, u.email, u.password, c.fk_city " +
                "FROM Client c " +
                "JOIN User u on c.cod_user = u.cod_user " +
                "WHERE c.cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client();
                    client.setIdUser(rs.getInt(COD_USER));
                    client.setName(rs.getString(NAME));
                    client.setEmail(rs.getString(EMAIL));
                    client.setPassword(rs.getString(PASSWORD));
                    client.setIdCity(rs.getInt(FK_CITY));
                }
            }
        }
        return client;
    }

    public static List<Client> getAllClients(Connection conn) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT c.cod_user, u.name, u.email, u.password, c.fk_city " +
                "FROM Client c " +
                "JOIN User u on c.cod_user = u.cod_user ";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setIdUser(rs.getInt(COD_USER));
                client.setName(rs.getString(NAME));
                client.setEmail(rs.getString(EMAIL));
                client.setPassword(rs.getString(PASSWORD));
                client.setIdCity(rs.getInt(FK_CITY));

                clients.add(client);
            }
        }
        return clients;
    }


    public static void updateClient(Connection conn, Client client) throws SQLException {
        String sql = "UPDATE Client SET fk_city = ? WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, client.getIdCity());
            stmt.setInt(2, client.getIdUser());
            stmt.executeUpdate();
        }
    }

    public static void deleteClient(Connection conn, int id) throws SQLException {
        String sql = SQLString.deleteFrom(Client.TABLE,
                                          Arrays.asList(Client.COD_CLIENTE + " = ?"));

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        UserCRUD.deleteUser(conn, id);
    }
}
