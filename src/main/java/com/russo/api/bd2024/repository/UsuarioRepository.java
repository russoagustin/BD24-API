package com.russo.api.bd2024.repository;

import java.sql.*;
import java.util.*;

public class UsuarioRepository {
    private static UsuarioRepository instance;
    private UsuarioRepository(){
    }

    public static UsuarioRepository getInstance(){
        if(instance == null){
            instance = new UsuarioRepository();
        }
        return instance;
    }

    public Optional<Map<String,Object>> findById(Integer id) throws SQLException {
        Connection con = Conexion.conexion();
        Optional<Map<String,Object>> usuarioOptional = Optional.empty();
        String stringQuery = "CALL obtener_usuario(?)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            st.setInt(1,id);
            ResultSet resultado = st.executeQuery();
            if (resultado.next()) {
                Map<String,Object> usuario = new HashMap<>();
                usuario.put("nombre",resultado.getString("nombre"));
                usuario.put("apellido",resultado.getString("apellido"));
                usuario.put("email",resultado.getString("email"));
                usuario.put("user_name",resultado.getString("user_name"));
                usuario.put("ocupacion",resultado.getString("ocupacion"));
                usuarioOptional = Optional.of(usuario);
            }
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return usuarioOptional;
    }

    public Optional<List<Map<String,Object>>> getUsuarios() throws SQLException {
        Connection con = Conexion.conexion();
        List<Map<String,Object>> listaUsuarios = new ArrayList<>();
        Optional<List<Map<String,Object>>> listaUsuariosOptional = Optional.empty();
        Optional<Map<String,Object>> usuarioOptional = Optional.empty();
        String stringQuery = "CALL obtener_usuario(NULL)";
        try {
            PreparedStatement st = con.prepareStatement(stringQuery);
            ResultSet resultado = st.executeQuery();
            while (resultado.next()) {
                Map<String,Object> usuario = new HashMap<>();
                usuario.put("nombre",resultado.getString("nombre"));
                usuario.put("apellido",resultado.getString("apellido"));
                usuario.put("email",resultado.getString("email"));
                usuario.put("user_name",resultado.getString("user_name"));
                usuario.put("ocupacion",resultado.getString("ocupacion"));
                listaUsuarios.add(usuario);
                usuarioOptional = Optional.of(usuario);
            }
            listaUsuariosOptional = Optional.of(listaUsuarios);
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return listaUsuariosOptional;
    }



}
