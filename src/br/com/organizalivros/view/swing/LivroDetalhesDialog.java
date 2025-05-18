package br.com.organizalivros.view.swing;

import br.com.organizalivros.model.Livro;

import javax.swing.*;
import java.awt.*;

public class LivroDetalhesDialog extends JDialog {

    public LivroDetalhesDialog(JFrame parent, Livro livro) {
        super(parent, "Detalhes do Livro", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JTextArea detalhesArea = new JTextArea(15, 40);
        detalhesArea.setEditable(false);
        detalhesArea.setText(gerarTextoDetalhado(livro));

        add(new JScrollPane(detalhesArea), BorderLayout.CENTER);

        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(fechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String gerarTextoDetalhado(Livro livro) {
        return String.format(
                "Título: %s\nAutor: %s\nGênero: %s\nISBN: %s\nEditora: %s\nAno: %d\nPáginas: %d\n" +
                        "Data de Aquisição: %s\nPreço: R$ %.2f\nCapa: %s\n\nStatus de Leitura: %s\n" +
                        "Início: %s\nFim: %s\nNota: %s\n\nAnotações:\n%s",
                livro.getTitulo(),
                livro.getAutor(),
                livro.getGenero(),
                livro.getIsbn(),
                livro.getEditora(),
                livro.getAno(),
                livro.getPaginas(),
                livro.getDataAquisicao(),
                livro.getPreco() == null ? 0.0 : livro.getPreco(),
                livro.getCapa(),
                livro.getStatusLeitura(),
                livro.getDataInicio(),
                livro.getDataFim(),
                livro.getNota() == null ? "—" : livro.getNota(),
                livro.getAnotacoes()
        );
    }
}
