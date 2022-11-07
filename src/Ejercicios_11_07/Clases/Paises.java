package Ejercicios_11_07.Clases;

public class Paises {

    private int id;
    private String nombrePais;

    public Paises(int id, String nombrePais) {
        this.id = id;
        this.nombrePais = nombrePais;
    }

    public String toString(){return nombrePais;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombrePais() {return nombrePais;}

    public void setNombrePais(String nombrePais) {this.nombrePais = nombrePais;}
}
