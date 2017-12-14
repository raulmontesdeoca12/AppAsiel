/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.productos;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Producto.ClaseIEProducto;
import BaseDeDatos.Producto.Gest_producto;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Saito
 */
public class Insertar_Stock extends javax.swing.JFrame {
    String idm="";
    String id="";
    int exist=0;
    Shape shape = null;
   

  
    public Insertar_Stock() {
        this.setResizable(false);
        initComponents();
        llenarCampos();
    }
    
    
     public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }


   void llenarCampos()
  {

      String ids =(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 0);
      String desc =(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 2);
      String tipo=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 3);
      String exis=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 4);
      id=ids;
      this.lab_desc.setText(id+" "+desc);
      this.lab_tipo.setText(tipo);
      this.lab_existencia.setText(exis);
      exist=Integer.valueOf(exis);
  }
   void validarCant()
   {
       int can =Integer.valueOf(this.txt_inser.getText());
       if(can<=0)
       {
           JOptionPane.showMessageDialog(this, "No Puede Ingresar Esa Cantidad", "Error",1,null);
       }else
       {
           insertarstock();
       }
   }


   void insertarstock()
   {
       
     try{
       int canv=0, can=0, cantidad=0;
      
       boolean r=false;
        
           
           
            can=Integer.valueOf(this.txt_inser.getText());
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            String sql = "SELECT * FROM producto  WHERE idproducto = '"+id+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            if (rs.first()) {
                canv=rs.getInt("cantidad_prod");
                }
            cantidad=canv+can;
               
        ClaseIEProducto ped = new ClaseIEProducto(id,cantidad);
        Gest_producto in = new Gest_producto();
        r=in.MovimientoStock(ped);
                    
        
    
       
       if(r==false)
       {
           JOptionPane.showMessageDialog(this,"Se ha ingresado con exito...", "Guardado",1,null); 
           
           this.dispose();
          
           
       }
       }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Insertar_Stock.class.getName()).log(Level.SEVERE,null,ex);
            }
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lab_desc = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lab_tipo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_inser = new javax.swing.JTextField();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lab_existencia = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insertar Stock");
        setAlwaysOnTop(true);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(491, 231));
        setMinimumSize(new java.awt.Dimension(491, 231));
        setPreferredSize(new java.awt.Dimension(491, 231));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar"));
        jPanel1.setOpaque(false);

        jLabel1.setText("Descripción:");

        jLabel3.setText("Tipo:");

        jLabel5.setText("Cantidad a Insertar:");

        txt_inser.setToolTipText("Ingrese solo un valor mayor o igual a la existencia");
        txt_inser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_inserKeyPressed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_guardar1.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Insertar Productos");
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
        btn_guardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_guardarKeyPressed(evt);
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
        btn_salir.setIconTextGap(-9);
        btn_salir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir2.png"))); // NOI18N
        btn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir3.png"))); // NOI18N
        btn_salir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jLabel7.setText("Existencia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_existencia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_inser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btn_guardar)
                .addGap(18, 18, 18)
                .addComponent(btn_salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_desc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lab_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_inser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(lab_existencia, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 11, 470, 210);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar500x375.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 500, 240);

        setSize(new java.awt.Dimension(507, 269));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
validarCant();  
Productos.cargar();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Productos.cargar();
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_guardarKeyPressed
 if(evt.getKeyCode() == KeyEvent.VK_ENTER) 
 {validarCant();
 Productos.cargar();}        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarKeyPressed

    private void txt_inserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_inserKeyPressed
 if(evt.getKeyCode() == KeyEvent.VK_ENTER) 
 {validarCant();
 Productos.cargar();}        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inserKeyPressed

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
            java.util.logging.Logger.getLogger(Insertar_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar_Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar_Stock().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lab_desc;
    public javax.swing.JLabel lab_existencia;
    public javax.swing.JLabel lab_tipo;
    public javax.swing.JTextField txt_inser;
    // End of variables declaration//GEN-END:variables
}
