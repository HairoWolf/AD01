package peval3;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import peval3.Clases.Libros;
import peval3.Clases.Prestamos;
import peval3.Clases.Usuario;

import java.sql.ResultSet;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Aplicacion para la gestion de una base de datos Neodatis
 */

public class AppMain {

    public static void main(String[] args) {

        /**
         * Constants to define the parameters of the DB
         */
        String PATH = "C:\\Users\\jtienda\\Documents\\2 DAM\\AD\\PEVAL3\\Fichero_neo\\biblioteca.neo";
        String DBNAME = "biblioteca";
        String DBUSER = "root";

        /**
         * Byte option to select in the menu
         */
        byte option = 0;
        boolean createdDB = false;

        //Menu de eleccion
        while(option != 8) {
            GMethods.println("Menu de eleccion:\n 1: Crear BD\n 2: Alta de libros\n"+
                    " 3: Bajas de usuarios\n 4: Modificaciones de préstamos\n 5: Préstamos de un usuario que se hayan entregado con retraso\n" +
                    " 6: Libros de un género y precio especificado\n 7: Préstamos realizados en una provincia determinada en un período de tiempo\n" +
                    " 8: Salir de la aplicacion");
            GMethods.printDiv();
            try {
                option = GMethods.keyBByte("Introduce la opcion");
                GMethods.printDiv();
                switch (option){
                    case 1:
                        new DBConnection(PATH, DBNAME, DBUSER);
                        break;
                    case 2:
                        newBook(PATH);
                        break;
                    case 3:
                        deleteUser(PATH);
                        break;
                    case 4:
                        modifyLoan(PATH);
                        break;
                    case 5:
                        queryDelay(PATH);
                        break;
                    case 6:
                        //TODO Libros de un género y precio especificado
                        break;
                    case 7:
                        //TODO Préstamos realizados en una provincia determinada en un período de tiempo
                        break;
                    case 8:
                        GMethods.println("Hasta luego!!!");
                        System.exit(0);
                        break;
                    default:
                        GMethods.printError("Elige una opcion valida");
                        break;
                }
            }
            //Catch all the possible exceptions
			catch(Exception e) {
                GMethods.printError("Error durante la ejecucion del programa");
                e.printStackTrace();
            }
        }
    }

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

    public static  void deleteUser(String PATH){
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

    public static void showUsers(ODB odb){
        Objects<Usuario> users = odb.getObjects(Usuario.class);
        while(users.hasNext()){
            Usuario user = users.next();
            user.printInformation();
        }
    }

    public static void showLoans(ODB odb){
        Objects<Prestamos> loans = odb.getObjects(Prestamos.class);
        while(loans.hasNext()){
            Prestamos loan = loans.next();
            loan.printInformation();
        }
    }

    public static void deleteUserLoan(ODB odb, Usuario user){
        IQuery queryLoans = odb.criteriaQuery(Prestamos.class, Where.equal("usuario", user));
        Objects <Prestamos> loans = odb.getObjects(queryLoans);
        while(loans.hasNext()) {
            Prestamos loan = loans.next();
            odb.delete(loan);
        }
        odb.commit();
    }

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

    private static void queryDelay(String PATH) {
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
            GMethods.printError("El prestamo no existe");
            GMethods.printDiv();
        }
        odb.close();
    }
}
