/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.AlumnoResumenDTO;
import examen_1_00000262728_DTOS.ConstanciaDTO;
import examen_1_00000262728_fachada.IGenerarConstanciaFachada;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Modelo del CU: habla con la fachada y notifica a su único observador.
 *
 * @author nafbr
 */
public class ModeloGenerarConstancia implements IModeloGenerarConstancia{

    private final IGenerarConstanciaFachada fachada;
    private IObserverGenerarConstancia observador; 

    private List<AlumnoResumenDTO> coincidencias = new ArrayList<>();
    private AlumnoDetalleDTO alumnoSeleccionado;
    private ConstanciaDTO constanciaActual;
    private String mensajeError;

    public ModeloGenerarConstancia(IGenerarConstanciaFachada fachada) {
        this.fachada = fachada;
    }

    public void suscribir(IObserverGenerarConstancia observador) {
        this.observador = observador;
    }

    public void notificarSubs() {
        if (observador != null) {
            observador.update(this);
        }
    }

    public void buscarCoincidencias(String idParcial) {
        mensajeError = null;
        alumnoSeleccionado = null; // una nueva búsqueda invalida la selección previa
        constanciaActual = null;
        String consulta = idParcial == null ? "" : idParcial.trim();
        coincidencias = consulta.isEmpty()
                ? new ArrayList<>()
                : new ArrayList<>(fachada.buscarAlumnosPorID(consulta));
        notificarSubs();
    }

    public void seleccionarAlumno(String idAlumno) {
        mensajeError = null;
        constanciaActual = null;
        alumnoSeleccionado = fachada.obtenerDetalleAlumno(idAlumno);
        if (alumnoSeleccionado == null) {
            mensajeError = "No se encontró el alumno seleccionado.";
        }
        notificarSubs();
    }

    public void emitirConstancia() {
        mensajeError = null;
        if (alumnoSeleccionado == null) {
            mensajeError = "Debe seleccionar un alumno antes de generar la constancia.";
            notificarSubs();
            return;
        }
        constanciaActual = fachada.generarConstanciaAlumno(alumnoSeleccionado.id());
        notificarSubs();
    }

    @Override
    public List<AlumnoResumenDTO> getCoincidencias() {
        return Collections.unmodifiableList(coincidencias);
    }

    @Override
    public AlumnoDetalleDTO getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    @Override
    public ConstanciaDTO getConstanciaActual() {
        return constanciaActual;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}
