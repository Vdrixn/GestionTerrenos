package com.is.gestionterrenos.dao;
import java.sql.*;

public class ConexionDB {
    public static void main(String[] args) {
        try {
            Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/balonmano", "root", "password");
			
			/*PreparedStatement miStatement=miConexion.prepareStatement("SELECT DISTINCTROW nombre_club from equipo");
			ResultSet rs=miStatement.executeQuery();
			int n=0;
			while(rs.next()){
				n++;
			}*/
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
