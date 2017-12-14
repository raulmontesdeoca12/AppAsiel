
package obed77.Ventas;

import BaseDeDatos.ConexionBD;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ventana_clientes extends javax.swing.JFrame {
Shape shape = null;
static DefaultTableModel model;
    public ventana_clientes() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),20,20);
        AWTUtilities.setWindowShape(this, shape);
        
        cargar();
    }
  
     @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
  public static void cargar() {
      

        try {
            String[] titulos = {"Rif o C.I", "Razon Social", "Teléfono", "Dirección"};
            String[] registros = new String[4];
            model = new DefaultTableModel(null, titulos);
            String sql;
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            sql = "SELECT * FROM clientes";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                registros[0] = rs.getString("idclientes");
                registros[1] = rs.getString("razon_social_cli");
                registros[2] = rs.getString("telefono_cli");
                registros[3] = rs.getString("direccion_cli");
                model.addRow(registros);
            }
            tabla_cliente.setModel(model);
            TableRowSorter modeloOrdenado = new TableRowSorter(model);
            tabla_cliente.setRowSorter(modeloOrdenado);
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
            tabla_cliente.setRowSorter(modeloOrdenado);


        } catch (SQLException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void validarBotones() {
        int filasel;
        try {
            filasel = tabla_cliente.getSelectedRow();

            if (filasel != -1) {
                this.btn_aceptar.setEnabled(true);

            } else {
                this.btn_aceptar.setEnabled(false);
            }


        } catch (Exception e) {
        }
    }
    public static void cargardatos()
    {
        String rif=(String) ventana_clientes.tabla_cliente.getValueAt(ventana_clientes.tabla_cliente.getSelectedRow(), 0);
        String nom=(String) ventana_clientes.tabla_cliente.getValueAt(ventana_clientes.tabla_cliente.getSelectedRow(), 1);
        String tel=(String) ventana_clientes.tabla_cliente.getValueAt(ventana_clientes.tabla_cliente.getSelectedRow(), 2);
        String dir=(String) ventana_clientes.tabla_cliente.getValueAt(ventana_clientes.tabla_cliente.getSelectedRow(), 3); 
        Ventana_Facturar.txt_rif.setText(rif);
        Ventana_Facturar.txt_nombre_cliente.setText(nom);
        Ventana_Facturar.txt_telefono.setText(tel);
        Ventana_Facturar.txt_direccion.setText(dir);
    }
    void Aceptar()
        { 
            int facpc=0;
            String sql;
              String rif=(String) this.tabla_cliente.getValueAt(this.tabla_cliente.getSelectedRow(), 0);
              String nom=(String) this.tabla_cliente.getValueAt(this.tabla_cliente.getSelectedRow(), 1);
              String tel=(String) this.tabla_cliente.getValueAt(this.tabla_cliente.getSelectedRow(), 2);
              String dir=(String) this.tabla_cliente.getValueAt(this.tabla_cliente.getSelectedRow(), 3); 
          
                            
                cargardatos();
                Ventana_Facturar.validarcamposllenos();
                this.dispose();
            
         
              
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_aceptar = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        btn_mostrar_todo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_cliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setUndecorated(true);
        setResizable(false);
        setShape(getShape());

        jPanel3.setToolTipText("Gestionar Productos");
        jPanel3.setAutoscrolls(true);
        jPanel3.setMinimumSize(new java.awt.Dimension(920, 370));
        jPanel3.setOpaque(false);

        jPanel2.setOpaque(false);

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo1.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.setToolTipText("Cargar Datos de Cliente");
        btn_aceptar.setBorder(null);
        btn_aceptar.setBorderPainted(false);
        btn_aceptar.setContentAreaFilled(false);
        btn_aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_aceptar.setEnabled(false);
        btn_aceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_aceptar.setIconTextGap(-3);
        btn_aceptar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_aceptar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_aceptar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo2.png"))); // NOI18N
        btn_aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo3.png"))); // NOI18N
        btn_aceptar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_aceptar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setToolTipText("Cerrar Modulo");
        btn_cerrar.setBorder(null);
        btn_cerrar.setBorderPainted(false);
        btn_cerrar.setContentAreaFilled(false);
        btn_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar.setIconTextGap(-3);
        btn_cerrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir2.png"))); // NOI18N
        btn_cerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir3.png"))); // NOI18N
        btn_cerrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setAutoscrolls(true);
        jPanel4.setOpaque(false);

        txt_buscar.setToolTipText("Ingrese texto para buscar en la tabla (Sensible a Mayúsculas y Minúsculas)");
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });

        btn_mostrar_todo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo.setText("Mostrar Todo");
        btn_mostrar_todo.setToolTipText("Restablecer Tabla");
        btn_mostrar_todo.setBorder(null);
        btn_mostrar_todo.setBorderPainted(false);
        btn_mostrar_todo.setContentAreaFilled(false);
        btn_mostrar_todo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mostrar_todo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_mostrar_todoMousePressed(evt);
            }
        });
        btn_mostrar_todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrar_todoActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

        tabla_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_cliente.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla_cliente.setAutoscrolls(false);
        tabla_cliente.setOpaque(false);
        tabla_cliente.getTableHeader().setResizingAllowed(false);
        tabla_cliente.getTableHeader().setReorderingAllowed(false);
        tabla_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clienteMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_clienteMouseReleased(evt);
            }
        });
        tabla_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_clienteFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_cliente);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_mostrar_todo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_buscar)
                    .addComponent(btn_mostrar_todo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(212, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        Aceptar();
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        cargar();
        btn_aceptar.setEnabled(false);
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed

    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
        cargar();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clienteMouseClicked

    private void tabla_clienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clienteMouseReleased
        validarBotones();   // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clienteMouseReleased

    private void tabla_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_clienteFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clienteFocusLost

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
            java.util.logging.Logger.getLogger(ventana_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventana_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventana_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventana_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventana_clientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_aceptar;
    public javax.swing.JButton btn_cerrar;
    public javax.swing.JButton btn_mostrar_todo;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla_cliente;
    public static javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
