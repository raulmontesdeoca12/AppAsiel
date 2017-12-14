
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
public class Contrato_Empleado extends javax.swing.JFrame {
static DefaultTableModel m;
DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
Shape shape = null;
    public Contrato_Empleado() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        
        inhabilitar();
        llenarDatos();
        llenarTabla();

    
    }


 public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
 
 public void llenarDatos()
 {
    
        String cedula =(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 0);
        String nombre=(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 1);
        String apellido =(String)Empleados.tabla_empleados.getValueAt(Empleados.tabla_empleados.getSelectedRow(), 2);
        String nom = nombre+" "+apellido;
        
            this.lab_cedula.setText(cedula);
            this.lab_na.setText(nom);
    
 }
 
 public  static void validarBotones()
 {
      int fsel = tabla_contratos.getSelectedRow();
        if (fsel!=-1)
        {
            Contrato_Empleado.btn_imp_con.setEnabled(false);
            Contrato_Empleado.btn_eliminar.setEnabled(false);
            Contrato_Empleado.btn_modificar.setEnabled(false);
        }
        {
            Contrato_Empleado.btn_imp_con.setEnabled(true);
            Contrato_Empleado.btn_eliminar.setEnabled(true);
            Contrato_Empleado.btn_modificar.setEnabled(true);
        }
 }
 public static void llenarDatos2()
 {
     int fsel = tabla_contratos.getSelectedRow();
        if (fsel!=-1)
        {
    try {
        String id = tabla_contratos.getValueAt(fsel, 0).toString();;
   
        String cont ="",cons="",conc="",cond="";
        boolean csueldo,ccesta,ccomi,cdesta;
        String ncont,condi,carg,fi,fe,ct,sueldo,destajo,ho;
        double sf,su;
        double sal;
        
        
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery("SELECT * FROM contratos WHERE idcontratos ='"+id+"'");
        Statement st2= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs2 = st2.executeQuery("SELECT cesta_ticket FROM datos_extras");
        if(rs.first())
        {
            ncont = rs.getString("idcontratos");
            csueldo= rs.getBoolean("con_sueldo");
            ccesta=rs.getBoolean("con_ticket");
            ccomi=rs.getBoolean("con_comision");
            cdesta=rs.getBoolean("con_destajo");
            if (csueldo)
            {
                cons="Sueldo Básico|";
                sf=rs.getDouble("sueldo_final");
                su=sf*30;
                sal=Math.rint(su*100)/100;
                sueldo=""+sal;
            }else
            {
                sueldo="No Aplica";
            }
            if(ccesta)
            {
                double ctd = 0;
                cont="Cesta Ticket| ";
                if(rs2.first())
                {
                    ctd=rs2.getDouble("cesta_ticket");
                }
                
                double calc=ctd*30;
                double red=Math.rint(calc*100)/100;
                ct=""+red; 
            }else
            {
                ct="No Aplica";
            }
            if(ccomi)
            {
                conc="Comision| ";
            }
            if(cdesta)
            {
                cond="Destajo|";
                destajo=rs.getString("sueldo_final");
            }else
            {
                destajo="No Aplica";
            }
            condi="|"+cons+cont+conc+cond;
            carg=rs.getString("fk_cargo");
            fi=rs.getString("fecha_ingreso");
            fe=rs.getString("fecha_egreso");
            ho=rs.getString("fk_horario");
            
            Contrato_Empleado.lab_ncon.setText(ncont);
            Contrato_Empleado.lab_condi.setText(condi);
            Contrato_Empleado.lab_cargo.setText(carg);
            Contrato_Empleado.lab_fin.setText(fi);
            Contrato_Empleado.lab_feg.setText(fe);
            Contrato_Empleado.lab_sbase.setText(sueldo);
            Contrato_Empleado.lab_alim.setText(ct);
            Contrato_Empleado.lab_dest.setText(destajo);
            Contrato_Empleado.lab_hor.setText(ho);
        }
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Contrato_Empleado.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Contrato_Empleado.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 }
 
 
 
 public static void llenarTabla()
 {
     limpiar();
     
        String emp = Contrato_Empleado.lab_cedula.getText();
        try{
           
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        sql = "SELECT * FROM contratos WHERE  fk_empleado='"+emp+"'ORDER BY idcontratos ";
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){
            Double si = rs.getDouble("sueldo_inicial");
            Double sa = rs.getDouble("sueldo_final");
            Double cc2=Math.rint((sa*30)*100)/100;
            Double cc=Math.rint((si*30)*100)/100;
            String num = rs.getString("idcontratos");
            String car = rs.getString("fk_cargo");
            String fi = rs.getString("fecha_ingreso");
            String fe =rs.getString("fecha_egreso");
            String sii =""+cc;
            String si2 =""+cc2;
            m=(DefaultTableModel)tabla_contratos.getModel();
            
            String filaelemento[] = {num,car,fi,fe,sii,si2};
            m.addRow(filaelemento);
           
        }
      
    }catch(SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    
 }
 
 
 
 public static void limpiar()
 {
     
     for (int i = 0; i < tabla_contratos.getRowCount(); i++) {
           m.removeRow(i);
           i-=1;
       }
       
        Contrato_Empleado.lab_ncon.setText("");
        Contrato_Empleado.lab_condi.setText("");
        Contrato_Empleado.lab_cargo.setText("");
        Contrato_Empleado.lab_fin.setText("");
        Contrato_Empleado.lab_feg.setText("");
        Contrato_Empleado.lab_sbase.setText("");
        Contrato_Empleado.lab_alim.setText("");
        Contrato_Empleado.lab_dest.setText("");
        Contrato_Empleado.lab_hor.setText("");
 }
 
  void accionbntnuevo(){
        int rc = tabla_contratos.getRowCount();
        if (rc>=1)
        {
        String ffinal = tabla_contratos.getValueAt(rc, 5).toString();
        if(ffinal.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Error...\nDebe Finalizar El Contrato Actual", "Error",1,null);
        }else
        {
            Nuevo_Contrato nc = new Nuevo_Contrato();
            nc.setVisible(true);
            btn_nuevo.setEnabled(false); 
        }
      
       
        }else
        {
            Nuevo_Contrato nc = new Nuevo_Contrato();
            nc.setVisible(true);
            btn_nuevo.setEnabled(false); 
        }
       

  }
  
   void inhabilitar(){
        btn_imp_con.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_modificar.setEnabled(false);
        limpiar();
    }
   
   public void eliminar()
   {
    try {
        int fsel = tabla_contratos.getSelectedRow();
        String id = tabla_contratos.getValueAt(fsel, 0).toString();
        ClaseEAEmpleado contrato = new ClaseEAEmpleado(id);
        Gest_contrato in = new Gest_contrato();
        boolean p;
        p=in.Eliminar(contrato);
        if (p=true)
        {
            JOptionPane.showMessageDialog(this,"Contrato Eliminado Correctamente","Eliminado",1,null);
            llenarTabla();
            
        }else
        {
            System.out.println("Error en cuenta");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Contrato_Empleado.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Contrato_Empleado.class.getName()).log(Level.SEVERE, null, ex);
    }
       
   }
 
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_nuevo = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_contratos = new javax.swing.JTable();
        lab_cedula = new javax.swing.JLabel();
        lab_na = new javax.swing.JLabel();
        lab_ncon = new javax.swing.JLabel();
        lab_condi = new javax.swing.JLabel();
        lab_cargo = new javax.swing.JLabel();
        lab_fin = new javax.swing.JLabel();
        lab_feg = new javax.swing.JLabel();
        lab_sbase = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lab_alim = new javax.swing.JLabel();
        lab_dest = new javax.swing.JLabel();
        lab_hor = new javax.swing.JLabel();
        btn_imp_con = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ins. Producto");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(550, 370));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Contratos Empleado"));
        panel.setMinimumSize(new java.awt.Dimension(453, 450));
        panel.setOpaque(false);
        panel.setPreferredSize(new java.awt.Dimension(453, 450));

        jLabel1.setText("C.I.:");

        jLabel2.setText("Nombre y Apellido:");

        jLabel4.setText("Cargo:");

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo_nuevo1.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setToolTipText("Habilitar Campos");
        btn_nuevo.setBorder(null);
        btn_nuevo.setBorderPainted(false);
        btn_nuevo.setContentAreaFilled(false);
        btn_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nuevo.setIconTextGap(-9);
        btn_nuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo_nuevo2.png"))); // NOI18N
        btn_nuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo3.png"))); // NOI18N
        btn_nuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(255, 255, 255));
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_modificar1.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setToolTipText("Restablecer Campos");
        btn_modificar.setBorder(null);
        btn_modificar.setBorderPainted(false);
        btn_modificar.setContentAreaFilled(false);
        btn_modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_modificar.setEnabled(false);
        btn_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_modificar.setIconTextGap(-3);
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
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setToolTipText("Guardar Nuevo Producto");
        btn_eliminar.setBorder(null);
        btn_eliminar.setBorderPainted(false);
        btn_eliminar.setContentAreaFilled(false);
        btn_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminar.setEnabled(false);
        btn_eliminar.setHideActionText(true);
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

        jLabel7.setText("N° Contrato:");

        jLabel12.setText("Sueldo:");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Base:");

        jLabel17.setText("Alimentación:");

        jLabel3.setText("F/Ingreso:");

        jLabel5.setText("F/Egreso:");

        jLabel6.setText("Condiciones:");

        jLabel10.setText("Contratos:");

        tabla_contratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° ", "Cargo", "F.Ingreso", "F.Egreso", "S.inicial", "S.Actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_contratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_contratosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_contratos);
        if (tabla_contratos.getColumnModel().getColumnCount() > 0) {
            tabla_contratos.getColumnModel().getColumn(0).setMinWidth(30);
            tabla_contratos.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla_contratos.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_contratos.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_contratos.getColumnModel().getColumn(4).setMinWidth(70);
            tabla_contratos.getColumnModel().getColumn(4).setMaxWidth(70);
            tabla_contratos.getColumnModel().getColumn(5).setMinWidth(70);
            tabla_contratos.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        jLabel18.setText("Destajo:");

        btn_imp_con.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_imprimir.png"))); // NOI18N
        btn_imp_con.setText("Imprimir");
        btn_imp_con.setBorderPainted(false);
        btn_imp_con.setContentAreaFilled(false);
        btn_imp_con.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_imp_con.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_imp_con.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_imp_con.setIconTextGap(2);
        btn_imp_con.setMaximumSize(new java.awt.Dimension(22, 22));
        btn_imp_con.setMinimumSize(new java.awt.Dimension(22, 22));
        btn_imp_con.setPreferredSize(new java.awt.Dimension(22, 22));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salir))
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lab_sbase, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lab_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_na, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_feg, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_imp_con, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lab_hor, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_ncon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_condi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(75, 75, 75)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lab_alim, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lab_dest, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lab_feg, lab_fin});

        panelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lab_alim, lab_dest, lab_sbase});

        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(lab_na, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lab_ncon, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(lab_condi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lab_cargo)
                        .addComponent(lab_fin)
                        .addComponent(lab_hor))
                    .addComponent(lab_feg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lab_sbase))
                            .addComponent(lab_alim)
                            .addComponent(lab_dest))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_modificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btn_imp_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lab_alim, lab_cargo, lab_cedula, lab_condi, lab_dest, lab_feg, lab_fin, lab_hor, lab_na, lab_ncon, lab_sbase});

        getContentPane().add(panel);
        panel.setBounds(10, 10, 530, 350);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        jLabel9.setMinimumSize(new java.awt.Dimension(500, 380));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(-10, 0, 670, 370);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        btn_eliminar.setEnabled(false);
        btn_modificar.setEnabled(false);
        int row=tabla_contratos.getRowCount();
        if(row==0)
        {
            Nuevo_Contrato nc = new Nuevo_Contrato();
            nc.setVisible(true);
        }else
        {
            int fsel = tabla_contratos.getSelectedRow();
            String fe = tabla_contratos.getValueAt(row-1, 3).toString();
            if(fe.equals("No Aplica"))
            {
                JOptionPane.showMessageDialog(this,"Debe Finalizar un Contrato Antes de Crear Otro","Atención",1,null);
            }else
            {
                    Nuevo_Contrato nc = new Nuevo_Contrato();
                    nc.setVisible(true);
            }
        }
        
        

    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       Empleados.cargar();
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
eliminar();
btn_eliminar.setEnabled(false);
btn_modificar.setEnabled(false);
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
   Modificar_Contrato mc = new Modificar_Contrato();
   mc.setVisible(true);
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void tabla_contratosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_contratosMouseReleased
llenarDatos2(); 
validarBotones();// TODO add your handling code here:
    }//GEN-LAST:event_tabla_contratosMouseReleased

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
            java.util.logging.Logger.getLogger(Contrato_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contrato_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contrato_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contrato_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contrato_Empleado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_eliminar;
    public static javax.swing.JButton btn_imp_con;
    public static javax.swing.JButton btn_modificar;
    public javax.swing.JButton btn_nuevo;
    public javax.swing.JButton btn_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lab_alim;
    public static javax.swing.JLabel lab_cargo;
    public static javax.swing.JLabel lab_cedula;
    public static javax.swing.JLabel lab_condi;
    public static javax.swing.JLabel lab_dest;
    public static javax.swing.JLabel lab_feg;
    public static javax.swing.JLabel lab_fin;
    public static javax.swing.JLabel lab_hor;
    public static javax.swing.JLabel lab_na;
    public static javax.swing.JLabel lab_ncon;
    public static javax.swing.JLabel lab_sbase;
    public javax.swing.JPanel panel;
    public static javax.swing.JTable tabla_contratos;
    // End of variables declaration//GEN-END:variables
}
