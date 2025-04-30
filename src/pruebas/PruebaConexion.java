package pruebas;

import java.sql.*;

public class PruebaConexion {
    public static void main(String[] args) {

        //Parámetros de la base de datos
        String db = "base";
        String url = "jdbc:mysql://localhost:3306/" + db;

        //Usuario y contraseña de la base de datos
        String user = "root";
        String passwd = "Propdp34#";

        try {
            Connection con = DriverManager.getConnection(url, user, passwd);

            System.out.println("Conexion establecida.");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from vendedores");

            double ventaMayor = 0;
            String vendedorMaxVenta = "";

            while (rs.next()) {
                if (rs.getDouble("ventas") > ventaMayor) {
                    vendedorMaxVenta = rs.getString("nombre");
                    ventaMayor = rs.getDouble("ventas");
                }
            }

            System.out.println(vendedorMaxVenta);

            st.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            System.out.println("La url o usuario o contraseña son incorrectas");
        }
    }
}