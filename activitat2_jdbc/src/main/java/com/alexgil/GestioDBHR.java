package com.alexgil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    // Método para establecer la conexión con la base de datos
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para cerrar la conexión
    public void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}