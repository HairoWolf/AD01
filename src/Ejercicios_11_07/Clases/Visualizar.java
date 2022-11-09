package Ejercicios_11_07.Clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;

public class Visualizar {

    private ODB odb;

    public Visualizar(ODB odb){
        this.odb = odb;
    }

    public void verDatos(){
        Objects<Paises> objectsPaises = odb.getObjects(Paises.class);

        System.out.printf("%d Paises: %n", objectsPaises.size());
        System.out.println("---------------------------------");

        int j = 1;
        //Visualizar los objetos
        while (objectsPaises.hasNext()) {
            Paises pais = objectsPaises.next();
            System.out.printf("---%s--- %n",pais.getNombrePais());

            Objects<Jugadores> objects = odb.getObjects(Jugadores.class);

            int i = 1;
            //Visualizar los objetos
            while (objects.hasNext()) {
                Jugadores jug = objects.next();
                if(jug.getPais().getNombrePais().equals(pais.getNombrePais())) {
                    System.out.printf("%d: %s, %s, %s, %d %n", i++, jug.getNombre(), jug.getDeporte(), jug.getCiudad(), jug.getEdad());
                }
            }
            System.out.println("---------------------------------");
        }
    }
}
