package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private ClientRepository() {}

    public static void insetInto(Client client) throws SQLException {

        String sql = "INSERT INTO Client (cod_user, fk_city) VALUES (?, ?)";

        String email = client.getUser().getEmail();
        client.getUser().setPassword("Password13");

        if (UserRepository.selectByEmail(email) == null) {
            UserRepository.insertInto(client.getUser());
            client.setUser(UserRepository.selectByEmail(email));
        }

        String cityName = client.getCity().getName();
        String state = client.getCity().getState();

        CityRepository.insetInto(client.getCity());
        client.setCity(CityRepository.selectCityByNameAndState(cityName, state));

        Connection conn = ConnectDatabase.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, client.getUser().getIdUser());
            ps.setInt(2, client.getCity().getIdCity());

            ps.executeUpdate();
        }

        conn.close();
    }

    public static void deletById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM Client WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }

        conn.close();
    }

    public static void updateData(Client client) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "UPDATE Client SET fk_city = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, client.getCity().getIdCity());
            ps.setInt(2, client.getUser().getIdUser());

            ps.executeUpdate();
        }

        conn.close();
    }

    public static List<Client> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = Client.builder()
                        .user(UserRepository.selectById(rs.getInt("cod_user")))
                        .city(CityRepository.selectById(rs.getInt("fk_city")))
                        .build();

                clients.add(client);
            }
        }

        conn.close();
        return clients;
    }

    public static Client selectById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        Client client = null;
        String sql = "SELECT * FROM Client WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    client = Client.builder()
                            .user(UserRepository.selectById(rs.getInt("cod_user")))
                            .city(CityRepository.selectById(rs.getInt("fk_city")))
                            .build();
                }
            }
        }

        conn.close();
        return client;
    }

    public static List<Client> selectByCollaboratorId(int idCollaborator) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT client.cod_user, client.fk_city " +
                "FROM Client client " +
                "JOIN R_Collaborator_Client_Project r_ccp on r_ccp.fk_client = client.cod_user " +
                "WHERE r_ccp.fk_collaborator = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCollaborator);

            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Client client = Client.builder()
                            .user(UserRepository.selectById(rs.getInt("cod_user")))
                            .city(CityRepository.selectById(rs.getInt("fk_city")))
                            .build();

                    clients.add(client);
                }
            }
        }

        conn.close();
        return clients;
    }



    public static int countData() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "SELECT COUNT(DISTINCT cod_user) FROM Client";

        int count = 0;

        assert conn != null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }

        conn.close();
        return count;
    }


}
