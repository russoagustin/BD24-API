package com.russo.api.bd2024.controllers;

import com.russo.api.bd2024.repository.ActividadRepository;
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

    ActividadRepository repository = ActividadRepository.getInstance();

    @GetMapping("/actividad")
    ResponseEntity<List<Map<String,Object>>> obtenerViajes(@RequestParam(required = false) String lugar) throws SQLException {
        Optional<List<Map<String,Object>>> listaOptional = repository.getActividadPorLugar(lugar);
        return listaOptional.isPresent() ?
                ResponseEntity.ok(listaOptional.get()) : ResponseEntity.notFound().build();
    }
}
