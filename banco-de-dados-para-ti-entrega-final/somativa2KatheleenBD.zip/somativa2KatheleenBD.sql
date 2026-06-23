DROP DATABASE IF EXISTS lojaVinhos;
CREATE DATABASE lojaVinhos;
USE lojaVinhos;

CREATE TABLE Regiao (
    codRegiao INT PRIMARY KEY AUTO_INCREMENT,
    nomeRegiao VARCHAR(100),
    descritivo VARCHAR(255)
);

CREATE TABLE Vinicola (
    codVinicola INT PRIMARY KEY AUTO_INCREMENT,
    nomeVinicola VARCHAR(100),
    descricao TEXT,
    telefone VARCHAR(20),
    email VARCHAR(100),
    codRegiao INT,
    FOREIGN KEY (codRegiao) REFERENCES Regiao(codRegiao)
);

CREATE TABLE Vinho (
    codVinho INT PRIMARY KEY AUTO_INCREMENT,
    nomeVinho VARCHAR(100),
    tipo VARCHAR(50),
    ano INT,
    descricao TEXT,
    codVinicola INT,
    FOREIGN KEY (codVinicola) REFERENCES Vinicola(codVinicola)
);

INSERT INTO Regiao (nomeRegiao, descritivo) VALUES
("Vale do Douro", "Famosa por vinhos tintos encorpados"),
("Mendoza", "Principal região vinícola da Argentina"),
("Bordeaux", "Tradicional região de vinhos franceses"),
("Toscana", "Reconhecida pelos vinhos Chianti"),
("Serra Gaúcha", "Maior produtora de vinhos do Brasil");

INSERT INTO Vinicola (nomeVinicola, descricao, telefone, email, codRegiao) VALUES
("Dom Cândido", "Produtora brasileira tradicional", "5432123456", "dom@vinhos.com", 5),
("Bodegas Trapiche", "Famosa vinícola argentina", "5411122233", "trapiche@mendoza.ar", 2),
("Château Margaux", "Uma das mais antigas da França", "3312345678", "info@chateaumargaux.fr", 3),
("Antinori", "Família vinícola com 600 anos de tradição", "3906112233", "antinori@toscana.it", 4),
("Quinta do Crasto", "Vinícola no coração do Douro", "351234567", "crasto@douro.pt", 1);

INSERT INTO Vinho (nomeVinho, tipo, ano, descricao, codVinicola) VALUES
("Reserva Especial", "Tinto", 2018, "Vinho encorpado, com notas de frutas vermelhas", 1),
("Malbec Premium", "Tinto", 2020, "Aveludado, com final persistente", 2),
("Margaux Grand Cru", "Tinto", 2015, "Aroma complexo e taninos elegantes", 3),
("Chianti Classico", "Tinto", 2019, "Frutado, ideal para massas", 4),
("Crasto Branco", "Branco", 2021, "Refrescante, com notas cítricas", 5);

SELECT 
    V.nomeVinho,
    V.ano,
    Vi.nomeVinicola,
    R.nomeRegiao
FROM 
    Vinho V
JOIN 
    Vinicola Vi ON V.codVinicola = Vi.codVinicola
JOIN 
    Regiao R ON Vi.codRegiao = R.codRegiao;

CREATE USER 'Somellier'@'localhost' IDENTIFIED BY 'vinhos123';

GRANT SELECT ON lojaVinhos.Vinho TO 'Somellier'@'localhost';
GRANT SELECT (codVinicola, nomeVinicola) ON lojaVinhos.Vinicola TO 'Somellier'@'localhost';

ALTER USER 'Somellier'@'localhost' WITH MAX_QUERIES_PER_HOUR 40;
