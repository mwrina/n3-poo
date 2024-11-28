import java.sql.*;

public class LivroCRUD {
    Connection connect = null;

    public void conexaoBD() {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    private double calcularPrecoPorGenero(String generoNome) {
        switch (generoNome.toLowerCase()) {
            case "terror":
                return 50.00;
            case "romance":
                return 30.00;
            case "ficção científica":
                return 40.00;
            case "outros":
                return 45.00;
            default:
                System.out.println("Gênero não reconhecido. Aplicando preço padrão de R$45,00.");
                return 45.00;
        }
    }

    public void createLivro(Livro l) {

        String sql = "INSERT INTO livro (id, nome_livro, preco, genero, autor) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            // Definindo os parâmetros de inserção
            pst.setInt(1, l.getId());
            pst.setString(2, l.getNome_livro());
            pst.setDouble(3, l.getPreco());
            pst.setInt(4, l.getGenero().getId());  // Relacionamento com o gênero
            pst.setInt(5, l.getAutor().getId());   // Relacionamento com o autor

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
                int generoId = rst.getInt("genero");
                int autorId = rst.getInt("autor");

                // Mostrar os livros com seus dados
                System.out.printf("ID: %d | Livro: %s | Preço: R$ %.2f | Gênero ID: %d | Autor ID: %d\n", id, nomeLivro, preco, generoId, autorId);
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao consultar livros: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void updateLivro (Livro l) {

        String sql = "UPDATE genero SET nome_livro = ?, preco = ?, genero = ?, autor = ? WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setString(1, l.getNome_livro());
            pst.setDouble(2, l.getPreco());
            pst.setInt(3, l.getGenero().getId());
            pst.setInt(4, l.getAutor().getId());
            pst.setInt(3, l.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso.");
            } else {
                System.out.println("Nenhum livro encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao atualizar o livro: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
