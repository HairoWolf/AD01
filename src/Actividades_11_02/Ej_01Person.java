package Actividades_11_02;

public class Ej_01Person {

    private String name;
    private String city;

    public Ej_01Person (String name, String city){
        this.name=name;
        this.city=city;
    }
    public Ej_01Person() {
        this.name = null;
        this.city = null;
    }

    //GETTERS Y SETTERS
    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String dir){this.city=dir;}
    public String getCity() {
        return city;
    }

}
