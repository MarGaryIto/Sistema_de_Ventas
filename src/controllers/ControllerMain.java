/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.ModelMain;
import mysql.ConexionOswa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import views.*;
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
    ConexionOswa conexionOswa = new ConexionOswa();    
    
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
                if("administrador".equals(modelMain.getTipoUsuario())){
                    
                    jLabel_Catalogos_Proveedores_ActionPerformed();
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
            }else if(evt.getComponent()==viewMain.jLabel_Aceptar){
                tryLogg();
            }else if(evt.getComponent()==viewMain.jLabel_Catalogos_Clientes){
                if("administrador".equals(modelMain.getTipoUsuario())){
                    jLabel_Catalogos_Clientes_ActionPerformed(); 
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
            }else if(evt.getComponent()==viewMain.jLabel_Reportes_Proveedores){
                if("administrador".equals(modelMain.getTipoUsuario())){
                    try { 
                        reporteProveedores();
                    } catch (JRException ex) {
                        Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(viewMain, "Asegurese de iniciar sesion");
                }
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
    public void initView(){
        viewMain.setTitle("TecnoPhone");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
    }
    
    public void jLabel_Catalogos_Proveedores_ActionPerformed(){
        this.viewMain.setContentPane(controllerProveedores.viewProveedores);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void HolaTransacciones_ActionPerformed(){
        this.viewMain.setContentPane(controllerClientes.viewProductos);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void setContentPaneInicio(){
        this.viewMain.setContentPane(viewMain);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }
    public void reporteProveedores() throws JRException{
        conexionOswa.conexion();
        String dir = "D:\\Sistema_de_Ventas\\src\\views\\reportProveedores.jrxml";
        JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null, conexionOswa.conexion());
        JasperViewer.viewReport(mostrarReporte);
    }
    public void regresarContentPaneInicio(){
        
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
            String sql = "SELECT * FROM usuarios WHERE nombre='"+usuario+"' AND contrasena='"+contrasena+"';";
 
            ResultSet rs = conexionOswa.getExecuteQuery(sql);
 
            if( rs.first()){
                JOptionPane.showMessageDialog(viewMain, "Acceso Correcto"); 
                viewMain.jPanel_Loggin.setVisible(false);
                viewMain.jLabel_Aceptar.setText(usuario);
                modelMain.setTipoUsuario(""+rs.getObject(4));
                System.out.println("Tipo de usuario: "+rs.getObject(4));
                Image imagenInterna = new ImageIcon(getClass().getResource("user_mar.gif")).getImage();
                viewMain.jLabel_icon.setIcon((Icon) imagenInterna);
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
