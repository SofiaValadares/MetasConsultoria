package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.Collaborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollaboratorRepository {

    private CollaboratorRepository() {}

    public static void insertInto(Collaborator collaborator) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "INSERT INTO Collaborator (city, neighborhood, street, house_number, complement, phone1, cod_user) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collaborator.getCity());
            ps.setString(2, collaborator.getNeighborhood());
            ps.setString(3, collaborator.getStreet());
            ps.setInt(4, collaborator.getNumber());
            ps.setString(5, collaborator.getComplement());
            ps.setString(6, collaborator.getPhoneNumber1());
            ps.setInt(8, collaborator.getIdUser());
            ps.executeUpdate();
            ps.close();
        }

        conn.close();
    }

    public static void deleteById(int idUser) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM Collaborator WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);
            ps.executeUpdate();
        }

        conn.close();
    }

    public static void updateData(Collaborator collaborator) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "UPDATE Collaborator SET city = ?, neighborhood = ?, street = ?, house_number = ?, complement = ?, phone1 = ?, phone2 = ?, supervised_by = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, collaborator.getCity());
            ps.setString(2, collaborator.getNeighborhood());
            ps.setString(3, collaborator.getStreet());
            ps.setInt(4, collaborator.getNumber());
            ps.setString(5, collaborator.getComplement());
            ps.setString(6, collaborator.getPhoneNumber1());
            ps.setString(7, collaborator.getPhoneNumber2());
            ps.setObject(8, collaborator.getSupervisedBy());
            ps.setInt(9, collaborator.getIdUser());
            ps.executeUpdate();
            ps.close();
        }

        conn.close();
    }

    public static List<Collaborator> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<Collaborator> collaborators = new ArrayList<>();
        String sql = "SELECT * FROM Collaborator";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Collaborator collaborator = Collaborator.builder()
                        .idUser(rs.getInt("cod_user"))
                        .city(rs.getString("city"))
                        .neighborhood(rs.getString("neighborhood"))
                        .street(rs.getString("street"))
                        .number(rs.getInt("house_number"))
                        .complement(rs.getString("complement"))
                        .phoneNumber1(rs.getString("phone1"))
                        .phoneNumber2(rs.getString("phone2"))
                        .supervisedBy(rs.getObject("supervised_by") != null ? rs.getInt("supervised_by") : null)
                        .build();
                collaborators.add(collaborator);
            }

            rs.close();
            ps.close();
        }

        conn.close();
        return collaborators;
    }

    public static Collaborator selectById(int idUser) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        Collaborator collaborator = null;
        String sql = "SELECT * FROM Collaborator WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    collaborator = Collaborator.builder()
                            .idUser(rs.getInt("cod_user"))
                            .city(rs.getString("city"))
                            .neighborhood(rs.getString("neighborhood"))
                            .street(rs.getString("street"))
                            .number(rs.getInt("house_number"))
                            .complement(rs.getString("complement"))
                            .phoneNumber1(rs.getString("phone1"))
                            .phoneNumber2(rs.getString("phone2"))
                            .supervisedBy(rs.getObject("supervised_by") != null ? rs.getInt("supervised_by") : null)
                            .build();
                }

                rs.close();
            }

            ps.close();
        }

        conn.close();
        return collaborator;
    }
}
