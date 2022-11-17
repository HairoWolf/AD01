package peval3;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import peval3.Clases.Libros;
import peval3.Clases.Prestamos;
import peval3.Clases.Usuario;

import static peval3.ShowInformation.showLoans;
import static peval3.ShowInformation.showUsers;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Class that have all the methods that alter the db
 */

public class AlterDB {

    public AlterDB(){}

    /**
     * Method that creates a new book
     * @param PATH the specified path where the db is located
     */
    public static void newBook(String PATH){
        ODB odb  = ODBFactory.open(PATH);
        int codLibro = Integer.parseInt(odb.getValues(new ValuesCriteriaQuery(Libros.class).max("codLibro")).getFirst().getByAlias("codLibro").toString()) + 1;
        String nomLibro = GMethods.keyBString("Introduce el nombre del libro: ");
        if(checkBook(nomLibro, odb)){
            GMethods.printDiv();
            GMethods.printError("El libro ya existe");
            GMethods.printDiv();
        }
        else {
            String editorial = GMethods.keyBString("Introduce la editorial del libro: ");
            String autor = GMethods.keyBString("Introduce el autor del libro: ");
            String genero = GMethods.keyBString("Introduce el genero del libro: ");
            String paisAutor = GMethods.keyBString("Introduce el pais del autor del libro: ");
            int numPaginas = GMethods.keyBInt("Introduce el numero de paginas del libro: ");
            int anyoEdicion = GMethods.keyBInt("Introduce el año de edicion del libro: ");
            double precioLibro = GMethods.keyBInt("Introduce el precio del libro: ");

            Libros myBook = new Libros(codLibro, numPaginas, anyoEdicion, nomLibro, editorial, autor, genero, paisAutor, precioLibro);
            odb.store(myBook);
            odb.commit();
            odb.close();
            GMethods.printDiv();
            GMethods.println("Libro almacenado");
            GMethods.printDiv();
        }
    }

    /**
     * Method to check if the book already exist
     * @param nomLibro Name of the new book (String)
     * @param odb The db on we work on
     * @return if the book already exist (boolean)
     */
    public static boolean checkBook(String nomLibro, ODB odb){
        boolean checker;
        try {
            Libros book = (Libros) odb.getObjects(new CriteriaQuery(Libros.class, Where.equal("nomLibro", nomLibro))).getFirst();
            checker = true;
        } catch (Exception e) {
            checker = false;
        }
        return  checker;
    }

    /**
     * Method that deletes a specified user and their loans
     * @param PATH the specified path where the db is located
     */
    public static void deleteUser(String PATH){
        ODB odb  = ODBFactory.open(PATH);
        showUsers(odb);
        int codUsuario = GMethods.keyBInt("Introduce el código del Usuario a borrar:");
        try {
            IQuery queryUser = new CriteriaQuery(Usuario.class, Where.equal("codUsuario", codUsuario));
            Usuario user = (Usuario) odb.getObjects(queryUser).getFirst();
            deleteUserLoan(odb, user);
            odb.delete(user);
            GMethods.println("Borrando el Usuario:");
            user.printInformation();
            GMethods.println("**********************************");
        }
        catch (IndexOutOfBoundsException e){
            GMethods.printError("El usuario no existe");
            GMethods.printDiv();
        }
        odb.commit();
        odb.close();
    }

    /**
     * Method that deletes all the loans of a specific user
     * @param odb The db on we work on
     * @param user from whom we are going to take out the loans to be deleted
     */
    public static void deleteUserLoan(ODB odb, Usuario user){
        IQuery queryLoans = odb.criteriaQuery(Prestamos.class, Where.equal("usuario", user));
        Objects <Prestamos> loans = odb.getObjects(queryLoans);
        while(loans.hasNext()) {
            Prestamos loan = loans.next();
            odb.delete(loan);
        }
        odb.commit();
    }

    /**
     * Method that modifies the information of a loan
     * @param PATH the specified path where the db is located
     */
    public static void modifyLoan(String PATH){
        ODB odb  = ODBFactory.open(PATH);
        showLoans(odb);
        int numPedido = GMethods.keyBInt("Introduce el numero de Pedido:");
        try {
            IQuery queryLoan = new CriteriaQuery(Prestamos.class, Where.equal("numPedido", numPedido));
            Prestamos loan = (Prestamos) odb.getObjects(queryLoan).getFirst();

            GMethods.printDiv();
            showUsers(odb);
            int codUsuario = GMethods.keyBInt("Introduce el código del nuevo usuario:");

            IQuery queryUser = new CriteriaQuery(Usuario.class, Where.equal("codUsuario", codUsuario));
            Usuario user = (Usuario) odb.getObjects(queryUser).getFirst();

            loan.setUsuario(user);
            odb.store(loan);
            GMethods.println("Prestamo Modificado");
            GMethods.println("**********************************");
            loan.printInformation();
        }
        catch (IndexOutOfBoundsException e){
            GMethods.printError("El prestamo no existe");
            GMethods.printDiv();
        }
        odb.commit();
        odb.close();
    }
}
