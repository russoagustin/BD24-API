package com.russo.api.bd2024.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RestauranteRepository {

    private static RestauranteRepository instance;

    private RestauranteRepository(){}

    public static RestauranteRepository getInstance() {
        if (instance == null){
            instance = new RestauranteRepository();
        }
        return instance;
    }

    public Optional<List<Map<String,Object>>> getUsuarios(String tipo) throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaRestaurantes = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaRestaurantesOptional = Optional.empty();
        String stringQuery = "SELECT RESTAURANTE.nombre, direccion,hora_apertura, hora_cierre FROM RESTAURANTE " +
                "INNER JOIN RESTAURANTE_PREPARA_COMIDA ON RESTAURANTE.idRESTAURANTE = RESTAURANTE_PREPARA_COMIDA.RESTAURANTE_id " +
                "INNER JOIN COMIDA ON RESTAURANTE_PREPARA_COMIDA.COMIDA_id = COMIDA.idCOMIDA WHERE tipo = ?";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setString(1,tipo);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> usuario = new HashMap<>();
                usuario.put("nombre",resultado.getString("nombre"));
                usuario.put("direccion",resultado.getString("direccion"));
                usuario.put("hora_apertura",resultado.getString("hora_apertura"));
                usuario.put("hora_cierre",resultado.getString("hora_cierre"));
                listaRestaurantes.add(usuario);
            }
            listaRestaurantesOptional = Optional.of(listaRestaurantes);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaRestaurantesOptional;
    }

}
