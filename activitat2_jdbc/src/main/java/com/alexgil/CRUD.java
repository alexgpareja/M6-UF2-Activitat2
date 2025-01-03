/* Implementa las operaciones CRUD específicas para interactuar con los datos en las tablas de la 
 * base de datos (insertar, leer, consultar y mostrar datos). 
 */

package com.alexgil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

    /**
     * Crea la base de dades utilitzant un script SQL llegit des d'un fitxer.
     * Les instruccions del script són executades línia a línia.
     */
    public boolean CreateDatabase(Connection connection, InputStream input)
            throws IOException, ConnectException, SQLException {

        boolean dupRecord = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            StringBuilder sqlStatement = new StringBuilder();
            String line;

            try (Statement statement = connection.createStatement()) {
                while ((line = br.readLine()) != null) {
                    // Ignorar comentaris i línies buides
                    line = line.trim();

                    if (line.isEmpty() || line.startsWith("--") || line.startsWith("//") || line.startsWith("/*")) {
                        continue;
                    }

                    // Acumular la línea al buffer
                    sqlStatement.append(line);

                    // el caràcter ";" es considera terminació de sentència SQL
                    if (line.endsWith(";")) {
                        // Eliminar el ";" i executar la instrucció
                        String sql = sqlStatement.toString().replace(";", "").trim();
                        statement.execute(sql);

                        // Reiniciar el buffer per la següent instrucció
                        sqlStatement.setLength(0);
                    }
                }
            } catch (SQLException sqle) {
                if (!sqle.getMessage().contains("Duplicate entry")) {
                    System.err.println(sqle.getMessage());
                } else {
                    dupRecord = true;
                    br.readLine();
                }
            }
        }

        return dupRecord;
    }

    /**
     * Insereix un nou registre a la taula `Llibres` de la base de dades.
     */
    public void InsertLlibre(Connection connection, String tableName, Llibre llibre) throws SQLException {

        String query = "INSERT INTO " + tableName
                + " (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        // recuperem valor inicial de l'autocommit
        boolean autocommitvalue = connection.getAutoCommit();

        // Desactivar autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            int idx = 1;
            // el idLlibre no esta posat perque té l'autoincrement
            prepstat.setLong(idx++, llibre.getIsbn());
            prepstat.setString(idx++, llibre.getTitol());
            prepstat.setString(idx++, llibre.getAutor());
            prepstat.setInt(idx++, llibre.getAnyPublicacio());
            prepstat.setBoolean(idx++, llibre.isDisponibilitat());
            prepstat.setInt(idx++, llibre.getIdCategoria());

            // Executa
            prepstat.executeUpdate();

            // Fem el commit
            connection.commit();
            System.out.println("Llibre insertat amb èxit.");

        } catch (SQLException sqle) {
            // Si hi ha un error, fer rollback
            connection.rollback();
            System.err.println("Error al insertar el llibre. S'ha realitzat un rollback.");
            if (sqle.getMessage().contains("Duplicate entry")) {
                System.err.println("Registre duplicat");
            } else {
                System.err.println(sqle.getMessage());
            }
        } finally {
            // Retornar al valor original del autocommit
            connection.setAutoCommit(autocommitvalue);
        }
    }

    /**
     * Llegeix tots els llibres de la base de dades amb suport per a paginació.
     * 
     * @param connection Connexió a la base de dades.
     * @param limit      Nombre màxim de registres per mostrar (-1 per mostrar tots
     *                   els registres).
     * @param offset     Posició inicial dels registres (s'ignora si limit és -1).
     * @throws SQLException En cas d'error de connexió o consulta.
     */
    public void ReadAllLlibres(Connection connection, int limit, int offset) throws SQLException {
        // Construcció de la consulta SQL
        String query = (limit == -1)
                ? "SELECT * FROM Llibres"
                : "SELECT * FROM Llibres LIMIT ? OFFSET ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            if (limit != -1) {
                prepstat.setInt(1, limit);
                prepstat.setInt(2, offset);
            }

            try (ResultSet rset = prepstat.executeQuery()) {
                // Obtenim el número de columnes i mostrem els registres
                int colNum = Utils.getColumnNames(rset);

                if (colNum > 0) {
                    Utils.recorrerRegistres(rset, colNum);
                } else {
                    System.out.println("No s'han trobat registres.");
                }
            }
        } catch (SQLException sqle) {
            System.err.println("Error al llegir els llibres: " + sqle.getMessage());
        }
    }

    /**
     * Cerca un llibre a la base de dades utilitzant el seu ISBN.
     */
    public Llibre ReadLlibreByIsbn(Connection connection, long isbn) throws SQLException {
        String query = "SELECT * FROM Llibres WHERE isbn = ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            prepstat.setLong(1, isbn);
            ResultSet rset = prepstat.executeQuery();

            if (rset.next()) {
                // Si troba el llibre, recuperem els valors desde la db
                int idLlibre = rset.getInt("id_llibre"); // autoincrement
                long isbnResult = rset.getInt("isbn");
                String titol = rset.getString("titol");
                String autor = rset.getString("autor");
                int anyPublicacio = rset.getInt("any_publicacio");
                boolean disponibilitat = rset.getBoolean("disponibilitat");
                int idCategoria = rset.getInt("id_categoria");

                // Tornar un nou objecte llibre amb els valors obtinguts
                return new Llibre(idLlibre, isbnResult, titol, autor, anyPublicacio, disponibilitat, idCategoria);
            }

        } catch (SQLException sqle) {
            System.err.println("Error al llegir el llibre: " + sqle.getMessage());
        }

        // Si no troba el llibre, retorna null
        return null;
    }

    /**
     * Cerca llibres a la base de dades utilitzant un títol parcial o complet.
     * Mostra els resultats per consola.
     */
    public void readLlibreByTitol(Connection connection, String titol) throws SQLException {
        String query = "SELECT * FROM Llibres WHERE titol LIKE ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            // Afegim els percentatges per a la cerca amb LIKE
            prepstat.setString(1, "%" + titol + "%");
            ResultSet rset = prepstat.executeQuery();

            // Comprovem si hi ha registres
            if (!rset.isBeforeFirst()) {
                System.out.println("No s'ha trobat cap llibre amb un títol que contingui: " + titol);
                return;
            }

            // Obtenim el número de columnes i mostrem els noms
            int colNum = Utils.getColumnNames(rset);

            // Recorrem els registres i els mostrem
            Utils.recorrerRegistres(rset, colNum);

        } catch (SQLException sqle) {
            System.err.println("Error al cercar llibres pel títol: " + sqle.getMessage());
        }
    }

    /**
     * Actualitza les dades d'un llibre existent a la base de dades.
     * Identifica el llibre utilitzant el seu ISBN.
     */
    public void UpdateLlibre(Connection connection, Llibre llibre) throws SQLException {
        String query = "UPDATE Llibres SET titol = ?, autor = ?, any_publicacio = ?, disponibilitat = ?, id_categoria = ? "
                + "WHERE isbn = ?";

        // Recuperem el valor inicial de l'autocommit
        boolean autocommitvalue = connection.getAutoCommit();

        // Desactivar l'autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            int idx = 1; // Inicialitzem el comptador d'índexs

            // Establim els valors dels camps que es poden modificar
            prepstat.setString(idx++, llibre.getTitol()); // Títol
            prepstat.setString(idx++, llibre.getAutor()); // Autor
            prepstat.setInt(idx++, llibre.getAnyPublicacio()); // Any de publicació
            prepstat.setBoolean(idx++, llibre.isDisponibilitat()); // Disponibilitat
            prepstat.setInt(idx++, llibre.getIdCategoria()); // ID de categoria

            // L'ISBN no ha de canviar, però s'utilitza com a identificador per realitzar
            // l'UPDATE
            prepstat.setLong(idx++, llibre.getIsbn()); // ISBN com a clau primària

            // Executar l'actualització
            int rowsAffected = prepstat.executeUpdate();

            // Si s'ha actualitzat almenys un registre
            if (rowsAffected > 0) {
                // Fem commit si l'operació ha estat exitosa
                connection.commit();
                System.out.println("Llibre actualitzat amb èxit.");
            } else {
                System.out.println("No s'ha trobat cap llibre amb l'ISBN: " + llibre.getIsbn());
            }

        } catch (SQLException sqle) {
            // Si hi ha un error, fem un rollback
            connection.rollback();
            System.err.println("Error en actualitzar el llibre. S'ha realitzat un rollback." + sqle.getMessage());
        } finally {
            // Tornem al valor original de l'autocommit
            connection.setAutoCommit(autocommitvalue);
        }
    }

    /**
     * Elimina un llibre de la base de dades utilitzant el seu ISBN.
     */
    public boolean DeleteLlibre(Connection connection, long isbn) throws SQLException {
        String query = "DELETE FROM Llibres WHERE isbn = ?";

        // Recuperar el valor inicial de l'autocommit
        boolean autocommitvalue = connection.getAutoCommit();

        // Desactivar autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            prepstat.setLong(1, isbn);

            // Executar la sentència DELETE
            int rowsAffected = prepstat.executeUpdate();

            // Si s'ha eliminat almenys un registre
            if (rowsAffected > 0) {
                connection.commit();
                return true; // Retornem true si s'ha eliminat
            } else {
                connection.rollback();
                return false; // Retornem false si no hi ha coincidències
            }
        } catch (SQLException sqle) {
            connection.rollback();
            throw sqle;
        } finally {
            // Tornem l'autocommit al valor original
            connection.setAutoCommit(autocommitvalue);
        }
    }

    /**
     * Llegeix els registres de la taula `Categoria` amb opció de paginació i els
     * mostra per consola.
     * 
     * @param connection La connexió a la base de dades.
     * @param limit      El nombre màxim de registres per pàgina. Passa -1 per
     *                   desactivar la paginació.
     * @param offset     El desplaçament inicial (només aplicable si el límit no és
     *                   -1).
     * @throws SQLException Si es produeix un error amb la base de dades.
     */
    public void readAllCategories(Connection connection, int limit, int offset) throws SQLException {
        // Construïm la consulta SQL amb o sense límits segons els paràmetres
        String query = (limit > 0)
                ? "SELECT * FROM Categoria LIMIT ? OFFSET ?"
                : "SELECT * FROM Categoria";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            if (limit > 0) {
                // Configurem els paràmetres de límit i desplaçament si cal
                prepstat.setInt(1, limit);
                prepstat.setInt(2, offset);
            }

            try (ResultSet rset = prepstat.executeQuery()) {
                // Obtenim el número de columnes i mostrem els noms de les columnes
                int colNum = Utils.getColumnNames(rset);

                // Si el número de columnes és > 0, procedim a llegir i mostrar els registres
                if (colNum > 0) {
                    Utils.recorrerRegistres(rset, colNum);
                } else {
                    System.out.println("No s'han trobat registres a la taula Categoria.");
                }
            }
        } catch (SQLException sqle) {
            // Gestionem i mostrem l'error si es produeix alguna excepció SQL
            System.err.println("Error al llegir les categories: " + sqle.getMessage());
        }
    }

    /**
     * Cerca una categoria a la base de dades utilitzant el seu identificador.
     * Mostra els resultats per consola.
     */
    public void readCategoriaById(Connection connection, int idCategoria) throws SQLException {
        // Consulta SQL per obtenir la categoria pel seu identificador
        String query = "SELECT * FROM Categoria WHERE id_categoria = ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            // Assignem el valor de l'identificador al paràmetre de la consulta
            prepstat.setInt(1, idCategoria);

            // Executem la consulta i obtenim els resultats
            try (ResultSet rset = prepstat.executeQuery()) {
                // Obtenim el nombre de columnes i mostrem els noms de les columnes
                int colNum = Utils.getColumnNames(rset);

                // Si hi ha resultats, recorrem i mostrem els registres
                if (colNum > 0) {
                    Utils.recorrerRegistres(rset, colNum);
                }
            }
        } catch (SQLException sqle) {
            // Mostrem l'error si es produeix una excepció SQL
            System.err.println("Error durant la consulta: " + sqle.getMessage());
        }
    }
}
