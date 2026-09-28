/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_1_00000262728_fachada;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.AlumnoResumenDTO;
import examen_1_00000262728_DTOS.ConstanciaDTO;
import java.util.List;

/**
 * Punto de contacto con el negocio.
 * @author nafbr
 */
public interface IGenerarConstanciaFachada {

    List<AlumnoResumenDTO> buscarAlumnosPorID(String idParcial);

    AlumnoDetalleDTO obtenerDetalleAlumno(String idAlumno);

    ConstanciaDTO generarConstanciaAlumno(String idAlumno);
}
