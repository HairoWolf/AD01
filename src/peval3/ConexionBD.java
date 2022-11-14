package peval3;

import java.sql.*;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Clase para crear la conexion a la base de datos
 */
//TODO Documentar
public class ConexionBD {

    private Statement myStatement;

    public ConexionBD() {
        crearConexion();
    }

    public void crearConexion() {
        try {
            //cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //establecemos la conexion con la BD
            com.mysql.jdbc.Connection conexion = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
            myStatement = (Statement) conexion.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //public  ResultSet
}