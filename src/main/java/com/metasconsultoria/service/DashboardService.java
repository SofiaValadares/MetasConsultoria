package com.metasconsultoria.service;

import com.metasconsultoria.entities.DashboardEntities;
import com.metasconsultoria.repository.CityRepository;
import com.metasconsultoria.repository.ClientRepository;
import com.metasconsultoria.repository.CollaboratorRepository;
import com.metasconsultoria.repository.ProjectRepository;

import java.sql.SQLException;

public class DashboardService {
    private DashboardService() {}

    public static DashboardEntities getStatics() throws SQLException {
        DashboardEntities dashboardData = new DashboardEntities();

        dashboardData.setTotalClients(ClientRepository.countData());
        dashboardData.setTotalMunicipalities(CityRepository.countData());
        dashboardData.setOngoingProjects(ProjectRepository.countData());
        dashboardData.setActiveEmployees(CollaboratorRepository.countData());
        dashboardData.setNewProjectsThisMonth(ProjectRepository.selectFinishList());
        dashboardData.setMonthlyRevenue(ProjectRepository.selectMonthlyRevenue());

        return dashboardData;
    }
}
