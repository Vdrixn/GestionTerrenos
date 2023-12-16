package com.is.gestionterrenos.dao;

import java.sql.*;

import com.is.gestionterrenos.modelo.Parcela;
import java.util.ArrayList;

//TODO:Ciclo 1
public class ParcelaDAO {
    public static Connection conn;


    public static void insertar(int idTerreno, String ubicacion, int limiteBase, int limiteAltura, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("INSERT INTO parcelas (idTerreno, ubicacion, limiteBase, limiteAltura, fechaRegistro) VALUES (?,?,?,?,?)");
            pS.setInt(1, idTerreno);
            pS.setString(2, ubicacion);
            pS.setInt(3, limiteBase);
            pS.setInt(4, limiteAltura);
            pS.setDate(5, fechaRegistro);
            pS.executeUpdate();
            pS.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }        

    
    }

    public static void eliminar(int idParcela){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("DELETE FROM parcelas WHERE id=?");
            pS.setInt(1, idParcela);
            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        
    }


    public static void actualizar(int id,int idTerreno, String ubicacion, int limiteBase, int limiteAltura, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("UPDATE parcelas SET ubicacion=?, limiteBase=?, limiteAltura=?, fechaRegistro=? WHERE id=?");
           
            pS.setString(1, ubicacion);
            pS.setInt(2, limiteBase);
            pS.setInt(3, limiteAltura);
            pS.setDate(4, fechaRegistro);

            pS.setInt(5, id);
            
            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    }

    public static Parcela buscarPorId(int idParcela){
        Parcela parcela=null;
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM parcelas WHERE id=?");
            pS.setInt(1, idParcela);
            ResultSet rs=pS.executeQuery();
            if(rs.next()){
                parcela=new Parcela(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDate(6));
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return parcela;                
    

    
    }
    public static ArrayList<Parcela>  listarTodos(){
        ArrayList<Parcela> parcelas=new ArrayList<>();
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("SELECT * FROM parcelas");
            ResultSet rs=pS.executeQuery();
            while(rs.next()){
                Parcela parcela=new Parcela(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDate(6));
                parcelas.add(parcela);
            }
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return parcelas;                  
    }

   public static void main(String[] args) {
    //Prueba de inserción
     insertar(1,"Calle 1",10,20,new Date(System.currentTimeMillis()));
    //Prueba de eliminación
     eliminar(2);
    //Prueba de actualización
     actualizar(3,1,"Calle 4",20,30,new Date(System.currentTimeMillis()));
    //Prueba de búsqueda por id
    Parcela parcela=buscarPorId(1);
    System.out.println(parcela);
    //Prueba de listado de todos
    ArrayList<Parcela> parcelas=listarTodos();
    for(Parcela p:parcelas){
        System.out.println(p);
    
    }
}
}
