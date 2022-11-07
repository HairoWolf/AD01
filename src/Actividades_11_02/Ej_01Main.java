package Actividades_11_02;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Ej_01Main {

    final static String BDPer = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Objects_Files\\DBEj_01.yap";
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDPer);

        //Creamos E1Personas
        Ej_01Person p1 = new Ej_01Person ("Juan", "Guadalajara");
        Ej_01Person p2 = new Ej_01Person ("María", "Madrid");
        Ej_01Person p3 = new Ej_01Person ("Enrique", "Málaga");
        Ej_01Person p4 = new Ej_01Person ("Manuel", "Sevilla");

        //Almacenar objetos Persona en la base de datos
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);

        //Cerramos la base de datos
        db.close();
    }
}
