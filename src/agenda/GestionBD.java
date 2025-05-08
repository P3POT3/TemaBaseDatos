package agenda;

import java.sql.*;

public class GestionBD {
    private static Connection con;
    private final String FICHERO = "C:\\Users\\ALUMNOS\\Downloads\\config.properties";

    public GestionBD() {}
    public GestionBD(String url, String user, String pass) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
    }

    public boolean addContacto(Contacto contacto) throws SQLException {
        String sql = "INSERT INTO contacto (nombre, telefono, email) VALUES (?, ?, ?)";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, contacto.getNombre());
            pstmt.setString(2, contacto.getTelefono());
            pstmt.setString(3, contacto.getEmail());

            return pstmt.executeUpdate() > 0;
        }

    }

    public Contacto buscar(String nombre) throws SQLException {
        String sql = "SELECT * FROM contacto WHERE nombre = ?";
        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, nombre);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return new Contacto(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"));
                } else {
                    return null;
                }
            }
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
            rs.next();
            return rs;
        }
    }

    public boolean borrarContacto(String nombre) throws SQLException{
        String sql = "delete from contacto where nombre = ?";
        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, nombre);

            return pstmt.executeUpdate() > 0;
        }
    }

    public void cerrarDB() throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}