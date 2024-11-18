package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Project;
import com.metasconsultoria.repository.ProjectRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private static final Connection conn = ConnData.connection;

    public static void insertProject(Project project) throws SQLException {
        ProjectRepository.insertInto(conn, project);
    }

    public static void updateProject(Project project) throws SQLException {
        ProjectRepository.updateData(conn, project);
    }

    public static void deleteProject(int idProject) throws SQLException {
        ProjectRepository.deleteById(conn, idProject);
    }

    public static List<Project> getAllProjects() throws SQLException {
        return ProjectRepository.selectAll(conn);
    }

    public static Project getProjectById(int idProject) throws SQLException {
        return ProjectRepository.selectById(conn, idProject);
    }
}
