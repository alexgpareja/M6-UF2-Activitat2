/*
 * Esta clase se ocupa de las configuraciones, la conexión a la base de datos y la ejecución de los scripts de inicialización (crear tablas y cargar datos)
 */

package com.alexgil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class GestioDBHR {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Constructor estático que carga la configuración desde el archivo .prop
    static {
        loadDatabaseConfig();
    }

    // Método privado para cargar la configuración desde el archivo .prop
    private static void loadDatabaseConfig() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.prop")) {
            properties.load(input);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar el script de crear BD y meter los inserts
    public void executeDatabaseScripts() {
        // Intentar la conexión
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Ejecutar el script de creación de la base de datos y tablas
            executeScript(conn, "db_scripts/DB_Schema_HR.sql");

            // Ejecutar el script de insert de datos
            executeScript(conn, "db_scripts/DB_Data_HR.sql");

            System.out.println("Transacción completada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar un script SQL desde un archivo
    public void executeScript(Connection conn, String scriptPath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(scriptPath)) {
            if (inputStream == null) {
                System.err.println("Error: No se pudo encontrar el archivo de script " + scriptPath);
                return;
            }

            // Leemos el script desde el archivo
            String script = new String(inputStream.readAllBytes());

            // Crear un Statement para ejecutar el script
            try (Statement stmt = conn.createStatement()) {
                // Ejecutar el script
                stmt.executeUpdate(script);
                System.out.println("Script ejecutado con éxito: " + scriptPath);
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}