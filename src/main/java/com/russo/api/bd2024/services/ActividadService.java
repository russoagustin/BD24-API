package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ActividadDTO;
import com.russo.api.bd2024.repository.IActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    IActividadRepository repository;

    @Override
    public List<ActividadDTO> getActividadPorLugar(String lugar) {
        return repository.getActividadPorLugar(lugar);
    }
}
