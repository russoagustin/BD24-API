package com.russo.api.bd2024.controllers;
import com.russo.api.bd2024.dto.UsuarioDTO;
import com.russo.api.bd2024.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class UsuariosController {

    @Autowired
    UsuarioRepository repositorio;

    @GetMapping("/usuario/{id}")
    ResponseEntity<UsuarioDTO> findUsuario(@PathVariable Integer id) throws SQLException {
        Optional<UsuarioDTO> usuarioOptional = repositorio.findById(id);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/")
    ResponseEntity<List<UsuarioDTO>> getUsuarios() throws SQLException {
        Optional<List<UsuarioDTO>> listaUsuariosOptional = repositorio.getUsuarios();
        return listaUsuariosOptional.isPresent() ?
                ResponseEntity.ok(listaUsuariosOptional.get()) : ResponseEntity.notFound().build();
    }
}
