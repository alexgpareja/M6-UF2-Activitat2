/*
 * Aquesta classe s'encarrega de gestionar la connexió a la base de dades,
 * executar els scripts d'inicialització, i proporcionar un menú per interactuar
 * amb la base de dades de llibres i categories.
 */
package com.alexgil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GestioDBHR {
    // Aquesta booleana controla si volem sortir de l'aplicació.
    static boolean sortirapp = false;
    static boolean DispOptions = true;

    public static void main(String[] args) {

        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in))) {

            try {
                // Carregar propietats des de l'arxiu de configuració
                Properties properties = new Properties();

                try (InputStream input = GestioDBHR.class.getClassLoader().getResourceAsStream("config.properties")) {
                    properties.load(input);

                    // Obtenir les credencials del fitxer de configuració
                    String dbUrl = properties.getProperty("db.url");
                    String dbUser = properties.getProperty("db.username");
                    String dbPassword = properties.getProperty("db.password");

                    // Establir connexió amb MariaDB
                    try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                        System.out.println("Conexió exitosa");

                        String File_create_script = "db_scripts/DB_Schema_HR.sql";

                        // Carregar i executar l'script de creació de la base de dades
                        InputStream input_sch = GestioDBHR.class.getClassLoader()
                                .getResourceAsStream(File_create_script);

                        CRUD crud = new CRUD();
                        crud.CreateDatabase(connection, input_sch);

                        // Mostrar el menú principal fins que es decideixi sortir
                        while (sortirapp == false) {
                            MenuOptions(br1, crud, connection);
                        }

                    } catch (Exception e) {
                        System.err.println("Error al conectar: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Error al carregar fitxer de propietats: " + e.getMessage());
                }
            } finally {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra el menú principal i gestiona les opcions seleccionades per l'usuari.
     */
    public static void MenuOptions(BufferedReader br, CRUD crud, Connection connection)
            throws NumberFormatException, IOException, SQLException, InterruptedException {

        Terminal terminal = TerminalBuilder.builder().system(true).build();

        // Presentació del menú
        String message = "";
        message = "==================";
        printScreen(terminal, message);

        message = "CONSULTA BD BIBLIOTECA";
        printScreen(terminal, message);

        message = "==================";
        printScreen(terminal, message);

        message = "OPCIONS";
        printScreen(terminal, message);

        // Opcions disponibles
        message = "1. CARREGAR LES DADES";
        printScreen(terminal, message);

        message = "2. CONSULTAR TOTES LES DADES";
        printScreen(terminal, message);

        message = "3. ALTRES CONSULTES";
        printScreen(terminal, message);

        message = "4. INSERIR NOU LLIBRE";
        printScreen(terminal, message);

        message = "5. ACTUALITZAR LLIBRE";
        printScreen(terminal, message);

        message = "6. ELIMINAR LLIBRE";
        printScreen(terminal, message);

        message = "7. GENERAR XML";
        printScreen(terminal, message);

        message = "9. SORTIR";
        printScreen(terminal, message);

        // Sol·licitar opció
        message = "Introdueix l'opcio tot seguit >> ";
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }

        int opcio = Integer.parseInt(br.readLine());

        // Processar l'opció seleccionada
        switch (opcio) {
            case 1:
                String File_data_script = "db_scripts/DB_Data_HR.sql";

                // Executar l'script d'inserció de dades inicials
                InputStream input_data = GestioDBHR.class.getClassLoader().getResourceAsStream(File_data_script);

                if (crud.CreateDatabase(connection, input_data) == true) {
                    System.out.println("Registres duplicats");
                } else {
                    System.out.println("Registres inserits amb éxit");
                }

                break;
            case 2:
                // Mostrar un menú per seleccionar quina taula consultar
                MenuSelect(br, crud, connection);
                break;

            case 3:
                // Consultes addicionals
                MenuSelectAltres(br, crud, connection);
                break;

            case 4:
                // Inserir un nou llibre
                MenuInsert(br, crud, connection);
                break;

            case 5:
                // Actualitzar les dades d'un llibre existent
                MenuUpdateLlibre(br, crud, connection);
                break;

            case 6:
                // Eliminar un llibre per ISBN
                menuEliminarLlibre(br, crud, connection);
                break;

            case 7:
                // Generar un XML amb els registres
                System.out.println("Generació de l'XML encara no implementada.");
                break;

            case 9:
                // Sortir de l'aplicació
                System.out.println("Adéu!!");
                sortirapp = true;
                break;

            default:
                System.out.print("Opcio no vàlida");
                MenuOptions(br, crud, connection);
        }

    }

    /**
     * Imprimeix un missatge caràcter a caràcter amb un efecte de retard.
     */
    private static void printScreen(Terminal terminal, String message) throws InterruptedException {
        for (char c : message.toCharArray()) {
            terminal.writer().print(c);
            terminal.flush();
            Thread.sleep(10);
        }
        System.out.println();
    }

    /**
     * Mostra un submenú per consultar els registres d'una taula específica.
     * L'usuari pot seleccionar entre llibres, categories o sortir del menú.
     */
    public static void MenuSelect(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, NumberFormatException, IOException {

        int opcio = 0;

        while (DispOptions) {
            // Mostrar opcions disponibles
            System.out.println("De quina taula vols mostrar els seus registres?");
            System.out.println("1. Llibres");
            System.out.println("2. Categoria");
            System.out.println("3. Sortir");

            System.out.print("Introdueix l'opció tot seguit >> ");

            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1:
                    // Consulta de tots els llibres
                    crud.ReadAllLlibres(connection);
                    break;
                case 2:
                    // Consulta de totes les categories
                    crud.readAllCategories(connection);
                    break;
                case 3:
                    // Sortir del menú
                    DispOptions = false;
                    break;
                default:
                    System.out.print("Opcio no vàlida");
            }

            if (DispOptions) {
                // Preguntar si es vol fer una altra consulta
                System.out.println("Vols fer altra consulta? (S o N): ");
                String opcioB = br.readLine();

                if (opcioB.equalsIgnoreCase("n")) {
                    System.out.println("No, no marxis si us plau!");
                    DispOptions = false;
                    break;
                }
            }
        }
    }

    /**
     * Mostra un submenú per realitzar consultes específiques, com buscar un llibre
     * pel seu ISBN o per un títol parcial o complet.
     */
    public static void MenuSelectAltres(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, IOException {

        int opcio = 0;

        while (DispOptions) {
            // Opcions de consulta específica
            System.out.println("Quina consulta vols fer?");
            System.out.println("1. Llibre per ISBN");
            System.out.println("2. Llibre per títol");
            System.out.println("3. Sortir");

            System.out.print("Introdueix l'opció tot seguit >> ");
            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1:
                    // Buscar un llibre pel seu ISBN
                    System.out.println("Introdueix el ISBN del llibre >> ");
                    int isbn = Integer.parseInt(br.readLine());
                    crud.ReadLlibreByIsbn(connection, isbn);
                    break;
                case 2:
                    // Buscar llibres amb un títol parcial o complet
                    System.out.println("Introdueix tot o part del títol del llibre >> ");
                    String titolACercar = br.readLine();
                    crud.readLlibreByTitol(connection, titolACercar);
                    break;
                case 3:
                    // Sortir del submenú
                    DispOptions = false;
                    break;
                default:
                    System.out.println("Opció no vàlida");
            }
        }
    }

    /**
     * Permet inserir un nou llibre a la base de dades sol·licitant les dades
     * necessàries a l'usuari.
     */
    public static void MenuInsert(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, NumberFormatException, IOException {

        System.out.println("Introdueix les dades del nou llibre");

        // Sol·licitar els camps obligatoris per inserir un llibre
        System.out.print("ISBN: ");
        int isbn = Integer.parseInt(br.readLine());

        System.out.print("Títol: ");
        String titol = br.readLine();

        System.out.print("Autor: ");
        String autor = br.readLine();

        System.out.print("Any de publicació: ");
        int anyPublicacio = Integer.parseInt(br.readLine());

        System.out.print("Disponibilitat (1 per disponible, 0 per no disponible): ");
        boolean disponibilitat = Integer.parseInt(br.readLine()) == 1;

        System.out.print("Id de Categoria: ");
        int idCategoria = Integer.parseInt(br.readLine());

        System.out.print("Número d'estanteria: ");
        int numEstanteria = Integer.parseInt(br.readLine());

        // Crear un objecte Llibre i inserir-lo a la base de dades
        Llibre llibre = new Llibre(0, isbn, titol, autor, anyPublicacio, disponibilitat, idCategoria, numEstanteria);
        crud.InsertLlibre(connection, "Llibre", llibre);

        System.out.println("Llibre afegit amb èxit!");
    }

    /**
     * Permet actualitzar un llibre existent basant-se en el seu ISBN.
     * L'usuari pot modificar només els camps que desitgi.
     */
    public static void MenuUpdateLlibre(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, IOException {

        System.out.println("Introdueix l'ISBN del llibre que vols actualitzar:");
        int isbn = Integer.parseInt(br.readLine());

        // Buscar el llibre segons l'ISBN
        Llibre llibre = crud.ReadLlibreByIsbn(connection, isbn);

        if (llibre == null) {
            System.out.println("No s'ha trobat cap llibre amb l'ISBN: " + isbn);
            return;
        }

        // Mostrar les dades actuals del llibre
        System.out.println("Llibre trobat: " + llibre);

        // Permetre a l'usuari modificar els camps que desitgi
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        System.out.print("Nou títol: ");
        String titol = br.readLine();
        if (!titol.isEmpty()) {
            llibre.setTitol(titol);
        }

        System.out.print("Nou autor: ");
        String autor = br.readLine();
        if (!autor.isEmpty()) {
            llibre.setAutor(autor);
        }

        System.out.print("Nou any de publicació: ");
        String anyPublicacioInput = br.readLine();
        if (!anyPublicacioInput.isEmpty()) {
            llibre.setAnyPublicacio(Integer.parseInt(anyPublicacioInput));
        }

        System.out.print("Nova disponibilitat (1 = disponible, 0 = no disponible): ");
        String disponibilitatInput = br.readLine();
        if (!disponibilitatInput.isEmpty()) {
            llibre.setDisponibilitat(Integer.parseInt(disponibilitatInput) == 1);
        }

        System.out.print("Nou id de categoria: ");
        String idCategoriaInput = br.readLine();
        if (!idCategoriaInput.isEmpty()) {
            llibre.setIdCategoria(Integer.parseInt(idCategoriaInput));
        }

        System.out.print("Nou número d'estanteria: ");
        String numEstanteriaInput = br.readLine();
        if (!numEstanteriaInput.isEmpty()) {
            llibre.setNumEstanteria(Integer.parseInt(numEstanteriaInput));
        }

        // Actualitzar el llibre a la base de dades
        crud.UpdateLlibre(connection, llibre);
    }

    /**
     * Permet eliminar un llibre existent basant-se en el seu ISBN després d'una
     * confirmació de l'usuari.
     */
    public static void menuEliminarLlibre(BufferedReader br, CRUD crud, Connection connection)
            throws SQLException, IOException {

        System.out.println("Introdueix l'ISBN del llibre que vols eliminar:");
        int isbn = Integer.parseInt(br.readLine());

        // Confirmar l'acció abans d'eliminar
        System.out.println("Segur que vols eliminar el llibre amb ISBN: " + isbn + "? (S/N)");
        String confirmacio = br.readLine();

        if (confirmacio.equalsIgnoreCase("S")) {
            try {
                boolean eliminat = crud.DeleteLlibre(connection, isbn);
                if (eliminat) {
                    System.out.println("Llibre eliminat amb èxit.");
                } else {
                    System.out.println("No s'ha trobat cap llibre amb l'ISBN: " + isbn);
                }
            } catch (SQLException e) {
                System.err.println("Error al intentar eliminar el llibre: " + e.getMessage());
            }
        } else {
            System.out.println("Eliminació cancel·lada.");
        }
    }

}