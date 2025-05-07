package miapp;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        GestionBD bbdd = new GestionBD("jdbc:mysql://localhost:3306/base","root","Propdp34#");
        do {
            try {
                System.out.println("""
                        Que quiere hacer:
                        1- Iniciar sesión
                        2- Registrarse
                        3- Salir
                        """);

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        iniciarSesion(sc, bbdd);
                        break;
                    case 2:
                        registrarse(sc, bbdd);
                        break;
                    case 3:
                        System.out.println("Adios");
                        break;
                    default:
                        System.out.println("Opción no valida");
                        break;
                }
            }catch (NumberFormatException ex){
                System.out.println("Introduzca un valor valido valido");
                opcion = 4;
            }
        }while (opcion != 3);
    }

    public static void registrarse(Scanner sc, GestionBD bbdd) {
        System.out.println("Introduce el nombre de usuario");
        String nombre = sc.nextLine();
        System.out.println("Introduce la contraseña");
        String contrasena = sc.nextLine();
        try{
            if (!bbdd.check(nombre)) {
                System.out.println(bbdd.altaUsuario(nombre, contrasena) ? "Usuario creado" : "El usuario no se a podido añadir");
            } else {
                System.out.println("Usuario ya existe");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void iniciarSesion(Scanner sc, GestionBD bbdd) {
        System.out.println("Introduce el nombre de usuario");
        String nombre = sc.nextLine();
        System.out.println("Introduce la contrasena");
        String contrasena = sc.nextLine();
        try {
            System.out.println(bbdd.login(nombre, contrasena));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}