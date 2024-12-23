package com.russo.api.bd2024.controllers;
import com.russo.api.bd2024.dto.UsuarioDTO;
import com.russo.api.bd2024.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class UsuariosController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuario/{id}")
    ResponseEntity<UsuarioDTO> findUsuario(@PathVariable Integer id)  {
        Optional<UsuarioDTO> usuarioOptional;
        try {
            usuarioOptional = service.findByDNI(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/")
    ResponseEntity<List<UsuarioDTO>> getUsuarios()  {
        Optional<List<UsuarioDTO>> listaUsuariosOptional;
        try {
            listaUsuariosOptional = service.getUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuariosOptional.isPresent() ?
                ResponseEntity.ok(listaUsuariosOptional.get()) : ResponseEntity.notFound().build();
    }
}
