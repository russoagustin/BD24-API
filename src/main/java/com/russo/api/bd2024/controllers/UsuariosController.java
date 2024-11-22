package com.russo.api.bd2024.controllers;
import com.russo.api.bd2024.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class UsuariosController {

    UsuarioRepository repositorio = UsuarioRepository.getInstance();

    @GetMapping("/usuario/{id}")
    ResponseEntity<Map<String,Object>> getUsuario(@PathVariable Integer id) throws SQLException {
        Optional<Map<String,Object>> usuarioOptional = repositorio.getUsuario(id);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
