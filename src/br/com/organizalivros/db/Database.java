package br.com.organizalivros.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USER = "vagnercruz";
    private static final String PASSWORD = "asdpoi04";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
            return null;
        }
    }
}