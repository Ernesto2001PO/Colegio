package ClasesImplementandoDao;

import Clases.Conexion;
import Clases.Estudiantes;
import Daos.EstudianteDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class EstudianteDaoImpl implements EstudianteDao {
    Logger logger = Logger.getLogger(EstudianteDaoImpl.class.getName());
    @Override
    public List<Estudiantes> getAllEstudiantes() {
        List<Estudiantes> estudiantes = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Estudiantes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEstudiante = rs.getInt("nro_registro");
                String nombre = rs.getString("snombrecompleto");
                Date fechaNacimiento = rs.getDate("fecha_nac");
                String Carrera= rs.getString("carrera");

                Estudiantes estudiante = new Estudiantes(idEstudiante, nombre, fechaNacimiento,Carrera);
                estudiantes.add(estudiante);
                logger.info("Extraer estudiante");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error en getAllEstudiantes");
        }
        logger.info("getAllEstudiantes");
        return estudiantes;

    }


    @Override
    public void updateEstudiante(Estudiantes estudiante) {
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Estudiantes SET snombrecompleto = ? WHERE nro_registro = ?"
            );
            stmt.setString(1, estudiante.getNombre());
            stmt.setInt(2, estudiante.getIdEstudiante());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEstudiante(Estudiantes estudiante) {
        try (Connection conn = Conexion.getConnection()) {
            // Eliminar las referencias del estudiante en la tabla estudiante_materia
            PreparedStatement stmtDeleteReferencias = conn.prepareStatement(
                    "DELETE FROM estudiante_materia WHERE id_estudiante = ?"
            );
            stmtDeleteReferencias.setInt(1, estudiante.getIdEstudiante());
            stmtDeleteReferencias.executeUpdate();

            // Luego, eliminar el estudiante de la tabla Estudiantes
            PreparedStatement stmtDeleteEstudiante = conn.prepareStatement(
                    "DELETE FROM Estudiantes WHERE nro_registro = ?"
            );
            stmtDeleteEstudiante.setInt(1, estudiante.getIdEstudiante());
            stmtDeleteEstudiante.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void createEstudiante(Estudiantes estudiante) {
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Estudiantes (nro_registro, snombrecompleto,fecha_nac,carrera) VALUES (?, ?,?,?)"
            );
            stmt.setInt(1, estudiante.getIdEstudiante());
            stmt.setString(2, estudiante.getNombre());
            stmt.setDate(3, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            stmt.setString(4, estudiante.getCarrera());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
