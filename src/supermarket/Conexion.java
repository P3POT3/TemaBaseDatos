package supermarket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class Conexion {

    private final Connection conn;

    public Conexion() throws IOException, SQLException {

        Properties prop = new Properties();

        try(FileInputStream fis = new FileInputStream("src\\supermarket\\supermarket.properties")){
            prop.load(fis);

            conn = DriverManager.getConnection(prop.getProperty("DRIVER") + prop.getProperty("BBDD")
            , prop.getProperty("USUARIO")
            , prop.getProperty("PASS"));
        }
    }

    public void cerrarConexion() throws SQLException {
        if(conn != null) {
            conn.close();
        }
    }

    public ArrayList<Supermercado> getSupermercados() throws SQLException {

        ArrayList<Supermercado> listaSupermercados = new ArrayList<>();
        String sql = "SELECT * FROM supermercados";

        try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                listaSupermercados.add(new Supermercado(
                        rs.getString("COD_SEDE"),
                        rs.getString("NOMBRE"),
                        rs.getString("DIRECCION"),
                        rs.getString("CIUDAD"),
                        rs.getString("TELEFONO"),
                        rs.getInt("METROS_CUADRADOS"),
                        rs.getInt("NUM_CAJAS"),
                        rs.getString("TIENE_PARKING").equalsIgnoreCase("SI"),
                        rs.getString("TIENE_CARNICERIA").equalsIgnoreCase("SI")));
            }
        }
        return listaSupermercados;
    }

    public ArrayList<Empleado> buscarPorCiudad(String codSede) throws SQLException {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE COD_SEDE = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, codSede);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaEmpleados.add(new Empleado(
                            rs.getString("DNI"),
                            rs.getString("COD_SEDE"),
                            rs.getString("NOMBRE"),
                            rs.getString("CATEGORIA"),
                            rs.getString("TURNO").charAt(0),
                            LocalDate.parse(rs.getString("FECHA_CONTRATO")),
                            rs.getFloat("SALARIO")
                    ));
                }
            }
        }
        return listaEmpleados;
    }

    public ArrayList<Supermercado> getSupermercadosCiudad(String ciudad) throws SQLException {
        ArrayList<Supermercado> listaSupermercados = new ArrayList<>();
        String sql = "SELECT * FROM supermercados WHERE CIUDAD = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, ciudad);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listaSupermercados.add(new Supermercado(                        rs.getString("COD_SEDE"),
                            rs.getString("NOMBRE"),
                            rs.getString("DIRECCION"),
                            rs.getString("CIUDAD"),
                            rs.getString("TELEFONO"),
                            rs.getInt("METROS_CUADRADOS"),
                            rs.getInt("NUM_CAJAS"),
                            rs.getString("TIENE_PARKING").equalsIgnoreCase("SI"),
                            rs.getString("TIENE_CARNICERIA").equalsIgnoreCase("SI")));
                }
            }
        }
        return listaSupermercados;
    }

    public ArrayList<String> calculoMediaSalarial() throws SQLException {
        ArrayList<String> mediaSalarial = new ArrayList<>();
        String sql = "SELECT categoria, AVG(salario) AS media FROM empleados GROUP BY categoria";

        try(Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                mediaSalarial.add("Categoria: " + rs.getString("categoria") +
                        " media salarial: " + rs.getString("media") + "\n");
            }
        }
        return mediaSalarial;
    }
}