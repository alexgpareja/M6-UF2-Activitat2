-- Inserts para Categoria (13)
INSERT INTO Categoria (nom_categoria) VALUES
('Aventura'),
('Misterio'),
('Ciencia ficción'),
('Fantasía'),
('Romance'),
('Thriller'),
('Historia'),
('Biografía'),
('Ciencia'),
('Filosofía'),
('Arte'),
('Poesía'),
('Manga'),
('Comedia'),
('Autoayuda'),
('Literatura');

-- Inserts para Client (5)
INSERT INTO Client (dni, nom_client, telefon, email, data_registre) VALUES
('12345678A', 'Ana García', '666111222', 'ana@email.com', '2024-01-15'),
('23456789B', 'Carlos Pérez', '677222333', 'carlos@email.com', '2024-02-20'),
('34567890C', 'María López', '688333444', 'maria@email.com', '2024-03-10'),
('45678901D', 'Juan Rodríguez', '699444555', 'juan@email.com', '2024-04-05'),
('56789012E', 'Laura Martínez', '611555666', 'laura@email.com', '2024-05-12');

-- Inserts para Bibliotecari (3)
INSERT INTO Bibliotecari (dni_bibliotecari, nom_bibliotecari, telefon, email) VALUES
('11223344F', 'Pedro Sánchez', '622666777', 'pedro@biblioteca.com'),
('22334455G', 'Lucía Fernández', '633777888', 'lucia@biblioteca.com'),
('33445566H', 'Miguel Torres', '644888999', 'miguel@biblioteca.com');

-- Inserts per a la taula Llibres (33)
INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria) VALUES
(9788401352836, 'Cien años de soledad', 'Gabriel García Márquez', 1967, 1, 1),
(9788420471839, '1984', 'George Orwell', 1949, 1, 4),
(9788466345897, 'El código Da Vinci', 'Dan Brown', 2003, 1, 3),
(9788497593793, 'El principito', 'Antoine de Saint-Exupéry', 1943, 1, 1),
(9788408163381, 'El alquimista', 'Paulo Coelho', 1988, 1, 5),
(9788420674209, 'Crimen y castigo', 'Fyodor Dostoevsky', 1866, 1, 1),
(9788408093107, 'El nombre de la rosa', 'Umberto Eco', 1980, 1, 3),
(9788466664479, 'La sombra del viento', 'Carlos Ruiz Zafón', 2001, 1, 1),
(9788420471815, 'Orgullo y prejuicio', 'Jane Austen', 1813, 1, 6),
(9788466664417, 'El psicoanalista', 'John Katzenbach', 2002, 1, 7),
(9788420471900, 'Sapiens: De animales a dioses', 'Yuval Noah Harari', 2011, 1, 2),
(9788408175728, 'El poder del ahora', 'Eckhart Tolle', 1997, 1, 11),
(9788408127032, 'La chica del tren', 'Paula Hawkins', 2015, 1, 7),
(9788466664692, 'El médico', 'Noah Gordon', 1986, 1, 8),
(9788420471950, 'Breve historia del tiempo', 'Stephen Hawking', 1988, 1, 10),
(9788408163375, 'Los pilares de la Tierra', 'Ken Follett', 1989, 1, 8),
(9788420471823, 'Matar a un ruiseñor', 'Harper Lee', 1960, 1, 1),
(9788466345881, 'El señor de los anillos', 'J.R.R. Tolkien', 1954, 1, 5),
(9788408175711, 'Crónica de una muerte anunciada', 'Gabriel García Márquez', 1981, 1, 1),
(9788420674194, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1605, 1, 1),
(9788408163399, 'El perfume', 'Patrick Süskind', 1985, 1, 1),
(9788466664455, 'La naranja mecánica', 'Anthony Burgess', 1962, 1, 4),
(9788420471847, 'Fahrenheit 451', 'Ray Bradbury', 1953, 1, 4),
(9788408093115, 'El retrato de Dorian Gray', 'Oscar Wilde', 1890, 1, 1),
(9788466664424, 'Drácula', 'Bram Stoker', 1897, 1, 7),
(9788420471854, 'Moby Dick', 'Herman Melville', 1851, 1, 1),
(9788408175735, 'Las aventuras de Sherlock Holmes', 'Arthur Conan Doyle', 1892, 1, 3),
(9788466664486, 'El gran Gatsby', 'F. Scott Fitzgerald', 1925, 1, 1),
(9788420471861, 'Ulises', 'James Joyce', 1922, 1, 1),
(9788408163405, 'Lolita', 'Vladimir Nabokov', 1955, 1, 1),
(9788466664493, 'En busca del tiempo perdido', 'Marcel Proust', 1913, 1, 1),
(9788420471878, 'Guerra y paz', 'León Tolstói', 1869, 1, 8),
(9788408175742, 'Madame Bovary', 'Gustave Flaubert', 1856, 1, 1);
