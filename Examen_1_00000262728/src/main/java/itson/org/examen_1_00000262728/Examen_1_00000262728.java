/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.org.examen_1_00000262728;

import examen_1_00000262728_fachada.GenerarConstanciaFachada;
import examen_1_00000262728_fachada.IGenerarConstanciaFachada;

/**
 *
 * @author nafbr
 */
public class Examen_1_00000262728 {

    public static void main(String[] args) {
        IGenerarConstanciaFachada fachada = new GenerarConstanciaFachada();
        ModeloGenerarConstancia modelo = new ModeloGenerarConstancia(fachada);
        ControlGenerarConstancia control = new ControlGenerarConstancia(modelo);
        VistaGenerarConstancia vista = new VistaGenerarConstancia(control);

        modelo.suscribir(vista);
        modelo.notificarSubs();  
        vista.setVisible(true);
    }
}
