import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GrafoLivros grafo = new GrafoLivros();

        Livro l1 = new Livro("O Hobbit", "J.R.R. Tolkien");
        Livro l2 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien");
        Livro l3 = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling");
        Livro l4 = new Livro("As Crônicas de Nárnia", "C.S. Lewis");
        Livro l5 = new Livro("A Guerra dos Tronos", "George R.R. Martin");
        Livro l6 = new Livro("Duna", "Frank Herbert");
        Livro l7 = new Livro("1984", "George Orwell");
        Livro l8 = new Livro("Admirável Mundo Novo", "Aldous Huxley");
        Livro l9 = new Livro("Fahrenheit 451", "Ray Bradbury");
        Livro l10 = new Livro("A Máquina do Tempo", "H.G. Wells");

        // Adicionar todos os livros ao grafo
        Livro[] livros = {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10};
        for (Livro livro : livros) {
            grafo.adicionarLivro(livro);
        }

        // Adicionar recomendações (2 por livro no mínimo)
        grafo.adicionarRecomendacao(l1, l2);
        grafo.adicionarRecomendacao(l1, l3);

        grafo.adicionarRecomendacao(l2, l1);
        grafo.adicionarRecomendacao(l2, l5);

        grafo.adicionarRecomendacao(l3, l4);
        grafo.adicionarRecomendacao(l3, l7);

        grafo.adicionarRecomendacao(l4, l1);
        grafo.adicionarRecomendacao(l4, l6);

        grafo.adicionarRecomendacao(l5, l2);
        grafo.adicionarRecomendacao(l5, l6);

        grafo.adicionarRecomendacao(l6, l9);
        grafo.adicionarRecomendacao(l6, l10);

        grafo.adicionarRecomendacao(l7, l8);
        grafo.adicionarRecomendacao(l7, l9);

        grafo.adicionarRecomendacao(l8, l7);
        grafo.adicionarRecomendacao(l8, l10);

        grafo.adicionarRecomendacao(l9, l7);
        grafo.adicionarRecomendacao(l9, l8);

        grafo.adicionarRecomendacao(l10, l6);
        grafo.adicionarRecomendacao(l10, l1);

        // Mostrar o grafo
        grafo.mostrarGrafo();

        System.out.println(">>> Recomendações para quem leu: " + l3);
        Set<Livro> recomendados = grafo.recomendarLivros(l3, 2); // profundidade 2
        for (Livro livro : recomendados) {
            System.out.println("- " + livro);
        }

    }
}