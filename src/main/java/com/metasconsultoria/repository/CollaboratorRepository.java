package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Collaborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CollaboratorRepository {

    private CollaboratorRepository() {}

    public static void insetInto (Connection conn, Collaborator collaborator) throws SQLException {
        String sql = "INSERT INTO Collaborator(city, neighborhood, street, house_number, complement, phone1, cod_user) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, collaborator.getCity());
            ps.setString(2, collaborator.getNeighborhood());
            ps.setString(3, collaborator.getStreet());
            ps.setInt(4, collaborator.getNumber());
            ps.setString(5, collaborator.getComplement());
            ps.setString(6, collaborator.getPhoneNumber1());
            ps.setInt(7, collaborator.getIdUser());

            ps.executeUpdate();
        }

    }

    public static void setSecondPhoneNumber(Connection conn, int id, String phone) throws SQLException {
        String sql = "UPDATE Collaborator SET phone2 = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }
}
