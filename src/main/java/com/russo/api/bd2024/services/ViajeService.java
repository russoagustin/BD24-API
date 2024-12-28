package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ViajeDTO;
import com.russo.api.bd2024.repository.IViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ViajeService implements IViajeService {

    @Autowired
    private IViajeRepository repository;

    public Optional<List<ViajeDTO>> getViajesPorEvento(String tipoEvento) {
        try {
            return repository.getViajesPorEvento(tipoEvento);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
