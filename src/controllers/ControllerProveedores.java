/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.ModelProveedores;
import views.ViewProveedores;
import mysql.ConexionOswa;
import mysql.LlenadoCampos;
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
    private String tarea = "ninguna";
    private String id;
    ConexionOswa conexionOswa = new ConexionOswa();
    ToString toString = new ToString();
    LlenadoCampos llenadoCampos = new LlenadoCampos();
    
    
    public ControllerProveedores(ModelProveedores modelProveedores,ViewProveedores viewProveedores) throws SQLException{
        this.modelProveedores = modelProveedores;
        this.viewProveedores = viewProveedores;
        
        initView();
        mouseListener();
    }
    private void mouseListener(){
        viewProveedores.jLabel_Agregar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabe_lEditar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Eliminar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Buscar.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_Conexion.addMouseListener(ActionPerformed_jLabels);
        viewProveedores.jLabel_aceptar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_cancelar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_limpiar.addMouseListener(ActionPerformed_jButons);
        viewProveedores.jLabel_Sandwich.addMouseListener(Sandwich);
    }
    MouseAdapter ActionPerformed_jLabels = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            if(evt.getComponent()==viewProveedores.jLabel_Conexion){
                tarea = "conexion";
                mostrarPanelMedio();
                ocultarTodoMenosId();
                ocultarId();
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
                        conexion();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerProveedores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    default:
                        break;
                }
            }else if(evt.getComponent()==viewProveedores.jLabel_cancelar){
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
        String nombre = viewProveedores.jTextField_nombre.getText();
        llenadoCampos.campoVacioMensaje(nombre);
        String rfc = viewProveedores.jTextField_rfc.getText();
        String calle = viewProveedores.jTextField_calle.getText();
        int no = toString.stringToInt(viewProveedores.jTextField_numero.getText()); 
        String colonia = viewProveedores.jTextField_colonia.getText();
        String ciudad = viewProveedores.jTextField_ciudad.getText();
        String estado = viewProveedores.jTextField_estado.getText();
        String nombre_contacto = viewProveedores.jTextField_nombrecontacto.getText();
        int telefono = toString.stringToInt(viewProveedores.jTextField_telefono.getText());
        String email = viewProveedores.jTextField_email.getText();
        String Datos = "'"+nombre+"','"+rfc+"','"+calle+"',"+no+",'"+colonia+"','"+ciudad+"','"+estado+"','"+nombre_contacto+"',"+telefono+",'"+email+"'";
        String Campos ="nombre,rfc,calle,no,colonia,ciudad,estado,nombre_contacto,telefono,email";
        String sql = "insert into proveedores("+Campos+") values ("+Datos+");";
        conexionOswa.executeUpdate(sql);
        viewProveedores.vaciaTabla();
        viewProveedores.setFilas();
    }
        
    private void editar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        id = JOptionPane.showInputDialog(viewProveedores, "Dijite el id del proveedor a editar:");
        if (id.length()>0){
            mostrarPanelMedio();
        }else{
            id = JOptionPane.showInputDialog(viewProveedores, "Error: Vuelva a dijitar el id del proveedor a editar:");
        }
               
    }
    private void eliminar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        id = viewProveedores.jTextField_id.getText();
        llenadoCampos.campoVacioMensaje(id);
        String sql = "delete from proveedores where id_proveedor = "+id+";";
        conexionOswa.executeUpdate(sql);
 
        viewProveedores.revalidate();
        limpiarCampos();
    }
    private void ejecutarEdicion(String id) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException{
        viewProveedores.jTextField_id.setText(id);
        String nombre = viewProveedores.jTextField_nombre.getText();
        String rfc = viewProveedores.jTextField_rfc.getText();
        String calle = viewProveedores.jTextField_calle.getText();
        String no = viewProveedores.jTextField_numero.getText();
        String colonia = viewProveedores.jTextField_colonia.getText();
        String ciudad = viewProveedores.jTextField_ciudad.getText();
        String estado = viewProveedores.jTextField_estado.getText();
        String nombre_contacto = viewProveedores.jTextField_nombrecontacto.getText();
        String telefono = viewProveedores.jTextField_telefono.getText();
        String email = viewProveedores.jTextField_email.getText();
        String sql;
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
    }
    private void buscar() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        String nombre = viewProveedores.jTextField_nombre.getText();
        String sql = "select id_proveedor,nombre from proveedores where nombre = '"+nombre+"';";
        Connection con = null;
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/acme","root","");
        System.out.println(sql);
        PreparedStatement us = con.prepareStatement(sql);
        ResultSet rs = us.executeQuery();
        int i=1;
        String Resultado = "";
        if(rs.first()){
            while(rs.next()){
                Resultado += rs.getObject(i)+", ";
                i++;
            }
            JOptionPane.showMessageDialog(viewProveedores, "Valor '"+nombre+"' encontrado en id: "+Resultado);
        }else{
            JOptionPane.showMessageDialog(viewProveedores, "No encontrado");
        }
    }
    private void conexion() throws SQLException{
        JOptionPane.showMessageDialog(viewProveedores, "Conexion correcta");
        
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
        viewMain.setVisible(true);
        viewMain.revalidate();
        viewMain.repaint();
    }
    private void mostrarPanelMedio(){
        viewProveedores.jPanel_Medio.setVisible(true);
    }
    private void ocultarPanelMedio(){
        viewProveedores.jPanel_Medio.setVisible(false);
    }
}
