package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.ActividadDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ActividadRepository implements IActividadRepository {

    @Override
    public Optional<List<ActividadDTO>> getActividadPorLugar(String lugar) throws SQLException {
        Connection con = Conexion.conexion();
        List<ActividadDTO> listaActividad = new ArrayList<>();
        Optional<List<ActividadDTO>> listaActividadOptional = Optional.empty();
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
                ActividadDTO actividad = new ActividadDTO(
                        resultado.getLong("idActividad"),
                        resultado.getString("nombre"),
                        resultado.getString("descripcion"),
                        resultado.getString("hora_inicio"),
                        resultado.getString("lugar"),
                        resultado.getString("url")
                );
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
