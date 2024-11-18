package com.metasconsultoria.service;

import com.metasconsultoria.entities.Collaborator;
import com.metasconsultoria.repository.ClientRepository;
import com.metasconsultoria.repository.CollaboratorRepository;
import com.metasconsultoria.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class CollaboratorService {

    private CollaboratorService() {}


    public static void insertCollaborator(Collaborator collaborator) throws SQLException {

        if (UserRepository.selectById(collaborator.getIdUser()) == null) {
            return;
        } else if (CollaboratorRepository.selectById(collaborator.getIdUser()) != null || ClientRepository.selectById(collaborator.getIdUser()) != null) {
            return;
        }
        CollaboratorRepository.insertInto(collaborator);
    }


    public static void updateCollaborator(Collaborator collaborator) throws SQLException {
        if (UserRepository.selectById(collaborator.getIdUser()) == null) {
            return; 
        } else if (CollaboratorRepository.selectById(collaborator.getIdUser()) == null) {
            return; 
        }

        CollaboratorRepository.updateData(collaborator);
    }

    public static void deleteCollaboratorById(int idUser) throws SQLException {
        if (CollaboratorRepository.selectById(idUser) == null) {
            return; 
        }

        CollaboratorRepository.deleteById(idUser);
    }

    public static Collaborator getCollaboratorById(int idUser) throws SQLException {
        return CollaboratorRepository.selectById(idUser);
    }

    public static List<Collaborator> getAllCollaborators() throws SQLException {
        return CollaboratorRepository.selectAll();
    }

    public static boolean isCollaborator(int idUser) throws SQLException {
        return CollaboratorRepository.selectById(idUser) != null;
    }
}
