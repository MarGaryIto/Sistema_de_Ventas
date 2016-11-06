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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.ModelProveedores;
import views.ViewProveedores;
import mysql.Conexion;
import views.ViewMain;
/**
 *
 * @author BLABPC23
 */
public class ControllerProveedores {
    ModelProveedores modelProveedores;
    ViewProveedores viewProveedores;
    ControllerMain controllerMain;
    ViewMain viewMain;
    Conexion conexion;
    private String tarea = "ninguna";
    mysql.Conexion con = new mysql.Conexion();
    
    public ControllerProveedores(ModelProveedores modelProveedores,ViewProveedores viewProveedores){
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
        viewProveedores.jLabel_Sandwich.addMouseListener(Sandwich);
    }
    MouseAdapter ActionPerformed_jLabels = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            if(evt.getComponent()==viewProveedores.jLabel_Conexion){
                tarea = "conexion";
                mostrarPanelMedio();
            }else if(evt.getComponent()==viewProveedores.jLabel_Agregar){
                mostrarTodo();
                ocultarId();
                tarea = "agregar";
                mostrarPanelMedio();
            }else if(evt.getComponent()==viewProveedores.jLabe_lEditar){
                mostrarTodo();
                ocultarTodoMenosId();
                tarea="editar";
                mostrarPanelMedio();
            }
            else if(evt.getComponent()==viewProveedores.jLabel_Eliminar){
                mostrarTodo();
                ocultarTodoMenosId();
                tarea = "eliminar";
                mostrarPanelMedio();
            }else if(evt.getComponent()==viewProveedores.jLabel_Buscar){
                mostrarTodo();
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
                    case "editar":
                        editar();
                        break;
                    case "eliminar":
                        eliminar();
                        break;
                    case "buscar":
                        buscar();
                        break;
                    case "conexion":
                        conexion();
                        break;
                    default:
                        break;
                }
            }else if(evt.getComponent()==viewProveedores.jLabel_cancelar){
                mostrarPanelMedio();
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
    private void agregar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{    
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
        String Datos = "'"+nombre+"','"+rfc+"','"+calle+"',"+no+",'"+colonia+"','"+ciudad+"','"+estado+"','"+nombre_contacto+"',"+telefono+",'"+email+"'";
        String Campos ="nombre,rfc,calle,no,colonia,ciudad,estado,nombre_contacto,telefono,email";
        String sDriver = "com.mysql.jdbc.Driver";
        String sURL = "jdbc:mysql://localhost:3306/tecno_phone";
        String sql = "insert into proveedores("+Campos+") values ("+Datos+");";
        Connection con = null;
        Class.forName(sDriver).newInstance();
        con = DriverManager.getConnection(sURL,"root","1234");
        System.out.println(sql);
        Statement stmt = con.prepareStatement(sql);
        stmt.executeUpdate(sql);
        viewProveedores.revalidate();
    } 

    private void initView() {
        viewProveedores.setVisible(true);
    }
        
    private void editar(){
        //
    }
    private void eliminar(){
        //
    }
    private void buscar(){
        //
    }
    private void conexion(){
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
    }
    private void ocultarTodoMenosId(){
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
    }
    private void ocultarId(){
        viewProveedores.jTextField_id.setVisible(true);
    }
    private void sandwichActionPerformed(){
        this.viewMain.setContentPane(null);
        viewMain.revalidate();
        viewMain.repaint();
    }
    private void mostrarPanelMedio(){
        if(viewProveedores.jPanel_Medio.isVisible()){
            viewProveedores.jPanel_Medio.setVisible(false);
            tarea = "ninguna";
        }else{
            viewProveedores.jPanel_Medio.setVisible(true);
        }
    }
}
