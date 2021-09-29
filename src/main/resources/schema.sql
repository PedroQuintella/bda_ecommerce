CREATE TABLE produto(
                        id                     INT NOT NULL,
                        nome                   VARCHAR(100) NOT NULL,
                        descricao              VARCHAR(150) NOT NULL,
                        fotoUrl                VARCHAR(500),
                        dataCadastro           DATE NOT NULL,
                        dataUltimaAtualizacao  TIMESTAMP NOT NULL,
                        preco                  FLOAT NOT NULL
);