package com.metasconsultoria.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private int idProject;
    private String name;
    private String description;
    private List<NextStep> nextSteps = new ArrayList<>();
    private boolean publicProject;
    private List<Report> reports = new ArrayList<>();
    private City location;

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

    public List<NextStep> getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(List<NextStep> nextSteps) {
        this.nextSteps = nextSteps;
    }

    public boolean isPublicProject() {
        return publicProject;
    }

    public void setPublicProject(boolean publicProject) {
        this.publicProject = publicProject;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public City getLocation() {
        return location;
    }

    public void setLocation(City location) {
        this.location = location;
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return project;
    }
}
