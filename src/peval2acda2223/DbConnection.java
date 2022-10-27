package peval2acda2223;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class DbConnection {
	
	private Connection conexion;
	private Statement sentencia ;

	DbConnection(){
		try{
			 Class.forName("com.mysql.jdbc.Driver");
	
			 conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/basketlite","root","");
			 sentencia = (Statement) conexion.createStatement();
		}
		catch (ClassNotFoundException cn){
			//TODO
			 cn.printStackTrace();
		}
		catch (SQLException e){
			//TODO
			 e.printStackTrace();
		}
	}
	
	/**
	 * Method that gets a string that contains sql query and prints it on the screen 
	 * @param query
	 * @throws SQLException 
	 */
	public void select(String query) throws SQLException {
		 ResultSet resul = sentencia.executeQuery(query);
		 if(!resul.next()) {
			 GMethods.printError("La ciudad no tiene ningun equipo");
			 resul.beforeFirst();
		 }
		 else {
			 resul.beforeFirst();
			 try {
				printResultColumns(resul);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 //sentencia.close();
		 resul.close();
	}
	
	/**
	 * Method to print every ResulSet
	 * @param resultSet
	 * @throws SQLException
	 * @throws IOException
	 */
	private static void printResultColumns(ResultSet resultSet) throws SQLException, IOException {
	    ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
	    int columnCount = rsmd.getColumnCount();

	    while (resultSet.next()) {
	        // you get a single result row in here, not the entire ResultSet
	        for (int i = 1; i <= columnCount; i++) {
	            switch (rsmd.getColumnType(i)) {
		            case Types.VARCHAR:
		            case Types.LONGVARCHAR:
		            case Types.CHAR:
		                System.out.print(resultSet.getString(i));
		                break;
		            case Types.DOUBLE:
		                System.out.print(resultSet.getDouble(i));
		                break;
		            case Types.INTEGER:
		                System.out.print(resultSet.getInt(i));
		                break;
		            case Types.DATE:
		                System.out.print(resultSet.getDate(i).toString());
		                break;
		            case Types.TIMESTAMP:
		                System.out.print(resultSet.getTimestamp(i).toString());
		                break;
		            case Types.BOOLEAN:
		                System.out.print(resultSet.getBoolean(i));
		                break;
		            case Types.DECIMAL:
		            case Types.NUMERIC:
		            case Types.BIGINT:
		                System.out.print(resultSet.getBigDecimal(i));
		                break;
		            /*default:
		            	System.out.println(rsmd.getColumnClassName(i));
		            	System.out.print(resultSet.getBigDecimal(i));
		            	break;*/
	            }
	            if(i != columnCount) {
		            System.out.print(", ");
	            }
	        }
	        GMethods.println("");
            GMethods.printDiv();
	    }
	}
	
	public void closeConnection() throws SQLException {
		sentencia.close();
		conexion.close();
	}
}
