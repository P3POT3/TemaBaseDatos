package transporte;

import agenda.Contacto;

import java.sql.*;
import java.util.ArrayList;

public class Conexion {
    private Connection conn;

    public Conexion() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transporte", "root", "Propdp34#");
    }

    public ArrayList<Conductores> getConductores() throws SQLException {

        ArrayList<Conductores> lista = new ArrayList<Conductores>();
        String sql = "SELECT * FROM autobuses join conductores on autobuses.matricula = conductores.matricula_asignada group by autobuses.MATRICULA";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Conductores(rs.getString("DNI"),
                        rs.getString("NOMBRE"),
                        rs.getString("LICENCIA"),
                        rs.getInt("ANOS_EXPERIENCIA"),
                        rs.getString("TURNOS"),
                        rs.getString("MATRICULA_ASIGNADA")));
            }
        }
        return lista;
    }

    public ArrayList<String> calculoMediaAnios() throws SQLException {
        ArrayList<String> mediaAnos = new ArrayList<>();
        String sql = "SELECT LICENCIA ,AVG(ANOS_EXPERIENCIA) AS mediaAnos FROM conductores GROUP BY LICENCIA";

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                mediaAnos.add("Licencia: " + rs.getString("LICENCIA") +
                        " media años trabajados: " + rs.getString("mediaAnos") + "\n");
            }
        }
        return mediaAnos;
    }

    public Autobuses encontrarAutobus(String marca) throws SQLException {
        String sql = "SELECT * FROM autobuses WHERE MARCA = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marca);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Autobuses(rs.getString("MATRICULA"),
                            rs.getString("MARCA"),
                            rs.getString("MODELO"),
                            rs.getInt("KILOMETRAJE"),
                            rs.getInt("CAPACIDAD"));
                } else {
                    return null;
                }
            }
        }
    }

    public String estadisticasSegunTurno(String turno) throws SQLException {
        String sql = "SELECT TURNO ,SUM(ANOS_EXPERIENCIA) AS numeroPorTurno FROM conductores WHERE TURNO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, turno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TURNO") + ": " + rs.getString("numeroPorTurno") ;
                } else {
                    return null;
                }
            }
        }
    }

    public boolean addConductor(ArrayList<Conductores> c) throws SQLException{
        String sql = "INSERT INTO conductores(DNI, NOMBRE, LICENCIA, ANOS_EXPERIENCIA, TURNO, MATRICULA_ASIGNADA) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            int contador = 0;
            boolean respuesta = false;
            for(Conductores con: c) {
                pstmt.setString(1, con.getDni());
                pstmt.setString(2, con.getNombre());
                pstmt.setString(3, con.getLicencia());
                pstmt.setInt(4, con.getAnos_experiencia());
                pstmt.setString(5, con.getTurnos());
                pstmt.setString(6, con.getMatricula_asignada());
                if(contador <= 1) {
                    if(pstmt.executeUpdate() > 0){
                        respuesta = true;
                    } else {
                        respuesta = false;
                    }
                }
                contador++;
            }
            return respuesta;

        }
    }

    public void cerrar() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
