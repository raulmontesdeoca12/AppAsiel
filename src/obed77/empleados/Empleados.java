/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.empleados;


import BaseDeDatos.ConexionBD;
import BaseDeDatos.Empleados.ClaseEAEmpleado;
import BaseDeDatos.Empleados.Clase_cuenta_empleado;
import BaseDeDatos.Empleados.Clase_telefono_empleado;
import BaseDeDatos.Empleados.Gest_empleado;
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
public class Empleados extends javax.swing.JPanel {
static DefaultTableModel model;
static DefaultTableModel m;
static DefaultTableModel m2;
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
    /**
     * Creates new form Productos
     */
    public Empleados() {
        initComponents();
        limpiarextra();
        llenarBanco();
        cargar();
    }
    
        public static void cargar(){
        limpiarextra();
        try{
        String [] titulos={"Cédula","Nombre","Apellido","F.Nacimiento","E.Civil","Correo","Dirección","Estatus"};
        String [] registros= new String[8];
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        model = new DefaultTableModel(null,titulos){public boolean isCellEditable(int row, int column) {
return false;
}};
         boolean eli= cbx_Eliminados.isSelected(); 
           if(eli==false){
                sql = "SELECT * FROM empleados WHERE empleados.estatus='Activo' ORDER BY nombre ASC ";
           }else{
                sql = "SELECT * FROM empleados ORDER BY nombre ASC";
           }
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){

            registros[0]=rs.getString("ci_empleado");
            registros[1]=rs.getString("nombre");
            registros[2]=rs.getString("apellido");
            registros[3]=rs.getString("f_nac");
            registros[4]=rs.getString("estado");
            registros[5]=rs.getString("correo");
            registros[6]=rs.getString("direccion");
            registros[7]=rs.getString("estatus");
            model.addRow(registros);
        }
        tabla_empleados.setModel(model);
        tabla_empleados.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla_empleados.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla_empleados.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla_empleados.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla_empleados.getColumnModel().getColumn(4).setPreferredWidth(80);
        tabla_empleados.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla_empleados.getColumnModel().getColumn(6).setPreferredWidth(350);
        tabla_empleados.getColumnModel().getColumn(7).setPreferredWidth(70);
        TableRowSorter modeloOrdenado = new TableRowSorter(model);
        tabla_empleados.setRowSorter(modeloOrdenado);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
        tabla_empleados.setRowSorter(modeloOrdenado);
    }catch(SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
      static void limpiarextra()
        {
            for (int i = 0; i < tabla_cuentas.getRowCount(); i++) {
           m.removeRow(i);
           i-=1;
           
       }
            
            for (int i = 0; i < tabla_telefonos.getRowCount(); i++) {
           m2.removeRow(i);
           i-=1;
          
       }
           btn_contrato.setEnabled(false);
           btn_agg_cuenta.setEnabled(false); 
           btn_agg_telefono.setEnabled(false);
           lab_fecha_in.setText("");
           lab_cargo.setText("");
           lab_horario.setText("");
           lab_base.setText("");
           lab_otro.setText("");
           lab_alim.setText("");
           
        }
      
        
     void cargarextra()
    {
        limpiarextra();
        this.btn_contrato.setEnabled(true);
        this.btn_agg_cuenta.setEnabled(true);
        this.btn_agg_telefono.setEnabled(true);
        int fsel = tabla_empleados.getSelectedRow();
        if (fsel!=-1)
        {
            String emp =tabla_empleados.getValueAt(fsel, 0).toString();
        try{
           
        String sql,sql2,sql3,sql4;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        sql = "SELECT * FROM cuenta_be WHERE  fk_empleado='"+emp+"'ORDER BY banco ASC ";
        sql2= "SELECT * FROM telefonos  WHERE fk_persona='"+emp+"'ORDER BY tipo ASC ";
        sql3= "SELECT * FROM contratos WHERE fk_empleado='"+emp+"'ORDER BY idcontratos";
        sql4= "SELECT cesta_ticket FROM datos_extras";
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        ResultSet  rs2= st2.executeQuery(sql2);
        ResultSet  rs3= st3.executeQuery(sql3);
        ResultSet  rs4= st4.executeQuery(sql4);
        while(rs.next()){
            String id = rs.getString("idcuenta_be");
            String ban = rs.getString("banco");
            String tip = rs.getString("tipo");
            String num = rs.getString("numero");
            m=(DefaultTableModel)tabla_cuentas.getModel();
            
            String filaelemento[] = {id,ban,tip,num};
            m.addRow(filaelemento);
           
        }
        while(rs2.next()){
            String id = rs2.getString("idtelefonos");
            String tip = rs2.getString("tipo");
            String num = rs2.getString("numero");
           
            m2=(DefaultTableModel)tabla_telefonos.getModel();
            
            String filaelemento[] = {id,tip,num};
            m2.addRow(filaelemento);
           
        }
        
        if (rs3.last())
        {
           this.lab_fecha_in.setText(rs3.getString("fecha_ingreso"));
           this.lab_cargo.setText(rs3.getString("fk_cargo"));
           this.lab_horario.setText(rs3.getString("fk_horario"));
           Double su = rs3.getDouble("sueldo_final");
           Double sur= Math.rint((su*30)*100)/100;
           this.lab_base.setText(""+sur);
           this.lab_otro.setText(""+Math.rint((su)*10000)/10000);
           if(rs4.first())
           {
               Double ce = rs4.getDouble("cesta_ticket");
               Double cer = Math.rint((ce*30)*100)/100;
               this.lab_alim.setText(""+cer);
           }
        }
    }catch(SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            limpiarextra();
            
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
            filasel = tabla_empleados.getSelectedRow();
            est = tabla_empleados.getValueAt(filasel, 7).toString();

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
        String n=(String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 1);
        String a= (String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 2);
        String descrip = n+" "+a;
        String ids= (String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 0);
        String id=ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Inhabilitar el Empleado "+descrip+"?", "Inhabilitar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAEmpleado Eempleado = new ClaseEAEmpleado(id);
        Gest_empleado in = new Gest_empleado();
        boolean r;
        r=in.Eliminar(Eempleado);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Inhabilitado", "Inhabilitar",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
void reactivar()
    {
       String n=(String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 1);
       String a= (String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 2);
       String descrip = n+" "+a;
       String ids=(String) tabla_empleados.getValueAt(tabla_empleados.getSelectedRow(), 0);
       String id = ids;
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Activar el Empleado "+descrip+"?", "Activar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        ClaseEAEmpleado Eempleado = new ClaseEAEmpleado(id);
        Gest_empleado in = new Gest_empleado();
        boolean r;
        r=in.Activar(Eempleado);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Activado", "Activar",1,null);
        }
      
        }catch(SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


void GuardarCuenta()
{
    String empleado;
    empleado=(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 0);
    
    try {
             Gest_empleado in = new Gest_empleado();
             boolean p;
             String banp = this.cbo_banco.getSelectedItem().toString();
             int pp= banp.indexOf('.');
             String ban = banp.substring(pp+1,banp.length());
             String tip = this.cbo_tipo.getSelectedItem().toString();
             String num = this.txt_cuenta.getText();



             String id, ref, des, descripcion;

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
            ResultSet co = cst.executeQuery("SELECT count(*) as total FROM cuenta_be");
            if (co.next())
            {
                contbdd=co.getInt("total");
            }
        if(contbdd!=0)
        {
            String array[] = new String[contbdd];
            ResultSet  rs = st.executeQuery("SELECT idcuenta_be FROM cuenta_be order by idcuenta_be asc");
            while(rs.next())
            {
                idi=rs.getString("idcuenta_be");
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
             
             
             Clase_cuenta_empleado pag = new Clase_cuenta_empleado(deberia,ban,tip,num,empleado);
                    p=in.InsertarCuenta(pag);

                    if (p=true)
                    {
                        JOptionPane.showMessageDialog(this,"Cuenta Agregada Correctamente","Guardado",1,null);
                        cargarextra();
                        
                    }else
                    {
                        System.out.println("Error en Cuenta");
                    }
        
            }catch (SQLException ex) {
                            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                        }

     
}

void GuardarTelefono()
{
    String empleado;
    empleado=(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 0);
    
    try {
             Gest_empleado in = new Gest_empleado();
             boolean p;
             String tipo = this.cbo_tipo_t.getSelectedItem().toString();
             String num = this.txt_telefono.getText();
             String id;

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
            ResultSet co = cst.executeQuery("SELECT count(*) as total FROM telefonos");
            if (co.next())
            {
                contbdd=co.getInt("total");
            }
        if(contbdd!=0)
        {
            String array[] = new String[contbdd];
            ResultSet  rs = st.executeQuery("SELECT idtelefonos FROM telefonos order by idtelefonos asc");
            while(rs.next())
            {
                idi=rs.getString("idtelefonos");
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
             
             
             Clase_telefono_empleado tel = new Clase_telefono_empleado(deberia,tipo,num,empleado);
                    p=in.InsertarTelefono(tel);

                    if (p=true)
                    {
                        JOptionPane.showMessageDialog(this,"Teléfono Agregado Correctamente","Guardado",1,null);
                        cargarextra();
                        
                    }else
                    {
                        System.out.println("Error en Telefono");
                    }
        
            }catch (SQLException ex) {
                            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                        }

     
}

public void validarRemoverCuentas()
 {
     int filasel;
        try {
            filasel = tabla_cuentas.getSelectedRow();

            if (filasel != -1) {
                this.btn_sus_cuenta.setEnabled(true);

            } else {
                 this.btn_sus_cuenta.setEnabled(false);
            }


        } catch (Exception e) {
        }
    
 }
public void validarRemoverTelefonos()
 {
     int filasel;
        try {
            filasel = tabla_telefonos.getSelectedRow();

            if (filasel != -1) {
                this.btn_sus_telefono.setEnabled(true);

            } else {
                 this.btn_sus_telefono.setEnabled(false);
            }


        } catch (Exception e) {
        }
    
 }
public void removerCuenta()
 {
    String id;
    id=(String)Empleados.tabla_cuentas.getValueAt(Empleados.tabla_cuentas.getSelectedRow(), 0);
    
    try {
            Clase_cuenta_empleado pag = new Clase_cuenta_empleado(id);
            boolean p;
            Gest_empleado in = new Gest_empleado();
            p=in.EliminarCuenta(pag);

            if (p=true)
            {
                JOptionPane.showMessageDialog(this,"Cuenta Eliminada Correctamente","Eliminada",1,null);
                cargarextra();
                this.btn_sus_cuenta.setEnabled(false);

            }else
            {
                System.out.println("Error en cuenta");
            }
    }catch (SQLException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                }

 }
public void removerTelefono()
 {
    String id;
    id=(String)Empleados.tabla_telefonos.getValueAt(Empleados.tabla_telefonos.getSelectedRow(), 0);
    
    try {
            Clase_telefono_empleado pag = new Clase_telefono_empleado(id);
            boolean p;
            Gest_empleado in = new Gest_empleado();
            p=in.EliminarTelefono(pag);

            if (p=true)
            {
                JOptionPane.showMessageDialog(this,"Teléfono Eliminado Correctamente","Eliminado",1,null);
                cargarextra();
                this.btn_sus_cuenta.setEnabled(false);

            }else
            {
                System.out.println("Error en telefonos");
            }
    }catch (SQLException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                }

 }
void fin(){
    
}


@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventana_cuenta = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbo_tipo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbo_banco = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_cuenta = new javax.swing.JFormattedTextField();
        lab_error_cuenta = new javax.swing.JLabel();
        ventana_telefono = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        cbo_tipo_t = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JFormattedTextField();
        btn_a_t = new javax.swing.JButton();
        btn_c_t = new javax.swing.JButton();
        lab_error_telefono = new javax.swing.JLabel();
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
        tabla_empleados = new javax.swing.JTable();
        cbx_Eliminados = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cuentas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_agg_cuenta = new javax.swing.JButton();
        btn_sus_cuenta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_telefonos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn_agg_telefono = new javax.swing.JButton();
        btn_sus_telefono = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lab_cargo = new javax.swing.JLabel();
        lab_horario = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lab_base = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lab_alim = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lab_otro = new javax.swing.JLabel();
        btn_contrato = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lab_fecha_in = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        ventana_cuenta.setTitle("Agregar Cuenta");
        ventana_cuenta.setAlwaysOnTop(true);
        ventana_cuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ventana_cuenta.setMinimumSize(new java.awt.Dimension(320, 210));
        ventana_cuenta.setPreferredSize(new java.awt.Dimension(320, 210));
        ventana_cuenta.setResizable(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tipo de Cuenta:");

        cbo_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo de Cuenta", "Ahorro", "Corriente" }));
        cbo_tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_tipoItemStateChanged(evt);
            }
        });
        cbo_tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_tipoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbo_tipoMouseReleased(evt);
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

        txt_cuenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lab_error_cuenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_banco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbo_tipo, 0, 203, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(txt_cuenta)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lab_error_cuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_error_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ventana_cuentaLayout = new javax.swing.GroupLayout(ventana_cuenta.getContentPane());
        ventana_cuenta.getContentPane().setLayout(ventana_cuentaLayout);
        ventana_cuentaLayout.setHorizontalGroup(
            ventana_cuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_cuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ventana_cuentaLayout.setVerticalGroup(
            ventana_cuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_cuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        ventana_cuenta.getAccessibleContext().setAccessibleParent(this);

        ventana_telefono.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ventana_telefono.setTitle("Agregar Telefono");
        ventana_telefono.setAlwaysOnTop(true);
        ventana_telefono.setMinimumSize(new java.awt.Dimension(295, 165));

        jLabel4.setText("Tipo:");

        cbo_tipo_t.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo de Teléfono", "Movil", "Local", "Emergencia" }));

        jLabel5.setText("Número:");

        try {
            txt_telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(####)-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_telefono.setToolTipText("Ejem: (0212)1113322");

        btn_a_t.setText("Aceptar");
        btn_a_t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_a_tActionPerformed(evt);
            }
        });

        btn_c_t.setText("Cancelar");
        btn_c_t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_tActionPerformed(evt);
            }
        });

        lab_error_telefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout ventana_telefonoLayout = new javax.swing.GroupLayout(ventana_telefono.getContentPane());
        ventana_telefono.getContentPane().setLayout(ventana_telefonoLayout);
        ventana_telefonoLayout.setHorizontalGroup(
            ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_telefonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventana_telefonoLayout.createSequentialGroup()
                        .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_tipo_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lab_error_telefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(ventana_telefonoLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btn_a_t, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_c_t)
                .addGap(0, 76, Short.MAX_VALUE))
        );
        ventana_telefonoLayout.setVerticalGroup(
            ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_telefonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbo_tipo_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_error_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventana_telefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_a_t)
                    .addComponent(btn_c_t))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(1263, 370));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setToolTipText("Gestionar Empleados");
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
        btn_nuevo.setToolTipText("Nuevo Empleado");
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
        btn_modificar.setToolTipText("Modificar Empleado Seleccionado");
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
        btn_eliminar.setToolTipText("Inhabilitar Empleado Seleccionado");
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
        btn_reactivar.setToolTipText("Reactivar Empleado Seleccionado");
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

        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_empleados.getTableHeader().setResizingAllowed(false);
        tabla_empleados.getTableHeader().setReorderingAllowed(false);
        tabla_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empleadosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_empleadosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_empleados);

        cbx_Eliminados.setText("Empleados Inactivos");
        cbx_Eliminados.setToolTipText("Mostrar empleados con Estatus \"Inactivo\"");
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

        tabla_cuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Banco", "Tipo de Cuenta", "N° de Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_cuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_cuentasMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_cuentas);
        if (tabla_cuentas.getColumnModel().getColumnCount() > 0) {
            tabla_cuentas.getColumnModel().getColumn(0).setMinWidth(30);
            tabla_cuentas.getColumnModel().getColumn(0).setMaxWidth(30);
            tabla_cuentas.getColumnModel().getColumn(1).setMinWidth(150);
            tabla_cuentas.getColumnModel().getColumn(1).setMaxWidth(150);
            tabla_cuentas.getColumnModel().getColumn(2).setMinWidth(100);
            tabla_cuentas.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        btn_agg_cuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_agg_cuenta.setToolTipText("Agregar Cuenta");
        btn_agg_cuenta.setBorderPainted(false);
        btn_agg_cuenta.setContentAreaFilled(false);
        btn_agg_cuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg_cuenta.setEnabled(false);
        btn_agg_cuenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_agg_cuenta.setIconTextGap(5);
        btn_agg_cuenta.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_agg_cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agg_cuentaActionPerformed(evt);
            }
        });

        btn_sus_cuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus.png"))); // NOI18N
        btn_sus_cuenta.setToolTipText("Eliminar Cuenta");
        btn_sus_cuenta.setBorderPainted(false);
        btn_sus_cuenta.setContentAreaFilled(false);
        btn_sus_cuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sus_cuenta.setEnabled(false);
        btn_sus_cuenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_sus_cuenta.setIconTextGap(12);
        btn_sus_cuenta.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_sus_cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sus_cuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_agg_cuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_sus_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_agg_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_sus_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla_telefonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_telefonos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_telefonosMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_telefonos);
        if (tabla_telefonos.getColumnModel().getColumnCount() > 0) {
            tabla_telefonos.getColumnModel().getColumn(0).setMinWidth(30);
            tabla_telefonos.getColumnModel().getColumn(0).setMaxWidth(30);
            tabla_telefonos.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_telefonos.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_telefonos.getColumnModel().getColumn(2).setMinWidth(150);
            tabla_telefonos.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        btn_agg_telefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_agg_telefono.setToolTipText("Agregar Telefono");
        btn_agg_telefono.setBorderPainted(false);
        btn_agg_telefono.setContentAreaFilled(false);
        btn_agg_telefono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg_telefono.setEnabled(false);
        btn_agg_telefono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_agg_telefono.setIconTextGap(5);
        btn_agg_telefono.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_agg_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agg_telefonoActionPerformed(evt);
            }
        });

        btn_sus_telefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus.png"))); // NOI18N
        btn_sus_telefono.setToolTipText("Eliminar Telefono");
        btn_sus_telefono.setBorderPainted(false);
        btn_sus_telefono.setContentAreaFilled(false);
        btn_sus_telefono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sus_telefono.setEnabled(false);
        btn_sus_telefono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_sus_telefono.setIconTextGap(12);
        btn_sus_telefono.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_sus_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sus_telefonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_agg_telefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_sus_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_agg_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_sus_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Cuentas:");

        jLabel2.setText("Teléfonos:");

        jLabel7.setText("Cargo:");

        jLabel8.setText("Horario:");

        jLabel11.setText("Sueldo:");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Base:");

        jLabel17.setText("Alim.:");

        jLabel19.setText("Día:");

        btn_contrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_pedido1.png"))); // NOI18N
        btn_contrato.setText("Contrato");
        btn_contrato.setToolTipText("Abrir Contrato");
        btn_contrato.setBorderPainted(false);
        btn_contrato.setContentAreaFilled(false);
        btn_contrato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_contrato.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_contrato.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_pedido2.png"))); // NOI18N
        btn_contrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_contratoActionPerformed(evt);
            }
        });

        jLabel12.setText("Ingreso:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_base, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_alim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_otro))
                    .addComponent(jLabel11)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lab_cargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lab_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_fecha_in, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_contrato)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel7, jLabel8});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lab_alim, lab_base, lab_otro});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lab_fecha_in, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(lab_cargo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lab_horario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_contrato)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lab_base, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(lab_alim)
                    .addComponent(jLabel19)
                    .addComponent(lab_otro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel14, jLabel17, jLabel19, jLabel7, jLabel8, lab_alim, lab_base, lab_cargo, lab_fecha_in, lab_horario, lab_otro});

        jLabel3.setText("Más Opciones:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_mostrar_todo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_Eliminados))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        Nuevo_Empleado nuevo = new Nuevo_Empleado();
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

        Modificar_Empleado mod = new Modificar_Empleado();
        mod.setVisible(true);
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        deshabilitar();
        cargar();
        limpiarextra();
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_reactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reactivarActionPerformed
        reactivar();
        cargar();
        limpiarextra();
        desactivarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reactivarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        cargar();
        limpiarextra();
        desactivarBotones();
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
        cargar();
        limpiarextra();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_empleadosMouseClicked

    private void tabla_empleadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadosMouseReleased
        validarBotones();
        cargarextra();// TODO add your handling code here:
    }//GEN-LAST:event_tabla_empleadosMouseReleased

    private void cbx_EliminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbx_EliminadosMouseClicked
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosMouseClicked

    private void cbx_EliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_EliminadosActionPerformed
        cargar();  
        limpiarextra();// TODO add your handling code here:
    }//GEN-LAST:event_cbx_EliminadosActionPerformed

    private void jPanel1formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formKeyReleased

    private void btn_agg_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agg_cuentaActionPerformed
        this.cbo_banco.setSelectedItem("Seleccione Banco.");
        this.cbo_tipo.setSelectedItem("Seleccione Tipo de Cuenta");
        this.txt_cuenta.setText("");
        this.lab_error_cuenta.setText("Todos los Campos son Requeridos");
        ventana_cuenta.setLocationRelativeTo(this);
        ventana_cuenta.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_agg_cuentaActionPerformed

    private void btn_sus_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sus_cuentaActionPerformed
        removerCuenta();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sus_cuentaActionPerformed

    private void cbo_tipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_tipoItemStateChanged
            // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipoItemStateChanged

    private void cbo_tipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipoMouseClicked

    private void cbo_tipoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_tipoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tipoMouseReleased

    private void cbo_bancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_bancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_bancoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tp = this.cbo_tipo.getSelectedItem().toString();
        String ba = this.cbo_banco.getSelectedItem().toString();
        String cu = this.txt_cuenta.getText();
        

        if ((tp.equals("Seleccione Tipo de Cuenta")  || ba.equals("Seleccione Banco."))|| cu.isEmpty())
        {
           this.lab_error_cuenta.setText("Error... No puede dejar Campos Vacios");
        }else
        {
            GuardarCuenta();
            ventana_cuenta.dispose();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ventana_cuenta.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_cuentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_cuentasMouseReleased
validarRemoverCuentas();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_cuentasMouseReleased

    private void tabla_telefonosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_telefonosMouseReleased
validarRemoverTelefonos();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_telefonosMouseReleased

    private void btn_agg_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agg_telefonoActionPerformed
this.cbo_tipo_t.setSelectedItem("Seleccione Tipo de Teléfono");
this.txt_telefono.setValue(null);
this.lab_error_telefono.setText("Todos los Campos son Requeridos");
ventana_telefono.setLocationRelativeTo(this);
ventana_telefono.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_agg_telefonoActionPerformed

    private void btn_sus_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sus_telefonoActionPerformed
removerTelefono();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sus_telefonoActionPerformed

    private void btn_a_tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_a_tActionPerformed
    String tipo = this.cbo_tipo_t.getSelectedItem().toString();
    String num= this.txt_telefono.getText();

    if (tipo.equals("Seleccione Tipo de Teléfono")  ||  num.isEmpty())
        {
            this.lab_error_telefono.setText("Error... No puede dejar Campos Vacios");
        }else
        {
            GuardarTelefono();
            ventana_telefono.dispose();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_a_tActionPerformed

    private void btn_c_tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_tActionPerformed
ventana_telefono.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_c_tActionPerformed

    private void btn_contratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_contratoActionPerformed
Contrato_Empleado ce = new Contrato_Empleado();
ce.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_contratoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_a_t;
    public static javax.swing.JButton btn_agg_cuenta;
    public static javax.swing.JButton btn_agg_telefono;
    private javax.swing.JButton btn_c_t;
    private javax.swing.JButton btn_cerrar;
    public static javax.swing.JButton btn_contrato;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_mostrar_todo;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reactivar;
    private javax.swing.JButton btn_sus_cuenta;
    private javax.swing.JButton btn_sus_telefono;
    private javax.swing.JComboBox cbo_banco;
    private javax.swing.JComboBox cbo_tipo;
    private javax.swing.JComboBox cbo_tipo_t;
    public static javax.swing.JCheckBox cbx_Eliminados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lab_alim;
    public static javax.swing.JLabel lab_base;
    public static javax.swing.JLabel lab_cargo;
    private javax.swing.JLabel lab_error_cuenta;
    private javax.swing.JLabel lab_error_telefono;
    public static javax.swing.JLabel lab_fecha_in;
    public static javax.swing.JLabel lab_horario;
    public static javax.swing.JLabel lab_otro;
    public static javax.swing.JTable tabla_cuentas;
    public static javax.swing.JTable tabla_empleados;
    public static javax.swing.JTable tabla_telefonos;
    public static javax.swing.JTextField txt_buscar;
    private javax.swing.JFormattedTextField txt_cuenta;
    private javax.swing.JFormattedTextField txt_telefono;
    private javax.swing.JDialog ventana_cuenta;
    private javax.swing.JDialog ventana_telefono;
    // End of variables declaration//GEN-END:variables
}
