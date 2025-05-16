package br.com.organizalivros.controller;

import br.com.organizalivros.db.Database;
import br.com.organizalivros.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void adicionar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, isbn, editora, ano, paginas, data_aquisicao, preco, capa, " +
                "status_leitura, data_inicio, data_fim, nota, anotacoes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setString(4, livro.getIsbn());
            stmt.setString(5, livro.getEditora());
            stmt.setInt(6, livro.getAno());
            stmt.setInt(7, livro.getPaginas());
            stmt.setObject(8, livro.getDataAquisicao());

            if (livro.getPreco() != null) {
                stmt.setDouble(9, livro.getPreco());
            } else {
                stmt.setNull(9, Types.DOUBLE);
            }

            stmt.setString(10, livro.getCapa());
            stmt.setString(11, livro.getStatusLeitura());
            stmt.setObject(12, livro.getDataInicio());
            stmt.setObject(13, livro.getDataFim());

            if (livro.getNota() != null) {
                stmt.setInt(14, livro.getNota());
            } else {
                stmt.setNull(14, Types.INTEGER);
            }

            stmt.setString(15, livro.getAnotacoes());

            stmt.executeUpdate();
            System.out.println("Livro salvo com sucesso no banco.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir livro no banco: " + e.getMessage());
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo=?, autor=?, genero=?, isbn=?, editora=?, ano=?, paginas=?, data_aquisicao=?, preco=?, capa=?, " +
                "status_leitura=?, data_inicio=?, data_fim=?, nota=?, anotacoes=? WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setString(4, livro.getIsbn());
            stmt.setString(5, livro.getEditora());
            stmt.setInt(6, livro.getAno());
            stmt.setInt(7, livro.getPaginas());
            stmt.setDate(8, Date.valueOf(livro.getDataAquisicao()));
            stmt.setObject(9, livro.getPreco());
            stmt.setString(10, livro.getCapa());
            stmt.setString(11, livro.getStatusLeitura());
            stmt.setObject(12, livro.getDataInicio());
            stmt.setObject(13, livro.getDataFim());

            if (livro.getNota() != null) {
                stmt.setInt(14, livro.getNota());
            } else {
                stmt.setNull(14, Types.INTEGER);
            }

            stmt.setString(15, livro.getAnotacoes());
            stmt.setInt(16, livro.getId());

            stmt.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM livros WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Livro excluído com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar livro: " + e.getMessage());
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setGenero(rs.getString("genero"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));
                livro.setPaginas(rs.getInt("paginas"));

                Date dataAq = rs.getDate("data_aquisicao");
                if (dataAq != null) livro.setDataAquisicao(dataAq.toLocalDate());

                double preco = rs.getDouble("preco");
                if (!rs.wasNull()) livro.setPreco(preco);

                livro.setCapa(rs.getString("capa"));
                livro.setStatusLeitura(rs.getString("status_leitura"));

                Date dataInicio = rs.getDate("data_inicio");
                if (dataInicio != null) livro.setDataInicio(dataInicio.toLocalDate());

                Date dataFim = rs.getDate("data_fim");
                if (dataFim != null) livro.setDataFim(dataFim.toLocalDate());

                int nota = rs.getInt("nota");
                if (!rs.wasNull()) livro.setNota(nota);

                livro.setAnotacoes(rs.getString("anotacoes"));

                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }
}
