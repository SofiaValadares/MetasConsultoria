package com.metasconsultoria;

import com.metasconsultoria.database.ConnectDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.Map;

@SpringBootApplication
public class MetasConsultoriaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        // Configurar manualmente propriedades do Spring Boot
        SpringApplication app = new SpringApplication(MetasConsultoriaApplication.class);
        app.setDefaultProperties(Map.of(
                "server.port", "8080"
        ));
        app.run(args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Iniciando a aplicação...");

        try (Connection conn = ConnectDatabase.getConnection()) {
            if (conn != null && conn.isValid(5)) { // Timeout de 5 segundos
                System.out.println("Banco de dados conectado com sucesso e pronto para uso!");
            } else {
                System.out.println("Falha ao conectar ou validar o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o banco de dados:");
            e.printStackTrace();
        }
    }
}
