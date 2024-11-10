package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProjectCRUD {

    private ProjectCRUD() {}

    public static void createProject(Connection conn, Project project) {
        String sql = SQLString.insertInto(Project.TABLE,
                                          Arrays.asList(Project.NAME, Project.DESCRIPTION, Project.FK_CITY));

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setInt(3, project.getIdCity());

            stmt.executeUpdate();
            System.out.println("Projeto criado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static Project getProjectById(Connection conn, int id) {
        Project project = null;
        String sql = "SELECT p.cod_project, p.name, p.description, p.public, p.date, p.fk_city " +
                "FROM Project p " +
                "WHERE cod_project = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    project = new Project();
                    project.setIdProject(rs.getInt("cod_project"));
                    project.setName(rs.getString("name"));
                    project.setDescription(rs.getString("description"));
                    project.setPublicProject(rs.getBoolean("public"));
                    project.setIdCity(rs.getInt("fk_city"));

                    java.sql.Date sqlDate = rs.getDate("date");
                    if (sqlDate != null) {
                        project.setDate(new Date(sqlDate.getTime()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }


    public static List<Project> getAllProjects(Connection conn) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.cod_project, p.name, p.description, p.public, p.date, p.fk_city " +
                     "FROM Project p";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setIdProject(rs.getInt("cod_project"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setPublicProject(rs.getBoolean("public"));
                project.setIdCity(rs.getInt("fk_city"));

                java.sql.Date sqlDate = rs.getDate("date");
                if (sqlDate != null) {
                    project.setDate(new Date(sqlDate.getTime()));
                }

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }


    public static List<Project> getPublicProjects(Connection conn) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Project WHERE public = 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setIdProject(rs.getInt("cod_project"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setPublicProject(rs.getBoolean("public"));
                project.setIdCity(rs.getInt("fk_city"));

                java.sql.Date sqlDate = rs.getDate("date");
                if (sqlDate != null) {
                    project.setDate(new Date(sqlDate.getTime()));
                }

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public static List<Project> getProjectsByClient(Connection conn, int idUser) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.cod_project, p.name, p.description, p.public, p.date, p.fk_city " +
                "FROM Project p " +
                "JOIN R_Collaborator_Client_Project r on r.fk_project = p.cod_project " +
                "WHERE r.fk_client = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setIdProject(rs.getInt("cod_project"));
                    project.setName(rs.getString("name"));
                    project.setDescription(rs.getString("description"));
                    project.setPublicProject(rs.getBoolean("public"));
                    project.setIdCity(rs.getInt("fk_city"));

                    java.sql.Date sqlDate = rs.getDate("date");
                    if (sqlDate != null) {
                        project.setDate(new Date(sqlDate.getTime()));
                    }

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }



    public static void updateProject(Connection conn, Project project) {
        String sql = "UPDATE Project SET name = ?, description = ?, public = ?, fk_city = ?, date = ? WHERE cod_project = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setBoolean(3, project.isPublicProject());
            stmt.setInt(4, project.getIdCity());
            if (project.getDate() != null) {
                stmt.setDate(5, new java.sql.Date(project.getDate().getTime()));
            } else {
                stmt.setDate(5, null);
            }
            stmt.setInt(6, project.getIdProject());
            stmt.executeUpdate();
            System.out.println("Projeto atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteProject(Connection conn, int id) {
        String sql = SQLString.deleteFrom(Project.TABLE,
                                          Arrays.asList(Project.COD_PROJECT + " = ?"));
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Projeto deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
