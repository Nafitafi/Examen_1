/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.org.examen_1_00000262728;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Clase auxiliar para tener una paleta de colores clara y no andar batallando.
 * @author nafbr
 */
public class Tema {
    public static final Color FONDO = new Color(0xF7F8FF);
    public static final Color AZUL_OSCURO = new Color(0x00246B);
    public static final Color AZUL = new Color(0x1F5FD6);
    public static final Color CAMPO = new Color(0xEFF3FD);
    public static final Color BORDE = new Color(0xE6E9F2);
    public static final Color TEXTO = new Color(0x1F2937);
    public static final Color TEXTO_SUAVE = new Color(0x6B7280);

    private static final Font BASE = new Font("SansSerif", Font.PLAIN, 12);

    private Tema() {
    }

    public static Font fuente(int estilo, float tamanio) {
        return BASE.deriveFont(estilo, tamanio);
    }

    public static JLabel etiqueta(String texto, int estilo, float tamanio, Color color) {
        JLabel l = new JLabel(texto);
        l.setFont(fuente(estilo, tamanio));
        l.setForeground(color);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    public static void estiloTarjeta(JPanel p) {
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDE, 1, true), new EmptyBorder(16, 16, 16, 16)));
    }

    public static JButton botonPrimario(String texto) {
        JButton b = new JButton(texto);
        b.setUI(new BasicButtonUI());
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBackground(AZUL_OSCURO);
        b.setForeground(Color.WHITE);
        b.setFont(fuente(Font.BOLD, 12));
        b.setBorder(new EmptyBorder(12, 16, 12, 16));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    public static JButton botonSecundario(String texto) {
        JButton b = new JButton(texto);
        b.setUI(new BasicButtonUI());
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBackground(CAMPO);
        b.setForeground(AZUL_OSCURO);
        b.setFont(fuente(Font.BOLD, 10));
        b.setBorder(new EmptyBorder(5, 12, 5, 12));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    //Cosillas del CSS
    private static final float ESCALA_CSS_PX = 1.3f;

    public static String html(String texto, int ancho) {
        int css = Math.round(ancho / ESCALA_CSS_PX);
        return "<html><body style='width:" + css + "px'>" + escapar(texto) + "</body></html>";
    }

    private static String escapar(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
