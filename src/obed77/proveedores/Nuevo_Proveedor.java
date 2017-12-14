
package obed77.proveedores;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.proveedores.ClaseProveedor;
import BaseDeDatos.proveedores.Gest_proveedor;
import BaseDeDatos.proveedores.clase_prov_pago;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Nuevo_Proveedor extends javax.swing.JFrame {
Shape shape = null;
DefaultTableModel m;
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
    public Nuevo_Proveedor() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        inhabilitar();
        llenarTipoPago();
        llenarBanco();
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
        cbo_rif.setSelectedItem("J");
        txt_rif.setValue(null);
        txt_nombre.setText("");
        txt_telefono.setValue(null);
        txt_contacto.setText("");
        txt_direccion.setText("");
         for (int i = 0; i < tabla_pagos_prov.getRowCount(); i++) {
           m.removeRow(i);
           i-=1;
       }
         btn_sus_tp.setEnabled(false);
       
 }
 
  void accionbntnuevo(){
      
        cbo_rif.setEnabled(true);
        txt_rif.setEnabled(true);
        txt_nombre.setEnabled(true);
        txt_contacto.setEnabled(true);
        txt_telefono.setEnabled(true);
        txt_direccion.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_limpiar.setEnabled(true);
        btn_nuevo.setEnabled(false);
        btn_agg_tp.setEnabled(true);
        btn_sus_tp.setEnabled(false);

  }
  
   void inhabilitar(){
        cbo_rif.setEnabled(false);
        txt_rif.setEnabled(false);
        txt_nombre.setEnabled(false);
        txt_contacto.setEnabled(false);
        txt_telefono.setEnabled(false);
        txt_direccion.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_limpiar.setEnabled(false);
        btn_agg_tp.setEnabled(false);
        btn_sus_tp.setEnabled(false);
        limpiar();
    }
   
 public void validarRemover()
 {
     int filasel;
        try {
            filasel = tabla_pagos_prov.getSelectedRow();

            if (filasel != -1) {
                this.btn_sus_tp.setEnabled(true);

            } else {
                 this.btn_sus_tp.setEnabled(false);
            }


        } catch (Exception e) {
        }
    
 }
 
 public void llenarTipoPago(){
        cbo_tipo_pago.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT * FROM tipos_pago");
            modeloCombo.addElement("Seleccione Tipo de Pago.");
            cbo_tipo_pago.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("idtipos_pago")+"."+rs.getObject("tipos_pago"));
                cbo_tipo_pago.setModel(modeloCombo);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void validarPago()
 {
     String tipop = this.cbo_tipo_pago.getSelectedItem().toString();
     int pospunt = tipop.indexOf('.');
     String inttipo= tipop.substring(0,pospunt);
     
     switch(inttipo)
     {
         case "Seleccione Tipo de Pago":
             this.cbo_banco.setEnabled(false);
             this.txt_cuenta.setEnabled(false);
             this.txt_cuenta.setText("");
             break;
         case "1":
             this.cbo_banco.setEnabled(false);
             this.txt_cuenta.setEnabled(false);
             this.txt_cuenta.setText("No Aplica");
             break;
         case "2":
             this.cbo_banco.setEnabled(false);
             this.txt_cuenta.setEnabled(false);
             this.txt_cuenta.setText("No Aplica");
             break;
         case "3":
             this.cbo_banco.setEnabled(false);
             this.txt_cuenta.setEnabled(false);
             this.txt_cuenta.setText("No Aplica");
             break;
         case "4":
             this.cbo_banco.setEnabled(true);
             llenarBanco();
             this.txt_cuenta.setEnabled(true);
             this.txt_cuenta.setText("");
             break;
         case "5":
             this.cbo_banco.setEnabled(true);
             llenarBanco();
             this.txt_cuenta.setEnabled(true);
             this.txt_cuenta.setText("");
             break;
         case "6":
             this.cbo_banco.setEnabled(false);
             this.txt_cuenta.setEnabled(false);
             this.txt_cuenta.setText("No Aplica");
             break;
         
     }
 }
 public void llenarBanco(){
        cbo_banco.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT * FROM bancos");
            modeloCombo2.addElement("Seleccione Banco.");
            cbo_banco.setModel(modeloCombo2);
            while (rs.next()) {
                modeloCombo2.addElement(rs.getObject("idbancos")+"."+rs.getObject("bancos"));
                cbo_banco.setModel(modeloCombo2);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void llenarpago()
 {
    
     String tipop = this.cbo_tipo_pago.getSelectedItem().toString();
     int pospunt = tipop.indexOf('.');
     String ban = this.cbo_banco.getSelectedItem().toString();
     int pp= ban.indexOf('.');
     String refa = ban.substring(0,pp);
     String refb = ban.substring(pp+1,ban.length());
     
    
     
     String id, tip, ref, des; // id Tipo Pago Referencia Descriocion
     id= tipop.substring(0,pospunt);
     tip= tipop.substring(pospunt+1,tipop.length());
     
     if (refa.equals("Seleccione Banco"))
     {
         ref="No Aplica";
     }else
     {
         ref=refb;
     }
     des=this.txt_cuenta.getText();
     
     m=(DefaultTableModel)tabla_pagos_prov.getModel();
     String filaelemento[] = {id,tip,ref,des};
     m.addRow(filaelemento);
     
 
       
 }
 
 public void remover()
 {
      int fsel = tabla_pagos_prov.getSelectedRow();
      m=(DefaultTableModel)tabla_pagos_prov.getModel();
      m.removeRow(fsel);
      tabla_pagos_prov.setModel(m);
      btn_sus_tp.setEnabled(false);
 }
void GuardarPagos()
{
    try {
        String tipo, descripcion,desa,desb, proveedor;
        String tr = this.cbo_rif.getSelectedItem().toString();
        String rn=this.txt_rif.getText();
        String g="-";
        proveedor=tr+g+rn;
        
        int fc = tabla_pagos_prov.getRowCount();
        
        
       
            Gest_proveedor in = new Gest_proveedor();
            boolean p;
            for (int i = 0; i < fc; i++) {
                 String deberia="1"; 
            int idn=0;
            String idi;
            int cont=0;
            int contbdd=0;
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            Statement cst= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet co = cst.executeQuery("SELECT count(*) as total FROM pago_prov");
            if (co.next())
            {
                contbdd=co.getInt("total");
            }
        if(contbdd!=0)
        {
            String array[] = new String[contbdd];
            ResultSet  rs = st.executeQuery("SELECT idpago_prov FROM pago_prov order by idpago_prov asc");
            while(rs.next())
            {
                idi=rs.getString("idpago_prov");
                array[cont]=idi;
                cont++;
            }
            List lista = Arrays.asList(array);
            
            String comp[] = new String[contbdd];
            //insertar numeros
            for (int j = 0; j < contbdd; j++) {
                int sum= j+1;
                comp[j]=""+sum;
            }
            List listac = Arrays.asList(comp);
            System.out.println("Lista Comparar: "+listac);
           
//         buscar numero faltante
              for (int x=0;x<contbdd;x++) 
                {
                    int n1 = Integer.parseInt(lista.get(x).toString()), n2=Integer.parseInt(listac.get(x).toString());
                    
                    if (n1==n2)
                    {
                        System.out.println("Iguales");
                        idn=Integer.parseInt(lista.get(x).toString());
                        int sum=idn+1;
                        deberia=""+sum;
                    }else
                    {
                        System.out.println("No Iguales");
                        
                        deberia=listac.get(x).toString();
                        x=contbdd;
                    }
                    
                    
                }
             
        } 
                
                tipo=tabla_pagos_prov.getValueAt(i, 0).toString();
                desa=tabla_pagos_prov.getValueAt(i, 2).toString();
                desb=tabla_pagos_prov.getValueAt(i, 3).toString();
                descripcion=desa+"."+desb;
                clase_prov_pago pag = new clase_prov_pago(deberia,proveedor,tipo,descripcion);
                p=in.InsertarPago(pag);
            } 
            if (p=true)
            {
                JOptionPane.showMessageDialog(this,"Proveedor Guardado Correctamente","Guardado",1,null);
                this.dispose();
            }else
            {
                System.out.println("Error en Pagos");
            }     
        
    }catch (ClassNotFoundException ex) {
                    Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                  
                } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
    }
    
     
}
    

void guardar(){
    String tr = this.cbo_rif.getSelectedItem().toString();
    String rn=this.txt_rif.getText();
    String g="-";
    String rif=tr+g+rn;
    String razon=this.txt_nombre.getText();
    String contacto=this.txt_contacto.getText();
    String telefono=this.txt_telefono.getText();
    String direccion=this.txt_direccion.getText();
    String estatus = "Activo";
    
    try{
        ClaseProveedor proveedor = new ClaseProveedor(rif, razon, direccion, contacto, telefono,estatus);
        Gest_proveedor in = new Gest_proveedor();
        boolean r;
        r=in.Insertar(proveedor);
        if (r==false)
        {
            GuardarPagos();
        }else
        {
            System.out.println("Error en Proveedor");
        }
 }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE,null,ex);
            }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventana_pagos = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbo_tipo_pago = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbo_banco = new javax.swing.JComboBox();
        txt_cuenta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pagos_prov = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
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
        cbo_rif = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_contacto = new javax.swing.JTextField();
        btn_agg_tp = new javax.swing.JButton();
        btn_sus_tp = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        ventana_pagos.setTitle("Tipo de Pago");
        ventana_pagos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ventana_pagos.setLocationByPlatform(true);
        ventana_pagos.setMinimumSize(new java.awt.Dimension(320, 200));
        ventana_pagos.setResizable(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tipo de Pago:");

        cbo_tipo_pago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_tipo_pagoItemStateChanged(evt);
            }
        });
        cbo_tipo_pago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_tipo_pagoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbo_tipo_pagoMouseReleased(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Banco:");

        jLabel10.setText("Cuenta N°:");

        cbo_banco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_bancoActionPerformed(evt);
            }
        });

        txt_cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cuentaActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_tipo_pago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_banco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(0, 64, Short.MAX_VALUE))
                            .addComponent(txt_cuenta))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_tipo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout ventana_pagosLayout = new javax.swing.GroupLayout(ventana_pagos.getContentPane());
        ventana_pagos.getContentPane().setLayout(ventana_pagosLayout);
        ventana_pagosLayout.setHorizontalGroup(
            ventana_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_pagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ventana_pagosLayout.setVerticalGroup(
            ventana_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_pagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ins. Proveedor");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(519, 380));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Proveedor"));
        panel.setToolTipText("");
        panel.setMinimumSize(new java.awt.Dimension(468, 367));
        panel.setOpaque(false);

        tabla_pagos_prov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo de Pago", "Banco", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_pagos_prov.getTableHeader().setReorderingAllowed(false);
        tabla_pagos_prov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_pagos_provMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_pagos_prov);
        if (tabla_pagos_prov.getColumnModel().getColumnCount() > 0) {
            tabla_pagos_prov.getColumnModel().getColumn(0).setMinWidth(30);
            tabla_pagos_prov.getColumnModel().getColumn(0).setMaxWidth(30);
            tabla_pagos_prov.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_pagos_prov.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_pagos_prov.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_pagos_prov.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jLabel1.setText("Rif :");

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
        btn_guardar.setToolTipText("Guardar Nuevo Proveedor");
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

        cbo_rif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "J", "E", "G" }));

        jLabel4.setText("Contacto:");

        txt_contacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_contactoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_contactoKeyTyped(evt);
            }
        });

        btn_agg_tp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_agg_tp.setText("Agregar");
        btn_agg_tp.setToolTipText("Agregar Tipos de Pago");
        btn_agg_tp.setBorderPainted(false);
        btn_agg_tp.setContentAreaFilled(false);
        btn_agg_tp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg_tp.setEnabled(false);
        btn_agg_tp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_agg_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agg_tpActionPerformed(evt);
            }
        });

        btn_sus_tp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus.png"))); // NOI18N
        btn_sus_tp.setText("Quitar");
        btn_sus_tp.setToolTipText("Quitar Tipo de Pago Seleccionado");
        btn_sus_tp.setBorderPainted(false);
        btn_sus_tp.setContentAreaFilled(false);
        btn_sus_tp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sus_tp.setEnabled(false);
        btn_sus_tp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_sus_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sus_tpActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipos de Pagos :");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(4, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addComponent(cbo_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nombre))
                            .addComponent(txt_direccion)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addComponent(txt_contacto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(86, 86, 86)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_agg_tp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_sus_tp)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(btn_nuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_salir)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btn_agg_tp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sus_tp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_limpiar)))
                .addGap(49, 49, 49))
        );

        getContentPane().add(panel);
        panel.setBounds(10, 11, 500, 410);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 900, 430);

        setSize(new java.awt.Dimension(524, 425));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        //Clientes.cargar();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        String rif=this.txt_rif.getText();
        String nombre=this.txt_nombre.getText();
        String telefono=this.txt_telefono.getText();
        String contacto=this.txt_contacto.getText();
        String direccion=this.txt_direccion.getText();

        if (" -        - ".equals(rif) || nombre.isEmpty() || "(  )-       ".equals(telefono)|| contacto.isEmpty() || direccion.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        }
        else{
            guardar();
            Proveedores.cargar();
            inhabilitar();
            this.btn_nuevo.setEnabled(true);
        }// TODO add your handling code here:
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

    private void btn_agg_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agg_tpActionPerformed
        this.cbo_banco.setSelectedItem("Seleccione Banco.");
        this.cbo_banco.setEnabled(false);
        this.cbo_tipo_pago.setSelectedItem("Seleccione Tipo de Pago.");
        this.txt_cuenta.setText("");
        this.txt_cuenta.setEnabled(false);
        ventana_pagos.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_agg_tpActionPerformed

    private void btn_sus_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sus_tpActionPerformed
remover();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sus_tpActionPerformed

    private void cbo_bancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_bancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_bancoActionPerformed

    private void txt_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cuentaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
ventana_pagos.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbo_tipo_pagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoItemStateChanged
validarPago();        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tp = this.cbo_tipo_pago.getSelectedItem().toString();
        String ba = this.cbo_banco.getSelectedItem().toString();
        String cu = this.txt_cuenta.getText();
        
        if ((tp.equals("Seleccione Tipo de Pago.")  && cu.isEmpty()) || (tp.equals("4.Depósito")&& ba.equals("Seleccione Banco."))||(tp.equals("5.Transferencia")&& ba.equals("Seleccione Banco."))|| cu.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Error...\n No puede dejar Campos Vacios","Atención",1,null);
        }else
        {
            llenarpago();
            ventana_pagos.dispose();
        }
                
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbo_tipo_pagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoMouseClicked
          // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoMouseClicked

    private void cbo_tipo_pagoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoMouseReleased
       // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoMouseReleased

    private void tabla_pagos_provMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pagos_provMouseReleased
validarRemover();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_pagos_provMouseReleased

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
        }       // TODO add your handling code here:
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
        }         // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Nuevo_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Proveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Nuevo_Proveedor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_agg_tp;
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_nuevo;
    public javax.swing.JButton btn_salir;
    public javax.swing.JButton btn_sus_tp;
    public javax.swing.JComboBox cbo_banco;
    public javax.swing.JComboBox cbo_rif;
    public javax.swing.JComboBox cbo_tipo_pago;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panel;
    public javax.swing.JTable tabla_pagos_prov;
    public javax.swing.JTextField txt_contacto;
    public javax.swing.JTextField txt_cuenta;
    public javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_nombre;
    public javax.swing.JFormattedTextField txt_rif;
    public javax.swing.JFormattedTextField txt_telefono;
    public javax.swing.JDialog ventana_pagos;
    // End of variables declaration//GEN-END:variables
}
