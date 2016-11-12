/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import views.View_Add_User;
import models.Model_Add_User;

/**
 *
 * @author BLABPC23
 */
public class Controller_Add_User implements ActionListener {

    View_Add_User viewAddUser = new View_Add_User();
    Model_Add_User modelAddUser = new Model_Add_User();
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    public String nombre;
    public String pass;

    public Controller_Add_User(Model_Add_User modelAddUser, View_Add_User viewAddUser) {

        this.modelAddUser = modelAddUser;
        this.viewAddUser = viewAddUser;
        this.viewAddUser.Jbt_Add.addActionListener(this);
    }

    public void init_View() {
        this.viewAddUser.setVisible(true);
    }

    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/acme", "root", "");
            st = conexion.createStatement();

            rs = st.executeQuery("Select * from usuarios");

            rs.next();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error " + err.getMessage());
        }

    }

    private void AddUser() {
        try {
            pass = this.viewAddUser.Jtf_Pass.getText();
            nombre = this.viewAddUser.Jtf_User.getText();
            st.executeUpdate("INSERT INTO usuarios (nombre,contrasena) " + " values ('" + nombre + "','" + pass + "');");
            JOptionPane.showMessageDialog(viewAddUser, "El usuario ha sido registrado con exito!!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error " + err.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewAddUser.Jbt_Add) {
            AddUser();
        }
    }
}
