package com.is.gestionterrenos.dao;

import com.is.gestionterrenos.modelo.Arrendatario;
import java.util.ArrayList;
import java.sql.*;

//TODO:Ciclo 1

public class ArrendatarioDAO {
    public static Connection conn;


    public static void insertar(String dni, String nombre, int edad, String sexo, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("INSERT INTO Arrendatarios (dni,nombre,edad,sexo,fechaRegistro) VALUES (?,?,?,?,?)");
            pS.setString(1, dni);
            pS.setString(2, nombre); 
            pS.setInt(3, edad);   
            pS.setString(4,sexo);
            pS.setDate(5, fechaRegistro);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    
    }

    public static void eliminar(int id){
     try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("DELETE FROM Arrendatarios WHERE id=?");
            pS.setInt(1, id);
            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }    
    }


    public static void actualizar(int id, String dni, String nombre, int edad, String sexo, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("UPDATE Arrendatarios SET dni=?, nombre=?, edad=?, sexo=?, fechaRegistro=? WHERE id=?");

            pS.setString(1, dni);
            pS.setString(2, nombre); 
            pS.setInt(3, edad);   
            pS.setString(4,sexo);
            pS.setDate(5, fechaRegistro);
            pS.setInt(6, id);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    }

    public static  Arrendatario buscarPorId(int idArrendatario){
        Arrendatario arrendatario = null;
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM arrendatarios WHERE id=?");
            pS.setInt(1, idArrendatario);
            ResultSet rs=pS.executeQuery();
            if(rs.next()){
                arrendatario = new Arrendatario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDate(6));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return arrendatario;        
    
    }
    public static ArrayList<Arrendatario>  listarTodos(){
        ArrayList<Arrendatario> arrendatarios=new ArrayList<Arrendatario>();    
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM Arrendatarios");
            ResultSet rs=pS.executeQuery();
            while(rs.next()){
                arrendatarios.add(new Arrendatario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDate(6)));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return arrendatarios;                
    }

    public static void main(String[] args) {
    //tests insertar
    //insertar("12388338Z","Jano",21,"H",new Date(System.currentTimeMillis()));
    //tests eliminar
    // eliminar(5);
    // eliminar(7);
    //tests actualizar
    //  actualizar(10,"12389338Z","Jorge",21,"H",new Date(System.currentTimeMillis()));
    //tests buscar por id
    //System.out.println(buscarPorId(3));
        //tests listar todos
        System.out.println(listarTodos());
   }
}
