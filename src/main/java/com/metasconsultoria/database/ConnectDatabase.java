package com.metasconsultoria.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ConnectDatabase {
    private static final String POPULATE_SCRIPT_PATH = "src/main/resources/populate.sql";

    public static Connection getConnection() {
        Properties prop = new Properties();

        try (InputStream input = ConnectDatabase.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Arquivo config.properties não encontrado.");
                return null;
            }

            // Carrega as propriedades do arquivo config.properties
            prop.load(input);
            String url = prop.getProperty("database.url");
            String user = prop.getProperty("database.user");
            String password = prop.getProperty("database.password");

            // Conectar ao banco de dados (cria o banco se ele não existir)
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao banco de dados.");

            if (isDatabaseEmpty(conn)) {
                System.out.println("Executando o script de população...");
                runScript(conn, POPULATE_SCRIPT_PATH);
            }

            return conn;

        } catch (SQLException | IOException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        } 
    }

    // Método para verificar se o banco de dados está vazio (ou seja, se as tabelas não existem)
    private static boolean isDatabaseEmpty(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("USE DatabaseMetas"); // Tente selecionar da tabela User como teste
            return false; // Se a consulta for bem-sucedida, o banco de dados não está vazio
        } catch (SQLException e) {
            return true; // Se ocorrer um erro, assumimos que o banco de dados está vazio (tabelas não existem)
        }
    }

    // Método para rodar o script de população no banco de dados
    private static void runScript(Connection conn, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {

            String line;
            StringBuilder sql = new StringBuilder();

            // Lê o arquivo SQL linha por linha
            while ((line = br.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    stmt.execute(sql.toString()); // Executa o comando SQL completo
                    sql.setLength(0); // Limpa o StringBuilder para o próximo comando
                }
            }
            System.out.println("Script de população executado com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de script SQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao executar o script SQL: " + e.getMessage());
        }
    }
}
