/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_1_00000262728_fachada;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.AlumnoResumenDTO;
import examen_1_00000262728_DTOS.ConstanciaDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Todito simulado
 * @author nafbr
 */
public class GenerarConstanciaFachada implements IGenerarConstanciaFachada{
    private static final String[] UNIDADES = {
            "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve",
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete",
            "dieciocho", "diecinueve", "veinte", "veintiuno", "veintidós", "veintitrés",
            "veinticuatro", "veinticinco", "veintiséis", "veintisiete", "veintiocho", "veintinueve"};
    private static final String[] DECENAS = {
            "", "", "", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
    private static final String[] MESES = {
            "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio",
            "agosto", "septiembre", "octubre", "noviembre", "diciembre"};

    private final Map<String, AlumnoDetalleDTO> alumnos = new LinkedHashMap<>();
    private final AtomicInteger consecutivo = new AtomicInteger(1);

    public GenerarConstanciaFachada() {
        registrar("00000262728", "Sofía Valentina Méndez Torres", "Ingeniería en Software y Sistemas", 42, 7, 6);
        registrar("00000262731", "Carlos Eduardo Rivera Cruz", "Ingeniería en Software y Sistemas", 39, 5, 6);
        registrar("00000262745", "Mariana Gómez Pérez", "Licenciatura en Ciencias de la Computación", 45, 3, 7);
        registrar("00000198403", "Luis Fernando Ortega Salas", "Ingeniería Industrial", 36, 9, 5);
        registrar("00000301152", "Ana Paula Ríos Domínguez", "Arquitectura", 48, 1, 8);
    }

    private void registrar(String id, String nombre, String programa, int creditos, int semestre, int materias) {
        alumnos.put(id, new AlumnoDetalleDTO(id, nombre, programa, creditos, semestre, materias));
    }

    @Override
    public List<AlumnoResumenDTO> buscarAlumnosPorID(String idParcial) {
        List<AlumnoResumenDTO> resultado = new ArrayList<>();
        if (idParcial == null || idParcial.isBlank()) {
            return resultado;
        }
        for (AlumnoDetalleDTO a : alumnos.values()) {
            if (a.id().contains(idParcial.trim())) {
                resultado.add(new AlumnoResumenDTO(a.id(), a.nombre()));
            }
        }
        return resultado;
    }

    @Override
    public AlumnoDetalleDTO obtenerDetalleAlumno(String idAlumno) {
        return alumnos.get(idAlumno);
    }

    @Override
    public ConstanciaDTO generarConstanciaAlumno(String idAlumno) {
        AlumnoDetalleDTO a = alumnos.get(idAlumno);
        if (a == null) {
            throw new IllegalArgumentException("Alumno inexistente: " + idAlumno);
        }
        LocalDate hoy = LocalDate.now();
        String periodo = hoy.getMonthValue() <= 6
                ? "Enero – Junio " + hoy.getYear()
                : "Agosto – Diciembre " + hoy.getYear();

        String parrafo1 = String.format(
                "Se encuentra debidamente inscrito(a) y con vigencia académica en el %d° Semestre "
                        + "durante el ciclo escolar vigente %s, cursando de forma presencial y regular "
                        + "un total de %d unidades de aprendizaje que equivalen a %d créditos académicos oficiales.",
                a.semestreInscrito(), periodo, a.cantidadMaterias(), a.creditos());

        String dia = hoy.getDayOfMonth() == 1
                ? "al primer día"
                : "a los " + enLetras(hoy.getDayOfMonth()) + " días";
        String parrafo2 = "A petición de la parte interesada y para los fines legales, administrativos "
                + "o becarios a que haya lugar, se extiende la presente constancia en la Ciudad Universitaria, "
                + dia + " del mes de " + MESES[hoy.getMonthValue() - 1]
                + " de " + anioEnLetras(hoy.getYear()) + ".";

        String folio = String.format("CE-%d-%06d", hoy.getYear(), consecutivo.getAndIncrement());
        Date fecha = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new ConstanciaDTO(folio, fecha, periodo, parrafo1 + "\n" + parrafo2);
    }

    
    //Utilidades
    private static String enLetras(int n) {
        if (n < 30) {
            return UNIDADES[n];
        }
        return DECENAS[n / 10] + (n % 10 == 0 ? "" : " y " + UNIDADES[n % 10]);
    }

    private static String anioEnLetras(int anio) {
        if (anio == 2000) {
            return "dos mil";
        }
        if (anio > 2000 && anio < 2100) {
            return "dos mil " + enLetras(anio - 2000);
        }
        return String.valueOf(anio);
    }
}
