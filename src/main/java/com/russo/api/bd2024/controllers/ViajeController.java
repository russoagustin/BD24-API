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
        Optional<List<ViajeDTO>> lista = service.getViajesPorEvento(tipoEvento);
        if (tipoEvento.isEmpty())
            return ResponseEntity.ok(lista.get()); //si se pide la lista completa siempre devuelve 200O K

        return lista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); //cuando se pide un tipo de evento especifico el cual no está en la BD defuelve un 404 NOT FOUD
    }
}

