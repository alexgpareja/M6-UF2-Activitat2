-- Inserts para la tabla Categoria
INSERT INTO Categoria (nom) VALUES ('Ficció');
INSERT INTO Categoria (nom) VALUES ('Ciència');
INSERT INTO Categoria (nom) VALUES ('Història');

-- Inserts para la tabla Zona
INSERT INTO Zona (ubicacio, capacitat, dni_bibliotecari) VALUES ('Primera planta', 50, '12345678A');
INSERT INTO Zona (ubicacio, capacitat, dni_bibliotecari) VALUES ('Segona planta', 30, '23456789B');

-- Inserts para la tabla Llibres
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9781234567897, 'El Senyor dels Anells', 'J.R.R. Tolkien', 1954, 1, 1, 1);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9789876543210, 'Una Breu Història del Temps', 'Stephen Hawking', 1988, 1, 2, 2);

-- Inserts para la tabla Client
INSERT INTO Client (dni, nom_client, telefon, email, data_registre) 
VALUES ('34567890C', 'Maria López', '600123456', 'maria@example.com', '2023-01-15');
INSERT INTO Client (dni, nom_client, telefon, email, data_registre) 
VALUES ('45678901D', 'Joan Martí', '700654321', 'joan@example.com', '2023-05-20');

-- Inserts para la tabla Bibliotecari
INSERT INTO Bibliotecari (dni_bibliotecari, nom_client, telefon, email) 
VALUES ('12345678A', 'Anna Pérez', '600999888', 'anna@example.com');
INSERT INTO Bibliotecari (dni_bibliotecari, nom_client, telefon, email) 
VALUES ('23456789B', 'Pau Garcia', '601888777', 'pau@example.com');

-- Inserts para la tabla Reserva
INSERT INTO Reserva (data_reserva, data_devolucio, dni_client, dni_bibliotecari, id_llibre) 
VALUES ('2024-12-01', '2024-12-15', '34567890C', '12345678A', 1);
INSERT INTO Reserva (data_reserva, data_devolucio, dni_client, dni_bibliotecari, id_llibre) 
VALUES ('2024-12-02', '2024-12-20', '45678901D', '23456789B', 2);
