/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Correo;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Asiel 77
 */
public class FiltraExtension extends FileFilter{
    private String descripcion = "Solo Archivos";
    private String[] extensiones;

    public FiltraExtension(String[] extensiones) {
        this.extensiones = extensiones;
    }

    public FiltraExtension(String extension) {
        this(new String[] {extension});
    }
    
    
    public FiltraExtension (String descripcion, String[] extensiones)
    {
        this.descripcion=descripcion;
        this.extensiones=extensiones;
    }
    
    
    
    private void generarDescripcion()
    {
        for (int i=0;i<extensiones.length;i++)
        {
            descripcion += extensiones[i];
        }
    }
    @Override
    public boolean accept(File file) {
        if(file.isDirectory())
        {
            return true;
        }else
        {
            for(int i=0;i<extensiones.length;i++)
            {
                if(file.getName().endsWith(extensiones[i]))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return descripcion;
    }
    
}
