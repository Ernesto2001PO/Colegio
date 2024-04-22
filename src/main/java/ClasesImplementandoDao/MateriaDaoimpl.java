package ClasesImplementandoDao;


import Clases.Materia;
import Clases.Conexion;
import Daos.MateriaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MateriaDaoimpl implements MateriaDao {

    Logger logger = Logger.getLogger(MateriaDaoimpl.class.getName());
    @Override
    public List<Materia> getAllMaterias() {
        List<Materia> materias = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Materias");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String snombre = rs.getString("snombre");
                Materia materia = new Materia(codigo, snombre);
                materias.add(materia);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materias;
    }

    public Materia getMateriaById(int id) {
        Materia materia = null;
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Materias WHERE codigo = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("snombre");
                materia = new Materia(id, nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.info("Error al obtener Materia por ID");
        }
        return materia;
    }



}