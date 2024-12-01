import java.sql.*;

public class CompraCRUD {

    Connection connect = null;

    public void conexaoBD () {
        connect = ConnectionMySQL.getConnectionMySQL();
    }

    // Método para salvar os livros da compra no banco de dados
    public void salvarCompra(Compra compra) {
        if (connect == null) {
            throw new IllegalStateException("A conexão com o banco de dados não foi inicializada.");
        }

        // Supondo que a compra será salva como uma transação, salvando os livros no banco
        // Não há uma tabela de 'compra' agora, mas os livros associados precisam ser armazenados
        try {
            for (Livro livro : compra.getLivros()) {
                String sql = "INSERT INTO compra_livro (livro_id) VALUES (?)";
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.setInt(1, livro.getId());

                pst.executeUpdate();
            }

            System.out.println("Livros da compra registrados no banco com sucesso!");

        } catch (SQLException ex) {
            System.out.println("Erro ao salvar os livros da compra: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
