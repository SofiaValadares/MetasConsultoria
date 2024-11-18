package com.metasconsultoria.repository;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {

    private ReportRepository() {}

    public static void insertInto(Report report) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        
        String sql = "INSERT INTO Report (report_date, description) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(report.getDate().getTime()));
            ps.setString(2, report.getDescription());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void updateData(Report report) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "UPDATE Report SET report_date = ?, description = ? WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(report.getDate().getTime()));
            ps.setString(2, report.getDescription());
            ps.setInt(3, report.getIdReport());

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static void deleteById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        String sql = "DELETE FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
        }

        conn.close();
    }

    public static List<Report> selectAll() throws SQLException {
        Connection conn = ConnectDatabase.getConnection();
        
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

            rs.close();
            ps.close();
        }


        conn.close();
        return reports;
    }

    public static Report selectById(int id) throws SQLException {
        Connection conn = ConnectDatabase.getConnection();

        Report report = null;
        String sql = "SELECT * FROM Report WHERE cod_report = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report = Report.builder()
                            .idReport(rs.getInt("cod_report"))
                            .date(rs.getDate("report_date"))
                            .description(rs.getString("description"))
                            .build();
                }

                rs.close();
            }

            ps.close();
        }


        conn.close();
        return report;
    }
}
