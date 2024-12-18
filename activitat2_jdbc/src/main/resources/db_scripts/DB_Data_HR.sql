-- Inserts para la tabla Categoria
INSERT INTO Categoria (nom) VALUES ('Ficció');
INSERT INTO Categoria (nom) VALUES ('Ciència');
INSERT INTO Categoria (nom) VALUES ('Història');
INSERT INTO Categoria (nom) VALUES ('Poesia');
INSERT INTO Categoria (nom) VALUES ('Filosofia');
INSERT INTO Categoria (nom) VALUES ('Biografia');
INSERT INTO Categoria (nom) VALUES ('Misteri');
INSERT INTO Categoria (nom) VALUES ('Fantasia');
INSERT INTO Categoria (nom) VALUES ('Terror');
INSERT INTO Categoria (nom) VALUES ('Aventura');
INSERT INTO Categoria (nom) VALUES ('Juvenil');
INSERT INTO Categoria (nom) VALUES ('Manga');
INSERT INTO Categoria (nom) VALUES ('Romàntica');
INSERT INTO Categoria (nom) VALUES ('Autoajuda');
INSERT INTO Categoria (nom) VALUES ('Educació');
INSERT INTO Categoria (nom) VALUES ('Tecnologia');
INSERT INTO Categoria (nom) VALUES ('Comedia');

-- Inserts para la tabla Zona
INSERT INTO Zona (ubicacio, capacitat, dni_bibliotecari) VALUES ('Primera planta', 50, '12345678A');
INSERT INTO Zona (ubicacio, capacitat, dni_bibliotecari) VALUES ('Segona planta', 30, '23456789B');

-- Inserts para la tabla Llibres
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788437604947, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1605, 1, 1, 3);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780140449266, 'Guerra i Pau', 'Lleó Tolstoi', 1869, 1, 1, 4);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780345538376, 'Joc de Trons', 'George R. R. Martin', 1996, 1, 8, 5);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780618260300, 'El Hobbit', 'J.R.R. Tolkien', 1937, 1, 8, 6);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788495359685, 'La Divina Comèdia', 'Dante Alighieri', 1320, 1, 4, 7);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788423337922, 'L''Home Invisible', 'H.G. Wells', 1897, 1, 2, 8);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780316769488, 'El Vigilant en el Camp de Sègol', 'J.D. Salinger', 1951, 1, 11, 9);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788497889401, '1984', 'George Orwell', 1949, 1, 1, 10);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788491052009, 'El Principito', 'Antoine de Saint-Exupéry', 1943, 1, 11, 11);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780156012195, 'L''Alquimista', 'Paulo Coelho', 1988, 1, 12, 12);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780141439587, 'Orgull i Prejudici', 'Jane Austen', 1813, 1, 13, 13);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780679783275, 'Frankenstein', 'Mary Shelley', 1818, 1, 9, 14);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780345803481, 'Cinquanta Ombres de Grey', 'E.L. James', 2011, 1, 13, 15);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788498387082, 'Crim i Càstig', 'Fiódor Dostoievski', 1866, 1, 1, 16);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788401023370, 'Dràcula', 'Bram Stoker', 1897, 1, 9, 17);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788423356763, 'L''Educació Sentimental', 'Gustave Flaubert', 1869, 1, 14, 18);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788408099408, 'Aprendre a Viure', 'Luc Ferry', 2006, 1, 5, 19);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788432250514, 'Els Pilars de la Terra', 'Ken Follett', 1989, 1, 3, 20);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780525562016, 'El Testimoni', 'John Grisham', 1999, 1, 7, 21);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9781400079988, 'Angels i Dimonis', 'Dan Brown', 2000, 1, 7, 22);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788499185090, 'La Sombra del Viento', 'Carlos Ruiz Zafón', 2001, 1, 1, 23);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780140449136, 'El Llibre del Tao', 'Lao Tse', -600, 1, 5, 24);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788490324023, 'El Diari d''Anna Frank', 'Anna Frank', 1947, 1, 6, 25);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788408036762, 'El Silmarillion', 'J.R.R. Tolkien', 1977, 1, 8, 26);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788423337144, 'El Nom de la Rosa', 'Umberto Eco', 1980, 1, 7, 27);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788466351876, 'El Petit Vampir', 'Angela Sommer-Bodenburg', 1979, 1, 9, 28);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780553213119, 'Les Aventures de Tom Sawyer', 'Mark Twain', 1876, 1, 10, 29);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780316015844, 'Crepuscle', 'Stephenie Meyer', 2005, 1, 9, 30);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9788498722325, 'La Casa de l''Esperit', 'Isabel Allende', 1982, 1, 1, 31);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780131103627, 'El Camí de l''Autodisciplina', 'Brian Tracy', 2003, 1, 12, 32);
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) 
VALUES (9780590353403, 'Harry Potter i la Pedra Filosofal', 'J.K. Rowling', 1997, 1, 8, 33);


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
