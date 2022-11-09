package Ejercicios_11_07;

import Ejercicios_11_07.Clases.Jugadores;
import Ejercicios_11_07.Clases.Paises;
import Ejercicios_11_07.Clases.Visualizar;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import peval2acda2223.GMethods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        byte option = 0;
        boolean almacenado = false;

        Path archivo = Paths.get("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\equipos.neo");
        ODB odb = ODBFactory.open("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\equipos.neo"); //Abrir BD

        while(option != 6) {
            System.out.println("Menu de eleccion:\n 1: Crear base de datos\n 2: Imprimir base de datos" +
                    "\n 3: Eliminar objeto\n 4: Crear objeto\n 5: Modificar objetos\n 6: Salir");
            System.out.println("================================================");
            try {
                option = GMethods.keyBByte("Introduce la opcion");
                if (option < 1 || option > 6) {
                    System.out.println("Elige una opcion valida");
                }
                System.out.println("================================================");
                switch (option) {
                    case 1:
                        if(Files.exists(archivo)){
                            System.out.println("La base de datos ya existe");
                        }
                        else {
                            Paises p1 = new Paises(1, "España");
                            Paises p2 = new Paises(2, "Francia");

                            Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, p1);
                            Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, p1);
                            Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15, p2);
                            Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14, p2);

                            //Almacenamos objetos
                            odb.store(j1);
                            odb.store(j2);
                            odb.store(j3);
                            odb.store(j4);
                            almacenado = true;
                            System.out.println("Base de datos creada....");
                        }
                        System.out.println("================================================");
                        break;

                    case 2:
                        if (almacenado || Files.exists(archivo)) {
                            Visualizar v = new Visualizar(odb);
                            v.verDatos();
                        } else {
                            System.out.println("La base de datos aun no esta creada");
                            System.out.println("================================================");
                        }
                        break;
                    case 6:
                        odb.close();
                        System.exit(0);
                        break;
                }
            }
            //Catch all the possible exceptions
            catch (Exception e) {
                System.out.println("Error durante la ejecucion del programa");
                odb.close();
                e.printStackTrace();
            }
        }
        odb.close();
    }
}
