/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_DTOS.AlumnoDetalleDTO;
import examen_1_00000262728_DTOS.ConstanciaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Objeto de presentación. Al ser dinamico y tener lógica propia, se toma como
 * su propio componente. Representa a la constancia generada.
 *
 * @author nafbr
 */
public class PnlConstancia extends JPanel{

    private static final int ANCHO_TEXTO = 500;

    private final JLabel lblNombre = Tema.etiqueta("", Font.BOLD, 16, Tema.AZUL_OSCURO);
    private final JLabel lblPrograma = Tema.etiqueta("", Font.PLAIN, 11, Tema.TEXTO);
    private final JLabel lblMatricula = Tema.etiqueta("", Font.BOLD, 14, Tema.AZUL);
    private final JPanel parrafos = new JPanel();
    private final JLabel lblPie = Tema.etiqueta("", Font.PLAIN, 10, Tema.TEXTO_SUAVE);

    public PnlConstancia() {
        setBackground(Color.WHITE);
        setBorder(new CompoundBorder(new LineBorder(Tema.BORDE, 1, true), new EmptyBorder(26, 28, 20, 28)));
        setLayout(new BorderLayout(0, 16));

        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearCuerpo(), BorderLayout.CENTER);
        add(lblPie, BorderLayout.SOUTH);
        setVisible(false);
    }

    public void mostrarConstancia(IModeloGenerarConstancia modelo) {
        ConstanciaDTO c = modelo.getConstanciaActual();
        AlumnoDetalleDTO a = modelo.getAlumnoSeleccionado();
        if (c == null || a == null) {
            setVisible(false);
            return;
        }
        lblNombre.setText(a.nombre().toUpperCase(Locale.ROOT));
        lblPrograma.setText(a.programaEdu());
        lblMatricula.setText(a.id());

        parrafos.removeAll();
        for (String p : c.contenido().split("\n")) {
            JLabel l = Tema.etiqueta(Tema.html(p, ANCHO_TEXTO), Font.PLAIN, 12, Tema.TEXTO);
            parrafos.add(l);
            parrafos.add(Box.createVerticalStrut(10));
        }
        lblPie.setText("Folio: " + c.folio()
                    + "   ·   Fecha de emisión: " + new SimpleDateFormat("dd/MM/yyyy").format(c.fechaEmision())
                + "   ·   Periodo: " + c.periodo());
        setVisible(true);
        revalidate();
        repaint();
    }

    private JPanel crearEncabezado() {
        JPanel h = new JPanel();
        h.setOpaque(false);
        h.setLayout(new BoxLayout(h, BoxLayout.Y_AXIS));

        JLabel sup = Tema.etiqueta("DOCUMENTO OFICIAL CERTIFICADO", Font.PLAIN, 9, Tema.TEXTO_SUAVE);
        JLabel titulo = Tema.etiqueta("CONSTANCIA DE ESTUDIOS Y ALUMNO INSCRITO", Font.BOLD, 17, Tema.AZUL_OSCURO);
        sup.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel linea = new JPanel();
        linea.setBackground(Tema.AZUL);
        Dimension d = new Dimension(64, 3);
        linea.setPreferredSize(d);
        linea.setMinimumSize(d);
        linea.setMaximumSize(d);
        linea.setAlignmentX(Component.CENTER_ALIGNMENT);

        h.add(sup);
        h.add(Box.createVerticalStrut(4));
        h.add(titulo);
        h.add(Box.createVerticalStrut(6));
        h.add(linea);
        return h;
    }

    private JPanel crearCuerpo() {
        JPanel cuerpo = new JPanel();
        cuerpo.setOpaque(false);
        cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));

        cuerpo.add(Tema.etiqueta("A QUIEN CORRESPONDA:", Font.BOLD, 11, Tema.TEXTO));
        cuerpo.add(Box.createVerticalStrut(6));
        cuerpo.add(Tema.etiqueta(Tema.html(
                "La Dirección de Servicios Escolares que suscribe, con base en los registros asentados "
                + "en el Sistema Integral de Control Escolar institucional, hace constar que el (la) alumno(a):",
                ANCHO_TEXTO), Font.PLAIN, 12, Tema.TEXTO));
        cuerpo.add(Box.createVerticalStrut(12));
        cuerpo.add(crearTarjetaAlumno());
        cuerpo.add(Box.createVerticalStrut(10));

        parrafos.setOpaque(false);
        parrafos.setLayout(new BoxLayout(parrafos, BoxLayout.Y_AXIS));
        parrafos.setAlignmentX(Component.LEFT_ALIGNMENT);
        cuerpo.add(parrafos);
        return cuerpo;
    }

    private JPanel crearTarjetaAlumno() {
        JPanel t = new JPanel(new BorderLayout(16, 0)) {
            @Override
            public Dimension getMaximumSize() { 
                return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
            }
        };
        t.setBackground(Tema.CAMPO);
        t.setBorder(new EmptyBorder(14, 16, 14, 16));
        t.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel izq = new JPanel();
        izq.setOpaque(false);
        izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
        izq.add(Tema.etiqueta("NOMBRE COMPLETO", Font.PLAIN, 9, Tema.TEXTO_SUAVE));
        izq.add(lblNombre);
        izq.add(lblPrograma);

        JPanel der = new JPanel();
        der.setBackground(Color.WHITE);
        der.setBorder(new EmptyBorder(8, 12, 8, 12));
        der.setLayout(new BoxLayout(der, BoxLayout.Y_AXIS));
        der.add(Tema.etiqueta("MATRÍCULA", Font.PLAIN, 9, Tema.TEXTO_SUAVE));
        der.add(lblMatricula);

        t.add(izq, BorderLayout.CENTER);
        t.add(der, BorderLayout.EAST);
        return t;
    }
}
