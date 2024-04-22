package Daos;

import Clases.Materia;
import java.util.List;

public interface MateriaDao {
    List<Materia> getAllMaterias();



    Materia getMateriaById(int materiaId);
}