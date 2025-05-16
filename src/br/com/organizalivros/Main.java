package br.com.organizalivros;


import br.com.organizalivros.db.Database;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo à Biblioteca Pessoal!");

        Connection conn = Database.connect();
        if (conn != null) {
            System.out.println("Conexão com PostgreSQL realizada com sucesso!");
        }
    }
}