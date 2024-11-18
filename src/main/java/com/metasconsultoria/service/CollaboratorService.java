package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Collaborator;
import com.metasconsultoria.repository.CollaboratorRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CollaboratorService {
    private static final Connection conn = ConnData.connection;

    public static void insertCollaborator(Collaborator collaborator) throws SQLException {
        CollaboratorRepository.insetInto(conn, collaborator);
    }

    public static void updateCollaborator(Collaborator collaborator) throws SQLException {
        // Implemente o método de atualização no repositório e chame aqui
        CollaboratorRepository.updateData(conn, collaborator);
    }

    public static void deleteCollaborator(int idUser) throws SQLException {
        CollaboratorRepository.deleteById(conn, idUser);
    }

    public static List<Collaborator> getAllCollaborators() throws SQLException {
        return CollaboratorRepository.selectAll(conn);
    }

    public static Collaborator getCollaboratorById(int idUser) throws SQLException {
        return CollaboratorRepository.selectById(conn, idUser);
    }
}
