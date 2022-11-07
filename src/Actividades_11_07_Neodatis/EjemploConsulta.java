package Actividades_11_07_Neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class EjemploConsulta {

    public static void main(String[] args) {

        ODB odb = ODBFactory.open("C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Neodatis\\jugadores.neo");

        IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("deporte","tenis"));
        query.orderByAsc("nombre,edad");

        //recuperamos todos los objetos
        Objects<Jugadores> objects = odb.getObjects(query);
        System.out.printf("%d Jugadores: %n", objects.size());

        int i = 1;
        //visualizar los objetos
        while (objects.hasNext()) {
            Jugadores jug = objects.next();
            System.out.printf("%d: %s, %s, %s, %d %n", i++, jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
        }
        odb.close();    //Cerrar BD
    }
}
