package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ActividadDTO;
import com.russo.api.bd2024.repository.IActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    IActividadRepository repository;

    @Override
    public Optional<List<ActividadDTO>> getActividadPorLugar(String lugar) {
        try {
            return repository.getActividadPorLugar(lugar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
