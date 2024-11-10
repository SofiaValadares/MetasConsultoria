package com.metasconsultoria;

import com.metasconsultoria.controllers.GenericController;
import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.entities.Client;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = ConnectDatabase.getConnection()) {
            if (conn != null) {
                System.out.println("Banco de dados pronto para uso.");


                City city = new City("testre", "teste");
                GenericController.insertInto(conn, City.class, city);

                Client client = new Client(12, 1);
                GenericController.insertInto(conn, Client.class, client);


            } else {
                System.out.println("Falha ao conectar ou criar o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o banco de dados: " + e.getMessage());
        }
    }

}