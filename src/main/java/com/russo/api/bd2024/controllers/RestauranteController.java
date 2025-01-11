package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.dto.RestauranteDTO;
import com.russo.api.bd2024.services.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class RestauranteController {

    @Autowired
    IRestauranteService service;

    @GetMapping("/Restaurante")
    public ResponseEntity<List<RestauranteDTO>> findRestaurantes(@RequestParam(required = false) String tipo) {
        return ResponseEntity.ok(service.getRestaurantes(tipo));
    }

}
