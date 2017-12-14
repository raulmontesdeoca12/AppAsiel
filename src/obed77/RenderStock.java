/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obed77;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import obed77.productos.Productos;

/**
 *
 * @author Saito
 */
public class RenderStock extends DefaultTableCellRenderer {
    private  int columna_patron;
    public RenderStock (int colpatron)
    {
        this.columna_patron=colpatron;
    }
    
        
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(Color.white);
        cell.setForeground(Color.black);
        int valor = Integer.parseInt(Productos.tabla_productos.getValueAt(row, columna_patron).toString());
        String estado = Productos.tabla_productos.getValueAt(row, 10).toString();
        if(isSelected)
        {
            setBackground(new Color(51,153,255));
            setForeground(Color.white);
            
        }else
        if(value instanceof String)
        {
        if(column==columna_patron)
        {
        if(valor==0)
        {
            cell.setBackground(new Color(255,51,51));
        }else
        
            if(valor<5)
            {
                cell.setBackground(Color.cyan);
            }else
            {
                cell.setBackground(Color.GREEN);
            }
        }
        if (column==0)
        {
            if(Productos.cbx_Eliminados.isSelected())
            {
            if(estado.equals("Activo")) 
            {
                
                cell.setBackground(new Color (153,255,153));
            }else
            {
                cell.setBackground(Color.pink);
            }
            }
        }
        }
        
        return cell;
    }
}
