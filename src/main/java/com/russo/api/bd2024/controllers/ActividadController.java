package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.dto.ActividadDTO;
import com.russo.api.bd2024.repository.ActividadRepository;
import com.russo.api.bd2024.services.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class ActividadController {

    @Autowired
    IActividadService service;

    @GetMapping("/actividad")
    ResponseEntity<List<ActividadDTO>> obtenerViajes(@RequestParam(required = false) String lugar) {
        Optional<List<ActividadDTO>> listaOptional = null;
        try {
            listaOptional = service.getActividadPorLugar(lugar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaOptional.isPresent() ?
                ResponseEntity.ok(listaOptional.get()) : ResponseEntity.notFound().build();
    }
}
