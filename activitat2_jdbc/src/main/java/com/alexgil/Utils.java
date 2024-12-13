package com.alexgil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
}
