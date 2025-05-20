package supermarket;

public class Supermercado {

    private String codSede;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private int metrosCuadrados;
    private int numCajas;
    private boolean parking;
    private boolean carniceria;

    public Supermercado(String codSede, String nombre, String direccion,
                        String ciudad, String telefono, int metrosCuadrados,
                        int numCajas, boolean parking, boolean carniceria) {
        this.codSede = codSede;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.metrosCuadrados = metrosCuadrados;
        this.numCajas = numCajas;
        this.parking = parking;
        this.carniceria = carniceria;
    }

    public String getCodSede() {
        return codSede;
    }

    public void setCodSede(String codSede) {
        this.codSede = codSede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public int getNumCajas() {
        return numCajas;
    }

    public void setNumCajas(int numCajas) {
        this.numCajas = numCajas;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isCarniceria() {
        return carniceria;
    }

    public void setCarniceria(boolean carniceria) {
        this.carniceria = carniceria;
    }

    public String toString(){
        return "CodSede: " + codSede + " Nombre: " + nombre + " Direccion: " + direccion +
                " Ciudad: " + ciudad + " Telefono: " + telefono + " numCajas: " + numCajas +
                " Parking: " + (parking ? "si":"no") + " Carniceria: " + (carniceria ? "si":"no");
    }

}
