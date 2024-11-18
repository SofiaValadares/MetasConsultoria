package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserRepository() {}

    public static void insertInto(User user) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "INSERT INTO User (name, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }


    public static void deleteById(int userId) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM User WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void updateData(User user) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "UPDATE User SET name = ?, email = ?, password = ? WHERE cod_user = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getIdUser());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static List<User> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

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

            rs.close();
            ps.close();
        }

        conn.close();
        return users;
    }

    public static User selectById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

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

                rs.close();
            }
            
            ps.close();
        }

        conn.close();
        return user;
    }

    public static User selectByEmail(String email) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();     

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

                rs.close();
            }

            ps.close();
        }

        conn.close();
        return user;
    }
}
