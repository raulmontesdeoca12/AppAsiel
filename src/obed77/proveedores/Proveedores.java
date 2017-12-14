/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.proveedores;


import BaseDeDatos.ConexionBD;
import BaseDeDatos.proveedores.ClaseEAProveedor;
import BaseDeDatos.proveedores.Gest_proveedor;
import BaseDeDatos.proveedores.clase_prov_pago;
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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import obed77.Principal;

/**
 *
 * @author user
 */
public class Proveedores extends javax.swing.JPanel {
static DefaultTableModel model;
DefaultTableModel m;
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
    /**
     * Creates new form Productos
     */
    public Proveedores() {
        initComponents();
        limpiarpagos();
        llenarTipoPago();
        llenarBanco();
        cargar();
    }
    
        public static void cargar(){
        
        try{
        String [] titulos={"RIF","Razón Social","Dirección","Contacto","Teléfono","Estatus"};
        String [] registros= new String[6];
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        model = new DefaultTableModel(null,titulos){public boolean isCellEditable(int row, int column) {
return false;
}};
         boolean eli= cbx_Eliminados.isSelected(); 
           if(eli==false){
                sql = "SELECT * FROM proveedores WHERE proveedores.estatus_prov='Activo' ORDER BY razon_social_prov ASC ";
           }else{
                sql = "SELECT * FROM proveedores ORDER BY estatus_prov ASC, razon_social_prov ASC";
           }
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){

            registros[0]=rs.getString("idproveedores");
            registros[1]=rs.getString("razon_social_prov");
            registros[2]=rs.getString("direccion_prov");
            registros[3]=rs.getString("contacto_prov");
            registros[4]=rs.getString("telefono_prov");
            registros[5]=rs.getString("estatus_prov");
            model.addRow(registros);
        }
        tabla_proveedores.setModel(model);
        tabla_proveedores.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla_proveedores.getColumnModel().getColumn(1).setPreferredWidth(350);
        tabla_proveedores.getColumnModel().getColumn(2).setPreferredWidth(350);
        tabla_proveedores.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabla_proveedores.getColumnModel().getColumn(4).setPreferredWidth(110);
        tabla_proveedores.getColumnModel().getColumn(5).setPreferredWidth(70);
        TableRowSorter modeloOrdenado = new TableRowSorter(model);
        tabla_proveedores.setRowSorter(modeloOrdenado);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
        tabla_proveedores.setRowSorter(modeloOrdenado);
    }catch(SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
      void limpiarpagos()
        {
            for (int i = 0; i < tabla_pagos.getRowCount(); i++) {
           m.removeRow(i);
           i-=1;
           this.btn_agg_tp.setEnabled(false);
       }
        }
        
     void cargarPagos()
    {
        limpiarpagos();
        this.btn_agg_tp.setEnabled(true);
        int fsel = tabla_proveedores.getSelectedRow();
        if (fsel!=-1)
        {
            String prov =tabla_proveedores.getValueAt(fsel, 0).toString();
        try{
           
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        sql = "SELECT * FROM pago_prov INNER JOIN tipos_pago ON tipos_pago.idtipos_pago = pago_prov.tipo WHERE proveedor_fk='"+prov+"'  ORDER BY idpago_prov ASC ";
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){
            String id = rs.getString("pago_prov.idpago_prov");
            String tip = rs.getString("tipos_pago.tipos_pago");
            String refp = rs.getString("pago_prov.descripcion");
            int pp= refp.indexOf('.');
            String ref = refp.substring(0,pp);
            String des = refp.substring(pp+1,refp.length());
           
            m=(DefaultTableModel)tabla_pagos.getModel();
            
            String filaelemento[] = {id,tip,ref,des};
            m.addRow(filaelemento);
           
        }
        
    }catch(SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            limpiarpagos();
            
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
            filasel = tabla_proveedores.getSelectedRow();
            est = tabla_proveedores.getValueAt(filasel, 5).toString();

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
        String descrip=(String) tabla_proveedores.getValueAt(tabla_proveedores.getSelectedRow(), 1);
        String ids= (String) tabla_proveedores.getValueAt(tabla_proveedores.getSelectedRow(), 0);
        String id=ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Inhabilitar el Proveedor "+descrip+"?", "Inhabilitar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAProveedor Eproveedor = new ClaseEAProveedor(id);
        Gest_proveedor in = new Gest_proveedor();
        boolean r;
        r=in.Eliminar(Eproveedor);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Inhabilitado", "Inhabilitar",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
void reactivar()
    {
       String nomb=(String) tabla_proveedores.getValueAt(tabla_proveedores.getSelectedRow(), 1);
       String ids=(String) tabla_proveedores.getValueAt(tabla_proveedores.getSelectedRow(), 0);
       String id = ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Activar el Proveedor "+nomb+"?", "Activar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAProveedor Eproveedor = new ClaseEAProveedor(id);
        Gest_proveedor in = new Gest_proveedor();
        boolean r;
        r=in.Activar(Eproveedor);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Activado", "Activar",1,null);
        }
      
        }catch(SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
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


void GuardarPagos()
{
    String proveedor;
    proveedor=(String)Proveedores.tabla_proveedores.getValueAt(Proveedores.tabla_proveedores.getSelectedRow(), 0);
    
    try {
             Gest_proveedor in = new Gest_proveedor();
             boolean p;
             String tipop = this.cbo_tipo_pago.getSelectedItem().toString();
             int pospunt = tipop.indexOf('.');
             String ban = this.cbo_banco.getSelectedItem().toString();
             int pp= ban.indexOf('.');
             String refa = ban.substring(0,pp);
             String refb = ban.substring(pp+1,ban.length());



             String id, ref, des, descripcion; // id Tipo Pago Referencia Descriocion
             id= tipop.substring(0,pospunt);

             if (refa.equals("Seleccione Banco"))
             {
                 ref="No Aplica";
             }else
             {
                 ref=refb;
             }
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
            for (int i = 0; i < contbdd; i++) {
                int sum= i+1;
                comp[i]=""+sum;
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
             
             des=this.txt_cuenta.getText();
             descripcion = ref+"."+des;
             clase_prov_pago pag = new clase_prov_pago(deberia,proveedor,id,descripcion);
                    p=in.InsertarPago(pag);

                    if (p=true)
                    {
                        JOptionPane.showMessageDialog(this,"Pago Agregado Correctamente","Guardado",1,null);
                        cargarPagos();
                        
                    }else
                    {
                        System.out.println("Error en Pagos");
                    }
        
            }catch (SQLException ex) {
                            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                        }

     
}



public void validarRemover()
 {
     int filasel;
        try {
            filasel = tabla_pagos.getSelectedRow();

            if (filasel != -1) {
                this.btn_sus_tp.setEnabled(true);

            } else {
                 this.btn_sus_tp.setEnabled(false);
            }


        } catch (Exception e) {
        }
    
 }
public void remover()
 {
    String id;
    id=(String)Proveedores.tabla_pagos.getValueAt(Proveedores.tabla_pagos.getSelectedRow(), 0);
    
    try {
            clase_prov_pago pag = new clase_prov_pago(id);
            boolean p;
            Gest_proveedor in = new Gest_proveedor();
            p=in.EliminarPago(pag);

            if (p=true)
            {
                JOptionPane.showMessageDialog(this,"Pago Eliminado Correctamente","Guardado",1,null);
                cargarPagos();
                this.btn_sus_tp.setEnabled(false);

            }else
            {
                System.out.println("Error en Pagos");
            }
    }catch (SQLException ex) {
                    Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Nuevo_Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                }

 }
void fin(){
    
}


@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventana_pagos = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbo_tipo_pago = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbo_banco = new javax.swing.JComboBox();
        txt_cuenta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        tabla_proveedores = new javax.swing.JTable();
        cbx_Eliminados = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_pagos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_agg_tp = new javax.swing.JButton();
        btn_sus_tp = new javax.swing.JButton();

        ventana_pagos.setTitle("Tipo de Pago");
        ventana_pagos.setAlwaysOnTop(true);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_tipo_pago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_banco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(0, 64, Short.MAX_VALUE))
                            .addComponent(txt_cuenta))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_tipo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ventana_pagosLayout.setVerticalGroup(
            ventana_pagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_pagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        btn_nuevo.setToolTipText("Nuevo Proveedor");
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
        btn_modificar.setToolTipText("Modificar Proveedor Seleccionado");
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
        btn_eliminar.setToolTipText("Inhabilitar Proveedor Seleccionado");
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
        btn_reactivar.setToolTipText("Reactivar Proveedor Seleccionado");
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
                .addContainerGap(56, Short.MAX_VALUE))
        );

        btn_nuevo.getAccessibleContext().setAccessibleDescription("Nuevo Cliente");
        btn_modificar.getAccessibleContext().setAccessibleDescription("Modificar Cliente Seleccionado");
        btn_eliminar.getAccessibleContext().setAccessibleDescription("Inhabilitar Cliente Seleccionado");
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

        tabla_proveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_proveedores.getTableHeader().setResizingAllowed(false);
        tabla_proveedores.getTableHeader().setReorderingAllowed(false);
        tabla_proveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_proveedoresMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_proveedoresMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_proveedores);

        cbx_Eliminados.setText("Proveedores Inactivos");
        cbx_Eliminados.setToolTipText("Mostrar proveedores con Estatus \"Inactivo\"");
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

        tabla_pagos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_pagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_pagosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_pagos);
        if (tabla_pagos.getColumnModel().getColumnCount() > 0) {
            tabla_pagos.getColumnModel().getColumn(0).setMinWidth(30);
            tabla_pagos.getColumnModel().getColumn(0).setMaxWidth(30);
            tabla_pagos.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_pagos.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_pagos.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_pagos.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        btn_agg_tp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_agg_tp.setText("Agregar");
        btn_agg_tp.setToolTipText("Agregar Tipo de Pago");
        btn_agg_tp.setBorderPainted(false);
        btn_agg_tp.setContentAreaFilled(false);
        btn_agg_tp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg_tp.setEnabled(false);
        btn_agg_tp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_agg_tp.setIconTextGap(5);
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
        btn_sus_tp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_sus_tp.setIconTextGap(12);
        btn_sus_tp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_sus_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sus_tpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_agg_tp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_sus_tp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_agg_tp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_sus_tp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mostrar_todo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_Eliminados)
                        .addContainerGap(343, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        Nuevo_Proveedor nuevo = new Nuevo_Proveedor();
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

        Modificar_Proveedor mod = new Modificar_Proveedor();
        mod.setVisible(true);
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        deshabilitar();
        cargar();
        limpiarpagos();
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_reactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reactivarActionPerformed
        reactivar();
        cargar();
        limpiarpagos();
        desactivarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reactivarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        cargar();
        limpiarpagos();
        desactivarBotones();
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
        cargar();
        limpiarpagos();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_proveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_proveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_proveedoresMouseClicked

    private void tabla_proveedoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_proveedoresMouseReleased
        validarBotones();
        cargarPagos();// TODO add your handling code here:
    }//GEN-LAST:event_tabla_proveedoresMouseReleased

    private void cbx_EliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbx_EliminadosMouseClicked
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosMouseClicked

    private void cbx_EliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_EliminadosActionPerformed
        cargar();  
        limpiarpagos();// TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosActionPerformed

    private void jPanel1formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formKeyReleased

    private void btn_agg_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agg_tpActionPerformed
        this.cbo_banco.setSelectedItem("Seleccione Banco.");
        this.cbo_banco.setEnabled(false);
        this.cbo_tipo_pago.setSelectedItem("Seleccione Tipo de Pago.");
        this.txt_cuenta.setText("");
        this.txt_cuenta.setEnabled(false);
        ventana_pagos.setLocationRelativeTo(this);
        ventana_pagos.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_agg_tpActionPerformed

    private void btn_sus_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sus_tpActionPerformed
        remover();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sus_tpActionPerformed

    private void cbo_tipo_pagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoItemStateChanged
        validarPago();        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoItemStateChanged

    private void cbo_tipo_pagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoMouseClicked

    private void cbo_tipo_pagoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipo_pagoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipo_pagoMouseReleased

    private void cbo_bancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_bancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_bancoActionPerformed

    private void txt_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cuentaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tp = this.cbo_tipo_pago.getSelectedItem().toString();
        String ba = this.cbo_banco.getSelectedItem().toString();
        String cu = this.txt_cuenta.getText();

        if ((tp.equals("Seleccione Tipo de Pago.")  && cu.isEmpty()) || (tp.equals("4.Depósito")&& ba.equals("Seleccione Banco."))||(tp.equals("5.Transferencia")&& ba.equals("Seleccione Banco."))|| cu.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Error...\n No puede dejar Campos Vacios","Atención",1,null);
        }else
        {
            GuardarPagos();
            ventana_pagos.dispose();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ventana_pagos.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_pagosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pagosMouseReleased
validarRemover();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_pagosMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agg_tp;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_mostrar_todo;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reactivar;
    private javax.swing.JButton btn_sus_tp;
    private javax.swing.JComboBox cbo_banco;
    private javax.swing.JComboBox cbo_tipo_pago;
    public static javax.swing.JCheckBox cbx_Eliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tabla_pagos;
    public static javax.swing.JTable tabla_proveedores;
    public static javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_cuenta;
    private javax.swing.JDialog ventana_pagos;
    // End of variables declaration//GEN-END:variables
}
