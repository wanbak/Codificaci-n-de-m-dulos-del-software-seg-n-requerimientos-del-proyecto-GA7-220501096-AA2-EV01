/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conecxionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConecxionJDBC {

    public static void main(String[] args) {

        // Configuración de la conexión
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3307/conecxionjdbc";
        Connection conexion = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConecxionJDBC.class.getName()).log(Level.SEVERE, "Error al cargar el driver JDBC", ex);
        }

        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
 
            // Insertar un nuevo usuario
            statement.executeUpdate("INSERT INTO USUARIOS(USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES('TEST', 'TEST@SENA.COM', 'TESTSENA123')");

            // Actualizar un usuario existente
            statement.executeUpdate("UPDATE USUARIOS SET USER_NAME = 'UPDATED' WHERE USER_NAME = 'TEST'");

            // Eliminar un usuario existente
            statement.executeUpdate("DELETE FROM USUARIOS WHERE USER_NAME = 'UPDATED'");

            // Realizar una consulta y procesar los resultados
            rs = statement.executeQuery("SELECT * FROM USUARIOS");
            while (rs.next()) {
                System.out.println(rs.getInt("user_ID") + " : " + rs.getString("user_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConecxionJDBC.class.getName()).log(Level.SEVERE, "Error en la conexión o ejecución de la consulta", ex);
        } finally {
            // Cerrar los recursos en el bloque finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConecxionJDBC.class.getName()).log(Level.SEVERE, "Error al cerrar los recursos", ex);
            }
        }
    }
}

