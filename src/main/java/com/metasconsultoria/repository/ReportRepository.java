package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {

    private ReportRepository() {}

    public static void insert(Connection conn, Report report) throws SQLException {
        String sql = "INSERT INTO Report (report_date, description) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, (Date) report.getDate());
            ps.setString(2, report.getDescription());
            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int codReport) throws SQLException {
        String sql = "DELETE FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codReport);
            ps.executeUpdate();
        }
    }

    public static void update(Connection conn, Report report) throws SQLException {
        String sql = "UPDATE Report SET report_date = ?, description = ? WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, (Date) report.getDate());
            ps.setString(2, report.getDescription());
            ps.setInt(3, report.getIdReport());
            ps.executeUpdate();
        }
    }

    public static List<Report> selectAll(Connection conn) throws SQLException {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Report";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Report report = Report.builder()
                        .idReport(rs.getInt("cod_report"))
                        .date(rs.getDate("report_date"))
                        .description(rs.getString("description"))
                        .build();
                reports.add(report);
            }
        }

        return reports;
    }

    public static Report selectById(Connection conn, int codReport) throws SQLException {
        Report report = null;
        String sql = "SELECT * FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codReport);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report = Report.builder()
                            .idReport(rs.getInt("cod_report"))
                            .date(rs.getDate("report_date"))
                            .description(rs.getString("description"))
                            .build();
                }
            }
        }

        return report;
    }
}
