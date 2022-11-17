package peval3.Clases;

import peval3.GMethods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Clase Prestamos
 */

public class Prestamos {

    //Campos de clase
    private int numPedido;
    /**
     * Campo relacion con la clase Libros
     */
    private Libros libro;
    /**
     * Campo relacion con la clase Usuario
     */
    private Usuario usuario;
    private Date fechaSalida, fechaMaxDevolucion, fechaDevolucion;

    /**
     * Constructor de clase parametrizado
     * @param numPedido Clave primaria BD (int)
     * @param libro Clave foranea clase Libros (Libros)
     * @param usuario Clave foranea clase Usuario (Usuario)
     * @param fechaSalida Fecha salida del presatamo (String)
     * @param fechaMaxDevolucion Fecha maxima devolucion del presatamo (String)
     * @param fechaDevolucion Fecha devolucion del presatamo (String)
     */
    public Prestamos(int numPedido, Libros libro, Usuario usuario, String fechaSalida, String fechaMaxDevolucion, String fechaDevolucion) {
        this.numPedido = numPedido;
        this.libro = libro;
        this.usuario = usuario;
        try {
            this.fechaSalida = new SimpleDateFormat("dd/MM/yyyy").parse(fechaSalida);
            this.fechaMaxDevolucion = new SimpleDateFormat("dd/MM/yyyy").parse(fechaMaxDevolucion);
            this.fechaDevolucion = new SimpleDateFormat("dd/MM/yyyy").parse(fechaDevolucion);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that print all the information of the loan
     */
    public void printInformation(){
        GMethods.println("Numero Pedido: " + this.numPedido +
                "\nNombre del Libro: " + this.libro.getNomLibro() +
                "\nNombre del Usuario: " + this.usuario.getNombre() +
                "\nCodigo del Usuario: " + this.usuario.getCodUsuario() +
                "\nFecha Salida prestamo: " + this.fechaSalida +
                "\nFecha Maxima devolucion: " + this.fechaMaxDevolucion +
                "\nFecha devolucion: " + this.fechaDevolucion);
        GMethods.printDiv();
    }

    /**
     * Getters y setters clase Prestamos
     */
    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(Date fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
