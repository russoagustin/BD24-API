package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.ViajeDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.*;

@Repository
public class ViajeRepository implements IViajeRepository{

    @Override
    public Optional<List<ViajeDTO>> getViajesPorEvento(String tipoEvento) throws SQLException {
        Connection con = Conexion.conexion();

        List<ViajeDTO> listaEventos = new ArrayList<>();
        Optional<List<ViajeDTO>> listaEventoOptional = Optional.empty();

        String stringQuery;
        if(tipoEvento.isEmpty()){
            stringQuery = "CALL obtener_viajes_tipo_evento(NULL)";
        }else {
            stringQuery = "CALL obtener_viajes_tipo_evento(?)";
        }
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            if (!tipoEvento.isEmpty()) {
                st.setString(1, tipoEvento);
            }

            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                ViajeDTO viaje = new ViajeDTO(
                        resultado.getInt("id"),
                        resultado.getString("transporte"),
                        resultado.getString("inicio"),
                        resultado.getString("fin"),
                        resultado.getString("destino"),
                        resultado.getString("evento")
                );
                listaEventos.add(viaje);
            }
            st.close();

        } finally {
            Conexion.closeConnection(con);
        }
        if (listaEventos.isEmpty()){
            return Optional.empty();
        }

        listaEventoOptional = Optional.of(listaEventos);
        return listaEventoOptional;
    }
}
