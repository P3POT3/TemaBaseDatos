package miapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class GestionBD {
    private Connection con;

    public GestionBD(String url, String user, String password) throws SQLException {
        con = DriverManager.getConnection(url, user, password);

    }

    public boolean check(String user) {
        String sql = "SELECT nickname From users where nickname = ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean altaUsuario(String user, String password) {
        String sql = "INSERT INTO users VALUES(?,?)";

        try(PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            return pstmt.executeUpdate() > 0;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean login(String user, String password) throws SQLException {
        String sql = "SELECT * From users where nickname = ? AND password = ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            try(ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private String cifradoString(String clave) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(clave.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}