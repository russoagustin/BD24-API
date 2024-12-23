package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.UsuarioDTO;
import com.russo.api.bd2024.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<UsuarioDTO> findByDNI(Integer dni) throws SQLException {
        return repository.findByDNI(dni);
    }

    public Optional<List<UsuarioDTO>> getUsuarios() throws SQLException {
        return repository.getUsuarios();
    }
}
