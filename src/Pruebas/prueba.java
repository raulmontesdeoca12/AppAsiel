/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        prueba();
    }
    
    public static void prueba()
    {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
        
        System.out.println("Now:    "+date.format(now));
    }
}
