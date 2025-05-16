package br.com.organizalivros.controller;


import br.com.organizalivros.db.Database;
import br.com.organizalivros.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void adicionar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, isbn, editora, ano, paginas, data_aquisicao, preco, capa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            stmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo=?, autor=?, genero=?, isbn=?, editora=?, ano=?, paginas=?, data_aquisicao=?, preco=?, capa=? WHERE id=?";

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
            stmt.setInt(11, livro.getId());

            stmt.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
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
                livro.setDataAquisicao(rs.getDate("data_aquisicao").toLocalDate());
                livro.setPreco(rs.getDouble("preco"));
                livro.setCapa(rs.getString("capa"));

                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}