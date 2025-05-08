package agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        GestionBD bbdd = new GestionBD("jdbc:mysql://localhost:3306/agenda", "root", "Propdp34#");

        int opcion = -1;

        do {
            try {
                System.out.println("""
                        Que quieres hacer
                        1- Añadir contacto
                        2- Buscar contacto
                        3- Eliminar contacto
                        4- Ver agenda
                        5- Contar contactos
                        6- Salir
                        """);
                opcion = Integer.parseInt(br.readLine());
            }catch (IOException e) {
                System.out.println("Caracter no valido");
            }catch (NumberFormatException e) {
                System.out.println("Introduzca un numero valido");
            }
            switch (opcion) {
                case 1:
                    aniadirContacto(br,bbdd);
                    break;
                case 2:
                    buscarContacto(br,bbdd);
                    break;
                case 3:
                    eliminarContacto(br,bbdd);
                    break;
                case 4:
                    verAgenda(GestionBD.getContactos(), bbdd);
                    break;
                case 5:
                    System.out.println(GestionBD.cantidadContactos() + " contactos en la agenda");
                    break;
                case 6:
                    System.out.println("Adios");
                default:
                    System.out.println("Opción no valida");
            }
        }while (opcion != 6);
        br.close();
        isr.close();
    }

    private static void aniadirContacto (BufferedReader br, GestionBD bbdd) throws SQLException, IOException {
        String nombre;
        String telefono;
        String email;
        do {
            System.out.println("Introduce el nombre del contacto");
            nombre = br.readLine();
        }while (nombre.isBlank());
        do {
            System.out.println("Introduce el telefono del contacto");
            telefono = br.readLine();
        }while (telefono.isBlank());
        do {
            System.out.println("Introduce el email del contacto");
            email = br.readLine();
        }while (email.isBlank());

        Contacto c = new Contacto(nombre, telefono, email);

        System.out.println(bbdd.addContacto(c) ? "El contacto fue añadido" : "El contacto no se añadió");
    }

    private static void verAgenda(ResultSet listado, GestionBD bbdd) throws SQLException {

        while (listado.next()) {
            System.out.println("Nombre: " + listado.getString("nombre")
            + " Email: " + listado.getString("email")
            + " Telefono: " + listado.getString("telefono"));
        }

    }

    public static void eliminarContacto(BufferedReader br, GestionBD bbdd) throws IOException, SQLException {
        System.out.println("Introduce el nombre del contacto a eliminar");
        String nombre = br.readLine();

        System.out.println(bbdd.borrarContacto(nombre) ? "Contacto eliminado" : "No existe el contacto" + nombre);
    }

    public static void buscarContacto(BufferedReader br, GestionBD bbdd) throws SQLException, IOException {
        System.out.println("Introduce el nombre del contacto a buscar");
        String nombre = br.readLine();
        Contacto contacto = bbdd.buscar(nombre);
        if (contacto != null) {
            System.out.println(contacto);
        } else {
            System.out.println("El contacto no existe");
        }
    }

}