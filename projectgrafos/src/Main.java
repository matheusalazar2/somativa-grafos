import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GrafoLivros grafo = new GrafoLivros();

        // Preenche o grafo com os livros e as recomendações
        CatalogoLivros.preencherGrafo(grafo);

        // Mostra o grafo completo
        grafo.mostrarGrafo();

        // Escolhe um livro qualquer para testar recomendação
        Livro exemplo = new Livro("A Guerra dos Tronos", "George R.R. Martin", "Fantasia");

        System.out.println("\n>>> Recomendações para quem leu: " + exemplo);
        Set<Livro> recomendacoes = grafo.recomendarLivros(exemplo, 2);

        for (Livro livro : recomendacoes) {
            System.out.println("- " + livro);
        }
    }
}