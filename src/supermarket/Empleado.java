package supermarket;

import java.time.LocalDate;

public class Empleado {

    private String dni;
    private String sede;
    private String nombre;
    private String categoria;
    private char turno;
    private LocalDate fechaAlta;
    private float salario;

    public Empleado(String dni, String sede, String nombre, String categoria,
                    char turno, LocalDate fechaAlta, float salario) {
        this.dni = dni;
        this.sede = sede;
        this.nombre = nombre;
        this.categoria = categoria;
        this.turno = turno;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "dni: " + dni + ", sede: " + sede  + ", nombre: " + nombre + ", categoria: " + categoria +
                ", turno: " + turno + ", fechaAlta: " + fechaAlta + ", salario: " + salario;
    }
}
