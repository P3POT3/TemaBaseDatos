package agenda;

public class Contacto {
    private String nombre;
    private int telefono;
    private String email;

    public Contacto(String nombre, int telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String toString() {
        return "Nombre: " + nombre + ", Telefono: " + telefono + ", Email: " + email;
    }
}
