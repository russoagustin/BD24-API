package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Optional<UsuarioDTO> findByDNI(Integer dni);
    List<UsuarioDTO> getUsuarios();
}
