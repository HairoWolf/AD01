package peval3;

import java.sql.ResultSet;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Aplicacion para la gestion de una base de datos Neodatis
 */

public class AppMain {

    public static void main(String[] args) {

        /**
         * Constants to define the parameters of the DB
         */
        String PATH = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\PEVAL3\\Fichero_neo\\biblioteca.neo";
        String DBNAME = "biblioteca";
        String DBUSER = "root";

        /**
         * Byte option to select in the menu
         */
        byte option = 0;
        boolean createdDB = false;

        //Menu de eleccion
        while(option != 8) {
            GMethods.println("Menu de eleccion:\n 1: Crear BD\n 2: Alta de libros\n"+
                    " 3: Bajas de usuarios\n 4: Modificaciones de préstamos\n 5: Préstamos de un usuario que se hayan entregado con retraso\n" +
                    " 6: Libros de un género y precio especificado\n 7: Préstamos realizados en una provincia determinada en un período de tiempo\n" +
                    " 8: Salir de la aplicacion");
            GMethods.printDiv();
            try {
                option = GMethods.keyBByte("Introduce la opcion");
                GMethods.printDiv();
                switch (option){
                    case 1:
                        //TODO comentar la clase
                        new DBConnection(PATH, DBNAME, DBUSER);
                        break;
                    case 2:
                        //TODO Alta de libros
                        break;
                    case 3:
                        //TODO Bajas de usuarios
                        break;
                    case 4:
                        //TODO Modificaciones de préstamos
                        break;
                    case 5:
                        //TODO Préstamos de un usuario que se hayan entregado con retraso
                        break;
                    case 6:
                        //TODO Libros de un género y precio especificado
                        break;
                    case 7:
                        //TODO Préstamos realizados en una provincia determinada en un período de tiempo
                        break;
                    case 8:
                        GMethods.println("Hasta luego!!!");
                        System.exit(0);
                        break;
                    default:
                        GMethods.printError("Elige una opcion valida");
                        break;
                }
            }
            //Catch all the possible exceptions
			catch(Exception e) {
                GMethods.printError("Error durante la ejecucion del programa");
                e.printStackTrace();
            }
        }
    }
}
