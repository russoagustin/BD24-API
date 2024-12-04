package com.russo.api.bd2024.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ViajeRepository {
    private static ViajeRepository instance;
    private ViajeRepository(){
    }

    public static ViajeRepository getInstance(){
        if(instance == null){
            instance = new ViajeRepository();
        }
        return instance;
    }

    public Optional<List<Map<String,Object>>> getViajesPorEvento(String tipoEvento) throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaEventos = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaEventoOptional = Optional.empty();
        String stringQuery = "CALL obtener_viajes_tipo_evento(?)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setString(1,tipoEvento);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> viaje = new HashMap<>();
                viaje.put("id",resultado.getString("id"));
                viaje.put("transporte",resultado.getString("transporte"));
                viaje.put("inicio",resultado.getString("inicio"));
                viaje.put("fin",resultado.getString("fin"));
                viaje.put("id_destino",resultado.getString("idDESTINO"));
                viaje.put("nombre_evento",resultado.getString("nombre_evento"));
                listaEventos.add(viaje);
            }
            listaEventoOptional = Optional.of(listaEventos);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaEventoOptional;
    }
}
