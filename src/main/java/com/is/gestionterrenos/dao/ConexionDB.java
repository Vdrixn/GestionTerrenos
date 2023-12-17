package com.is.gestionterrenos.dao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.File;

public class ConexionDB {

    public static Connection getConn() {
        try {
            //CAMBIAR "password" por vuestra contraseña en mySql
            Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionterrenos", "root", "root");
			
            return miConexion;
			//PreparedStatement miStatement=miConexion.prepareStatement("INSERT INTO parcelas VALUES (1,null,'treinta', 'limite')");
			//miStatement.executeUpdate();
			/*int n=0;
			while(rs.next()){
				n++;
			}*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public static void resetDatabase() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establece la conexión
            conn = getConn();

            // Crear un objeto Statement
            stmt = conn.createStatement();

            // Leer el archivo SQL
            File file = new File("src/main/resources/testDB.sql");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sqlQuery = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Ignora las líneas de comentarios y vacías
                if (line.startsWith("--") || line.trim().isEmpty()) {
                    continue;
                }
                sqlQuery.append(line);

                // Si la línea termina en un punto y coma, ejecuta la consulta
                if (line.endsWith(";")) {
                    stmt.execute(sqlQuery.toString());
                    sqlQuery = new StringBuilder();
                }
            }

            reader.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
