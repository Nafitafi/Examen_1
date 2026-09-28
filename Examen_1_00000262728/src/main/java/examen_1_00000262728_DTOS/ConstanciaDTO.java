/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package examen_1_00000262728_DTOS;

import java.util.Date;

/**
 * DTO dedicada a la constancia emitida.
 * @author nafbr
 */
public record ConstanciaDTO(String folio,
                            Date fechaEmision,
                            String periodo,
                            String contenido) {
}
