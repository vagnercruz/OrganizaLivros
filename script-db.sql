CREATE TABLE livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(255),
    isbn VARCHAR(20),
    editora VARCHAR(100),
    ano INT,
    paginas INT,
    data_aquisicao DATE,
    preco DECIMAL(10,2),
    capa TEXT,
    status_leitura VARCHAR(20),
	data_inicio DATE,
	data_fim DATE,
	nota INTEGER,
	anotacoes TEXT
);


CREATE TABLE generos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE livro_genero (
    livro_id INT REFERENCES livros(id) ON DELETE CASCADE,
    genero_id INT REFERENCES generos(id) ON DELETE CASCADE,
    PRIMARY KEY (livro_id, genero_id)
);

