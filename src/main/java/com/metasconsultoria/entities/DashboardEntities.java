package com.metasconsultoria.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardEntities {
    private int totalClients; // Número total de clientes
    private int totalMunicipalities; // Número total de municípios
    private int ongoingProjects; // Número de projetos em andamento
    private int activeEmployees; // Número de colaboradores ativos
    private List<RevenueData> monthlyRevenue; // Dados de receita do mês
    private List<ProjectEntities> newProjectsThisMonth; // Lista de projetos novos do mês

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectEntities {
        private String name; // Nome do projeto
        private String municipality; // Município onde o projeto está sendo realizado
        private String startDate; // Data de início do projeto
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RevenueData {
        private String date; // Data específica do mês (ex.: "2024-11-01")
        private double revenue; // Receita do dia
    }
}
