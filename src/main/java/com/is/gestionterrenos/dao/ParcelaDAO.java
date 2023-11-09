package com.is.gestionterrenos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.is.gestionterrenos.modelo.Terreno;

//TODO:Ciclo 1
public class ParcelaDAO {
    public static Connection conn;


    public static void insertar(String nombre, String ubicacion, int tamHectareas , String tipo, int limiteBase, int limiteAltura, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("INSERT INTO terrenos (nombre, ubicacion, tamHectareas, tipo, limiteBase, limiteAltura, fechaRegistro) VALUES (?,?,?,?,?,?,?)");
            pS.setString(1, nombre);    
            pS.setString(2, ubicacion);
            pS.setInt(3, tamHectareas);
            pS.setString(4, tipo);
            pS.setInt(5, limiteBase);
            pS.setInt(6, limiteAltura);
            pS.setDate(7, fechaRegistro);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    
    }

    public void eliminar(int idTerreno){
     try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("DELETE FROM terrenos WHERE id=?");
            pS.setInt(1, idTerreno);
            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }


    public void actualizar(int id,String nombre, String ubicacion, int tamHectareas , String tipo, int limiteBase, int limiteAltura, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("UPDATE terrenos SET nombre=?, ubicacion=?,tamHectareas=?,tipo=?, limiteBase=?,limiteAltura=?,fechaRegistro=? WHERE id=?");

            pS.setString(1, nombre);    
            pS.setString(2, ubicacion);
            pS.setInt(3, tamHectareas);
            pS.setString(4, tipo);
            pS.setInt(5, limiteBase);
            pS.setInt(6, limiteAltura);
            pS.setDate(7, fechaRegistro);
            pS.setInt(8, id);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    }

    public Terreno buscarPorId(int idTerreno){
        Terreno terreno=null;
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM terrenos WHERE id=?");
            pS.setInt(1, idTerreno);
            ResultSet rs=pS.executeQuery();
            if(rs.next()){
                terreno=new Terreno(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return terreno;        
    
    }
    public ArrayList<Terreno>  listarTodos(){
        ArrayList<Terreno> terrenos=new ArrayList<Terreno>();
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM terrenos");
            ResultSet rs=pS.executeQuery();
            while(rs.next()){
                terrenos.add(new Terreno(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return terrenos;                
    }

   public static void main(String[] args) {
    insertar("Terreno 1", "Calle 1", 10, "Terreno", 10, 10, new Date(System.currentTimeMillis()));
   }
}
