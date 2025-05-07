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
                    //aniadirContacto();
                    break;
                case 2:
                    //buscarContacto();
                    break;
                case 3:
                    //eliminarContacto();
                    break;
                case 4:
                    verAgenda(GestionBD.getContactos());
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

    private static void verAgenda(ResultSet listado) throws SQLException {

        while (listado.next()) {
            System.out.println("Nombre: " + listado.getString("nombre")
            + " Email: " + listado.getString("email")
            + " Telefono: " + listado.getString("telefono"));
        }

    }
}