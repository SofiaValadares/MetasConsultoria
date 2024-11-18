package com.metasconsultoria.service;

import com.metasconsultoria.entities.User;
import com.metasconsultoria.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserService {

    public static void insertUser(String name, String email, String password) throws SQLException {
        
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        if (UserRepository.selectByEmail(email) == null) {
            return;
        }

        UserRepository.insertInto(user);

    }

    public static void deleteUser(User user) throws SQLException {
        UserRepository.deleteById(user.getIdUser());
    }

    public static void updateUser(User user) throws SQLException {
        if (user == null) {
            return;
        }

        User oldUser = UserRepository.selectById(user.getIdUser());

        if (!Objects.equals(oldUser.getEmail(), user.getEmail())) {
            User userSafeTest = UserRepository.selectByEmail(user.getEmail());

            if (userSafeTest != null) {
                return;
            }
        }

        UserRepository.updateData(user);
    }



    public static User login(String email, String password) throws SQLException {

        User user = UserRepository.selectByEmail(email);

        if (Objects.equals(user.getPassword(), password)) {
            return user;
        }

        return null;
    }

    public static User getUser(int idUser) throws SQLException {
        return UserRepository.selectById(idUser);
    }

    public static List<User> getAllUsers() throws SQLException {
        return UserRepository.selectAll();
    }
}
