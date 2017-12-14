
package obed77.Ventas;

import obed77.clientes.*;
import BaseDeDatos.Clientes.ClaseCliente;
import BaseDeDatos.Clientes.Gest_cliente;
import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_ml;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import obed77.EmailValidator;

public class Agregar_Pago extends javax.swing.JFrame {
Shape shape = null;

    public Agregar_Pago() {
        initComponents();
        llenarCampos();
        this.setResizable(false);
    
    }

 @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 
 
  
  
  void llenarCampos()
  {

      String id =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 0);
      String cliente =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 4);
      this.lab_id.setText(id);
      this.txt_cliente.setText(cliente);
}
   
void guardar(){
    int id = Integer.parseInt(this.lab_id.getText());
    String refco = this.txt_refco.getText();
    String estado = "";
    if(cbx_mp.isSelected())
    {
        estado="Cobrado MP";
        refco = "MP "+refco;
    }else
    {
        estado="Cobrado";
    }
    
    
    
    
    
    try{
        clase_ml ml = new clase_ml(id, estado, refco);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        
        r=in.InsertarCobro(ml);
        
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Cobro Ingresado Exitosamente", "Cobro",1,null);
            Ventas_ml.cargar();
            this.dispose();
        }
        
    }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Agregar_Pago.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_cliente = new javax.swing.JTextField();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_refco = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lab_id = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbx_mp = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Pago");
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMaximumSize(new java.awt.Dimension(370, 275));
        setMinimumSize(new java.awt.Dimension(370, 275));
        setPreferredSize(new java.awt.Dimension(370, 275));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Cobro"));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        jLabel2.setText("Cliente:");

        txt_cliente.setEditable(false);
        txt_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_clienteKeyReleased(evt);
            }
        });

        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar1.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.setToolTipText("Restablecer Campos");
        btn_limpiar.setBorder(null);
        btn_limpiar.setBorderPainted(false);
        btn_limpiar.setContentAreaFilled(false);
        btn_limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_limpiar.setIconTextGap(-9);
        btn_limpiar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar2.png"))); // NOI18N
        btn_limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar3.png"))); // NOI18N
        btn_limpiar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_limpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_guardar1.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar Pago");
        btn_guardar.setBorder(null);
        btn_guardar.setBorderPainted(false);
        btn_guardar.setContentAreaFilled(false);
        btn_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_guardar.setHideActionText(true);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setIconTextGap(-9);
        btn_guardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_guardar2.png"))); // NOI18N
        btn_guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_guardar3.png"))); // NOI18N
        btn_guardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setToolTipText("Cerrar Ventana");
        btn_salir.setBorder(null);
        btn_salir.setBorderPainted(false);
        btn_salir.setContentAreaFilled(false);
        btn_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir.setIconTextGap(-8);
        btn_salir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir2.png"))); // NOI18N
        btn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir3.png"))); // NOI18N
        btn_salir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jLabel14.setText("Ref. Cobro:");

        txt_refco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_refcoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_refcoKeyTyped(evt);
            }
        });

        jLabel16.setText("ID:");

        jLabel15.setText("Cobrado:");

        cbx_mp.setText("Mercado Pago");
        cbx_mp.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btn_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salir))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(cbx_mp))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel15)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_refco, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel14))))
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lab_id, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_mp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_refco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_guardar))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 11, 340, 230);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 400, 380);

        getAccessibleContext().setAccessibleName("Agregar Cobro");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Ventas_ml.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        String cliente=this.txt_cliente.getText();
        String cobro=this.txt_refco.getText();

        if (cliente.isEmpty() || cobro.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        }else
        {            
            guardar();
            Ventas_ml.cargar();
            this.dispose();
        }
        
         //TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        llenarCampos();// TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_clienteKeyReleased

    private void txt_refcoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_refcoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_refcoKeyReleased

    private void txt_refcoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_refcoKeyTyped
txt_refco = (JTextField) evt.getComponent();
        String cadena = txt_refco.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_refco.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_refcoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agregar_Pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_Pago().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir;
    public javax.swing.JCheckBox cbx_mp;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel lab_id;
    public javax.swing.JPanel panel;
    public javax.swing.JTextField txt_cliente;
    public javax.swing.JTextField txt_refco;
    // End of variables declaration//GEN-END:variables
}
