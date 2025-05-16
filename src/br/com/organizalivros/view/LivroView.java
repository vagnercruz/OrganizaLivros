package br.com.organizalivros.view;

import br.com.organizalivros.controller.LivroDAO;
import br.com.organizalivros.model.Livro;

import java.time.LocalDate;
import java.util.Scanner;

public class LivroView {
    private final LivroDAO dao = new LivroDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- MENU LIVROS ---");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Editar livro");
            System.out.println("3. Excluir livro");
            System.out.println("4. Listar livros");
            System.out.println("0. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir enter

            switch (opcao) {
                case 1 -> adicionar();
                case 2 -> editar();
                case 3 -> excluir();
                case 4 -> listar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void adicionar() {
        Livro livro = lerDadosDoLivro(null);
        dao.adicionar(livro);
    }

    private void editar() {
        listar();
        System.out.print("ID do livro para editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Livro livro = lerDadosDoLivro(id);
        dao.atualizar(livro);
    }

    private void excluir() {
        listar();
        System.out.print("ID do livro para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        dao.deletar(id);
    }

    private void listar() {
        System.out.println("\n--- Lista de Livros ---");
        for (Livro l : dao.listar()) {
            System.out.printf("ID: %d | Título: %s | Autor: %s\n", l.getId(), l.getTitulo(), l.getAutor());
        }
    }

    private Livro lerDadosDoLivro(Integer id) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        System.out.print("Páginas: ");
        int paginas = scanner.nextInt();
        scanner.nextLine(); // limpar
        System.out.print("Data de aquisição (YYYY-MM-DD): ");
        LocalDate dataAquisicao = LocalDate.parse(scanner.nextLine());
        System.out.print("Preço (opcional, enter para nulo): ");
        String precoStr = scanner.nextLine();
        Double preco = precoStr.isEmpty() ? null : Double.parseDouble(precoStr);
        System.out.print("Capa (URL ou caminho): ");
        String capa = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, genero, isbn, editora, ano, paginas, dataAquisicao, preco, capa);
        if (id != null) livro.setId(id);
        return livro;
    }
}