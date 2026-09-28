/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.AlumnoResumenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Objeto de presentación. Al ser dinamico y tener lógica propia, se toma como
 * su propio componente
 *
 * @author nafbr
 */
public class PnlBloqueAlumno extends JPanel {

    private final Consumer<String> alSeleccionar;

    public PnlBloqueAlumno(Consumer<String> alSeleccionar) {
        this.alSeleccionar = alSeleccionar;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void mostrarCoincidencias(IModeloGenerarConstancia modelo) {
        removeAll();
        List<AlumnoResumenDTO> lista = modelo.getCoincidencias();
        AlumnoDetalleDTO seleccionado = modelo.getAlumnoSeleccionado();
        String idSeleccionado = seleccionado == null ? null : seleccionado.id();

        if (lista.isEmpty()) {
            add(Tema.etiqueta("Sin alumnos que mostrar. Escriba un ID o matrícula para buscar.",
                    Font.PLAIN, 12, Tema.TEXTO_SUAVE));
        } else {
            for (AlumnoResumenDTO a : lista) {
                add(crearFila(a, a.id().equals(idSeleccionado)));
                add(Box.createVerticalStrut(6));
            }
        }
        revalidate();
        repaint();
    }

    private JPanel crearFila(AlumnoResumenDTO alumno, boolean seleccionado) {
        JPanel fila = new JPanel(new BorderLayout(8, 0));
        fila.setBackground(seleccionado ? Color.WHITE : new Color(0xFAFBFF));
        Border marco = seleccionado
                ? new LineBorder(Tema.AZUL, 2, true)
                : new LineBorder(Tema.BORDE, 1, true);
        fila.setBorder(new CompoundBorder(marco, new EmptyBorder(9, 12, 9, 12)));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(Tema.etiqueta(alumno.id(), Font.PLAIN, 10,
                seleccionado ? Tema.AZUL : Tema.TEXTO_SUAVE));
        textos.add(Tema.etiqueta(alumno.nombre(),
                seleccionado ? Font.BOLD : Font.PLAIN, 12,
                seleccionado ? Tema.AZUL_OSCURO : Tema.TEXTO));
        fila.add(textos, BorderLayout.CENTER);

        if (!seleccionado) {
            JButton boton = Tema.botonSecundario("Seleccionar");
            boton.addActionListener(e -> alSeleccionar.accept(alumno.id()));
            fila.add(boton, BorderLayout.EAST);
        }
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, fila.getPreferredSize().height));
        return fila;
    }
}
