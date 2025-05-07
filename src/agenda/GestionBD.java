package agenda;

import java.sql.*;

public class GestionBD {
    private static Connection con;
    private final String FICHERO = "C:\\Users\\ALUMNOS\\Downloads\\config.properties";

    public GestionBD() {}
    public GestionBD(String url, String user, String pass) throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "Propdp34#");
    }

    public void buscar(String nombre) {
        String sql = "select * from contacto where nombre = ?";
        String datos;

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            datos = pstmt.getGeneratedKeys().getString(nombre);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static int cantidadContactos() {
        String sql = "select count(*) from contacto";
        try(Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return 0;
    }

    public static ResultSet getContactos() throws SQLException{
        String sql = "select * from contacto";
        try(Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            return rs;
        }
    }

    public void cerrarDB() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}