
package obed77.empleados;
import BaseDeDatos.Empleados.ClaseEmpleado;
import BaseDeDatos.Empleados.Gest_empleado;
import com.sun.awt.AWTUtilities;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import obed77.EmailValidator;

public class Nuevo_Empleado extends javax.swing.JFrame {
Shape shape = null;

    public Nuevo_Empleado() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        inhabilitar();
        this.setResizable(false);
    
    }


 
 public void limpiar()
 {
        cbo_ci.setSelectedItem("V");
        txt_ci.setText(null);
        txt_nombre.setText("");
        txt_direccion.setText("");
        txt_correo.setText("");
        cbo_ecivil.setSelectedItem("Soltero");
        jdc_fecha.setDate(null);
       
 }
 
  void accionbntnuevo(){
      
        cbo_ci.setEnabled(true);
        txt_ci.setEnabled(true);
        txt_nombre.setEnabled(true);
        txt_apellido.setEnabled(true);
        txt_correo.setEnabled(true);
        txt_direccion.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_limpiar.setEnabled(true);
        btn_nuevo.setEnabled(false);
        cbo_ecivil.setEnabled(true);
        jdc_fecha.setEnabled(true);

  }
  
   void inhabilitar(){
        cbo_ci.setEnabled(false);
        txt_ci.setEnabled(false);
        txt_nombre.setEnabled(false);
        txt_apellido.setEnabled(false);
        txt_correo.setEnabled(false);
        txt_direccion.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_limpiar.setEnabled(false);
        cbo_ecivil.setEnabled(false);
        jdc_fecha.setEnabled(false);
        limpiar();
    }
 
 
void guardar(){
    String tr = this.cbo_ci.getSelectedItem().toString();
    String rn=this.txt_ci.getText();
    String g="-";
    String ci=tr+g+rn;
    String nombre=this.txt_nombre.getText();
    String apellido=this.txt_apellido.getText();
    String correo=this.txt_correo.getText();
    String direccion=this.txt_direccion.getText();
    String estado = this.cbo_ecivil.getSelectedItem().toString();
    String estatus = "Activo";
    String formato ="yyyy-MM-dd";
    Date fec=this.jdc_fecha.getDate();
    Calendar cal = Calendar.getInstance();
    int an = jdc_fecha.getCalendar().get(Calendar.YEAR);
    int mn= jdc_fecha.getCalendar().get(Calendar.MONTH);
    int dn =jdc_fecha.getCalendar().get(Calendar.DATE);
    int anio= cal.get(Calendar.YEAR)-an;
    int mes= cal.get(Calendar.MONTH)-mn;
    int dia= cal.get(Calendar.DATE)-dn;
    if(mes<0 ||(mes==0 && dia<0)){
        anio =anio-1;
        }
    String edad = String.valueOf(anio);
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    String fecha= sdf.format(fec);
    
    
    
    
    try{
        ClaseEmpleado empleado = new ClaseEmpleado(ci, nombre, apellido,fecha,edad,estado, correo, direccion,estatus);
        Gest_empleado in = new Gest_empleado();
        boolean r;
        r=in.Insertar(empleado);
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
                Logger.getLogger(Nuevo_Empleado.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        btn_nuevo = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_correo = new javax.swing.JTextField();
        cbo_ci = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        txt_ci = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jdc_fecha = new com.toedter.calendar.JDateChooser();
        cbo_ecivil = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ins. Cliente");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(519, 300));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar Cliente"));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        jLabel1.setText("C.I.:");

        jLabel2.setText("Nombres:");

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

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

        cbo_ci.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));

        jLabel4.setText("Apellidos:");

        txt_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_apellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidoKeyTyped(evt);
            }
        });

        txt_ci.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ciFocusLost(evt);
            }
        });
        txt_ci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ciKeyTyped(evt);
            }
        });

        jLabel7.setText("Fecha de nacimiento:");

        jdc_fecha.setOpaque(false);

        cbo_ecivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soltero", "Casado", "Divorciado", "Viudo" }));

        jLabel9.setText("Estado Civil:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btn_nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_direccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel2))
                            .addComponent(jLabel8)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(cbo_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ci, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_apellido)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(27, 27, 27))))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(cbo_ecivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3)
                                .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbo_ecivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        panel.setBounds(10, 11, 500, 280);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 520, 330);

        setSize(new java.awt.Dimension(519, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Empleados.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        String rif=this.txt_ci.getText();
        String nombre=this.txt_nombre.getText();
        String contacto=this.txt_apellido.getText();
        String correo=this.txt_correo.getText();
        String direccion=this.txt_direccion.getText();
        EmailValidator ev = new EmailValidator();
        Date f = jdc_fecha.getDate();

        if (" -        - ".equals(rif) || f==null || nombre.isEmpty() || contacto.isEmpty()|| correo.isEmpty() || direccion.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        }
        else{
            if(!ev.validate(correo.trim()))
            {
            JOptionPane.showMessageDialog(null, "El Correo no es Válido");
            }else
            {
                 guardar();
            Empleados.cargar();
            inhabilitar();
            this.btn_nuevo.setEnabled(true);
            }
           
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        accionbntnuevo();
        txt_ci.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void txt_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreKeyReleased

    private void txt_apellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoKeyReleased

    private void txt_ciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ciKeyTyped
char caracter = evt.getKeyChar();
String ci = this.txt_ci.getText();
if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')|| (ci.length()==9) ) {
evt.consume(); // ignorar el evento de teclado
}
      // TODO add your handling code here:
    }//GEN-LAST:event_txt_ciKeyTyped

    private void txt_ciFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ciFocusLost




// TODO add your handling code here:
    }//GEN-LAST:event_txt_ciFocusLost

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

    private void txt_apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidoKeyTyped
      txt_apellido = (JTextField) evt.getComponent();
        String cadena = txt_apellido.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_apellido.setText(String.valueOf(caracteres));
        }
        }    // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoKeyTyped

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
        }  // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionKeyTyped

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
            java.util.logging.Logger.getLogger(Nuevo_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Nuevo_Empleado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_nuevo;
    public javax.swing.JButton btn_salir;
    public javax.swing.JComboBox cbo_ci;
    public javax.swing.JComboBox cbo_ecivil;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public com.toedter.calendar.JDateChooser jdc_fecha;
    public javax.swing.JPanel panel;
    public javax.swing.JTextField txt_apellido;
    public javax.swing.JTextField txt_ci;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
