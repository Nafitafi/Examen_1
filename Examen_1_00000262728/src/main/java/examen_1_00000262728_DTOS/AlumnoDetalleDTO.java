/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_1_00000262728_DTOS;

/**
 * Detalle del alumno. 
 * @author nafbr
 */
public record AlumnoDetalleDTO(String id,
                               String nombre,
                               String programaEdu,
                               Integer creditos,
                               Integer semestreInscrito,
                               Integer cantidadMaterias) {
}
