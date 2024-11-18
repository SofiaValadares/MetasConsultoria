package com.metasconsultoria.service;

import com.metasconsultoria.entities.Report;
import com.metasconsultoria.repository.ReportRepository;

import java.sql.SQLException;
import java.util.List;

public class ReportService {

    public static void insertReport(Report report) throws SQLException {
        ReportRepository.insertInto(report);
    }

    public static void updateReport(Report report) throws SQLException {
        ReportRepository.updateData(report);
    }

    public static void deleteReport(int idReport) throws SQLException {
        ReportRepository.deleteById(idReport);
    }

    public static List<Report> getAllReports() throws SQLException {
        return ReportRepository.selectAll();
    }

    public static Report getReportById(int idReport) throws SQLException {
        return ReportRepository.selectById(idReport);
    }
}
