package Ejercicios_11_02;

import peval2acda2223.GMethods;

import java.util.Scanner;

public class Ej_01Main {

    final static String DBEmple = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Objects_Files\\EMPLEDEP.yap";

    public static void main(String[] args) {
        byte option = 0;
        //boolean loop = true;
        while(option != 5) {
            try {
                option = GMethods.keyBByte("Menu de eleccion:\n 1: Insertar registros\n 2: Eliminar registros\n 3: Actualizar registros\n 4: Salir");
                if(option < 1 || option > 4) {
                    GMethods.printError("Elige una opcion valida");
                }
                GMethods.printDiv();
                switch (option){
                    case 1:
                        //TODO Insercion registros clase
                        break;
                    case 2:
                        //TODO Eliminacion registros
                        break;
                    case 3:
                        //TODO Actualizacion registros
                        break;
                    case 4:
                        System.exit(0);
                        break;
                }
            }
            //Catch all the possible exceptions
            catch (Exception e) {
                GMethods.printError("Error durante la ejecucion del programa");
                e.printStackTrace();
            }
        }
    }
}
