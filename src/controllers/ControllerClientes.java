/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import views.ViewProductos;
import models.ModelClientes;

/**
 *
 * @author Oswaldo
 */
public class ControllerClientes {
    ViewProductos viewProductos;
    ModelClientes modelClientes;
    public ControllerClientes(ModelClientes modelClientes,ViewProductos viewProductos){
        this.modelClientes = modelClientes;
        this.viewProductos = viewProductos;
        initView();
    }
    private void initView(){
        viewProductos.setVisible(true);
    }
}
