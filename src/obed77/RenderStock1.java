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
import obed77.Ventas.Ventas_ml;
import obed77.productos.Productos;

/**
 *
 * @author Saito
 */
public class RenderStock1 extends DefaultTableCellRenderer {
    private  int columna_patron;
    public RenderStock1 (int colpatron)
    {
        this.columna_patron=colpatron;
    }
    
        
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(Color.white);
        cell.setForeground(Color.black);
        String estado = Ventas_ml.tabla_ml.getValueAt(row, 6).toString();
        if(isSelected)
        {
            setBackground(new Color(51,153,255));
            setForeground(Color.white);
            
        }else
        if(value instanceof String)
        {
        if(column==columna_patron)
        {
        if("Por Cobrar".equals(estado))
        {
            cell.setBackground(new Color(96,96,96));
        }else
        
            if("Cobrado".equals(estado)||"Cobrado MP".equals(estado))
            {
                cell.setBackground(Color.yellow);
            }else
                
               if("Transfirio".equals(estado))
            {
                cell.setBackground(new Color(102,0,204));
            }else
                   if("Facturado".equals(estado))
                   {
                       cell.setBackground(Color.pink);
                   }else
                   if("Enviado".equals(estado))
                   {
                       cell.setBackground(new Color(0,255,255));
                   }else
                       if("Concretada".equals(estado))
                   {
                       cell.setBackground( Color.green);
                   }else
                       if("Cancelada".equals(estado))
                       {
                           cell.setBackground( Color.red);
                       }
        }
        
        }
        
        return cell;
    }
}
