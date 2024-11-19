package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Collaborator;
import com.metasconsultoria.service.CollaboratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/collaborators")
public class CollaboratorController {

    @GetMapping("/")
    public ResponseEntity<List<Collaborator>> getAllCollaborators() throws SQLException {
        List<Collaborator> collaborators = CollaboratorService.getAllCollaborators();
        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable int id) throws SQLException {
        Collaborator collaborator = CollaboratorService.getCollaboratorById(id);
        if (collaborator != null) {
            return ResponseEntity.ok(collaborator);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createCollaborator(@RequestBody Collaborator collaborator) throws SQLException {
        CollaboratorService.insertCollaborator(collaborator);
        return ResponseEntity.ok("Colaborador criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCollaborator(@PathVariable int id, @RequestBody Collaborator collaborator) throws SQLException {
        collaborator.setIdUser(id);
        CollaboratorService.updateCollaborator(collaborator);
        return ResponseEntity.ok("Colaborador atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollaborator(@PathVariable int id) throws SQLException {
        CollaboratorService.deleteCollaboratorById(id);
        return ResponseEntity.ok("Colaborador deletado com sucesso.");
    }
}
