package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.repository.ViajeRepository;
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
public class ViajeController {

    @Autowired
    private ViajeRepository repository;

    @GetMapping("/Viaje")
    ResponseEntity<List<Map<String,Object>>> getViajesTipoEvento(@RequestParam(required = false) String tipoEvento) throws SQLException {
        Optional<List<Map<String,Object>>> lista = repository.getViajesPorEvento(tipoEvento);
        if (lista.isPresent()){
            return ResponseEntity.ok(lista.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
