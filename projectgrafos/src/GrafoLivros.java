import java.util.*;

public class GrafoLivros {

    private Map<Livro, Set<Livro>> grafo;

    public GrafoLivros() {
        grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashSet<>());
    }

    public void adicionarRecomendacao(Livro origem, Livro recomendado) {
        grafo.get(origem).add(recomendado);
    }

    public void mostrarGrafo() {
        for (Livro livro : grafo.keySet()) {
            System.out.println("Livro: " + livro);
            System.out.println("Recomendações: " + grafo.get(livro));
            System.out.println();
        }
    }

    public Set<Livro> recomendarLivros(Livro livroLido, int profundidade) {
        Set<Livro> recomendacoes = new HashSet<>();
        Set<Livro> visitados = new HashSet<>();
        Queue<Livro> fila = new LinkedList<>();

        fila.add(livroLido);
        visitados.add(livroLido);

        int nivel = 0;

        while (!fila.isEmpty() && nivel < profundidade) {
            int tamanhoNivel = fila.size();

            for (int i = 0; i < tamanhoNivel; i++) {
                Livro atual = fila.poll();
                Set<Livro> vizinhos = grafo.getOrDefault(atual, new HashSet<>());

                for (Livro vizinho : vizinhos) {
                    if (!visitados.contains(vizinho)) {
                        recomendacoes.add(vizinho);
                        fila.add(vizinho);
                        visitados.add(vizinho);
                    }
                }
            }

            nivel++;
        }

        return recomendacoes;
    }
}