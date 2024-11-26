public class Genero {

    private int id;
    private String genero;

    public Genero(int id, String genero) {
        this.id = id;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // RETORNA O PREÇO COM BASE NO GÊNERO
    public double getPreco() {
        switch (this.genero) {
            case "Terror":
                return 50.00;
            case "Romance":
                return 30.00;
            case "Ficção Científica":
                return 40.00;
            default:
                return 45.00; // Preço para outros gêneros
        }
    }
}
