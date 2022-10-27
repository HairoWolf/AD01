package Actividades_19_10;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Ej_02 {
	
	public static void main(String[] args) {
		try{
			 //cargar el driver
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //establecemos la conexion con la BD
			 Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbtest","root","");
	
			 //preparamos la consulta
			 Statement sentencia = (Statement) conexion.createStatement();
			 String sql = "SELECT * FROM ej_02";
			 ResultSet resul = sentencia.executeQuery(sql);
	
			 //Recorremos el resultado para visualizar cada fila
			 //Se hace un buclee mientras haya regisros y se van mostrando
			 while (resul.next()){
			 System.out.printf("%d, %s, %s %n",
			 resul.getInt(1),
			 resul.getString(2),
			 resul.getString(3));
			 }
	
			 resul.close(); //Cerrar ResultSet
			 sentencia.close(); //Cerrar Statement
			 conexion.close(); //Cerrar conexión
	
		}
		catch (ClassNotFoundException cn){
				 cn.printStackTrace();
		}
		catch (SQLException e){
				 e.printStackTrace();
		}
	}
}
