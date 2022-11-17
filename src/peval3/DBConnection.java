package peval3;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import peval3.Clases.Libros;
import peval3.Clases.Prestamos;
import peval3.Clases.Usuario;

import java.sql.*;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Clase para crear la conexion a la base de datos y la creacion de la BD en un .neo
 */

public class DBConnection {

    //Campos de clase
    private Connection connection;
    private Statement myStatement;
    private ODB odb;

    /**
     * Constructor de la clase conexion
     * @param PATH ruta para la creacion del fichero .neo
     * @param DBNAME Nombre de la base de datos
     * @param DBUSER Nombre del usuario de la base de datos
     */
    public DBConnection(String PATH, String DBNAME, String DBUSER) {
        createConnection(DBNAME, DBUSER);
        createNeoDB(PATH);
        GMethods.println("---Base de Datos Neodatis Creada---");
        GMethods.printDiv();
        closeConnection();
    }

    /**
     * Metodo para crear la conexion a la BD
     * @param DBNAME Nombre de la BD
     * @param DBUSER Usuario de la BD
     */
    public void createConnection(String DBNAME, String DBUSER) {
        try {
            //cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            //establecemos la conexion con la BD
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/" + DBNAME, DBUSER, "");
            myStatement = (Statement) connection.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para la creacion de la BD en el .neo
     * @param PATH ruta para la creacion del .neo
     */
    public void createNeoDB(String PATH){
        odb = ODBFactory.open(PATH);
        try {
            setBooksData(odb);
            setUsersData(odb);
            setLoanData(odb);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        odb.close();
    }

    /**
     * Metodo que extrae la informacion de los libros de la BD y la introduce en el .neo
     * @param odb Objeto tipo ODB sobre el que se va a almacenar la informacion
     * @throws SQLException
     */
    public void setBooksData(ODB odb) throws SQLException {
        ResultSet resultBooks = myStatement.executeQuery("SELECT * FROM libros");
        while (resultBooks.next()){
            odb.store(new Libros(resultBooks.getInt(1), resultBooks.getInt(7), resultBooks.getInt(8),
                    resultBooks.getString(2), resultBooks.getString(3), resultBooks.getString(4),
                    resultBooks.getString(5), resultBooks.getString(6), Double.valueOf(resultBooks.getInt(9))));
        }
        resultBooks.close();
        odb.commit();
    }

    /**
     * Metodo que extrae la informacion de los usuarios de la BD y la introduce en el .neo
     * @param odb Objeto tipo ODB sobre el que se va a almacenar la informacion
     * @throws SQLException
     */
    public void setUsersData(ODB odb) throws SQLException {
        ResultSet resultUsers = myStatement.executeQuery("SELECT * FROM usuario");
        while (resultUsers.next()){
            odb.store(new Usuario(resultUsers.getInt(1), resultUsers.getString(2),
                    resultUsers.getString(3), resultUsers.getString(4), resultUsers.getString(5),
                    resultUsers.getString(6), resultUsers.getString(7), resultUsers.getString(8)));
        }
        resultUsers.close();
        odb.commit();
    }

    /**
     * Metodo que extrae la informacion de los prestamos de la BD y la introduce en el .neo
     * @param odb Objeto tipo ODB sobre el que se va a almacenar la informacion
     * @throws SQLException
     */
    public void setLoanData(ODB odb) throws SQLException {
        ResultSet resultLoan = myStatement.executeQuery("SELECT * FROM prestamos");
        while (resultLoan.next()){
            odb.store(new Prestamos(resultLoan.getInt(1),
                    (Libros) odb.getObjects(new CriteriaQuery(Libros.class, Where.equal("codLibro", resultLoan.getInt(2)))).getFirst(),
                    (Usuario) odb.getObjects(new CriteriaQuery(Usuario.class, Where.equal("codUsuario", resultLoan.getInt(3)))).getFirst(),
                    resultLoan.getString(4), resultLoan.getString(5), resultLoan.getString(6)));
        }
        resultLoan.close();
        odb.commit();
    }

    /**
     * Metodo que finaliza la conexion con la BD
     */
    public void closeConnection() {
        try {
            connection.close();
            myStatement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}