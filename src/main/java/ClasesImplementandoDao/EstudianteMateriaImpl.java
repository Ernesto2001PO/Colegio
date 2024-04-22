package ClasesImplementandoDao;


import Clases.Conexion;
import Clases.EstudianteMateria;
import Clases.Estudiantes;
import Daos.EstudianteMateriaDao;
import java.sql.Date;
import java.util.Calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class EstudianteMateriaImpl implements EstudianteMateriaDao {


    Logger logger = Logger.getLogger(EstudianteDaoImpl.class.getName());



    @Override
    public List<EstudianteMateria> getEstudianteMateriasByEstudianteId(int estudianteId) {
        List<EstudianteMateria> estudianteMaterias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT id_estudiante, id_materia FROM Estudiante_Materia WHERE id_estudiante = ?");
            stmt.setInt(1, estudianteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEstudiante = rs.getInt("id_estudiante");
                int idMateria = rs.getInt("id_materia");
                EstudianteMateria estudianteMateria = new EstudianteMateria(idEstudiante, idMateria);
                estudianteMaterias.add(estudianteMateria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error al obtener las relaciones EstudianteMateria para el estudiante con ID: " + estudianteId);
        }
        return estudianteMaterias;
    }

    @Override
    public List<EstudianteMateria> getNotasByEstudianteIdAndMateriaId(int estudianteId, int materiaId) {
        List<EstudianteMateria> estudianteMaterias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Estudiante_Materia WHERE id_estudiante = ? AND id_materia = ?");
            stmt.setInt(1, estudianteId);
            stmt.setInt(2, materiaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idEstudiante = rs.getInt("id_estudiante");
                int idMateria = rs.getInt("id_materia");
                double notas = rs.getDouble("notas");
                String tipoNota = rs.getString("tipo_nota");
                EstudianteMateria estudianteMateria = new EstudianteMateria(idEstudiante, idMateria, notas, tipoNota);
                estudianteMaterias.add(estudianteMateria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error al obtener las notas para el estudiante con ID: " + estudianteId + " y materia con ID: " + materiaId);
        }
        return estudianteMaterias;
    }



    @Override
    public void createEstudianteMateria(EstudianteMateria estudianteMateria) {
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Estudiante_Materia (id_estudiante, id_materia, tipo_nota, fecha_evaluacion) VALUES (?, ?, ?, ?)"
            );
            stmt.setInt(1, estudianteMateria.getIdEstudiante());
            stmt.setInt(2, estudianteMateria.getIdMateria());
            stmt.setString(3, "Sin Nota"); // Asignar un valor predeterminado a tipo_nota

            // Asignar un valor predeterminado a fecha_evaluacion
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1); // Añadir un año a la fecha actual
            Date defaultDate = new Date(calendar.getTimeInMillis());
            stmt.setDate(4, (java.sql.Date) defaultDate);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error al crear la relación EstudianteMateria");
        }
    }
    public void addNotaToEstudianteMateria(int idEstudiante, int idMateria, String tipoNota, double nota) {
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE Estudiante_Materia SET tipo_nota = ?, notas = ? WHERE id_estudiante = ? AND id_materia = ?"
            );
            stmt.setString(1, tipoNota);
            stmt.setDouble(2, nota);
            stmt.setInt(3, idEstudiante);
            stmt.setInt(4, idMateria);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }


