package br.com.organizalivros.model;

import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;
    private String editora;
    private int ano;
    private int paginas;
    private LocalDate dataAquisicao;
    private Double preco;
    private String capa;


    private String statusLeitura; // "Não lido", "Lendo", etc.
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer nota;
    private String anotacoes;

    // Construtores
    public Livro() {}

    public Livro(String titulo, String autor, String genero, String isbn, String editora,
                 int ano, int paginas, LocalDate dataAquisicao, Double preco, String capa) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.editora = editora;
        this.ano = ano;
        this.paginas = paginas;
        this.dataAquisicao = dataAquisicao;
        this.preco = preco;
        this.capa = capa;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getStatusLeitura() { return statusLeitura; }
    public void setStatusLeitura(String statusLeitura) { this.statusLeitura = statusLeitura; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getAnotacoes() { return anotacoes; }
    public void setAnotacoes(String anotacoes) { this.anotacoes = anotacoes; }
}
