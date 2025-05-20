package transporte;

public class Conductores extends Autobuses {
    private String dni;
    private String nombre;
    private String licencia;
    private int anos_experiencia;
    private String turnos;
    private String matricula_asignada;

    public Conductores(String dni, String nombre, String licencia, int anos_experiencia, String turnos, String matricula_asignada) {
        super("","","",0,0);
        this.dni = dni;
        this.nombre = nombre;
        this.licencia = licencia;
        this.anos_experiencia = anos_experiencia;
        this.turnos = turnos;
        this.matricula_asignada = matricula_asignada;
    }
    public Conductores(String dni, String nombre, String licencia, int anos_experiencia, String turnos, String matricula_asignada
    ,String marca, String modelo, int kilometraje, int capacidad) {
        super(matricula_asignada, marca, modelo, kilometraje, capacidad);
        this.dni = dni;
        this.nombre = nombre;
        this.licencia = licencia;
        this.anos_experiencia = anos_experiencia;
        this.turnos = turnos;
        this.matricula_asignada = matricula_asignada;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public int getAnos_experiencia() {
        return anos_experiencia;
    }

    public void setAnos_experiencia(int anos_experiencia) {
        this.anos_experiencia = anos_experiencia;
    }

    public String getTurnos() {
        return turnos;
    }

    public void setTurnos(String turnos) {
        this.turnos = turnos;
    }

    public String getMatricula_asignada() {
        return matricula_asignada;
    }

    public void setMatricula_asignada(String matricula_asignada) {
        this.matricula_asignada = matricula_asignada;
    }

    public String toString(){
        return "Dni: " + dni + ", Nombre: " + nombre + ", Licencia: " + licencia + ", Años de Experiencia: " +
                anos_experiencia + ", Turnos: " + turnos + ", Matricula asignada: " + matricula_asignada;
    }

    public String toStringCompleto(){
        return "Dni: " + dni + ", Nombre: " + nombre + ", Licencia: " + licencia + ", Años de Experiencia: " +
                anos_experiencia + ", Turnos: " + turnos + ", Matricula asignada: " + matricula_asignada +
                super.toString();
    }

}