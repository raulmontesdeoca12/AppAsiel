
package obed77.Ventas;

import obed77.clientes.*;
import BaseDeDatos.Clientes.ClaseCliente;
import BaseDeDatos.Clientes.Gest_cliente;
import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_descontar;
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

public class Modificar_ventaml extends javax.swing.JFrame {
Shape shape = null;
String prodv ;
int cantv;

    public Modificar_ventaml() {
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

  }
  
  
  void llenarCampos()
  {

    try {
        String id =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 0);
        String fecha=(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 1);
        String cliente =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 4);
        String telefono =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 5);
        String estado = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 6);
        String factura = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 7);
        String ref1 = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 8);
        String empresa =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 9);
        String ref2 = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 10);
        String fechae = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 11);
        String correo = (String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 12);
        
        switch (estado) {
            case "Por Cobrar":
            case "Transfirio":
            case "Cancelada":
                this.txt_factura.setEditable(false);
                this.txt_refco.setEditable(false);
                this.txt_refen.setEditable(false);
                this.dch_fechae.setEnabled(false);
                this.cbo_empresa.setEnabled(false);
                break;
            case "Cobrado":
            case "Cobrado MP":
            case "Facturado":
                this.txt_factura.setEditable(true);
                this.txt_refco.setEditable(true);
                this.txt_refen.setEditable(false);
                this.dch_fechae.setEnabled(false);
                this.cbo_empresa.setEnabled(false);
                break;
            case "Enviado":
            case "Concretada":
                this.dch_fechae.setEnabled(true);
                this.txt_factura.setEditable(true);
                this.txt_refco.setEditable(true);
                this.txt_refen.setEditable(true);
                this.cbo_empresa.setEnabled(true);
                break;
        }
        
        this.lab_id.setText(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date Fech = sdf.parse(fecha);
        if("Enviado".equals(estado) || "Concretada".equals(estado))
        {
            Date Feche = sdf.parse(fechae);
            this.dch_fechae.setDate(Feche);
            System.out.println("Fecha E "+Feche); 
        }else 
        {
            this.dch_fechae.setDate(null);
        }
        
        
        if("".equals(empresa))
        {
            this.cbo_empresa.setSelectedItem("Seleccione");
        }else
        {
            this.cbo_empresa.setSelectedItem(empresa);
        }
        this.dch_fecha.setDate(Fech);
        
        this.txt_cliente.setText(cliente);
        this.txt_telefono.setText(telefono);
        this.txt_factura.setText(factura);
        this.txt_refco.setText(ref1);
        this.txt_refen.setText(ref2);
        this.txt_correo.setText(correo);
    } catch (ParseException ex) {
        Logger.getLogger(Modificar_ventaml.class.getName()).log(Level.SEVERE, null, ex);
    }
      
}
       
void guardar(){
    int fac =0;
    
    String formato ="yyyy-MM-dd";
    Date fec=this.dch_fecha.getDate();
    Date fece=this.dch_fechae.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato);
    int id = Integer.parseInt(this.lab_id.getText());
    String fecha= sdf.format(fec);
    String fechae= "";
    String cliente=this.txt_cliente.getText();
    String telefono=this.txt_telefono.getText();
    String factura = this.txt_factura.getText();
    
    String empresa ="";
    
    String refco = this.txt_refco.getText();
    String refen = this.txt_refen.getText();
    String correo = this.txt_correo.getText();
    
    
    if (!cbo_empresa.isEnabled())
    {
        empresa="";
    }else
    {
        empresa = this.cbo_empresa.getSelectedItem().toString();
    }
    
    
    
    try{
        if(factura.isEmpty())
        {
        }else
        {
        fac=Integer.parseInt(factura);
        }
        
        if (fece!=null)
        {
            fechae=sdf2.format(fece);
        }
        clase_ml ml = new clase_ml(fecha, cliente, telefono, refco, refen, fac, id,fechae,empresa,correo);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        if(factura.isEmpty())
        {
        r=in.ModVenMlSf(ml);
        
        }else
        {
            
        fac=Integer.parseInt(factura);
        r=in.ModVenMlCf(ml);
        }
        
        if (r==false)
        {
            JOptionPane.showMessageDialog(this, "Venta modificada correctamente","Modificado",1,null);
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
                Logger.getLogger(Modificar_ventaml.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_refco = new javax.swing.JTextField();
        txt_refen = new javax.swing.JTextField();
        txt_factura = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lab_id = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dch_fechae = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        cbo_empresa = new javax.swing.JComboBox();
        txt_correo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Venta ML");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(480, 400));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar Venta"));
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
        btn_guardar.setToolTipText("Guardar Venta ML Modificada");
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

        jLabel12.setText("Factura:");

        jLabel14.setText("Ref. Cobro:");

        jLabel15.setText("Ref. Envío:");

        txt_refco.setEditable(false);
        txt_refco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_refcoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_refcoKeyTyped(evt);
            }
        });

        txt_refen.setEditable(false);
        txt_refen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_refenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_refenKeyTyped(evt);
            }
        });

        txt_factura.setEditable(false);
        txt_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_facturaKeyReleased(evt);
            }
        });

        jLabel16.setText("ID:");

        jLabel17.setText("Fecha de Envío:");

        dch_fechae.setMaximumSize(new java.awt.Dimension(110, 28));
        dch_fechae.setMinimumSize(new java.awt.Dimension(110, 28));
        dch_fechae.setOpaque(false);
        dch_fechae.setPreferredSize(new java.awt.Dimension(110, 28));

        jLabel18.setText("Empresa");

        cbo_empresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "ZOOM", "MRW", "Domesa", "Otro" }));
        cbo_empresa.setOpaque(false);

        txt_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_correoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_correoKeyTyped(evt);
            }
        });

        jLabel19.setText("Correo:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel14)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel12))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_refco, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(dch_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cliente))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_correo)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_id, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(122, 122, 122)
                                        .addComponent(btn_guardar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_limpiar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_salir))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(67, 67, 67))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                .addComponent(cbo_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(69, 69, 69)
                                                .addComponent(jLabel17))
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(txt_refen, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dch_fechae, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 19, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dch_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(2, 2, 2)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_refco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_refen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_guardar)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_limpiar))))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26))
                        .addComponent(dch_fechae, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 11, 480, 350);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 520, 470);

        setSize(new java.awt.Dimension(504, 400));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        Ventas_ml.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        Date fecha = dch_fecha.getDate();
        String cliente=this.txt_cliente.getText();
        String telefono=this.txt_telefono.getText();
        String correo=this.txt_correo.getText();
        EmailValidator ev = new EmailValidator();
        

        if (fecha == null || cliente.isEmpty() || "(  )-       ".equals(telefono)|| correo.isEmpty())
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
                    this.dispose();
                }
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

    private void txt_refenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_refenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_refenKeyReleased

    private void txt_facturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_facturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_facturaKeyReleased

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
        }
       
        
        
            // TODO add your handling code here:
    }//GEN-LAST:event_txt_clienteKeyTyped

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

    private void txt_refenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_refenKeyTyped
txt_refen = (JTextField) evt.getComponent();
        String cadena = txt_refen.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_refen.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_refenKeyTyped

    private void txt_correoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoKeyReleased

    private void txt_correoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_correoKeyTyped

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
            java.util.logging.Logger.getLogger(Modificar_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_ventaml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar_ventaml().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir;
    public javax.swing.JComboBox cbo_empresa;
    public com.toedter.calendar.JDateChooser dch_fecha;
    public com.toedter.calendar.JDateChooser dch_fechae;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel lab_id;
    public javax.swing.JPanel panel;
    public javax.swing.JTextField txt_cliente;
    public javax.swing.JTextField txt_correo;
    public javax.swing.JTextField txt_factura;
    public javax.swing.JTextField txt_refco;
    public javax.swing.JTextField txt_refen;
    public javax.swing.JFormattedTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
