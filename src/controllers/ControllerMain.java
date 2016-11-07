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
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.ModelMain;
import views.*;
import mysql.Conexion;
/**
 *
 * @author MarGaryIto
 */
public class ControllerMain{
    ModelMain modelMain;
    ViewMain viewMain;
    ViewProveedores viewProveedores;
    ControllerProductos controllerProductos;
    ControllerProveedores controllerProveedores;
    ControllerClientes controllerClientes;
    Conexion conexion;
    
    Object modules[];
    
    mysql.Conexion con = new mysql.Conexion();
    
    public ControllerMain(ModelMain modelMain,ViewMain viewMain,Object modules[]){
        this.modelMain = modelMain;
        this.viewMain = viewMain;
        
        controllerProductos = (ControllerProductos)modules[0];
        controllerProveedores = (ControllerProveedores)modules[1];
        
        initView();
        mouseListener();
    }
    private void mouseListener(){
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
    MouseAdapter ActionPerformed_jLabels = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent evt){
            if(evt.getComponent()==viewMain.jLabel_Catalogos_Proveedores){
                jLabel_Catalogos_Proveedores_ActionPerformed();
            }
            if(evt.getComponent()==viewMain.jLabel_Aceptar){
                tryLogg();
            }
            if(evt.getComponent()==viewMain.jLabel_Catalogos_Clientes){
               jLabel_Catalogos_Clientes_ActionPerformed(); 
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
            jlabel.setForeground(Color.white);
        }
    };

    private void initView(){
        viewMain.setTitle("TecnoPhone");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
    }
    
    public void jLabel_Catalogos_Proveedores_ActionPerformed(){
        this.viewMain.setContentPane(controllerProveedores.viewProveedores);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void jLabel_Catalogos_Clientes_ActionPerformed(){
        View_Clientes JFrame = new View_Clientes();
        JFrame.setVisible(true);
    }
    public void tryLogg(){
        if(viewMain.jLabel_Sesiones_Usuarios.getText().length()>0 && viewMain.jPasswordField_Contrasena.getPassword().length>0){
        try{
            String usuario = viewMain.jTextField_Usuario.getText();
            String contrasena = new String(viewMain.jPasswordField_Contrasena.getPassword());
            Connection unaConexion  = DriverManager.getConnection ("jdbc:mysql://localhost:3306/tecno_phone","root","1234");
            // Preparamos la consulta
            Statement instruccionSQL = unaConexion.createStatement();
            ResultSet resultadosConsulta = instruccionSQL.executeQuery ("SELECT * FROM usuarios WHERE nombre='"+usuario+"' AND contrasena='"+contrasena+"';");
 
            if( resultadosConsulta.first() ){
                JOptionPane.showMessageDialog(viewMain, "Acceso Correcto"); 
                viewMain.jPanel_Loggin.setVisible(false);
                viewMain.jLabel_Aceptar.setText(usuario);
            }else{
                JOptionPane.showMessageDialog(viewMain, "Error: Usuario o Contraseña Incorrecto");
            }
        }catch(Exception e){
            System.out.println("error"+e);
        }
        }else{
            JOptionPane.showMessageDialog(viewMain, "No debes dejar campos vacios !!");        
        }
    }
}
