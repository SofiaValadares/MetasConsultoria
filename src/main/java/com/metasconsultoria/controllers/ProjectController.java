package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Project;
import com.metasconsultoria.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() throws SQLException {
        List<Project> projects = ProjectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) throws SQLException {
        Project project = ProjectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createProject(@RequestBody Project project) throws SQLException {
        ProjectService.insertProject(project);
        return ResponseEntity.ok("Projeto criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProject(@RequestBody Project project) throws SQLException {
        ProjectService.updateProject(project);
        return ResponseEntity.ok("Projeto atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable int id) throws SQLException {
        ProjectService.deleteProject(id);
        return ResponseEntity.ok("Projeto deletado com sucesso.");
    }
}
