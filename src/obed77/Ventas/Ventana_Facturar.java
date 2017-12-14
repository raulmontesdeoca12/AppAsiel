package obed77.Ventas;

import BaseDeDatos.ConexionBD;
import BaseDeDatos.Ventas.Gest_ventas;
import BaseDeDatos.Ventas.clase_descontar;
import BaseDeDatos.Ventas.clase_factura;
import BaseDeDatos.Ventas.clase_factura_det;
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
import java.text.DecimalFormat;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import obed77.productos.Extraer_Stock;
import obed77.productos.Insertar_Stock;
import static obed77.productos.Productos.tabla_productos;

public class Ventana_Facturar extends javax.swing.JFrame {

    int exc, exp, ivapor, pedido;
    DefaultTableModel m;
    DefaultTableModel mp;
    Properties pp = new Properties();
    static double total;
    double sub_total;
    double ivam;
    double excento;
    int cont_cant;
    int cod;
    String dia, mes, anio;
    String nid;
    String fecha, estatus;
    Shape shape = null;
    int idfact;
    DecimalFormat formatter;

    public Ventana_Facturar() {
        initComponents();
        shape = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 20, 20);
        AWTUtilities.setWindowShape(this, shape);
        total = 0;
        sub_total = 0;
        ivam = 0;
        excento = 0;
        cont_cant = 0;
        formatter = new DecimalFormat("###,###.00");
        deshabilitarEdicion();
        limpiar();
        cargarlabels(0,"Otro");
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/LogoObed77.png"));

        return retValue;
    }

    void habilitar() {

        this.cbo_formadp.setEnabled(true);
        this.rad_contado.setEnabled(true);
        this.rad_dia.setEnabled(true);
        this.btn_buscar_clie.setEnabled(true);
        this.btn_agregar_prod.setEnabled(true);
        this.btn_facturar.setEnabled(true);
    }

     void cargarlabels(double monto, String tipoPago)
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
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
       }
      //cargar fecha
      Calendar cal = Calendar.getInstance();
      int iAnio, iDia;
      
      iAnio=cal.get(Calendar.YEAR);
      int mesi=cal.get(Calendar.MONTH);
      int mest=mesi+1;
      
      
      
      
      iDia=cal.get(Calendar.DATE);
      
      Formatter fmt = new Formatter();
      anio = iAnio+"";
      fmt.format("%02d",mest);
      mes=fmt+"";
      fmt = new Formatter();
      fmt.format("%02d",iDia);
      dia = fmt+"";
      fecha= anio+"-"+mes+"-"+dia;
        System.out.println("Fecha: "+fecha);
      int alicuota = 0;
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
            
        }else{
            JOptionPane.showMessageDialog(this,"Error...\n No se encuentra ningún dato","Error",1,null);
        }
        rs.close();
        st.close();
        con.close();
        
        if(tipoPago.equalsIgnoreCase("Transferencia")){
        String sql2 = "SELECT alicuota FROM configuracion_iva WHERE "+monto+" >= monto_min and "+monto+" <= monto_max and sysdate() <= fecha_vigencia ";
       
        Class.forName(parametros.getDriver());
        con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
        st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql2);
        
        if(rs.last())
        {
           alicuota=rs.getInt("alicuota");
            
        }else{
            JOptionPane.showMessageDialog(this,"Error...\n No se encuentra ningún dato","Error",1,null);
        }
            
        }
          System.out.println("Iva: "+ivapor+ " Ali: "+alicuota);
        ivapor = ivapor-alicuota;
        
        ivas=ivapor+"";
        
      this.lab_numfact.setText(nid);
      this.lab_fecha.setText(fecha);
      this.lab_ivapor.setText(ivas);
      calcularTotal();
       
      }catch(SQLException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
       }
       
     //llenar los label
      
    }

    void deshabilitarEdicion() {
        this.txt_rif.setEditable(false);
        this.txt_nombre_cliente.setEditable(false);
        this.txt_telefono.setEditable(false);
        this.txt_direccion.setEditable(false);
    }

    void limpiaTabla() {

        m = (DefaultTableModel) tabla_factura.getModel();
        int a = m.getRowCount() - 1;
        while (a >= 0) {
            m.removeRow(0);
        }

    }

    void limpiar() {
        this.lab_numfact.setText(null);
        this.lab_fecha.setText(null);
        this.lab_excento.setText(null);
        this.lab_subtotal.setText(null);
        this.lab_iva.setText(null);
        this.lab_valortotal.setText(null);
        Ventana_Facturar.txt_rif.setText(null);
        Ventana_Facturar.txt_nombre_cliente.setText(null);
        Ventana_Facturar.txt_telefono.setText(null);
        Ventana_Facturar.txt_direccion.setText(null);
        this.lab_son.setText(null);
        limpiaTabla();

    }

    public static void validarcamposllenos() {
        String ci, rif;
        rif = txt_rif.getText();
        if (rif.isEmpty()) {
            Ventana_Facturar.btn_agregar_prod.setEnabled(false);
        } else {
            Ventana_Facturar.btn_agregar_prod.setEnabled(true);
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
                this.btn_facturar.setEnabled(true);

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

    void cargarproductos() {
        String des_s;
        float des = 0;
        des_s = this.cbo_desc.getSelectedItem().toString();
        switch (des_s) {
            case "Desc":
                des = 0;
                break;
            case "2%":
                des = (float) 0.02;
                break;
            case "3%":
                des = (float) 0.03;
                break;
            case "5%":
                des = (float) 0.05;
                break;
            case "7%":
                des = (float) 0.07;
                break;
            case "10%":
                des = (float) 0.10;
                break;
            case "12%":
                des = (float) 0.12;
                break;
            case "13%":
                des = (float) 0.13;
                break;
            case "15%":
                des = (float) 0.15;
                break;
        }

        try {
            double precio_si = 0;
            double aux1, aux2;
            double precio_de, precio_des;

            if (this.rad_normal.isSelected()) {
                String[] titulos = {"Código", "Cantidad", "Descripción", "Precio Unitario Nor."};
                String[] registros = new String[4];
                mp = new DefaultTableModel(null, titulos) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                String sql;
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                sql = "SELECT * FROM producto WHERE estatus_prod = 'Activo' and cantidad_prod>0 order by idproducto asc";
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {

                    registros[0] = rs.getString("idproducto");
                    registros[1] = rs.getString("cantidad_prod");
                    registros[2] = rs.getString("descripcion_prod");
                    precio_si = rs.getDouble("precio_si");
                    if (des == 0) {

                        registros[3] = String.valueOf(precio_si);
                    } else {

                        System.out.println("Precio si:" + precio_si);
                        System.out.println("Desc %%:" + des);
                        aux1 = precio_si * des;
                        aux2 = Math.rint(aux1 * 100) / 100;
                        System.out.println("Descuento:" + aux2);
                        precio_de = precio_si - aux2;
                        precio_des = Math.rint(precio_de * 100) / 100;
                        System.out.println("Precio des:" + precio_des);
                        registros[3] = String.valueOf(precio_des);

                    }

                    mp.addRow(registros);
                }

                tabla_producto.setModel(mp);

                tabla_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(20);
                tabla_producto.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabla_producto.getColumnModel().getColumn(3).setPreferredWidth(90);
                TableRowSorter modeloOrdenado = new TableRowSorter(mp);
                tabla_producto.setRowSorter(modeloOrdenado);
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txt_buscar.getText()));
                tabla_producto.setRowSorter(modeloOrdenado);

            } else if (this.rad_mercado.isSelected()) {
                double precio_si_ml;
                double rend;
                String precio;

                String[] titulos = {"Código", "Cantidad", "Descripción", "Precio Unitario ML."};
                String[] registros = new String[4];
                mp = new DefaultTableModel(null, titulos) {
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
                    precio_si = rs.getDouble("precio_si");
                    precio_si_ml = precio_si / 0.9;
                    rend = Math.rint(precio_si_ml * 100) / 100;
                    precio = String.valueOf(rend);
                    registros[3] = precio;
                    mp.addRow(registros);
                }

                tabla_producto.setModel(mp);
                tabla_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
                tabla_producto.getColumnModel().getColumn(1).setPreferredWidth(20);
                tabla_producto.getColumnModel().getColumn(2).setPreferredWidth(120);
                tabla_producto.getColumnModel().getColumn(3).setPreferredWidth(90);
                TableRowSorter modeloOrdenado = new TableRowSorter(mp);
                tabla_producto.setRowSorter(modeloOrdenado);
                modeloOrdenado.setRowFilter(RowFilter.regexFilter("(?i)" + txt_buscar.getText()));
                tabla_producto.setRowSorter(modeloOrdenado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardarbdd() {
        int rsu = 0;
        FileInputStream fis;
        int lon;
        try {
            pp.load(new BufferedReader(new FileReader("ajustes.properties")));
            String dir;
            dir = pp.getProperty("directorio");
            fis = new FileInputStream(dir + "/Obed77/PDF/Facturas/Factura_" + cod + ".pdf");
            String sFichero = dir + "/Obed77/PDF/Facturas/Factura_" + cod + ".pdf";
            File fichero = new File(sFichero);
            lon = (int) fichero.length();
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            String sql = "INSERT into documento_fac (iddocumento_fac,fac) values (?,?);";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setBlob(2, fis, lon);
            ps.setInt(1, cod);
            rsu = ps.executeUpdate();
            if (rsu == 1) {
                JOptionPane.showMessageDialog(this, "Documento Guardado en Base de Datos", "Guardar", 1, null);
                this.dispose();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ventana_Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void accbtnaceptar(int fsel) {

        try {
            String codi, desc, prec, canp, imp, cant;
            float x, calcula;
            int cann = 0;
            int cans = 0;
            boolean b = false;

            if (b) {
                System.out.println("1 true");
            } else {
                System.out.println("1 false");
            }

            m = (DefaultTableModel) tabla_producto.getModel();
            codi = tabla_producto.getValueAt(fsel, 0).toString();
            canp = tabla_producto.getValueAt(fsel, 1).toString();
            desc = tabla_producto.getValueAt(fsel, 2).toString();
            prec = tabla_producto.getValueAt(fsel, 3).toString();
            cant = txt_cant.getText();
            cans = Integer.valueOf(canp);
            cann = Integer.valueOf(cant);

            x = (Float.parseFloat(prec) * Integer.parseInt(cant));
            imp = String.valueOf(x);
            int ftotal = tabla_factura.getRowCount();
            for (int i = 0; i < ftotal; i++) {
                String code = tabla_factura.getValueAt(i, 0).toString();
                b = code.equals(codi);
                if (b) {

                    System.out.println(i + "B=true ");
                    break;
                } else {
                    System.out.println(i + "B=false ");
                }

            }

            if (b == true) {
                this.jLabel2.setText("Atención...No puede volver a ingresar el mismo producto");
            } else if (cans < cann) {
                this.jLabel2.setText("Atención... No puede ingresar más productos de los que hay en existencia");
            } else {
                int cant_tabla = tabla_factura.getRowCount();
                if (cant_tabla == 20) {
                    this.jLabel2.setText("Atención... No puede ingresar más de 20 Productos...");
                } else {

                    m = (DefaultTableModel) tabla_factura.getModel();
                    String filaelemento[] = {codi, desc, cant, prec, imp};
                    m.addRow(filaelemento);
                    cont_cant = cont_cant + cann;

                    calcula = Float.parseFloat(imp);

                    //calcula sub total
                    sub_total = sub_total + calcula;
                    sub_total = (Math.rint(sub_total*100)/100);

                    calcularTotal();
                   
                    this.jLabel2.setText("Producto " + codi + " Ingresado Exitosamente...");
                }

            }

            if (b) {
                System.out.println("2 True");
            } else {
                System.out.println("2 false");
            }
            int it = this.tabla_factura.getRowCount();
            this.lab_items.setText(it + "");
            String tipoPago= cbo_formadp.getSelectedItem().toString();
            cargarlabels(sub_total,  tipoPago);

        } catch (Exception e) {
        }
    }

    
    private void calcularTotal(){
                     //calcula el iva del sub total
                     ivam=(sub_total*ivapor)/100;
                     ivam=(Math.rint(ivam*100)/100);
                     System.out.println("Ivam "+ivam);

                     //valida la cantidad de productos

                     //calcula el total
                     total=(sub_total+ivam); 
                     System.out.println(sub_total+"+"+ivam);
                     total= (Math.rint(total*100)/100);
                     System.out.println("Total: "+total);



                     //asignamos los valores;
                     this.lab_subtotal.setText(sub_total+"");
                     this.lab_iva.setText(ivam+"");
                     this.lab_excento.setText(excento+"");
                     this.lab_valortotal.setText(formatter.format(total));
                     llenarson();
  }
    void llenarson() {

//        n2t numero;
//        int parte;
//        String partd;
//        String res;
//        String rescom;
//        String nums = lab_valortotal.getText();
//        float num = Float.parseFloat(nums);
//        if (nums != "0" || nums != "") {
//
//            String[] arr = nums.split("\\.");
//            partd = arr[1];
//            parte = (int) num;
//            numero = new n2t(parte);
//            res = numero.convertirLetras(parte);
//            rescom = res + " Con " + partd + "/100";
//            this.lab_son.setText(rescom);
//        } else {
//            lab_son.setText("");
//        }
         NumberToText numero = new NumberToText();
         this.lab_son.setText(numero.Convertir(lab_valortotal.getText(), true));
    }

    void remover() {
        String imp;
        int cantidad;
        double calc;
        int fsel = tabla_factura.getSelectedRow();
        imp = tabla_factura.getValueAt(fsel, 4).toString();
        cantidad = Integer.parseInt(tabla_factura.getValueAt(fsel, 2).toString());
        cont_cant = cont_cant - cantidad;

        calc = Double.parseDouble(imp);

        sub_total = sub_total - calc;
        sub_total =(Math.rint(sub_total * 100) / 100);

        ivam = (sub_total * ivapor) / 100;
        ivam = (Math.rint(ivam * 100) / 100);

        if (cont_cant >= exc) {
            excento = (sub_total * exp) / 100;
        } else {
            excento = 0;
        }
        excento = (Math.rint(excento * 100) / 100);
        total = (sub_total + ivam) - excento;
        total = (Math.rint(total * 100) / 100);
        m = (DefaultTableModel) tabla_factura.getModel();
        m.removeRow(fsel);
        tabla_factura.setModel(m);

        this.lab_subtotal.setText(sub_total + "");
        this.lab_iva.setText(ivam + "");
        this.lab_excento.setText(excento + "");
        this.lab_valortotal.setText(formatter.format(total));
        int it = this.tabla_factura.getRowCount();
        this.lab_items.setText(it + "");
        String tipoPago = cbo_formadp.getSelectedItem().toString();
        cargarlabels(sub_total, tipoPago);

    }

    void validarrad() {
        if (rad_contado.isSelected()) {
            this.txt_vencimiento.setEnabled(false);
        } else {
            this.txt_vencimiento.setEnabled(true);
        }
    }

    void modstock() {
        try {
            int canv = 0, can = 0, cantidad = 0, id = 0;
            String idprod;
            boolean r = false;
            int fc = tabla_factura.getRowCount();
            for (int i = 0; i < fc; i++) {

                idprod = (String) tabla_factura.getValueAt(i, 0);
                can = Integer.parseInt(tabla_factura.getValueAt(i, 2).toString());
                ConexionBD parametros = new ConexionBD();
                Class.forName(parametros.getDriver());
                Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                String sql = "SELECT * FROM producto WHERE idproducto = '" + idprod + "'";
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                if (rs.first()) {
                    canv = rs.getInt("cantidad_prod");
                }
                cantidad = canv - can;

                clase_descontar ped = new clase_descontar(cantidad, idprod);
                Gest_ventas in = new Gest_ventas();
                r = in.ModStock(ped);
            }
            if (r == false) {
                guardar();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error...\n Consulte con su Gestor de Base de Datos" + e, "Error", 0, null);
            System.out.println("ERROR STOCK");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void facturar() {

        try {
            int fc = tabla_factura.getRowCount();
            System.out.println("Cuenta Columnas");
            if (fc == 0) {
                JOptionPane.showMessageDialog(this, "Error...\n La Factura no posee productos", "Atención", 1, null);
            } else {
                int ad = 0;
                String ciudad, forma_pago, vencimiento, son, cliente;
                cod = Integer.parseInt(this.lab_numfact.getText());
                ciudad = this.txt_lugar.getText();
                forma_pago = this.cbo_formadp.getSelectedItem().toString();
                if (rad_contado.isSelected()) {
                    vencimiento = "Contado";
                } else {
                    vencimiento = this.txt_vencimiento.getText() + " Dias";
                }
                System.out.println("Pasa 2");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                Date fec = sdf.parse(fecha);
                cal.setTime(fec);
                cal.add(Calendar.DATE, ad);
                System.out.println("Pasa 3");
                son = this.lab_son.getText();
                System.out.println("Pasa 4");
                if (rad_contado.isSelected()) {
                    this.txt_vencimiento.setEnabled(false);
                    estatus = "Cobrada";
                } else {
                    if (rad_dia.isSelected()) {
                        estatus = "Por Cobrar";
                    }
                }
                System.out.println("Pasa 5");
                cliente = Ventana_Facturar.txt_rif.getText();
                try {
                    String d = dia;
                    String me = mes;
                    String a = anio;
                    System.out.println("Pasa 6");
                    String venta = "Normal";
                    System.out.println("Facturar "+total);
                    clase_factura fac = new clase_factura(cod, ciudad, fecha, forma_pago, vencimiento, son, d, me, a, estatus, cliente, sub_total, ivam, excento, total, venta,ivapor);
                    System.out.println("Pasa 7");
                    Gest_ventas in = new Gest_ventas();
                    System.out.println("Pasa 8");
                    System.out.println(fac.getTot());
                    boolean r;
                    r = in.InsertarFac(fac);
                    System.out.println("Pasa 9");
                    if (r == false) {
                        modstock();
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error...\n Consulte con su Gestor de Base de Datos" + e, "Error", 0, null);
                    System.out.println("Error:" + e);

                } catch (ClassNotFoundException e) {
                    Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void guardar() {

        String idfacts;
        String idprod;
        int cant_prod;
        float total_prod;
        float p_unitario;

        //validar tabla
        int fc = tabla_factura.getRowCount();
        idfacts = this.lab_numfact.getText();
        idfact = Integer.parseInt(this.lab_numfact.getText());

        try {
            Gest_ventas in = new Gest_ventas();

            boolean p = false;
            for (int i = 0; i < fc; i++) {

                idprod = tabla_factura.getValueAt(i, 0).toString();
                cant_prod = Integer.parseInt(tabla_factura.getValueAt(i, 2).toString());
                total_prod = Float.parseFloat(tabla_factura.getValueAt(i, 4).toString());
                p_unitario = Float.parseFloat(tabla_factura.getValueAt(i, 3).toString());
                clase_factura_det dped = new clase_factura_det(idprod, cant_prod, idfact, p_unitario, total_prod);

                p = in.InsertarDet(dped);

            }
            if (p = false) {

            } else {
                try {
                    pp.load(new BufferedReader(new FileReader("ajustes.properties")));
                    String dir;
                    dir = pp.getProperty("directorio");
                    ConexionBD parametros = new ConexionBD();
                    Class.forName(parametros.getDriver());
                    Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
                    JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Factura.jasper");
                    Map par = new HashMap();
                    par.clear();
                    par.put("cod_fact", idfact);
                    JasperPrint jPrint = JasperFillManager.fillReport(Report, par, con);
                    JasperPrintManager.printReport(jPrint, true);
                    JasperExportManager.exportReportToPdfFile(jPrint, dir + "/Obed77/PDF/Facturas/Factura_" + idfacts + ".pdf");
                    JasperReport Report2 = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/FacturaNV.jasper");

                    JasperPrint jPrint2 = JasperFillManager.fillReport(Report2, par, con);
                    JasperExportManager.exportReportToPdfFile(jPrint2, dir + "/Obed77/PDF/Facturasn/Factura_NoValida_" + idfacts + ".pdf");
                } catch (JRException ex) {
                    Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
                }
                int op = JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Factura?", "Imprimir", JOptionPane.YES_NO_OPTION);
                if (op == 0) {
                    guardarbdd();
                } else {
                    this.txt_lugar.setEnabled(false);
                    this.cbo_formadp.setEnabled(false);
                    this.rad_contado.setEnabled(false);
                    this.rad_dia.setEnabled(false);
                    this.txt_vencimiento.setEnabled(false);
                    this.btn_buscar_clie.setEnabled(false);
                    this.btn_agregar_prod.setEnabled(false);
                    this.btn_remover_prod.setEnabled(false);
                    this.btn_facturar.setVisible(false);
                    this.btn_facturar_imprimir.setVisible(true);
                    this.btn_facturar_imprimir.setEnabled(true);
                    this.tabla_factura.setEnabled(false);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error...\n Consulte con su Gestor de Base de Datos" + e, "Error", 0, null);
            System.out.println("Error en Guardar");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void imprimir() {
        String idfacts = String.valueOf(idfact);
        try {
            pp.load(new BufferedReader(new FileReader("ajustes.properties")));
            String dir;
            dir = pp.getProperty("directorio");
            ConexionBD parametros = new ConexionBD();
            Class.forName(parametros.getDriver());
            Connection con = DriverManager.getConnection(parametros.getUrl(), parametros.getUser(), parametros.getPass());
            JasperReport Report = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/Factura.jasper");
            Map par = new HashMap();
            par.clear();
            par.put("cod_fact", idfact);
            JasperPrint jPrint = JasperFillManager.fillReport(Report, par, con);
            JasperPrintManager.printReport(jPrint, true);
            JasperExportManager.exportReportToPdfFile(jPrint, dir + "/Obed77/PDF/Facturas/Factura_" + idfacts + ".pdf");
            JasperReport Report2 = (JasperReport) JRLoader.loadObject("src/Reportes/Ventas/FacturaNV.jasper");

            JasperPrint jPrint2 = JasperFillManager.fillReport(Report2, par, con);
            JasperExportManager.exportReportToPdfFile(jPrint2, dir + "/Obed77/PDF/Facturasn/Factura_NoValida_" + idfacts + ".pdf");
        } catch (JRException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }
        int op = JOptionPane.showConfirmDialog(this, "¿Imprimió Correctamente la Factura?", "Imprimir", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            guardarbdd();
        } else {

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
        jLabel2 = new javax.swing.JLabel();
        cbo_desc = new javax.swing.JComboBox();
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
        jLabel15 = new javax.swing.JLabel();
        txt_lugar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_agregar_prod = new javax.swing.JButton();
        btn_remover_prod = new javax.swing.JButton();
        btn_facturar = new javax.swing.JButton();
        btn_facturar_imprimir = new javax.swing.JButton();
        lab_items = new javax.swing.JLabel();
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

        ventana_prod.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ventana_prod.setTitle("AGIF");
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
            .addComponent(btn_aceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_cerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        tabla_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabla_productoKeyTyped(evt);
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

        cbo_desc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Desc", "2%", "3%", "5%", "7%", "10%", "12%", "13%", "15%" }));
        cbo_desc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_descItemStateChanged(evt);
            }
        });
        cbo_desc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbo_descPropertyChange(evt);
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
                .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mostrar_todo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rad_normal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rad_mercado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbo_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mostrar_todo)
                    .addComponent(rad_mercado)
                    .addComponent(rad_normal)
                    .addComponent(cbo_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturar");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setMinimumSize(new java.awt.Dimension(770, 710));
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
        jPanel6.add(btn_agregar_prod);
        btn_agregar_prod.setBounds(0, 0, 41, 40);

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
        jPanel6.add(btn_remover_prod);
        btn_remover_prod.setBounds(47, 0, 41, 40);

        btn_facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ventas/facturar_pedido.png"))); // NOI18N
        btn_facturar.setToolTipText("Facturar");
        btn_facturar.setBorder(null);
        btn_facturar.setBorderPainted(false);
        btn_facturar.setContentAreaFilled(false);
        btn_facturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_facturar.setEnabled(false);
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
        btn_facturar.setBounds(650, 0, 45, 45);

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
        btn_facturar_imprimir.setBounds(650, 0, 45, 45);

        lab_items.setText("0");
        jPanel6.add(lab_items);
        lab_items.setBounds(140, 10, 30, 20);

        jLabel20.setText("Items: ");
        jPanel6.add(jLabel20);
        jLabel20.setBounds(100, 10, 40, 20);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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
        cbo_formadp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_formadpItemStateChanged(evt);
            }
        });

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
                .addComponent(cbo_formadp, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_son, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jPanel1.setBounds(10, 11, 750, 690);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoInsertar1200x900.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 810, 730);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscar_clieMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_clieMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_clieMouseReleased

    private void btn_buscar_clieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_clieActionPerformed
        ventana_clientes vc = new ventana_clientes();
        vc.setVisible(true);
        validarcamposllenos();// TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_clieActionPerformed

    private void btn_agregar_prodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregar_prodMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregar_prodMouseReleased

    private void btn_agregar_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_prodActionPerformed
        ventana_prod.setLocationRelativeTo(this);
        ventana_prod.setVisible(true);
        cargarproductos();
    }//GEN-LAST:event_btn_agregar_prodActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        int fsel = tabla_producto.getSelectedRow();
        accbtnaceptar(fsel);
        llenarson();
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
        this.txt_cant.grabFocus();
        // TODO add your handling code here:
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
        try {
            filasel = tabla_producto.getSelectedRow();

            if (filasel != -1 && !this.txt_cant.getText().isEmpty() && evt.getKeyCode() == KeyEvent.VK_ENTER) {

                accbtnaceptar(filasel);
                llenarson();
                this.txt_cant.setText(null);
            } else {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    ventana_prod.dispose();
                }
            }
        } catch (Exception e) {
        }// TODO add your handling code here:
    }//GEN-LAST:event_txt_cantKeyPressed

    private void txt_cantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantKeyReleased
        validarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantKeyReleased

    private void ventana_prodWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ventana_prodWindowOpened
        cargarproductos();        // TODO add your handling code here:
    }//GEN-LAST:event_ventana_prodWindowOpened

    private void tabla_facturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_facturaMouseReleased
        validarBotones2();        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_facturaMouseReleased

    private void btn_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturarActionPerformed
        if (txt_lugar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Puede Dejar Campos Vacios");
        } else {
            facturar();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btn_facturarActionPerformed

    private void txt_lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarActionPerformed

    private void txt_lugarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyPressed
        validarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyPressed

    private void txt_lugarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyReleased
        validarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyReleased

    private void btn_remover_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remover_prodActionPerformed
        remover();
        validarBotones2();// TODO add your handling code here:
    }//GEN-LAST:event_btn_remover_prodActionPerformed

    private void btn_remover_prodMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_remover_prodMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_remover_prodMouseReleased

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

    private void cbo_descPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbo_descPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_descPropertyChange

    private void cbo_descItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_descItemStateChanged
        cargarproductos();         // TODO add your handling code here:
    }//GEN-LAST:event_cbo_descItemStateChanged

    private void ventana_prodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ventana_prodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ventana_prodKeyPressed

    private void txt_lugarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lugarKeyTyped
        txt_lugar = (JTextField) evt.getComponent();
        String cadena = txt_lugar.getText();
        char[] caracteres = cadena.toCharArray();

        if (cadena.length() != 0) {

            caracteres[0] = Character.toUpperCase(caracteres[0]);
            for (int i = 0; i < cadena.length() - 1; i++) {
                if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',' || caracteres[i] == '-') {
                    caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
                }
                txt_lugar.setText(String.valueOf(caracteres));
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lugarKeyTyped

    private void tabla_facturaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_facturaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_facturaComponentAdded

    private void rad_normalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_normalItemStateChanged
        cargarproductos();
        this.txt_cant.setText(null);
        this.btn_aceptar.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_rad_normalItemStateChanged

    private void tabla_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoKeyTyped

    private void tabla_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_productoKeyPressed

    private void rad_mercadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rad_mercadoItemStateChanged
        cargarproductos();
        this.txt_cant.setText(null);
        this.btn_aceptar.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_rad_mercadoItemStateChanged

    private void cbo_formadpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_formadpItemStateChanged
        String tipoPago= cbo_formadp.getSelectedItem().toString();
        calcularTotal();
        cargarlabels(sub_total,  tipoPago);        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_formadpItemStateChanged

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
            java.util.logging.Logger.getLogger(Ventana_Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Facturar().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_aceptar;
    public static javax.swing.JButton btn_agregar_prod;
    public javax.swing.JButton btn_buscar_clie;
    javax.swing.JButton btn_cancelar;
    public javax.swing.JButton btn_cerrar;
    javax.swing.JButton btn_facturar;
    javax.swing.JButton btn_facturar_imprimir;
    public javax.swing.JButton btn_mostrar_todo;
    public static javax.swing.JButton btn_remover_prod;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JComboBox cbo_desc;
    public javax.swing.JComboBox cbo_formadp;
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
    public javax.swing.JLabel lab_excento;
    public javax.swing.JLabel lab_fecha;
    public javax.swing.JLabel lab_items;
    public javax.swing.JLabel lab_iva;
    public javax.swing.JLabel lab_ivapor;
    public javax.swing.JLabel lab_numfact;
    public javax.swing.JLabel lab_son;
    public javax.swing.JLabel lab_subtotal;
    public javax.swing.JLabel lab_valortotal;
    public javax.swing.JRadioButton rad_contado;
    public javax.swing.JRadioButton rad_dia;
    public javax.swing.JRadioButton rad_mercado;
    public javax.swing.JRadioButton rad_normal;
    public javax.swing.JTable tabla_factura;
    public static javax.swing.JTable tabla_producto;
    public static javax.swing.JTextField txt_buscar;
    public javax.swing.JTextField txt_cant;
    public static javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_lugar;
    public static javax.swing.JTextField txt_nombre_cliente;
    public static javax.swing.JTextField txt_rif;
    public static javax.swing.JTextField txt_telefono;
    public javax.swing.JTextField txt_vencimiento;
    public javax.swing.JDialog ventana_prod;
    // End of variables declaration//GEN-END:variables
}
