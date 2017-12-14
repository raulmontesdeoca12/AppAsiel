/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Ventas;

import obed77.clientes.*;
import BaseDeDatos.Clientes.ClaseEACliente;
import BaseDeDatos.Clientes.Gest_cliente;
import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Clase_det_ml;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_descontar;
import BaseDeDatos.Ventas.clase_ml;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import obed77.Principal;
import obed77.RenderStock1;

/**
 *
 * @author user
 */
public class Ventas_ml extends javax.swing.JPanel {
static DefaultTableModel model;
DefaultTableModel m;
String globalprod;
    /**
     * Creates new form Productos
     */
    public Ventas_ml() {
        initComponents();
        cargar();
        limpiarProductos();
        RenderStock1 ft= new RenderStock1(6);
        tabla_ml.setDefaultRenderer(Object.class, ft);
    }
    
        public static void cargar(){
       
        try{
        String [] titulos={"ID","Fecha","RIF/CI","Cliente Factura","Nombre Mercadolibre","Teléfono","Estado","Factura","Ref. Cobro","Empresa","Ref. Envío","Fec. Envío","Correo Electrónico"};
        String [] registros= new String[13];
        String sql="";
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        model = new DefaultTableModel(null,titulos){public boolean isCellEditable(int row, int column) {
return false;
}};
        String filtro = cbo_filtro.getSelectedItem().toString();
       
            switch (filtro) {
                case "Filtrar Por":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes ORDER BY FIELD(ventas_ml.estado, 'Por Cobrar', 'Transfirio','Cobrado','Cobrado MP','Facturado','Enviado','Concretada','Cancelada'), ventas_ml.fecha DESC";
                    break;
                case "Por Cobrar":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes  WHERE ventas_ml.estado = 'Por Cobrar' ORDER BY ventas_ml.fecha DESC ";
                    break;
                case "Transfirio":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Transfirio' ORDER BY ventas_ml.fecha DESC ";
                    break;
                case "Cobrado":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Cobrado' OR ventas_ml.estado = 'Cobrado MP' ORDER BY ventas_ml.fecha DESC ";
                    break;
                    case "Facturado":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Facturado' ORDER BY ventas_ml.fecha DESC ";
                    break;
                case "Enviado":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Enviado' ORDER BY ventas_ml.fecha DESC ";
                    break;
                case "Concretada":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Concretada' ORDER BY ventas_ml.fecha DESC ";
                    break;
                case "Cancelada":
                    sql = "SELECT * FROM ventas_ml INNER JOIN clientes ON ventas_ml.fk_cliente = clientes.idclientes WHERE ventas_ml.estado = 'Cancelada' ORDER BY ventas_ml.fecha DESC ";
                    break;
                        
                        
            }
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){

            registros[0]=rs.getString("ventas_ml.idventas_ml");
            registros[1]=rs.getString("ventas_ml.fecha");
            registros[2]=rs.getString("ventas_ml.fk_cliente");
            registros[3]=rs.getString("clientes.razon_social_cli");
            registros[4]=rs.getString("ventas_ml.cliente");
            registros[5]=rs.getString("ventas_ml.telefono");
            registros[6]=rs.getString("ventas_ml.estado");
            registros[7]=rs.getString("ventas_ml.fk_factura");
            registros[8]=rs.getString("ventas_ml.referencia_cobro");
            registros[9]=rs.getString("ventas_ml.empresa");
            registros[10]=rs.getString("ventas_ml.referencia_envio");
            registros[11]=rs.getString("ventas_ml.fecha_envio");
            registros[12]=rs.getString("ventas_ml.correo");
            model.addRow(registros);
        }
        tabla_ml.setModel(model);
        tabla_ml.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tabla_ml.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_ml.getColumnModel().getColumn(1).setMinWidth(80);
        tabla_ml.getColumnModel().getColumn(2).setMinWidth(90);
        tabla_ml.getColumnModel().getColumn(3).setMinWidth(150);
        tabla_ml.getColumnModel().getColumn(4).setMinWidth(150);
        tabla_ml.getColumnModel().getColumn(5).setMinWidth(100);
        tabla_ml.getColumnModel().getColumn(6).setMinWidth(85);
        tabla_ml.getColumnModel().getColumn(7).setMinWidth(70);
        tabla_ml.getColumnModel().getColumn(8).setMinWidth(120);
        tabla_ml.getColumnModel().getColumn(9).setMinWidth(80);
        tabla_ml.getColumnModel().getColumn(10).setMinWidth(120);
        tabla_ml.getColumnModel().getColumn(11).setMinWidth(95);
        tabla_ml.getColumnModel().getColumn(12).setMinWidth(300);
        tabla_ml.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tabla_ml.getColumnModel().getColumn(11).setCellRenderer(tcr);
        
        TableRowSorter modeloOrdenado = new TableRowSorter(model);
        tabla_ml.setRowSorter(modeloOrdenado);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar.getText()));
        tabla_ml.setRowSorter(modeloOrdenado);
    }catch(SQLException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      void desactivarBotones()
    {
        this.btn_modificar.setEnabled(false);
        this.btn_transferencia.setEnabled(false);
        this.btn_facturar.setEnabled(false);
        this.btn_agg_envio.setEnabled(false);
        this.btn_concretar.setEnabled(false);
        this.btn_cancelar.setEnabled(false);
        this.btn_crear_cliente.setEnabled(false);
        this.btn_enviar_correo.setEnabled(false);
        this.btn_aso_cliente.setEnabled(false);
        this.btn_agg.setEnabled(false);
        this.btn_sus.setEnabled(false);
    }
      void validarBotonFact()
    {
        int filasel;
        filasel = tabla_ml.getSelectedRow();
        String est;
        est = tabla_ml.getValueAt(filasel, 7).toString();
        String ci=tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 2).toString();

        if ((ci.equals("V-00000000-0") && filasel!=-1) || (!ci.equals("V-00000000-0") && ("Cobrado".equals(est) || "Cobrado MP".equals(est)) && filasel!=-1))
            {
                this.btn_facturar.setEnabled(false);
                
                
            }else
                if(filasel!=-1 && !ci.equals("V-00000000-0"))
            {
               this.btn_facturar.setEnabled(true);
            }
    }
    void validarBotones2()
    {
        int filasel;
        filasel = tabla_ml.getSelectedRow();
        String est;
        est = tabla_ml.getValueAt(filasel, 6).toString();
        String ci=tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 2).toString();

        if (ci.equals("V-00000000-0") && filasel!=-1)
            {
                this.btn_crear_cliente.setEnabled(true);
                this.btn_aso_cliente.setEnabled(true);
            }else
                if(filasel!=-1 && !ci.equals("V-00000000-0"))
            {
               this.btn_crear_cliente.setEnabled(false);
               this.btn_aso_cliente.setEnabled(false);
            }
    }
    void validarBotones()
    {
        
        int filasel;
        String est;
        try
        {            
            filasel = tabla_ml.getSelectedRow();
            est = tabla_ml.getValueAt(filasel, 6).toString();
            
            

            if ("Por Cobrar".equals(est) && filasel!=-1)
            {
                this.btn_modificar.setEnabled(true);
                this.btn_facturar.setEnabled(false);
                this.btn_agg_envio.setEnabled(false);
                this.btn_transferencia.setEnabled(true);
                this.btn_concretar.setEnabled(false);
                this.btn_cancelar.setEnabled(true);
                this.btn_enviar_correo.setEnabled(true);
                this.btn_agg.setEnabled(true);
                this.btn_cobrar.setEnabled(true);
                
            }else
            if("Transfirio".equals(est)&& filasel!=-1)
            {
                this.btn_modificar.setEnabled(true);
                this.btn_facturar.setEnabled(false);
                this.btn_agg_envio.setEnabled(false);
                this.btn_transferencia.setEnabled(false);
                this.btn_concretar.setEnabled(false);
                this.btn_cancelar.setEnabled(true);
                this.btn_enviar_correo.setEnabled(true);
                this.btn_agg.setEnabled(false);
                this.btn_cobrar.setEnabled(true);
            }else
            if("Cobrado".equals(est) || "Cobrado MP".equals(est) && filasel!=-1)
            {
                this.btn_modificar.setEnabled(true);
                this.btn_facturar.setEnabled(true);
                this.btn_agg_envio.setEnabled(false);
                this.btn_transferencia.setEnabled(false);
                this.btn_concretar.setEnabled(false);
                this.btn_cancelar.setEnabled(true);
                this.btn_enviar_correo.setEnabled(true);
                this.btn_agg.setEnabled(false);
                this.btn_cobrar.setEnabled(false);
            }else
            
            if("Enviado".equals(est) && filasel!=-1)
            {
               this.btn_modificar.setEnabled(true);
               this.btn_facturar.setEnabled(false);
               this.btn_agg_envio.setEnabled(false);
               this.btn_transferencia.setEnabled(false); 
               this.btn_concretar.setEnabled(true);
               this.btn_cancelar.setEnabled(true);
               this.btn_enviar_correo.setEnabled(true);
               this.btn_agg.setEnabled(false);
               this.btn_cobrar.setEnabled(false);
            }else
            validarBotonFact();
            if("Facturado".equals(est) && filasel!=-1)
            {
                this.btn_modificar.setEnabled(true);
                this.btn_facturar.setEnabled(false);
                this.btn_agg_envio.setEnabled(true);
                this.btn_transferencia.setEnabled(false);
                this.btn_concretar.setEnabled(false);
                this.btn_cancelar.setEnabled(true);
                this.btn_enviar_correo.setEnabled(true);
                this.btn_agg.setEnabled(false);
                this.btn_cobrar.setEnabled(false);
            }
            if("Concretada".equals(est) && filasel!=-1)
            {
               this.btn_modificar.setEnabled(true);
               this.btn_facturar.setEnabled(false);
               this.btn_agg_envio.setEnabled(false);
               this.btn_transferencia.setEnabled(false); 
               this.btn_concretar.setEnabled(false);
               this.btn_cancelar.setEnabled(false);
               this.btn_enviar_correo.setEnabled(false);
               this.btn_agg.setEnabled(false);
               
               this.btn_cobrar.setEnabled(false);
            }else
            if("Cancelada".equals(est) && filasel!=-1)
            {
               this.btn_modificar.setEnabled(true);
               this.btn_facturar.setEnabled(false);
               this.btn_agg_envio.setEnabled(false);
               this.btn_transferencia.setEnabled(false); 
               this.btn_concretar.setEnabled(false);
               this.btn_cancelar.setEnabled(false);
               this.btn_crear_cliente.setEnabled(false);
               this.btn_enviar_correo.setEnabled(false);
               this.btn_aso_cliente.setEnabled(false);
               this.btn_agg.setEnabled(false);
               this.btn_cobrar.setEnabled(false);
            }
            
        }
        catch (Exception e){
        }
    }
    
    
    void validarBotonesp()
    {
        
        int filasel1;
        int filasel2;
        String est;
        try
        {            
            filasel1 = tabla_ml.getSelectedRow();
            filasel2 = tabla_prodml.getSelectedRow();
            est = tabla_ml.getValueAt(filasel1, 6).toString();
            
            if (filasel2!=-1)
            {
                if ("Por Cobrar".equals(est) && filasel1!=-1)
                {
                this.btn_sus.setEnabled(true);
                
                }else
                if("Transfirio".equals(est)&& filasel1!=-1)
                {
                    this.btn_sus.setEnabled(true);
                }else
                if("Cobrado".equals(est) || "Cobrado MP".equals(est) && filasel1!=-1)
                {
                    this.btn_sus.setEnabled(true);
                }else
                if("Facturado".equals(est) && filasel1!=-1)
                {
                   this.btn_sus.setEnabled(false);
                }else
                if("Enviado".equals(est) && filasel1!=-1)
                {
                   this.btn_sus.setEnabled(false);
                }else
                validarBotonFact();
                if("Concretada".equals(est) && filasel1!=-1)
                {
                   this.btn_sus.setEnabled(false);
                }else
                if("Cancelada".equals(est) && filasel1!=-1)
                {
                   this.btn_sus.setEnabled(false);
                }
            }

            
            
        }
        catch (Exception e){
        }
    }
void transfirio(){
        String descrip=(String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 4);
        String ids= (String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0);
        int id=Integer.parseInt(ids);
        String estado="Transfirio";
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Cambiar Estado de "+descrip+" a Transfirió?", "Transfirió",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        clase_ml ml = new clase_ml(id,estado);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        r=in.CambiarTransfer(ml);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Estado cambiado a 'Transfirió'", "Transfirio",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }
void sumStock()
   {
     try{
       int canv=0, can=0, cantidad=0, id=0;
       String idprod;
       boolean r=false;
       idprod=(String) tabla_prodml.getValueAt(tabla_prodml.getSelectedRow(), 0);
       can=Integer.parseInt((String) tabla_prodml.getValueAt(tabla_prodml.getSelectedRow(), 2));
       ConexionBD parametros = new ConexionBD();
       Class.forName(parametros.getDriver());
       Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
       String sql = "SELECT * FROM producto WHERE idproducto = '"+idprod+"'";
       Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       ResultSet rs = st.executeQuery(sql);
       if (rs.first()) 
       {
            canv=rs.getInt("cantidad_prod");
        }
       cantidad=canv+can;
               
        clase_descontar ped = new clase_descontar(cantidad,idprod);
        Gest_ventas in = new Gest_ventas();
        r=in.ModStock(ped);
       
        if(r==false)
       {
           JOptionPane.showMessageDialog(this,"Producto removido exitosamente","Remover Producto",1,null);
       }
    
       }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
                System.out.println("ERROR STOCK");
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE,null,ex);
            }
   }

void cancelar(){
        String descrip=(String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 4);
        String ids= (String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0);
        int id=Integer.parseInt(ids);
        String estado="Cancelada";
        cargarProductos();
       

try{
        clase_ml ml = new clase_ml(id,estado);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        r=in.CambiarTransfer(ml);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Estado cambiado a 'Cancelada'", "Cancelar",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
void concretar(){
        String descrip=(String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 2);
        String ids= (String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0);
        int id=Integer.parseInt(ids);
        String estado="Concretada";
       int opc = JOptionPane.showConfirmDialog(null, "¿Desea Cambiar Estado de "+descrip+" a Concretada?", "Concretar",JOptionPane.YES_NO_OPTION);
       if (opc==0){

try{
        clase_ml ml = new clase_ml(id,estado);
        Gest_ventas in = new Gest_ventas();
        boolean r;
        r=in.CambiarTransfer(ml);
        if (r==false)
        {
            JOptionPane.showMessageDialog(this,"Estado cambiado a 'Concretada'", "Concretar",1,null);
        }
       
        }catch(SQLException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }


    void asociar()
    {
        
    }
    
    public void remover()
 {
    String id,cod;
    id=(String)tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0);
    cod=(String)tabla_prodml.getValueAt(tabla_prodml.getSelectedRow(), 0);
    
    try {
            Clase_det_ml pag = new Clase_det_ml(id,cod);
            boolean p;
            Gest_ventas in = new Gest_ventas();
            p=in.EliminarDetVenMl(pag);

            if (p=true)
            {
                JOptionPane.showMessageDialog(this,"Producto Removido Correctamente","Remover",1,null);
                cargarProductos();
                this.btn_sus.setEnabled(false);

            }else
            {
                System.out.println("Error en Productos");
            }
    }catch (SQLException ex) {
                    Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
                }

 }
    void removerProd()
    {
        try{
       int canv=0, can=0, cantidad=0, id=0;
       String idprod;
       boolean r=false;
       idprod=(String) tabla_prodml.getValueAt(tabla_prodml.getSelectedRow(), 0);
       can=Integer.parseInt((String) tabla_prodml.getValueAt(tabla_prodml.getSelectedRow(), 2));
       ConexionBD parametros = new ConexionBD();
       Class.forName(parametros.getDriver());
       Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
       String sql = "SELECT * FROM producto WHERE idproducto = '"+idprod+"'";
       Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       ResultSet rs = st.executeQuery(sql);
       if (rs.first()) 
       {
            canv=rs.getInt("cantidad_prod");
        }
       cantidad=canv+can;
               
        clase_descontar ped = new clase_descontar(cantidad,idprod);
        Gest_ventas in = new Gest_ventas();
        r=in.ModStock(ped);
       
        if(r==false)
       {
           
           remover();
       }
    
       }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
                System.out.println("ERROR STOCK");
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE,null,ex);
            }
    }
    public void validarRemover()
 {
     int filasel2, filasel1;
     filasel1 = tabla_ml.getSelectedRow();
      filasel2 = tabla_prodml.getSelectedRow();
      String est = tabla_ml.getValueAt(filasel1, 6).toString();
            
            

            if (("Por Cobrar".equals(est) || "Transfirio".equals(est) || "Cobrado".equals(est) || "Cobrado MP".equals(est) ) && filasel2!=-1)
            {
                this.btn_sus.setEnabled(true);
                
            }else
            {
                 this.btn_sus.setEnabled(false);
            }


    
 }

void limpiarProductos()
        {
            for (int i = 0; i < tabla_prodml.getRowCount(); i++) {
           m.removeRow(i);
           i-=1;
       }
        }
     void cargarProductos()
    {
        int cantt = 0;
        float tott = 0;
        limpiarProductos();
        int fsel = tabla_ml.getSelectedRow();
        
           this.lab_cantprod.setText("0");
           this.lab_totalp.setText("0 Bs.");
        if (fsel!=-1)
        {
            String id =tabla_ml.getValueAt(fsel, 0).toString();
        try{
           
        String sql;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        sql = "SELECT * FROM detalles_ml INNER JOIN producto ON producto.idproducto = detalles_ml.fkproducto  WHERE fkventas_ml='"+id+"'";
        Statement st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet  rs = st.executeQuery(sql);
        while(rs.next()){
            String cod = rs.getString("detalles_ml.fkproducto");
            String det = rs.getString("producto.descripcion_prod");
            String can = rs.getString("detalles_ml.cantidad");
            int cani = rs.getInt("detalles_ml.cantidad");
            cantt=cantt+cani;
            String pre = rs.getString("detalles_ml.precio");
            String tot = rs.getString("detalles_ml.total");
            float totd = rs.getFloat("detalles_ml.total");
            tott=tott+totd;
           
            m=(DefaultTableModel)tabla_prodml.getModel();
            
            String filaelemento[] = {cod,det,can,pre,tot};
            m.addRow(filaelemento);
            this.lab_cantprod.setText(""+cantt);
            this.lab_totalp.setText(tott+" Bs.");
           
        }
        
    }catch(SQLException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            limpiarProductos();
            
        }
        
    }
    void accbtnaceptar()
  {
      int fsel = tabla_producto.getSelectedRow();
      
     
      
       String  cod,codi, prec, cant,id,canp;
       int cann=0;
       int cans=0;
       id = tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0).toString();
       float x;
       double y;
       boolean b=false;
       
    
                
                   cod=tabla_producto.getValueAt(fsel, 0).toString();
                   canp=tabla_producto.getValueAt(fsel, 1).toString();
                   prec=tabla_producto.getValueAt(fsel, 3).toString();
                   cant = txt_cant.getText();
                   cans=Integer.valueOf(canp);
                   cann=Integer.valueOf(cant);
                   x=(Float.parseFloat(prec) * Integer.parseInt(cant));
                   y=(Math.rint(x*100)/100); 
                   
                   int ftotal = tabla_prodml.getRowCount();
                      for (int i = 0; i < ftotal; i++) {
                         String code=tabla_prodml.getValueAt(i, 0).toString(); 
                         b = code.equals(cod);
                          if (b)
                          {
                              
                              System.out.println(i+ "B=true ");
                              break;
                          }else
                          {
                              System.out.println(i+ "B=false ");
                          }
                          
                          }
                
       if(b==true)
       {
           this.jLabel11.setText("Atención...No puede volver a ingresar el mismo producto");
       }
       else
            if(cans<cann)
            {
                this.jLabel2.setText("Atención... No puede ingresar más productos de los que hay en existencia");
            }else
                
       {
                    try{
                           int canv=0, can=0, cantidad=0;
                           boolean r=false;
                           can=Integer.parseInt(cant);
                           ConexionBD parametros = new ConexionBD();
                           Class.forName(parametros.getDriver());
                           Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                           String sql = "SELECT * FROM producto WHERE idproducto = '"+cod+"'";
                           Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                           ResultSet rs = st.executeQuery(sql);
                           if (rs.first()) 
                           {
                                canv=rs.getInt("cantidad_prod");
                            }
                           cantidad=canv-can;

                            clase_descontar ped = new clase_descontar(cantidad,cod);
                            Gest_ventas in = new Gest_ventas();
                            r=in.ModStock(ped);

                            if(r==false)
                           {
                                Clase_det_ml ml = new Clase_det_ml(id, cod, cant,prec,String.valueOf(y));
                                boolean p;
                                p=in.InsertarDetVenMl(ml);
                                if (p==false)
                                {
                                    JOptionPane.showMessageDialog(ventana_prod,"Producto Ingresado Correctamente","Ingresar Producto",1,null);
                                    cargarProductos();
                                    ventana_prod.dispose();
                                }
                           }
                        
                    }                        
                    catch(SQLException e)
                            {
                                JOptionPane.showMessageDialog(ventana_prod,"Error"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                            }
                         catch(ClassNotFoundException ex)
                            {
                                Logger.getLogger(Nueva_ventaml.class.getName()).log(Level.SEVERE,null,ex);
                            }                                   
         }

    }
   
   void cargarproductostb()
   {
    try {
        String [] titulos={"Código","Cantidad","Descripción","Precio Unitario ML."};
        String[] registros = new String[4];
        DefaultTableModel mp = new DefaultTableModel(null,titulos){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String sqlm;
        ConexionBD parametros = new ConexionBD();
        Class.forName(parametros.getDriver());
        Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        sqlm = "SELECT * FROM producto WHERE estatus_prod = 'Activo' and cantidad_prod>0 order by idproducto asc";
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(sqlm);
        while (rs.next()) {
            
            
            registros[0] = rs.getString("idproducto");
            registros[1] = rs.getString("cantidad_prod");
            registros[2] = rs.getString("descripcion_prod");
            registros[3] = rs.getString("precio_ml");
            mp.addRow(registros);
        }
        
        tabla_producto.setModel(mp);
        tabla_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabla_producto.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla_producto.getColumnModel().getColumn(3).setPreferredWidth(90);
        TableRowSorter modeloOrdenado = new TableRowSorter(mp);
        tabla_producto.setRowSorter(modeloOrdenado);
        modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)"+txt_buscar1.getText()));
        tabla_producto.setRowSorter(modeloOrdenado);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   void validarBotonestb() {
        int filasel;
        String cant;
        try {
            filasel = tabla_producto.getSelectedRow();
            cant = txt_cant.getText();

            if (filasel != -1 && !cant.isEmpty()) {
                this.btn_aceptar.setEnabled(true);

            } else {
                this.btn_aceptar.setEnabled(false);
            }


        } catch (Exception e) {
        }
    }
   
   public void removerAll()
 {
    String id,cod;
    id=(String)tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 0);
    
    int fc2=tabla_prodml.getRowCount();      
       for (int i = 0; i < fc2; i++) {
           cod=(String)tabla_prodml.getValueAt(i, 0); 
           try {
            Clase_det_ml pag = new Clase_det_ml(id,cod);
            boolean p;
            Gest_ventas in = new Gest_ventas();
            p=in.EliminarDetVenMl(pag);

            if (p=true)
            {
               cancelar();

            }else
            {
                System.out.println("Error en Remover");
            }
    }catch (SQLException ex) {
                    Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ventas_ml.class.getName()).log(Level.SEVERE, null, ex);
                }

 }
 }
   
   void sumStockTab()
   {
       try{
       int canv=0, can=0, cantidad=0, id=0;
       String idprod;
       boolean r=false;
       int fc=tabla_prodml.getRowCount();      
       for (int i = 0; i < fc; i++) {
           
            idprod=(String) tabla_prodml.getValueAt(i, 0);
            can=Integer.parseInt(tabla_prodml.getValueAt(i, 2).toString());
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            String sql = "SELECT * FROM producto WHERE idproducto = '"+idprod+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            if (rs.first()) {
                canv=rs.getInt("cantidad_prod");
                }
            cantidad=canv+can;
               
        clase_descontar ped = new clase_descontar(cantidad,idprod);
        Gest_ventas in = new Gest_ventas();
        r=in.ModStock(ped);
       }
        if(r==false)
       {
           cancelar();
       }else
        {
            System.out.println("Error sumando prods");
        }
    
       
       
       }
    catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this,"Error...\n Consulte con su Gestor de Base de Datos"+e,"Error",0,null);
                System.out.println("ERROR STOCK");
            }
         catch(ClassNotFoundException ex)
            {
                Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE,null,ex);
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

        ventana_prod = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btn_aceptar = new javax.swing.JButton();
        btn_cerrar1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txt_buscar1 = new javax.swing.JTextField();
        btn_mostrar_todo1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txt_cant = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_agg_envio = new javax.swing.JButton();
        btn_facturar = new javax.swing.JButton();
        btn_transferencia = new javax.swing.JButton();
        btn_concretar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_cobrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_buscar = new javax.swing.JTextField();
        btn_mostrar_todo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ml = new javax.swing.JTable();
        cbo_filtro = new javax.swing.JComboBox();
        btn_crear_cliente = new javax.swing.JButton();
        btn_enviar_correo = new javax.swing.JButton();
        btn_aso_cliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_prodml = new javax.swing.JTable();
        btn_agg = new javax.swing.JButton();
        btn_sus = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lab_cantprod = new javax.swing.JLabel();
        lab_cantprod1 = new javax.swing.JLabel();
        lab_totalp = new javax.swing.JLabel();

        ventana_prod.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ventana_prod.setTitle("AGIF");
        ventana_prod.setAlwaysOnTop(true);
        ventana_prod.setMinimumSize(new java.awt.Dimension(755, 300));
        ventana_prod.setModal(true);
        ventana_prod.setResizable(false);
        ventana_prod.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                ventana_prodWindowOpened(evt);
            }
        });
        ventana_prod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ventana_prodKeyPressed(evt);
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

        btn_cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir1.png"))); // NOI18N
        btn_cerrar1.setText("Cerrar");
        btn_cerrar1.setToolTipText("Cerrar Modulo");
        btn_cerrar1.setBorder(null);
        btn_cerrar1.setBorderPainted(false);
        btn_cerrar1.setContentAreaFilled(false);
        btn_cerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cerrar1.setIconTextGap(-3);
        btn_cerrar1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir2.png"))); // NOI18N
        btn_cerrar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_salir_salir3.png"))); // NOI18N
        btn_cerrar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_cerrar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setAutoscrolls(true);
        jPanel10.setOpaque(false);

        txt_buscar1.setToolTipText("Ingrese texto para buscar en la tabla (Sensible a Mayúsculas y Minúsculas)");
        txt_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar1ActionPerformed(evt);
            }
        });
        txt_buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscar1KeyTyped(evt);
            }
        });

        btn_mostrar_todo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo1.setText("Mostrar Todo");
        btn_mostrar_todo1.setToolTipText("Restablecer Tabla");
        btn_mostrar_todo1.setBorder(null);
        btn_mostrar_todo1.setBorderPainted(false);
        btn_mostrar_todo1.setContentAreaFilled(false);
        btn_mostrar_todo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mostrar_todo1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Principal/Clientes/btn_gest3.png"))); // NOI18N
        btn_mostrar_todo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_mostrar_todo1MousePressed(evt);
            }
        });
        btn_mostrar_todo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrar_todo1ActionPerformed(evt);
            }
        });

        jScrollPane3.setOpaque(false);

        tabla_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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
        jScrollPane3.setViewportView(tabla_producto);

        jLabel13.setText("Cantidad :");

        txt_cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cantKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txt_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mostrar_todo1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mostrar_todo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

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
        btn_nuevo.setToolTipText("Nuevo Cliente");
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
        btn_modificar.setToolTipText("Modificar Cliente Seleccionado");
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

        btn_agg_envio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/envio1.png"))); // NOI18N
        btn_agg_envio.setText("Ag.Envio");
        btn_agg_envio.setToolTipText("Agregar Envio");
        btn_agg_envio.setBorder(null);
        btn_agg_envio.setBorderPainted(false);
        btn_agg_envio.setContentAreaFilled(false);
        btn_agg_envio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg_envio.setEnabled(false);
        btn_agg_envio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agg_envio.setIconTextGap(-3);
        btn_agg_envio.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_agg_envio.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_agg_envio.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_agg_envio.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/envio1.png"))); // NOI18N
        btn_agg_envio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/envio2.png"))); // NOI18N
        btn_agg_envio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_agg_envio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agg_envio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agg_envioActionPerformed(evt);
            }
        });

        btn_facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar.setText("Facturar");
        btn_facturar.setToolTipText("Facturar Venta");
        btn_facturar.setBorder(null);
        btn_facturar.setBorderPainted(false);
        btn_facturar.setContentAreaFilled(false);
        btn_facturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_facturar.setEnabled(false);
        btn_facturar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_facturar.setIconTextGap(-3);
        btn_facturar.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_facturar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_facturar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_facturar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido2.png"))); // NOI18N
        btn_facturar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_facturar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturarActionPerformed(evt);
            }
        });

        btn_transferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Transferencia1.png"))); // NOI18N
        btn_transferencia.setText("Transfirio");
        btn_transferencia.setToolTipText("Colocar Estado en \"Transfirio\"");
        btn_transferencia.setBorder(null);
        btn_transferencia.setBorderPainted(false);
        btn_transferencia.setContentAreaFilled(false);
        btn_transferencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transferencia.setEnabled(false);
        btn_transferencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_transferencia.setIconTextGap(-3);
        btn_transferencia.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_transferencia.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_transferencia.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_transferencia.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Transferencia1.png"))); // NOI18N
        btn_transferencia.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Transferencia2_1.png"))); // NOI18N
        btn_transferencia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_transferencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_transferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferenciaActionPerformed(evt);
            }
        });

        btn_concretar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Concretar1.png"))); // NOI18N
        btn_concretar.setText("Concretar");
        btn_concretar.setToolTipText("Concretar Venta");
        btn_concretar.setBorder(null);
        btn_concretar.setBorderPainted(false);
        btn_concretar.setContentAreaFilled(false);
        btn_concretar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_concretar.setEnabled(false);
        btn_concretar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_concretar.setIconTextGap(-3);
        btn_concretar.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_concretar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_concretar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_concretar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Concretar1.png"))); // NOI18N
        btn_concretar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Concretar2.png"))); // NOI18N
        btn_concretar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_concretar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_concretar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_concretarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Cancelar1.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setToolTipText("Cancelar Venta");
        btn_cancelar.setBorder(null);
        btn_cancelar.setBorderPainted(false);
        btn_cancelar.setContentAreaFilled(false);
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar.setEnabled(false);
        btn_cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cancelar.setIconTextGap(-3);
        btn_cancelar.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_cancelar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_cancelar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_cancelar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Cancelar1.png"))); // NOI18N
        btn_cancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Cancelar2.png"))); // NOI18N
        btn_cancelar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_cobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_cobrar.setText("Cobrar");
        btn_cobrar.setToolTipText("Facturar Venta");
        btn_cobrar.setBorder(null);
        btn_cobrar.setBorderPainted(false);
        btn_cobrar.setContentAreaFilled(false);
        btn_cobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cobrar.setEnabled(false);
        btn_cobrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cobrar.setIconTextGap(-3);
        btn_cobrar.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_cobrar.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_cobrar.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_cobrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_cobrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido2.png"))); // NOI18N
        btn_cobrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cobrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cobrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_cobrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_concretar, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                .addComponent(btn_agg_envio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_transferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_facturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_agg_envio, btn_cancelar, btn_concretar, btn_facturar, btn_modificar, btn_nuevo, btn_transferencia});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_facturar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_agg_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_concretar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_agg_envio, btn_cancelar, btn_concretar, btn_facturar, btn_modificar, btn_nuevo, btn_transferencia});

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

        tabla_ml.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_ml.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_ml.getTableHeader().setResizingAllowed(false);
        tabla_ml.getTableHeader().setReorderingAllowed(false);
        tabla_ml.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_mlFocusLost(evt);
            }
        });
        tabla_ml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_mlMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_mlMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_ml);

        cbo_filtro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Filtrar Por", "Por Cobrar", "Transfirio", "Cobrado", "Enviado", "Concretada", "Cancelada" }));
        cbo_filtro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_filtroItemStateChanged(evt);
            }
        });
        cbo_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_filtroActionPerformed(evt);
            }
        });

        btn_crear_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/CrearCliente1.png"))); // NOI18N
        btn_crear_cliente.setToolTipText("Crear Cliente Desde Venta");
        btn_crear_cliente.setBorder(null);
        btn_crear_cliente.setBorderPainted(false);
        btn_crear_cliente.setContentAreaFilled(false);
        btn_crear_cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_crear_cliente.setEnabled(false);
        btn_crear_cliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_crear_cliente.setIconTextGap(-3);
        btn_crear_cliente.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_crear_cliente.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_crear_cliente.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_crear_cliente.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/CrearCliente1.png"))); // NOI18N
        btn_crear_cliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/CrearCliente2.png"))); // NOI18N
        btn_crear_cliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_crear_cliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_crear_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crear_clienteActionPerformed(evt);
            }
        });

        btn_enviar_correo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Email1.png"))); // NOI18N
        btn_enviar_correo.setToolTipText("Enviar Correo");
        btn_enviar_correo.setBorder(null);
        btn_enviar_correo.setBorderPainted(false);
        btn_enviar_correo.setContentAreaFilled(false);
        btn_enviar_correo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_enviar_correo.setEnabled(false);
        btn_enviar_correo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_enviar_correo.setIconTextGap(-3);
        btn_enviar_correo.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_enviar_correo.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_enviar_correo.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_enviar_correo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Email1.png"))); // NOI18N
        btn_enviar_correo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Email2.png"))); // NOI18N
        btn_enviar_correo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_enviar_correo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_enviar_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviar_correoActionPerformed(evt);
            }
        });

        btn_aso_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Asociar1.png"))); // NOI18N
        btn_aso_cliente.setToolTipText("Asociar Cliente Existente");
        btn_aso_cliente.setBorder(null);
        btn_aso_cliente.setBorderPainted(false);
        btn_aso_cliente.setContentAreaFilled(false);
        btn_aso_cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_aso_cliente.setEnabled(false);
        btn_aso_cliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_aso_cliente.setIconTextGap(-3);
        btn_aso_cliente.setMaximumSize(new java.awt.Dimension(45, 57));
        btn_aso_cliente.setMinimumSize(new java.awt.Dimension(45, 57));
        btn_aso_cliente.setPreferredSize(new java.awt.Dimension(45, 57));
        btn_aso_cliente.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Asociar1.png"))); // NOI18N
        btn_aso_cliente.setRequestFocusEnabled(false);
        btn_aso_cliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/Asociar2.png"))); // NOI18N
        btn_aso_cliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_aso_cliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_aso_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aso_clienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Ventas:");

        jLabel2.setText("Productos:");

        tabla_prodml.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_prodml.getTableHeader().setResizingAllowed(false);
        tabla_prodml.getTableHeader().setReorderingAllowed(false);
        tabla_prodml.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabla_prodmlFocusLost(evt);
            }
        });
        tabla_prodml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_prodmlMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_prodmlMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_prodml);
        if (tabla_prodml.getColumnModel().getColumnCount() > 0) {
            tabla_prodml.getColumnModel().getColumn(0).setMinWidth(80);
            tabla_prodml.getColumnModel().getColumn(0).setMaxWidth(80);
            tabla_prodml.getColumnModel().getColumn(1).setMinWidth(250);
            tabla_prodml.getColumnModel().getColumn(2).setMinWidth(60);
            tabla_prodml.getColumnModel().getColumn(2).setMaxWidth(60);
            tabla_prodml.getColumnModel().getColumn(3).setMinWidth(85);
            tabla_prodml.getColumnModel().getColumn(3).setMaxWidth(75);
            tabla_prodml.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_prodml.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        btn_agg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus.png"))); // NOI18N
        btn_agg.setText("Agregar");
        btn_agg.setToolTipText("Agregar Tipo de Pago");
        btn_agg.setBorderPainted(false);
        btn_agg.setContentAreaFilled(false);
        btn_agg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_agg.setEnabled(false);
        btn_agg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_agg.setIconTextGap(5);
        btn_agg.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_agregar_plus2.png"))); // NOI18N
        btn_agg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aggActionPerformed(evt);
            }
        });

        btn_sus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus.png"))); // NOI18N
        btn_sus.setText("Quitar");
        btn_sus.setToolTipText("Quitar Tipo de Pago Seleccionado");
        btn_sus.setBorderPainted(false);
        btn_sus.setContentAreaFilled(false);
        btn_sus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sus.setEnabled(false);
        btn_sus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_sus.setIconTextGap(12);
        btn_sus.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btn_eliminar_minus2.png"))); // NOI18N
        btn_sus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_susActionPerformed(evt);
            }
        });

        jLabel3.setText("Cant. Productos: ");

        lab_cantprod.setText("0");

        lab_cantprod1.setText("Total a pagar: ");

        lab_totalp.setText("0 Bs.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_agg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_sus))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_mostrar_todo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbo_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_crear_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btn_aso_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_enviar_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lab_cantprod, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(lab_cantprod1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_totalp, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 103, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_mostrar_todo)
                            .addComponent(cbo_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_crear_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_aso_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enviar_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_agg, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_sus, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lab_cantprod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lab_cantprod1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_totalp, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        cargar();
        desactivarBotones();
        limpiarProductos();
        
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void btn_mostrar_todoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todoMousePressed
        desactivarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mostrar_todoMousePressed

    private void btn_mostrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todoActionPerformed
        txt_buscar.setText("");
        cbo_filtro.setSelectedItem("Filtrar Por");
        cargar();
    }//GEN-LAST:event_btn_mostrar_todoActionPerformed

    private void tabla_mlFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_mlFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_mlFocusLost

    private void tabla_mlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_mlMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_mlMouseClicked

    private void tabla_mlMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_mlMouseReleased
        validarBotones2();
        validarBotones(); 
        cargarProductos();
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_mlMouseReleased

    private void jPanel1formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formKeyReleased

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed

        Modificar_ventaml mod = new Modificar_ventaml();
        mod.setVisible(true);
        desactivarBotones();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        int res = JOptionPane.showConfirmDialog(null,"¿Seguro Que Desea Cerrar Esta Pestaña?","Cerrar Pestaña",JOptionPane.YES_NO_OPTION);
        if (res==0)
        {
            Principal.panel_principal.remove(this);
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        Nueva_ventaml nuevo = new Nueva_ventaml();
        nuevo.setVisible(true);
        desactivarBotones();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_agg_envioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agg_envioActionPerformed
    Agregar_Envio v = new Agregar_Envio();
    v.setVisible(true);
    desactivarBotones();    // TODO add your handling code here:
    }//GEN-LAST:event_btn_agg_envioActionPerformed

    private void btn_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturarActionPerformed
    int fsel = tabla_ml.getSelectedRow();
    String ci=tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 2).toString();

        if (ci.equals("V-00000000-0"))
            {
                JOptionPane.showMessageDialog(this,"No puede facturar sin un cliente registrado", "Error",1,null);
            }else
        {
             Ventana_Facturar_Ml v = new Ventana_Facturar_Ml();
    v.setVisible(true);
    desactivarBotones();
        }
   // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturarActionPerformed

    private void btn_transferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferenciaActionPerformed
    int c = tabla_prodml.getRowCount();
    if (c < 1)
    {
        JOptionPane.showMessageDialog(this,"No puede marcar como transferido una venta sin productos", "Error",1,null);
    }else
    {
    transfirio();
    desactivarBotones();
    cargar();
    }// TODO add your handling code here:
    }//GEN-LAST:event_btn_transferenciaActionPerformed

    private void btn_concretarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_concretarActionPerformed
    concretar();
    desactivarBotones();
    cargar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_concretarActionPerformed

    private void cbo_filtroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_filtroItemStateChanged
    cargar();        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_filtroItemStateChanged

    private void cbo_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_filtroActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        String descrip=(String) tabla_ml.getValueAt(tabla_ml.getSelectedRow(), 2);
        int opc = JOptionPane.showConfirmDialog(null, "¿Desea Cambiar Estado de "+descrip+" a Cancelada?", "Cancelar",JOptionPane.YES_NO_OPTION);
       if (opc==0){
        sumStockTab();
    desactivarBotones();
    cargar();} else
       {
           cargar();
       }// TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_crear_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crear_clienteActionPerformed
    Nuevo_Cliente_Ml c = new Nuevo_Cliente_Ml();
    c.setVisible(true);
    desactivarBotones();// TODO add your handling code here:
    }//GEN-LAST:event_btn_crear_clienteActionPerformed

    private void btn_enviar_correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviar_correoActionPerformed
    Enviar_Correos c = new Enviar_Correos();
    c.setVisible(true);
    desactivarBotones();// TODO add your handling code here:
    }//GEN-LAST:event_btn_enviar_correoActionPerformed

    private void btn_aso_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aso_clienteActionPerformed
    ventana_clientes_ventas n = new ventana_clientes_ventas();
    n.setVisible(true);
    desactivarBotones();// TODO add your handling code here:
    }//GEN-LAST:event_btn_aso_clienteActionPerformed

    private void tabla_prodmlFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_prodmlFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_prodmlFocusLost

    private void tabla_prodmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_prodmlMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_prodmlMouseClicked

    private void tabla_prodmlMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_prodmlMouseReleased
validarRemover();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_prodmlMouseReleased

    private void btn_aggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aggActionPerformed
cargarproductostb();         
        ventana_prod.setLocationRelativeTo(this);
        ventana_prod.setVisible(true);    
              // TODO add your handling code here:
    }//GEN-LAST:event_btn_aggActionPerformed

    private void btn_susActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_susActionPerformed
removerProd();    // TODO add your handling code here:
    }//GEN-LAST:event_btn_susActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        accbtnaceptar();
        this.btn_aceptar.setEnabled(false);
        this.txt_cant.setText(null);

    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void btn_cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar1ActionPerformed
        ventana_prod.dispose();
    }//GEN-LAST:event_btn_cerrar1ActionPerformed

    private void txt_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar1ActionPerformed

    private void txt_buscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar1KeyReleased
        cargarproductostb();
        btn_aceptar.setEnabled(false);
    }//GEN-LAST:event_txt_buscar1KeyReleased

    private void txt_buscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar1KeyTyped
           // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar1KeyTyped

    private void btn_mostrar_todo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrar_todo1MousePressed

    }//GEN-LAST:event_btn_mostrar_todo1MousePressed

    private void btn_mostrar_todo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_todo1ActionPerformed
        txt_buscar.setText("");
        cargarproductostb();
    }//GEN-LAST:event_btn_mostrar_todo1ActionPerformed

    private void tabla_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabla_productoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoFocusLost

    private void tabla_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseClicked
        this.txt_cant.setText(null);
        this.txt_cant.grabFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoMouseClicked

    private void tabla_productoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseReleased
        validarBotonestb();   // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoMouseReleased

    private void txt_cantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantKeyPressed
        validarBotonestb();
        int filasel;
        try
        {
            filasel = tabla_producto.getSelectedRow();

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
        validarBotonestb();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantKeyReleased

    private void ventana_prodWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ventana_prodWindowOpened
        cargarproductostb();        // TODO add your handling code here:
    }//GEN-LAST:event_ventana_prodWindowOpened

    private void ventana_prodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ventana_prodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ventana_prodKeyPressed

    private void btn_cobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cobrarActionPerformed
int c = tabla_prodml.getRowCount();
    if (c < 1)
    {
        JOptionPane.showMessageDialog(this,"No puede cobrar una venta sin productos", "Error",1,null);
    }else
    {
        Agregar_Pago nuevo = new Agregar_Pago();
        nuevo.setVisible(true);
        desactivarBotones();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cobrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_agg;
    private javax.swing.JButton btn_agg_envio;
    private javax.swing.JButton btn_aso_cliente;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_cerrar1;
    private javax.swing.JButton btn_cobrar;
    private javax.swing.JButton btn_concretar;
    private javax.swing.JButton btn_crear_cliente;
    private javax.swing.JButton btn_enviar_correo;
    private javax.swing.JButton btn_facturar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_mostrar_todo;
    private javax.swing.JButton btn_mostrar_todo1;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_sus;
    private javax.swing.JButton btn_transferencia;
    public static javax.swing.JComboBox cbo_filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lab_cantprod;
    private javax.swing.JLabel lab_cantprod1;
    private javax.swing.JLabel lab_totalp;
    public static javax.swing.JTable tabla_ml;
    public static javax.swing.JTable tabla_prodml;
    public static javax.swing.JTable tabla_producto;
    public static javax.swing.JTextField txt_buscar;
    public static javax.swing.JTextField txt_buscar1;
    private javax.swing.JTextField txt_cant;
    private javax.swing.JDialog ventana_prod;
    // End of variables declaration//GEN-END:variables
}
