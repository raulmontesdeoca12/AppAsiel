
package obed77.empleados;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Empleados.ClaseEAEmpleado;
import BaseDeDatos.Empleados.Gest_contrato;
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
import javax.swing.table.DefaultTableModel;
public class Pago_Nomina extends javax.swing.JFrame {
static DefaultTableModel m;
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
static String destajo="";
Shape shape = null;
    public Pago_Nomina() {
        initComponents();
//        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
//        AWTUtilities.setWindowShape(this, shape);
//        llenarDatos2();
//        
       

    
    }


// public Image getIconImage() {
//        Image retValue = Toolkit.getDefaultToolkit().
//                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));
//
//
//        return retValue;
//    }
// 
// public void llenarDatos()
// {
//    
//        String cedula =(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 0);
//        String nombre=(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 1);
//        String apellido =(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 2);
//        String nom = nombre+" "+apellido;
//        
//            this.lab_cedula.setText(cedula);
//            this.lab_na.setText(nom);
//    
// }
// 
// 
// public static void llenarDatos2()
// {
//     
//    try {
//        String id = lab_cedula.getText();
//   
//        String cont ="",cons="",conc="",cond="";
//        boolean csueldo,ccesta,ccomi,cdesta;
//        String ncont,condi,carg,fi,fe,ct,sueldo,ho;
//        double sf,su;
//        double sal;
//        
//        
//        ConexionBD parametros = new ConexionBD();
//        Class.forName(parametros.getDriver());
//        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
//        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet  rs = st.executeQuery("SELECT * FROM contratos WHERE fk_empleado ='"+id+"' AND fecha_egreso ='No Aplica'");
//        Statement st2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet  rs2 = st2.executeQuery("SELECT cesta_ticket FROM datos_extras");
//        if(rs.first())
//        {
//            ncont = rs.getString("idcontratos");
//            csueldo= rs.getBoolean("con_sueldo");
//            ccesta=rs.getBoolean("con_ticket");
//            ccomi=rs.getBoolean("con_comision");
//            cdesta=rs.getBoolean("con_destajo");
//            if (csueldo)
//            {
//                cons="Sueldo Básico|";
//                sf=rs.getDouble("sueldo_final");
//                su=sf*30;
//                sal=Math.rint(su*100)/100;
//                sueldo=""+sal;
//            }else
//            {
//                sueldo="0";
//            }
//            if(ccesta)
//            {
//                double ctd = 0;
//                cont="Cesta Ticket| ";
//                if(rs2.first())
//                {
//                    ctd=rs2.getDouble("cesta_ticket");
//                }
//                
//                double calc=ctd*30;
//                double red=Math.rint(calc*100)/100;
//                ct=""+red; 
//            }else
//            {
//                ct="0";
//            }
//            
//            if(cdesta)
//            {
//                cond="Destajo|";
//                destajo=rs.getString("sueldo_final");
//                btn_destajo.setEnabled(true);
//            }else
//            {
//                destajo="0";
//            }
//                        
//            txt_sueldo.setText(sueldo);
//            txt_alimentacion.setText(ct);
//            
//       }
//    } catch (ClassNotFoundException ex) {
//        Logger.getLogger(Pago_Nomina.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (SQLException ex) {
//        Logger.getLogger(Pago_Nomina.class.getName()).log(Level.SEVERE, null, ex);
//    }
// }
// 
// 
// 
// 
// public static void LlenarDatos()
// {
//     
// }
//
//   void inhabilitar(){
//        btn_imp_con.setEnabled(false);
//        btn_eliminar.setEnabled(false);
//        btn_modificar.setEnabled(false);
//        limpiar();
//    }
//   
//   public void eliminar()
//   {
//    try {
//        int fsel = tabla_contratos.getSelectedRow();
//        String id = tabla_contratos.getValueAt(fsel, 0).toString();
//        ClaseEAEmpleado contrato = new ClaseEAEmpleado(id);
//        Gest_contrato in = new Gest_contrato();
//        boolean p;
//        p=in.Eliminar(contrato);
//        if (p=true)
//        {
//            JOptionPane.showMessageDialog(this,"Contrato Eliminado Correctamente","Eliminado",1,null);
//            llenarTabla();
//            
//        }else
//        {
//            System.out.println("Error en cuenta");
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(Pago_Nomina.class.getName()).log(Level.SEVERE, null, ex);
//    } catch (ClassNotFoundException ex) {
//        Logger.getLogger(Pago_Nomina.class.getName()).log(Level.SEVERE, null, ex);
//    }
//       
//   }
 
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_imprimir = new javax.swing.JButton();
        lab_cedula = new javax.swing.JLabel();
        lab_na = new javax.swing.JLabel();
        jdc_fecha = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_sueldo = new javax.swing.JTextField();
        txt_alimentacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_comision = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_destajo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_destajo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_sueldo5 = new javax.swing.JTextField();
        txt_sueldo7 = new javax.swing.JTextField();
        txt_sueldo9 = new javax.swing.JTextField();
        spin_d = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        spin_n = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        spin_f = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        txt_sueldo14 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_sueldo6 = new javax.swing.JTextField();
        spin_dn = new javax.swing.JSpinner();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        txt_sueldo11 = new javax.swing.JTextField();
        txt_sueldo12 = new javax.swing.JTextField();
        txt_sueldo13 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_sueldo15 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel19 = new javax.swing.JLabel();
        btn_salir1 = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        jLabel9.setMinimumSize(new java.awt.Dimension(500, 380));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pag. Nómina");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(465, 715));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Pago de Nómina"));
        panel.setMinimumSize(new java.awt.Dimension(453, 450));
        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(453, 450));

        jLabel1.setText("C.I.:");

        jLabel2.setText("Nombre y Apellido:");

        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imprimir.png"))); // NOI18N
        btn_imprimir.setText("Imprimir");
        btn_imprimir.setToolTipText("Cerrar Ventana");
        btn_imprimir.setBorder(null);
        btn_imprimir.setBorderPainted(false);
        btn_imprimir.setContentAreaFilled(false);
        btn_imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setIconTextGap(-6);
        btn_imprimir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imprimir.png"))); // NOI18N
        btn_imprimir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imprimir2.png"))); // NOI18N
        btn_imprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });

        lab_cedula.setText("V-21467258");

        jdc_fecha.setMaximumSize(new java.awt.Dimension(100, 20));
        jdc_fecha.setMinimumSize(new java.awt.Dimension(100, 20));
        jdc_fecha.setOpaque(false);
        jdc_fecha.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel11.setText("Fecha:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Base"));
        jPanel1.setOpaque(false);

        jLabel3.setText("Sueldo:");

        txt_sueldo.setEditable(false);
        txt_sueldo.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldoActionPerformed(evt);
            }
        });

        txt_alimentacion.setEditable(false);
        txt_alimentacion.setBackground(new java.awt.Color(255, 255, 255));
        txt_alimentacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_alimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alimentacionActionPerformed(evt);
            }
        });

        jLabel4.setText("Alimentación:");

        txt_comision.setEditable(false);
        txt_comision.setBackground(new java.awt.Color(255, 255, 255));
        txt_comision.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_comision.setText("0");
        txt_comision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_comisionActionPerformed(evt);
            }
        });

        jLabel5.setText("Comisión:");

        txt_destajo.setEditable(false);
        txt_destajo.setBackground(new java.awt.Color(255, 255, 255));
        txt_destajo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_destajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_destajoActionPerformed(evt);
            }
        });

        jLabel8.setText("Destajo:");

        btn_destajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_destajo.setToolTipText("Agregar Cuenta");
        btn_destajo.setBorderPainted(false);
        btn_destajo.setContentAreaFilled(false);
        btn_destajo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_destajo.setEnabled(false);
        btn_destajo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_destajo.setIconTextGap(5);
        btn_destajo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_destajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_destajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_comision, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_alimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_destajo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_destajo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_alimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_destajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_comision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_destajo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Extras"));
        jPanel2.setOpaque(false);

        jLabel10.setText("Horas:");

        txt_sueldo5.setEditable(false);
        txt_sueldo5.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo5ActionPerformed(evt);
            }
        });

        txt_sueldo7.setEditable(false);
        txt_sueldo7.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo7ActionPerformed(evt);
            }
        });

        txt_sueldo9.setEditable(false);
        txt_sueldo9.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo9ActionPerformed(evt);
            }
        });

        spin_d.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spin_d.setOpaque(false);

        jLabel15.setText("Horas:");

        spin_n.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spin_n.setOpaque(false);

        jLabel16.setText("Horas:");

        spin_f.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spin_f.setOpaque(false);

        jCheckBox1.setText("Diurno");
        jCheckBox1.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox1.setOpaque(false);

        jCheckBox2.setText("Feriado");
        jCheckBox2.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox2.setOpaque(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Nocturno");
        jCheckBox3.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox3.setOpaque(false);

        jLabel14.setText("Total Extra:");

        txt_sueldo14.setEditable(false);
        txt_sueldo14.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo14.setText("0");
        txt_sueldo14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spin_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_sueldo7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(txt_sueldo5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_sueldo9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txt_sueldo14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCheckBox1, jCheckBox2, jCheckBox3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel10)
                            .addComponent(spin_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sueldo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(spin_n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sueldo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jLabel16)
                            .addComponent(spin_f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sueldo9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sueldo14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox1, jCheckBox3});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel16});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Descuentos"));
        jPanel3.setOpaque(false);

        txt_sueldo6.setEditable(false);
        txt_sueldo6.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo6ActionPerformed(evt);
            }
        });

        spin_dn.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spin_dn.setOpaque(false);

        jCheckBox4.setText("Días No Laborados");
        jCheckBox4.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox4.setOpaque(false);

        jCheckBox5.setText("L.P.H.");
        jCheckBox5.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox5.setOpaque(false);
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox6.setText("Paro Forzoso");
        jCheckBox6.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox6.setOpaque(false);

        jCheckBox7.setText("I.V.S.S");
        jCheckBox7.setToolTipText("Hora Extra Turno Diurno");
        jCheckBox7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBox7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBox7.setOpaque(false);
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        txt_sueldo11.setEditable(false);
        txt_sueldo11.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo11ActionPerformed(evt);
            }
        });

        txt_sueldo12.setEditable(false);
        txt_sueldo12.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo12ActionPerformed(evt);
            }
        });

        txt_sueldo13.setEditable(false);
        txt_sueldo13.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo13ActionPerformed(evt);
            }
        });

        jLabel20.setText("Total Descuento:");

        txt_sueldo15.setEditable(false);
        txt_sueldo15.setBackground(new java.awt.Color(255, 255, 255));
        txt_sueldo15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sueldo15.setText("0");
        txt_sueldo15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueldo15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jCheckBox7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(spin_dn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sueldo6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_sueldo13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sueldo11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sueldo12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(txt_sueldo15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(spin_dn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sueldo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sueldo11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox5)
                            .addComponent(txt_sueldo12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sueldo15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(txt_sueldo13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCheckBox4, jCheckBox5, jCheckBox6, jCheckBox7});

        jTextPane1.setBorder(null);
        jScrollPane1.setViewportView(jTextPane1);

        jLabel19.setText("Observaciones:");

        btn_salir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_salir1.setText("Salir");
        btn_salir1.setToolTipText("Cerrar Ventana");
        btn_salir1.setBorder(null);
        btn_salir1.setBorderPainted(false);
        btn_salir1.setContentAreaFilled(false);
        btn_salir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salir1.setIconTextGap(-9);
        btn_salir1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir2.png"))); // NOI18N
        btn_salir1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir3.png"))); // NOI18N
        btn_salir1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btn_imprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir1))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lab_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_na, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lab_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(lab_na, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel19)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lab_cedula, lab_na});

        getContentPane().add(panel);
        panel.setBounds(10, 10, 450, 690);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar1200x900.png"))); // NOI18N
        jLabel13.setMinimumSize(new java.awt.Dimension(500, 380));
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 0, 470, 780);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
        Empleados.cargar();
        this.dispose();
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void txt_sueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldoActionPerformed

    private void txt_alimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alimentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alimentacionActionPerformed

    private void txt_comisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_comisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_comisionActionPerformed

    private void txt_destajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destajoActionPerformed

    private void txt_sueldo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo5ActionPerformed

    private void txt_sueldo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo7ActionPerformed

    private void txt_sueldo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo9ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void txt_sueldo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo6ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void txt_sueldo11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo11ActionPerformed

    private void txt_sueldo12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo12ActionPerformed

    private void txt_sueldo13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo13ActionPerformed

    private void txt_sueldo14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo14ActionPerformed

    private void txt_sueldo15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueldo15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldo15ActionPerformed

    private void btn_salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salir1ActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
//        llenarCampos();// TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_destajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_destajoActionPerformed
//        this.cbo_banco.setSelectedItem("Seleccione Banco.");
//        this.cbo_tipo.setSelectedItem("Seleccione Tipo de Cuenta");
//        this.txt_cuenta.setText("");
//        this.lab_error_cuenta.setText("Todos los Campos son Requeridos");
//        ventana_cuenta.setLocationRelativeTo(this);
//        ventana_cuenta.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_destajoActionPerformed

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
            java.util.logging.Logger.getLogger(Pago_Nomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pago_Nomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pago_Nomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pago_Nomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Pago_Nomina().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_destajo;
    public javax.swing.JButton btn_imprimir;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir1;
    public javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JCheckBox jCheckBox2;
    public javax.swing.JCheckBox jCheckBox3;
    public javax.swing.JCheckBox jCheckBox4;
    public javax.swing.JCheckBox jCheckBox5;
    public javax.swing.JCheckBox jCheckBox6;
    public javax.swing.JCheckBox jCheckBox7;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextPane jTextPane1;
    public static com.toedter.calendar.JDateChooser jdc_fecha;
    public static javax.swing.JLabel lab_cedula;
    public static javax.swing.JLabel lab_na;
    public javax.swing.JPanel panel;
    public static javax.swing.JSpinner spin_d;
    public static javax.swing.JSpinner spin_dn;
    public static javax.swing.JSpinner spin_f;
    public static javax.swing.JSpinner spin_n;
    public static javax.swing.JTextField txt_alimentacion;
    public static javax.swing.JTextField txt_comision;
    public static javax.swing.JTextField txt_destajo;
    public static javax.swing.JTextField txt_sueldo;
    public static javax.swing.JTextField txt_sueldo11;
    public static javax.swing.JTextField txt_sueldo12;
    public static javax.swing.JTextField txt_sueldo13;
    public static javax.swing.JTextField txt_sueldo14;
    public static javax.swing.JTextField txt_sueldo15;
    public static javax.swing.JTextField txt_sueldo5;
    public static javax.swing.JTextField txt_sueldo6;
    public static javax.swing.JTextField txt_sueldo7;
    public static javax.swing.JTextField txt_sueldo9;
    // End of variables declaration//GEN-END:variables
}
