package Actividades_11_02;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Ej_01Modify {
    final static String BDPer = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\DB_Objects\\DBEj_01.yap";
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(BDPer);
        //CAMBIAR LA CIUDAD DONDE VIVE JUAN POR TOLEDO
        Ej_01Person perJuan = new Ej_01Person("Juan",null);
        ObjectSet<Ej_01Person> resultJuan = db.queryByExample(perJuan);
        if(resultJuan.size() == 0) System.out.println("No existen Juan...");
        else{
            Ej_01Person existe = (Ej_01Person) resultJuan.next();
            existe.setCity("Toledo");
            db.store(existe); //ciudad modificada
            //consultar los datos
            ObjectSet <Ej_01Person> result = db.queryByExample(new Ej_01Person(null,null));
            while(result.hasNext()){
                Ej_01Person p = result.next();
                System.out.println("Nombre: " + p.getName() + ", Ciudad: " + p.getCity());
            }
        }
        //CIERRA LA BASE DE DATOS
        db.close();
    }
}
