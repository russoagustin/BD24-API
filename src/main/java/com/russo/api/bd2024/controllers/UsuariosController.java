package com.russo.api.bd2024.controllers;
import com.russo.api.bd2024.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class UsuariosController {

    UsuarioRepository repositorio = UsuarioRepository.getInstance();

    @GetMapping("/usuario/{id}")
    ResponseEntity<Map<String,Object>> findUsuario(@PathVariable Integer id) throws SQLException {
        Optional<Map<String,Object>> usuarioOptional = repositorio.findById(id);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/")
    ResponseEntity<List<Map<String,Object>>> getUsuarios() throws SQLException {
        Optional<List<Map<String,Object>>> listaUsuariosOptional = repositorio.getUsuarios();
        return listaUsuariosOptional.isPresent() ?
                ResponseEntity.ok(listaUsuariosOptional.get()) : ResponseEntity.notFound().build();
    }
}
