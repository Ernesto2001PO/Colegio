package Daos;

import Clases.EstudianteMateria;
import java.util.List;


public interface EstudianteMateriaDao {
    List<EstudianteMateria> getEstudianteMateriasByEstudianteId(int estudianteId);
    List<EstudianteMateria> getNotasByEstudianteIdAndMateriaId (int estudianteId, int materiaId);

    void createEstudianteMateria(EstudianteMateria estudianteMateria);
    void addNotaToEstudianteMateria (int idEstudiante, int idMateria, String tipoNota, double nota);
}