package com.alexgil;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.function.Consumer;

public class Utils {

    // Aquest mètode auxiliar podria ser utileria del READ, mostra el nom de les
    // columnes i quantes n'hi ha
    public static int getColumnNames(ResultSet rs) throws SQLException {

        int numberOfColumns = 0;

        if (rs != null) {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            numberOfColumns = rsMetaData.getColumnCount();

            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }
        }

        System.out.println();

        return numberOfColumns;

    }

    public static void recorrerRegistres(ResultSet rs, int ColNum) throws SQLException {

        while (rs.next()) {
            for (int i = 0; i < ColNum; i++) {
                if (i + 1 == ColNum) {
                    System.out.println(rs.getString(i + 1));
                } else {

                    System.out.print(rs.getString(i + 1) + ", ");
                }
            }
        }
    }

    public static void showPagedData(BufferedReader br, CRUD crud, Connection connection, String tableName,
            Consumer<Integer[]> dataFetcher) throws SQLException, IOException {
        int limit = 10; // Nombre de registres per pàgina (per defecte)
        int offset = 0; // Posició inicial

        System.out.println("Vols activar la paginació per a la taula " + tableName + "? (S/N)");
        String paginar = br.readLine();
        if (paginar.equalsIgnoreCase("S")) {
            System.out.print("Quants registres vols mostrar per pàgina? ");
            limit = Integer.parseInt(br.readLine());
        } else {
            limit = -1; // Mostrar tots els registres de cop
        }

        boolean continuar = true;
        while (continuar) {
            // Crida al mètode que executa la consulta amb el limit i l'offset
            dataFetcher.accept(new Integer[] { limit, offset });

            if (limit != -1) {
                System.out.println("Vols veure més registres? (S/N)");
                String resposta = br.readLine();
                if (resposta.equalsIgnoreCase("S")) {
                    offset += limit; // Incrementar l'offset
                } else {
                    continuar = false;
                }
            } else {
                continuar = false; // Si no hi ha paginació, acabar
            }
        }
    }

}
