package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ActividadDTO;
import com.russo.api.bd2024.repository.IActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    IActividadRepository repository;

    @Override
    public Optional<List<ActividadDTO>> getActividadPorLugar(String lugar) throws SQLException{
        return repository.getActividadPorLugar(lugar);
    }
}
