package Clases;

import java.util.Date;

public class Estudiantes {
    private int idEstudiante;
    private String nombrecompleto;
    private Date fechaNacimiento;
    private String Carrera;




    public Estudiantes(int idEstudiante, String nombre, Date fechaNacimiento, String Carrera) {
        this.idEstudiante = idEstudiante;
        this.nombrecompleto = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.Carrera = Carrera;
    }
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombrecompleto;
    }

    public void setNombre(String nombre) {
        this.nombrecompleto = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public String getCarrera() {
        return Carrera;
    }


    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
