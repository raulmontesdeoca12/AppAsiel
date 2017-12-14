/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Propiedades;

import BaseDeDatos.ConexionBD;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class Ventana_Ajustes extends javax.swing.JFrame {
Shape shape = null;
Properties pp = new Properties();
    public Ventana_Ajustes() {
        UIManager.put("TabbedPane.contentOpaque", false);
        initComponents();
        cargardatos();
        
    }
@Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
    void cargardatos()
    {
    try {
        pp.load(new BufferedReader(new FileReader("ajustes.properties")));
        String bdd, user, pass,dir;
        bdd=pp.getProperty("bdd");
        user=pp.getProperty("user");
        pass=pp.getProperty("pass");
        dir=pp.getProperty("directorio");
        this.txt_bdd.setText(bdd);
        this.txt_user.setText(user);
        this.txt_pass.setText(pass);
        this.txt_dic.setText(dir);
        if(txt_dic.getText().isEmpty())
        {
            area.setText("Debe crear un directorio");
        }else
        {
            consultarDir();
        }
        
        if(txt_bdd.getText().isEmpty() || txt_user.getText().isEmpty()|| txt_pass.getText().isEmpty() )
        {
            this.lab_accion.setText("No se cargaron los datos");
            this.lab_error.setText("No Hay Error...");
        }else
        {
            this.lab_accion.setText("Datos Cargados");
            this.lab_error.setText("No Hay Error...");
        }
    } catch (IOException ex) {
        Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        this.lab_error.setText(""+ex);
    }
    }
    void consultarDir()
    {
        String s="";
         File p = new File(txt_dic.getText()+"/Obed77");
        File pd = new File(txt_dic.getText()+"/Obed77/PDF");
        File f = new File(txt_dic.getText()+"/Obed77/PDF/Facturas");
        File c = new File(txt_dic.getText()+"/Obed77/PDF/Cotizaciones");
        File i = new File(txt_dic.getText()+"/Obed77/PDF/Inventario");
        File fn = new File(txt_dic.getText()+"/Obed77/PDF/Facturasn");
        area.setText("Analizando directorio en busca de carpeta \"Obed77\"\n");
        s=area.getText();
        if (p.isDirectory())
        {
            s=s+"Carpeta \"Obed77\" ya existe buscando carpeta \"PDF\"\n";
            area.setText(s);
            if(pd.isDirectory())
            {
                s=s+"Carpeta \"PDF\" ya existe buscando sub-carpetas\n";
                area.setText(s);
                if(f.isDirectory())
                {
                    s=s+"Carpeta \"Facturas\" ya existe \n";
                    area.setText(s);
                }else
                {
                    s=s+"Carpeta \"Facturas\" OJO---->No Existe\n";
                    area.setText(s);
                }
                 if(fn.isDirectory())
                {
                    s=s+"Carpeta \"Facturasn\" ya existe \n";
                    area.setText(s);
                }else
                {
                    s=s+"Carpeta \"Facturasn\" OJO---->No Existe\n";
                    area.setText(s);
                }
                if(c.isDirectory())
                {
                     s=s+"Carpeta \"Cotizaciones\" ya existe \n";
                    area.setText(s);
                    
                }else
                {
                    s=s+"Carpeta \"Cotizaciones\" OJO----> No Existe\n";
                    area.setText(s);
                }
                if(i.isDirectory()){
                    s=s+"Carpeta \"Inventario\" ya existe \n";
                    area.setText(s);
                }else
                {
                    s=s+"Carpeta \"Inventario\" OJO----> No Existe\n";
                    area.setText(s);
                }
            }else
            {
                s=s+"Carpeta \"PDF\" OJO----> No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Facturas\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Facturasn\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Cotizaciones\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Inventario\" No Existe\n";
                area.setText(s);
            }
            
        }else
        {
                
                s=s+"Carpeta \"Obed77\" OJO----> No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"PDF\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Facturas\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Facturasn\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Cotizaciones\" No Existe\n";
                area.setText(s);
                s=s+"Carpeta \"Inventario\" No Existe\n";
                area.setText(s);
        }
    }
    void modificardatos()
    {
    try {
        String user, pass, bdd;
        String v1,v2,v3;
        bdd= this.txt_bdd.getText();
        user= txt_user.getText();
        char[] arrayc = txt_pass.getPassword();
        pass = new String(arrayc);
        pp.load(new BufferedReader(new FileReader("ajustes.properties")));
        
        pp.setProperty("bdd", bdd);
        pp.setProperty("user", user);
        pp.setProperty("pass", pass);
        
        pp.store(new BufferedWriter(new FileWriter("ajustes.properties")), "Nuevos Datos");
       
        
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        this.lab_accion.setText("No se pudo modificar");
        this.lab_error.setText(""+ex);
    } catch (IOException ex) {
        Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        this.lab_accion.setText("No se pudo modificar");
        this.lab_error.setText(""+ex);
    }
    
    cargardatos();
    this.lab_accion.setText("Datos Modificados y Cargados");
    this.lab_error.setText("No Hay Error...");
        
        
    }
    
    void prueba()
    {
        try{
            
                    String bdd, user, pass, url;
                    bdd= txt_bdd.getText();
                    user= txt_user.getText();
                    pass=txt_pass.getText();
                    url="jdbc:mysql://localhost/"+bdd;
                     ConexionBD parametros = new ConexionBD();
                     Class.forName(parametros.getDriver());
                     Connection con = DriverManager.getConnection(url, user, pass);
                  
                     this.lab_accion.setText("Conectado con la Base de Datos...");
                     this.lab_error.setText("No Hay Error...");
                         
                     
                    
                    }catch(SQLException ex) {
                       this.lab_accion.setText("Error con la Base de Datos");
                       String er ="";
                       int error = ex.getErrorCode();
                       if (error==1045)
                       {
                          er = "Error: Usuario O Contraseña Incorrectos";
                       }else
                           if(error==1049)
                           {
                               er = "Error: No Existe la Base de Datos '"+txt_bdd.getText()+"'";
                           }else
                           {
                               er = "Error N°: "+ex.getErrorCode();
                           }
                        this.lab_error.setText(er);

                    } catch (ClassNotFoundException ex) {
                          this.lab_accion.setText("Error con la Base de Datos...");
                          System.out.println("Error al conectar con la Base de Datos..."+ex);
                      
                    }
                
    }
    void creardir()
    {
        area.setText("");
        String s= "";
        File p = new File(txt_dic.getText()+"/Obed77");
        File pd = new File(txt_dic.getText()+"/Obed77/PDF");
        File f = new File(txt_dic.getText()+"/Obed77/PDF/Facturas");
        File c = new File(txt_dic.getText()+"/Obed77/PDF/Cotizaciones");
        File i = new File(txt_dic.getText()+"/Obed77/PDF/Inventario");
        File fn = new File(txt_dic.getText()+"/Obed77/PDF/Facturasn");
        area.setText("Analizando directorio en busca de carpeta \"Obed77\"\n");
        s=area.getText();
        if (p.isDirectory())
        {
            s=s+"Carpeta \"Obed77\" ya existe buscando carpeta \"PDF\"\n";
            area.setText(s);
            if(pd.isDirectory())
            {
                s=s+"Carpeta \"PDF\" ya existe buscando sub-carpetas\n";
                area.setText(s);
                if(f.isDirectory())
                {
                    s=s+"Carpeta \"Facturas\" ya existe No se creará la carpeta\n";
                    area.setText(s);
                }else
                {
                    f.mkdir();
                    s=s+"Carpeta \"Facturas\" Creada\n";
                    area.setText(s);
                }
                 if(fn.isDirectory())
                {
                    s=s+"Carpeta \"Facturasn\" ya existe No se creará la carpeta\n";
                    area.setText(s);
                }else
                {
                    fn.mkdir();
                    s=s+"Carpeta \"Facturasn\" Creada\n";
                    area.setText(s);
                }
                if(c.isDirectory())
                {
                     s=s+"Carpeta \"Cotizaciones\" ya existe No se creará la carpeta\n";
                    area.setText(s);
                    
                }else
                {
                    c.mkdir();
                    s=s+"Carpeta \"Cotizaciones\" Creada\n";
                    area.setText(s);
                }
                if(i.isDirectory()){
                    s=s+"Carpeta \"Inventario\" ya existe No se creará la carpeta\n";
                    area.setText(s);
                }else
                {
                    i.mkdir();
                    s=s+"Carpeta \"Inventario\" Creada\n";
                    area.setText(s);
                }
            }else
            {
                pd.mkdir();
                s=s+"Carpeta \"PDF\" Creada\n";
                area.setText(s);
                f.mkdir();
                s=s+"Carpeta \"Facturas\" Creada\n";
                area.setText(s);
                fn.mkdir();
                s=s+"Carpeta \"Facturasn\" Creada\n";
                area.setText(s);
                c.mkdir();
                s=s+"Carpeta \"Cotizaciones\" Creada\n";
                area.setText(s);
                i.mkdir();
                s=s+"Carpeta \"Inventario\" Creada\n";
                area.setText(s);
            }
            
        }else
        {
                p.mkdir();
                s=s+"Carpeta \"Obed77\" Creada\n";
                area.setText(s);
                pd.mkdir();
                s=s+"Carpeta \"PDF\" Creada\n";
                area.setText(s);
                f.mkdir();
                s=s+"Carpeta \"Facturas\" Creada\n";
                area.setText(s);
                fn.mkdir();
                s=s+"Carpeta \"Facturasn\" Creada\n";
                area.setText(s);
                c.mkdir();
                s=s+"Carpeta \"Cotizaciones\" Creada\n";
                area.setText(s);
                i.mkdir();
                s=s+"Carpeta \"Inventario\" Creada\n";
                area.setText(s);
        }
        try{
        String dir;
        dir= this.txt_dic.getText();
        
        pp.load(new BufferedReader(new FileReader("ajustes.properties")));
        
        pp.setProperty("directorio", dir);
        
        pp.store(new BufferedWriter(new FileWriter("ajustes.properties")), "Nuevos Datos");
       
        
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        this.lab_accion.setText("No se pudo modificar");
        this.lab_error.setText(""+ex);
    } catch (IOException ex) {
        Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
        this.lab_accion.setText("No se pudo modificar");
        this.lab_error.setText(""+ex);
    }
    
    cargardatos();
    this.lab_accion.setText("Datos de Directorios Modificados y Cargados");
    this.lab_error.setText("No Hay Error...");

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_dic = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_crearc = new javax.swing.JButton();
        btn_limpiard = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_probar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        txt_bdd = new javax.swing.JTextField();
        txt_user = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        btn_salir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lab_accion = new javax.swing.JLabel();
        lab_error = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustes");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(790, 590));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Directorio Principal"));
        jPanel2.setOpaque(false);

        jLabel6.setText("Directorio:");

        txt_dic.setEditable(false);

        btn_buscar.setText("Buscar");
        btn_buscar.setOpaque(false);
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        jLabel7.setText(" Elija un directorio para crear las carpetas que almacenarán los PDF");
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_crearc.setText("Crear Carpetas");
        btn_crearc.setOpaque(false);
        btn_crearc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearcActionPerformed(evt);
            }
        });

        btn_limpiard.setText("Limpiar");
        btn_limpiard.setOpaque(false);
        btn_limpiard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiardActionPerformed(evt);
            }
        });

        area.setEditable(false);
        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_limpiard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_crearc))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_dic, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buscar))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_crearc)
                    .addComponent(btn_limpiard))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 10, 400, 310);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Base de Datos"));
        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Base de Datos:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Usuario:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contraseña:");

        btn_probar.setText("Probar");
        btn_probar.setOpaque(false);
        btn_probar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_probarActionPerformed(evt);
            }
        });

        btn_guardar.setText("Guardar");
        btn_guardar.setOpaque(false);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btn_probar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_guardar)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_bdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar)
                    .addComponent(btn_probar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(420, 10, 370, 170);

        btn_salir.setText("Salir");
        btn_salir.setOpaque(false);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir);
        btn_salir.setBounds(701, 523, 80, 30);

        jLabel4.setText("Acción:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(290, 530, 60, 14);
        getContentPane().add(lab_accion);
        lab_accion.setBounds(326, 530, 360, 14);
        getContentPane().add(lab_error);
        lab_error.setBounds(290, 510, 400, 14);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar800x600.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        String dir ="";
        JFileChooser fc = new JFileChooser();
        //Mostrar la ventana para abrir archivo y recoger la respuesta
        //En el parámetro del showOpenDialog se indica la ventana
        //  al que estará asociado. Con el valor this se asocia a la
        //  ventana que la abre.
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = fc.showOpenDialog(this);
        //Comprobar si se ha pulsado Aceptar
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            try {
                //Crear un objeto File con el archivo elegido
                File archivoElegido = fc.getSelectedFile();
                dir =  archivoElegido.getCanonicalPath();
                dir = dir.replace("\\", "/");
                    txt_dic.setText(dir);
                } // TODO add your handling code here:
                catch (IOException ex) {
                    Logger.getLogger(Ventana_Ajustes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        modificardatos();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_probarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_probarActionPerformed
        prueba();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_probarActionPerformed

    private void btn_limpiardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiardActionPerformed
txt_dic.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiardActionPerformed

    private void btn_crearcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearcActionPerformed
        if (txt_dic.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No puede dejar el campo vacio", "Error", 1, null);
        }
        else
        {
            creardir();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_crearcActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Ajustes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_crearc;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_limpiard;
    private javax.swing.JButton btn_probar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_accion;
    private javax.swing.JLabel lab_error;
    private javax.swing.JTextField txt_bdd;
    private javax.swing.JTextField txt_dic;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
