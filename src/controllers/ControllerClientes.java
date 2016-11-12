/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import views.ViewProductos;
import models.ModelClientes;
import views.ViewMain;
import views.View_Clientes;
import models.Model_Options;
import controllers.Controller_Options;
import views.View_Options;
import views.View_Options;
/**
 *
 * @author Oswaldo
 */
public class ControllerClientes implements ActionListener {
    View_Clientes viewClients;
    ModelClientes modelClientes;
    Model_Options modelOptions;
    View_Options viewOptions;
    Controller_Options  controllerOptions;
    public ControllerClientes(ModelClientes modelClientes,View_Clientes viewClients){
        this.modelClientes = modelClientes;
        this.viewClients = viewClients;
        initView();
    }
    ViewMain viewMain = new ViewMain();
    DefaultTableModel modelTable;
    mysql.Conexion con = new mysql.Conexion();

    /**
     * Creates new form View_Clientes
     */
    public ControllerClientes() {
        modelTable = new DefaultTableModel(null, getColumnas());
        setFilas();
    }

    private String[] getColumnas() {
        String columna[] = new String[]{"id_cliente", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "E-mail", "RFC", "Calle", "Numero", "Colonia", "Ciudad", "Estado"};
        return columna;
    }

    private void setFilas() {
        try {
            String sql = "Select * from clientes";
            PreparedStatement us = con.conexion().prepareStatement(sql);
            Object datos[] = new Object[12];
            ResultSet res = us.executeQuery();

            while (res.next()) {
                for (int i = 0; i < 12; i++) {
                    datos[i] = res.getObject(i + 1);
                }
                modelTable.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Options_ActionPerformed(){
        View_Options viewOptions = new View_Options();
        viewOptions.setVisible(true);
    }
    private void initView(){
        viewClients.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.viewClients.Jbt_Options){
           Options_ActionPerformed();
        }
    }
}
