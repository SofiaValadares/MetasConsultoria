package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.entities.Client;
import com.metasconsultoria.entities.User;
import com.metasconsultoria.repository.CityRepository;
import com.metasconsultoria.repository.ClientRepository;
import com.metasconsultoria.repository.CollaboratorRepository;
import com.metasconsultoria.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientService {
    private static final Connection conn = ConnData.connection;

    public static void insertClient (User user, City city) throws SQLException {
        if (UserRepository.selectById(conn, user.getIdUser()) == null) {
            return;
        } else if (CollaboratorRepository.selectById(conn, user.getIdUser()) != null || ClientRepository.selectById(conn, user.getIdUser()) != null) {
            return;
        }

        if (CityRepository.selectById(conn, city.getIdCity()) == null) {
            return;
        }

        Client client = Client.builder()
                .idUser(user.getIdUser())
                .idCity(city.getIdCity())
                .build();

        ClientRepository.insetInto(conn, client);
    }

    public static void deletClient (Client client) throws SQLException {
        ClientRepository.deletById(conn, client.getIdUser());
    }

}
