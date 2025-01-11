package com.russo.api.bd2024.controllers;
import com.russo.api.bd2024.dto.UsuarioDTO;
import com.russo.api.bd2024.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("BD2024")
public class UsuariosController {

    @Autowired
    IUsuarioService service;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> findUsuario(@PathVariable Integer id)  {
        Optional<UsuarioDTO> usuarioOptional = service.findByDNI(id);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios()  {
        List<UsuarioDTO> listaUsuarios = service.getUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }
}
