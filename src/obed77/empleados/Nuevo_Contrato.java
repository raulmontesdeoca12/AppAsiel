
package obed77.empleados;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Empleados.ClaseContrato;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
public class Nuevo_Contrato extends javax.swing.JFrame {
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
Shape shape = null;
    public Nuevo_Contrato() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        Inicio();
        

    
    }


 public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 public void Inicio(){
     int id = 0,idn;
     String ids;
     try {
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
            String f=date.format(now);
            this.jdc_fecha.setMaxSelectableDate(date.parse(f));
        } catch (ParseException ex) {
            Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT idcontratos FROM contratos");
        if(rs.last())
        {
            id=rs.getInt("idcontratos");
            idn=id+1;
            ids=Integer.toString(idn);
        }else
        {
            ids="0";
        }
        this.lab_id_con.setText(ids);
        llenarHorario();
        llenarCargos();
        limpiar();
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 
 public void limpiar()
 {
       
        this.jdc_fecha.setDate(null);
        txt_sueldo.setValue(null);
        txt_alim.setValue(null);
        txt_sueldo_dia.setValue(null);
        txt_alim_dia.setValue(null);
        cbo_horario.setSelectedItem("Seleccione Horario");
        cbo_cargo.setSelectedItem("Seleccione Cargo");
        this.cbx_base.setSelected(false);
        this.cbx_alim.setSelected(false);
        this.cbx_com.setSelected(false);
        this.cbx_des.setSelected(false);
        txt_sueldo.setEnabled(false);
        txt_alim.setEnabled(false);
        txt_sueldo_dia.setEnabled(false);
        txt_alim_dia.setEnabled(false);
          
 }
 public void llenarMinNormal()
 {
      float sm=0,cs;
     double s,sd;
    try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT * FROM datos_extras");
        if(rs.first())
        {
            sm=rs.getFloat("sueldo_min");
        }
        cs=sm*30;
        s=Math.rint(cs*100)/100;
        sd=Math.rint(sm*10000)/10000;
        this.txt_sueldo.setValue(s);
        this.txt_sueldo_dia.setValue(sd);
        this.txt_sueldo.setEnabled(true);
        this.txt_sueldo_dia.setEnabled(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 public void llenarCesta()
 {
     
     float ct=0,cc;
     double c,cd;
    try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT * FROM datos_extras");
        if(rs.first())
        {
            ct=rs.getFloat("cesta_ticket");
        }
        cc=ct*30;
        c=Math.rint(cc*100)/100;
        cd=Math.rint(ct*100)/100;
        this.txt_alim.setValue(c);
        this.txt_alim_dia.setValue(cd);
        this.txt_alim.setEnabled(true);
        this.txt_alim_dia.setEnabled(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 
 public void llenarMinDia()
 {
     float sm=0;
     double sd;
    try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT * FROM datos_extras");
        if(rs.first())
        {
            sm=rs.getFloat("sueldo_min");
        }
        sd=Math.rint(sm*10000)/10000;
        this.txt_sueldo.setValue(null);
        this.txt_sueldo_dia.setValue(sd);
        this.txt_sueldo_dia.setEnabled(true);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
  
 public void calcular(){
    try {
        double sd,calc,ss;
        String c;
        c=this.txt_sueldo.getText();
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        DecimalFormat formateador = new DecimalFormat("########.##",simbolos);
        
        if(c.isEmpty())
        {
            txt_sueldo_dia.setValue(null);
        }else
     {
         Number numero = formateador.parse(c);
         sd=numero.doubleValue();
         calc=sd/30;
         ss=Math.rint(calc*10000)/10000;
         this.txt_sueldo_dia.setValue(ss);
         System.out.println("calc: "+ss);
     }
     
            } catch (ParseException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 
 public  void llenarHorario(){
        cbo_horario.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT horarios FROM horarios");
            modeloCombo.addElement("Seleccione Horario");
            cbo_horario.setModel(modeloCombo);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("horarios"));
                cbo_horario.setModel(modeloCombo);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
  public  void llenarCargos(){
        cbo_cargo.removeAllItems();
        
        try {
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet  rs = st.executeQuery("SELECT cargos FROM cargos");
            modeloCombo2.addElement("Seleccione Cargo");
            cbo_cargo.setModel(modeloCombo2);
            while (rs.next()) {
                modeloCombo2.addElement(rs.getObject("cargos"));
                cbo_cargo.setModel(modeloCombo2);
            }
 
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
void guardar(){
    try {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        DecimalFormat formateador = new DecimalFormat("########.####",simbolos);
        String id=this.lab_id_con.getText();
        String formato ="yyyy-MM-dd";
        Date fec=this.jdc_fecha.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        String fecha= sdf.format(fec);
        System.out.println("Fecha: "+fecha);
        String cargo=this.cbo_cargo.getSelectedItem().toString();
        int base,alim,com,des;
        base=cbx_base.isSelected()?1:0;
        alim=cbx_alim.isSelected()?1:0;
        com=cbx_com.isSelected()?1:0;
        des=cbx_des.isSelected()?1:0;
        String c=this.txt_sueldo_dia.getText();
        Number numero = formateador.parse(c);
        Double sueldo_inicial=numero.doubleValue();
        Double sueldo_final=sueldo_inicial;
        String horario=this.cbo_horario.getSelectedItem().toString();
        String empleado=Contrato_Empleado.lab_cedula.getText();

        ClaseContrato contrato = new ClaseContrato(id, fecha, sueldo_inicial, sueldo_final, horario,empleado,cargo,base,alim,com,des);
        Gest_contrato in = new Gest_contrato();
        boolean r;
        r=in.Insertar(contrato);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Registro Exitoso...", "Guardado",1,null);
        }
    } catch (ParseException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Nuevo_Contrato.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lab_id_con = new javax.swing.JLabel();
        jdc_fecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbo_horario = new javax.swing.JComboBox();
        cbo_cargo = new javax.swing.JComboBox();
        txt_sueldo = new javax.swing.JFormattedTextField();
        txt_alim = new javax.swing.JFormattedTextField();
        txt_sueldo_dia = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_alim_dia = new javax.swing.JFormattedTextField();
        cbx_base = new javax.swing.JCheckBox();
        cbx_alim = new javax.swing.JCheckBox();
        cbx_com = new javax.swing.JCheckBox();
        cbx_des = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Contrato");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(570, 275));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(570, 275));
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Contrato"));
        panel.setMinimumSize(new java.awt.Dimension(550, 250));
        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(550, 250));
        panel.setVerifyInputWhenFocusTarget(false);

        jLabel1.setText("ID Contrato:");

        jLabel2.setText("Ingreso:");

        jLabel3.setText("Cargo:");

        jLabel4.setText("Sueldo/Mes:");

        btn_limpiar.setBackground(new java.awt.Color(255, 255, 255));
        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar1.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.setToolTipText("Reestablecer Campos");
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
        btn_guardar.setToolTipText("Guardar Nuevo Contrato");
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

        jLabel8.setText("Horario:");

        lab_id_con.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jdc_fecha.setDateFormatString("yyyy/MM/dd");
        jdc_fecha.setMinimumSize(new java.awt.Dimension(100, 20));
        jdc_fecha.setOpaque(false);
        jdc_fecha.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel7.setText("Alimentación:");

        jLabel12.setText("Sueldo/Día:");

        cbo_horario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbo_cargo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txt_sueldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_sueldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_sueldoKeyReleased(evt);
            }
        });

        txt_alim.setEditable(false);
        txt_alim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txt_sueldo_dia.setEditable(false);
        txt_sueldo_dia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0000"))));

        jLabel10.setText("Alim/Día:");

        txt_alim_dia.setEditable(false);
        txt_alim_dia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        cbx_base.setText("Sueldo Base");
        cbx_base.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbx_base.setOpaque(false);
        cbx_base.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_baseItemStateChanged(evt);
            }
        });

        cbx_alim.setText("Alimentación");
        cbx_alim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbx_alim.setOpaque(false);
        cbx_alim.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_alimItemStateChanged(evt);
            }
        });

        cbx_com.setText("Comisiones");
        cbx_com.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbx_com.setOpaque(false);
        cbx_com.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_comItemStateChanged(evt);
            }
        });

        cbx_des.setText("Destajo");
        cbx_des.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbx_des.setOpaque(false);
        cbx_des.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_desItemStateChanged(evt);
            }
        });

        jLabel5.setText("Condiciones:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_id_con, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(cbo_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txt_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_sueldo_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_alim, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_alim_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_horario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btn_limpiar)
                        .addGap(6, 6, 6)
                        .addComponent(btn_guardar)
                        .addGap(6, 6, 6)
                        .addComponent(btn_salir))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel10)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel8))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_base, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_alim)
                                .addGap(4, 4, 4)
                                .addComponent(cbx_com, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_des, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbx_alim, cbx_base, cbx_com});

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_id_con, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbx_base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbx_alim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbx_com, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbx_des, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addGap(6, 6, 6)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sueldo_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_alim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbo_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_alim_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_guardar)
                    .addComponent(btn_salir)))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbx_alim, cbx_base, cbx_com});

        panelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbo_cargo, jLabel1, jLabel2, jLabel3, lab_id_con});

        getContentPane().add(panel);
        panel.setBounds(10, 10, 550, 260);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        jLabel9.setMinimumSize(new java.awt.Dimension(500, 380));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(-50, 0, 690, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
limpiar();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
      Date fecha = jdc_fecha.getDate();
      String cargo=cbo_cargo.getSelectedItem().toString();
      String horario=cbo_horario.getSelectedItem().toString();
      String sueld=txt_sueldo.getText();
      
      if(fecha==null || cargo.equals("Seleccione Cargo") || (!cbx_base.isSelected()&&!cbx_des.isSelected()) || (cbx_base.isSelected()&&sueld.isEmpty()) || horario.equals("Seleccione Horario"))
      {
          JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
      }else
      {
          guardar();
          Contrato_Empleado.llenarTabla();
          this.dispose();
          
      }
      

    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
      
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void cbx_desItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_desItemStateChanged
    if(this.cbx_des.isSelected())
     {
         cbx_base.setSelected(false);
         llenarMinDia();
         this.txt_sueldo_dia.setEditable(true);
         
         
     }else
     {
         this.txt_sueldo_dia.setValue(null);
         this.txt_sueldo_dia.setEnabled(false);
         this.txt_sueldo_dia.setEditable(false);
     }    // TODO add your handling code here:
    }//GEN-LAST:event_cbx_desItemStateChanged

    private void cbx_comItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_comItemStateChanged
       // TODO add your handling code here:
    }//GEN-LAST:event_cbx_comItemStateChanged

    private void cbx_alimItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_alimItemStateChanged
    if(this.cbx_alim.isSelected() && (cbx_base.isSelected()||cbx_des.isSelected()))
{
    cbx_alim.setSelected(true);
}else
{
    cbx_alim.setSelected(false);
}
        
        
 if(this.cbx_alim.isSelected())
     {
         llenarCesta();
         
     }else
     {
         if(!cbx_alim.isSelected() && cbx_base.isSelected())
         {
             cbx_base.setSelected(false);
         }
         this.txt_alim.setValue(null);
         this.txt_alim_dia.setValue(null);
         this.txt_alim.setEnabled(false);
         this.txt_alim_dia.setEnabled(false);
     }       // TODO add your handling code here:
    }//GEN-LAST:event_cbx_alimItemStateChanged

    private void cbx_baseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_baseItemStateChanged
 if(this.cbx_base.isSelected())
     {
         this.cbx_alim.setSelected(true);
         cbx_des.setSelected(false);
         llenarMinNormal();
         
     }else
     {
         this.txt_sueldo.setValue(null);
         this.txt_sueldo_dia.setValue(null);
         this.txt_sueldo.setEnabled(false);
         this.txt_sueldo_dia.setEnabled(false);
         this.cbx_alim.setSelected(false);
     }        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_baseItemStateChanged

    private void txt_sueldoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sueldoKeyReleased
calcular();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueldoKeyReleased

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
            java.util.logging.Logger.getLogger(Nuevo_Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_Contrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_Contrato().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_salir;
    public javax.swing.JComboBox cbo_cargo;
    public javax.swing.JComboBox cbo_horario;
    public javax.swing.JCheckBox cbx_alim;
    public javax.swing.JCheckBox cbx_base;
    public javax.swing.JCheckBox cbx_com;
    public javax.swing.JCheckBox cbx_des;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public com.toedter.calendar.JDateChooser jdc_fecha;
    public javax.swing.JLabel lab_id_con;
    public javax.swing.JPanel panel;
    public javax.swing.JFormattedTextField txt_alim;
    public javax.swing.JFormattedTextField txt_alim_dia;
    public javax.swing.JFormattedTextField txt_sueldo;
    public javax.swing.JFormattedTextField txt_sueldo_dia;
    // End of variables declaration//GEN-END:variables
}
