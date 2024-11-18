package com.metasconsultoria.service;

import com.metasconsultoria.entities.Project;
import com.metasconsultoria.repository.ProjectRepository;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    public static void insertProject(Project project) throws SQLException {
        ProjectRepository.insertInto(project);
    }

    public static void updateProject(Project project) throws SQLException {
        ProjectRepository.updateData(project);
    }

    public static void deleteProject(int idProject) throws SQLException {
        ProjectRepository.deleteById(idProject);
    }

    public static List<Project> getAllProjects() throws SQLException {
        return ProjectRepository.selectAll();
    }

    public static Project getProjectById(int idProject) throws SQLException {
        return ProjectRepository.selectById(idProject);
    }
}
