import java.util.Scanner;

/**
 * Classe que representa o menu da biblioteca, permitindo ao usuário e ao bibliotecário interagir com o sistema.
 */
public class LibraryMenu {
    private Scanner scanner; // Scanner para entrada de dados do usuário
    private Library library; // Instância da biblioteca que será manipulada

    /**
     * Construtor da classe LibraryMenu.
     * Inicializa o scanner e a instância da biblioteca.
     */
    public LibraryMenu() {
        scanner = new Scanner(System.in);
        library = new Library(); // Inicializa a biblioteca com livros e estruturas
    }

    /**
     * Método principal que inicia o menu principal, oferecendo as opções de acesso aos menus de bibliotecário e usuário.
     */
    public void start() {
        int option = -1;
        do {
            // Menu principal com opções de bibliotecário e usuário
            System.out.println("\n--- Welcome ---");
            System.out.println("1. Librarian Menu");
            System.out.println("2. User Menu");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (option) {
                    case 1 -> librarianMenu(); // Acessa o menu do bibliotecário
                    case 2 -> userMenu();      // Acessa o menu do usuário
                    case 0 -> System.out.println("Exiting program..."); // Sai do sistema
                    default -> System.out.println("Invalid option.");   // Opção inválida
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Limpa o buffer após erro
            }

        } while (option != 0); // Loop até o usuário escolher sair

        scanner.close(); // Fecha o scanner ao final
    }

    /**
     * Exibe o menu de opções para o bibliotecário, permitindo-lhe gerenciar livros e a lista de espera.
     */
    private void librarianMenu() {
        int option = -1;
        do {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. Add book");
            System.out.println("2. List books");
            System.out.println("3. Remove book");
            System.out.println("4. Add person to waitlist");
            System.out.println("5. Serve next in waitlist");
            System.out.println("6. Show waitlist");
            System.out.println("7. Return a book");
            System.out.println("9. Return to main menu");
            System.out.print("Choose an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (option) {
                    case 1 -> addBook();                         // Adiciona um novo livro
                    case 2 -> library.listBooks();               // Lista todos os livros
                    case 3 -> removeBook();                      // Remove um livro pelo título
                    case 4 -> addToWaitlist(null);               // Adiciona alguém à lista de espera
                    case 5 -> library.serveNextPersonInWaitlist(); // Atende próxima pessoa da fila
                    case 6 -> library.showWaitlist();            // Mostra a lista de espera
                    case 7 -> returnBook();                      // Marca um livro como devolvido
                    case 9 -> System.out.println("Returning to main menu..."); // Retorna ao menu principal
                    default -> System.out.println("Invalid option.");         // Opção inválida
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again."); // Captura erro de entrada inválida
                scanner.nextLine(); // Limpa buffer
            }

        } while (option != 9); // Retorna ao menu principal ao escolher 9
    }

    /**
     * Exibe o menu de opções para o usuário, permitindo-lhe visualizar livros, solicitar livros, retornar livros e ver recomendações.
     */
    private void userMenu() {
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine(); // Recebe o nome do usuário

        int option = -1;
        do {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View book");
            System.out.println("2. Request book (enter waitlist)");
            System.out.println("3. Show book recommendations");
            System.out.println("4. Return a book");
            System.out.println("5. Show shortest recommendation paths");
            System.out.println("9. Return to main menu");
            System.out.print("Choose an option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (option) {
                    case 1 -> viewBook(userName);       // Visualiza um livro e salva no histórico
                    case 2 -> addToWaitlist(userName);  // Solicita um livro e entra na fila de espera
                    case 3 -> showRecommendations(userName); // Mostra recomendações de livros
                    case 4 -> returnBook();             // Devolve um livro
                    case 5 -> showShortestPaths();      // Busca pelo algoritmo de Dijkstra
                    case 9 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine(); // Limpa o buffer
            }

        } while (option != 9);
    }

    /**
     * Adiciona um livro ao acervo da biblioteca.
     */
    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Year of publication: ");
        int year = scanner.nextInt();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        scanner.nextLine(); // Limpa buffer
        library.addBook(new Book(title, author, year, genre));
    }

    /**
     * Remove um livro do acervo da biblioteca com base no título.
     */
    private void removeBook() {
        System.out.print("Enter the title of the book to remove: ");
        String title = scanner.nextLine();
        library.removeBookByTitle(title);
        System.out.println("Title removed from the library's catalog.");
    }

    /**
     * Adiciona uma pessoa à lista de espera de um livro.
     * @param userName Nome do usuário que está solicitando o livro.
     */
    private void addToWaitlist(String userName) {
        if (userName == null) {
            System.out.print("Enter the person's name: ");
            userName = scanner.nextLine();
        }

        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        library.addPersonToWaitlist(userName, title);
    }

    /**
     * Visualiza um livro específico e registra no histórico do usuário.
     * @param userName Nome do usuário que está visualizando o livro.
     */
    private void viewBook(String userName) {
        System.out.print("Enter the title of the book to view: ");
        String title = scanner.nextLine();
        library.viewBook(userName, title);
    }

    /**
     * Exibe recomendações de livros para o usuário com base no histórico ou título.
     * @param userName Nome do usuário que está visualizando as recomendações.
     */
    private void showRecommendations(String userName) {
        System.out.println("1. Show recommendations by history");
        System.out.println("2. Show recommendations by title");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        if (option == 1) {
            library.showRecommendationsByHistory(userName); // Recomendações com base no histórico
        } else if (option == 2) {
            System.out.print("Enter the title of the book: ");
            String title = scanner.nextLine();
            library.showRecommendationsByTitle(title); // Recomendações com base em título
        } else {
            System.out.println("Invalid option.");
        }
    }

    /**
     * Marca um livro como devolvido.
     */
    private void returnBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        library.returnBook(title);
    }


    private void showShortestPaths() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        library.showShortestPathsFrom(title);
    }
}
