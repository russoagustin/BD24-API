package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.repository.RestauranteRepository;
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

    private RestauranteRepository repository = RestauranteRepository.getInstance();
    @GetMapping("/Restaurante")
    ResponseEntity<List<Map<String, Object>>> findRestaurantes(@RequestParam String tipo) throws SQLException {
        Optional<List<Map<String,Object>>> restaurantesOptional = repository.getUsuarios(tipo);
        return restaurantesOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
