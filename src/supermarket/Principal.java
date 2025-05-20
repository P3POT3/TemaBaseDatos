package supermarket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Conexion con = new Conexion();

            //listarSupermercados(con);
            //buscarEmpleadosPorCiudad(con, sc);
            //buscarSupermercadosPorCiudad(con);
            mediaSalarial(con);
        } catch (SQLException | IOException e) {
            System.out.println("No se ha podido conectar al servidor \n" + e.getMessage());
        }
    }

    private static void listarSupermercados(Conexion con) throws SQLException {
        ArrayList<Supermercado> listaSupermercados = con.getSupermercados();
        for (Supermercado supermercado : listaSupermercados) {
            System.out.println(supermercado);
        }
    }

    private static void buscarEmpleadosPorCiudad(Conexion con, Scanner sc) throws SQLException {
        String codSede;
        do {
            System.out.println("Ingrese el COD_SEDE");
            codSede = sc.nextLine();
        }while (codSede.isBlank());
        ArrayList<Empleado> listaEmpleados = con.buscarPorCiudad(codSede);
        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado);
        }
    }

    private static void buscarSupermercadosPorCiudad(Conexion con) throws SQLException {
        String ciudad = "Barcelona";
        ArrayList<Supermercado> listaSupermercados = con.getSupermercadosCiudad(ciudad);
        for (Supermercado supermercado : listaSupermercados) {
            System.out.println(supermercado);
        }
    }

    public static void listarEmpleados(Conexion con) throws SQLException {}

    public static void mediaSalarial(Conexion con) throws SQLException {
        ArrayList<String> mediaSalarial = con.calculoMediaSalarial();

        if (mediaSalarial.isEmpty()) {
            System.out.println("No hay datos");
        } else {
            System.out.println(mediaSalarial);
        }

    }

}
