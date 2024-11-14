public class Livro {
    private int id;
    private String nome_livro;
    private double preco;
    private Genero genero;
    private Autor autor;

    public Livro(int id, String nome_livro, double preco, Genero genero, Autor autor) {
        this.id = id;
        this.nome_livro = nome_livro;
        this.preco = preco;
        this.genero = genero;
        this.autor = autor;
    }

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
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
