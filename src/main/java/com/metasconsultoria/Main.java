package com.metasconsultoria;

import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.CRUDs.CityCRUD;
import com.metasconsultoria.CRUDs.ProjectCRUD;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.entities.Project;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = ConnectDatabase.getConnection()) {
            if (conn != null) {
                System.out.println("Banco de dados pronto para uso.");

                // Testes para CityCRUD
                createCity(conn);
                getCityById(conn, 1);
                getCitiesByState(conn, "PE");
                updateCity(conn, 1, "Recife Atualizado", "PE");
                deleteCity(conn, 2);
                getCitiesWithPublicProjects(conn);

                // Testes para ProjectCRUD
                createProject(conn);
                getProjectById(conn, 1);
                getAllProjects(conn);
                getPublicProjects(conn);
                updateProject(conn, 1, "Projeto Atualizado", "Descrição atualizada", false, 1);
                deleteProject(conn, 3);

            } else {
                System.out.println("Falha ao conectar ou criar o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o banco de dados: " + e.getMessage());
        }
    }

    // Métodos de teste para CityCRUD
    public static void createCity(Connection conn) {
        City city = new City();
        city.setName("Recife");
        city.setState("PE");
        CityCRUD.createCity(conn, city);
        System.out.println("Cidade criada: " + city.getName());
    }

    public static void getCityById(Connection conn, int id) {
        City city = CityCRUD.getCityById(conn, id);
        if (city != null) {
            System.out.println("Cidade encontrada: " + city.getName() + ", " + city.getState());
        } else {
            System.out.println("Cidade não encontrada.");
        }
    }


    public static void getCitiesByState(Connection conn, String state) {
        List<City> cities = CityCRUD.getCitiesByState(conn, state);
        for (City city : cities) {
            System.out.println("Cidade encontrada pelo estado: " + city.getName() + ", " + city.getState());
        }
    }

    public static void updateCity(Connection conn, int id, String newName, String newState) {
        City city = new City();
        city.setIdCity(id);
        city.setName(newName);
        city.setState(newState);
        CityCRUD.updateCity(conn, city);
        System.out.println("Cidade atualizada: " + city.getName());
    }

    public static void deleteCity(Connection conn, int id) {
        CityCRUD.deleteCity(conn, id);
        System.out.println("Cidade deletada com ID: " + id);
    }

    public static void getCitiesWithPublicProjects(Connection conn) {
        List<City> cities = CityCRUD.getCitiesWithPublicProjects(conn);
        for (City city : cities) {
            System.out.println("Cidade com projeto público: " + city.getName() + ", " + city.getState());
        }
    }

    // Métodos de teste para ProjectCRUD
    public static void createProject(Connection conn) {
        Project project = new Project();
        project.setName("Novo Projeto");
        project.setDescription("Descrição do projeto");
        project.setPublicProject(true);
        project.setIdCity(1); // Associa o projeto a uma cidade
        ProjectCRUD.createProject(conn, project);
        System.out.println("Projeto criado: " + project.getName());
    }

    public static void getProjectById(Connection conn, int id) {
        Project project = ProjectCRUD.getProjectById(conn, id);
        if (project != null) {
            System.out.println("Projeto encontrado: " + project.getName() + ", Descrição: " + project.getDescription());
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }

    public static void getAllProjects(Connection conn) {
        List<Project> projects = ProjectCRUD.getAllProjects(conn);
        for (Project project : projects) {
            System.out.println("Projeto: " + project.getName() + ", Público: " + project.isPublicProject());
        }
    }

    public static void getPublicProjects(Connection conn) {
        List<Project> projects = ProjectCRUD.getPublicProjects(conn);
        for (Project project : projects) {
            System.out.println("Projeto público: " + project.getName());
        }
    }

    public static void updateProject(Connection conn, int id, String newName, String newDescription, boolean isPublic, int cityId) {
        Project project = new Project();
        project.setIdProject(id);
        project.setName(newName);
        project.setDescription(newDescription);
        project.setPublicProject(isPublic);
        project.setIdCity(cityId);
        ProjectCRUD.updateProject(conn, project);
        System.out.println("Projeto atualizado: " + project.getName());
    }

    public static void deleteProject(Connection conn, int id) {
        ProjectCRUD.deleteProject(conn, id);
        System.out.println("Projeto deletado com ID: " + id);
    }
}
