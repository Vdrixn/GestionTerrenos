package com.is.gestionterrenos.dao;

import java.sql.*;

import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Terreno;
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

    public void eliminar(int idParcela){
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


    public void actualizar(int id,int idTerreno, String ubicacion, int limiteBase, int limiteAltura, Date fechaRegistro){
        try{
            conn=ConexionDB.getConn();
            PreparedStatement pS=conn.prepareStatement("UPDATE parcelas SET id=?, ubicacion=?, limiteBase=?, limiteAltura=?, fechaRegistro=? WHERE id=?");
            pS.setInt(1, idTerreno);
            pS.setString(2, ubicacion);
            pS.setInt(3, limiteBase);
            pS.setInt(4, limiteAltura);
            pS.setDate(5, fechaRegistro);
            pS.setInt(6, id);
            pS.executeUpdate();
            pS.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }        

    }

    public Parcela buscarPorId(int idParcela){
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
    public ArrayList<Parcela>  listarTodos(){
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
    }
}
