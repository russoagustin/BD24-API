package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.dto.ViajeDTO;
import com.russo.api.bd2024.services.IViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class ViajeController {

    @Autowired
    IViajeService service;

    @GetMapping("/Viaje")
    public ResponseEntity<List<ViajeDTO>> getViajesTipoEvento(@RequestParam(required = false) String tipoEvento) {
        Optional<List<ViajeDTO>> lista;
        try {
            lista = service.getViajesPorEvento(tipoEvento);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return lista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
