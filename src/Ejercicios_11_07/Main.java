package Ejercicios_11_07;

import Ejercicios_11_07.Clases.Jugadores;
import Ejercicios_11_07.Clases.Paises;
import Ejercicios_11_07.Clases.Visualizar;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Main {

    public static void main(String[] args) {

        Paises p1 = new Paises(1, "España");
        Paises p2 = new Paises(2, "Francia");

        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, p1);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, p1);
        Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15, p2);
        Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14, p2);

        ODB odb = ODBFactory.open("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\equipos.neo"); //Abrir BD

        //Almacenamos objetos
        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);

        Visualizar v = new Visualizar(odb);
        v.verDatos();

        odb.close();
    }
}
