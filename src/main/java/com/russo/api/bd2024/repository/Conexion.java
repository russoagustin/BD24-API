package com.russo.api.bd2024.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase cuenta con los métodos para conectarse a la base de datos.
public class Conexion {
    public static Connection conexion(){
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfi_2?useSSL=false&serverTimezone=UTC","root","65400");
            return con;
        }catch (SQLException e){
            System.err.println("No se pudo hacer la conexión a la base de datos" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(Connection con){
        try {
            con.close() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
