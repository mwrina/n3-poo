import java.util.*;

public class Compra {
    private List<Livro> livros;
    private double totalCompra;
    private boolean compraAutorizada;

    public Compra() {
        this.livros = new ArrayList<>();
        this.totalCompra = 0.0;
        this.compraAutorizada = false;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public boolean precisaDePermissao() {
        return totalCompra > 100.00;
    }

    public boolean realizarCompra(String respostaPermissao) {
        if (precisaDePermissao()) {
            if (respostaPermissao.equalsIgnoreCase("sim")) {
                compraAutorizada = true;
                System.out.println("Compra autorizada e concluída!");
            } else {
                System.out.println("Compra cancelada por falta de permissão.");
                compraAutorizada = false;
            }
        } else {
            compraAutorizada = true;
            System.out.println("Compra concluída com sucesso!");
        }
        return compraAutorizada;
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
        this.totalCompra += livro.getPreco();
    }

    public void exibirCarrinho() {
        if (livros.isEmpty()) {
            System.out.println("Carrinho vazio!");
        } else {
            for (Livro livro : livros) {
                System.out.printf("Livro: %s, Preço: R$%.2f%n", livro.getNome_livro(), livro.getPreco());
            }
        }
    }
}
