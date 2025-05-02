package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        HashMap<String, Vendedores> plantilla = new HashMap<>();
        int opcion;
        System.out.println("""
                Menu de opciones:
                1- Alta de usuarios
                2- Login de usuario
                3- Salir
                """);
        opcion = entrada.nextInt();

        switch (opcion) {
            case 1:
                altausuario(plantilla);
                break;
            case 2:
                loginusuario();
                break;
            case 3:
                System.out.println("Adios muchas gracias");
        }
    }
    public static void altausuario(HashMap<String, Vendedores> plantilla) {

        Scanner entrada = new Scanner(System.in);
        String sql = "INSERT INTO vendedores VALUES(?,?,?,?,?)";

        String dni, nombre, apellido, email;
        int telefono;
        double ventas;

        do {
            System.out.println("Ingrese el dni del usuario");
            dni = entrada.nextLine();
            if (dni.isBlank()){
                System.out.println("DNI no valido");
            }
        }while (dni.isBlank());

        do{
            System.out.println("Introduzca el nombre de usuario");
            nombre = entrada.nextLine();
            if (nombre.isBlank()){
                System.out.println("Nombre no valido");
            }
        }while (nombre.isBlank());

        boolean bien = true;

        do {
            try {



            } catch (NumberFormatException e) {
                System.out.println("Valor no valido");
                bien = false;
            }
        }while (!bien);

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "Propdp34#")){

            PreparedStatement ptsm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            for (String key : plantilla.keySet()) {
                ptsm.setString(1, plantilla.get(key).getDni());
                ptsm.setString(2, plantilla.get(key).getNombre());
                ptsm.setInt(3, plantilla.get(key).getTelefono());
                ptsm.setString(4, plantilla.get(key).getCorreo());
                ptsm.setDouble(5, plantilla.get(key).getVentas());

            }

            conn.commit();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void loginusuario() {}
}
