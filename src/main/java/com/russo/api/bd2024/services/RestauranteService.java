package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.RestauranteDTO;
import com.russo.api.bd2024.repository.IRestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService implements IRestauranteService{

    @Autowired
    IRestauranteRepository repository;

    @Override
    public Optional<List<RestauranteDTO>> getRestaurantes() {
        try {
            return repository.getRestaurantes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<RestauranteDTO>> getRestaurantes(String tipo) {
        try {
            return repository.getRestaurantes(tipo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
