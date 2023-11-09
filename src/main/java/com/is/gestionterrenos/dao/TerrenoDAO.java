package com.is.gestionterrenos.dao;

import java.sql.*;
import java.util.Date;
import com.is.gestionterrenos.modelo.Arrendatario;
import com.is.gestionterrenos.modelo.Terreno;
import java.sql.SQLException;

//TODO:Ciclo 1
public class TerrenoDAO {
    public static Connection conn;


    public static void insertar(String nombre, String ubicacion, int tamHectareas ){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("INSERT INTO terrenos (nombre, ubicacion,tamaño_hectareas) VALUES (?,?,?)");
            pS.setString(1, nombre);    
            pS.setString(2, ubicacion);
            pS.setInt(3, tamHectareas);
            

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        

    }
    public void eliminar(int idTerreno){
        
    }
    public void actualizar(Terreno terreno){
        
    }
    public Arrendatario buscarPorId(int idTerreno){
        return null;
    }
    public void listarTodos(){
        
    }

    public static void main(String[] args) {
        //Imprimir un objeto tipo Date, que contenga la fecha de hoy:


        insertar("Chabola","Calle Adrian Romero, 562",5);
    }
}
