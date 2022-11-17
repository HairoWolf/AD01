package peval3.Clases;

import peval3.GMethods;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Clase Usuario
 */

public class Usuario {

    //Campos de clase
    private int codUsuario;
    private String nombre, apellido, dni, domicilio, poblacion, provincia, fechaNacimiento;

    /**
     * Constructor de clase parametrizado
     * @param codUsuario Clave primaria BD (int)
     * @param nombre Nombre de usuario (String)
     * @param apellido Apellido de usuario (String)
     * @param dni DNI del usuario (String)
     * @param domicilio Domicilio del usuario (String)
     * @param poblacion Poblacion del usuario (String)
     * @param provincia Provincia del usuario (String)
     * @param fechaNacimiento Fecha de nacimiento del usuario (String)
     */
    public Usuario(int codUsuario, String nombre, String apellido, String dni, String domicilio, String poblacion, String provincia, String fechaNacimiento) {
        this.codUsuario = codUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Method that print all the information of the user
     */
    public void printInformation(){
        GMethods.println("Codigo Usuario: " + this.codUsuario +
                "\nNombre: " + this.nombre +
                "\nApellido: " + this.apellido +
                "\nDNI: " + this.dni +
                "\nDomicilio: " + this.domicilio +
                "\nPoblacion: " + this.poblacion +
                "\nProvincia: " + this.provincia +
                "\nFecha Nacimiento: " + this.fechaNacimiento);
        GMethods.printDiv();
    }

    /**
     * Getters y setters clase Usuario
     */
    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
