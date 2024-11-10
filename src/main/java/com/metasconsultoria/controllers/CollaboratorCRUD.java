package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Collaborator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollaboratorCRUD {
    private static final String COD_USER = "c.cod_user";
    private static final String NAME = "u.name";
    private static final String EMAIL = "u.email";
    private static final String PASSWORD = "u.password";
    private static final String CITY = "c.city";
    private static final String NEIGHBORHOOD = "c.neighborhood";
    private static final String STREET = "c.street";
    private static final String HOUSE_NUMBER = "c.house_number";
    private static final String COMPLEMENT = "c.complement";
    private static final String PHONE1 = "c.phone1";
    private static final String PHONE2 = "c.phone2";

    private CollaboratorCRUD () {}

    public static void createCollaborator(Connection conn, Collaborator collaborator) {
        UserCRUD.createUser(conn, collaborator);
        String sql = SQLString.insertInto(Collaborator.TABLE,
                                          Arrays.asList(Collaborator.COD_COLLABORATOR, Collaborator.CITY, Collaborator.NEIGHBORHOOD, Collaborator.STREET, Collaborator.NUMBER,Collaborator.COMPLEMENT, Collaborator.PHONE1, Collaborator.PHONE2));
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, collaborator.getIdUser());
            stmt.setString(2, collaborator.getCity());
            stmt.setString(3, collaborator.getNeighborhood());
            stmt.setString(4, collaborator.getStreet());
            stmt.setInt(5, collaborator.getNumber());
            stmt.setString(6, collaborator.getComplement());
            stmt.setString(7, collaborator.getPhoneNumber1());
            stmt.setString(8, collaborator.getPhoneNumber2());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Collaborator getCollaboratorById(Connection conn, int id) {
        Collaborator collaborator = null;
        String sql = "SELECT c.cod_user, u.name, u.email, u.password, c.city, c.neighborhood, c.street, c.house_number, c.complement, c.phone1, c.phone2, c.supervised_by " +
                "FROM Collaborator c " +
                "JOIN User u on c.cod_user = u.cod_user " +
                "WHERE c.cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    collaborator = new Collaborator();
                    collaborator.setIdUser(rs.getInt(COD_USER));
                    collaborator.setName(rs.getString(NAME));
                    collaborator.setEmail(rs.getString(EMAIL));
                    collaborator.setPassword(rs.getString(PASSWORD));
                    collaborator.setCity(rs.getString(CITY));
                    collaborator.setNeighborhood(rs.getString(NEIGHBORHOOD));
                    collaborator.setStreet(rs.getString(STREET));
                    collaborator.setNumber(rs.getInt(HOUSE_NUMBER));
                    collaborator.setComplement(rs.getString(COMPLEMENT));
                    collaborator.setPhoneNumber1(rs.getString(PHONE1));
                    collaborator.setPhoneNumber2(rs.getString(PHONE2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collaborator;
    }

    public static List<Collaborator> getAllCollaborators(Connection conn) {
        List<Collaborator> collaborators = new ArrayList<>();
        String sql = "SELECT c.cod_user, u.name, u.email, u.password, c.city, c.neighborhood, c.street, c.house_number, c.complement, c.phone1, c.phone2, c.supervised_by " +
                "FROM Collaborator c " +
                "JOIN User u on c.cod_user = u.cod_user";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Collaborator collaborator = new Collaborator();
                collaborator.setIdUser(rs.getInt(COD_USER));
                collaborator.setName(rs.getString(NAME));
                collaborator.setEmail(rs.getString(EMAIL));
                collaborator.setPassword(rs.getString(PASSWORD));
                collaborator.setCity(rs.getString(CITY));
                collaborator.setNeighborhood(rs.getString(NEIGHBORHOOD));
                collaborator.setStreet(rs.getString(STREET));
                collaborator.setNumber(rs.getInt(HOUSE_NUMBER));
                collaborator.setComplement(rs.getString(COMPLEMENT));
                collaborator.setPhoneNumber1(rs.getString(PHONE1));
                collaborator.setPhoneNumber2(rs.getString(PHONE2));
                collaborators.add(collaborator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collaborators;
    }

    public static void updateCollaborator(Connection conn, Collaborator collaborator) {
        String sql = "UPDATE Collaborator SET city = ?, neighborhood = ?, street = ?, house_number = ?, complement = ?, phone1 = ?, phone2 = ? WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collaborator.getCity());
            stmt.setString(2, collaborator.getNeighborhood());
            stmt.setString(3, collaborator.getStreet());
            stmt.setInt(4, collaborator.getNumber());
            stmt.setString(5, collaborator.getComplement());
            stmt.setString(6, collaborator.getPhoneNumber1());
            stmt.setString(7, collaborator.getPhoneNumber2());
            stmt.setInt(8, collaborator.getIdUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCollaborator(Connection conn, int id) {
        String sql = SQLString.deleteFrom(Collaborator.TABLE,
                                          Arrays.asList(Collaborator.COD_COLLABORATOR + " = ?"));

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserCRUD.deleteUser(conn, id);
    }
}
