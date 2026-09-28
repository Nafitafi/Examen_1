package itson.org.examen_1_00000262728;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Pantalla o bundary principal.
 *
 * @author nafbr
 */
public class VistaGenerarConstancia extends JFrame implements IObserverGenerarConstancia {

    private final ControlGenerarConstancia control;
    private IModeloGenerarConstancia modeloActual;

    private final JTextField campoId = new JTextField();
    private final PnlBloqueAlumno pnlBloqueAlumno;
    private final PnlVistaPreviaAlumno pnlVistaPrevia;
    private final PnlConstancia pnlConstancia = new PnlConstancia();
    private final JButton btnNueva = Tema.botonPrimario("Generar Constancia Nueva");
    private JScrollPane scroll;
    private JPanel raiz;

    public VistaGenerarConstancia(ControlGenerarConstancia control) {
        super("Constancia de Inscripción");
        this.control = control;
        this.pnlBloqueAlumno = new PnlBloqueAlumno(this::onAlumnoSeleccionado);
        this.pnlVistaPrevia = new PnlVistaPreviaAlumno(this::onClickGenerarConstancia);
        construirUI();
    }

    //Eventos del usuario
    public void onIdEscrito(String id) {
        control.filtrarAlumnos(id);
    }

    public void onAlumnoSeleccionado(String id) {
        control.seleccionarAlumno(id);
    }

    public void onClickGenerarConstancia() {
        control.generarConstancia();
    }

    //TODITO del observer
    @Override
    public void update(IModeloGenerarConstancia modelo) {
        this.modeloActual = modelo;
        pnlBloqueAlumno.mostrarCoincidencias(modelo);
        pnlVistaPrevia.mostrarAlumno(modelo);
        pnlConstancia.mostrarConstancia(modelo);
        btnNueva.setVisible(modelo.getConstanciaActual() != null);
        revalidate();
        repaint();
        if (modelo.getConstanciaActual() != null) {
            SwingUtilities.invokeLater(this::desplazarAConstancia);
        }
        if (modelo.getMensajeError() != null) {
            mostrarError();
        }
    }

    public void mostrarError() {
        JOptionPane.showMessageDialog(this, modeloActual.getMensajeError(),
                "Constancia de Inscripción", JOptionPane.WARNING_MESSAGE);
    }

    //La construcción de la interfaz como tal
    private void construirUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contenido = new JPanel(new BorderLayout(0, 16));
        contenido.setOpaque(false);
        contenido.add(crearEncabezado(), BorderLayout.NORTH);
        contenido.add(crearCuerpo(), BorderLayout.CENTER);

        raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Tema.FONDO);
        raiz.setBorder(new EmptyBorder(20, 28, 20, 28));
        raiz.add(contenido, BorderLayout.NORTH);

        scroll = new JScrollPane(raiz,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        setContentPane(scroll);

        btnNueva.setVisible(false);
        btnNueva.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnNueva.getPreferredSize().height));
        btnNueva.addActionListener(e -> onClickNuevaConstancia());

        ajustarATamanioDePantalla();
    }

    //No cabía en mi pantalla, tuve que ajustar
    private void ajustarATamanioDePantalla() {
        Rectangle u = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int ancho = Math.min(980, u.width);
        setMinimumSize(new Dimension(Math.min(640, u.width), Math.min(480, u.height)));
        setBounds(u.x + (u.width - ancho) / 2, u.y, ancho, u.height);
    }

    private void desplazarAConstancia() {
        JViewport vp = scroll.getViewport();
        Point p = SwingUtilities.convertPoint(pnlConstancia.getParent(), pnlConstancia.getLocation(), raiz);
        int max = Math.max(0, raiz.getHeight() - vp.getHeight());
        vp.setViewPosition(new Point(0, Math.min(Math.max(0, p.y - 12), max)));
    }

    private JPanel crearEncabezado() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(Tema.etiqueta("Constancia de Inscripción", Font.BOLD, 26, Tema.AZUL_OSCURO));
        p.add(Tema.etiqueta("Busca y selecciona al estudiante para generar su constancia oficial con validez académica.",
                Font.PLAIN, 12, Tema.TEXTO_SUAVE));
        return p;
    }

    private JPanel crearCuerpo() {
        JPanel cuerpo = new JPanel(new GridBagLayout());
        cuerpo.setOpaque(false);

        GridBagConstraints g = new GridBagConstraints();
        g.gridy = 0;
        g.anchor = GridBagConstraints.NORTHWEST;
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0;
        g.weightx = 0;
        g.insets = new Insets(0, 0, 0, 20);
        cuerpo.add(crearTarjetaBusqueda(), g);

        g.gridx = 1;
        g.weightx = 1;
        g.insets = new Insets(0, 0, 0, 0);
        cuerpo.add(crearColumnaDerecha(), g);
        return cuerpo;
    }

    private JPanel crearTarjetaBusqueda() {
        JPanel tarjeta = new JPanel();
        Tema.estiloTarjeta(tarjeta);
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        campoId.setFont(Tema.fuente(Font.BOLD, 16));
        campoId.setForeground(Tema.AZUL_OSCURO);
        campoId.setBackground(Tema.CAMPO);
        campoId.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(), new EmptyBorder(10, 14, 10, 14)));
        campoId.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoId.getPreferredSize().height));
        campoId.setAlignmentX(LEFT_ALIGNMENT);
        campoId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onIdEscrito(campoId.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onIdEscrito(campoId.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onIdEscrito(campoId.getText());
            }
        });

        tarjeta.add(Tema.etiqueta("Ingresa el ID o Matrícula del Alumno", Font.BOLD, 11, Tema.AZUL_OSCURO));
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(campoId);
        tarjeta.add(javax.swing.Box.createVerticalStrut(8));
        tarjeta.add(Tema.etiqueta("Ejemplo: 00000262728", Font.PLAIN, 10, Tema.TEXTO_SUAVE));
        tarjeta.setPreferredSize(new Dimension(300, tarjeta.getPreferredSize().height));

        //Para que la tarjeta no se estire verticalmente.
        JPanel envoltorio = new JPanel(new BorderLayout());
        envoltorio.setOpaque(false);
        envoltorio.add(tarjeta, BorderLayout.NORTH);
        return envoltorio;
    }

    private JPanel crearColumnaDerecha() {
        JPanel col = new JPanel(new GridBagLayout()) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.width = Math.max(d.width, 560);
                return d;
            }
        };
        col.setOpaque(false);

        JLabel titulo = Tema.etiqueta("Alumnos encontrados", Font.BOLD, 12, Tema.AZUL_OSCURO);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.NORTHWEST;

        g.gridy = 0;
        g.insets = new Insets(0, 0, 8, 0);
        col.add(titulo, g);
        g.gridy = 1;
        g.insets = new Insets(0, 0, 8, 0);
        col.add(pnlBloqueAlumno, g);
        g.gridy = 2;
        g.insets = new Insets(4, 0, 12, 0);
        col.add(pnlVistaPrevia, g);
        g.gridy = 3;
        col.add(pnlConstancia, g);
        g.gridy = 4;
        g.insets = new Insets(0, 0, 0, 0);
        col.add(btnNueva, g);
        return col;
    }

    private void onClickNuevaConstancia() {
        if (campoId.getText().isEmpty()) {
            onIdEscrito("");
        } else {
            campoId.setText(""); // el DocumentListener dispara onIdEscrito("")
        }
        campoId.requestFocusInWindow();
    }
}
