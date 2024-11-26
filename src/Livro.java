public class Livro {
    private int id;
    private String nome_livro;
    private double preco;
    private Genero genero;
    private Autor autor;

    // Construtor
    public Livro(int id, String nome_livro, Genero genero, Autor autor) {
        this.id = id;
        this.nome_livro = nome_livro;
        this.genero = genero;
        this.autor = autor;
        this.preco = genero.getPreco(); // Preço determinado pelo gênero
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome_livro() {
        return nome_livro;
    }

    public double getPreco() {
        return preco;
    }

    public Genero getGenero() {
        return genero;
    }

    public Autor getAutor() {
        return autor;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
        this.preco = genero.getPreco(); // Atualiza o preço quando o gênero muda
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
