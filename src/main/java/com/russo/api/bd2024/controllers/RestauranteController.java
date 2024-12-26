package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.dto.RestauranteDTO;
import com.russo.api.bd2024.repository.IRestauranteRepository;
import com.russo.api.bd2024.repository.RestauranteRepository;
import com.russo.api.bd2024.services.IRestauranteService;
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
    private IRestauranteService service;

    @GetMapping("/Restaurante")
    ResponseEntity<List<RestauranteDTO>> findRestaurantes(@RequestParam(required = false) String tipo) throws SQLException {
        Optional<List<RestauranteDTO>> restaurantesOptional;
        if (tipo == null){
            restaurantesOptional = service.getRestaurantes();
        }else {
            restaurantesOptional = service.getRestaurantes(tipo);
        }
        return restaurantesOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
