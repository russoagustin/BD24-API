package com.russo.api.bd2024.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class RestauranteRepository {

    public Optional<List<Map<String,Object>>> getRestaurantes() throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaRestaurantes = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaRestaurantesOptional = Optional.empty();
        String stringQuery = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> restaurante = new HashMap<>();
                restaurante.put("nombre",resultado.getString("nombre"));
                restaurante.put("direccion",resultado.getString("direccion"));
                restaurante.put("hora_apertura",resultado.getString("hora_apertura"));
                restaurante.put("hora_cierre",resultado.getString("hora_cierre"));
                restaurante.put("url",resultado.getString("url"));
                restaurante.put("descripción",resultado.getString("descripcion"));
                listaRestaurantes.add(restaurante);
            }
            listaRestaurantesOptional = Optional.of(listaRestaurantes);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaRestaurantesOptional;
    }
    public Optional<List<Map<String,Object>>> getRestaurantes(String tipo) throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaRestaurantes = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaRestaurantesOptional = Optional.empty();
        String stringQuery = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE "+
                "INNER JOIN RESTAURANTE_PREPARA_COMIDA ON RESTAURANTE.idRESTAURANTE = RESTAURANTE_PREPARA_COMIDA.RESTAURANTE_id "+
                "WHERE COMIDA_id IN (SELECT idCOMIDA FROM COMIDA WHERE tipo LIKE ?)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setString(1,tipo);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> restaurante = new HashMap<>();
                restaurante.put("nombre",resultado.getString("nombre"));
                restaurante.put("direccion",resultado.getString("direccion"));
                restaurante.put("hora_apertura",resultado.getString("hora_apertura"));
                restaurante.put("hora_cierre",resultado.getString("hora_cierre"));
                restaurante.put("url",resultado.getString("url"));
                restaurante.put("descripción",resultado.getString("descripcion"));
                listaRestaurantes.add(restaurante);
            }
            listaRestaurantesOptional = Optional.of(listaRestaurantes);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaRestaurantesOptional;
    }
}
