package peval3.Clases;

/**
 *
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 *  Clase Libros
 */

public class Libros {

    //Campos de clase
    private int codLibro, numPaginas, anyoEdicion;
    private String nomLibro, editorial, autor, genero, paisAutor, precioLibro;

    /**
     * Constructor de clase parametrizado
     * @param codLibro Clave primaria BD (int)
     * @param numPaginas Numero de paginas del libro (int)
     * @param anyoEdicion Anyo de Edicion del libro (int)
     * @param nomLibro Nombre del libro (String)
     * @param editorial Nombre de la editorial del libro (String)
     * @param autor Nombre del autor del libro (String)
     * @param genero Genero del libro (String)
     * @param paisAutor Pais del autor del libro (String)
     * @param precioLibro Precio del libro (String)
     */
    public Libros(int codLibro, int numPaginas, int anyoEdicion, String nomLibro, String editorial, String autor, String genero, String paisAutor, String precioLibro) {
        this.codLibro = codLibro;
        this.numPaginas = numPaginas;
        this.anyoEdicion = anyoEdicion;
        this.nomLibro = nomLibro;
        this.editorial = editorial;
        this.autor = autor;
        this.genero = genero;
        this.paisAutor = paisAutor;
        this.precioLibro = precioLibro;
    }

    /**
     * Getters y setters clase Libros
     */
    public int getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(int codLibro) {
        this.codLibro = codLibro;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getAnyoEdicion() {
        return anyoEdicion;
    }

    public void setAnyoEdicion(int anyoEdicion) {
        this.anyoEdicion = anyoEdicion;
    }

    public String getNomLibro() {
        return nomLibro;
    }

    public void setNomLibro(String nomLibro) {
        this.nomLibro = nomLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPaisAutor() {
        return paisAutor;
    }

    public void setPaisAutor(String paisAutor) {
        this.paisAutor = paisAutor;
    }

    public String getPrecioLibro() {
        return precioLibro;
    }

    public void setPrecioLibro(String precioLibro) {
        this.precioLibro = precioLibro;
    }
}
