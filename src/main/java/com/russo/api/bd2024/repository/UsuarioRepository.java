package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.UsuarioDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepository implements IUsuarioRepository{

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<UsuarioDTO> usuarioDTORowMapper = ((rs, rowNum) -> new UsuarioDTO(
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("email"),
            rs.getString("user_name"),
            rs.getString("ocupacion")
    ));
    @Override
    public Optional<UsuarioDTO> findByDNI(Integer id) {
        String sql = "CALL obtener_usuario(?)";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql,usuarioDTORowMapper,id));
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        String sql = "CALL obtener_usuario(NULL)";
        return jdbcTemplate.query(sql,usuarioDTORowMapper);
    }



}
