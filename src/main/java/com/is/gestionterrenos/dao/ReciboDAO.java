package com.is.gestionterrenos.dao;

import com.is.gestionterrenos.modelo.Recibo;
import java.util.ArrayList;
import java.sql.*;

public class ReciboDAO {
    public static Connection conn;

    public static int insertar(int idArren, int idParcela, Date fechaEmision, double importe, double iva, double irpf, boolean pagado, boolean activo){
        int idRecibo=-1;
        try {
            conn = ConexionDB.getConn();
            PreparedStatement pS = conn.prepareStatement("INSERT INTO Recibos (idArren, idParcela, fechaEmision, importe, iva, irpf, alquilado, pagado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pS.setInt(1, idArren);
            pS.setInt(2, idParcela);
            pS.setDate(3, fechaEmision);
            pS.setDouble(4, importe);
            pS.setDouble(5, iva);
            pS.setDouble(6, irpf);
            if(activo)
                pS.setInt(7,1);
            else
                pS.setInt(7,0);
                
            if(pagado)
                pS.setInt(8, 1);
            else
                pS.setInt(8,0);

            pS.executeUpdate();

            ResultSet generatedKeys = pS.getGeneratedKeys();
            if (generatedKeys.next()) {
                idRecibo = generatedKeys.getInt(1); 
            }
            
            pS.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idRecibo;
    }

    public static void eliminar(int id){
        try {
            conn = ConexionDB.getConn();
            PreparedStatement pS = conn.prepareStatement("DELETE FROM Recibos WHERE id = ?");
            pS.setInt(1, id);

            pS.executeUpdate();
            pS.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, int idArren, int idParcela, double importe, double iva, double irpf){
        try {
            conn = ConexionDB.getConn();
            PreparedStatement pS = conn.prepareStatement("UPDATE Recibos SET idArren = ?, idParcela = ?, importe = ?, iva = ?, irpf = ? WHERE id = ?");
            pS.setInt(1, idArren);
            pS.setInt(2, idParcela);
            pS.setDouble(3, importe);
            pS.setDouble(4, iva);
            pS.setDouble(5, irpf);
            pS.setInt(6, id);

            pS.executeUpdate();
            pS.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Recibo buscarPorId(int idRecibo){
        Recibo recibo = null;
        try {
            conn = ConexionDB.getConn();
            PreparedStatement pS = conn.prepareStatement("SELECT * FROM Recibos WHERE id = ?");
            pS.setInt(1, idRecibo);

            ResultSet rs = pS.executeQuery();
            if (rs.next()) {
                recibo = new Recibo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
            }
            pS.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recibo;
    }

    public static ArrayList<Recibo> buscarRecibos(int idArren, int idParcela, double importe, double iva, double irpf) {
        ArrayList<Recibo> recibos = new ArrayList<Recibo>();
        try {
            conn = ConexionDB.getConn();
            String consulta = "SELECT * FROM Recibos WHERE TRUE";
            boolean hayIdArren = idArren != 0;
            boolean hayIdParcela = idParcela != 0;
            // boolean hayFecha = fechaEmision != null;
            boolean hayImporte = importe != 0.0;
            boolean hayIva = iva != 0.0;
            boolean hayIrpf = irpf != 0.0;

            if (hayIdArren) {
                consulta += " AND idArren = ?";
            }
            if (hayIdParcela) {
                consulta += " AND idParcela = ?";
            }
            // if (hayFecha) {
            //     consulta += " AND fechaEmision = ?";
            // }
            if (hayImporte) {
                consulta += " AND importe = ?";
            }
            if (hayIva) {
                consulta += " AND iva = ?";
            }
            if (hayIrpf) {
                consulta += " AND irpf = ?";
            }

            PreparedStatement pS = conn.prepareStatement(consulta);
            int i = 1;

            if (hayIdArren) {
                pS.setInt(i++, idArren);
            }
            if (hayIdParcela) {
                pS.setInt(i++, idParcela);
            }
            // if (hayFecha) {
            //     pS.setDate(i++, fechaEmision);
            // }
            if (hayImporte) {
                pS.setDouble(i++, importe);
            }
            if (hayIva) {
                pS.setDouble(i++, iva);
            }
            if (hayIrpf) {
                pS.setDouble(i++, irpf);
            }

            ResultSet rs = pS.executeQuery();
            while (rs.next()) {
                recibos.add(new Recibo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recibos;
    }

    public static ArrayList<Recibo> listarTodos() {
        ArrayList<Recibo> recibos = new ArrayList<Recibo>();
        try {
            conn = ConexionDB.getConn();
            PreparedStatement pS = conn.prepareStatement("SELECT * FROM Recibos");
            ResultSet rs = pS.executeQuery();
            while (rs.next()) {
                recibos.add(new Recibo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }
            pS.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recibos;
    }

    // Pruebas -----
    
}
