package agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        InputStreamReader via = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(via);

        int opcion = -1;

        try {
            Agenda miAgenda = new Agenda();

            do {
                try {
                    System.out.println(
                            "Elige una opción:\n" + "1-Añade contacto\n" + "2-Busca contacto\n" + "3-Elimina contacto\n"
                                    + "4-Visualiza agenda\n" + "5-Número de contactos de mi agenda\n\n" + "0-SALIR");
                    System.out.print("Opción: ");

                    opcion = Integer.parseInt(teclado.readLine());

                    switch (opcion) {

                        case 1:
                            addContacto(teclado, miAgenda);
                            break;

                        case 2:
                            findContacto(teclado, miAgenda);
                            break;

                        case 3:
                            delContacto(teclado, miAgenda);
                            break;

                        case 4:
                            viewAgenda(miAgenda.getContactos());
                            break;

                        case 5:
                            System.out.println(miAgenda.tamanoAgenda() + " contactos");
                            break;

                        case 0:
                            System.out.println("Hasta pronto");
                            miAgenda.cerrarDB();
                            break;

                        default:
                            System.out.println("Opcion no válida");
                            break;
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Formato erróneo\n");
                } catch (SQLException ex) {
                    System.out.println("Error en la consulta " + ex.getMessage());
                }catch(IOException ex) {
                    System.out.println("Error en la E/S de teclado");
                }

            } while (opcion != 0);

        } catch (IOException ex) {
            System.out.println("Error en properties");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión BBDD " + ex.getMessage());
        }

    }//fin main

    private static void viewAgenda(ArrayList<Contacto> listaContactos) {
        // bucle para recorrer los contactos

        for (Contacto c : listaContactos) {
            System.out.println(c);
        }

    }

    private static void addContacto(BufferedReader teclado, Agenda miAgenda) throws IOException, SQLException {
        Contacto c = new Contacto();
        System.out.println("Introduce el nombre del contacto");
        c.setNombre(teclado.readLine());
        System.out.println("Introduce el telefono del contacto");
        c.setTelefono(teclado.readLine());
        System.out.println("Introduce el email del contacto");
        c.setEmail(teclado.readLine());

        System.out.println(miAgenda.addContacto(c) ? "Contacto añadido" : "No se ha podido añadir el contacto");
    }

    private static void findContacto(BufferedReader teclado, Agenda miAgenda) throws IOException, SQLException {
        System.out.println("Introduce el nombre del contacto a buscar");
        String nombre = teclado.readLine();

        Contacto c = miAgenda.encontrarContacto(nombre);

        if(c != null) {
            System.out.println(c);
        }else {
            System.out.println("No existe el contacto " + nombre);
        }
    }

    private static void delContacto(BufferedReader teclado, Agenda miAgenda) throws IOException, SQLException{

        System.out.println("Introduce el nombre del contacto a eliminar");
        String nombre = teclado.readLine();

        System.out.println(miAgenda.borrarContacto(nombre) ?
                "Contacto eliminado" : "No existe el contacto " + nombre);
    }

}