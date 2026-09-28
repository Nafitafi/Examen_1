/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

/**
 * Control del CU. Se encarga de manejar el flujo.
 * @author nafbr
 */
public class ControlGenerarConstancia {

    private final ModeloGenerarConstancia modelo;

    public ControlGenerarConstancia(ModeloGenerarConstancia modelo) {
        this.modelo = modelo;
    }

    public void filtrarAlumnos(String idParcial) {
        modelo.buscarCoincidencias(idParcial);
    }

    public void seleccionarAlumno(String idAlumno) {
        modelo.seleccionarAlumno(idAlumno);
    }

    public void generarConstancia() {
        modelo.emitirConstancia();
    }
}
