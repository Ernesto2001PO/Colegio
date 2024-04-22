package Clases;
public class EstudianteMateria {
    private int idEstudiante;
    private int idMateria;


    private double nota;
    private String tipoNota;

    public EstudianteMateria(int idEstudiante, int idMateria, double nota, String tipoNota) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
        this.tipoNota = tipoNota;
    }



    public EstudianteMateria(int idEstudiante, int idMateria) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public double getNotas() {
        return nota;
    }

    public String getTipoNota() {
        return tipoNota;
    }
}