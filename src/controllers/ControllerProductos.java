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
import views.AgregarProductos;
import views.OpcionesProductos;
import views.ViewProductos;

/**
 *
 * @author akma
 */
public class ControllerProductos implements ActionListener {

    private Connection conexion; //Conexion
    private Statement st; //Consulta
    private ResultSet rs; //Resultados
    private final AgregarProductos agregarProductos;
    private final OpcionesProductos opcionesProductos;
    private final ViewProductos viewProductos;
    //private final ModelVolumen modelVolumen;

    public ControllerProductos(AgregarProductos agregarProductos, OpcionesProductos opcionesProductos, ViewProductos viewProductos) {
        this.agregarProductos = agregarProductos;
        this.opcionesProductos = opcionesProductos;
        this.viewProductos = viewProductos;

    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == viewProductos.jbAgregar) {
            agregarProductos();
        } else if (a.getSource() == viewProductos.jBOpciones){
            opcionesProductos();
        }

        Conectar("tecnofone", "root", "");
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM productos");

            while (rs.next()) {
                /**
                 * Recorrer los resultados
                 */
                System.out.println("" + rs.getString(1));
                System.out.println("" + rs.getString(2));
                System.out.println("" + rs.getString(3));
                System.out.println("" + rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Algo falló en la conexión. " + e.getMessage());
        }

    }

    public void Conectar(String baseDeDatos, String usuario, String password) {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/TecnoFon" + baseDeDatos + "", usuario, password);
        } catch (SQLException e) {
            System.out.println("Algo falló en la conexión. " + e.getMessage());
        }
    }

    private void agregarProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//se crearon para corregir el error de el override

    private void opcionesProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//se crearon para corregir el error de el override

}
