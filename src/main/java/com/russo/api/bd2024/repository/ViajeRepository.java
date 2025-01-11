package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.ViajeDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ViajeRepository implements IViajeRepository{

    private final JdbcTemplate jdbcTemplate;

    public ViajeRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //Defino el mapeo del DTO con el resultado de la query
    private RowMapper<ViajeDTO> viajeDTORowMapper = ((rs, rowNum) -> new ViajeDTO(
            rs.getInt("id"),
            rs.getString("transporte"),
            rs.getString("inicio"),
            rs.getString("fin"),
            rs.getString("destino"),
            rs.getString("evento")
    ));

    @Override
    public Optional<List<ViajeDTO>> getViajesPorEvento(String tipoEvento) {
        if (tipoEvento.isEmpty()){
            String sql = "CALL obtener_viajes_tipo_evento(NULL)";
            return Optional.of(jdbcTemplate.query(sql, viajeDTORowMapper));
        }else {
            String sql = "CALL obtener_viajes_tipo_evento(?)";
            return Optional.of(jdbcTemplate.query(sql, viajeDTORowMapper,tipoEvento));
        }
    }

}
