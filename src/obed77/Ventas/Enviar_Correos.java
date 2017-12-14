
package obed77.Ventas;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.Timer;
import javax.swing.filechooser.FileFilter;
import obed77.Correo.Enviando;
import obed77.Correo.Enviar_Correo_Envio;
import obed77.Correo.Enviar_Correo_Facturado;
import obed77.Correo.ImagePreview;
import obed77.Correo.Enviar_Correo_Importante;
import obed77.Correo.HiloEnviado;
import obed77.Correo.HiloFacturado;
import obed77.Correo.HiloImportante;

public class Enviar_Correos extends javax.swing.JFrame {
Shape shape = null;
private Timer timer = new Timer();
int ic=0;
String co;
String pro;
String ca;
String pre;
static String est;
int s=0;

    public Enviar_Correos() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        llenarCampos();
        this.setResizable(false);
        validarbotones();
    
    }

 @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 

   void inhabilitarBtn()
   {
       btn_salir.setEnabled(false);
    btn_importante.setEnabled(false);
    btn_envio.setEnabled(false);
    btn_facturado.setEnabled(false);
   }
   
    
public void enviarCorreoI()
  {        
    Enviar_Correo_Importante c = new Enviar_Correo_Importante(co);
    c.AsignarDatos();
    inhabilitarBtn();
    
      System.out.println("Ejecutando hilo");
    HiloImportante hilo = new HiloImportante("ec");
    hilo.start();
  
  }
public void enviarCorreoF()
  {        
    Enviar_Correo_Facturado c = new Enviar_Correo_Facturado(co);
    c.AsignarDatos();
    
    inhabilitarBtn();
      System.out.println("Ejecutando hilo");
    HiloFacturado hilo = new HiloFacturado("ec");
    hilo.start();
  
  }

  
public void enviarCorreoE()
  {        
    
    String p , ci = lab_ci.getText(), adj = lab_adj.getText(),emp=lab_emp.getText(), ref = lab_ref.getText(), nota = txt_nota.getText();
    Enviar_Correo_Envio c = new Enviar_Correo_Envio(ci,emp,ref,nota,adj);
    c.AsignarDatos();
    
    inhabilitarBtn();
    HiloEnviado hilo = new HiloEnviado();
    hilo.start();
    
  }
public static void validarbotones()
{
     if ("Enviado".equals(est) || "Concretada".equals(est))
      {
          btn_envio.setEnabled(true);
          btn_adjuntar.setEnabled(true);
          txt_nota.setEnabled(true);
          btn_importante.setEnabled(false);
          btn_facturado.setEnabled(false);
      }else
         if("Por Cobrar".equals(est) || "Cobrado".equals(est) || "Cobrado MP".equals(est))
      {
          btn_envio.setEnabled(false);
          btn_adjuntar.setEnabled(false);
          txt_nota.setEnabled(false);
          btn_importante.setEnabled(true);
          btn_facturado.setEnabled(false);
      }else
         {
          btn_envio.setEnabled(false);
          btn_adjuntar.setEnabled(false);
          txt_nota.setEnabled(false);
          btn_importante.setEnabled(true);
          btn_facturado.setEnabled(true); 
         }
}
  void llenarCampos()
  {

      String ci=(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 2);
      String cliente =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 3);
      String empresa = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 9);
      String referencia = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 10);
      est = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 6);
      co =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 12);
     
      lab_ci.setText(ci);
      this.lab_nombre.setText(cliente);
      Enviar_Correos.lab_emp.setText(empresa);
      Enviar_Correos.lab_ref.setText(referencia);
}


  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        lab_ci = new javax.swing.JLabel();
        lab_adj = new javax.swing.JLabel();
        btn_adjuntar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btn_importante = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lab_emp = new javax.swing.JLabel();
        lab_ref = new javax.swing.JLabel();
        lab_nombre = new javax.swing.JLabel();
        btn_envio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_nota = new javax.swing.JTextArea();
        btn_facturado = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enviar Correos");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(390, 350));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Enviar Correos"));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        jLabel2.setText("Cliente:");

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

        lab_ci.setText("V-10000000-1");

        lab_adj.setText("No hay adjuntos...");

        btn_adjuntar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Attach.png"))); // NOI18N
        btn_adjuntar.setToolTipText("Adjuntar Archivo");
        btn_adjuntar.setBorder(null);
        btn_adjuntar.setBorderPainted(false);
        btn_adjuntar.setContentAreaFilled(false);
        btn_adjuntar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_adjuntar.setHideActionText(true);
        btn_adjuntar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_adjuntar.setIconTextGap(-9);
        btn_adjuntar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Attach.png"))); // NOI18N
        btn_adjuntar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Attach.png"))); // NOI18N
        btn_adjuntar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_adjuntar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_adjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adjuntarActionPerformed(evt);
            }
        });

        jLabel19.setText("Adjuntar Archivo:");

        btn_importante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi1.png"))); // NOI18N
        btn_importante.setText("Información Importante");
        btn_importante.setToolTipText("Enviar Correo");
        btn_importante.setBorder(null);
        btn_importante.setBorderPainted(false);
        btn_importante.setContentAreaFilled(false);
        btn_importante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_importante.setHideActionText(true);
        btn_importante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_importante.setIconTextGap(-9);
        btn_importante.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi1.png"))); // NOI18N
        btn_importante.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi2.png"))); // NOI18N
        btn_importante.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_importante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_importante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importanteActionPerformed(evt);
            }
        });

        jLabel3.setText("Empresa:");

        jLabel7.setText("Referencia:");

        lab_emp.setText("Cliente:");

        lab_ref.setText("Cliente:");

        lab_nombre.setText("Cliente:");

        btn_envio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_enve1.png"))); // NOI18N
        btn_envio.setText("Datos de Envío");
        btn_envio.setToolTipText("Enviar Correo");
        btn_envio.setBorder(null);
        btn_envio.setBorderPainted(false);
        btn_envio.setContentAreaFilled(false);
        btn_envio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_envio.setHideActionText(true);
        btn_envio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_envio.setIconTextGap(-9);
        btn_envio.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_enve1.png"))); // NOI18N
        btn_envio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_enve2.png"))); // NOI18N
        btn_envio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_envio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_envio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_envioActionPerformed(evt);
            }
        });

        jLabel4.setText("NOTA:");

        txt_nota.setColumns(20);
        txt_nota.setRows(5);
        jScrollPane1.setViewportView(txt_nota);

        btn_facturado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi1.png"))); // NOI18N
        btn_facturado.setText("Facturado");
        btn_facturado.setToolTipText("Enviar Correo");
        btn_facturado.setBorder(null);
        btn_facturado.setBorderPainted(false);
        btn_facturado.setContentAreaFilled(false);
        btn_facturado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_facturado.setHideActionText(true);
        btn_facturado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_facturado.setIconTextGap(-9);
        btn_facturado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi1.png"))); // NOI18N
        btn_facturado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_envi2.png"))); // NOI18N
        btn_facturado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_facturado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_facturado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_ci)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lab_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_ref, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_adj, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_adjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_importante)
                        .addGap(2, 2, 2)
                        .addComponent(btn_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btn_facturado)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lab_ci)
                    .addComponent(lab_nombre))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(lab_emp)
                    .addComponent(lab_ref))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_adjuntar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_adj)
                .addGap(5, 5, 5)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_importante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_envio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_facturado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panel);
        panel.setBounds(10, 10, 370, 320);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 520, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adjuntarActionPerformed
        String adjunto ="";
        //Método para apertura de archivo.
        //Creamos un objeto JFileChooser
        JFileChooser jf =  new JFileChooser();
        //Creamos un filtro de imágenes utilizando nuestra clase Utils
        jf.addChoosableFileFilter(new FileFilter() {
            //Los métos siguientes son implementaciones
            @Override
            public boolean accept(File f) {
                if(f.isDirectory()){
                    return true;
                }
                String exts = Utilidad.getExtension(f);
                if (exts != null) {
                    if (
                        exts.equals(Utilidad.jpeg) ||
                        exts.equals(Utilidad.jpg) ||
                        exts.equals(Utilidad.png)) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                }

                return false;
                
            }

            @Override
            public String getDescription() {
                return "Imágenes";
            }
        });
        //Establecemos un accesorio que será nuestra vista previa.
        jf.setAccessory(new ImagePreview(jf));;

        int respuesta = jf.showSaveDialog(this);
        if (respuesta == 0)
        {
            File archivoElegido = jf.getSelectedFile();
            try
            {
                // adjunto = adjunto + archivoElegido.getCanonicalPath() + ",";
                adjunto =  archivoElegido.getCanonicalPath();
                adjunto = adjunto.replace("\\", "/");
                    System.out.println("Adjuntos" + adjunto);
                    this.lab_adj.setText(adjunto);
                }
                catch (IOException ex)
                {

                }
            }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_adjuntarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Ventas_ml.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_importanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importanteActionPerformed
        enviarCorreoI();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_importanteActionPerformed

    private void btn_envioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_envioActionPerformed
        if(this.lab_adj.getText().equals("No hay adjuntos..."))
        {
            JOptionPane.showMessageDialog(this,"Error...\n No puede enviar correo sin adjunto","Error",1,null);
        }else
        {
            enviarCorreoE();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_envioActionPerformed

    private void btn_facturadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturadoActionPerformed
    enviarCorreoF();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturadoActionPerformed

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
            java.util.logging.Logger.getLogger(Enviar_Correos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enviar_Correos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enviar_Correos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enviar_Correos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Enviar_Correos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_adjuntar;
    public static javax.swing.JButton btn_envio;
    public static javax.swing.JButton btn_facturado;
    public static javax.swing.JButton btn_importante;
    public static javax.swing.JButton btn_salir;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lab_adj;
    public static javax.swing.JLabel lab_ci;
    public static javax.swing.JLabel lab_emp;
    public javax.swing.JLabel lab_nombre;
    public static javax.swing.JLabel lab_ref;
    public javax.swing.JPanel panel;
    public static javax.swing.JTextArea txt_nota;
    // End of variables declaration//GEN-END:variables
}
