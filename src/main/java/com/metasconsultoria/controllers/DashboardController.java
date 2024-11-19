package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.DashboardEntities;
import com.metasconsultoria.service.DashboardService;
import org.springframework.web.bind.annotation.CrossOrigin; // Import necessário
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3002")
public class DashboardController {

    @GetMapping("/dashboard")
    public DashboardEntities getDashboardStatistics() throws SQLException {
        return DashboardService.getStatics();
    }
}
