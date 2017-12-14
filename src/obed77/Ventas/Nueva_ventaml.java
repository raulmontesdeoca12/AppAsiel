
package obed77.Ventas;

import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_ml;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import obed77.Correo.Enviar_Correo_Importante;
import obed77.Correo.HiloImportante;
import obed77.EmailValidator;

public class Nueva_ventaml extends javax.swing.JFrame {
Shape shape = null;
private Timer timer;
int ic=0;
String co;
String pro;
String ca;
String pre;

    public Nueva_ventaml() {
        initComponents();
//        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
//        AWTUtilities.setWindowShape(this, shape);
    limpiar();
        this.setResizable(false);
    
    }

 @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 
 public void limpiar()
 {
        dch_fecha.setDate(null);
        txt_cliente.setText("");
        txt_telefono.setValue(null);
        txt_correo.setText("");
       
 }
 
  void accionbntnuevo(){
      
        dch_fecha.setEnabled(true);
        txt_cliente.setEnabled(true);
        txt_telefono.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_limpiar.setEnabled(true);
        txt_correo.setEnabled(true);

  }
  
   void inhabilitar(){
        dch_fecha.setEnabled(false);
        txt_cliente.setEnabled(false);
        txt_telefono.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_limpiar.setEnabled(false);
        txt_correo.setEnabled(false);
        limpiar();
    }
 
   
   
   
   

   
   
    
public void enviarCorreo()
  {        
    
   String p;
    Enviar_Correo_Importante c = new Enviar_Correo_Importante(co);
    c.AsignarDatos();
    HiloImportante hilo = new HiloImportante("vm");
    hilo.start();
    
  }
void validarcerrar()
{
    if(ch_evt.isSelected())
    {
        this.dispose();
    }
}
   
void guardar(){
    
    
    String formato ="yyyy-MM-dd";
    Date fec=this.dch_fecha.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    String fecha= sdf.format(fec);
    String cliente=this.txt_cliente.getText();
    String telefono=this.txt_telefono.getText();
    String estado = "Por Cobrar";
    String correo = this.txt_correo.getText();
    co=correo;
    
    try{
        clase_ml ml = new clase_ml(fecha, cliente, telefono,estado,correo);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        r=in.InsertarVenMl(ml);
        if (r==false)
        {
           int opc = JOptionPane.showConfirmDialog(this,"Venta Creada Correctamente\n ¿Desea enviar el Correo?","Ventas Mercadolibre",JOptionPane.YES_NO_OPTION);
         
           if (opc==0)
           {
               enviarCorreo();
               this.btn_salir.setEnabled(false);
           }else
           {
               dispose();
           }
              
            
        }else
        {
            dispose();
        }
        
    }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Nueva_ventaml.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ch_evt = new javax.swing.JCheckBox();
        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_cliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JFormattedTextField();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        dch_fecha = new com.toedter.calendar.JDateChooser();
        txt_correo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        ch_evt.setText("jCheckBox1");
        ch_evt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_evtItemStateChanged(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Venta");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(490, 290));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        jLabel2.setText("Cliente:");

        txt_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_clienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_clienteKeyTyped(evt);
            }
        });

        jLabel3.setText("Telefono:");

        try {
            txt_telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(####)-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_telefono.setToolTipText("Ejem: (0212)1113322");

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
        btn_guardar.setToolTipText("Guardar Nueva Venta ML");
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

        jLabel7.setText("Fecha:");

        dch_fecha.setMaximumSize(new java.awt.Dimension(110, 28));
        dch_fecha.setMinimumSize(new java.awt.Dimension(110, 28));
        dch_fecha.setOpaque(false);
        dch_fecha.setPreferredSize(new java.awt.Dimension(110, 28));

        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_correoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });

        jLabel12.setText("Correo:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btn_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir)
                        .addGap(0, 146, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(txt_correo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(dch_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cliente)
                        .addContainerGap())
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(93, 93, 93))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dch_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btn_guardar)
                                .addContainerGap(176, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_limpiar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 11, 480, 240);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 520, 380);

        setBounds(850, 50, 504, 297);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Ventas_ml.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        Date fecha = dch_fecha.getDate();
        String cliente=this.txt_cliente.getText();
        String telefono=this.txt_telefono.getText();
        String correo = this.txt_correo.getText();
        EmailValidator ev = new EmailValidator();

        if (fecha == null || cliente.isEmpty() || "(  )-       ".equals(telefono)||  correo.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        }else
        {    
            
            if(!ev.validate(correo.trim()))
            {
            JOptionPane.showMessageDialog(null, "El Correo no es Válido");
            }else
            {
                guardar();
                Ventas_ml.cargar();
                inhabilitar();
            }
           
        }
        
         //TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_clienteKeyReleased

    private void txt_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_clienteKeyTyped
txt_cliente = (JTextField) evt.getComponent();
        String cadena = txt_cliente.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_cliente.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_clienteKeyTyped

    private void txt_correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoKeyReleased

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoKeyTyped

    private void ch_evtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_evtItemStateChanged
    validarcerrar();        // TODO add your handling code here:
    }//GEN-LAST:event_ch_evtItemStateChanged

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
            java.util.logging.Logger.getLogger(Nueva_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nueva_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nueva_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nueva_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Nueva_ventaml().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir;
    public static javax.swing.JCheckBox ch_evt;
    public com.toedter.calendar.JDateChooser dch_fecha;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JPanel panel;
    public javax.swing.JTextField txt_cliente;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JFormattedTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
