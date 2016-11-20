/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.Main;
import models.ModelMain;
import models.ModelProveedores;
import views.ViewProveedores;
import mysql.ConexionOswa;
import views.ViewMain;
import mysql.ToString;
/**
 *
 * @author BLABPC23
 */
public final class ControllerProveedores {
    ModelProveedores modelProveedores;
    ViewProveedores viewProveedores;
    ControllerMain controllerMain;
    ViewMain viewMain;
    ModelMain modelMain;
    private String tarea = "ninguna";
    private String id;
    ConexionOswa conexionOswa = new ConexionOswa();
    ToString toString = new ToString();
    Object modules[];
    Main main;
    
    
    public ControllerProveedores(ModelProveedores modelProveedores,ViewProveedores viewProveedores) throws SQLException{
        this.modelProveedores = modelProveedores;
        this.viewProveedores = viewProveedores;
        
        setTablaValoresOriginales();
        initView();
        mouseListener();
        
    }
    private void mouseListener(){
        viewProveedores.jLabel_Agregar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabe_lEditar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Eliminar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Buscar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Tabla.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_aceptar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_cancelar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_limpiar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_Sandwich.addMouseListener(Sandwich);
    }
    MouseAdapter ActionPerformed_jLabels = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            if(evt.getComponent()==viewProveedores.jLabel_Tabla){
                int eleccion = JOptionPane.showConfirmDialog(viewProveedores,"Refrescar tabla a valores iniciales");
                System.out.println(eleccion);
                if(eleccion==0){
                    try {
                        viewProveedores.vaciaTabla();
                        setTablaValoresOriginales();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else if(evt.getComponent()==viewProveedores.jLabel_Agregar){
                mostrarPanelMedio();
                mostrarTodo();
                ocultarId();
                tarea = "agregar";
            }else if(evt.getComponent()==viewProveedores.jLabe_lEditar){
                tarea="editar";
                try {
                    editar();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(evt.getComponent()==viewProveedores.jLabel_Eliminar){
                mostrarTodo();
                ocultarTodoMenosId();
                tarea = "eliminar";
                mostrarPanelMedio();
            }else if(evt.getComponent()==viewProveedores.jLabel_Buscar){
                mostrarTodo();
                ocultarTodoMenosNombre();
                tarea = "buscar";
                mostrarPanelMedio();
            }
            JLabel jlabel = (JLabel) evt.getComponent();
            jlabel.setForeground(Color.darkGray);
            viewProveedores.jLabel_aceptar.setText(tarea);
        }
        @Override
        public void mouseEntered(MouseEvent men){
            JLabel jlabel = (JLabel) men.getComponent();
            jlabel.setForeground(Color.gray);
        }
        @Override
        public void mouseExited(MouseEvent mle){
            JLabel jlabel = (JLabel) mle.getComponent();
            jlabel.setForeground(Color.white);
        }
    };
    MouseAdapter ActionPerformed_jButons = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            if(evt.getComponent()==viewProveedores.jLabel_aceptar){
                if(null != tarea)switch (tarea) {
                    case "agregar":
                {
                    try {
                        agregar();
                    } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "editar":{
                    try {
                        ejecutarEdicion(id);
                    } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                        break;
                    case "eliminar":
                {
                    try {
                        eliminar();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "buscar":
                {
                    try {
                        buscar();
                    } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "conexion":
                {
                    try {
                        tabla();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    default:
                        break;
                }
            }else if(evt.getComponent()==viewProveedores.jLabel_cancelar){
                limpiarCampos();
                ocultarPanelMedio();
            }else if(evt.getComponent()==viewProveedores.jLabel_limpiar){
                limpiarCampos();
            }
            JLabel jlabel = (JLabel) evt.getComponent();
            jlabel.setForeground(Color.darkGray);
        }
        @Override
        public void mouseEntered(MouseEvent men){
            JLabel jlabel = (JLabel) men.getComponent();
            jlabel.setForeground(Color.gray);
        }
        @Override
        public void mouseExited(MouseEvent mle){
            JLabel jlabel = (JLabel) mle.getComponent();
            jlabel.setForeground(Color.blue);
        }
    };
    MouseAdapter Sandwich = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            sandwichActionPerformed();
        }
    };
    
    private void initView() {
        viewProveedores.setVisible(true);
    }
    
    private void agregar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{    
        String[][] elementosClasificacion = new  String[2][10];
        String nombre = viewProveedores.jTextField_nombre.getText();
        elementosClasificacion[0][0]=nombre;elementosClasificacion[1][0]="nombre";
        String rfc = viewProveedores.jTextField_rfc.getText();
        elementosClasificacion[0][1]=rfc;elementosClasificacion[1][1]="rfc";
        String calle = viewProveedores.jTextField_calle.getText();
        elementosClasificacion[0][2]=calle;elementosClasificacion[1][2]="calle";
        int no = toString.stringToInt(viewProveedores.jTextField_numero.getText()); 
        elementosClasificacion[0][3]=""+no;elementosClasificacion[1][3]="no";
        String colonia = viewProveedores.jTextField_colonia.getText();
        elementosClasificacion[0][4]=colonia;elementosClasificacion[1][4]="colonia";
        String ciudad = viewProveedores.jTextField_ciudad.getText();
        elementosClasificacion[0][5]=ciudad;elementosClasificacion[1][5]="ciudad";
        String estado = viewProveedores.jTextField_estado.getText();
        elementosClasificacion[0][6]=estado;elementosClasificacion[1][6]="estado";
        String nombre_contacto = viewProveedores.jTextField_nombrecontacto.getText();
        elementosClasificacion[0][7]=nombre_contacto;elementosClasificacion[1][7]="nombre de contacto";
        int telefono = toString.stringToInt(viewProveedores.jTextField_telefono.getText());
        elementosClasificacion[0][8]=""+telefono;elementosClasificacion[1][8]="telefono";
        String email = viewProveedores.jTextField_email.getText();
        elementosClasificacion[0][9]=email;elementosClasificacion[1][9]="email";
        String Datos = "'"+nombre+"','"+rfc+"','"+calle+"',"+no+",'"+colonia+"','"+ciudad+"','"+estado+"','"+nombre_contacto+"',"+telefono+",'"+email+"'";
        String Campos ="nombre,rfc,calle,no,colonia,ciudad,estado,nombre_contacto,telefono,email";
        String sql = "insert into proveedores("+Campos+") values ("+Datos+");";  
        String elmVac = devuelveElementosVacios(elementosClasificacion);
        if(elmVac.length()>1){
            JOptionPane.showMessageDialog(viewProveedores, "Falta llenar campos: "+elmVac);
        }else{
            conexionOswa.executeUpdate(sql);
            viewProveedores.vaciaTabla();
            setTablaValoresOriginales();
            limpiarCampos();
            JOptionPane.showMessageDialog(viewProveedores, "Datos almacenados correctamente");
        }
    }
    private void eliminar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        int id = toString.stringToInt(viewProveedores.jTextField_id.getText());
        String sql = "select id_proveedor from proveedores where id_proveedor = "+id+";";
        ResultSet rs = conexionOswa.getExecuteQuery(sql);
        if(rs.first()){
            sql = "delete from proveedores where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            limpiarCampos();
            viewProveedores.vaciaTabla();
            setTablaValoresOriginales();
            JOptionPane.showMessageDialog(viewProveedores, "eliminacion correcta");
        }else{
            JOptionPane.showMessageDialog(viewProveedores, "id no encontrado");
        }
    }
    private void editar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        id = ""+toString.stringToInt( JOptionPane.showInputDialog(viewProveedores, "Dijite el id del proveedor a editar:"));
        String sql = "select * from proveedores where id_proveedor = "+id+";";
        ResultSet rs = conexionOswa.getExecuteQuery(sql);
        if(rs.first()){
            mostrarPanelMedio();
            mostrarTodo();
            viewProveedores.jTextField_id.setText(""+rs.getObject(1));
            viewProveedores.jTextField_nombre.setText(""+rs.getObject(2));
            viewProveedores.jTextField_rfc.setText(""+rs.getObject(3));
            viewProveedores.jTextField_calle.setText(""+rs.getObject(4));
            viewProveedores.jTextField_numero.setText(""+rs.getObject(5));
            viewProveedores.jTextField_colonia.setText(""+rs.getObject(6));
            viewProveedores.jTextField_ciudad.setText(""+rs.getObject(7));
            viewProveedores.jTextField_estado.setText(""+rs.getObject(8));
            viewProveedores.jTextField_nombrecontacto.setText(""+rs.getObject(9));
            viewProveedores.jTextField_telefono.setText(""+rs.getObject(10));
            viewProveedores.jTextField_email.setText(""+rs.getObject(11));
        }else{
            JOptionPane.showMessageDialog(viewProveedores, "id no encontrado");
        }
    }
    private void ejecutarEdicion(String id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException{
        String[][] elementosClasificacion = new  String[2][10];
        String sql="";
        String nombre = viewProveedores.jTextField_nombre.getText();
        elementosClasificacion[0][0]=nombre;elementosClasificacion[1][0]="nombre";
        String rfc = viewProveedores.jTextField_rfc.getText();
        elementosClasificacion[0][1]=rfc;elementosClasificacion[1][1]="rfc";
        String calle = viewProveedores.jTextField_calle.getText();
        elementosClasificacion[0][2]=calle;elementosClasificacion[1][2]="calle";
        int no = toString.stringToInt(viewProveedores.jTextField_numero.getText()); 
        elementosClasificacion[0][3]=""+no;elementosClasificacion[1][3]="no";
        String colonia = viewProveedores.jTextField_colonia.getText();
        elementosClasificacion[0][4]=colonia;elementosClasificacion[1][4]="colonia";
        String ciudad = viewProveedores.jTextField_ciudad.getText();
        elementosClasificacion[0][5]=ciudad;elementosClasificacion[1][5]="ciudad";
        String estado = viewProveedores.jTextField_estado.getText();
        elementosClasificacion[0][6]=estado;elementosClasificacion[1][6]="estado";
        String nombre_contacto = viewProveedores.jTextField_nombrecontacto.getText();
        elementosClasificacion[0][7]=nombre_contacto;elementosClasificacion[1][7]="nombre de contacto";
        int telefono = toString.stringToInt(viewProveedores.jTextField_telefono.getText());
        elementosClasificacion[0][8]=""+telefono;elementosClasificacion[1][8]="telefono";
        String email = viewProveedores.jTextField_email.getText();
        elementosClasificacion[0][9]=email;elementosClasificacion[1][9]="email";
        String elmVac = devuelveElementosVacios(elementosClasificacion);
        if(elmVac.length()>1){
            JOptionPane.showMessageDialog(viewProveedores, "Falta llenar campos: "+elmVac);
        }else{
            sql = "update proveedores set nombre = '"+nombre+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set rfc = '"+rfc+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set calle = '"+calle+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set no = '"+no+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set colonia = '"+colonia+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set ciudad = '"+ciudad+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set estado = '"+estado+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set nombre_contacto = '"+nombre_contacto+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set telefono = '"+telefono+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            sql = "update proveedores set email = '"+email+"' where id_proveedor = "+id+";";
            conexionOswa.executeUpdate(sql);
            limpiarCampos();
            viewProveedores.vaciaTabla();
            setTablaValoresOriginales();
            JOptionPane.showMessageDialog(viewProveedores, "actualizacion correcta");
    }
}
    private void buscar() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String nombre = viewProveedores.jTextField_nombre.getText();
        String sql = "select * from proveedores where nombre = '"+nombre+"';";
        ResultSet rs = conexionOswa.getExecuteQuery(sql);
        if(rs.first()){
            modelProveedores.setSql(sql);
            modelProveedores.setNumeroColumnas(11);
            viewProveedores.vaciaTabla();
            System.out.println("valores: "+modelProveedores.getSql());
            setFilas();
        }else{
            JOptionPane.showMessageDialog(viewProveedores, "No encontrado");
        }
    }
    private void tabla() throws SQLException{
        setTablaValoresOriginales();
    }
    private void mostrarTodo(){
        viewProveedores.jTextField_id.setVisible(true);
        viewProveedores.jTextField_nombre.setVisible(true);
        viewProveedores.jTextField_rfc.setVisible(true);
        viewProveedores.jTextField_calle.setVisible(true);
        viewProveedores.jTextField_numero.setVisible(true);
        viewProveedores.jTextField_colonia.setVisible(true);
        viewProveedores.jTextField_ciudad.setVisible(true);
        viewProveedores.jTextField_estado.setVisible(true);
        viewProveedores.jTextField_nombrecontacto.setVisible(true);
        viewProveedores.jTextField_telefono.setVisible(true);
        viewProveedores.jTextField_email.setVisible(true);
        
        viewProveedores.jLabel_idproveedor.setVisible(true);
        viewProveedores.jLabel_nombre.setVisible(true);
        viewProveedores.jLabel_rfc.setVisible(true);
        viewProveedores.jLabel_calle.setVisible(true);
        viewProveedores.jLabel_numero.setVisible(true);
        viewProveedores.jLabel_colonia.setVisible(true);
        viewProveedores.jLabel_ciudad.setVisible(true);
        viewProveedores.jLabel_estado.setVisible(true);
        viewProveedores.jLabel_nomcontacto.setVisible(true);
        viewProveedores.jLabel1_telefono.setVisible(true);
        viewProveedores.jLabel_email.setVisible(true);
    }
    private void ocultarTodoMenosId(){
        viewProveedores.jTextField_id.setVisible(true);
        viewProveedores.jTextField_nombre.setVisible(false);
        viewProveedores.jTextField_rfc.setVisible(false);
        viewProveedores.jTextField_calle.setVisible(false);
        viewProveedores.jTextField_numero.setVisible(false);
        viewProveedores.jTextField_colonia.setVisible(false);
        viewProveedores.jTextField_ciudad.setVisible(false);
        viewProveedores.jTextField_estado.setVisible(false);
        viewProveedores.jTextField_nombrecontacto.setVisible(false);
        viewProveedores.jTextField_telefono.setVisible(false);
        viewProveedores.jTextField_email.setVisible(false);
        
        viewProveedores.jLabel_idproveedor.setVisible(true);
        viewProveedores.jLabel_nombre.setVisible(false);
        viewProveedores.jLabel_rfc.setVisible(false);
        viewProveedores.jLabel_calle.setVisible(false);
        viewProveedores.jLabel_numero.setVisible(false);
        viewProveedores.jLabel_colonia.setVisible(false);
        viewProveedores.jLabel_ciudad.setVisible(false);
        viewProveedores.jLabel_estado.setVisible(false);
        viewProveedores.jLabel_nomcontacto.setVisible(false);
        viewProveedores.jLabel1_telefono.setVisible(false);
        viewProveedores.jLabel_email.setVisible(false);
    }
    private void ocultarTodoMenosNombre(){
        ocultarTodoMenosId();
        ocultarId();
        viewProveedores.jLabel_nombre.setVisible(true);
        viewProveedores.jTextField_nombre.setVisible(true);
    }
    private void limpiarCampos(){
        viewProveedores.jTextField_id.setText("");
        viewProveedores.jTextField_id.setText("");
        viewProveedores.jTextField_nombre.setText("");
        viewProveedores.jTextField_rfc.setText("");
        viewProveedores.jTextField_calle.setText("");
        viewProveedores.jTextField_numero.setText("");
        viewProveedores.jTextField_colonia.setText("");
        viewProveedores.jTextField_ciudad.setText("");
        viewProveedores.jTextField_estado.setText("");
        viewProveedores.jTextField_nombrecontacto.setText("");
        viewProveedores.jTextField_telefono.setText("");
        viewProveedores.jTextField_email.setText("");
    }
    private void ocultarId(){
        viewProveedores.jLabel_idproveedor.setVisible(false);
        viewProveedores.jTextField_id.setVisible(false);
    }
    private void sandwichActionPerformed(){
        
    }
    private void mostrarPanelMedio(){
        viewProveedores.jPanel_Medio.setVisible(true);
    }
    private void ocultarPanelMedio(){
        viewProveedores.jPanel_Medio.setVisible(false);
    }
    private void setTablaValoresOriginales() throws SQLException{
        modelProveedores.setSql("select id_proveedor,nombre,rfc,calle,no,colonia,ciudad,estado,nombre_contacto,telefono,email from proveedores;");
        modelProveedores.setNumeroColumnas(11);
        setFilas();
    }
    public void setFilas() throws SQLException{
        try{
            String sql = modelProveedores.getSql();
            int numeroColumnas = modelProveedores.getNumeroColumnas();
            try (ResultSet res = conexionOswa.getExecuteQuery(sql)) {
                Object datos[] = new Object[numeroColumnas];//Numero de columnas de la consulta
                
                while (res.next()){
                    for(int i = 0;i<datos.length;i++){
                        datos[i] = res.getObject(i+1);
                    }
                    viewProveedores.getModeloTabla().addRow(datos);
                }
            } //Numero de columnas de la consulta
        }catch(SQLException ex){
            Logger.getLogger(ViewProveedores.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private String devuelveElementosVacios(String[][] elementosClasificacion){
        int vueltas = 0;
        String elementosVacios = "";
        while(vueltas<elementosClasificacion[0].length){
            if(elementosClasificacion[0][vueltas].length()==0){
                elementosVacios+=elementosClasificacion[1][vueltas]+", ";
            }
            vueltas+=1;
        }
        return elementosVacios;
    }
}
