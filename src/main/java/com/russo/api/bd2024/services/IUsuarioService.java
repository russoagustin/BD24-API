package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Optional<UsuarioDTO> findByDNI(Integer dni) throws SQLException;
    Optional<List<UsuarioDTO>> getUsuarios() throws SQLException;
}