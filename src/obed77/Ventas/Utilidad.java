/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Ventas;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Asiel 77
 */
class Utilidad {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String png = "png";

    /*
     * Método para obtener la extensión de cada archivo desde nuestro JFileChooser .
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    //Método para obtener una imagen desde una URL.
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utilidad.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
}
