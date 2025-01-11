package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.ViajeDTO;

import java.util.List;
import java.util.Optional;

public interface IViajeRepository {
    public Optional<List<ViajeDTO>> getViajesPorEvento(String tipoEvento);
}
