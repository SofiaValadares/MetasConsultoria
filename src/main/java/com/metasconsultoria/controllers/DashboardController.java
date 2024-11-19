package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.DashboardEntities;
import com.metasconsultoria.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class DashboardController {

    /**
     * Endpoint para obter os dados estáticos do dashboard.
     *
     * @return Objeto DashboardEntities com as informações consolidadas.
     * @throws SQLException caso ocorra um problema ao acessar os dados.
     */
    @GetMapping("/dashboard")
    public DashboardEntities getDashboardStatistics() throws SQLException {
        // Chamando o método estático diretamente, já que DashboardService não tem instância.
        return DashboardService.getStatics();
    }
}
