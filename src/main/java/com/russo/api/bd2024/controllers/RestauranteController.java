package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.dto.RestauranteDTO;
import com.russo.api.bd2024.services.IRestauranteService;
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
public class RestauranteController {

    @Autowired
    IRestauranteService service;

    @GetMapping("/Restaurante")
    public ResponseEntity<List<RestauranteDTO>> findRestaurantes(@RequestParam(required = false) String tipo) {
        try {
            Optional<List<RestauranteDTO>> restaurantesOptional;
            if (tipo == null) {
                restaurantesOptional = service.getRestaurantes();
            } else {
                restaurantesOptional = service.getRestaurantes(tipo);
            }
            return restaurantesOptional
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
