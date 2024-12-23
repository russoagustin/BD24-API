package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    public Optional<UsuarioDTO> findByDNI(Integer id) throws SQLException;

    public Optional<List<UsuarioDTO>> getUsuarios() throws SQLException;
}
