package Actividades_11_02;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Ej_01Reader {
    final static String BDPer = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Objects\\DBEj_01.yap";
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(BDPer);
        //LEER TODOS LOS REGISTROS
        System.out.println("Todas las personas son:");
        Ej_01Person per = new Ej_01Person(null,null);
        ObjectSet <Ej_01Person> result = db.queryByExample(per);
        if(result.size()==0) System.out.println("No existen Registros de Personas..");
        else{
            System.out.println("Número de registros: "+result.size());

            while(result.hasNext()){
                Ej_01Person p = result.next();
                System.out.println("Nombre: " + p.getName() + ", Ciudad: " + p.getCity());
            }
        }
        //LEER SÓLO LOS REGISROS DE AQUELLOS LLAMADOS Enrique
        System.out.println("Todos los Enrique son...");
        Ej_01Person perEnrique = new Ej_01Person("Enrique",null);
        ObjectSet <Ej_01Person> resultEnrique = db.queryByExample(perEnrique);
        if(resultEnrique.size()==0) System.out.println("No existen Registros de Personas..");
        else{
            System.out.println("Número de registros: "+resultEnrique.size());

            while(resultEnrique.hasNext()){
                Ej_01Person p = resultEnrique.next();
                System.out.println("Nombre: " + p.getName() + ", Ciudad: " + p.getCity());
            }
        }
        //LEER SÓLO LOS REGISTROS DE AQUELLAS PERSONAS QUE VIVAN EN Madrid
        System.out.println("En Madrid viven...");
        Ej_01Person perMadrid = new Ej_01Person(null,"Madrid");
        ObjectSet <Ej_01Person> resultMadrid = db.queryByExample(perMadrid);
        if(resultMadrid.size()==0) System.out.println("No existen Registros de Personas..");
        else{
            System.out.println("Número de registros: "+resultMadrid.size());

            while(resultMadrid.hasNext()){
                Ej_01Person p=resultMadrid.next();
                System.out.println("Nombre: " + p.getName() + ", Ciudad: " + p.getCity());
            }
        }
        //CIERRA LA BASE DE DATOS
        db.close();
    }
}
