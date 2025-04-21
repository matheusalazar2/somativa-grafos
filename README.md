# somativa-grafos

O sistema foi projetado com o objetivo de simular a rotina de uma biblioteca real, por isso as funcionalidades foram divididas entre dois perfis principais: o bibliotecário, responsável pela gestão do acervo e atendimento de solicitações, e o usuário, que pode visualizar livros, entrar em listas de espera, consultar recomendações e devolver obras.

A classe principal, Library, centraliza toda a lógica de funcionamento da biblioteca. Os livros são armazenados em uma LinkedList, estrutura que permite inserções e remoções eficientes, ideal para manipular um acervo em constante atualização. Quando um usuário acessa um livro, essa ação é registrada em seu histórico pessoal, armazenado por meio de um Map<String, Stack<Book>>. A pilha (Stack) garante que o histórico mantenha a ordem das visualizações, com o livro mais recente sempre no topo, facilitando consultas por recomendações baseadas na navegação.

Já as solicitações por livros indisponíveis são controladas por uma fila (Queue) do tipo LinkedList, representando uma lista de espera. Essa estrutura permite que os usuários sejam atendidos em ordem de chegada, simulando o comportamento justo e organizado de uma fila real.

O sistema também oferece um mecanismo de recomendações com base no gênero dos livros, utilizando um Map<Book, Set<Book>>, que associa cada livro a um conjunto de outros com temática semelhante.

Por fim, a classe LibraryMenu organiza a interação com o sistema por meio de menus para bibliotecários e usuários. Ela utiliza tratamento de exceções (try...catch) para garantir uma navegação segura, mesmo em casos de entradas inválidas, contribuindo para uma experiência de uso robusta e fluida.
