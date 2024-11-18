package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {

    private ReportRepository() {}

    public static void insertInto(Connection conn, Report report) throws SQLException {
        String sql = "INSERT INTO Report (report_date, description) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(report.getDate().getTime()));
            ps.setString(2, report.getDescription());

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, Report report) throws SQLException {
        String sql = "UPDATE Report SET report_date = ?, description = ? WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(report.getDate().getTime()));
            ps.setString(2, report.getDescription());
            ps.setInt(3, report.getIdReport());

            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public static List<Report> selectAll(Connection conn) throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Report";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Report report = new Report();
                report.setIdReport(rs.getInt("cod_report"));
                report.setDate(rs.getDate("report_date"));
                report.setDescription(rs.getString("description"));
                // Você pode precisar buscar o objeto Collaborator correspondente e setar no report

                reports.add(report);
            }
        }

        return reports;
    }

    public static Report selectById(Connection conn, int id) throws SQLException {
        Report report = null;
        String sql = "SELECT * FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report = new Report();
                    report.setIdReport(rs.getInt("cod_report"));
                    report.setDate(rs.getDate("report_date"));
                    report.setDescription(rs.getString("description"));
                    // Você pode precisar buscar o objeto Collaborator correspondente e setar no report
                }
            }
        }

        return report;
    }
}
