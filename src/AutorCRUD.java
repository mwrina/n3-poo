import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorCRUD {

    Connection connect = null;

    public void conexaoBD () {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    public void createAutor (Autor a) {
        String sql = "INSERT INTO autor VALUES (?, ?)";

        try {

            PreparedStatement pst;
            //Prepara o sql para ser executado
            pst = connect.prepareStatement(sql);

            //"Setta" os valores a serem inseridos em "?, ?"
            pst.setInt(1, a.getId());
            pst.setString(2, a.getNome_autor());

            //Executa o sql preparado
            pst.executeUpdate();

            System.out.println("Autor criado");
        } catch (SQLException ex) {
            System.out.println("Criação deu errado.");
        }
    }

    public void readAutor() {
        String sql = "SELECT * FROM autor";

        try (PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            // Tratar os resultados retornados pela consulta
            while (rst.next()) {
                int id = rst.getInt("id");
                String nome = rst.getString("nome_autor");

                System.out.printf("Id: %d | Nome: %s \n", id, nome);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void readAutorEspecifico (Autor a) {

        String sql = "select nome_autor from autor where id = ?";

        try {

            PreparedStatement pst;
            pst = connect.prepareStatement(sql);

            pst.setInt(1, a.getId());

            //trata os casos em que há mais de 1 resultado na pesquisa
            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                int id = rst.getInt("id");
                String nome = rst.getString(2); //Indica a segunda coluna

                System.out.printf("Id: %d | Nome: %s \n", id, nome);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados");
        }

    }

    public void updateAutor (Autor a) {

        String sql = "UPDATE autor SET nome_autor = ? WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setString(1, a.getNome_autor());
            pst.setInt(2, a.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Autor atualizado com sucesso.");
            } else {
                System.out.println("Nenhum autor encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao atualizar o autor: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void deleteAutor (int id) {

        String sql = "DELETE FROM autor WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Autor deletado com sucesso.");
            } else {
                System.out.println("Nenhum autor encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao deletar o autor: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
