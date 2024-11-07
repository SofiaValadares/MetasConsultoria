package com.metasconsultoria.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Project {
    private int idProject;
    private String name;
    private String description;
    private boolean publicProject;
    private Date date;
    private int idCity;

    public Project() {

    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublicProject() {
        return publicProject;
    }

    public void setPublicProject(boolean publicProject) {
        this.publicProject = publicProject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public static List<Project> getProjects(Connection conn) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Project";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setIdProject(rs.getInt("cod_project"));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setPublicProject(rs.getBoolean("public"));

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

    public static Project getProjectById(Connection conn, int id) {
        Project project = null;
        String sql = "SELECT * FROM Project WHERE cod_project = ?";

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

}
