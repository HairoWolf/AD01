package peval3;

import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import peval3.Clases.Prestamos;
import peval3.Clases.Usuario;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Class that have all the methods that shows the information of the db
 */

public class ShowInformation {

    public ShowInformation(){}


    /**
     * Method to display all users information
     * @param odb The db on we work on
     */
    public static void showUsers(ODB odb){
        Objects<Usuario> users = odb.getObjects(Usuario.class);
        while(users.hasNext()){
            Usuario user = users.next();
            user.printInformation();
        }
    }

    /**
     * Method to display all loans information
     * @param odb The db on we work on
     */
    public static void showLoans(ODB odb){
        Objects<Prestamos> loans = odb.getObjects(Prestamos.class);
        while(loans.hasNext()){
            Prestamos loan = loans.next();
            loan.printInformation();
        }
    }
}
