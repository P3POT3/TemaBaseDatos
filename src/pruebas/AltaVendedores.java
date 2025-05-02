package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class AltaVendedores {
    public static void main(String[] args) {

        HashMap<String, Vendedores> plantilla = new HashMap<>();

        plantilla.put("12345678E", new Vendedores("12345678E", "Paco", 555123, "mail.com", 1200.35));
        plantilla.put("87654321F", new Vendedores("87654321F", "Marta", 555789, "sumail.com", 2345.23));

        String sql = "INSERT INTO vendedores VALUES(?,?,?,?,?)";
        int registrosInsertados = 0;

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "Propdp34#");
                PreparedStatement pstmt = conn.prepareStatement(sql)){

            conn.setAutoCommit(false);

            for (String key : plantilla.keySet()) {
                pstmt.setString(1, plantilla.get(key).getDni());
                pstmt.setString(2, plantilla.get(key).getNombre());
                pstmt.setInt(3, plantilla.get(key).getTelefono());
                pstmt.setString(4, plantilla.get(key).getCorreo());
                pstmt.setDouble(5, plantilla.get(key).getVentas());

                registrosInsertados += pstmt.executeUpdate();
            }

            conn.commit();

            System.out.println("Vendedores añadidos: " + registrosInsertados);

        }catch (SQLException ex){
            System.out.println("Error en la conexión a la base de datos.");
        }
    }
}
