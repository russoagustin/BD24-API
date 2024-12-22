package com.russo.api.bd2024.repository;

import com.russo.api.bd2024.dto.UsuarioDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class UsuarioRepository {

    public Optional<UsuarioDTO> findById(Integer id) throws SQLException {
        Connection con = Conexion.conexion();
        Optional<UsuarioDTO> usuarioOptional = Optional.empty();
        String stringQuery = "CALL obtener_usuario(?)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setInt(1,id);
            ResultSet resultado = st.executeQuery();
            if (resultado.next()) {
                UsuarioDTO usuario = new UsuarioDTO(
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("email"),
                        resultado.getString("user_name"),
                        resultado.getString("ocupacion")
                );
                usuarioOptional = Optional.of(usuario);
            }
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return usuarioOptional;
    }

    public Optional<List<UsuarioDTO>> getUsuarios() throws SQLException {
        Connection con = Conexion.conexion();
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();
        Optional<List<UsuarioDTO>> listaUsuariosOptional;
        String stringQuery = "CALL obtener_usuario(NULL)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                UsuarioDTO usuario = new UsuarioDTO(
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("email"),
                        resultado.getString("user_name"),
                        resultado.getString("ocupacion")
                );
                listaUsuarios.add(usuario);
            }

            st.close();

            if (listaUsuarios.isEmpty()){
                listaUsuariosOptional = Optional.empty();
            }else {
                listaUsuariosOptional = Optional.of(listaUsuarios);
            }
        } finally {
            Conexion.closeConnection(con);
        }


        return listaUsuariosOptional;
    }



}
