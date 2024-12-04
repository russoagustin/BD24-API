package com.russo.api.bd2024.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ActividadRepository {
    private static ActividadRepository instance;
    private ActividadRepository(){
    }
    public static ActividadRepository getInstance(){
        if (instance == null){
            instance = new ActividadRepository();
        }
        return instance;
    }

    public Optional<List<Map<String,Object>>> getActividadPorLugar(String lugar) throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaActividad = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaActividadOptional = Optional.empty();
        String stringQuery;
        if(lugar==null){
            stringQuery = "CALL actividad_por_lugar_api(NULL)";
        }else {
            stringQuery = "CALL actividad_por_lugar_api(?)";
        }
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            if (lugar !=null) {
                st.setString(1, lugar);
            }
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> actividad = new HashMap<>();
                actividad.put("id",resultado.getString("idActividad"));
                actividad.put("actividad",resultado.getString("nombre"));
                actividad.put("descripcion",resultado.getString("descripcion"));
                actividad.put("hora",resultado.getString("hora_inicio"));
                actividad.put("lugar",resultado.getString("lugar"));
                actividad.put("url",resultado.getString("url"));
                listaActividad.add(actividad);
            }
            listaActividadOptional = Optional.of(listaActividad);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaActividadOptional;
    }
}
