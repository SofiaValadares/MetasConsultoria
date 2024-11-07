import com.metasconsultoria.database.ConnectDatabase;
import com.metasconsultoria.entities.City;
import com.metasconsultoria.entities.Project;
import com.metasconsultoria.entities.User;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection conn = ConnectDatabase.getConnection()) {
            if (conn != null) {
                System.out.println("Banco de dados pronto para uso.");

                List<Project> projects = Project.getProjects(conn);

                List<City> cities = City.getCities(conn);

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
