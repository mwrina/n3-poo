import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneroCRUD {

    Connection connect = null;

    public void conexaoBD () {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    public void createGenero (Genero g) {
        String sql = "INSERT INTO genero VALUES (?, ?)";

        try {

            PreparedStatement pst;
            //Prepara o sql para ser executado
            pst = connect.prepareStatement(sql);

            //"Setta" os valores a serem inseridos em "?, ?"
            pst.setInt(1, g.getId());
            pst.setString(2, g.getGenero());

            //Executa o sql preparado
            pst.executeUpdate();

            System.out.println("Gênero criado");
        } catch (SQLException ex) {
            System.out.println("Criação deu errado.");
        }
    }

    public void readGenero() {
        String sql = "SELECT * FROM genero";

        try (PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            // Tratar os resultados retornados pela consulta
            while (rst.next()) {
                int id = rst.getInt("id");
                String nome = rst.getString("genero");

                System.out.printf("Id: %d | Gênero: %s \n", id, nome);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void readGeneroEspecifico (Genero g) {

        String sql = "select genero from genero where id = ?";

        try {

            PreparedStatement pst;
            pst = connect.prepareStatement(sql);

            pst.setInt(1, g.getId());

            //trata os casos em que há mais de 1 resultado na pesquisa
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String nome = rst.getString(2); //Indica a segunda coluna

                System.out.printf("Id: %d | Gênero: %s \n", id, nome);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados");
        }

    }

    public void updateGenero (Genero g) {

        String sql = "UPDATE genero SET genero = ? WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setString(1, g.getGenero());
            pst.setInt(2, g.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Gênero atualizado com sucesso.");
            } else {
                System.out.println("Nenhum gênero encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao atualizar o gênero: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void deleteGenero (int id) {

        String sql = "DELETE FROM genero WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Gênero deletado com sucesso.");
            } else {
                System.out.println("Nenhum gênero encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao deletar o gênero: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
