/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Objeto de presentación. Al ser dinamico y tener lógica propia, se toma como
 * su propio componente
 *
 * @author nafbr
 */

public class PnlVistaPreviaAlumno extends JPanel{
    
private static final String SIN_DATO = "—";

    private final JLabel lblNombre = Tema.etiqueta("", Font.BOLD, 16, Tema.AZUL_OSCURO);
    private final JLabel lblCarrera = Tema.etiqueta(SIN_DATO, Font.BOLD, 11, Tema.TEXTO);
    private final JLabel lblSemestre = Tema.etiqueta(SIN_DATO, Font.BOLD, 11, Tema.TEXTO);
    private final JLabel lblMaterias = Tema.etiqueta(SIN_DATO, Font.BOLD, 11, Tema.TEXTO);

    public PnlVistaPreviaAlumno(Runnable alClickGenerar) {
        Tema.estiloTarjeta(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Tema.etiqueta("ESTUDIANTE SELECCIONADO", Font.PLAIN, 10, Tema.TEXTO_SUAVE));
        add(Box.createVerticalStrut(2));
        add(lblNombre);
        add(Box.createVerticalStrut(8));

        JSeparator sep = new JSeparator();
        sep.setForeground(Tema.BORDE);
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        add(sep);
        add(Box.createVerticalStrut(10));

        JPanel datos = new JPanel(new GridLayout(2, 2, 16, 10)) {
            @Override
            public Dimension getMaximumSize() {
                return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
            }
        };
        datos.setOpaque(false);
        datos.setAlignmentX(Component.LEFT_ALIGNMENT);
        datos.add(campo("Carrera", lblCarrera));
        datos.add(campo("Semestre Actual", lblSemestre));
        datos.add(campo("Materias Inscritas", lblMaterias));
        JPanel vacio = new JPanel();
        vacio.setOpaque(false);
        datos.add(vacio);
        add(datos);
        add(Box.createVerticalStrut(16));

        JButton btnGenerar = Tema.botonPrimario("Generar Constancia");
        btnGenerar.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnGenerar.getPreferredSize().height));
        btnGenerar.addActionListener(e -> alClickGenerar.run());
        add(btnGenerar);

        lblNombre.setText("Ningún estudiante seleccionado");
    }

    public void mostrarAlumno(IModeloGenerarConstancia modelo) {
        AlumnoDetalleDTO a = modelo.getAlumnoSeleccionado();
        if (a == null) {
            lblNombre.setText("Ningún estudiante seleccionado");
            lblCarrera.setText(SIN_DATO);
            lblSemestre.setText(SIN_DATO);
            lblMaterias.setText(SIN_DATO);
        } else {
            lblNombre.setText(a.nombre() + " (" + a.id() + ")");
            lblCarrera.setText(a.programaEdu());
            lblSemestre.setText(a.semestreInscrito() + "° Semestre");
            lblMaterias.setText(a.cantidadMaterias() + " asignaturas (" + a.creditos() + " créditos)");
        }
        revalidate();
        repaint();
    }

    private static JPanel campo(String titulo, JLabel valor) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Tema.etiqueta(titulo, Font.PLAIN, 10, Tema.TEXTO_SUAVE));
        p.add(valor);
        return p;
    }
}
