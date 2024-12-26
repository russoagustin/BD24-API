package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.RestauranteDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class RestauranteRepository implements IRestauranteRepository {

    @Override
    public Optional<List<RestauranteDTO>> getRestaurantes() throws SQLException {
        Connection con = Conexion.conexion();
        List<RestauranteDTO> listaRestaurantes = new ArrayList<>();
        Optional<List<RestauranteDTO>> listaRestaurantesOptional = Optional.empty();
        String stringQuery = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                RestauranteDTO restaurante = new RestauranteDTO(
                        resultado.getString("nombre"),
                        resultado.getString("direccion"),
                        resultado.getString("hora_apertura"),
                        resultado.getString("hora_cierre"),
                        resultado.getString("url"),
                        resultado.getString("descripcion")
                );
                listaRestaurantes.add(restaurante);
            }
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        if (!listaRestaurantes.isEmpty()){
            listaRestaurantesOptional = Optional.of(listaRestaurantes);
        }
        return listaRestaurantesOptional;
    }

    @Override
    public Optional<List<RestauranteDTO>> getRestaurantes(String tipo) throws SQLException {
        Connection con = Conexion.conexion();
        List<RestauranteDTO> listaRestaurantes = new ArrayList<>();
        Optional<List<RestauranteDTO>> listaRestaurantesOptional = Optional.empty();

        String stringQuery = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE "+
                "INNER JOIN RESTAURANTE_PREPARA_COMIDA ON RESTAURANTE.idRESTAURANTE = RESTAURANTE_PREPARA_COMIDA.RESTAURANTE_id "+
                "WHERE COMIDA_id IN (SELECT idCOMIDA FROM COMIDA WHERE tipo LIKE ?)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setString(1,tipo);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                RestauranteDTO restaurante = new RestauranteDTO(
                        resultado.getString("nombre"),
                        resultado.getString("direccion"),
                        resultado.getString("hora_apertura"),
                        resultado.getString("hora_cierre"),
                        resultado.getString("url"),
                        resultado.getString("descripcion")
                );
                listaRestaurantes.add(restaurante);
            }
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        if (!listaRestaurantes.isEmpty()){
            listaRestaurantesOptional = Optional.of(listaRestaurantes);
        }
        return listaRestaurantesOptional;
    }
}
