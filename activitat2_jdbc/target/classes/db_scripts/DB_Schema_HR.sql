CREATE DATABASE IF NOT EXISTS BIBLIOTECA;

USE BIBLIOTECA;

CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nom_categoria VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Client (
    dni VARCHAR(9) PRIMARY KEY,
    nom_client VARCHAR(50) NOT NULL,
    telefon VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    data_registre VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Bibliotecari (
    dni_bibliotecari VARCHAR(9) PRIMARY KEY,
    nom_bibliotecari VARCHAR(50) NOT NULL,
    telefon VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS Llibres (
    id_llibre INT AUTO_INCREMENT PRIMARY KEY,
    isbn BIGINT UNIQUE,
    titol VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    any_publicacio INT NOT NULL,
    disponibilitat TINYINT NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

