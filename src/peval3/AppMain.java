package peval3;

import static peval3.AlterDB.*;
import static peval3.QuerysDB.queryDelay;
import static peval3.QuerysDB.specificQuery;

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
        String PATH = "D:\\peval3acda2223\\biblioteca.neo";
        String DBNAME = "biblioteca";
        String DBUSER = "root";

        /**
         * Byte option to select in the menu
         */
        byte option = 0;

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
                        new DBConnection(PATH, DBNAME, DBUSER);
                        break;
                    case 2:
                        newBook(PATH);
                        break;
                    case 3:
                        deleteUser(PATH);
                        break;
                    case 4:
                        modifyLoan(PATH);
                        break;
                    case 5:
                        queryDelay(PATH);
                        break;
                    case 6:
                        specificQuery(PATH);
                        break;
                    case 7:
                        //Falta implementacion por falta de tiempo
                        GMethods.printError("En obras disculpe las molestias");
                        GMethods.printDiv();
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
