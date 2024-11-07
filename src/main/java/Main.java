import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.User;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = ConnectDatabase.getConnection()) {
            if (conn != null) {
                System.out.println("Banco de dados pronto para uso.");


                User user = User.getUserByLogin(conn, "andresa@exemplo.com", "Oi12345!");

                if (user != null) {
                    System.out.println(user.toString() + user.getPassword());
                } else {
                    System.out.println("Usuário não encontrado.");
                }

            } else {
                System.out.println("Falha ao conectar ou criar o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o banco de dados: " + e.getMessage());
        }
    }
}
