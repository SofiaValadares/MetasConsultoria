package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.User;
import com.metasconsultoria.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static final Connection conn = ConnData.connection;
    public static void insertUser(String name, String email, String password) throws SQLException {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        if (UserRepository.selectByEmail(conn, email) == null) {
            return;
        }

        UserRepository.insertInto(conn, user);
    }

    public static void deleteUser(User user) throws SQLException {
        UserRepository.deleteById(conn, user.getIdUser());
    }

    public static void updateUser(User user) throws SQLException {
        if (user == null) {
            return;
        }

        User userTest = UserRepository.selectByEmail(conn, user.getEmail());

        if (userTest != null && userTest.getIdUser() != user.getIdUser()) {
            return;
        }

        UserRepository.updateData(conn, user);
    }

    public static User login(String email, String password) throws SQLException {
        User user = UserRepository.selectByEmail(conn, email);

        if (Objects.equals(user.getPassword(), password)) {
            return user;
        }

        return null;
    }

    public static User getUser(int idUser) throws SQLException {
        return UserRepository.selectById(conn, idUser);
    }

    public static List<User> getAllUsers() throws SQLException {
        return UserRepository.selectAll(conn);
    }
}
