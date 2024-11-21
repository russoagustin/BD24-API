package com.russo.api.bd2024.repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Map<String,Object>> getUsuario(Integer id) throws SQLException {
        Connection con = Conexion.conexion();
        Optional<Map<String,Object>> usuarioOptional = Optional.empty();
        String stringQuery = "SELECT nombre, apellido,email, user_name,dni FROM PERSONA " +
                "INNER JOIN USUARIO ON PERSONA.idPERSONA = USUARIO.PERSONA_idPERSONA " +
                "INNER JOIN VIAJANTE ON PERSONA.idPERSONA = VIAJANTE.PERSONA_idPERSONA " +
                "WHERE dni = ?";
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
                usuario.put("dni",resultado.getString("dni"));
                usuarioOptional = Optional.of(usuario);
            }
            st.close();
        } finally {
            Conexion.closeConnection(con);
        }
        return usuarioOptional;
    }



}
