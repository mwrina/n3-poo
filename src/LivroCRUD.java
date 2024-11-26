import java.sql.*;

public class LivroCRUD {
    Connection connect = null;

    public void conexaoBD() {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    public void createLivro(Livro livro) {
        String sql = "INSERT INTO livro (id, nome_livro, preco, genero_id, autor_id) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            // Definindo os parâmetros de inserção
            pst.setInt(1, livro.getId());
            pst.setString(2, livro.getNome_livro());
            pst.setDouble(3, livro.getPreco());
            pst.setInt(4, livro.getGenero().getId());  // Relacionamento com o gênero
            pst.setInt(5, livro.getAutor().getId());   // Relacionamento com o autor

            // Executando a inserção no banco de dados
            pst.executeUpdate();

            System.out.println("Livro criado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Falha ao criar o livro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void readLivro() {
        String sql = "SELECT * FROM livro";

        try (PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            // Tratar os resultados retornados pela consulta
            while (rst.next()) {
                int id = rst.getInt("id");
                String nomeLivro = rst.getString("nome_livro");
                double preco = rst.getDouble("preco");
                int generoId = rst.getInt("genero_id");
                int autorId = rst.getInt("autor_id");

                // Mostrar os livros com seus dados
                System.out.printf("ID: %d | Livro: %s | Preço: R$ %.2f | Gênero ID: %d | Autor ID: %d\n", id, nomeLivro, preco, generoId, autorId);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao consultar livros: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
