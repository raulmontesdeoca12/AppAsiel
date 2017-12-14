/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.clientes;

import BaseDeDatos.Clientes.ClaseEACliente;
import BaseDeDatos.Clientes.Gest_cliente;
import BaseDeDatos.ConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import obed77.Principal;

/**
 *
 * @author user
 */
public class Clientes extends javax.swing.JPanel {
static DefaultTableModel model;
    /**
     * Creates new form Productos
     */
    public Clientes() {
        initComponents();
        cargar();
    }
    
        public static void cargar(){
       
        try{
        String [] titulos={"RIF o C.I","Nombre o Razón Social","Dirección","Correo","Contacto","Teléfono","Estatus"};
        String [] registros= new String[7];
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        model = new DefaultTableModel(null,titulos){public boolean isCellEditable(int row, int column) {
return false;
}};
         boolean eli= cbx_Eliminados.isSelected(); 
           if(eli==false){
                sql = "SELECT * FROM clientes WHERE clientes.estatus_cli='Activo' ORDER BY razon_social_cli ASC ";
           }else{
                sql = "SELECT * FROM clientes ORDER BY estatus_cli ASC, razon_social_cli ASC";
           }
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){

            registros[0]=rs.getString("idclientes");
            registros[1]=rs.getString("razon_social_cli");
            registros[2]=rs.getString("direccion_cli");
            registros[3]=rs.getString("correo_cli");
            registros[4]=rs.getString("contacto_cli");
            registros[5]=rs.getString("telefono_cli");
            registros[6]=rs.getString("estatus_cli");
            model.addRow(registros);
        }
        tabla_clientes.setModel(model);
        tabla_clientes.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla_clientes.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabla_clientes.getColumnModel().getColumn(2).setPreferredWidth(350);
        tabla_clientes.getColumnModel().getColumn(3).setPreferredWidth(300);
        tabla_clientes.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabla_clientes.getColumnModel().getColumn(5).setPreferredWidth(130);
        tabla_clientes.getColumnModel().getColumn(6).setPreferredWidth(80);
        TableRowSorter modeloOrdenado = new TableRowSorter(model);
        tabla_clientes.setRowSorter(modeloOrdenado);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
        tabla_clientes.setRowSorter(modeloOrdenado);
    }catch(SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      void desactivarBotones()
    {
        this.btn_modificar.setEnabled(false);
        this.btn_eliminar.setEnabled(false);
        this.btn_reactivar.setEnabled(false);
    }
      
    void validarBotones()
    {
        int filasel;
        String est;
        try
        {            
            filasel = tabla_clientes.getSelectedRow();
            est = tabla_clientes.getValueAt(filasel, 6).toString();

            if ("Activo".equals(est) && filasel!=-1)
            {
                 this.btn_modificar.setEnabled(true);
                 this.btn_eliminar.setEnabled(true);
                 this.btn_reactivar.setEnabled(false);
            }else
            if("Inactivo".equals(est)&& filasel!=-1)
            {
                 this.btn_modificar.setEnabled(true);
                 this.btn_eliminar.setEnabled(false);
                 this.btn_reactivar.setEnabled(true);
            }
        }
        catch (Exception e){
        }
    }
void deshabilitar(){
        String descrip=(String) tabla_clientes.getValueAt(tabla_clientes.getSelectedRow(), 1);
        String ids= (String) tabla_clientes.getValueAt(tabla_clientes.getSelectedRow(), 0);
        String id=ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Inhabilitar el cliente "+descrip+"?", "Inhabilitar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEACliente Ecliente = new ClaseEACliente(id);
        Gest_cliente in = new Gest_cliente();
        boolean r;
        r=in.Eliminar(Ecliente);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Inhabilitado", "Inhabilitar",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
void reactivar()
    {
       String nomb=(String) tabla_clientes.getValueAt(tabla_clientes.getSelectedRow(), 1);
       String ids=(String) tabla_clientes.getValueAt(tabla_clientes.getSelectedRow(), 0);
       String id = ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Activar el Cliente "+nomb+"?", "Activar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEACliente Ecliente = new ClaseEACliente(id);
        Gest_cliente in = new Gest_cliente();
        boolean r;
        r=in.Activar(Ecliente);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Activado", "Activar",1,null);
        }
      
        }catch(SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_reactivar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        btn_mostrar_todo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        cbx_Eliminados = new javax.swing.JCheckBox();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setToolTipText("Gestionar Clientes");
        jPanel1.setAutoscrolls(true);
        jPanel1.setMinimumSize(new java.awt.Dimension(920, 370));
        jPanel1.setName(""); // NOI18N
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1formKeyReleased(evt);
            }
        });

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo1.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setToolTipText("Nuevo Cliente");
        btn_nuevo.setBorder(null);
        btn_nuevo.setBorderPainted(false);
        btn_nuevo.setContentAreaFilled(false);
        btn_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nuevo.setIconTextGap(-3);
        btn_nuevo.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_nuevo.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_nuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo2.png"))); // NOI18N
        btn_nuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo3.png"))); // NOI18N
        btn_nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setToolTipText("Cerrar Módulo");
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

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_modificar1.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setToolTipText("Modificar Cliente Seleccionado");
        btn_modificar.setBorder(null);
        btn_modificar.setBorderPainted(false);
        btn_modificar.setContentAreaFilled(false);
        btn_modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_modificar.setEnabled(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setIconTextGap(-3);
        btn_modificar.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_modificar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_modificar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_modificar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_modificar2.png"))); // NOI18N
        btn_modificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_modificar3.png"))); // NOI18N
        btn_modificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar1.png"))); // NOI18N
        btn_eliminar.setText("Inhabilitar");
        btn_eliminar.setToolTipText("Inhabilitar Cliente Seleccionado");
        btn_eliminar.setBorder(null);
        btn_eliminar.setBorderPainted(false);
        btn_eliminar.setContentAreaFilled(false);
        btn_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminar.setEnabled(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setIconTextGap(-3);
        btn_eliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar2.png"))); // NOI18N
        btn_eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar3.png"))); // NOI18N
        btn_eliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_reactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_react0.png"))); // NOI18N
        btn_reactivar.setText("Reactivar");
        btn_reactivar.setToolTipText("Reactivar Cliente Seleccionado");
        btn_reactivar.setBorder(null);
        btn_reactivar.setBorderPainted(false);
        btn_reactivar.setContentAreaFilled(false);
        btn_reactivar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reactivar.setEnabled(false);
        btn_reactivar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_reactivar.setIconTextGap(-3);
        btn_reactivar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_react1.png"))); // NOI18N
        btn_reactivar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_react2.png"))); // NOI18N
        btn_reactivar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_reactivar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_reactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(btn_reactivar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_reactivar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btn_reactivar.getAccessibleContext().setAccessibleDescription("Activar Cliente Seleccionado");

        jPanel3.setAutoscrolls(true);

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

        btn_mostrar_todo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo.setText("Mostrar Todo");
        btn_mostrar_todo.setToolTipText("Restablecer Tabla");
        btn_mostrar_todo.setBorder(null);
        btn_mostrar_todo.setBorderPainted(false);
        btn_mostrar_todo.setContentAreaFilled(false);
        btn_mostrar_todo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mostrar_todo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_mostrartodo.png"))); // NOI18N
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

        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_clientes.getTableHeader().setResizingAllowed(false);
        tabla_clientes.getTableHeader().setReorderingAllowed(false);
        tabla_clientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_clientesFocusLost(evt);
            }
        });
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_clientes);

        cbx_Eliminados.setText("Clientes Inactivos");
        cbx_Eliminados.setToolTipText("Mostrar Clientes con Estatus \"Inactivo\"");
        cbx_Eliminados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbx_Eliminados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbx_EliminadosMouseClicked(evt);
            }
        });
        cbx_Eliminados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_EliminadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_mostrar_todo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_Eliminados)
                .addContainerGap(159, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_buscar)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_mostrar_todo)
                        .addComponent(cbx_Eliminados)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
        );

        cbx_Eliminados.getAccessibleContext().setAccessibleDescription("Mostrar clientes con Estatus \"Inactivo\"");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(112, Short.MAX_VALUE))))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
   
        Nuevo_Cliente nuevo = new Nuevo_Cliente();
        nuevo.setVisible(true);
        desactivarBotones();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        int res = JOptionPane.showConfirmDialog(null,"¿Seguro Que Desea Cerrar Esta Pestaña?","Cerrar Pestaña",JOptionPane.YES_NO_OPTION);
        if (res==0)
        {
            Principal.panel_principal.remove(this);
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed

        Modificar_Cliente mod = new Modificar_Cliente();
        mod.setVisible(true);
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        deshabilitar();
        cargar();
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_reactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reactivarActionPerformed
        reactivar();
        cargar();
        desactivarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reactivarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        cargar();
        desactivarBotones();
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
        cargar();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_clientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_clientesFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesFocusLost

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void tabla_clientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseReleased
        validarBotones();   // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseReleased

    private void cbx_EliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbx_EliminadosMouseClicked
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosMouseClicked

    private void cbx_EliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_EliminadosActionPerformed
        cargar();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosActionPerformed

    private void jPanel1formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_mostrar_todo;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reactivar;
    public static javax.swing.JCheckBox cbx_Eliminados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla_clientes;
    public static javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
