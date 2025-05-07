package agenda;

import java.sql.*;

public class GestionBD {
    private Connection con;
    private final String FICHERO = "C:\\Users\\ALUMNOS\\Downloads\\config.properties";

    public GestionBD() {}
    public GestionBD(String url, String user, String pass) throws SQLException {
        con = DriverManager.getConnection(url, user, pass);
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
}