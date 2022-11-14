package peval3;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Aplicacion para la gestion de una base de datos Neodatis
 */

public class AppMain {

    public static void main(String[] args) {
        byte option = 0;
        boolean bdCreada = false;

        //Menu de eleccion
        while(option != 8) {
            GMethods.println("Menu de eleccion:\n 1: Crear BD\n 2: Alta de libros\n"+
                    " 3: Bajas de usuarios\n 4: Modificaciones de préstamos\n 5: Préstamos de un usuario que se hayan entregado con retraso\n" +
                    " 6: Libros de un género y precio especificado\n 7: Préstamos realizados en una provincia determinada en un período de tiempo\n" +
                    " 8: Salir de la aplicacion");
            GMethods.printDiv();
            try {
                option = GMethods.keyBByte("Introduce la opcion");
                if (option < 1 || option > 8) {
                    GMethods.printError("Elige una opcion valida");
                }
                GMethods.printDiv();
                switch (option){
                    case 1:
                        //TODO crear el .neo y almacenar en el toda la informacion (Libros, Usuarios y Prestamos)
                        bdCreada = true;
                        break;
                    case 2:
                        if(bdCreada){
                            //TODO Alta de libros
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 3:
                        if(bdCreada){
                            //TODO Bajas de usuarios
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 4:
                        if(bdCreada){
                            //TODO Modificaciones de préstamos
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 5:
                        if(bdCreada){
                            //TODO Préstamos de un usuario que se hayan entregado con retraso
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 6:
                        if(bdCreada){
                            //TODO Libros de un género y precio especificado
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 7:
                        if(bdCreada){
                            //TODO Préstamos realizados en una provincia determinada en un período de tiempo
                        }
                        else{
                            GMethods.printError("La base de datos no ha sido creada aun");
                        }
                        break;
                    case 8:
                        GMethods.println("Hasta luego!!!");
                        System.exit(0);
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
