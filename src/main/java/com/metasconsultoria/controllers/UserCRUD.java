package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserCRUD {
    private static final String COD_USER = "cod_user";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private UserCRUD() {}

    public static void createUser(Connection conn, User user) {
        String sql = SQLString.insertInto(User.TABLE,
                                          Arrays.asList(NAME, EMAIL, PASSWORD));

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserById(Connection conn, int id) {
        User user = null;
        String sql = "SELECT cod_user, name, email, password FROM User WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setIdUser(rs.getInt(COD_USER));
                    user.setName(rs.getString(NAME));
                    user.setEmail(rs.getString(EMAIL));
                    user.setPassword(rs.getString(PASSWORD));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> getAllUsers(Connection conn) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT cod_user, name, email, password FROM User";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt(COD_USER));
                user.setName(rs.getString(NAME));
                user.setEmail(rs.getString(EMAIL));
                user.setPassword(rs.getString(PASSWORD));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void updateUser(Connection conn, User user) {
        String sql = "UPDATE User SET name = ?, email = ?, password = ? WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getIdUser());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(Connection conn, int id) {
        String sql = "DELETE FROM User WHERE cod_user = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByLogin(Connection conn, String email, String password) {
        User user = null;
        String sql = "SELECT cod_user, name, email, password FROM User WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setIdUser(rs.getInt(COD_USER));
                    user.setName(rs.getString(NAME));
                    user.setEmail(rs.getString(EMAIL));
                    user.setPassword(rs.getString(PASSWORD));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
