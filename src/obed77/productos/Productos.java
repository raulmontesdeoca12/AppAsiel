/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.productos;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Producto.ClaseEAProducto;
import BaseDeDatos.Producto.Gest_producto;
import java.awt.event.KeyEvent;
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
import obed77.RenderStock;

/**
 *
 * @author user
 */
public class Productos extends javax.swing.JPanel {
    static DefaultTableModel model;
    /**
     * Creates new form Productos
     */
    public Productos() {
        initComponents();
        cargar();
        RenderStock ft= new RenderStock(4);
        tabla_productos.setDefaultRenderer(Object.class, ft);
        
    }
    
        public static void cargar(){
       
        try{
        String [] titulos={"Código","Proveedor","Descripción","Tipo de Producto","Cantidad","Ganancia","Costo","Precio Sin IVA","Precio Con IVA","Precio MercadoL","Estatus"};
        String [] registros= new String[11];
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        model = new DefaultTableModel(null,titulos){public boolean isCellEditable(int row, int column) {
return false;
}};
        
         boolean eli= cbx_Eliminados.isSelected(); 
           if(eli==false){
                sql = "SELECT * FROM producto INNER JOIN tipo_producto ON producto.fk_tipo_prod=tipo_producto.tipo_producto INNER JOIN proveedores ON producto.fk_proveedor=proveedores.idproveedores WHERE producto.estatus_prod='Activo' ORDER BY cantidad_prod DESC, descripcion_prod ASC ";
           }else{
                sql = "SELECT * FROM producto INNER JOIN tipo_producto ON producto.fk_tipo_prod=tipo_producto.tipo_producto INNER JOIN proveedores ON producto.fk_proveedor=proveedores.idproveedores ORDER BY cantidad_prod DESC, descripcion_prod ASC";
           }
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){

            registros[0]=rs.getString("producto.idproducto");
            registros[1]=rs.getString("proveedores.razon_social_prov");
            registros[2]=rs.getString("producto.descripcion_prod");
            registros[3]=rs.getString("tipo_producto.tipo_producto");
            registros[4]=rs.getString("producto.cantidad_prod");
            registros[5]=rs.getString("producto.tasa_calculo");
            registros[6]=rs.getString("producto.costo_prod");
            registros[7]=rs.getString("producto.precio_si");
            registros[8]=rs.getString("producto.precio_ci");
            registros[9]=rs.getString("producto.precio_ml");
            registros[10]=rs.getString("producto.estatus_prod");
            model.addRow(registros);
        }
        tabla_productos.setModel(model);
        tabla_productos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla_productos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla_productos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabla_productos.getColumnModel().getColumn(3).setPreferredWidth(120);
        tabla_productos.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabla_productos.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabla_productos.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabla_productos.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabla_productos.getColumnModel().getColumn(8).setPreferredWidth(120);
        tabla_productos.getColumnModel().getColumn(9).setPreferredWidth(120);
        tabla_productos.getColumnModel().getColumn(10).setPreferredWidth(80);
        TableRowSorter modeloOrdenado = new TableRowSorter(model);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
        tabla_productos.setRowSorter(modeloOrdenado);
        
    }catch(SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      void desactivarBotones()
    {
        this.btn_modificar.setEnabled(false);       
        this.btn_insertar.setEnabled(false);
        this.btn_extraer.setEnabled(false);
        this.btn_eliminar.setEnabled(false);
        this.btn_reactivar.setEnabled(false);
    }
      
    void validarBotones()
    {
        int filasel;
        String est;
        try
        {            
            filasel = tabla_productos.getSelectedRow();
            est = tabla_productos.getValueAt(filasel, 10).toString();

            if ("Activo".equals(est) && filasel!=-1)
            {
                 this.btn_modificar.setEnabled(true);
                 this.btn_insertar.setEnabled(true);
                 this.btn_extraer.setEnabled(true);
                 this.btn_eliminar.setEnabled(true);
                 this.btn_reactivar.setEnabled(false);
            }else
            if("Inactivo".equals(est)&& filasel!=-1)
            {
                 this.btn_modificar.setEnabled(true);
                 this.btn_insertar.setEnabled(true);
                 this.btn_extraer.setEnabled(true);
                 this.btn_eliminar.setEnabled(false);
                 this.btn_reactivar.setEnabled(true);
            }
        }
        catch (Exception e){
        }
    }
void deshabilitar(){
        String descrip=(String) tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 2);
        String ids= (String) tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 0);
        String id=ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Deshabilitar el producto "+descrip+"?", "Deshabilitar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAProducto Eproducto = new ClaseEAProducto(id);
        Gest_producto in = new Gest_producto();
        boolean r;
        r=in.Eliminar(Eproducto);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Inhabilidato", "Inhabilitarr",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
void reactivar()
    {
       String nomb=(String) tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 2);
       String ids=(String) tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 0);
       String id = ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Activar el Producto "+nomb+"?", "Activar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAProducto Eproducto = new ClaseEAProducto(id);
        Gest_producto in = new Gest_producto();
        boolean r;
        r=in.Activar(Eproducto);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Activado", "Activar",1,null);
        }
      
        }catch(SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
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
        btn_extraer = new javax.swing.JButton();
        btn_insertar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_reactivar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        btn_mostrar_todo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        cbx_Eliminados = new javax.swing.JCheckBox();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

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
        btn_nuevo.setToolTipText("Nuevo Producto");
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
        btn_modificar.setToolTipText("Modificar Producto Seleccionado");
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

        btn_extraer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_extraer.setText("Extraer");
        btn_extraer.setToolTipText("Extraer del Stock");
        btn_extraer.setBorder(null);
        btn_extraer.setBorderPainted(false);
        btn_extraer.setContentAreaFilled(false);
        btn_extraer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_extraer.setEnabled(false);
        btn_extraer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_extraer.setIconTextGap(-3);
        btn_extraer.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_extraer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_extraer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_extraerActionPerformed(evt);
            }
        });

        btn_insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_insertar.setText("Insertar");
        btn_insertar.setToolTipText("Insertar en el Stock");
        btn_insertar.setBorder(null);
        btn_insertar.setBorderPainted(false);
        btn_insertar.setContentAreaFilled(false);
        btn_insertar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_insertar.setEnabled(false);
        btn_insertar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_insertar.setIconTextGap(-3);
        btn_insertar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_insertar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_insertar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_insertar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar1.png"))); // NOI18N
        btn_eliminar.setText("Inhabilitar");
        btn_eliminar.setToolTipText("Inhabilitar Producto Seleccionado");
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
        btn_reactivar.setToolTipText("Reactivar Producto Seleccionado");
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
                    .addComponent(btn_insertar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_extraer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(btn_reactivar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_extraer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_reactivar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_productos.getTableHeader().setResizingAllowed(false);
        tabla_productos.getTableHeader().setReorderingAllowed(false);
        tabla_productos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_productosFocusLost(evt);
            }
        });
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_productosMouseReleased(evt);
            }
        });
        tabla_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_productos);

        cbx_Eliminados.setText("Productos Inactivos");
        cbx_Eliminados.setToolTipText("Mostrar productos con Estatus \"Inactivo\"");
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
                .addContainerGap(149, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
        );

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        Nuevo_Producto nuevo = new Nuevo_Producto();
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

        Modificar_Producto mod = new Modificar_Producto();
        mod.setVisible(true);
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_extraerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_extraerActionPerformed
        Extraer_Stock ex = new Extraer_Stock();
        ex.setVisible(true);
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_extraerActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        Insertar_Stock is = new Insertar_Stock();
        is.setVisible(true);
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertarActionPerformed

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

    private void tabla_productosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_productosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productosFocusLost

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void tabla_productosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseReleased
        validarBotones();   // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productosMouseReleased

    private void cbx_EliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbx_EliminadosMouseClicked
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosMouseClicked

    private void cbx_EliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_EliminadosActionPerformed
        cargar();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosActionPerformed

    private void jPanel1formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formKeyReleased

    private void tabla_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productosKeyPressed
   int filasel;
        try
        {            
            filasel = tabla_productos.getSelectedRow();

            if (filasel!=-1 && evt.getKeyCode() == KeyEvent.VK_ADD)
            {
                 Insertar_Stock is = new Insertar_Stock();
                 is.setVisible(true);
                 desactivarBotones();
            }else
            if(filasel!=-1 && evt.getKeyCode() == KeyEvent.VK_SUBTRACT)
            {
                 Extraer_Stock ex = new Extraer_Stock();
                 ex.setVisible(true);
                 desactivarBotones();
            }
        }
        catch (Exception e){
        }// TODO add your handling code here:
    }//GEN-LAST:event_tabla_productosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_extraer;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_mostrar_todo;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reactivar;
    public static javax.swing.JCheckBox cbx_Eliminados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla_productos;
    public static javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
