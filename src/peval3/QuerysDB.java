package peval3;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import peval3.Clases.Libros;
import peval3.Clases.Prestamos;
import peval3.Clases.Usuario;

import static peval3.ShowInformation.showUsers;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Class that have all the methods that prints distinct querys
 */

public class QuerysDB {

    public QuerysDB(){}

    /**
     * Method that prints the loans of one specified user when the return date is greater than the return max date
     * @param PATH the specified path where the db is located
     */
    public static void queryDelay(String PATH) {
        ODB odb = ODBFactory.open(PATH);
        showUsers(odb);
        int codUsuario = GMethods.keyBInt("Introduce el código del Usuario:");
        GMethods.printDiv();

        try {
            IQuery queryUser = new CriteriaQuery(Usuario.class, Where.equal("codUsuario", codUsuario));
            Usuario user = (Usuario) odb.getObjects(queryUser).getFirst();

            IQuery queryLoans = odb.criteriaQuery(Prestamos.class, Where.equal("usuario", user));
            Objects<Prestamos> loans = odb.getObjects(queryLoans);
            while (loans.hasNext()) {
                Prestamos loan = loans.next();
                ICriterion compare = Where.gt("fechaDevolucion", loan.getFechaMaxDevolucion());
                IQuery queryCompare = new CriteriaQuery(Prestamos.class, compare);
                Prestamos loanCompared = (Prestamos) odb.getObjects(queryCompare).getFirst();
                loanCompared.printInformation();
            }
        }
        catch (IndexOutOfBoundsException e){
            GMethods.printError("No existe ningun prestamo que cumpla esas conciciones");
            GMethods.printDiv();
        }
        odb.close();
    }

    /**
     * Method that prints the books that meet two conditions typed by keyboard
     * @param PATH the specified path where the db is located
     */
    public static void specificQuery(String PATH){
        ODB odb = ODBFactory.open(PATH);
        String genero = GMethods.keyBString("Introduce el genero del Libro: ");
        double precio = GMethods.keyBDouble("Introduce el limite de precio: ");
        GMethods.printDiv();

        try {
            ICriterion criterion = new And().add(Where.equal("genero",genero)).add(Where.lt("precioLibro",precio));
            IQuery queryBooks = new CriteriaQuery(Libros.class, criterion);
            Objects<Libros> books = odb.getObjects(queryBooks);
            while (books.hasNext()){
                Libros book = books.next();
                book.printInformation();
            }
        }
        catch (IndexOutOfBoundsException e){
            GMethods.printError("No existe ningun libro que cumpla esas conciciones");
            GMethods.printDiv();
        }
        odb.close();
    }

    /**
     * Method that prints the loans of a specified province and between a time range
     * @param PATH the specified path where the db is located
     */
    /*public static void queryTime(String PATH) {
        ODB odb = ODBFactory.open(PATH);
    }*/
}
