package Daos;

import Clases.Estudiantes;

import java.util.List;

public interface EstudianteDao {
    List<Estudiantes> getAllEstudiantes();
    void updateEstudiante(Estudiantes estudiante);
    void deleteEstudiante(Estudiantes estudiante);
    void createEstudiante(Estudiantes    estudiante);


}


