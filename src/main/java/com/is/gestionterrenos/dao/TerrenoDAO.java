package com.is.gestionterrenos.dao;

import java.sql.*;
import com.is.gestionterrenos.modelo.Terreno;
import java.util.ArrayList;

//TODO:Ciclo 1
public class TerrenoDAO {
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

    public static void eliminar(int idTerreno){
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


    public static void actualizar(int id,String nombre, String ubicacion, int tamHectareas , String tipo, int limiteBase, int limiteAltura, Date fechaRegistro){
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

    public static Terreno buscarPorId(int idTerreno){
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
    public static ArrayList<Terreno>  listarTodos(){
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
    insertar("Terreno 2", "Calle 2", 10, "Finca", 10, 10, new Date(System.currentTimeMillis()));
    //System.out.println(listarTodos());

   }
}
