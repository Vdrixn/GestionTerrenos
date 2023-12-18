package com.is.gestionterrenos.controlador;

import java.sql.Date;
import java.util.ArrayList;

import com.is.gestionterrenos.dao.ParcelaDAO;
import com.is.gestionterrenos.dao.TerrenoDAO;
import com.is.gestionterrenos.modelo.Parcela;
import com.is.gestionterrenos.modelo.Terreno;
import com.is.gestionterrenos.vista.VistaParcelas;

public class ControladorParcelas {

    public ControladorParcelas() {
    }

    public static int insertar() {
        return ParcelaDAO.insertar(Integer.parseInt(VistaParcelas.IdTerrenoasociadoActual),
                VistaParcelas.ubicacionActual, Integer.parseInt(VistaParcelas.limiteBaseActual),
                Integer.parseInt(VistaParcelas.limiteAlturaActual), new Date(System.currentTimeMillis()));
    }

    public static int borrar(String strParecela) {
        // Sacamos el id del arrendatario, como todos tienen el mismo formato, podemos
        // sacar el id en la misma posición. EMPIEZA EN POS 17
        int idParcela;
        idParcela = Integer.parseInt("" + strParecela.charAt(12));
        int i = 13;
        while (Character.isDigit(strParecela.charAt(i))) {
            idParcela = idParcela * 10 + Integer.parseInt("" + strParecela.charAt(i));
            i++;
        }

        // Procedemos con el borrado
        return ParcelaDAO.eliminar(idParcela);
    }

    public static int actualizar(String strParcela, String Ubi, String Limitebase, String limiteAltura) {
        int idParcela;
        idParcela = Integer.parseInt("" + strParcela.charAt(12));
        int i = 13;
        while (Character.isDigit(strParcela.charAt(i))) {
            idParcela = idParcela * 10 + Integer.parseInt("" + strParcela.charAt(i));
            i++;
        }
        Parcela parcela = null;

        parcela = ParcelaDAO.buscarPorId(idParcela);
        String Idterreno = String.valueOf(parcela.getIdTerreno());

        if (Ubi.equals("")) {
            Ubi = parcela.getUbicacion();
        }
        if (Limitebase.equals("")) {
            Limitebase = String.valueOf(parcela.getLimiteBase());
        }
        if (limiteAltura.equals("")) {
            limiteAltura = String.valueOf(parcela.getLimiteAltura());
        }
        return ParcelaDAO.actualizar(idParcela, Integer.parseInt(Idterreno), Ubi, Integer.parseInt(Limitebase),
                Integer.parseInt(limiteAltura), new Date(System.currentTimeMillis()));
    }

    public static ArrayList<Parcela> listar() {
        return ParcelaDAO.listarTodos();
    }

    public static boolean ValidarIdTerreno(int IdTerreno) {
        Terreno Terrenoactual = TerrenoDAO.buscarPorId(IdTerreno);
        if (Terrenoactual != null && Terrenoactual.getId() == IdTerreno) {
            return true;
        } else
            return false;
    }
}
