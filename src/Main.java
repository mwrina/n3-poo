import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando as classes CRUD para manipular o banco de dados
        AutorCRUD autorCRUD = new AutorCRUD();
        GeneroCRUD generoCRUD = new GeneroCRUD();
        LivroCRUD livroCRUD = new LivroCRUD();

        // Conectando ao banco de dados
        autorCRUD.conexaoBD();
        generoCRUD.conexaoBD();
        livroCRUD.conexaoBD();

        // Menu de opções
        while (true) {
            System.out.println("\nBem-vindo à Livraria!\n");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Autor");
            System.out.println("2 - Cadastrar Gênero");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Consultar Livros");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar

            switch (opcao) {
                case 1:
                    // Cadastro de autor
                    System.out.print("Digite o ID do autor: ");
                    int autorId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do autor: ");
                    String nomeAutor = scanner.nextLine();
                    Autor autor = new Autor(autorId, nomeAutor);
                    autorCRUD.createAutor(autor);
                    break;

                case 2:
                    // Cadastro de gênero
                    System.out.print("Digite o ID do gênero: ");
                    int generoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do gênero (ex: Terror, Romance, Ficção Científica, Outros): ");
                    String nomeGenero = scanner.nextLine();
                    Genero genero = new Genero(generoId, nomeGenero);
                    generoCRUD.createGenero(genero);
                    break;

                case 3:
                    // Cadastro de livro
                    System.out.print("Digite o ID do livro: ");
                    int livroId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome do livro: ");
                    String nomeLivro = scanner.nextLine();

                    // Selecionar autor
                    System.out.println("Escolha um autor para o livro:");
                    autorCRUD.readAutor();  // Exibe os autores cadastrados
                    System.out.print("Digite o ID do autor: ");
                    int autorLivroId = scanner.nextInt();
                    Autor autorLivro = new Autor(autorLivroId, ""); // Criando o autor com base no ID (não precisa do nome agora)

                    // Selecionar gênero
                    System.out.println("Escolha um gênero para o livro:");
                    generoCRUD.readGenero();  // Exibe os gêneros cadastrados
                    System.out.print("Digite o ID do gênero: ");
                    int generoLivroId = scanner.nextInt();
                    Genero generoLivro = new Genero(generoLivroId, ""); // Criando o gênero com base no ID

                    // Criação do livro
                    Livro livro = new Livro(livroId, nomeLivro, generoLivro, autorLivro);
                    livroCRUD.createLivro(livro);
                    break;

                case 4:
                    // Consultar livros
                    System.out.println("Livros cadastrados:");
                    livroCRUD.readLivro();  // Mostra os livros cadastrados
                    break;

                case 5:
                    // Sair do programa
                    System.out.println("Finalizando...");
                    scanner.close();
                    return;  // Finaliza o programa

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
