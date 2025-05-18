package br.com.organizalivros.view.swing;

import br.com.organizalivros.controller.LivroDAO;
import br.com.organizalivros.model.Livro;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class LivroForm extends JFrame {

    private final JTextField tituloField = new JTextField(20);
    private final JTextField autorField = new JTextField(20);
    private final JList<String> generoList = new JList<>();
    private final JTextField isbnField = new JTextField(20);
    private final JTextField editoraField = new JTextField(20);
    private final JTextField anoField = new JTextField(4);
    private final JTextField paginasField = new JTextField(5);
    private final JTextField dataAquisicaoField = new JTextField(10);
    private final JTextField precoField = new JTextField(10);
    private final JTextField capaField = new JTextField(30);
    private final JComboBox<String> statusCombo = new JComboBox<>(new String[] {
            "Não lido", "Lendo", "Lido", "Quero ler"
    });
    private final JTextField dataInicioField = new JTextField(10);
    private final JTextField dataFimField = new JTextField(10);
    private final JTextField notaField = new JTextField(2);
    private final JTextArea anotacoesArea = new JTextArea(3, 30);

    private final LivroDAO dao = new LivroDAO();

    public LivroForm() {
        setTitle("Cadastro de Livro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Editora:"));
        panel.add(editoraField);
        panel.add(new JLabel("Ano:"));
        panel.add(anoField);
        panel.add(new JLabel("Páginas:"));
        panel.add(paginasField);
        panel.add(new JLabel("Data de aquisição (YYYY-MM-DD):"));
        panel.add(dataAquisicaoField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);
        panel.add(new JLabel("Capa (URL ou caminho):"));
        panel.add(capaField);

        panel.add(new JLabel("Status de Leitura:"));
        panel.add(statusCombo);
        panel.add(new JLabel("Data Início Leitura:"));
        panel.add(dataInicioField);
        panel.add(new JLabel("Data Fim Leitura:"));
        panel.add(dataFimField);
        panel.add(new JLabel("Nota (1 a 5):"));
        panel.add(notaField);
        panel.add(new JLabel("Anotações:"));
        panel.add(new JScrollPane(anotacoesArea));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarLivro());

        add(panel, BorderLayout.CENTER);
        add(salvarButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void salvarLivro() {
        try {
            Livro livro = new Livro();
            livro.setTitulo(tituloField.getText());
            livro.setAutor(autorField.getText());
            livro.setGenero(generoField.getText());
            livro.setIsbn(isbnField.getText());
            livro.setEditora(editoraField.getText());
            livro.setAno(Integer.parseInt(anoField.getText()));
            livro.setPaginas(Integer.parseInt(paginasField.getText()));
            livro.setDataAquisicao(LocalDate.parse(dataAquisicaoField.getText()));
            livro.setPreco(precoField.getText().isEmpty() ? null : Double.parseDouble(precoField.getText()));
            livro.setCapa(capaField.getText());
            livro.setStatusLeitura((String) statusCombo.getSelectedItem());
            livro.setDataInicio(dataInicioField.getText().isEmpty() ? null : LocalDate.parse(dataInicioField.getText()));
            livro.setDataFim(dataFimField.getText().isEmpty() ? null : LocalDate.parse(dataFimField.getText()));
            livro.setNota(notaField.getText().isEmpty() ? null : Integer.parseInt(notaField.getText()));
            livro.setAnotacoes(anotacoesArea.getText());

            dao.adicionar(livro);
            JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");
            limparCampos();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar livro: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        tituloField.setText("");
        autorField.setText("");
        generoField.setText("");
        isbnField.setText("");
        editoraField.setText("");
        anoField.setText("");
        paginasField.setText("");
        dataAquisicaoField.setText("");
        precoField.setText("");
        capaField.setText("");
        statusCombo.setSelectedIndex(0);
        dataInicioField.setText("");
        dataFimField.setText("");
        notaField.setText("");
        anotacoesArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LivroForm::new);
    }
}
