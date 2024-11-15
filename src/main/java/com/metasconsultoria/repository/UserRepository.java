package com.metasconsultoria.repository;

import com.metasconsultoria.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserRepository() {}

    public static void insertInto(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO User (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int userId) throws SQLException {
        String sql = "DELETE FROM User WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, User user) throws SQLException {
        String sql = "UPDATE User SET name = ?, email = ?, password = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUser());

            ps.executeUpdate();
        }
    }

    public static List<User> selectAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = User.builder()
                        .idUser(rs.getInt("cod_user"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .build();


                users.add(user);
            }
        }
        return users;
    }

    public static User selectById(Connection conn, int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM User WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = User.builder()
                            .idUser(rs.getInt("cod_user"))
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .build();
                }
            }
        }
        return user;
    }

    public static User selectByEmail(Connection conn, String email) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM User WHERE email = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = User.builder()
                            .idUser(rs.getInt("cod_user"))
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .build();
                }
            }
        }
        return user;
    }
}
