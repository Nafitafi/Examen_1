/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.AlumnoResumenDTO;
import examen_1_00000262728_DTOS.ConstanciaDTO;
import java.util.List;

/**
 * Modelo segregado Read only
 * @author nafbr
 */
public interface IModeloGenerarConstancia {

    public List<AlumnoResumenDTO> getCoincidencias();

    public AlumnoDetalleDTO getAlumnoSeleccionado();

    public ConstanciaDTO getConstanciaActual();

    public String getMensajeError();
}
