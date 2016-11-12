/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import views.View_Options;
import models.Model_Options;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author BLABPC23
 */
public class Controller_Options implements ActionListener   {
    View_Options viewOpciones = new View_Options();
    Model_Options modelOptins = new Model_Options();
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    public String search = "";
    public String nombre;
    public String ApPaterno;
    public String ApMaterno;
    public String Tel;
    public String Email;
    public String Rfc;
    public String Street;
    public String No;
    public String Col;
    public String City;
    public String State;
    
              
    public Controller_Options(Model_Options modelOptions,View_Options viewOpciones) {
        this.modelOptins = modelOptions;
        this.viewOpciones = viewOpciones;
        this.viewOpciones.Jbt_Edit.addActionListener(this);
        this.viewOpciones.Jbt_Search.addActionListener(this);
        this.viewOpciones.Jbt_Save.addActionListener(this);
        
    }


    
    public void init_View() {
        
        this.viewOpciones.setVisible(true);
    }

    /**
     * Creates new form View_Options
     */
    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/acme", "root", "");
            st = conexion.createStatement();

            rs = st.executeQuery("Select * from clientes");

            rs.next();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error " + err.getMessage());
        }

    }

    private void Buscar() {
        search = this.viewOpciones.Jtf_Search.getText();
        try {
            rs = st.executeQuery("Select * from clientes where id_cliente = '" + search + "'");
            rs.next();

            this.viewOpciones.Jtf_Id.setText(rs.getString("id_cliente"));
            this.viewOpciones.Jtf_Name.setText(rs.getString("nombre"));
            this.viewOpciones.Jtf_ApPaterno.setText(rs.getString("ap_paterno"));
            this.viewOpciones.Jtf_ApMaterno.setText(rs.getString("ap_materno"));
            this.viewOpciones.Jtf_Tel.setText(rs.getString("telefono"));
            this.viewOpciones.Jtf_Email.setText(rs.getString("email"));
            this.viewOpciones.Jtf_Rfc.setText(rs.getString("rfc"));
            this.viewOpciones.Jtf_Street.setText(rs.getString("calle"));
            this.viewOpciones.Jtf_No.setText(rs.getString("no"));
            this.viewOpciones.Jtf_Col.setText(rs.getString("colonia"));
            this.viewOpciones.Jtf_City.setText(rs.getString("ciudad"));
            this.viewOpciones.Jtf_State.setText(rs.getString("estado"));

            this.viewOpciones.Jlb_Read.setVisible(true);
            this.viewOpciones.Jlb_Edit.setVisible(false);

            this.viewOpciones.Jtf_Id.setEditable(false);
            this.viewOpciones.Jtf_Name.setEditable(false);
            this.viewOpciones.Jtf_ApPaterno.setEditable(false);
            this.viewOpciones.Jtf_ApMaterno.setEditable(false);
            this.viewOpciones.Jtf_Tel.setEditable(false);
            this.viewOpciones.Jtf_Email.setEditable(false);
            this.viewOpciones.Jtf_Rfc.setEditable(false);
            this.viewOpciones.Jtf_Street.setEditable(false);
            this.viewOpciones.Jtf_No.setEditable(false);
            this.viewOpciones.Jtf_Col.setEditable(false);
            this.viewOpciones.Jtf_City.setEditable(false);
            this.viewOpciones.Jtf_State.setEditable(false);

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error " + err.getMessage());
        }
    }

    private void Editar() {
        this.viewOpciones.Jtf_Id.setEditable(true);
        this.viewOpciones.Jtf_Name.setEditable(true);
        this.viewOpciones.Jtf_ApPaterno.setEditable(true);
        this.viewOpciones.Jtf_ApMaterno.setEditable(true);
        this.viewOpciones.Jtf_Tel.setEditable(true);
        this.viewOpciones.Jtf_Email.setEditable(true);
        this.viewOpciones.Jtf_Rfc.setEditable(true);
        this.viewOpciones.Jtf_Street.setEditable(true);
        this.viewOpciones.Jtf_No.setEditable(true);
        this.viewOpciones.Jtf_Col.setEditable(true);
        this.viewOpciones.Jtf_City.setEditable(true);
        this.viewOpciones.Jtf_State.setEditable(true);
        this.viewOpciones.Jlb_Read.setVisible(false);
        this.viewOpciones.Jlb_Edit.setVisible(true);
    }

    private void Modificar() {
       nombre=this.viewOpciones.Jtf_Name.getText();
       ApPaterno=this.viewOpciones.Jtf_ApPaterno.getText();
       ApMaterno=this.viewOpciones.Jtf_ApMaterno.getText();
       Tel=this.viewOpciones.Jtf_Tel.getText();
       Email=this.viewOpciones.Jtf_Email.getText();
       Rfc=this.viewOpciones.Jtf_Rfc.getText();
       Street=this.viewOpciones.Jtf_Street.getText();
       No=this.viewOpciones.Jtf_No.getText();
       Col=this.viewOpciones.Jtf_Col.getText();
       City=this.viewOpciones.Jtf_City.getText();
       State=this.viewOpciones.Jtf_State.getText();
       
       
        try {
            st.executeUpdate("update clientes set nombre ='"+nombre+"', ap_paterno= '"+ApPaterno+"', ap_materno= '"+ApMaterno+"', telefono= '"+Tel+"', email= '"+Email+"', rfc= '"+Rfc+"', calle= '"+Street+"', no= '"+No+"', colonia= '"+Col+"', ciudad= '"+City+"', estado= '"+State+"' where id_cliente ='" + search + "'");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error " + err.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewOpciones.Jbt_Search){
            this.Buscar();
        }
        if(e.getSource() == viewOpciones.Jbt_Edit){
            this.Editar();
        }
        if(e.getSource() == viewOpciones.Jbt_Save){
            this.Modificar();
        }
            
    }
}
