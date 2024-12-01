import java.sql.*;

public class LivroCRUD {
    Connection connect = null;

    public void conexaoBD() {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    public void createLivro(Livro l) {

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

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

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

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

    public Livro getLivroPorId(int livroId) {
        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

        String sql = "SELECT * FROM livro WHERE id = ?";
        try {
            System.out.println("Conectando ao banco...");
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, livroId);

            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                int id = rst.getInt("id");
                String nome = rst.getString("nome_livro");
                int autorId = rst.getInt("autor");
                int generoId = rst.getInt("genero");

                Autor autor = new AutorCRUD().getAutorPorId(autorId);
                Genero genero = new GeneroCRUD().getGeneroPorId(generoId);

                return new Livro(id, nome, genero, autor); // Preço será calculado pelo gênero
            } else {
                System.out.println("Livro com ID " + livroId + " não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar livro: " + ex.getMessage());
            ex.printStackTrace(); // Exibe detalhes do erro
        }
        return null;
    }



    public boolean existeLivroPorAutor(int autorId) {

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

        String sql = "SELECT COUNT(*) AS total FROM livro WHERE autor = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, autorId);

            ResultSet rst = pst.executeQuery();

            if (rst.next()) {
                int total = rst.getInt("total");
                return total > 0; // Retorna verdadeiro se houver pelo menos um livro com esse autor
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados: " + ex.getMessage());
        }
        return false;

    }

    public boolean existeLivroPorGenero(int generoId) {

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

        String sql = "SELECT COUNT(*) AS total FROM livro WHERE genero = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.setInt(1, generoId);

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                int total = rst.getInt("total");
                return total > 0; // Retorna verdadeiro se houver ao menos um livro com esse gênero
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar resultados");
        }

        return false;
    }


    public void updateLivro (Livro l) {

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

        String sql = "UPDATE livro SET nome_livro = ?, preco = ?, genero = ?, autor = ? WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setString(1, l.getNome_livro());
            pst.setDouble(2, l.getPreco());
            pst.setInt(3, l.getGenero().getId());
            pst.setInt(4, l.getAutor().getId());
            pst.setInt(5, l.getId());

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

    public void deleteLivro(int id) {

        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada. Chame conexaoBD() primeiro.");
        }

        String sql = "DELETE FROM livro WHERE id = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sql);

            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Livro deletado com sucesso.");
            } else {
                System.out.println("Nenhum livro encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao deletar o livro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
