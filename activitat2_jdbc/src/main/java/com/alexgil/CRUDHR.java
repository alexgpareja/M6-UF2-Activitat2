/* Implementa las operaciones CRUD específicas para interactuar con los datos en las tablas de la 
 * base de datos (insertar, leer, consultar y mostrar datos). 
 */

package com.alexgil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDHR {

    // Añade un libro
    public void InsertLlibre(Connection connection, Llibre llibre) throws SQLException {

        String query = "INSERT INTO Llibres (isbn, titol, autor, any_publicacio, disponibilitat, id_categoria, num_estanteria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Desactivar autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            int idx = 1;
            prepstat.setInt(idx++, llibre.getIsbn());
            prepstat.setString(idx++, llibre.getTitol());
            prepstat.setString(idx++, llibre.getAutor());
            prepstat.setInt(idx++, llibre.getAnyPublicacio());
            prepstat.setBoolean(idx++, llibre.isDisponibilitat());
            prepstat.setInt(idx++, llibre.getIdCategoria());
            prepstat.setInt(idx++, llibre.getNumEstanteria());

            // Ejecutar la operación
            prepstat.executeUpdate();

            // Hacer commit si la operación fue exitosa
            connection.commit();
            System.out.println("Libro insertado con éxito.");

        } catch (SQLException sqle) {
            // Si hay un error, hacer rollback
            connection.rollback();
            System.err.println("Error al insertar el libro. Se ha realizado un rollback.");
            if (sqle.getMessage().contains("Duplicate entry")) {
                System.out.println("Registro duplicado");
            } else {
                System.err.println(sqle.getMessage());
            }
        } finally {
            // Devolver el valor original del autocommit
            connection.setAutoCommit(true);
        }
    }

    // Lee todos los registros de libros que encuentre
    public void ReadAllLlibres(Connection connection) throws SQLException {
        String query = "SELECT * FROM Llibres";

        try (Statement statement = connection.createStatement()) {
            ResultSet rset = statement.executeQuery(query);

            // Obtener el número de columnas y mostrar los registros
            int colNum = Utils.getColumnNames(rset);

            // Si hay columnas, leer y mostrar los registros
            if (colNum > 0) {
                Utils.recorrerRegistres(rset, colNum);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    // Lee el libro segun el isbn indicado
    public void ReadLlibreByIsbn(Connection connection, int isbn) throws SQLException {
        String query = "SELECT * FROM Llibres WHERE isbn = ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            prepstat.setInt(1, isbn);
            ResultSet rset = prepstat.executeQuery();

            // Obtener el número de columnas y mostrar los registros
            int colNum = Utils.getColumnNames(rset);

            // Si hay columnas, leer y mostrar los registros
            if (colNum > 0) {
                Utils.recorrerRegistres(rset, colNum);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    // Actualiza los datos de un libro
    public void UpdateLlibre(Connection connection, Llibre llibre) throws SQLException {
        String query = "UPDATE Llibres SET titol = ?, autor = ?, any_publicacio = ?, disponibilitat = ?, id_categoria = ?, num_estanteria = ? "
                + "WHERE isbn = ?";

        // Desactivar autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            prepstat.setString(1, llibre.getTitol());
            prepstat.setString(2, llibre.getAutor());
            prepstat.setInt(3, llibre.getAnyPublicacio());
            prepstat.setBoolean(4, llibre.isDisponibilitat());
            prepstat.setInt(5, llibre.getIdCategoria());
            prepstat.setInt(6, llibre.getNumEstanteria());
            prepstat.setInt(7, llibre.getIsbn());

            // Ejecutar la actualización
            prepstat.executeUpdate();

            // Hacer commit si la operación fue exitosa
            connection.commit();
            System.out.println("Libro actualizado con éxito.");

        } catch (SQLException sqle) {
            // Si hay un error, hacer rollback
            connection.rollback();
            System.err.println("Error al actualizar el libro. Se ha realizado un rollback.");
        } finally {
            // Devolver el valor original del autocommit
            connection.setAutoCommit(true);
        }
    }

    // Elimina el libro segun el isbn
    public void DeleteLlibre(Connection connection, int isbn) throws SQLException {

        // Sentencia SQL para eliminar el libro por ISBN
        String query = "DELETE FROM Llibres WHERE isbn = ?";

        // Desactivar autocommit
        connection.setAutoCommit(false);

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            // Usar el ISBN para especificar qué libro eliminar
            prepstat.setInt(1, isbn);

            // Ejecutar la sentencia de eliminación
            int rowsAffected = prepstat.executeUpdate();

            // Si no se afectaron filas, el libro no fue encontrado
            if (rowsAffected == 0) {
                System.out.println("No se encontró el libro con ISBN: " + isbn);
            } else {
                // Hacer commit si la operación fue exitosa
                connection.commit();
                System.out.println("Libro eliminado con éxito.");
            }

        } catch (SQLException sqle) {
            // Si hay un error, hacer rollback
            connection.rollback();
            System.err.println("Error al eliminar el libro. Se ha realizado un rollback.");
        } finally {
            // Devolver el valor original del autocommit
            connection.setAutoCommit(true);
        }
    }

    // Lee todos los registros de categorias que encuentre
    public void readAllCategories(Connection connection) throws SQLException {
        // Consulta SQL para obtener todas las categorías
        String query = "SELECT * FROM Categoria"; // Asegúrate de que el nombre de la tabla sea correcto

        try (Statement statement = connection.createStatement();
                ResultSet rset = statement.executeQuery(query)) {

            // Obtener el número de columnas y mostrar los registros
            int colNum = Utils.getColumnNames(rset);

            // Si el número de columnas es > 0, procedemos a leer y mostrar los registros
            if (colNum > 0) {
                Utils.recorrerRegistres(rset, colNum);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    // Lee una categoria segun el id recibido
    public void readCategoriaById(Connection connection, int idCategoria) throws SQLException {
        String query = "SELECT * FROM Categoria WHERE id_categoria = ?";

        try (PreparedStatement prepstat = connection.prepareStatement(query)) {
            prepstat.setInt(1, idCategoria);
            try (ResultSet rset = prepstat.executeQuery()) {
                int colNum = Utils.getColumnNames(rset);
                if (colNum > 0) {
                    Utils.recorrerRegistres(rset, colNum);
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

}
