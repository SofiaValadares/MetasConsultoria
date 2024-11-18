package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Client;
import com.metasconsultoria.repository.ClientRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private static final Connection conn = ConnData.connection;

    public static void insertClient(int idUser, int idCity) throws SQLException {
        Client client = Client.builder()
                .idUser(idUser)
                .idCity(idCity)
                .build();

        ClientRepository.insetInto(conn, client);
    }

    public static void deleteClient(int idUser) throws SQLException {
        ClientRepository.deletById(conn, idUser);
    }

    public static void updateClient(Client client) throws SQLException {
        ClientRepository.updateData(conn, client);
    }

    public static List<Client> getAllClients() throws SQLException {
        return ClientRepository.selectAll(conn);
    }

    public static Client getClientById(int idUser) throws SQLException {
        return ClientRepository.selectById(conn, idUser);
    }
}
