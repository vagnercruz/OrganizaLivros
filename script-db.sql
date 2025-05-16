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
    capa TEXT
);

