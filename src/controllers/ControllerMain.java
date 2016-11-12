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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.ModelMain;
import views.*;
import models.Model_Add_User;
import controllers.Controller_Add_User;
import controllers.ControllerClientes;
import controllers.ControllerProductos;
import java.awt.HeadlessException;
import mysql.Conexion;
import mysql.ConexionOswa;

/**
 *
 * @author MarGaryIto
 */
public class ControllerMain {

    ModelMain modelMain;
    ViewMain viewMain;
    ViewProveedores viewProveedores;
    ControllerProductos controllerProductos;
    ControllerProveedores controllerProveedores;
    ControllerClientes controllerClientes;
    Model_Add_User modelAddUser;
    View_Add_User viewAddUser;
    Controller_Add_User controllerAddUser;
    Controller_About controllerAbout;
    View_About viewAbout;
    View_Clientes viewClients;
    ViewProductos viewProductos;
    boolean inicionSesion = false;
    ConexionOswa conexionOswa = new ConexionOswa();
    
    
    Conexion conexion;

    Object modules[];

    mysql.Conexion con = new mysql.Conexion();

    public ControllerMain(ModelMain modelMain, ViewMain viewMain, Object modules[]) {
        this.modelMain = modelMain;
        this.viewMain = viewMain;

               
        controllerProveedores = (ControllerProveedores) modules[1];
        controllerAddUser = (Controller_Add_User) modules[2];
        controllerAbout = (Controller_About) modules[3];
        controllerClientes = (ControllerClientes) modules[4];
        controllerProductos = (ControllerProductos) modules[5];
        

        initView();
        mouseListener();
    }

    private void mouseListener() {
        viewMain.jLabel_Ayuda.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Ayuda_AcercaDe.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Catalogos.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Catalogos_Clientes.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Catalogos_Productos.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Catalogos_Proveedores.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Operaciones.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Operaciones_Compras.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Operaciones_Ventas.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Reportes.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Reportes_Clientes.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Reportes_Productos.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Reportes_Proveedores.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Reportes_Ventas.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Sesiones.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Sesiones_IniciarSesion.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Sesiones_Usuarios.addMouseListener(ActionPerformed_jLabels);
        viewMain.jLabel_Aceptar.addMouseListener(ActionPerformed_jLabels);
    }
    MouseAdapter ActionPerformed_jLabels = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getComponent() == viewMain.jLabel_Catalogos_Proveedores) {
                if(inicionSesion==true){
                    jLabel_Catalogos_Proveedores_ActionPerformed();
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
            }
            if (evt.getComponent() == viewMain.jLabel_Aceptar) {
                tryLogg();
            }
            if (evt.getComponent() == viewMain.jLabel_Catalogos_Clientes) {
                if(inicionSesion==true){
                    jLabel_Catalogos_Clientes_ActionPerformed();
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
            }
            if (evt.getComponent() == viewMain.jLabel_Sesiones_Usuarios){
                jLabel_Sesiones_Usuarios_ActionPerformed();
            }
            if(evt.getComponent() == viewMain.jLabel_Ayuda_AcercaDe){
                jLabel_Ayuda_AcercaDe_ActionPerformed();
            }
            if(evt.getComponent() == viewMain.jLabel_Catalogos_Productos){
                if(inicionSesion==true){
                    jLabel_Catalogos_Productos_ActionPerformed();
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
            }
            JLabel jlabel = (JLabel) evt.getComponent();
            jlabel.setForeground(Color.darkGray);
        }

        @Override
        public void mouseEntered(MouseEvent men) {
            JLabel jlabel = (JLabel) men.getComponent();
            jlabel.setForeground(Color.gray);
        }

        @Override
        public void mouseExited(MouseEvent mle) {
            JLabel jlabel = (JLabel) mle.getComponent();
            jlabel.setForeground(Color.white);
        }
    };

    private void initView() {
        viewMain.setTitle("TecnoPhone");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
    }

    public void jLabel_Catalogos_Proveedores_ActionPerformed() {
        this.viewMain.setContentPane(controllerProveedores.viewProveedores);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    public void jLabel_Catalogos_Clientes_ActionPerformed() {
        View_Clientes viewClients = new View_Clientes();
        viewClients.setVisible(true);
    }

    public void jLabel_Sesiones_Usuarios_ActionPerformed() {
        this.controllerAddUser.Conectar();
        this.viewMain.setContentPane(controllerAddUser.viewAddUser);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void jLabel_Ayuda_AcercaDe_ActionPerformed(){
        this.viewMain.setContentPane(controllerAbout.viewAbout);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void jLabel_Catalogos_Productos_ActionPerformed(){
        this.viewMain.setContentPane(controllerProductos.viewProductos);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    public void tryLogg(){
        if(viewMain.jLabel_Sesiones_Usuarios.getText().length()>0 && viewMain.jPasswordField_Contrasena.getPassword().length>0){
        try{
            String usuario = viewMain.jTextField_Usuario.getText();
            String contrasena = new String(viewMain.jPasswordField_Contrasena.getPassword());
            String sql = "SELECT * FROM usuarios WHERE nombre='"+usuario+"' AND contrasena='"+contrasena+"';";
 
            ResultSet resultadosConsulta = conexionOswa.getExecuteQuery(sql);
 
            if( resultadosConsulta.first()){
                JOptionPane.showMessageDialog(viewMain, "Acceso Correcto"); 
                viewMain.jPanel_Loggin.setVisible(false);
                viewMain.jLabel_Aceptar.setText(usuario);
                inicionSesion = true;
            }else{
                JOptionPane.showMessageDialog(viewMain, "Error: Usuario o Contraseña Incorrecto");
            }
        }catch(SQLException | HeadlessException e){
            System.out.println("error: "+e);
        }
        }else{
            JOptionPane.showMessageDialog(viewMain, "No debes dejar campos vacios !!");        
        }
    }
}
