package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.RestauranteDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RestauranteRepository implements IRestauranteRepository {

    private final JdbcTemplate jdbcTemplate;

    public RestauranteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }

    public RowMapper<RestauranteDTO> restauranteDTORowMapper = ((rs, rowNum) -> new RestauranteDTO(
            rs.getString("nombre"),
            rs.getString("direccion"),
            rs.getString("hora_apertura"),
            rs.getString("hora_cierre"),
            rs.getString("url"),
            rs.getString("descripcion")
    ));

    @Override
    public List<RestauranteDTO> getRestaurantes(){
        String sql = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE";
        return jdbcTemplate.query(sql,restauranteDTORowMapper);
    }

    @Override
    public List<RestauranteDTO> getRestaurantes(String tipo) {
        String stringQuery = "SELECT * FROM RESTAURANTE " +
                "INNER JOIN MULTIMEDIA_RESTAURANTE ON RESTAURANTE.idRESTAURANTE=MULTIMEDIA_RESTAURANTE.idMULTIMEDIA_RESTAURANTE "+
                "INNER JOIN RESTAURANTE_PREPARA_COMIDA ON RESTAURANTE.idRESTAURANTE = RESTAURANTE_PREPARA_COMIDA.RESTAURANTE_id "+
                "WHERE COMIDA_id IN (SELECT idCOMIDA FROM COMIDA WHERE tipo LIKE ?)";
        return jdbcTemplate.query(stringQuery,restauranteDTORowMapper,tipo);
    }
}
