package com.is.gestionterrenos.dao;

import com.is.gestionterrenos.modelo.Arrendatario;
import java.util.ArrayList;
import java.sql.*;

//TODO:Ciclo 1

public class ArrendatarioDAO {
    public static Connection conn;


    public static void insertar(int id, String dni, String nombre, int edad, char sexo, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("INSERT INTO Arrendatarios (id,dni,nombre,edad,sexo,fechaRegistro) VALUES (?,?,?,?,?,?)");
            pS.setInt(1, id);
            pS.setString(2, dni);
            pS.setString(3, nombre); 
            pS.setInt(4, edad);   
            pS.setChar(5,sexo);
            pS.setDate(6, fechaRegistro);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    
    }

    public void eliminar(int id){
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


    public void actualizar(int id, String dni, String nombre, int edad, char sexo, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("UPDATE Arrendatarios SET dni=?, nombre=?, edad=?, sexo=?, fechaRegistro=? WHERE id=?");

            pS.setString(1, dni);
            pS.setString(2, nombre); 
            pS.setInt(3, edad);   
            pS.setChar(4,sexo);
            pS.setDate(5, fechaRegistro);

            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    }

    public Terreno buscarPorId(int idArrendatario){
        Arrendatario arrendatario = null;
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM terrenos WHERE id=?");
            pS.setInt(1, idArrendatario);
            ResultSet rs=pS.executeQuery();
            if(rs.next()){
                arrendatario = new Arrendatario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getChar(5),rs.getDate(6));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return terreno;        
    
    }
    public static ArrayList<Arrendatario>  listarTodos(){
        ArrayList<Arrendatario> arrendatarios=new ArrayList<Arrendatario>();
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM Arrendatarios");
            ResultSet rs=pS.executeQuery();
            while(rs.next()){
                arrendatarios.add(new Arrendatario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getChar(5),rs.getDate(6)));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return terrenos;                
    }

   public static void main(String[] args) {
    //tests
    insertar(1,"12345678A","Juan",30,'H',null);
    insertar(2,"87654321B","Maria",25,'M',null);
    insertar(3,"23456789C","Pedro",40,'H',null);
   }
}
