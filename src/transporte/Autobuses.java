package transporte;

public class Autobuses {
    private String matricula;
    private String marca;
    private String modelo;
    private int kilometraje;
    private int capacidad;

    public Autobuses(String matricula, String marca, String modelo, int kilometraje, int capacidad) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.capacidad = capacidad;

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String toString() {
        return "Matricula: " + matricula + ", Marca: " + marca + ", Modelo: " + modelo +
                ", Kilometraje: " + kilometraje + ", Capacidad: " + capacidad;
    }

}
