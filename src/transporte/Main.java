package transporte;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Conexion conn = null;
        try{
            int opcion = 1;
            conn = new Conexion();
            switch (opcion) {
                case 1:
                    addConductor(conn);
                    break;

                case 2:
                    buscarPorMarca(conn);
                    break;

                case 3:
                    conductoresPorLicencia(conn);
                    break;

                case 4:
                    estadisticasPorTurno(conn);
            }

            conn.cerrar();

        } catch (SQLException | RuntimeException e) {
            System.out.println("Error en la conexion de la base de datos: " + e.getMessage());
        }
    }

    private static void addConductor(Conexion conn) throws SQLException {
        ArrayList<Conductores> conductor = new ArrayList<>();
        conductor.add(new Conductores("1C", "Unai Ruiz", "D",
                10, "tarde", "1234ABC"));
        conductor.add(new Conductores("2C", "Prueba Fernandez", "D",
                10, "tarde", "1234ABC"));
        System.out.println(conn.addConductor(conductor) ? "Los conductores se añadieron correctamente" :
                "Error al añadir los conductores");
    }

    private static void buscarPorMarca(Conexion conn) throws SQLException {
        Autobuses auto = conn.encontrarAutobus("Mercedes");
        System.out.println(auto.toString());
    }

    private static void conductoresPorLicencia(Conexion conn) throws SQLException {
        String licencia = "C";
        ArrayList<String> conductores= conn.calculoMediaAnios();

        for (String conductor : conductores) {
            System.out.println(conductor);
        }
    }

    private static void estadisticasPorTurno(Conexion conn) throws SQLException {
        String turno = "mañana";
        String estat = conn.estadisticasSegunTurno(turno);
        System.out.println(estat);
    }

}