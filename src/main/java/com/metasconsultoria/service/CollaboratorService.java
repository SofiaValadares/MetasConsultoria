package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Collaborator;

import com.metasconsultoria.entities.User;
import com.metasconsultoria.repository.ClientRepository;
import com.metasconsultoria.repository.CollaboratorRepository;
import com.metasconsultoria.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class CollaboratorService {
    private static final Connection conn = ConnData.connection;

    private CollaboratorService() {}

    public static void insertCollaborator(User user, String city, String neighborhood, String street, int number, String complement, String phoneNumber1) throws SQLException {
        if (UserRepository.selectById(conn, user.getIdUser()) == null) {
            return;
        } else if (CollaboratorRepository.selectById(conn, user.getIdUser()) != null || ClientRepository.selectById(conn, user.getIdUser()) != null) {
            return;
        }

        Collaborator collaborator = Collaborator.builder()
                .idUser(user.getIdUser())
                .city(city)
                .neighborhood(neighborhood)
                .street(street)
                .number(number)
                .complement(complement)
                .phoneNumber1(phoneNumber1)
                .build();

        CollaboratorRepository.insertInto(conn, collaborator);
    }

}
