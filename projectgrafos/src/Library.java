import java.util.*;

/**
 * Classe que representa a Biblioteca. Gerencia livros, lista de espera, histórico de usuários e recomendações.
 */
public class Library {

    /** Lista encadeada para armazenar os livros disponíveis na biblioteca */
    private LinkedList<Book> bookLinkedList;

    /** Fila para gerenciar a lista de espera dos usuários que solicitaram livros indisponíveis */
    private Queue<WaitlistEntry> waitlist;

    /** Mapa para armazenar o histórico de navegação de cada usuário, utilizando pilha (Stack) */
    private Map<String, Stack<Book>> userHistory;

    /** Mapa de recomendações baseado no gênero dos livros */
    private Map<Book, Set<Book>> recommendations;

    /**
     * Construtor. Inicializa a biblioteca com livros padrão.
     */
    public Library() {
        bookLinkedList = new LinkedList<>();
        waitlist = new LinkedList<>();
        userHistory = new HashMap<>();
        recommendations = new HashMap<>();
        seedBooks();
    }

    /**
     * Adiciona um livro à biblioteca ou aumenta sua quantidade se ele já existir.
     * @param book O livro a ser adicionado.
     */
    public void addBook(Book book) {
        for (Book b: bookLinkedList) {
            if (b.equals(book)) {
                b.setAvailableBooks(b.getAvailableBooks() + 1);
                addGenreBasedRecommendations(book);
                return;
            }
        }
        bookLinkedList.add(book);
        addGenreBasedRecommendations(book);
    }

    /** Lista todos os livros disponíveis na biblioteca. */
    public void listBooks() {
        if (bookLinkedList.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : bookLinkedList) {
                System.out.println(book);
            }
        }
    }

    /**
     * Remove um livro da biblioteca com base no título.
     * @param title Título do livro a ser removido.
     */
    public void removeBookByTitle(String title) {
        bookLinkedList.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Realiza a devolução de um livro.
     * @param title Título do livro a ser devolvido.
     */
    public void returnBook(String title) {
        boolean found = false;
        for (Book book : bookLinkedList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailableBooks(book.getAvailableBooks() + 1);
                System.out.println("Thank you for returning \"" + book.getTitle() + "\".");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found in the library's catalog.");
        }
    }

    /**
     * Adiciona uma pessoa à lista de espera para um livro.
     * @param name Nome da pessoa.
     * @param title Título do livro desejado.
     */
    public void addPersonToWaitlist(String name, String title) {
        waitlist.add(new WaitlistEntry(name, title));
        System.out.println(name + " added to waitlist for: " + title);
    }

    /** Atende a próxima pessoa na lista de espera, se possível. */
    public void serveNextPersonInWaitlist() {
        if (waitlist.isEmpty()) {
            System.out.println("The waitlist is empty.");
        } else {
            WaitlistEntry next = waitlist.peek();
            for (Book book : bookLinkedList) {
                if (book.getTitle().equalsIgnoreCase(next.getBookTitle())) {
                    if (book.getAvailableBooks() > 0) {
                        waitlist.poll();
                        book.setAvailableBooks(book.getAvailableBooks() - 1);
                        System.out.println(next.getUserName() + " has been served for book: " + book.getTitle());
                    } else {
                        System.out.println("Book \"" + book.getTitle() + "\" is currently unavailable.");
                        System.out.println(next.getUserName() + " remains in the waitlist.");
                    }
                    return;
                }
            }
            System.out.println("Requested book \"" + next.getBookTitle() + "\" not found in the catalog.");
            waitlist.poll();
        }
    }

    /** Exibe a lista de espera atual. */
    public void showWaitlist() {
        if (waitlist.isEmpty()) {
            System.out.println("The waitlist is empty.");
        } else {
            System.out.println("Waitlist:");
            for (WaitlistEntry entry : waitlist) {
                System.out.println("- " + entry);
            }
        }
    }

    /**
     * Permite a visualização de um livro e registra essa ação no histórico do usuário.
     * @param userName Nome do usuário.
     * @param title Título do livro.
     */
    public void viewBook(String userName, String title) {
        for (Book book : bookLinkedList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Viewing book:");
                System.out.println(book);
                userHistory.computeIfAbsent(userName, k -> new Stack<>()).push(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    /**
     * Exibe o histórico de navegação de um usuário.
     * @param userName Nome do usuário.
     */
    public void showHistory(String userName) {
        Stack<Book> history = userHistory.get(userName);
        if (history == null || history.isEmpty()) {
            System.out.println("No recent activity.");
        } else {
            System.out.println("Navigation history for "+ userName + ":");
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.get(i));
            }
        }
    }

    /**
     * Adiciona uma recomendação de livro com base em outro.
     * @param book Livro de referência.
     * @param recommended Livro recomendado.
     */
    public void addRecommendation(Book book, Book recommended) {
        recommendations.computeIfAbsent(book, k -> new HashSet<>()).add(recommended);
    }

    /**
     * Adiciona recomendações automáticas com base no gênero.
     * @param book Livro recém-adicionado.
     */
    private void addGenreBasedRecommendations(Book book) {
        for (Book b : bookLinkedList) {
            if (!b.equals(book) && b.getGenre().equalsIgnoreCase(book.getGenre())) {
                addRecommendation(book, b);
            }
        }
    }

    /**
     * Mostra recomendações com base no título do livro.
     * @param title Título do livro.
     */
    public void showRecommendationsByTitle(String title) {
        Book found = null;
        for (Book book : bookLinkedList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                found = book;
                break;
            }
        }
        if (found != null && recommendations.containsKey(found)) {
            System.out.println("Recommended books for: " + found.getTitle());
            for (Book rec : recommendations.get(found)) {
                System.out.println("- " + rec);
            }
        } else {
            System.out.println("No recommendations found for this book.");
        }
    }

    /**
     * Mostra recomendações com base no último livro visualizado pelo usuário.
     * @param userName Nome do usuário.
     */
    public void showRecommendationsByHistory(String userName) {
        Stack<Book> history = userHistory.get(userName);
        if (history == null || history.isEmpty()) {
            System.out.println("No recent activity.");
        } else {
            showRecommendationsByTitle(history.getLast().getTitle());
        }
    }

    /** Adiciona livros iniciais ao sistema para testes e demonstração. */
    private void seedBooks() {
        Book[] allBooks = {
                new Book("1984", "George Orwell", 1949, "Dystopian"),
                new Book("Brave New World", "Aldous Huxley", 1932, "Science Fiction"),
                new Book("Fahrenheit 451", "Ray Bradbury", 1953, "Dystopian"),
                new Book("Animal Farm", "George Orwell", 1945, "Political Satire"),
                new Book("The Giver", "Lois Lowry", 1993, "Dystopian"),
                new Book("The Hunger Games", "Suzanne Collins", 2008, "Dystopian"),
                new Book("Divergent", "Veronica Roth", 2011, "Science Fiction"),
                new Book("The Maze Runner", "James Dashner", 2009, "Science Fiction"),
                new Book("The Handmaid's Tale", "Margaret Atwood", 1985, "Speculative Fiction"),
                new Book("Neuromancer", "William Gibson", 1984, "Cyberpunk"),
                new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy"),
                new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, "Fantasy"),
                new Book("The Name of the Wind", "Patrick Rothfuss", 2007, "Fantasy"),
                new Book("A Game of Thrones", "George R.R. Martin", 1996, "Fantasy"),
                new Book("Mistborn: The Final Empire", "Brandon Sanderson", 2006, "Fantasy"),
                new Book("Pride and Prejudice", "Jane Austen", 1813, "Romance"),
                new Book("The Notebook", "Nicholas Sparks", 1996, "Romance"),
                new Book("Me Before You", "Jojo Moyes", 2012, "Romance"),
                new Book("Outlander", "Diana Gabaldon", 1991, "Romance"),
                new Book("The Fault in Our Stars", "John Green", 2012, "Romance")
        };

        bookLinkedList.addAll(Arrays.asList(allBooks));
        Arrays.stream(allBooks).forEach(this::addGenreBasedRecommendations);
    }
}
