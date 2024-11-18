package com.metasconsultoria.service;

import com.metasconsultoria.entities.Client;
import com.metasconsultoria.repository.ClientRepository;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    public static void insertClient(int idUser, int idCity) throws SQLException {
        
        Client client = Client.builder()
                .idUser(idUser)
                .idCity(idCity)
                .build();

        ClientRepository.insetInto(client);
    }

    public static void deleteClient(int idUser) throws SQLException {
        ClientRepository.deletById(idUser);
    }

    public static void updateClient(Client client) throws SQLException {

        ClientRepository.updateData(client);
    }

    public static List<Client> getAllClients() throws SQLException {

        return ClientRepository.selectAll();
    }

    public static Client getClientById(int idUser) throws SQLException {
        return ClientRepository.selectById(idUser);
    }
}
