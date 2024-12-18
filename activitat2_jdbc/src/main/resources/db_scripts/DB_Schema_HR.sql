CREATE DATABASE IF NOT EXISTS BIBLIOTECA;

USE BIBLIOTECA;

-- Taula Categoria
CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nom_categoria VARCHAR(50) NOT NULL
);

-- Taula Bibliotecari
CREATE TABLE IF NOT EXISTS Bibliotecari (
    dni_bibliotecari VARCHAR(9) PRIMARY KEY,
    nom_bibliotecari VARCHAR(50) NOT NULL,
    telefon VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

-- Taula Zona
CREATE TABLE IF NOT EXISTS Zona (
    num_estanteria INT AUTO_INCREMENT PRIMARY KEY,
    ubicacio VARCHAR(50) NOT NULL,
    capacitat INT NOT NULL,
    dni_bibliotecari VARCHAR(9),
    FOREIGN KEY (dni_bibliotecari) REFERENCES Bibliotecari(dni_bibliotecari)
);

-- Taula Usuari
CREATE TABLE IF NOT EXISTS Usuari (
    dni_usuari VARCHAR(9) PRIMARY KEY,
    nom_usuari VARCHAR(50) NOT NULL,
    telefon VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    data_registre VARCHAR(40) NOT NULL,
    dni_bibliotecari VARCHAR(9),
    FOREIGN KEY (dni_bibliotecari) REFERENCES Bibliotecari(dni_bibliotecari)
);

-- Taula Llibre
CREATE TABLE IF NOT EXISTS Llibres (
    isbn INT PRIMARY KEY,
    titol VARCHAR(50) NOT NULL,
    autor VARCHAR(50) NOT NULL,
    any_publicacio INT NOT NULL,
    disponibilitat TINYINT NOT NULL,
    id_categoria INT,
    num_estanteria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
    FOREIGN KEY (num_estanteria) REFERENCES Zona(num_estanteria)
);

-- Taula Reserva
CREATE TABLE IF NOT EXISTS Reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data_reserva VARCHAR(50) NOT NULL,
    data_devolucio VARCHAR(50) NOT NULL,
    dni_usuari VARCHAR(9),
    dni_bibliotecari VARCHAR(9),
    isbn INT,
    FOREIGN KEY (dni_usuari) REFERENCES Usuari(dni_usuari),
    FOREIGN KEY (dni_bibliotecari) REFERENCES Bibliotecari(dni_bibliotecari),
    FOREIGN KEY (id_llibre) REFERENCES Llibres(isbn)
);
