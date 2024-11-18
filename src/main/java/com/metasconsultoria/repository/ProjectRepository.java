package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private ProjectRepository() {}

    public static void insertInto(Connection conn, Project project) throws SQLException {
        String sql = "INSERT INTO Project (name, description, public, date, fk_city) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setBoolean(3, project.isPublicProject());
            ps.setDate(4, (Date) project.getDate());
            ps.setInt(5, project.getIdProject());
            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int codProject) throws SQLException {
        String sql = "DELETE FROM Project WHERE cod_project = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codProject);
            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, Project project) throws SQLException {
        String sql = "UPDATE Project SET name = ?, description = ?, public = ?, date = ?, fk_city = ? WHERE cod_project = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setBoolean(3, project.isPublicProject());
            ps.setDate(4, (Date) project.getDate());
            ps.setInt(5, project.getIdCity());
            ps.setInt(6, project.getIdProject());
            ps.executeUpdate();
        }
    }

    public static List<Project> selectAll(Connection conn) throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Project";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Project project = Project.builder()
                        .idProject(rs.getInt("cod_project"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .publicProject(rs.getBoolean("public"))
                        .date(rs.getDate("date"))
                        .idCity(rs.getInt("fk_city"))
                        .build();
                projects.add(project);
            }
        }

        return projects;
    }

    public static Project selectById(Connection conn, int codProject) throws SQLException {
        Project project = null;
        String sql = "SELECT * FROM Project WHERE cod_project = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codProject);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    project = Project.builder()
                            .idProject(rs.getInt("cod_project"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .publicProject(rs.getBoolean("public"))
                            .date(rs.getDate("date"))
                            .idCity(rs.getInt("fk_city"))
                            .build();
                }
            }
        }

        return project;
    }
}
