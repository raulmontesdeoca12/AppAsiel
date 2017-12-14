
package obed77.productos;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Producto.Clase_mod_Producto;
import BaseDeDatos.Producto.Gest_producto;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Modificar_Producto extends javax.swing.JFrame {
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
Shape shape = null;
    public Modificar_Producto() {
        initComponents();
        llenarTipoProducto();
        llenarProveedor();
        llenarCampos();

    }
    
    
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
public void calcular(){
     double costo;
     double ganancia;
     double presi;
     double preci;
     double iva;
     double presir;
     double ivar;
     double precir;
     double preciml;
     double precimlr;
             
     String c;
     c=this.txt_costo.getText();
     if(c.isEmpty())
     {
         txt_precio_ci.setText("");
         txt_precio_si.setText("");
         txt_precio_ml.setText("");
     }else
     {
         ganancia=Double.parseDouble(this.cbx_ganancia.getSelectedItem().toString());
         costo=Double.parseDouble(c);
         presi=costo/ganancia;
         presir=Math.rint(presi*100)/100;
         this.txt_precio_si.setText(Double.toString(presir));
         iva=presir*0.12;
         ivar=Math.rint(iva*100)/100;
         preci=presir+ivar;
         precir=Math.rint(preci*100)/100;
         this.txt_precio_ci.setText(Double.toString(precir));
         preciml=precir/0.9;
         precimlr=Math.rint(preciml*100)/100;
         this.txt_precio_ml.setText(Double.toString(precimlr));
     }
     
 }
void llenarCampos()
  {

      String id =(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 0);
      String proveedor=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 1);
      String desc =(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 2);
      String tipo=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 3);
      String tasa=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 5);
      String costo=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 6);
      String precio_si=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 7);
      String precio_ci=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 8);
      String precio_ml=(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 9);
      
      this.txt_id.setText(id);
      this.txt_descrip.setText(desc);
      this.txt_costo.setText(costo);
      this.cbo_tipo_producto.setSelectedItem(tipo);
      this.cbo_proveedor.setSelectedItem(proveedor);
      this.txt_precio_si.setText(precio_si);
      this.txt_precio_ci.setText(precio_ci);
      this.cbx_ganancia.setSelectedItem(tasa);
      this.txt_precio_ml.setText(precio_ml);
  }
  
 public  void llenarTipoProducto(){
        cbo_tipo_producto.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT tipo_producto FROM tipo_producto");
            modeloCombo.addElement("Seleccione Tipo de Producto");
            cbo_tipo_producto.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("tipo_producto"));
                cbo_tipo_producto.setModel(modeloCombo);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Modificar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modificar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public  void llenarProveedor(){
        cbo_proveedor.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT razon_social_prov FROM proveedores");
            modeloCombo2.addElement("Seleccione Proveedor");
            cbo_proveedor.setModel(modeloCombo2);
            while (rs.next()) {
                modeloCombo2.addElement(rs.getObject("razon_social_prov"));
                cbo_proveedor.setModel(modeloCombo2);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Modificar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modificar_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
void guardar(){
    String idv =(String)Productos.tabla_productos.getValueAt(Productos.tabla_productos.getSelectedRow(), 0);
    String id=this.txt_id.getText();
    String descripcion=this.txt_descrip.getText();
    Double costo=Double.parseDouble(this.txt_costo.getText());
    String tipo=this.cbo_tipo_producto.getSelectedItem().toString();
    double precio_si=Double.parseDouble(this.txt_precio_si.getText());
    double precio_ci=Double.parseDouble(this.txt_precio_ci.getText());
    String cboproveedor=this.cbo_proveedor.getSelectedItem().toString();
    String proveedor="";
    double precio_ml=Double.parseDouble(this.txt_precio_ml.getText());
    String tasas=this.cbx_ganancia.getSelectedItem().toString();
    double tasa=Double.parseDouble(tasas);
    double ivan= precio_si*0.12;
    double iva= Math.rint(ivan*100)/100;
    double preciosimln=precio_si/0.9;
    double preciosiml=Math.rint(preciosimln*100)/100;
    double ivamln=preciosiml*0.12;
    double ivaml=Math.rint(ivamln*100)/100;
    
    
    try{
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT idproveedores FROM proveedores where razon_social_prov='"+cboproveedor+"'");
        if (rs.first())
        {
            proveedor=rs.getString("idproveedores");
        }
        Clase_mod_Producto producto = new Clase_mod_Producto(idv,id, descripcion, costo, precio_si, precio_ci,proveedor,tipo,precio_ml,tasa,iva,ivaml,preciosiml);
        Gest_producto in = new Gest_producto();
        boolean r;
        r=in.Modificar(producto);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Modificacion Exitosa", "Modificar",1,null);
        }
    }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos","Error",0,null);
                System.out.println(e);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Modificar_Producto.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        txt_descrip = new javax.swing.JTextField();
        txt_costo = new javax.swing.JTextField();
        cbo_tipo_producto = new javax.swing.JComboBox();
        cbo_proveedor = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_id = new javax.swing.JFormattedTextField();
        cbx_ganancia = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_precio_si = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_precio_ci = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_precio_ml = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Producto");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMaximumSize(new java.awt.Dimension(530, 355));
        setMinimumSize(new java.awt.Dimension(530, 355));
        setPreferredSize(new java.awt.Dimension(530, 355));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar Producto"));
        panel.setMinimumSize(new java.awt.Dimension(453, 450));
        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(453, 450));

        txt_descrip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descripKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_descripKeyTyped(evt);
            }
        });

        txt_costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_costoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_costoKeyTyped(evt);
            }
        });

        cbo_tipo_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbo_tipo_productoMouseEntered(evt);
            }
        });

        cbo_proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbo_proveedorMouseEntered(evt);
            }
        });

        jLabel1.setText("ID Producto:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Costo:");

        jLabel4.setText("Tipo de Producto: ");

        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar1.png"))); // NOI18N
        btn_limpiar.setText("Reanudar");
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
        btn_guardar.setToolTipText("Guardar Producto Modificado");
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

        jLabel8.setText("Proveedor:");

        try {
            txt_id.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("???-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cbx_ganancia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0.50", "0.55", "0.60", "0.65", "0.70", "0.75", "0.80" }));
        cbx_ganancia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_gananciaItemStateChanged(evt);
            }
        });

        jLabel5.setText("Ganancia:");

        jLabel6.setText("Precio Sin IVA:");

        txt_precio_si.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_precio_siKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_siKeyTyped(evt);
            }
        });

        jLabel10.setText("Precio Con IVA:");

        txt_precio_ci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_precio_ciKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_ciKeyTyped(evt);
            }
        });

        jLabel11.setText("Precio ML:");

        txt_precio_ml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precio_mlKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_precio_mlKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_mlKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btn_limpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salir))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_descrip)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(cbo_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(136, 136, 136)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(cbo_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_ganancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txt_precio_si, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio_ci, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txt_precio_ml, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_costo, txt_precio_ci, txt_precio_ml, txt_precio_si});

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(txt_descrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_tipo_producto)
                            .addComponent(cbo_proveedor))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbx_ganancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_precio_si, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_precio_ci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel11)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_precio_ml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_guardar)
                    .addComponent(btn_salir))
                .addGap(158, 158, 158))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 10, 510, 310);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        jLabel9.setMinimumSize(new java.awt.Dimension(500, 380));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 600, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_tipo_productoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipo_productoMouseEntered

    }//GEN-LAST:event_cbo_tipo_productoMouseEntered

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
llenarTipoProducto();
llenarProveedor();
llenarCampos();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
    String descripcion=this.txt_descrip.getText();
    String medida=this.txt_costo.getText();
    String tipo=this.cbo_tipo_producto.getSelectedItem().toString();
    String precio=this.txt_precio_si.getText();
    String proveedor=this.cbo_proveedor.getSelectedItem().toString();
    if (descripcion.isEmpty() || "".equals(medida) || "Seleccione Tipo de Producto".equals(tipo) ||"Seleccione Proveedor".equals(proveedor)|| "".equals(precio))
    {
        JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
    }
    else
    {
        guardar();
        Productos.cargar();
        this.dispose();
    }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Productos.cargar();
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_costoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costoKeyTyped
    char car = evt.getKeyChar();  
        if((car<'0' || car>'9') && txt_costo.getText().contains(".")){
         evt.consume();
        }else if((car<'0' || car>'9') && (car!='.')){
     evt.consume();
    }           // TODO add your handling code here:
    }//GEN-LAST:event_txt_costoKeyTyped

    private void cbo_proveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_proveedorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_proveedorMouseEntered

    private void txt_descripKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripKeyReleased

    private void cbx_gananciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_gananciaItemStateChanged
        calcular();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_gananciaItemStateChanged

    private void txt_precio_siKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_siKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_siKeyReleased

    private void txt_precio_siKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_siKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_siKeyTyped

    private void txt_precio_ciKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_ciKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_ciKeyReleased

    private void txt_precio_ciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_ciKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_ciKeyTyped

    private void txt_costoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costoKeyReleased
   calcular();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_costoKeyReleased

    private void txt_precio_mlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_mlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_mlKeyPressed

    private void txt_precio_mlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_mlKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_mlKeyReleased

    private void txt_precio_mlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_mlKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_mlKeyTyped

    private void txt_descripKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripKeyTyped
 txt_descrip = (JTextField) evt.getComponent();
        String cadena = txt_descrip.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_descrip.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripKeyTyped

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
            java.util.logging.Logger.getLogger(Modificar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Modificar_Producto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir;
    public javax.swing.JComboBox cbo_proveedor;
    public javax.swing.JComboBox cbo_tipo_producto;
    public javax.swing.JComboBox cbx_ganancia;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel panel;
    public javax.swing.JTextField txt_costo;
    public javax.swing.JTextField txt_descrip;
    public javax.swing.JFormattedTextField txt_id;
    public javax.swing.JTextField txt_precio_ci;
    public javax.swing.JTextField txt_precio_ml;
    public javax.swing.JTextField txt_precio_si;
    // End of variables declaration//GEN-END:variables
}
