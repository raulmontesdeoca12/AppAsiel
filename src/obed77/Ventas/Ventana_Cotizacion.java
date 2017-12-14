
package obed77.Ventas;


import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_cotizacion;
import BaseDeDatos.Ventas.clase_cotizacion_det;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import static obed77.Ventas.Ventana_Facturar.tabla_producto;


public class Ventana_Cotizacion extends javax.swing.JFrame {
int exc,exp, ivapor,pedido;
DefaultTableModel m;
DefaultTableModel mp;
Properties pp = new Properties();
static float total;
float sub_total;
float ivam;
float excento;
float de;
float pre_total;
int cont_cant,idfact;
int cod,dia,mes,anio;
String nid;
String fecha,estatus;
Shape shape = null;
    public Ventana_Cotizacion() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),30,30);
        AWTUtilities.setWindowShape(this, shape);
        total=0;
        sub_total=0;
        ivam=0;
        excento=0;
        cont_cant=0;
        deshabilitarEdicion();
        limpiar();
        this.btn_imprimir.setVisible(false);
        cargarlabels();
    }
 @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
    void habilitar()
    {
        this.cbo_desc.setEnabled(false);
        this.btn_buscar_clie.setEnabled(true);
        this.btn_agregar_prod.setEnabled(true);
        this.btn_cotizar.setEnabled(true);
    }
     
    void cargarlabels()
    {
        //cargar numero de pedido
        int id, pid;
        
        try
      {
       
        String sql = "SELECT * FROM cotizacion";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        if(rs.last())
        {
            id=rs.getInt("idcotizacion");
            pid=id+1;
            nid=Integer.toString(pid);
            System.out.println(""+nid);
            
            
        }else{
            nid="0"; 
        }
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
       }
      //cargar fecha
      Calendar cal = Calendar.getInstance();
      anio=cal.get(Calendar.YEAR);
      int mesi=cal.get(Calendar.MONTH);
      int mest=mesi+1;
      mes=mest;
      dia=cal.get(Calendar.DATE);
      
      fecha= anio+"-"+mes+"-"+dia;
      String ivas = null;
       try
      {
       
        String sql = "SELECT * FROM datos_extras";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        if(rs.last())
        {
           ivapor=rs.getInt("iva");
           ivas=rs.getString("iva");
            
        }else{
            JOptionPane.showMessageDialog(this,"Error...\n No se encuentra ningún dato","Error",1,null);
        }
        this.lab_numfact.setText(nid);
      this.lab_fecha.setText(fecha);
      this.lab_ivapor.setText(ivas);
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }

       
  

  void deshabilitarEdicion()
  {
      this.txt_rif.setEditable(false);
      this.txt_nombre_cliente.setEditable(false);
      this.txt_telefono.setEditable(false);
      this.txt_direccion.setEditable(false);
  }
      void limpiaTabla(){
           
               m = (DefaultTableModel) tabla_factura.getModel();
               int a =m.getRowCount()-1;
               while(a>=0)
               m.removeRow(0);
           
       }
  void limpiar()
  {

      this.lab_excento.setText(null);
      this.lab_subtotal.setText(null);
      this.lab_iva.setText(null);
      this.lab_valortotal.setText(null);
      Ventana_Cotizacion.txt_rif.setText(null);
      Ventana_Cotizacion.txt_nombre_cliente.setText(null);
      Ventana_Cotizacion.txt_telefono.setText(null);
      Ventana_Cotizacion.txt_direccion.setText(null);
      this.spin_vencimiento.setValue(1);
      this.cbo_flete.setSelectedItem("Cliente");
      this.spin_credito.setValue(0);
      this.cbo_desc.setSelectedItem("0%");
      this.cbo_desc.setEnabled(true);
      this.ch_mostrar.setSelected(true);
      limpiaTabla();
       
  }

  public static void validarcamposllenos()
  {
      String ci, rif;
      rif=txt_rif.getText();
      if(rif.isEmpty())
      {
          Ventana_Cotizacion.btn_agregar_prod.setEnabled(false);
      }else
      {
           Ventana_Cotizacion.btn_agregar_prod.setEnabled(true);
      }
      
  }
   void validarBotones() {
        int filasel;
        String cant;
        try {
            filasel = tabla_producto.getSelectedRow();
            cant = txt_cant.getText();

            if (filasel != -1 && !cant.isEmpty()) {
                this.btn_aceptar.setEnabled(true);
                this.btn_cotizar.setEnabled(true);

            } else {
                this.btn_aceptar.setEnabled(false);
            }


        } catch (Exception e) {
        }
    }
   
  void validarBotones2() {
        int filasel;
        try {
            filasel = tabla_factura.getSelectedRow();

            if (filasel != -1) {
                this.btn_remover_prod.setEnabled(true);

            } else {
                 this.btn_remover_prod.setEnabled(false);
            }


        } catch (Exception e) {
        }
    }
  void cargarproductos()
  {
      try {
            if(this.rad_normal.isSelected())
            {
            String [] titulos={"Código","Descripción","Precio Nor.","Cant."};
            String[] registros = new String[4];
            mp = new DefaultTableModel(null,titulos){
                 @Override
            public boolean isCellEditable(int row, int column) {
                return false;}
            };
            String sql;
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            sql = "SELECT * FROM producto WHERE estatus_prod = 'Activo'  order by descripcion_prod asc";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            

                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("descripcion_prod");
                registros[2] = rs.getString("precio_si");
                registros[3] = rs.getString("cantidad_prod");
                mp.addRow(registros);
            }
            
            tabla_producto.setModel(mp);
            tabla_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla_producto.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla_producto.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableRowSorter modeloOrdenado = new TableRowSorter(mp);
            tabla_producto.setRowSorter(modeloOrdenado);
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
            tabla_producto.setRowSorter(modeloOrdenado);

            }else if (this.rad_mercado.isSelected())
            {
            double precio_si;
            double precio_si_ml;
            double rend;
            String precio;
            
            String [] titulos={"Código","Descripción","Precio ML.","Cant."};
            String[] registros = new String[4];
            mp = new DefaultTableModel(null,titulos){
                 @Override
            public boolean isCellEditable(int row, int column) {
                return false;}
            };
            String sqlm;
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            sqlm = "SELECT * FROM producto WHERE estatus_prod = 'Activo' order by descripcion_prod asc";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sqlm);
            while (rs.next()) {
            

                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("descripcion_prod");
                
                precio_si=rs.getDouble("precio_si");
                precio_si_ml=precio_si/0.9;
                rend=Math.rint(precio_si_ml*100)/100;
                precio=String.valueOf(rend);
                registros[2] = precio;
                registros[3] = rs.getString("cantidad_prod");
                mp.addRow(registros);
            }
            
            tabla_producto.setModel(mp);
            tabla_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabla_producto.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla_producto.getColumnModel().getColumn(3).setPreferredWidth(30);
            TableRowSorter modeloOrdenado = new TableRowSorter(mp);
            tabla_producto.setRowSorter(modeloOrdenado);
            modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
            tabla_producto.setRowSorter(modeloOrdenado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  }
  void accbtnaceptar()
  {
      int fsel = tabla_producto.getSelectedRow();
     
      
      try {
       
       float x,calcula,descuento = 0;
       int cann=0;
       int cans=0;
       
       boolean b=false;
       String  codi,desc, prec, canp, imp,cant,descuento_s;
       descuento_s=this.cbo_desc.getSelectedItem().toString();
                switch (descuento_s) {
                    case "0%":
                        descuento=0;
                        break;
                    case "2%":
                        descuento=(float)0.02;
                        break;    
                    case "5%":
                        descuento=(float)0.05;
                        break; 
                    case "10%":
                        descuento=(float)0.10;
                        break;
                }
                
                   
                   codi=tabla_producto.getValueAt(fsel, 0).toString();
                   desc=tabla_producto.getValueAt(fsel, 1).toString();
                   prec=tabla_producto.getValueAt(fsel, 2).toString();
                   cant = txt_cant.getText();
                   cann=Integer.valueOf(cant);

                   x=(Float.parseFloat(prec) * Integer.parseInt(cant));
                   imp=String.valueOf(x);
                   int ftotal = tabla_factura.getRowCount();
                      for (int i = 0; i < ftotal; i++) {
                         String code=tabla_factura.getValueAt(i, 0).toString(); 
                         b = code.equals(codi);

                          }               
      
       if(b==true)
       {
           this.jLabel21.setText("Atención...No puede volver a ingresar el mismo producto");
       }
            else{
                  
                 m=(DefaultTableModel)tabla_factura.getModel();
                 String filaelemento[] = {codi,desc,cant,prec,imp};
                 m.addRow(filaelemento);
                 cont_cant=cont_cant+cann;

                 calcula=Float.parseFloat(imp);
                 
                 //calcula sub total
                 pre_total=pre_total+calcula;
                 
                 //calcula descuento
                 if (descuento==0)
                 {
                     de=0;
                 }else
                 {
                     de=pre_total*descuento;
                 }
                 sub_total=pre_total-de;
                 //calcula el iva del sub total
                 ivam=(sub_total*ivapor)/100;

                 //valida la cantidad de productos
                 
                 //calcula el total
                 total=(sub_total+ivam); 
                 
                 

                 //asignamos los valores;
                 float subtotalr, ivar, excentor, valortotalr, descue,pre;
                 subtotalr=(float) (Math.rint(sub_total*100)/100);
                 ivar=(float) (Math.rint(ivam*100)/100);
                 excentor=(float) (Math.rint(excento*100)/100);
                 valortotalr=(float) (Math.rint(total*100)/100);
                 descue=(float) (Math.rint(de*100)/100);
                 pre=(float) (Math.rint(pre_total*100)/100);
                 this.lab_subtotal.setText(subtotalr+"");
                 this.lab_iva.setText(ivar+"");
                 this.lab_excento.setText(excentor+"");
                 this.lab_valortotal.setText(valortotalr+"");
                 this.lab_descuento.setText(descue+"");
                 this.lab_pretotal.setText(pre+"");
                 int it = this.tabla_factura.getRowCount();
                 this.lab_items.setText(it+""); 
                 this.jLabel21.setText("Producto "+codi+" Ingresado Exitosamente...");

            }
       
          
       
          
      } catch (Exception e) {
      }
  }
  
  void remover()
  {
      String imp;
      int cantidad;
      float calc;
      float descuento = 0;
      int fsel = tabla_factura.getSelectedRow();
      imp = tabla_factura.getValueAt(fsel, 4).toString();
      cantidad=Integer.parseInt(tabla_factura.getValueAt(fsel,2).toString());
    String descuento_s = this.cbo_desc.getSelectedItem().toString();
                switch (descuento_s) {
                    case "0%":
                        descuento=0;
                        break;
                    case "2%":
                        descuento=(float)0.02;
                        break;    
                    case "5%":
                        descuento=(float)0.05;
                        break; 
                    case "10%":
                        descuento=(float)0.10;
                        break;
                }
      cont_cant=cont_cant-cantidad;
      
      calc=Float.parseFloat(imp);
      
      pre_total=pre_total-calc;
      
      if (descuento==0)
      {
          de=0;
      }else
      {
          de=pre_total*descuento;
      }
      sub_total=pre_total-de;
      ivam=(sub_total*ivapor)/100;
      
       if(cont_cant>=exc)
       {
         excento=(sub_total*exp)/100;
       }else
        {
           excento=0;
        }
      total=(sub_total+ivam)-excento;
      m=(DefaultTableModel)tabla_factura.getModel();
      m.removeRow(fsel);
      tabla_factura.setModel(m);
      
      float str=(float) (Math.rint(sub_total*100)/100);
      float ivar=(float) (Math.rint(ivam*100)/100);
      float exce=(float) (Math.rint(excento*100)/100);
      float totr=(float) (Math.rint(total*100)/100);
      float descue=(float) (Math.rint(de*100)/100);
      float pre=(float) (Math.rint(pre_total*100)/100);
      
       this.lab_subtotal.setText(str+"");
       this.lab_iva.setText(ivar+"");
       this.lab_excento.setText(exce+"");
       this.lab_valortotal.setText(totr+"");
       this.lab_descuento.setText(descue+"");
       this.lab_pretotal.setText(pre+"");
       
       int it = this.tabla_factura.getRowCount();
       this.lab_items.setText(it+""); 
      
      
  }
  
 
  

  void Cotizar()
   {
      
       int fc=tabla_factura.getRowCount();
       if(fc==0)
       {
           JOptionPane.showMessageDialog(this,"Error...\n La Cotizacion no posee productos","Atención",1,null);
       }else{
           int ad = 0;
           String fechas,flete,credito,descuentopor,apartado,cliente;
           int vencimiento,cred;
           int most=0;
           float pre,des,sub,iva,tot;
           cod=Integer.parseInt(this.lab_numfact.getText());
           cred=Integer.valueOf(this.spin_credito.getValue().toString());
           
           if (cred==-1)
           {
               credito="Anticipado";
           }else
           if(cred==0)
           {
               credito="Contado";
           }else
               
           {
               credito=cred+" Días";
           }
       
           if (this.ch_mostrar.isSelected())
           {
               most=1;
           }else
           {
               most=0;
           }
           apartado="no";
           pre=Float.parseFloat(this.lab_pretotal.getText());
           sub=Float.parseFloat(this.lab_subtotal.getText());
           iva=Float.parseFloat(this.lab_iva.getText());
           des=Float.parseFloat(this.lab_descuento.getText());
           tot=Float.parseFloat(this.lab_valortotal.getText());
           fechas=this.lab_fecha.getText();
           String ven=this.spin_vencimiento.getValue().toString();
           vencimiento=Integer.valueOf(ven);
           flete=this.cbo_flete.getSelectedItem().toString();
           descuentopor=Ventana_Cotizacion.cbo_desc.getSelectedItem().toString();
           
           cliente=Ventana_Cotizacion.txt_rif.getText();
           try {
               clase_cotizacion cot = new clase_cotizacion(cod,fechas,vencimiento,flete,credito,descuentopor,apartado,cliente,pre,des,sub,iva,tot,most);
               Gest_ventas in = new Gest_ventas();
               boolean r;
               r=in.InsertarCot(cot);
               if(r==false)
               {
                   llenardetalle();
               }
               
           } catch(SQLException e)
           {
               JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
               System.out.println("Error en Cotiz");
           }
           catch(ClassNotFoundException ex)
           {
               Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
           }
       }
        
           
                
        
       
   }
  public void guardarbdd()
    {
        int rsu=0;
        FileInputStream fis;
        int lon;
      
            try {
                pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                String dir;
                dir=pp.getProperty("directorio");
                fis = new FileInputStream(dir+"/Obed77/PDF/Cotizaciones/Cotizacion_"+idfact+".pdf");
                String sFichero = dir+"/Obed77/PDF/Cotizaciones/Cotizacion_"+idfact+".pdf";
                File fichero = new File(sFichero);
                lon=(int)fichero.length();
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                String sql ="INSERT into documento_cot (iddocumento_cot,cot) values (?,?);";
                PreparedStatement ps;
                ps = con.prepareStatement(sql);
                ps.setBlob(2, fis, lon);
                ps.setInt(1, idfact);
                rsu=ps.executeUpdate();
                if (rsu==1)
                {
                    JOptionPane.showMessageDialog(this,"Documento Guardado en Base de Datos","Guardar",1,null);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
        Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
    }
  void llenardetalle()
  {
      
      String idfacts;
      String idprod;
      int cant_prod;
      float total_prod;
      float p_unitario;
 
      
      //validar tabla
      int fc = tabla_factura.getRowCount();
      idfacts=this.lab_numfact.getText();
      idfact=Integer.parseInt(this.lab_numfact.getText());
        
             try{
            Gest_ventas in = new Gest_ventas();
       
            boolean p = false;
            for (int i = 0; i < fc; i++) {
                
                idprod=tabla_factura.getValueAt(i, 0).toString();
                cant_prod=Integer.parseInt(tabla_factura.getValueAt(i, 2).toString());
                total_prod=Float.parseFloat(tabla_factura.getValueAt(i, 4).toString());
                p_unitario=Float.parseFloat(tabla_factura.getValueAt(i, 3).toString());
                clase_cotizacion_det dped = new clase_cotizacion_det(idprod, cant_prod,idfact,p_unitario,total_prod);
                
                
                p=in.InsertarDetCot(dped);
               
                
            }
            if (p=false)
            {
                
            }
            else
            {
              try {
                  pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                String dir;
                dir=pp.getProperty("directorio");
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Cotizacion.jasper");
                Map par = new HashMap();
                par.clear(); 
                par.put("codcot", idfact);
                JasperPrint jPrint = JasperFillManager.fillReport(Report, par , con);
                JasperPrintManager.printReport(jPrint, true);
                JasperExportManager.exportReportToPdfFile( jPrint, dir+"/Obed77/PDF/Cotizaciones/Cotizacion_"+idfacts+".pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                int op= JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Cotización?", "Imprimir", JOptionPane.YES_NO_OPTION);
                if (op==0)
              {
                  guardarbdd();
                  this.dispose();
              }else
                {
                    this.btn_cotizar.setVisible(false);
                    this.btn_imprimir.setVisible(true);
                    this.btn_limpiar.setEnabled(false);
                    this.spin_credito.setEnabled(false);
                    this.cbo_flete.setEnabled(false);
                    Ventana_Cotizacion.cbo_desc.setEnabled(false);
                    this.spin_vencimiento.setEnabled(false);
                    this.btn_buscar_clie.setEnabled(false);
                    Ventana_Cotizacion.btn_agregar_prod.setEnabled(false);
                    Ventana_Cotizacion.btn_remover_prod.setEnabled(false);
                    this.ch_mostrar.setEnabled(false);
                }
            }
             
        }
    
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",1,null);
                System.out.println("Error en Guardar");
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
            }
      
  }
  public void imprimir()
  {
       String idfacts=String.valueOf(idfact);
       try {
           pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                String dir;
                dir=pp.getProperty("directorio");
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Cotizacion.jasper");
                Map par = new HashMap();
                par.clear(); 
                par.put("codcot", idfact);
                JasperPrint jPrint = JasperFillManager.fillReport(Report, par , con);
                JasperPrintManager.printReport(jPrint, true);
                JasperExportManager.exportReportToPdfFile( jPrint, dir+"/Obed77/PDF/Cotizaciones/Cotizacion_"+idfacts+".pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
        Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
    }
                int op= JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Cotización?", "Imprimir", JOptionPane.YES_NO_OPTION);
                if (op==0)
              {
                  guardarbdd();
                  this.dispose();
              }else
                {
                    
                }
   }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventana_prod = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_aceptar = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        btn_mostrar_todo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txt_cant = new javax.swing.JTextField();
        rad_mercado = new javax.swing.JRadioButton();
        rad_normal = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_rif = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre_cliente = new javax.swing.JTextField();
        btn_buscar_clie = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lab_numfact = new javax.swing.JLabel();
        lab_fecha = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_agregar_prod = new javax.swing.JButton();
        btn_remover_prod = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();
        btn_cotizar = new javax.swing.JButton();
        lab_items = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_factura = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        lab_pretotal = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbo_flete = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cbo_desc = new javax.swing.JComboBox();
        spin_vencimiento = new javax.swing.JSpinner();
        spin_credito = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        ch_mostrar = new javax.swing.JCheckBox();
        jRadioButton1 = new javax.swing.JRadioButton();
        btn_cancelar1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lab_iva = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lab_subtotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lab_excento = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lab_ivapor = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lab_valortotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lab_descuento = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        ventana_prod.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ventana_prod.setTitle("AGIF");
        ventana_prod.setAlwaysOnTop(true);
        ventana_prod.setMinimumSize(new java.awt.Dimension(755, 300));
        ventana_prod.setModal(true);
        ventana_prod.setResizable(false);
        ventana_prod.setType(java.awt.Window.Type.UTILITY);
        ventana_prod.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                ventana_prodWindowOpened(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Producto"));
        jPanel8.setToolTipText("Gestionar Productos");
        jPanel8.setAutoscrolls(true);
        jPanel8.setMinimumSize(new java.awt.Dimension(920, 370));
        jPanel8.setOpaque(false);

        jPanel9.setOpaque(false);

        btn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo1.png"))); // NOI18N
        btn_aceptar.setText("Aceptar");
        btn_aceptar.setToolTipText("Cargar Datos de Cliente");
        btn_aceptar.setBorder(null);
        btn_aceptar.setBorderPainted(false);
        btn_aceptar.setContentAreaFilled(false);
        btn_aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_aceptar.setEnabled(false);
        btn_aceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_aceptar.setIconTextGap(-3);
        btn_aceptar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_aceptar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_aceptar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo2.png"))); // NOI18N
        btn_aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_nuevo3.png"))); // NOI18N
        btn_aceptar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_aceptar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setToolTipText("Cerrar Modulo");
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setAutoscrolls(true);
        jPanel10.setOpaque(false);

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

        btn_mostrar_todo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo.setText("Mostrar Todo");
        btn_mostrar_todo.setToolTipText("Restablecer Tabla");
        btn_mostrar_todo.setBorder(null);
        btn_mostrar_todo.setBorderPainted(false);
        btn_mostrar_todo.setContentAreaFilled(false);
        btn_mostrar_todo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mostrar_todo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
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

        jScrollPane2.setOpaque(false);

        tabla_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_producto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla_producto.setAutoscrolls(false);
        tabla_producto.setOpaque(false);
        tabla_producto.getTableHeader().setResizingAllowed(false);
        tabla_producto.getTableHeader().setReorderingAllowed(false);
        tabla_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_productoFocusLost(evt);
            }
        });
        tabla_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_productoMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_producto);

        jLabel13.setText("Cantidad :");

        txt_cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cantKeyReleased(evt);
            }
        });

        buttonGroup2.add(rad_mercado);
        rad_mercado.setText("Mercado");
        rad_mercado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rad_mercadoItemStateChanged(evt);
            }
        });

        buttonGroup2.add(rad_normal);
        rad_normal.setSelected(true);
        rad_normal.setText("Normal");
        rad_normal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rad_normalItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_mostrar_todo)
                            .addGap(18, 18, 18)
                            .addComponent(rad_normal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rad_mercado))))
                .addGap(1, 1, 1))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mostrar_todo)
                    .addComponent(rad_mercado)
                    .addComponent(rad_normal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout ventana_prodLayout = new javax.swing.GroupLayout(ventana_prod.getContentPane());
        ventana_prod.getContentPane().setLayout(ventana_prodLayout);
        ventana_prodLayout.setHorizontalGroup(
            ventana_prodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_prodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 735, Short.MAX_VALUE)
                .addContainerGap())
        );
        ventana_prodLayout.setVerticalGroup(
            ventana_prodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventana_prodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotizar");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(910, 690));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jPanel3.setOpaque(false);

        jLabel3.setText("RIF :");

        jLabel4.setText("Nombre :");

        btn_buscar_clie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/buscar_empleado.png"))); // NOI18N
        btn_buscar_clie.setText("Buscar Cliente");
        btn_buscar_clie.setToolTipText("Buscar Vendedor");
        btn_buscar_clie.setBorder(null);
        btn_buscar_clie.setBorderPainted(false);
        btn_buscar_clie.setContentAreaFilled(false);
        btn_buscar_clie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_buscar_clie.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/buscar_empleado.png"))); // NOI18N
        btn_buscar_clie.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/buscar_empleado.png"))); // NOI18N
        btn_buscar_clie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_buscar_clieMouseReleased(evt);
            }
        });
        btn_buscar_clie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_clieActionPerformed(evt);
            }
        });

        jLabel5.setText("Dirección :");

        jLabel16.setText("Teléfono:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_buscar_clie))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(txt_nombre_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_clie, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cotización"));
        jPanel4.setOpaque(false);

        jLabel6.setText("N°:");

        jLabel7.setText("Fecha :");

        lab_numfact.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lab_fecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_numfact, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(lab_numfact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));
        jPanel5.setOpaque(false);

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(498, 35));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_agregar_prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/agregar_prod.png"))); // NOI18N
        btn_agregar_prod.setToolTipText("Agregar Nuevo Producto");
        btn_agregar_prod.setBorder(null);
        btn_agregar_prod.setBorderPainted(false);
        btn_agregar_prod.setContentAreaFilled(false);
        btn_agregar_prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agregar_prod.setEnabled(false);
        btn_agregar_prod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/agregar_prod2.png"))); // NOI18N
        btn_agregar_prod.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/agregar_prod.png"))); // NOI18N
        btn_agregar_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_agregar_prodMouseReleased(evt);
            }
        });
        btn_agregar_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_prodActionPerformed(evt);
            }
        });
        jPanel6.add(btn_agregar_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 45));

        btn_remover_prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/remover_prod.png"))); // NOI18N
        btn_remover_prod.setToolTipText("Remover Producto");
        btn_remover_prod.setBorder(null);
        btn_remover_prod.setBorderPainted(false);
        btn_remover_prod.setContentAreaFilled(false);
        btn_remover_prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_remover_prod.setEnabled(false);
        btn_remover_prod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/remover_prod2.png"))); // NOI18N
        btn_remover_prod.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/remover_prod.png"))); // NOI18N
        btn_remover_prod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_remover_prodMouseReleased(evt);
            }
        });
        btn_remover_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remover_prodActionPerformed(evt);
            }
        });
        jPanel6.add(btn_remover_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 0, -1, 45));

        btn_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido1.png"))); // NOI18N
        btn_imprimir.setToolTipText("Cotizar");
        btn_imprimir.setBorder(null);
        btn_imprimir.setBorderPainted(false);
        btn_imprimir.setContentAreaFilled(false);
        btn_imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_imprimir.setIconTextGap(-6);
        btn_imprimir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido1.png"))); // NOI18N
        btn_imprimir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido2.png"))); // NOI18N
        btn_imprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });
        jPanel6.add(btn_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, -1, -1));

        btn_cotizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido1.png"))); // NOI18N
        btn_cotizar.setToolTipText("Cotizar");
        btn_cotizar.setBorder(null);
        btn_cotizar.setBorderPainted(false);
        btn_cotizar.setContentAreaFilled(false);
        btn_cotizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cotizar.setEnabled(false);
        btn_cotizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cotizar.setIconTextGap(-6);
        btn_cotizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido1.png"))); // NOI18N
        btn_cotizar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/btn_pedido2.png"))); // NOI18N
        btn_cotizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cotizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cotizarActionPerformed(evt);
            }
        });
        jPanel6.add(btn_cotizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, -1, -1));

        lab_items.setText("0");
        jPanel6.add(lab_items, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 20, 40));

        jLabel23.setText("Items: ");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 40));

        tabla_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción", "Cantidad", "Precio Unitario", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_factura.setOpaque(false);
        tabla_factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_facturaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_factura);
        if (tabla_factura.getColumnModel().getColumnCount() > 0) {
            tabla_factura.getColumnModel().getColumn(0).setResizable(false);
            tabla_factura.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla_factura.getColumnModel().getColumn(1).setResizable(false);
            tabla_factura.getColumnModel().getColumn(1).setPreferredWidth(300);
            tabla_factura.getColumnModel().getColumn(2).setResizable(false);
            tabla_factura.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabla_factura.getColumnModel().getColumn(3).setResizable(false);
            tabla_factura.getColumnModel().getColumn(3).setPreferredWidth(80);
            tabla_factura.getColumnModel().getColumn(4).setResizable(false);
            tabla_factura.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        jLabel17.setText("Pre-Total Bs. :");

        lab_pretotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_pretotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lab_pretotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar2.png"))); // NOI18N
        btn_limpiar.setToolTipText("Limpiar Formularios");
        btn_limpiar.setBorder(null);
        btn_limpiar.setBorderPainted(false);
        btn_limpiar.setContentAreaFilled(false);
        btn_limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_limpiar.setIconTextGap(-6);
        btn_limpiar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar2.png"))); // NOI18N
        btn_limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_limpiar3.png"))); // NOI18N
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Condiciones"));
        jPanel11.setOpaque(false);

        jLabel2.setText("Día/s");

        cbo_flete.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empresa", "Cliente" }));
        cbo_flete.setSelectedIndex(1);
        cbo_flete.setOpaque(false);

        jLabel8.setText("Día/s");

        cbo_desc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0%", "2%", "5%", "10%" }));
        cbo_desc.setOpaque(false);

        spin_vencimiento.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spin_vencimiento.setOpaque(false);

        spin_credito.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(-1), null, Integer.valueOf(1)));
        spin_credito.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Descuento :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Vencimiento :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Flete :");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Crédito :");

        ch_mostrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ch_mostrar.setSelected(true);
        ch_mostrar.setText("Mostrar Total");
        ch_mostrar.setOpaque(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spin_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_flete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spin_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_mostrar)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbo_flete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(cbo_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_mostrar))
                .addGap(5, 5, 5))
        );

        jRadioButton1.setText("jRadioButton1");

        btn_cancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar2.png"))); // NOI18N
        btn_cancelar1.setToolTipText("Cancelar Cotización");
        btn_cancelar1.setAlignmentY(0.0F);
        btn_cancelar1.setBorder(null);
        btn_cancelar1.setBorderPainted(false);
        btn_cancelar1.setContentAreaFilled(false);
        btn_cancelar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cancelar1.setIconTextGap(-5);
        btn_cancelar1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar2.png"))); // NOI18N
        btn_cancelar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar3.png"))); // NOI18N
        btn_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 11, 890, 640);

        jPanel7.setOpaque(false);

        jLabel9.setText("Sub-Total Bs. :");

        lab_iva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel10.setText("I.V.A. ");

        lab_subtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel11.setText("Total Exento :");

        lab_excento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel12.setText("Valor Total Bs.:");

        lab_ivapor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lab_ivapor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel14.setText("% Bs.:");

        lab_valortotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel15.setText("Descuento:");

        lab_descuento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_ivapor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lab_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_excento, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_valortotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lab_descuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_iva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_excento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_valortotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_ivapor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel7);
        jPanel7.setBounds(10, 650, 841, 30);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar1200x900.png"))); // NOI18N
        getContentPane().add(jLabel22);
        jLabel22.setBounds(0, 0, 980, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
    accbtnaceptar();
    this.btn_aceptar.setEnabled(false);
    this.txt_cant.setText(null);
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        ventana_prod.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
       cargarproductos();
       btn_aceptar.setEnabled(false);
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed

    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
       cargarproductos();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseClicked
        this.txt_cant.setText(null);
        this.txt_cant.grabFocus();         // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoMouseClicked

    private void tabla_productoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseReleased
        validarBotones();   // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoMouseReleased

    private void tabla_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_productoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoFocusLost

    private void txt_cantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantKeyPressed
 validarBotones(); 
 int filasel;
        try
        {            
            filasel = this.tabla_producto.getSelectedRow();

            if (filasel!=-1 && !this.txt_cant.getText().isEmpty() && evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                  accbtnaceptar();  
                  this.txt_cant.setText(null);
            }else
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
               ventana_prod.dispose(); 
            }
        }
        catch (Exception e){
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_cantKeyPressed

    private void txt_cantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantKeyReleased
 validarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantKeyReleased

    private void ventana_prodWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ventana_prodWindowOpened
cargarproductos();        // TODO add your handling code here:
    }//GEN-LAST:event_ventana_prodWindowOpened

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
limpiar();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void tabla_facturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_facturaMouseReleased
        validarBotones2();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_facturaMouseReleased

    private void btn_cotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cotizarActionPerformed
     Cotizar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cotizarActionPerformed

    private void btn_remover_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remover_prodActionPerformed
        remover();
        validarBotones2();// TODO add your handling code here:
    }//GEN-LAST:event_btn_remover_prodActionPerformed

    private void btn_remover_prodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remover_prodMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_remover_prodMouseReleased

    private void btn_agregar_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_prodActionPerformed
        ventana_prod.setLocationRelativeTo(this);
        ventana_prod.setVisible(true);
        cargarproductos();
    }//GEN-LAST:event_btn_agregar_prodActionPerformed

    private void btn_agregar_prodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregar_prodMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregar_prodMouseReleased

    private void btn_buscar_clieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_clieActionPerformed
        Ventana_clientes_cl vc = new Ventana_clientes_cl();
        vc.setVisible(true);
        validarcamposllenos();// TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_clieActionPerformed

    private void btn_buscar_clieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_clieMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_clieMouseReleased

    private void btn_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar1ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelar1ActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed
imprimir();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void rad_normalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_normalItemStateChanged
cargarproductos();
    this.txt_cant.setText(null);
    this.btn_aceptar.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_rad_normalItemStateChanged

    private void rad_mercadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_mercadoItemStateChanged
cargarproductos();
    this.txt_cant.setText(null);
    this.btn_aceptar.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_rad_mercadoItemStateChanged

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
            java.util.logging.Logger.getLogger(Ventana_Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Cotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Cotizacion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_aceptar;
    public static javax.swing.JButton btn_agregar_prod;
    public javax.swing.JButton btn_buscar_clie;
    javax.swing.JButton btn_cancelar1;
    public javax.swing.JButton btn_cerrar;
    javax.swing.JButton btn_cotizar;
    javax.swing.JButton btn_imprimir;
    javax.swing.JButton btn_limpiar;
    public javax.swing.JButton btn_mostrar_todo;
    public static javax.swing.JButton btn_remover_prod;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JComboBox cbo_desc;
    public javax.swing.JComboBox cbo_flete;
    public javax.swing.JCheckBox ch_mostrar;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    public javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lab_descuento;
    public javax.swing.JLabel lab_excento;
    public javax.swing.JLabel lab_fecha;
    public javax.swing.JLabel lab_items;
    public javax.swing.JLabel lab_iva;
    public javax.swing.JLabel lab_ivapor;
    public javax.swing.JLabel lab_numfact;
    public javax.swing.JLabel lab_pretotal;
    public javax.swing.JLabel lab_subtotal;
    public javax.swing.JLabel lab_valortotal;
    public javax.swing.JRadioButton rad_mercado;
    public javax.swing.JRadioButton rad_normal;
    public javax.swing.JSpinner spin_credito;
    public javax.swing.JSpinner spin_vencimiento;
    public javax.swing.JTable tabla_factura;
    public static javax.swing.JTable tabla_producto;
    public static javax.swing.JTextField txt_buscar;
    public javax.swing.JTextField txt_cant;
    public static javax.swing.JTextField txt_direccion;
    public static javax.swing.JTextField txt_nombre_cliente;
    public static javax.swing.JTextField txt_rif;
    public static javax.swing.JTextField txt_telefono;
    public javax.swing.JDialog ventana_prod;
    // End of variables declaration//GEN-END:variables
}
