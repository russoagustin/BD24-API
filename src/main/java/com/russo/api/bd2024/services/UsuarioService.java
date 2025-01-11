package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.UsuarioDTO;
import com.russo.api.bd2024.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public Optional<UsuarioDTO> findByDNI(Integer dni) {
        return repository.findByDNI(dni);
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        return repository.getUsuarios();
    }
}
