
-- Creación de la DB
CREATE DATABASE IF NOT EXISTS HR;

-- Utilizar esta
USE HR;

-- Creación de la tabla Categoria
CREATE TABLE IF NOT EXISTS Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);

-- Creación de la tabla Zona
CREATE TABLE IF NOT EXISTS Zona (
    num_estanteria INT AUTO_INCREMENT PRIMARY KEY,
    ubicacio VARCHAR(50) NOT NULL,
    capacitat INT NOT NULL,
    dni_bibliotecari VARCHAR(9),
    FOREIGN KEY (dni_bibliotecari) REFERENCES Bibliotecari(dni_bibliotecari)
);

-- Creación de la tabla Llibres
CREATE TABLE IF NOT EXISTS Llibres (
    id_llibre INT AUTO_INCREMENT PRIMARY KEY,
    isbn INT UNIQUE,
    titol VARCHAR(50) NOT NULL,
    autor VARCHAR(50) NOT NULL,
    any_publicacio INT NOT NULL,
    disponibilitat TINYINT NOT NULL,
    id_categoria INT,
    num_estanteria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
    FOREIGN KEY (num_estanteria) REFERENCES Zona(num_estanteria)
);

-- Creación de la tabla Client
CREATE TABLE IF NOT EXISTS Client (
    dni VARCHAR(9) PRIMARY KEY,
    nom_client VARCHAR(50) NOT NULL,
    telefon VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    data_registre VARCHAR NOT NULL
);

-- Creación de la tabla Bibliotecari
CREATE TABLE IF NOT EXISTS Bibliotecari (
    dni_bibliotecari VARCHAR(9) PRIMARY KEY,
    nom_client VARCHAR(50) NOT NULL,
    telefon VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

-- Creación de la tabla Reserva
CREATE TABLE IF NOT EXISTS Reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data_reserva VARCHAR(50) NOT NULL,
    data_devolucio VARCHAR(50) NOT NULL,
    dni_client VARCHAR(9),
    dni_bibliotecari VARCHAR(9),
    id_llibre INT,
    FOREIGN KEY (dni_client) REFERENCES Client(dni),
    FOREIGN KEY (dni_bibliotecari) REFERENCES Bibliotecari(dni_bibliotecari),
    FOREIGN KEY (id_llibre) REFERENCES Llibres(id_llibre)
);
