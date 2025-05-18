package br.com.organizalivros.view.swing;

import br.com.organizalivros.controller.LivroDAO;
import br.com.organizalivros.model.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LivroListForm extends JFrame {

    private final JTable tabela;
    private final DefaultTableModel modelo;
    private final LivroDAO dao = new LivroDAO();

    public LivroListForm() {
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        // Modelo da Tabela
        modelo = new DefaultTableModel(new String[]{
                "ID", "Título", "Autor", "Gênero", "Data de Aquisição", "Nota"
        }, 0);
        tabela = new JTable(modelo);

        // Scroll
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Botões
        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(e -> carregarLivros());

        JButton novoButton = new JButton("Novo Livro");
        novoButton.addActionListener(e -> new LivroForm());

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(atualizarButton);
        botoesPanel.add(novoButton);

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        // Carregar dados
        carregarLivros();

        setVisible(true);
    }

    private void carregarLivros() {
        List<Livro> livros = dao.listar();
        modelo.setRowCount(0); // Limpa tabela
        for (Livro livro : livros) {
            modelo.addRow(new Object[]{
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getGenero(),
                    livro.getDataAquisicao(),
                    livro.getNota()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LivroListForm::new);
    }
}
