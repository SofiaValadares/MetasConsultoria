package com.metasconsultoria.CRUDs;

import com.metasconsultoria.entities.Collaborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollaboratorCRUD {

    public static void createCollaborator(Connection conn, Collaborator collaborator) {
        String sql = "INSERT INTO Collaborator (name, email, password, city, neighborhood, street, number, complement, phoneNumber1, phoneNumber2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collaborator.getName());
            stmt.setString(2, collaborator.getEmail());
            stmt.setString(3, collaborator.getPassword());
            stmt.setString(4, collaborator.getCity());
            stmt.setString(5, collaborator.getNeighborhood());
            stmt.setString(6, collaborator.getStreet());
            stmt.setInt(7, collaborator.getNumber());
            stmt.setString(8, collaborator.getComplement());
            stmt.setString(9, collaborator.getPhoneNumber1());
            stmt.setString(10, collaborator.getPhoneNumber2());

            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Collaborator getCollaboratorById(Connection conn, int id) {
        Collaborator collaborator = null;
        String sql = "SELECT * FROM Collaborator WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    collaborator = new Collaborator();
                    collaborator.setIdUser(rs.getInt("cod_user"));
                    collaborator.setName(rs.getString("name"));
                    collaborator.setEmail(rs.getString("email"));
                    collaborator.setPassword(rs.getString("password"));
                    collaborator.setCity(rs.getString("city"));
                    collaborator.setNeighborhood(rs.getString("neighborhood"));
                    collaborator.setStreet(rs.getString("street"));
                    collaborator.setNumber(rs.getInt("number"));
                    collaborator.setComplement(rs.getString("complement"));
                    collaborator.setPhoneNumber1(rs.getString("phoneNumber1"));
                    collaborator.setPhoneNumber2(rs.getString("phoneNumber2"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collaborator;
    }

    public static List<Collaborator> getAllCollaborators(Connection conn) {
        List<Collaborator> collaborators = new ArrayList<>();
        String sql = "SELECT * FROM Collaborator";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Collaborator collaborator = new Collaborator();
                collaborator.setIdUser(rs.getInt("cod_user"));
                collaborator.setName(rs.getString("name"));
                collaborator.setEmail(rs.getString("email"));
                collaborator.setPassword(rs.getString("password"));
                collaborator.setCity(rs.getString("city"));
                collaborator.setNeighborhood(rs.getString("neighborhood"));
                collaborator.setStreet(rs.getString("street"));
                collaborator.setNumber(rs.getInt("number"));
                collaborator.setComplement(rs.getString("complement"));
                collaborator.setPhoneNumber1(rs.getString("phoneNumber1"));
                collaborator.setPhoneNumber2(rs.getString("phoneNumber2"));
                collaborators.add(collaborator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collaborators;
    }

    public static void updateCollaborator(Connection conn, Collaborator collaborator) {
        String sql = "UPDATE Collaborator SET name = ?, email = ?, password = ?, city = ?, neighborhood = ?, street = ?, number = ?, complement = ?, phoneNumber1 = ?, phoneNumber2 = ? WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collaborator.getName());
            stmt.setString(2, collaborator.getEmail());
            stmt.setString(3, collaborator.getPassword());
            stmt.setString(4, collaborator.getCity());
            stmt.setString(5, collaborator.getNeighborhood());
            stmt.setString(6, collaborator.getStreet());
            stmt.setInt(7, collaborator.getNumber());
            stmt.setString(8, collaborator.getComplement());
            stmt.setString(9, collaborator.getPhoneNumber1());
            stmt.setString(10, collaborator.getPhoneNumber2());
            stmt.setInt(11, collaborator.getIdUser());

            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCollaborator(Connection conn, int id) {
        String sql = "DELETE FROM Collaborator WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
