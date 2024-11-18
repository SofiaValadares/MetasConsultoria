package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.Report;
import com.metasconsultoria.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @GetMapping("/")
    public ResponseEntity<List<Report>> getAllReports() throws SQLException {
        List<Report> reports = ReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) throws SQLException {
        Report report = ReportService.getReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createReport(@RequestBody Report report) throws SQLException {
        ReportService.insertReport(report);
        return ResponseEntity.ok("Relatório criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReport(@PathVariable int id, @RequestBody Report report) throws SQLException {
        report.setIdReport(id);
        ReportService.updateReport(report);
        return ResponseEntity.ok("Relatório atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable int id) throws SQLException {
        ReportService.deleteReport(id);
        return ResponseEntity.ok("Relatório deletado com sucesso.");
    }
}
