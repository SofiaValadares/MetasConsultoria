package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Report;
import com.metasconsultoria.repository.ReportRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportService {
    private static final Connection conn = ConnData.connection;

    public static void insertReport(Report report) throws SQLException {
        ReportRepository.insertInto(conn, report);
    }

    public static void updateReport(Report report) throws SQLException {
        ReportRepository.updateData(conn, report);
    }

    public static void deleteReport(int idReport) throws SQLException {
        ReportRepository.deleteById(conn, idReport);
    }

    public static List<Report> getAllReports() throws SQLException {
        return ReportRepository.selectAll(conn);
    }

    public static Report getReportById(int idReport) throws SQLException {
        return ReportRepository.selectById(conn, idReport);
    }
}
