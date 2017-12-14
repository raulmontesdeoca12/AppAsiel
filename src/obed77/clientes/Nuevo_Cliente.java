
package obed77.clientes;

import BaseDeDatos.Clientes.ClaseCliente;
import BaseDeDatos.Clientes.Gest_cliente;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import obed77.EmailValidator;
import obed77.Ventas.Ventas_ml;

public class Nuevo_Cliente extends javax.swing.JFrame {
Shape shape = null;

    public Nuevo_Cliente() {
        
        initComponents();
        this.setResizable(false);
       
    
    }
 public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 
 public void limpiar()
 {
        cbo_rif.setSelectedItem("V");
        txt_rif.setValue(null);
        txt_nombre.setText("");
        txt_telefono.setValue(null);
        txt_direccion.setText("");
        txt_correo.setText("");
       
 }

  void accionbntnuevo(){
      
        cbo_rif.setEnabled(true);
        txt_rif.setEnabled(true);
        txt_nombre.setEnabled(true);
        txt_contacto.setEnabled(true);
        txt_telefono.setEnabled(true);
        txt_correo.setEnabled(true);
        txt_direccion.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_limpiar.setEnabled(true);
        btn_nuevo.setEnabled(false);

  }
  
   void inhabilitar(){
        cbo_rif.setEnabled(false);
        txt_rif.setEnabled(false);
        txt_nombre.setEnabled(false);
        txt_contacto.setEnabled(false);
        txt_telefono.setEnabled(false);
        txt_correo.setEnabled(false);
        txt_direccion.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_limpiar.setEnabled(false);
        limpiar();
    }
 
 
void guardar(){
    String tr = this.cbo_rif.getSelectedItem().toString();
    String rn=this.txt_rif.getText();
    String g="-";
    String rif=tr+g+rn;
    String nombre=this.txt_nombre.getText();
    String contacto=this.txt_contacto.getText();
    String telefono=this.txt_telefono.getText();
    String correo=this.txt_correo.getText();
    String direccion=this.txt_direccion.getText();
    String estatus = "Activo";
    
    try{
        ClaseCliente cliente = new ClaseCliente(rif, nombre, direccion, correo, contacto, telefono,estatus);
        Gest_cliente in = new Gest_cliente();
        boolean r;
        r=in.Insertar(cliente);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Registro Exitoso", "Guardado",1,null);
        }
        
    }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Nuevo_Cliente.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        txt_rif = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        btn_nuevo = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        cbo_rif = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_contacto = new javax.swing.JTextField();
        rad_rif = new javax.swing.JRadioButton();
        rad_ci = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Cliente");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(509, 370));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Cliente"));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        try {
            txt_rif.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_rif.setToolTipText("Si Es Persona Natural use un 0 al final del Rif");

        jLabel2.setText("Razón Social:");

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

        jLabel3.setText("Telefono:");

        try {
            txt_telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(####)-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_telefono.setToolTipText("Ejem: (0212)1113322");

        jLabel8.setText("Direccion:");

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo1.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setToolTipText("Habilitar Campos");
        btn_nuevo.setBorder(null);
        btn_nuevo.setBorderPainted(false);
        btn_nuevo.setContentAreaFilled(false);
        btn_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nuevo.setIconTextGap(-4);
        btn_nuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo2.png"))); // NOI18N
        btn_nuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo3.png"))); // NOI18N
        btn_nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
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
        btn_guardar.setToolTipText("Guardar Nuevo Cliente");
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

        jLabel6.setText("Correo:");

        cbo_rif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "J", "E", "G" }));

        jLabel4.setText("Contacto:");

        txt_contacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_contactoKeyTyped(evt);
            }
        });

        buttonGroup1.add(rad_rif);
        rad_rif.setSelected(true);
        rad_rif.setText("Rif");
        rad_rif.setOpaque(false);
        rad_rif.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rad_rifItemStateChanged(evt);
            }
        });
        rad_rif.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rad_rifStateChanged(evt);
            }
        });
        rad_rif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rad_rifActionPerformed(evt);
            }
        });
        rad_rif.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                rad_rifVetoableChange(evt);
            }
        });

        buttonGroup1.add(rad_ci);
        rad_ci.setText("C.I.");
        rad_ci.setOpaque(false);
        rad_ci.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rad_ciItemStateChanged(evt);
            }
        });
        rad_ci.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                rad_ciHierarchyChanged(evt);
            }
        });
        rad_ci.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rad_ciStateChanged(evt);
            }
        });
        rad_ci.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                rad_ciVetoableChange(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btn_nuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_salir)
                .addContainerGap(133, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel8))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(rad_rif)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rad_ci)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel2)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(cbo_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_correo)
                                    .addComponent(txt_nombre)))
                            .addComponent(txt_direccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_contacto))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rad_rif)
                    .addComponent(rad_ci))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(btn_guardar)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(261, 261, 261))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_limpiar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 11, 501, 358);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 520, 380);

        setSize(new java.awt.Dimension(525, 408));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Clientes.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        String rif=this.txt_rif.getText();
        String nombre=this.txt_nombre.getText();
        String telefono=this.txt_telefono.getText();
        String contacto=this.txt_contacto.getText();
        String correo=this.txt_correo.getText();
        String direccion=this.txt_direccion.getText();
        EmailValidator ev = new EmailValidator();

        if (" -        - ".equals(rif) || nombre.isEmpty() || "(  )-       ".equals(telefono)|| contacto.isEmpty()|| correo.isEmpty() || direccion.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        }else
        {
            if(!ev.validate(correo.trim()))
            {
            JOptionPane.showMessageDialog(null, "El Correo no es Válido");
            }else{
            guardar();
            Clientes.cargar();
            inhabilitar();
            this.btn_nuevo.setEnabled(true);
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        accionbntnuevo();
        txt_rif.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void txt_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreKeyReleased

    private void txt_contactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactoKeyReleased

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
 txt_nombre = (JTextField) evt.getComponent();
        String cadena = txt_nombre.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_nombre.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_contactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactoKeyTyped
 txt_contacto = (JTextField) evt.getComponent();
        String cadena = txt_contacto.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_contacto.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactoKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
 txt_direccion = (JTextField) evt.getComponent();
        String cadena = txt_direccion.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_direccion.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionKeyTyped

    private void rad_rifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rad_rifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rad_rifActionPerformed

    private void rad_rifStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rad_rifStateChanged
   // TODO add your handling code here:
    }//GEN-LAST:event_rad_rifStateChanged

    private void rad_ciStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rad_ciStateChanged
      
      // TODO add your handling code here:
    }//GEN-LAST:event_rad_ciStateChanged

    private void rad_rifVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_rad_rifVetoableChange
       // TODO add your handling code here:
    }//GEN-LAST:event_rad_rifVetoableChange

    private void rad_ciVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_rad_ciVetoableChange
       // TODO add your handling code here:
    }//GEN-LAST:event_rad_ciVetoableChange

    private void rad_ciHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_rad_ciHierarchyChanged
     // TODO add your handling code here:
    }//GEN-LAST:event_rad_ciHierarchyChanged

    private void rad_ciItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_ciItemStateChanged
        
        if (rad_ci.isSelected())
            cbo_rif.removeAllItems();
        txt_rif.setText(null);
        cbo_rif.addItem("V");cbo_rif.addItem("E");
       
          txt_rif.setFormatterFactory(new javax.swing.JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                try
                {
                    return new javax.swing.text.MaskFormatter("########-0");
                } catch (ParseException ex) {
                    Logger.getLogger(Nuevo_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        });

    
    }//GEN-LAST:event_rad_ciItemStateChanged

    private void rad_rifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_rifItemStateChanged
        if(rad_rif.isSelected())
            cbo_rif.removeAllItems();
        cbo_rif.addItem("V");
        cbo_rif.addItem("E");
        cbo_rif.addItem("J");
        cbo_rif.addItem("G");
        txt_rif.setText(null);
        txt_rif.setFormatterFactory(new javax.swing.JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                try
                {
                    return new javax.swing.text.MaskFormatter("########-#");
                } catch (ParseException ex) {
                    Logger.getLogger(Nuevo_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        });
    }//GEN-LAST:event_rad_rifItemStateChanged

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
            java.util.logging.Logger.getLogger(Nuevo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_Cliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_nuevo;
    public javax.swing.JButton btn_salir;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox cbo_rif;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JPanel panel;
    public javax.swing.JRadioButton rad_ci;
    public javax.swing.JRadioButton rad_rif;
    public javax.swing.JTextField txt_contacto;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_nombre;
    public javax.swing.JFormattedTextField txt_rif;
    public javax.swing.JFormattedTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
