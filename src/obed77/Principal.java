/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77;

import Propiedades.Ventana_Ajustes;
import BaseDeDatos.ConexionBD;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import obed77.Ajustes.BaseDeDatos;
import obed77.Ventas.Ventana_Cotizacion;
import obed77.Ventas.Ventana_Facturar;
import obed77.Ventas.Ventas_ml;
import obed77.clientes.Clientes;
import obed77.empleados.Empleados;
import obed77.productos.Productos;
import obed77.proveedores.Proveedores;

/**
 *
 * @author user
 */
public class Principal extends javax.swing.JFrame {

    Properties pp = new Properties();

    /**
     * Creates new form Principal
     */
    public Principal() {
      
           
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
 
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        initComponents();
        
    }
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
    void close(){
        int opc = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opc == 0) {
            System.exit(0);
        }
    }                
    public void CrearPanelProductos() {
        Productos panelproducto = new Productos();
        String titulo = "Gestionar Productos";
        int index = panel_principal.indexOfTab(titulo);



        if (index == -1) {
            panel_principal.addTab(titulo, panelproducto);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);


        } else {
            panel_principal.remove(index);
            panel_principal.addTab(titulo, panelproducto);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);
            
        }
    }
    public void ReporteInventario()
    {
        int dia,mes,anio;
          Calendar cal = Calendar.getInstance();
          anio=cal.get(Calendar.YEAR);
          int mesi=cal.get(Calendar.MONTH);
          int mest=mesi+1;
          mes=mest;
          dia=cal.get(Calendar.DATE);
        try {
            pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                String dir;
                dir=pp.getProperty("directorio");
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Productos/Inventario.jasper");
            JasperPrint jp = JasperFillManager.fillReport(Report, null, con);
            JasperViewer jviewer=new JasperViewer(jp,false);
            jviewer.setTitle("Reporte de Stock");
            jviewer.setVisible(true);
            JasperExportManager.exportReportToPdfFile( jp, dir+"/Obed77/PDF/Inventario/Inventario_"+dia+"_"+mes+"_"+anio+".pdf");
        } catch (ClassNotFoundException | SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void ReporteListadoPrecios(boolean opc)
    {
        int dia,mes,anio;
          Calendar cal = Calendar.getInstance();
          anio=cal.get(Calendar.YEAR);
          int mesi=cal.get(Calendar.MONTH);
          int mest=mesi+1;
          mes=mest;
          Map par = new HashMap();
          par.clear(); 
          par.put("tot", opc);
          dia=cal.get(Calendar.DATE);
        try {
            pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                String dir;
                dir=pp.getProperty("directorio");
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Productos/ListadoPrecios.jasper");
            
            JasperPrint jp = JasperFillManager.fillReport(Report, par, con);
            JasperViewer jviewer=new JasperViewer(jp,false);
            jviewer.setTitle("Reporte de Stock");
            jviewer.setVisible(true);
            JasperExportManager.exportReportToPdfFile( jp, dir+"/Obed77/PDF/Inventario/ListadoPrecios_"+dia+"_"+mes+"_"+anio+".pdf");
        } catch (ClassNotFoundException | SQLException | JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CrearPanelCliente() {
        Clientes panelcliente = new Clientes();
        String titulo = "Gestionar Clientes";
        int index = panel_principal.indexOfTab(titulo);



        if (index == -1) {
            panel_principal.addTab(titulo, panelcliente);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);


        } else {
            panel_principal.remove(index);
            panel_principal.addTab(titulo, panelcliente);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);
        }
    }
    public void CrearPanelProveedor() {
        Proveedores panelproveedores = new Proveedores();
        String titulo = "Gestionar Proveedores";
        int index = panel_principal.indexOfTab(titulo);



        if (index == -1) {
            panel_principal.addTab(titulo, panelproveedores);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);


        } else {
            panel_principal.remove(index);
            panel_principal.addTab(titulo, panelproveedores);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);
        }
    }
     public void CrearPanelMl() {
        Ventas_ml panelml = new Ventas_ml();
        String titulo = "Ventas ML";
        int index = panel_principal.indexOfTab(titulo);



        if (index == -1) {
            panel_principal.addTab(titulo, panelml);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);


        } else {
            panel_principal.remove(index);
            panel_principal.addTab(titulo, panelml);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);
        }
    }
//      public void CrearPanelNml() {
//        Ventas_normal panelnml = new Ventas_normal();
//        String titulo = "Ventas Normales";
//        int index = panel_principal.indexOfTab(titulo);
//
//
//
//        if (index == -1) {
//            panel_principal.addTab(titulo, panelnml);
//            int i = panel_principal.indexOfTab(titulo);
//            panel_principal.setSelectedIndex(i);
//
//
//        } else {
//            panel_principal.remove(index);
//            panel_principal.addTab(titulo, panelnml);
//            int i = panel_principal.indexOfTab(titulo);
//            panel_principal.setSelectedIndex(i);
//        }
//    }
     
    public void CrearPanelEmpleado() {
        Empleados panelempleados = new Empleados();
        String titulo = "Gestionar Empleados";
        int index = panel_principal.indexOfTab(titulo);



        if (index == -1) {
            panel_principal.addTab(titulo, panelempleados);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);


        } else {
            panel_principal.remove(index);
            panel_principal.addTab(titulo, panelempleados);
            int i = panel_principal.indexOfTab(titulo);
            panel_principal.setSelectedIndex(i);
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
        btn_ges_prod = new javax.swing.JButton();
        btn_ges_cli = new javax.swing.JButton();
        btn_gen_ped = new javax.swing.JButton();
        btn_gen_cot = new javax.swing.JButton();
        btn_ges_prov = new javax.swing.JButton();
        btn_ges_emp = new javax.swing.JButton();
        btn_ml = new javax.swing.JButton();
        panel_principal = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_aju = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        men_productos = new javax.swing.JMenu();
        mni_panel_prod = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mni_Listado_Precios = new javax.swing.JMenuItem();
        men_clientes = new javax.swing.JMenu();
        mni_panel_cli = new javax.swing.JMenuItem();
        men_proveedores = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        men_empleados = new javax.swing.JMenu();
        mni_ges_emp = new javax.swing.JMenuItem();
        men_ventas = new javax.swing.JMenu();
        men_cobranza = new javax.swing.JMenu();
        men_opciones = new javax.swing.JMenu();
        mni_ajustes = new javax.swing.JMenuItem();
        mni_ajustes1 = new javax.swing.JMenuItem();
        men_sesion = new javax.swing.JMenu();
        men_ayuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Obed77 Principal");
        setBackground(new java.awt.Color(0, 153, 153));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1600, 900));

        jPanel1.setMinimumSize(new java.awt.Dimension(1083, 57));
        jPanel1.setOpaque(false);

        btn_ges_prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Productos/btn_gest1.png"))); // NOI18N
        btn_ges_prod.setToolTipText("Gestionar Productos");
        btn_ges_prod.setBorder(null);
        btn_ges_prod.setBorderPainted(false);
        btn_ges_prod.setContentAreaFilled(false);
        btn_ges_prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ges_prod.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ges_prod.setInheritsPopupMenu(true);
        btn_ges_prod.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Productos/btn_gest1.png"))); // NOI18N
        btn_ges_prod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Productos/btn_gest2.png"))); // NOI18N
        btn_ges_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ges_prodActionPerformed(evt);
            }
        });

        btn_ges_cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest1.png"))); // NOI18N
        btn_ges_cli.setToolTipText("Gestionar Clientes");
        btn_ges_cli.setBorder(null);
        btn_ges_cli.setBorderPainted(false);
        btn_ges_cli.setContentAreaFilled(false);
        btn_ges_cli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ges_cli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ges_cli.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest1.png"))); // NOI18N
        btn_ges_cli.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest2.png"))); // NOI18N
        btn_ges_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ges_cliActionPerformed(evt);
            }
        });

        btn_gen_ped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_facturar1.png"))); // NOI18N
        btn_gen_ped.setToolTipText("Generar Factura");
        btn_gen_ped.setBorder(null);
        btn_gen_ped.setBorderPainted(false);
        btn_gen_ped.setContentAreaFilled(false);
        btn_gen_ped.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_gen_ped.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_gen_ped.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_facturar3.png"))); // NOI18N
        btn_gen_ped.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_facturar2.png"))); // NOI18N
        btn_gen_ped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gen_pedActionPerformed(evt);
            }
        });

        btn_gen_cot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_orden1.png"))); // NOI18N
        btn_gen_cot.setToolTipText("Generar Cotización");
        btn_gen_cot.setBorder(null);
        btn_gen_cot.setBorderPainted(false);
        btn_gen_cot.setContentAreaFilled(false);
        btn_gen_cot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_gen_cot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_gen_cot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_orden1.png"))); // NOI18N
        btn_gen_cot.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_orden2.png"))); // NOI18N
        btn_gen_cot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gen_cotActionPerformed(evt);
            }
        });

        btn_ges_prov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Proveedores/btn_gestionar1.png"))); // NOI18N
        btn_ges_prov.setToolTipText("Gestionar Proveedores");
        btn_ges_prov.setBorder(null);
        btn_ges_prov.setBorderPainted(false);
        btn_ges_prov.setContentAreaFilled(false);
        btn_ges_prov.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ges_prov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ges_prov.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Proveedores/btn_gestionar1.png"))); // NOI18N
        btn_ges_prov.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Proveedores/btn_gestionar2.png"))); // NOI18N
        btn_ges_prov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ges_provActionPerformed(evt);
            }
        });

        btn_ges_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_gestionar1.png"))); // NOI18N
        btn_ges_emp.setToolTipText("Gestionar Empleados");
        btn_ges_emp.setBorder(null);
        btn_ges_emp.setBorderPainted(false);
        btn_ges_emp.setContentAreaFilled(false);
        btn_ges_emp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ges_emp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ges_emp.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_gestionar1.png"))); // NOI18N
        btn_ges_emp.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_gestionar2.png"))); // NOI18N
        btn_ges_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ges_empActionPerformed(evt);
            }
        });

        btn_ml.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_ml1.png"))); // NOI18N
        btn_ml.setToolTipText("Listado Ventas MercadoLibre");
        btn_ml.setBorder(null);
        btn_ml.setBorderPainted(false);
        btn_ml.setContentAreaFilled(false);
        btn_ml.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ml.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ml.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_ml1.png"))); // NOI18N
        btn_ml.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_ml2.png"))); // NOI18N
        btn_ml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_ges_prod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ges_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ges_prov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ges_emp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gen_ped)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gen_cot)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ml)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ges_cli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ges_prod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_gen_ped, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ges_prov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ges_emp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_gen_cot)
                            .addComponent(btn_ml))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1259, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );

        panel_principal.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_principal_1.png")), jPanel3); // NOI18N

        btn_aju.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_config1.png"))); // NOI18N
        btn_aju.setToolTipText("Ajustes de Sistema");
        btn_aju.setBorder(null);
        btn_aju.setBorderPainted(false);
        btn_aju.setContentAreaFilled(false);
        btn_aju.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_aju.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_aju.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_config1.png"))); // NOI18N
        btn_aju.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_config2.png"))); // NOI18N
        btn_aju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_aju)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_aju)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(56, 32));

        men_productos.setText("Productos");
        men_productos.setToolTipText("Menú de Productos");
        men_productos.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N

        mni_panel_prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Productos/btn_gest3.png"))); // NOI18N
        mni_panel_prod.setText("Panel Productos");
        mni_panel_prod.setToolTipText("Gestionar Productos");
        mni_panel_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_panel_prodActionPerformed(evt);
            }
        });
        men_productos.add(mni_panel_prod);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Productos/btn_reportes1.png"))); // NOI18N
        jMenu1.setText("Reportes");
        jMenu1.setToolTipText("Reportes Producto");

        jMenuItem1.setText("Listado de Costos");
        jMenuItem1.setToolTipText("Inventario con Costo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        mni_Listado_Precios.setText("Listado de Precios");
        mni_Listado_Precios.setToolTipText("Inventario con Precios ");
        mni_Listado_Precios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_Listado_PreciosActionPerformed(evt);
            }
        });
        jMenu1.add(mni_Listado_Precios);

        men_productos.add(jMenu1);

        jMenuBar1.add(men_productos);

        men_clientes.setText("Clientes");
        men_clientes.setToolTipText("Menú de Clientes");
        men_clientes.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N

        mni_panel_cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
        mni_panel_cli.setText("Panel Clientes");
        mni_panel_cli.setToolTipText("Gestionar Clientes");
        mni_panel_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_panel_cliActionPerformed(evt);
            }
        });
        men_clientes.add(mni_panel_cli);

        jMenuBar1.add(men_clientes);

        men_proveedores.setText("Proveedores");
        men_proveedores.setToolTipText("Menú de Proveedores");
        men_proveedores.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Proveedores/btn_gestionar3.png"))); // NOI18N
        jMenuItem2.setText("Panel Proveedores");
        jMenuItem2.setToolTipText("Gestionar Proveedores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        men_proveedores.add(jMenuItem2);

        jMenuBar1.add(men_proveedores);

        men_empleados.setText("Empleados");
        men_empleados.setToolTipText("Menú de Empleados");
        men_empleados.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N

        mni_ges_emp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Empleados/btn_gestionar3.png"))); // NOI18N
        mni_ges_emp.setText("Panel Empleados");
        mni_ges_emp.setToolTipText("Gestionar Empleados");
        mni_ges_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ges_empActionPerformed(evt);
            }
        });
        men_empleados.add(mni_ges_emp);

        jMenuBar1.add(men_empleados);

        men_ventas.setText("Ventas");
        men_ventas.setToolTipText("Menú de Ventas");
        men_ventas.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N
        jMenuBar1.add(men_ventas);

        men_cobranza.setText("Cobranza");
        men_cobranza.setToolTipText("Menú de Cobranza");
        men_cobranza.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N
        jMenuBar1.add(men_cobranza);

        men_opciones.setText("Opciones");
        men_opciones.setToolTipText("Menú de Opciones");
        men_opciones.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N

        mni_ajustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_config3.png"))); // NOI18N
        mni_ajustes.setText("Ajustes");
        mni_ajustes.setToolTipText("Ajustes de Sistema");
        mni_ajustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ajustesActionPerformed(evt);
            }
        });
        men_opciones.add(mni_ajustes);

        mni_ajustes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_config3.png"))); // NOI18N
        mni_ajustes1.setText("Respaldar-Restaurar Base de Datos");
        mni_ajustes1.setToolTipText("Ajustes de Sistema");
        mni_ajustes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_ajustes1ActionPerformed(evt);
            }
        });
        men_opciones.add(mni_ajustes1);

        jMenuBar1.add(men_opciones);

        men_sesion.setText("Sesión");
        men_sesion.setToolTipText("Menú de Sesión");
        men_sesion.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N
        jMenuBar1.add(men_sesion);

        men_ayuda.setText("Ayuda");
        men_ayuda.setToolTipText("Menú de Ayuda");
        men_ayuda.setFont(new java.awt.Font("Goudy Old Style", 0, 14)); // NOI18N
        jMenuBar1.add(men_ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panel_principal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_principal))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ges_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ges_prodActionPerformed
        CrearPanelProductos();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ges_prodActionPerformed

    private void mni_panel_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_panel_prodActionPerformed
    CrearPanelProductos();         // TODO add your handling code here:
    }//GEN-LAST:event_mni_panel_prodActionPerformed

    private void btn_ges_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ges_cliActionPerformed
        CrearPanelCliente();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ges_cliActionPerformed

    private void mni_panel_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_panel_cliActionPerformed
CrearPanelCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_mni_panel_cliActionPerformed

    private void btn_gen_pedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gen_pedActionPerformed
        Ventana_Facturar vp = new Ventana_Facturar();
        vp.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_gen_pedActionPerformed

    private void btn_gen_cotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gen_cotActionPerformed
        Ventana_Cotizacion vp = new Ventana_Cotizacion();
        vp.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_gen_cotActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
ReporteInventario();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_ajuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajuActionPerformed
        Ventana_Ajustes va = new Ventana_Ajustes();
        va.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btn_ajuActionPerformed

    private void mni_ajustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ajustesActionPerformed
Ventana_Ajustes va = new Ventana_Ajustes();
        va.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_mni_ajustesActionPerformed

    private void mni_Listado_PreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_Listado_PreciosActionPerformed
       int op = JOptionPane.showConfirmDialog(this,"¿Desea imprimir el reporte totalizado?", "Imprimir Reporte",JOptionPane.YES_NO_CANCEL_OPTION);
       if(op==0)
       {
         ReporteListadoPrecios(true);  
       }else
       {
           if(op==1)
           {
              ReporteListadoPrecios(false); 
           }
       }
           // TODO add your handling code here:
    }//GEN-LAST:event_mni_Listado_PreciosActionPerformed

    private void btn_ges_provActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ges_provActionPerformed
CrearPanelProveedor();    // TODO add your handling code here:
    }//GEN-LAST:event_btn_ges_provActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
 CrearPanelProveedor();    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_ges_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ges_empActionPerformed
CrearPanelEmpleado();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ges_empActionPerformed

    private void mni_ges_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ges_empActionPerformed
CrearPanelEmpleado();        // TODO add your handling code here:
    }//GEN-LAST:event_mni_ges_empActionPerformed

    private void btn_mlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mlActionPerformed
CrearPanelMl();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mlActionPerformed

    private void mni_ajustes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_ajustes1ActionPerformed
    BaseDeDatos i = new BaseDeDatos();
    i.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_mni_ajustes1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_aju;
    public static javax.swing.JButton btn_gen_cot;
    public static javax.swing.JButton btn_gen_ped;
    public static javax.swing.JButton btn_ges_cli;
    public static javax.swing.JButton btn_ges_emp;
    public static javax.swing.JButton btn_ges_prod;
    public static javax.swing.JButton btn_ges_prov;
    public static javax.swing.JButton btn_ml;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenu men_ayuda;
    private javax.swing.JMenu men_clientes;
    private javax.swing.JMenu men_cobranza;
    private javax.swing.JMenu men_empleados;
    private javax.swing.JMenu men_opciones;
    private javax.swing.JMenu men_productos;
    private javax.swing.JMenu men_proveedores;
    private javax.swing.JMenu men_sesion;
    private javax.swing.JMenu men_ventas;
    private javax.swing.JMenuItem mni_Listado_Precios;
    private javax.swing.JMenuItem mni_ajustes;
    private javax.swing.JMenuItem mni_ajustes1;
    private javax.swing.JMenuItem mni_ges_emp;
    private javax.swing.JMenuItem mni_panel_cli;
    private javax.swing.JMenuItem mni_panel_prod;
    public static javax.swing.JTabbedPane panel_principal;
    // End of variables declaration//GEN-END:variables
}
