package Actividades_11_07_Neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class EjemploOID {

    public static void main(String[] args) {
        try {
            //Abrir la bd
            ODB odb = ODBFactory.open("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\jugadores.neo");

            // Obtengo el objeto con el OID=5
            OID oid = OIDFactory.buildObjectOID(5);

            Jugadores jug = (Jugadores) odb.getObjectFromId(oid);
            System.out.println(jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
            odb.delete(jug);
            odb.commit();
            odb.close();
        }
        catch (ODBRuntimeException odbe){
            System.out.println("El oid no existe");
            odbe.printStackTrace();
        }
    }
}
