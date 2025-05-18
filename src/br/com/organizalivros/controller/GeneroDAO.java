package br.com.organizalivros.controller;

import br.com.organizalivros.db.Database;
import br.com.organizalivros.model.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    public Genero buscarOuCriar(String nome) {
        String selectSql = "SELECT id FROM generos WHERE nome = ?";
        String insertSql = "INSERT INTO generos (nome) VALUES (?) RETURNING id";

        try (Connection conn = Database.connect()) {
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setString(1, nome);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Genero(rs.getInt("id"), nome);
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, nome);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Genero(rs.getInt("id"), nome);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Genero> listarTodos() {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT * FROM generos";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Genero genero = new Genero(rs.getInt("id"), rs.getString("nome"));
                generos.add(genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generos;
    }
}
