package agenda;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Agenda {

    private final String FICHERO = "C:\\Users\\Clases\\Documents\\Clases\\CONCERTADA\\PROG\\Ejercicios\\Agenda\\config.properties";
    private Connection conn;

    public Agenda() throws IOException, SQLException {

        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(FICHERO)) {
            prop.load(fis);

            conn = DriverManager.getConnection(prop.getProperty("jdbc") + prop.getProperty("BBDD"),
                    prop.getProperty("USUARIO"), prop.getProperty("CLAVE"));
        }
    }

    public ArrayList<Contacto> getContactos() throws SQLException {

        ArrayList<Contacto> lista = new ArrayList<Contacto>();
        String sql = "SELECT * FROM contacto";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email")));
            }
        }
        return lista;
    }

    public int tamanoAgenda() throws SQLException {
        String sql = "SELECT COUNT(*) AS numTotal FROM contacto ";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getInt("numTotal");
        }

    }

    public boolean addContacto(Contacto c) throws SQLException{
        String sql = "INSERT INTO contacto(nombre, telefono, email) VALUES (?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getTelefono());
            pstmt.setString(3, c.getEmail());

            return pstmt.executeUpdate()>0;

        }
    }

    public boolean borrarContacto(String nombre) throws SQLException {
        String sql = "DELETE FROM contacto WHERE nombre = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);

            return pstmt.executeUpdate() > 0;
        }

    }

    public Contacto encontrarContacto(String nombre) throws SQLException {
        String sql = "SELECT * FROM contacto WHERE nombre = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
                } else {
                    return null;
                }
            }
        }

    }

    public void cerrarDB() throws SQLException {

        if (conn != null) {
            conn.close();
        }

    }

}