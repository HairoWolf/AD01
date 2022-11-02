package Actividades_10_19;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Ej_03 {
	
	private static int emp_no;
	private static String apellido;
	private static String profesion;
	private static int director;
	private static int salario;
	private static int comision;
	private static int dept_no;
	
	public static void main(String[] args) {
		try{
			 //cargar el driver
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //establecemos la conexion con la BD
			 Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbtest","root","");
	
			 for(int i = 0; i < 3; i++) {
				 emp_no = keyBInt("Introduce el numero de empleado");
				 apellido = keyBString("Introduce el apellido del empleado");
				 profesion = keyBString("Introduce la profesion del empleado");
				 director = keyBInt("Introduce el numero de director");
				 salario = keyBInt("Introduce el salario del empleado");
				 comision = keyBInt("Introduce la comisión de empleado");
				 dept_no = keyBInt("Introduce el numero de departamento del empleado");
				 
				 //preparamos la consulta
				 Statement sentencia = (Statement) conexion.createStatement();
				 
				 if(salario > 0 && !apellido.equals("") && !profesion.equals("")) {
					 dept_noVerify(sentencia);
				 }
				 else if (salario <= 0){
					 println("El salario es menor o igual a 0");
				 }
				 else if(apellido.equals("")){
					 println("El apellido es nulo");
				 }
				 else if(profesion.equals("")){
					 println("La profesion es nula");
				 }
				 sentencia.close(); //Cerrar Statement
				 conexion.close(); //Cerrar conexión
			 }
	
		}
		catch (ClassNotFoundException cn){
				 cn.printStackTrace();
		}
		catch (SQLException e){
				 e.printStackTrace();
		}
	}
	
	public static void dept_noVerify(Statement sentencia) {
		String sql = "SELECT * FROM empleados WHERE dept_no LIKE " + dept_no;
		try {
			ResultSet resul = sentencia.executeQuery(sql);
			if(!resul.next()) {
				println("El departamento no existe");
			}
			else {
				emp_noVerify(sentencia);
			}
			resul.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void emp_noVerify(Statement sentencia) {
		String sql = "SELECT * FROM empleados WHERE emp_no LIKE " + emp_no;
		try {
			ResultSet resul = sentencia.executeQuery(sql);
			if(!resul.next()) {
				dirVerify(sentencia);
			}
			else {
				println("El empleado ya existe");
			}
			resul.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void dirVerify(Statement sentencia) {
		String sql = "SELECT * FROM empleados WHERE emp_no LIKE " + director;
		try {
			ResultSet resul = sentencia.executeQuery(sql);
			if(!resul.next()) {
				println("El director no existe");
			}
			else {
				String sqlInsert = "INSERT INTO empleados VALUES (" + emp_no + ", '" + apellido + "', '" + profesion + "', " +
						director + ", " + salario + ", " + comision + ", " + dept_no + ", now())";
				System.out.println(sqlInsert);
				sentencia.executeUpdate(sqlInsert);
				println("Empleado insertado con exito");
			}
			resul.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt
	 */
	public static void println(String txt) {
		System.out.println(txt);
	}

	/**
	 * Method prints a split text
	 * 
	 * @param txt
	 */
	public static void printDiv() {
		System.out.println("------------------------------------------");
	}
	
	
	public static int keyBInt(String text) {
		println(text);
		Scanner keyb;
		keyb = new Scanner(System.in);
		return keyb.nextInt();
	}

	
	public static String keyBString(String text) {
		println(text);
		Scanner keyb;
		keyb = new Scanner(System.in);
		return keyb.nextLine();
	}
}
