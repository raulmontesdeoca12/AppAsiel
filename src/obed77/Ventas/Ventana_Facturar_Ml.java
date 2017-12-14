
package obed77.Ventas;


import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_descontar;
import BaseDeDatos.Ventas.clase_factura;
import BaseDeDatos.Ventas.clase_factura_det;
import BaseDeDatos.Ventas.clase_ml;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import obed77.Correo.Enviar_Correo_Facturado;
import obed77.Correo.HiloFacturado;
import static obed77.Ventas.Ventas_ml.tabla_prodml;

public class Ventana_Facturar_Ml extends javax.swing.JFrame {
int exc,exp, ivapor,pedido;
DefaultTableModel m;DefaultTableModel m2;
Properties pp = new Properties();
DefaultTableModel mp;
static double total;
double sub_total;
double ivam;
double excento;
int cont_cant,idml,ic=0;
int cod;
String dia,mes,anio;
String nid, correo,prod,precio;
String fecha,estatus;
Shape shape = null;
private Timer timer;
int idfact;
    public Ventana_Facturar_Ml() {
        initComponents();
        shape = new RoundRectangle2D.Float(0,0,this.getWidth(),this.getHeight(),20,20);
        AWTUtilities.setWindowShape(this, shape);
        total=0;
        sub_total=0;
        ivam=0;
        excento=0;
        cont_cant=0;
        
        deshabilitarEdicion();
        limpiar();
        cargarlabels();
        cargar();
    }
 @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));


        return retValue;
    }
     
    void cargar()
    {
        idml= Integer.parseInt((String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 0));
        String ci =(String)Ventas_ml.tabla_ml.getValueAt(Ventas_ml.tabla_ml.getSelectedRow(), 2);
        
              
        cargarcliente(ci);
        
        llenarprimer(idml);
      
        

    }
    void cargarproducto()
    {
       
       
    }
    void habilitar()
    {
        this.btn_facturar.setEnabled(true);
        
        this.cbo_formadp.setEnabled(true);
        this.rad_contado.setEnabled(true);
        this.rad_dia.setEnabled(true);
        this.btn_facturar.setEnabled(true);
    }
    
    
    void cargarcliente(String ci)
    {
       String nombre = null, telefono = null, direccion = null;
        try
      {
       
        String sql = "SELECT * FROM clientes WHERE idclientes ='"+ci+"'";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        if(rs.last())
        {
            nombre=rs.getString("razon_social_cli");
            telefono=rs.getString("telefono_cli");
            direccion=rs.getString("direccion_cli");
            correo= rs.getString("correo_cli");
            
            
        }
        txt_rif.setText(ci);
        txt_nombre_cliente.setText(nombre);
        txt_telefono.setText(telefono);
        txt_direccion.setText(direccion);
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
       }
       
     //llenar los label
      
    }
     
    void cargarlabels()
    {
        //cargar numero de pedido
        int id, pid;
        
        try
      {
       
        String sql = "SELECT * FROM factura";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        if(rs.last())
        {
            id=rs.getInt("idfactura");
            pid=id+1;
            nid=Integer.toString(pid);
            System.out.println(""+nid);
            
            
        }else{
            nid="0"; 
        }
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
       }
      //cargar fecha
       //cargar fecha
     Calendar cal = Calendar.getInstance();
        int iAnio, iDia;

        iAnio = cal.get(Calendar.YEAR);
        int mesi = cal.get(Calendar.MONTH);
        int mest = mesi + 1;

        iDia = cal.get(Calendar.DATE);

        Formatter fmtm = new Formatter();
        fmtm.format("%02d", mest);
        mes = fmtm + "";
        Formatter fmtd = new Formatter();
        fmtd.format("%02d", iDia);
        dia = fmtd + "";
        anio = String.valueOf(iAnio);
        
        fecha = anio + "-" + mes + "-" + dia;
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
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
       }
       
     //llenar los label
      
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
      this.lab_numfact.setText(null);
      this.lab_fecha.setText(null);
      this.lab_excento.setText(null);
      this.lab_subtotal.setText(null);
      this.lab_iva.setText(null);
      this.lab_valortotal.setText(null);
      Ventana_Facturar_Ml.txt_rif.setText(null);
      Ventana_Facturar_Ml.txt_nombre_cliente.setText(null);
      Ventana_Facturar_Ml.txt_telefono.setText(null);
      Ventana_Facturar_Ml.txt_direccion.setText(null);
      this.lab_son.setText(null);
      limpiaTabla();
       
  }

  
 void guardarEstado(){
    String estado = "Facturado";
    
    
    try{
        clase_factura ml = new clase_factura(cod, idml, estado);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        
        r=in.InsertarFactura(ml);
        
        if (r==false)
        {
            int op= JOptionPane.showConfirmDialog(this, "Registro Exitoso ¿Desea enviar el correo?", "Guardar", JOptionPane.YES_NO_OPTION);
            if (op==0)
              {
                  enviarCorreo();
                  
              }else
            {
                dispose();
            }
        }else
        {
            dispose();
        }
        
    }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Agregar_Pago.class.getName()).log(Level.SEVERE,null,ex);
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
                fis = new FileInputStream(dir+"/Obed77/PDF/Facturas/Factura_"+cod+".pdf");
                String sFichero = dir+"/Obed77/PDF/Facturas/Factura_"+cod+".pdf";
                File fichero = new File(sFichero);
                lon=(int)fichero.length();
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                String sql ="INSERT into documento_fac (iddocumento_fac,fac) values (?,?);";
                PreparedStatement ps;
                ps = con.prepareStatement(sql);
                ps.setBlob(2, fis, lon);
                ps.setInt(1, cod);
                rsu=ps.executeUpdate();
                if (rsu==1)
                {
                    JOptionPane.showMessageDialog(this,"Documento Guardado en Base de Datos","Guardar",1,null);
                    guardarEstado();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
        Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
    }
  void llenarprimer(int id)
  {
      String  codi,desc, prec = null, imp,cant;
      
      
      try
      {
       
        String sql = "SELECT * FROM detalles_ml INNER JOIN producto ON producto.idproducto= detalles_ml.fkproducto WHERE detalles_ml.fkventas_ml ='"+id+"'";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next())
        {
            codi=rs.getString("detalles_ml.fkproducto");
            cant=rs.getString("detalles_ml.cantidad");
            desc=rs.getString("producto.descripcion_prod");
            prec=rs.getString("producto.precio_si_ml");
            float precf=rs.getFloat("producto.precio_si_ml");
            int canti = rs.getInt("detalles_ml.cantidad");
            double x;
            float impf = precf * canti;
            x=(Math.rint(impf*100)/100);
            imp=""+x;
            
            sub_total=sub_total+x;
            
            m2=(DefaultTableModel)tabla_factura.getModel();
            
            String filaelemento[] = {codi,desc,cant,prec,imp};
            m.addRow(filaelemento);
            
            
        }
       int cann=0;
       int cans=0;
       boolean b=false;
       
       ivam=(sub_total*ivapor)/100;
       total=(sub_total+ivam); 
       double subtotalr, ivar, excentor, valortotalr;
       subtotalr= (Math.rint(sub_total*100)/100);
       ivar=(Math.rint(ivam*100)/100);
       excentor= (Math.rint(excento*100)/100);
       valortotalr= (Math.rint(total*100)/100);
       this.lab_subtotal.setText(subtotalr+"");
       this.lab_iva.setText(ivar+"");
       this.lab_excento.setText(excentor+"");
       this.lab_valortotal.setText(valortotalr+"");
       llenarson();
       
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
       }
      
  }
 
  
  void llenarson(){
      
      
      n2t numero;
      int parte;
      String partd;
      String res;
      String rescom;
      String nums=lab_valortotal.getText();
      float num=Float.parseFloat(nums);
      if (nums!="0" || nums != "")
      {
      
      String [] arr=nums.split("\\.");
                partd=arr[1];
                parte=(int)num;
       numero = new n2t(parte);
       res = numero.convertirLetras(parte);
       rescom=res+" Con "+partd+"/100";
       this.lab_son.setText(rescom);
      }else
      {
          lab_son.setText("");
      }
      
       
  }
  
  
  
  
 
 void validarrad()
 {
     if(rad_contado.isSelected())
     {
         this.txt_vencimiento.setEnabled(false);
     }else
     {
         this.txt_vencimiento.setEnabled(true);
     }
 }
 
public void enviarCorreo()
  {        
    
   String p;
    Enviar_Correo_Facturado c = new Enviar_Correo_Facturado(correo);
    c.AsignarDatos();
    this.btn_cancelar.setEnabled(false);
    this.btn_facturar.setEnabled(false);
    this.btn_facturar_imprimir.setEnabled(false);
    HiloFacturado hilo = new HiloFacturado("fm");
    hilo.start();
    
  }
  void facturar()
   {
      
       try {
                int fc=tabla_factura.getRowCount();
                System.out.println("Cuenta Columnas");
                if(fc==0)
                {
                    JOptionPane.showMessageDialog(this,"Error...\n La Factura no posee productos","Atención",1,null);
                }else{
                int ad = 0, ali;
                String venta = "Mercadolibre";
                String ciudad,forma_pago,vencimiento,son,cliente;
                float sub,iva,ex,tot;
                cod=Integer.parseInt(this.lab_numfact.getText());
                ciudad=this.txt_lugar.getText();
                forma_pago=this.cbo_formadp.getSelectedItem().toString();
                if(rad_contado.isSelected())
                {
                    vencimiento="Contado";
                }else
                    
                    {
                        vencimiento=this.txt_vencimiento.getText()+" Dias";
                    }            
                    System.out.println("Pasa 2");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                Date fec = sdf.parse(fecha);
                cal.setTime(fec);
                cal.add( Calendar.DATE, ad );
                    System.out.println("Pasa 3");
                sub=Float.parseFloat(this.lab_subtotal.getText());
                iva=Float.parseFloat(this.lab_iva.getText());
                ex=Float.parseFloat(this.lab_excento.getText());
                tot=Float.parseFloat(this.lab_valortotal.getText());
                ali=Integer.parseInt(this.lab_ivapor.getText());
                son=this.lab_son.getText();
                    System.out.println("Pasa 4");
                if (rad_contado.isSelected())
                {
                    this.txt_vencimiento.setEnabled(false);
                    estatus="Cobrada";
                }else
                   if(rad_dia.isSelected())
                   {
                       estatus="Por Cobrar";
                   }
                    System.out.println("Pasa 5");
                cliente=Ventana_Facturar_Ml.txt_rif.getText();
                try {
                  String d =dia;
                  String me = mes;
                  String a = anio;
                    System.out.println("Pasa 6");
                 clase_factura fac = new clase_factura(cod,ciudad,fecha,forma_pago,vencimiento,son,d,me,a,estatus,cliente,sub,iva,ex,tot,venta,ali);
                    System.out.println("Pasa 7");
                 Gest_ventas in = new Gest_ventas();
                    System.out.println("Pasa 8");
                 boolean r;
                 r=in.InsertarFac(fac);
                    System.out.println("Pasa 9");
                 if(r==false)
                 {
                     guardar();
                 }
                
                } catch(SQLException e)
                     {
                         JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
                         System.out.println("Error:"+e);
                     
                     }
                  catch(ClassNotFoundException e)
                     {
                         Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE,null,ex);
                     }
                }
                }
         catch(ParseException ex)
            {
                Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE,null,ex);
            }
           
                
        
       
   }
  void guardar()
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
                clase_factura_det dped = new clase_factura_det(idprod, cant_prod,idfact,p_unitario,total_prod);
                
                
                p=in.InsertarDet(dped);
               
                
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
                JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Factura.jasper");
                Map par = new HashMap();
                par.clear(); 
                par.put("cod_fact", idfact);
                JasperPrint jPrint = JasperFillManager.fillReport(Report, par , con);
                JasperPrintManager.printReport(jPrint, true);
                JasperExportManager.exportReportToPdfFile( jPrint, dir+"/Obed77/PDF/Facturas/Factura_"+idfacts+".pdf");
                JasperReport Report2 = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/FacturaNV.jasper");
                
                JasperPrint jPrint2 = JasperFillManager.fillReport(Report2, par , con);
                JasperExportManager.exportReportToPdfFile( jPrint2, dir+"/Obed77/PDF/Facturasn/Factura_NoValida_"+idfacts+".pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
                }
                int op= JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Factura?", "Imprimir", JOptionPane.YES_NO_OPTION);
                if (op==0)
              {
                  guardarbdd();
                  
              }else
                    
                {
                    this.txt_lugar.setEnabled(false);
                    this.cbo_formadp.setEnabled(false);
                    this.rad_contado.setEnabled(false);
                    this.rad_dia.setEnabled(false);
                    this.txt_vencimiento.setEnabled(false);
                    this.btn_facturar.setVisible(false);
                    this.btn_facturar_imprimir.setVisible(true);
                    this.btn_facturar_imprimir.setEnabled(true);
                    this.tabla_factura.setEnabled(false);
                }
            }
             
        }
    
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
                System.out.println("Error en Guardar");
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE,null,ex);
            }
      
  }
  void validarcerrar()
  {
      if(ch_evt.isSelected())
      {
          dispose();
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
                JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Factura.jasper");
                Map par = new HashMap();
                par.clear(); 
                par.put("cod_fact", idfact);
                JasperPrint jPrint = JasperFillManager.fillReport(Report, par , con);
                JasperPrintManager.printReport(jPrint, true);
                JasperExportManager.exportReportToPdfFile( jPrint,dir+"/Obed77/PDF/Facturas/Factura_"+idfacts+".pdf");
                JasperReport Report2 = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/FacturaNV.jasper");
                
                JasperPrint jPrint2 = JasperFillManager.fillReport(Report2, par , con);
                JasperExportManager.exportReportToPdfFile( jPrint2, dir+"/Obed77/PDF/Facturasn/Factura_NoValida_"+idfacts+".pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
        Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(Level.SEVERE, null, ex);
    }
                int op= JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Factura?", "Imprimir", JOptionPane.YES_NO_OPTION);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        ch_evt = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_rif = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre_cliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lab_numfact = new javax.swing.JLabel();
        lab_fecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_lugar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_facturar = new javax.swing.JButton();
        btn_facturar_imprimir = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_factura = new javax.swing.JTable();
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
        btn_cancelar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbo_formadp = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        rad_contado = new javax.swing.JRadioButton();
        rad_dia = new javax.swing.JRadioButton();
        txt_vencimiento = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lab_son = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        ch_evt.setText("jCheckBox1");
        ch_evt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_evtItemStateChanged(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturar");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(830, 710));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setOpaque(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        jPanel3.setOpaque(false);

        jLabel3.setText("RIF :");

        jLabel4.setText("Nombre :");

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
                        .addComponent(txt_direccion))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_rif, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
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
                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Factura"));
        jPanel4.setOpaque(false);

        jLabel6.setText("N°:");

        jLabel7.setText("Fecha :");

        lab_numfact.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lab_fecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel15.setText("Ciudad :");

        txt_lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lugarActionPerformed(evt);
            }
        });
        txt_lugar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_lugarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_lugarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_lugarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_numfact, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_numfact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txt_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));
        jPanel5.setOpaque(false);

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(498, 35));
        jPanel6.setLayout(null);

        btn_facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar.setToolTipText("Facturar");
        btn_facturar.setBorder(null);
        btn_facturar.setBorderPainted(false);
        btn_facturar.setContentAreaFilled(false);
        btn_facturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_facturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_facturar.setIconTextGap(-6);
        btn_facturar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido2.png"))); // NOI18N
        btn_facturar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_facturar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturarActionPerformed(evt);
            }
        });
        jPanel6.add(btn_facturar);
        btn_facturar.setBounds(690, 0, 45, 45);

        btn_facturar_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar_imprimir.setToolTipText("Facturar");
        btn_facturar_imprimir.setBorder(null);
        btn_facturar_imprimir.setBorderPainted(false);
        btn_facturar_imprimir.setContentAreaFilled(false);
        btn_facturar_imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_facturar_imprimir.setEnabled(false);
        btn_facturar_imprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_facturar_imprimir.setIconTextGap(-6);
        btn_facturar_imprimir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido2.png"))); // NOI18N
        btn_facturar_imprimir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar_imprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_facturar_imprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_facturar_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturar_imprimirActionPerformed(evt);
            }
        });
        jPanel6.add(btn_facturar_imprimir);
        btn_facturar_imprimir.setBounds(690, 0, 45, 45);

        jLabel20.setText("VENTA POR MERCADOLIBRE ");
        jPanel6.add(jLabel20);
        jLabel20.setBounds(300, 10, 290, 20);

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
        tabla_factura.getTableHeader().setReorderingAllowed(false);
        tabla_factura.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabla_facturaComponentAdded(evt);
            }
        });
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

        jPanel7.setOpaque(false);

        jLabel9.setText("Sub-Total Bs. :");

        lab_iva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel10.setText("I.V.A. ");

        lab_subtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel11.setText("Total Exento :");

        lab_excento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel12.setText("Valor Total Bs.:");

        lab_ivapor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel14.setText("% Bs.:");

        lab_valortotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(lab_ivapor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_excento, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_valortotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lab_subtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_iva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lab_ivapor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_excento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_valortotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/cancelar_pedido.png"))); // NOI18N
        btn_cancelar.setToolTipText("Cancelar Factura");
        btn_cancelar.setBorder(null);
        btn_cancelar.setBorderPainted(false);
        btn_cancelar.setContentAreaFilled(false);
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cancelar.setIconTextGap(-6);
        btn_cancelar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/cancelar_pedido.png"))); // NOI18N
        btn_cancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/cancelar_pedido2.png"))); // NOI18N
        btn_cancelar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Pago"));
        jPanel11.setOpaque(false);

        jLabel17.setText("Forma de Pago : ");

        cbo_formadp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Depósito", "Transferencia", "Cheque", "Contado", "Efectivo", "Otros" }));
        cbo_formadp.setOpaque(false);

        jLabel18.setText("Vencimiento:");

        buttonGroup1.add(rad_contado);
        rad_contado.setSelected(true);
        rad_contado.setText("Contado");
        rad_contado.setOpaque(false);
        rad_contado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rad_contadoStateChanged(evt);
            }
        });
        rad_contado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rad_contadoMouseClicked(evt);
            }
        });

        buttonGroup1.add(rad_dia);
        rad_dia.setText("Días :");
        rad_dia.setOpaque(false);
        rad_dia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rad_diaStateChanged(evt);
            }
        });

        txt_vencimiento.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_formadp, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rad_contado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rad_dia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbo_formadp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rad_dia)
                    .addComponent(rad_contado)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jLabel19.setText("Son: ");

        jRadioButton1.setText("jRadioButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_son, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 68, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lab_son, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 11, 810, 690);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar1200x900.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 890, 730);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
this.dispose();   
     
    // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void tabla_facturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_facturaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_facturaMouseReleased

    private void btn_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturarActionPerformed
 if(txt_lugar.getText().isEmpty())
    {
        JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
    }else
    {
       facturar(); 
       Ventas_ml.cargar();
    }
// TODO add your handling code here:
    }//GEN-LAST:event_btn_facturarActionPerformed

    private void txt_lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarActionPerformed

    private void txt_lugarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyPressed
              // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyPressed

    private void txt_lugarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyReleased
               // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyReleased

    private void rad_contadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rad_contadoMouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_rad_contadoMouseClicked

    private void rad_contadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rad_contadoStateChanged
validarrad();      // TODO add your handling code here:
    }//GEN-LAST:event_rad_contadoStateChanged

    private void rad_diaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rad_diaStateChanged
validarrad();        // TODO add your handling code here:
    }//GEN-LAST:event_rad_diaStateChanged

    private void btn_facturar_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturar_imprimirActionPerformed
 imprimir();       // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturar_imprimirActionPerformed

    private void txt_lugarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyTyped
 txt_lugar = (JTextField) evt.getComponent();
        String cadena = txt_lugar.getText();
        char[] caracteres = cadena.toCharArray(); 
        
        if (cadena.length()!= 0)
        {
             
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < cadena.length()-1; i++)
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-')
     
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            txt_lugar.setText(String.valueOf(caracteres));
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyTyped

    private void tabla_facturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_facturaComponentAdded
  // TODO add your handling code here:
    }//GEN-LAST:event_tabla_facturaComponentAdded

    private void ch_evtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_evtItemStateChanged
    validarcerrar();        // TODO add your handling code here:
    }//GEN-LAST:event_ch_evtItemStateChanged

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
            java.util.logging.Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar_Ml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Facturar_Ml().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_cancelar;
    javax.swing.JButton btn_facturar;
    javax.swing.JButton btn_facturar_imprimir;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JComboBox cbo_formadp;
    public static javax.swing.JCheckBox ch_evt;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JRadioButton jRadioButton1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lab_excento;
    public javax.swing.JLabel lab_fecha;
    public javax.swing.JLabel lab_iva;
    public javax.swing.JLabel lab_ivapor;
    public javax.swing.JLabel lab_numfact;
    public javax.swing.JLabel lab_son;
    public javax.swing.JLabel lab_subtotal;
    public javax.swing.JLabel lab_valortotal;
    public javax.swing.JRadioButton rad_contado;
    public javax.swing.JRadioButton rad_dia;
    public javax.swing.JTable tabla_factura;
    public static javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_lugar;
    public static javax.swing.JTextField txt_nombre_cliente;
    public static javax.swing.JTextField txt_rif;
    public static javax.swing.JTextField txt_telefono;
    public javax.swing.JTextField txt_vencimiento;
    // End of variables declaration//GEN-END:variables
}
