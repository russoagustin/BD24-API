package com.russo.api.bd2024.services;

import com.russo.api.bd2024.dto.ViajeDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IViajeService {
    Optional<List<ViajeDTO>> getViajesPorEvento(String tipoEvento);
}
