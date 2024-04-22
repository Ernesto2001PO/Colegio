package Principal;

import ClasesImplementandoDao.EstudianteDaoImpl;
import ClasesImplementandoDao.EstudianteMateriaImpl;
import ClasesImplementandoDao.MateriaDaoimpl;
import Clases.Estudiantes;
import Clases.EstudianteMateria;
import Clases.Materia;
import Daos.EstudianteDao;
import Daos.EstudianteMateriaDao;
import Daos.MateriaDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

public class PanelPrincipal extends JFrame {

    Logger logger = Logger.getLogger(PanelPrincipal.class.getName());

    public PanelPrincipal() {
        // Crear los JFrames y JTextAreas fuera de los ActionListener
        JFrame frameMateria = new JFrame("Materias");
        frameMateria.setMinimumSize(new Dimension(300, 200));
        JTextArea textAreaMateria = new JTextArea();

        //---------------------------------------------------------------//

        JFrame frameEstudiante = new JFrame("Estudiantes");
        frameEstudiante.setMinimumSize(new Dimension(300, 200));
        JTextArea textAreaEstudiante = new JTextArea();

        //---------------------------------------------------------------//

        JFrame frameEstudiantesEnMateria = new JFrame("Estudiantes en Materia");
        frameEstudiantesEnMateria.setMinimumSize(new Dimension(300, 200));
        JTextArea textAreaEstudiantesEnMateria = new JTextArea();

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();

        // Crear las opciones del menú
        JMenu menuMaterias = new JMenu("Materias");
        JMenu menuEstudiantes = new JMenu("Estudiantes");

        // Crear las acciones del menú
        JMenuItem verMaterias = new JMenuItem("Ver materias");
        JMenuItem verEstudiantes = new JMenuItem("Ver estudiantes");
        JMenuItem inscribirEstudianteMateria = new JMenuItem("Inscribir estudiante en Materia");

        // Crear las opciones del submenú "Gestionar estudiantes"
        JMenuItem inscripcionEstudiante = new JMenuItem("Inscribir estudiante");
        JMenuItem eliminarEstudiante = new JMenuItem("Eliminar estudiante");
        JMenuItem modificarEstudiante = new JMenuItem("Modificar estudiante");


        // Crear el submenú "Gestionar estudiantes" y agregar las opciones
        JMenu gestionarEstudiantes = new JMenu("Gestionar estudiantes");
        gestionarEstudiantes.add(inscripcionEstudiante);
        gestionarEstudiantes.add(eliminarEstudiante);
        gestionarEstudiantes.add(modificarEstudiante);

        // Agregar las acciones a las opciones del menú
        menuMaterias.add(verMaterias);
        menuMaterias.add(inscribirEstudianteMateria);
        menuEstudiantes.add(verEstudiantes);
        menuEstudiantes.add(gestionarEstudiantes);

        // Agregar las opciones del menú a la barra de menú
        menuBar.add(menuMaterias);
        menuBar.add(menuEstudiantes);

        // Configurar la barra de menú del JFrame
        this.setJMenuBar(menuBar);

        logger.info("Se ha creado la barra de menú");
        logger.info("Se ha creado el menú Materias");
        logger.info("Se ha creado el menú Estudiantes");

        inscribirEstudianteMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del estudiante
                String estudianteIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante: ");
                int estudianteId = Integer.parseInt(estudianteIdInput);

                // Obtener el ID de la materia
                String materiaIdInput = JOptionPane.showInputDialog("Ingrese el ID de la materia: ");
                int materiaId = Integer.parseInt(materiaIdInput);

                // Crear una nueva instancia de EstudianteMateria
                EstudianteMateria estudianteMateria = new EstudianteMateria(estudianteId, materiaId);

                // Crear una nueva instancia de EstudianteMateriaDao
                EstudianteMateriaDao estudianteMateriaDao = new EstudianteMateriaImpl();

                // Insertar la relación en la base de datos
                estudianteMateriaDao.createEstudianteMateria(estudianteMateria);

                JOptionPane.showMessageDialog(null, "Estudiante inscrito en la materia exitosamente!");
            }
        });


        verMaterias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar el JTextArea
                textAreaMateria.setText("");

                // Implementar la lógica para ver las materias de la base de datos
                // Obtén una instancia de MateriaDAO
                MateriaDao materiaDAO = new MateriaDaoimpl();

                // Obtén todas las materias
                List<Materia> materias = materiaDAO.getAllMaterias();

                // Muestra las materias al usuario
                for (Materia materia : materias) {
                    //Muestra en una nueva ventana pequeña
                    textAreaMateria.append(materia.getNombre() + "\n");
                }
                frameMateria.getContentPane().add(textAreaMateria);
                frameMateria.setVisible(true);
            }

        });
        logger.info("Se ha creado la opción Ver Materias");
        verEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar el JTextArea
                textAreaEstudiante.setText("");

                // Implementar la lógica para ver los estudiantes de la base de datos
                // Obtén una instancia de EstudianteDAO
                EstudianteDao estudianteDAO = new EstudianteDaoImpl();

                // Obtén todos los estudiantes
                List<Estudiantes> estudiantes = estudianteDAO.getAllEstudiantes();

                // Muestra los estudiantes al usuario
                for (Estudiantes estudiante : estudiantes) {
                    textAreaEstudiante.append(estudiante.getIdEstudiante() + " - " + estudiante.getNombre() + "\n");
                }
                frameEstudiante.getContentPane().add(textAreaEstudiante);
                frameEstudiante.setVisible(true);
            }
        });


        JMenuItem verMateriasEstudiante = new JMenuItem("Ver materias de estudiante");
        menuMaterias.add(verMateriasEstudiante);

        verMateriasEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del estudiante
                String estudianteIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante: ");
                int estudianteId = Integer.parseInt(estudianteIdInput);

                // Crear una nueva instancia de EstudianteMateriaDao
                EstudianteMateriaDao estudianteMateriaDao = new EstudianteMateriaImpl();

                // Obtener todas las relaciones EstudianteMateria para el estudiante
                List<EstudianteMateria> estudianteMaterias = estudianteMateriaDao.getEstudianteMateriasByEstudianteId(estudianteId);

                // Crear un DefaultListModel y un JList
                DefaultListModel<String> listModel = new DefaultListModel<>();
                JList<String> list = new JList<>(listModel);

                // Verificar si la lista estudianteMaterias está vacía
                if (estudianteMaterias.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El estudiante con ID: " + estudianteId + " no está inscrito en ninguna materia.");
                } else {
                    // Crear una nueva instancia de MateriaDao
                    MateriaDao materiaDao = new MateriaDaoimpl();

                    // Obtener y mostrar las materias para el estudiante
                    for (EstudianteMateria estudianteMateria : estudianteMaterias) {
                        Materia materia = materiaDao.getMateriaById(estudianteMateria.getIdMateria());
                        listModel.addElement("Materia: " + materia.getNombre());
                    }

                    // Mostrar la lista en un JScrollPane en un JOptionPane
                    JOptionPane.showMessageDialog(null, new JScrollPane(list));
                }
            }
        });




        // MUESTRA LAS NOTAS DE UN ESTUDIANTE EN UNA MATERIA ESPECÍFICA

        JMenuItem verNotasMateriaEspecifica = new JMenuItem("Ver Notas de Materia Específica");
        menuMaterias.add(verNotasMateriaEspecifica);

        verNotasMateriaEspecifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del estudiante
                String estudianteIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante: ");
                int estudianteId = Integer.parseInt(estudianteIdInput);

                // Obtener el ID de la materia
                String materiaIdInput = JOptionPane.showInputDialog("Ingrese el ID de la materia: ");
                int materiaId = Integer.parseInt(materiaIdInput);

                // Crear una nueva instancia de EstudianteMateriaDao
                EstudianteMateriaDao estudianteMateriaDao = new EstudianteMateriaImpl();

                // Obtener todas las notas para el estudiante y la materia especificados
                List<EstudianteMateria> estudianteMaterias = estudianteMateriaDao.getNotasByEstudianteIdAndMateriaId(estudianteId, materiaId);

                // Crear un DefaultListModel y un JList
                DefaultListModel<String> listModel = new DefaultListModel<>();
                JList<String> list = new JList<>(listModel);

                // Verificar si la lista estudianteMaterias está vacía
                if (estudianteMaterias.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El estudiante con ID: " + estudianteId + " no tiene notas para la materia con ID: " + materiaId);
                } else {
                    // Mostrar las notas al usuario
                    for (EstudianteMateria estudianteMateria : estudianteMaterias) {
                        double notas = estudianteMateria.getNotas();
                        String tipoNota = estudianteMateria.getTipoNota();
                        listModel.addElement("Tipo de nota: " + tipoNota + ", Nota: " + notas);
                    }


                    // ---------------------BOTON PONDERAR NOTAS ---------------------------

                    // Mostrar la lista en un JScrollPane en un JOptionPane
                    JOptionPane.showMessageDialog(null, new JScrollPane(list));
                    // Crear un botón "Ponderar notas"
                    JButton ponderarButton = new JButton("Ponderar notas");

                    // Agregar un ActionListener al botón
                    ponderarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Inicializar la suma de las notas
                            double sumOfGrades = 0;

                            // Iterar sobre las notas y sumarlas
                            for (int i = 0; i < listModel.size(); i++) {
                                String[] parts = listModel.get(i).split(",");
                                double nota = Double.parseDouble(parts[1].split(":")[1].trim());
                                sumOfGrades += nota;
                            }

                            // Mostrar la suma de las notas al usuario
                            JOptionPane.showMessageDialog(null, "Suma de todas las notas: " + sumOfGrades);
                        }
                    });


                    // Crear un panel para contener el botón "Ponderar notas"
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.add(ponderarButton);
                    // Mostrar el panel de botones en el JOptionPane
                    JOptionPane.showMessageDialog(null, buttonPanel);
                }
            }
        });

        inscripcionEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the student's ID
                String studentIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante: ");
                int studentId = Integer.parseInt(studentIdInput);

                // Get the student's name
                String studentName = JOptionPane.showInputDialog("Ingrese el nombre del estudiante: ");

                // Get the student's birth date
                String studentBirthDateInput = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del estudiante (yyyy-mm-dd): ");
                Date studentBirthDate = Date.valueOf(studentBirthDateInput); // Convert the input to a java.sql.Date

                // Get the student's career
                String studentCareer = JOptionPane.showInputDialog("Ingrese la carrera del estudiante: ");

                // Create a new instance of Estudiante
                Estudiantes newStudent = new Estudiantes(studentId, studentName, studentBirthDate, studentCareer);

                // Insert this student into the database
                EstudianteDao estudianteDao = new EstudianteDaoImpl();
                estudianteDao.createEstudiante(newStudent);

                JOptionPane.showMessageDialog(null, "Estudiante inscrito exitosamente!");
            }
        });


        eliminarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the student's ID
                String studentIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante a eliminar: ");

                // Convert the input to an integer
                int studentId = Integer.parseInt(studentIdInput);

                // Create a new instance of Estudiante
                Estudiantes studentToDelete = new Estudiantes(studentId, null, null, null);

                // Delete this student from the database
                // You need to create a method in EstudianteDaoImpl for this
                EstudianteDao estudianteDao = new EstudianteDaoImpl();
                estudianteDao.deleteEstudiante(studentToDelete);

                JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente!");
            }
        });

        modificarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the student's ID and new details
                String studentIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante a modificar: ");
                String newStudentNameInput = JOptionPane.showInputDialog("Ingrese el nuevo nombre del estudiante: ");

                // Convert the inputs to appropriate data types
                int studentId = Integer.parseInt(studentIdInput);

                // Create a new instance of Estudiante with the new details
                Estudiantes studentToUpdate = new Estudiantes(studentId, newStudentNameInput, null, null);

                // Update this student in the database
                // You need to create a method in EstudianteDaoImpl for this
                EstudianteDao estudianteDao = new EstudianteDaoImpl();
                estudianteDao.updateEstudiante(studentToUpdate);

                JOptionPane.showMessageDialog(null, "Estudiante modificado exitosamente!");
            }
        });


        JButton agregarNotaButton = new JButton("Agregar nota");
        menuMaterias.add(agregarNotaButton);
        agregarNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the student's ID
                String studentIdInput = JOptionPane.showInputDialog("Ingrese el ID del estudiante: ");
                int studentId = Integer.parseInt(studentIdInput);

                // Get the subject's ID
                String subjectIdInput = JOptionPane.showInputDialog("Ingrese el ID de la materia: ");
                int subjectId = Integer.parseInt(subjectIdInput);

                // Get the type of grade
                String gradeType = JOptionPane.showInputDialog("Ingrese el tipo de nota: ");

                // Get the grade
                String gradeInput = JOptionPane.showInputDialog("Ingrese la nota: ");
                double grade = Double.parseDouble(gradeInput);

                // Add the grade to the student for the specific subject
                EstudianteMateriaDao estudianteMateriaDao = new EstudianteMateriaImpl();
                estudianteMateriaDao.addNotaToEstudianteMateria(studentId, subjectId, gradeType, grade);

                JOptionPane.showMessageDialog(null, "Nota agregada exitosamente!");
            }
        });


        // Configurar el JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0x125644));

    }

    public static void main(String[] args) {
        // Crear una instancia de PanelPrincipal
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        panelPrincipal.setVisible(true);

    }
}