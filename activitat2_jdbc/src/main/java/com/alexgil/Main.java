package com.alexgil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {

    // Com veurem, aquesta booleana controla si volem sortir de l'aplicació.
    static boolean sortirapp = false;
    static boolean DispOptions = true;

    public static void main(String[] args) throws Exception {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            // Usamos la clase GestioDBHR para conectarnos a la base de datos
            GestioDBHR gestio = new GestioDBHR();
            Connection connection = gestio.connect(); // Obtener la conexión a la base de datos

            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.\n");

                // Ejecutar los scripts de creación y carga de datos
                gestio.executeDatabaseScripts();

                // Crear instancias de CRUD
                CRUD crud = new CRUD();

                // Mostrar el menú de opciones
                while (!sortirapp) {
                    MenuOptions(br1, crud, connection);
                }

            } else {
                System.err.println("Error al conectar a la base de datos.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menú principal para la aplicación
    public static void MenuOptions(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, IOException, InterruptedException {

        Terminal terminal = TerminalBuilder.builder().system(true).build();

        String message = "==================\n";
        printScreen(terminal, message);

        message = "GESTIONAR LIBROS Y CATEGORÍAS";
        printScreen(terminal, message);

        message = "==================";
        printScreen(terminal, message);

        message = "OPCIONES\n";
        printScreen(terminal, message);

        message = "1. CONSULTAR TODAS LAS CATEGORÍAS";
        printScreen(terminal, message);

        message = "2. CONSULTAR TODOS LOS LIBROS";
        printScreen(terminal, message);

        message = "3. INSERTAR NUEVO LIBRO";
        printScreen(terminal, message);

        message = "4. ACTUALIZAR LIBRO";
        printScreen(terminal, message);

        message = "5. ELIMINAR LIBRO";
        printScreen(terminal, message);

        message = "9. SALIR";
        printScreen(terminal, message);

        message = "Elige una opción >> ";
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }

        int opcion = Integer.parseInt(br.readLine());

        switch (opcion) {
            case 1:
                crud.readAllCategories(connection); // Consultar todas las categorías
                break;
            case 2:
                crud.ReadAllLlibres(connection); // Consultar todos los libros
                break;
            case 3:
                InsertLibro(br, crud, connection); // Insertar un nuevo libro
                break;
            case 4:
                UpdateLibro(br, crud, connection); // Actualizar un libro
                break;
            case 5:
                DeleteLibro(br, crud, connection); // Eliminar un libro
                break;
            case 9:
                System.out.println("¡Hasta luego!");
                sortirapp = true;
                break;
            default:
                System.out.println("Opción no válida, por favor ingrese otra opción.");
        }
    }

    // Método para insertar un nuevo libro
    public static void InsertLibro(BufferedReader br, CRUD crud, Connection connection)
            throws IOException, SQLException {
        System.out.println("Ingrese los datos del nuevo libro:");

        // Recopilar datos del libro
        System.out.print("ISBN: ");
        int isbn = Integer.parseInt(br.readLine());

        System.out.print("Título: ");
        String titol = br.readLine();

        System.out.print("Autor: ");
        String autor = br.readLine();

        System.out.print("Año de publicación: ");
        int anyPublicacio = Integer.parseInt(br.readLine());

        System.out.print("Disponibilidad (true/false): ");
        boolean disponibilidad = Boolean.parseBoolean(br.readLine());

        System.out.print("ID de categoría: ");
        int idCategoria = Integer.parseInt(br.readLine());

        System.out.print("Número de estantería: ");
        int numEstanteria = Integer.parseInt(br.readLine());

        // Crear un nuevo objeto Llibre sin establecer el idLlibre (que es
        // autoincremental)
        Llibre nuevoLibro = new Llibre(0, isbn, titol, autor, anyPublicacio, disponibilidad, idCategoria,
                numEstanteria);
        crud.InsertLlibre(connection, nuevoLibro); // La base de datos asignará el idLlibre

        System.out.println("Libro insertado con éxito.");
    }

    // Método para actualizar un libro
    public static void UpdateLibro(BufferedReader br, CRUD crud, Connection connection)
            throws IOException, SQLException {
        System.out.print("Ingrese el ISBN del libro a actualizar: ");
        int isbn = Integer.parseInt(br.readLine());

        // Buscar el libro por ISBN (usando el método ReadLlibreByIsbn si lo tienes)
        Llibre libro = crud.ReadLlibreByIsbn(connection, isbn);

        if (libro != null) {
            System.out.println("Libro encontrado: " + libro);

            // Actualizar los valores
            System.out.print("Nuevo título: ");
            libro.setTitol(br.readLine());

            System.out.print("Nuevo autor: ");
            libro.setAutor(br.readLine());

            System.out.print("Nuevo año de publicación: ");
            libro.setAnyPublicacio(Integer.parseInt(br.readLine()));

            System.out.print("Nuevo estado de disponibilidad (true/false): ");
            libro.setDisponibilitat(Boolean.parseBoolean(br.readLine()));

            crud.UpdateLlibre(connection, libro); // Actualiza el libro en la base de datos

            System.out.println("Libro actualizado con éxito.");
        } else {
            System.out.println("No se encontró el libro con el ISBN: " + isbn);
        }
    }

    // Método para eliminar un libro
    public static void DeleteLibro(BufferedReader br, CRUD crud, Connection connection)
            throws IOException, SQLException {
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        int isbn = Integer.parseInt(br.readLine());

        crud.DeleteLlibre(connection, isbn); // Eliminar el libro

        System.out.println("Libro eliminado con éxito.");
    }

    // Método auxiliar para imprimir en pantalla
    private static void printScreen(Terminal terminal, String message) throws InterruptedException {
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }
        System.out.println();
    }
}
