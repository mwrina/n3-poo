import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando as classes CRUD para manipular o banco de dados
        AutorCRUD autorCRUD = new AutorCRUD();
        GeneroCRUD generoCRUD = new GeneroCRUD();
        LivroCRUD livroCRUD = new LivroCRUD();
        CompraCRUD compraCRUD = new CompraCRUD();

        // Conectando ao banco de dados
        autorCRUD.conexaoBD();
        generoCRUD.conexaoBD();
        livroCRUD.conexaoBD();
        compraCRUD.conexaoBD();

        // Menu de opções
        while (true) {
            System.out.println("\nBem-vindo à Livraria!\n");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Editar");
            System.out.println("3 - Listar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Comprar livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar

            switch (opcao) {
                case 1: //CADASTRAR

                    System.out.println("1 - Cadastrar Autor");
                    System.out.println("2 - Cadastrar Gênero");
                    System.out.println("3 - Cadastrar Livro");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha: ");

                    opcao = scanner.nextInt();
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

                        case 3: //CADASTRAR LIVRO

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
                            scanner.nextLine();
                            Autor autorLivro = new Autor(autorLivroId, ""); // Criar autor pelo ID (nome opcional aqui)

                            // Selecionar gênero
                            generoCRUD.readGenero();

                            System.out.print("Digite o ID do gênero: ");
                            int generoLivroId = scanner.nextInt();
                            scanner.nextLine();

                            // Buscar nome do gênero pelo ID
                            nomeGenero = generoCRUD.readGeneroEspecifico(generoLivroId);

                            if (nomeGenero != null) {
                                Genero generoLivro = new Genero(generoLivroId, nomeGenero); // Cria o objeto gênero com o nome buscado
                                Livro livro = new Livro(livroId, nomeLivro, generoLivro, autorLivro);
                                livroCRUD.createLivro(livro);

                                System.out.printf("Livro cadastrado com sucesso! Preço: R$%.2f\n", livro.getPreco());
                            } else {
                                System.out.println("Cadastro de livro cancelado: gênero inválido.");
                            }

                            break;

                        case 0:
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                            break;
                    }
                    break;

                case 2:

                    System.out.println("1 - Editar Autor");
                    System.out.println("2 - Editar Gênero");
                    System.out.println("3 - Editar Livro");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha: ");

                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpar

                    switch (opcao) {
                        case 1: // Editar Autor
                            System.out.println("Autores cadastrados:");
                            autorCRUD.readAutor(); // Exibe autores cadastrados

                            System.out.print("Digite o ID do autor que deseja editar: ");
                            int autorId = scanner.nextInt();
                            scanner.nextLine(); // Limpar

                            System.out.print("Digite o novo nome do autor: ");
                            String novoNomeAutor = scanner.nextLine();

                            Autor autorEditado = new Autor(autorId, novoNomeAutor);
                            autorCRUD.updateAutor(autorEditado);
                            break;

                        case 2: // Editar Gênero
                            System.out.println("Gêneros cadastrados:");
                            generoCRUD.readGenero(); // Exibe gêneros cadastrados

                            System.out.print("Digite o ID do gênero que deseja editar: ");
                            int generoId = scanner.nextInt();
                            scanner.nextLine(); // Limpar

                            System.out.print("Digite o novo nome do gênero: ");
                            String novoNomeGenero = scanner.nextLine();

                            Genero generoEditado = new Genero(generoId, novoNomeGenero);
                            generoCRUD.updateGenero(generoEditado);
                            break;

                        case 3: // Editar Livro
                            System.out.println("Livros cadastrados:");
                            livroCRUD.readLivro(); // Exibe livros cadastrados

                            System.out.print("Digite o ID do livro que deseja editar: ");
                            int livroId = scanner.nextInt();
                            scanner.nextLine(); // Limpar buffer

                            System.out.print("Digite o novo nome do livro: ");
                            String novoNomeLivro = scanner.nextLine();

                            // Selecionar novo autor
                            System.out.println("Escolha um novo autor para o livro:");
                            autorCRUD.readAutor();
                            System.out.print("Digite o ID do novo autor: ");
                            int novoAutorId = scanner.nextInt();
                            scanner.nextLine();
                            Autor novoAutorLivro = new Autor(novoAutorId, ""); // Cria o autor com base no ID

                            // Selecionar novo gênero
                            System.out.println("Escolha um novo gênero para o livro:");
                            generoCRUD.readGenero();
                            System.out.print("Digite o ID do novo gênero: ");
                            int novoGeneroId = scanner.nextInt();
                            scanner.nextLine();

                            String nomeGenero = generoCRUD.readGeneroEspecifico(novoGeneroId);

                            if (nomeGenero != null) {
                                Genero novoGeneroLivro = new Genero(novoGeneroId, nomeGenero); // Cria o gênero com o nome buscado

                                Livro livroEditado = new Livro(livroId, novoNomeLivro, novoGeneroLivro, novoAutorLivro);

                                System.out.printf("Preço atualizado automaticamente com base no gênero (%s): R$%.2f\n", nomeGenero, livroEditado.getPreco());

                                livroCRUD.updateLivro(livroEditado);

                            } else {
                                System.out.println("Atualização cancelada: gênero inválido.");
                            }
                            break;

                        case 0:
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                            break;
                    }
                    break;

                case 3:

                    System.out.println("1 - Listar Autores");
                    System.out.println("2 - Listar Gêneros");
                    System.out.println("3 - Listar Livros");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha: ");

                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpar

                    switch (opcao) {
                        case 1:
                            System.out.println("Autores cadastrados:");
                            autorCRUD.readAutor();
                            break;

                        case 2:
                            System.out.println("Gêneros cadastrados:");
                            generoCRUD.readGenero();
                            break;

                        case 3:
                            System.out.println("Livros cadastrados:");
                            livroCRUD.readLivro();
                            break;

                        case 0:
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                            break;

                    }
                    break;

                case 4: //EXCLUIR
                    System.out.println("1 - Excluir Autor");
                    System.out.println("2 - Excluir Gênero");
                    System.out.println("3 - Excluir Livro");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha: ");

                    opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                        case 1: //EXCLUIR AUTOR

                            autorCRUD.readAutor();

                            System.out.print("Informe o ID do autor que deseja deletar: ");
                            int excluirAutorId = scanner.nextInt();
                            scanner.nextLine();

                            if (livroCRUD.existeLivroPorAutor(excluirAutorId)) {
                                System.out.println("Não é possível excluir o autor, pois existe pelo menos um livro cadastrado sob a autoria dele.");
                            } else {
                                autorCRUD.deleteAutor(excluirAutorId);
                                System.out.println("Autor excluído com sucesso.");
                            }
                            break;

                        case 2: //EXCLUIR GENERO

                            generoCRUD.readGenero();

                            System.out.print("Informe o ID do gênero que deseja deletar: ");
                            int excluirGeneroId = scanner.nextInt();
                            scanner.nextLine();

                            if (livroCRUD.existeLivroPorGenero(excluirGeneroId)) {
                                System.out.println("Não é possível excluir o gênero, pois existe pelo menos um livro nele");
                            } else {
                                generoCRUD.deleteGenero(excluirGeneroId);
                                System.out.println("Gênero excluído com sucesso.");
                            }
                            break;

                        case 3:
                            livroCRUD.readLivro();

                            System.out.print("Informe o ID do livro que deseja deletar: ");
                            int excluirLivroId = scanner.nextInt();
                            scanner.nextLine();

                            livroCRUD.deleteLivro(excluirLivroId);
                            System.out.println("Livro excluído com sucesso.");

                            break;

                        case 0:
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                            break;

                    }
                    break;

                case 5:
                    System.out.println("Comprando livros...");
                    List<Livro> carrinho = new ArrayList<>();
                    double totalCompra = 0.0;
                
                    while (true) {
                        livroCRUD.readLivro();  // Exibe os livros disponíveis para compra
                        System.out.print("Digite o ID do livro que deseja adicionar ao carrinho (ou 0 para finalizar): ");
                        int livroId = scanner.nextInt();
                        scanner.nextLine();  // Limpa o buffer de entrada
                
                        if (livroId == 0) break;  // Finaliza a compra
                
                        Livro livro = livroCRUD.getLivroPorId(livroId);  // Obtém o livro pelo ID
                        if (livro != null) {
                            carrinho.add(livro);  // Adiciona o livro ao carrinho
                            totalCompra += livro.getGenero().getPreco();  // Adiciona o preço do livro ao total (baseado no gênero)
                            System.out.printf("Livro '%s' adicionado ao carrinho. Preço: R$%.2f%n", livro.getNome_livro(), livro.getGenero().getPreco());
                        } else {
                            System.out.println("Livro não encontrado. Tente novamente.");
                        }
                    }
                
                    // Exibe o total da compra
                    System.out.printf("Total da compra: R$%.2f%n", totalCompra);
                
                    // Verifica se a compra ultrapassa R$100 e necessita de permissão
                    if (totalCompra > 100.0) {
                        System.out.print("Sua compra ultrapassou R$100,00. Você precisa de permissão para concluir a compra. Autorize (sim/não): ");
                        String respostaPermissao = scanner.nextLine();
                        if (respostaPermissao.equalsIgnoreCase("sim")) {
                            System.out.println("Compra autorizada!");
                        } else {
                            System.out.println("Compra não autorizada. A compra foi cancelada.");
                        }
                    } else {
                        System.out.println("Compra concluída com sucesso! Total: R$" + totalCompra);
                    }
                    break;

                case 0:
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
