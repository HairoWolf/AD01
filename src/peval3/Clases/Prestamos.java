package peval3.Clases;

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
    private String fechaSalida, fechaMaxDevolucion, fechaDevolucion;

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
        this.fechaSalida = fechaSalida;
        this.fechaMaxDevolucion = fechaMaxDevolucion;
        this.fechaDevolucion = fechaDevolucion;
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

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(String fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
