package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.User;
import com.metasconsultoria.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private static final Connection conn = ConnData.connection;
    public static User login(String email, String password) throws SQLException {
        return UserRepository.selectByLogin(conn, email, password);
    }
}
