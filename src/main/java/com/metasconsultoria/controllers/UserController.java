package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserController {

    private UserController() {}

    public static void insertInto(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO User (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        }
    }

    public static void deleteUserById(Connection conn, int userId) throws SQLException {
        String sql = "DELETE FROM User WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ps.executeUpdate();
        }
    }

    public static void updateUser(Connection conn, User user) throws SQLException {
        String sql = "UPDATE User SET name = ?, email = ?, password = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUser());

            ps.executeUpdate();
        }
    }


    public static List<User> getAllUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("cod_user"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        }
        return users;
    }

    public static User selectUserById(Connection conn, int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM User WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setIdUser(rs.getInt("cod_user"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
            }
        }

        return user;
    }

    public static User selectUserByLogin(Connection conn, String email, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setIdUser(rs.getInt("cod_user"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                }
            }
        }

        return user;
    }
}
