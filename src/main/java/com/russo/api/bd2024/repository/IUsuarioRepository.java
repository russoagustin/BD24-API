package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Optional<UsuarioDTO> findByDNI(Integer id);

    List<UsuarioDTO> getUsuarios();
}
