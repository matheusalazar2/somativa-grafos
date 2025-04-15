import java.util.*;

public class CatalogoLivros {
    public static void preencherGrafo(GrafoLivros grafo) {
        List<Livro> livros = Arrays.asList(
                new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia"),
                new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia"),
                new Livro("Harry Potter", "J.K. Rowling", "Fantasia"),
                new Livro("As Crônicas de Nárnia", "C.S. Lewis", "Fantasia"),
                new Livro("A Guerra dos Tronos", "George R.R. Martin", "Fantasia"),
                new Livro("Eragon", "Christopher Paolini", "Fantasia"),

                new Livro("1984", "George Orwell", "Distopia"),
                new Livro("Admirável Mundo Novo", "Aldous Huxley", "Distopia"),
                new Livro("Fahrenheit 451", "Ray Bradbury", "Distopia"),
                new Livro("A Máquina do Tempo", "H.G. Wells", "Distopia"),
                new Livro("O Conto da Aia", "Margaret Atwood", "Distopia"),
                new Livro("Laranja Mecânica", "Anthony Burgess", "Distopia"),

                new Livro("Duna", "Frank Herbert", "Ficção Científica"),
                new Livro("Neuromancer", "William Gibson", "Ficção Científica"),
                new Livro("Fundação", "Isaac Asimov", "Ficção Científica"),
                new Livro("Eu, Robô", "Isaac Asimov", "Ficção Científica"),
                new Livro("Solaris", "Stanisław Lem", "Ficção Científica"),
                new Livro("Encontro com Rama", "Arthur C. Clarke", "Ficção Científica"),

                new Livro("Orgulho e Preconceito", "Jane Austen", "Romance"),
                new Livro("Dom Casmurro", "Machado de Assis", "Romance"),
                new Livro("O Morro dos Ventos Uivantes", "Emily Brontë", "Romance"),
                new Livro("Jane Eyre", "Charlotte Brontë", "Romance"),
                new Livro("O Amor nos Tempos do Cólera", "Gabriel García Márquez", "Romance"),
                new Livro("A Culpa é das Estrelas", "John Green", "Romance"),

                new Livro("Assassinato no Expresso do Oriente", "Agatha Christie", "Mistério"),
                new Livro("O Cão dos Baskerville", "Arthur Conan Doyle", "Mistério"),
                new Livro("Garota Exemplar", "Gillian Flynn", "Mistério"),
                new Livro("O Código Da Vinci", "Dan Brown", "Mistério"),
                new Livro("Os Homens que Não Amavam as Mulheres", "Stieg Larsson", "Mistério"),
                new Livro("O Silêncio dos Inocentes", "Thomas Harris", "Mistério")
        );

        // Adiciona todos os livros ao grafo
        for (Livro livro : livros) {
            grafo.adicionarLivro(livro);
        }

        // Adiciona recomendações de 5 livros do mesmo gênero
        for (Livro livro : livros) {
            List<Livro> doMesmoGenero = new ArrayList<>(livros.stream()
                    .filter(l -> !l.equals(livro) && l.getGenero().equals(livro.getGenero()))
                    .toList());

            Collections.shuffle(doMesmoGenero);
            int max = Math.min(5, doMesmoGenero.size());
            for (int i = 0; i < max; i++) {
                grafo.adicionarRecomendacao(livro, doMesmoGenero.get(i));
            }
        }
    }
}
