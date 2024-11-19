package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.DashboardEntities;
import com.metasconsultoria.entities.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private ProjectRepository() {}

    public static void insertInto(Project project) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "INSERT INTO Project (name, description, public, date, fk_city) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setBoolean(3, project.isPublicProject());
            ps.setDate(4, (Date) project.getDate());
            ps.setInt(5, project.getIdProject());
            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void deleteById(int codProject) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        String sql = "DELETE FROM Project WHERE cod_project = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codProject);
            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void updateData(Project project) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        String sql = "UPDATE Project SET name = ?, description = ?, public = ?, date = ?, fk_city = ? WHERE cod_project = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setBoolean(3, project.isPublicProject());
            ps.setDate(4, (Date) project.getDate());
            ps.setInt(5, project.getIdCity());
            ps.setInt(6, project.getIdProject());
            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static List<Project> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
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

        conn.close();
        return projects;
    }

    public static Project selectById(int codProject) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
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

                rs.close();
            }

            ps.close();
        }

        conn.close();
        return project;
    }

    public static int countData() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "SELECT COUNT(DISTINCT cod_project) FROM Project WHERE date is null";

        int count = 0;

        assert conn != null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }

        conn.close();
        return count;
    }

    public static List<Project> selectFinishList() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Project WHERE date IS NOT NULL ORDER BY date";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    public static List<DashboardEntities.RevenueData> selectMonthlyRevenue() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        List<DashboardEntities.RevenueData> revenues = new ArrayList<>();
        String sql = """
        SELECT 
            DATE_FORMAT(date, '%Y-%m-01') AS month, 
            SUM(money) AS total_revenue
        FROM Project
        WHERE date IS NOT NULL
        GROUP BY DATE_FORMAT(date, '%Y-%m-01')
        ORDER BY DATE_FORMAT(date, '%Y-%m-01');
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DashboardEntities.RevenueData revenueData = new DashboardEntities.RevenueData(
                        rs.getString("month"),     // Mês no formato "YYYY-MM-01"
                        rs.getDouble("total_revenue") // Receita total do mês
                );

                revenues.add(revenueData);
            }
        }

        return revenues;
    }


}
