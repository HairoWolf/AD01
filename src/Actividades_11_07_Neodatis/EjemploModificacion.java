package Actividades_11_07_Neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class EjemploModificacion {

    public static void main(String[] args) {

        ODB odb = ODBFactory.open("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\jugadores.neo");

        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("nombre","Alicia"));

        Objects<Jugadores> objects = odb.getObjects(query);
        //Obtiene solo el primer objeto encontrado
        Jugadores jug = (Jugadores) objects.getFirst();

        jug.setDeporte("Equitacion");//Cambia el deporte
        odb.store(jug);

        int i = 1;
        //visualizar los objetos
        while (objects.hasNext()) {
            jug = objects.next();
            System.out.printf("%d: %s, %s, %s, %d %n", i++, jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
        }
        odb.commit();
        odb.close();    //Cerrar BD
    }
}
