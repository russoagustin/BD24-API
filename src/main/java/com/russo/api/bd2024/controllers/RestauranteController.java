package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.repository.RestauranteRepository;
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
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @GetMapping("/Restaurante")
    ResponseEntity<List<Map<String, Object>>> findRestaurantes(@RequestParam(required = false) String tipo) throws SQLException {
        Optional<List<Map<String,Object>>> restaurantesOptional;
        if (tipo == null){
            restaurantesOptional = repository.getRestaurantes();
        }else {
            restaurantesOptional = repository.getRestaurantes(tipo);
        }
        return restaurantesOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
