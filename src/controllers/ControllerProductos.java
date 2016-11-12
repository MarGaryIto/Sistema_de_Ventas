package controllers;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import controllers.Conection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import views.ViewProductos;
import models.ModelProductos;

public class ControllerProductos implements ActionListener {

    public ViewProductos viewProductos;
    public ModelProductos modelClientes;

    DefaultTableModel model;

    Statement st;
    ResultSet rs;

    Connection cn;

    public ControllerProductos(ViewProductos viewProductos, ModelProductos modelClientes) {

        this.viewProductos = viewProductos;
        this.modelClientes = modelClientes;

        this.viewProductos.jtf_id_productos.setVisible(false);

        this.viewProductos.jbtn_agregar.addActionListener(this);
        this.viewProductos.jbtn_eliminar.addActionListener(this);
        this.viewProductos.jbtn_editar.addActionListener(this);
        this.viewProductos.jbtn_buscar.addActionListener(this);
        //this.viewProductos.jbtn_mostar_todo.addActionListener(this);
        this.viewProductos.jB_actualizar.addActionListener(this);
    }

    void editar() {
        try {
            int filaseleccionada;
            filaseleccionada = viewProductos.jTable.getSelectedRow();

            if (filaseleccionada >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProductos.jTable.getModel();

                String id = (String) modelotabla.getValueAt(filaseleccionada, 0);
                String producto = (String) modelotabla.getValueAt(filaseleccionada, 1);
                String descripcion = (String) modelotabla.getValueAt(filaseleccionada, 2);
                String precio_compra = (String) modelotabla.getValueAt(filaseleccionada, 3);
                String precio_venta = (String) modelotabla.getValueAt(filaseleccionada, 4);
                String existencias = (String) modelotabla.getValueAt(filaseleccionada, 5);
            

                viewProductos.jtf_id_productos.setText(id);
                viewProductos.jtf_producto.setText(producto);
                viewProductos.jtf_descripcion.setText(descripcion);
                viewProductos.jtf_precio_compra.setText(precio_compra);
                viewProductos.jtf_precio_venta.setText(precio_venta);
                viewProductos.jtf_existencias.setText(existencias);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }

    }

    void Limpiar() {
        viewProductos.jtf_id_productos.setText("");
        viewProductos.jtf_producto.setText("");
        viewProductos.jtf_descripcion.setText("");
        viewProductos.jtf_precio_compra.setText("");
        viewProductos.jtf_precio_venta.setText("");
        viewProductos.jtf_existencias.setText("");

    }

    void Actualizar() {

        try {
            this.modelClientes.setDescripcion(this.viewProductos.jtf_descripcion.getText());
            this.modelClientes.setId_producto(this.viewProductos.jtf_id_productos.getText());
            this.modelClientes.setProducto(this.viewProductos.jtf_producto.getText());
            this.modelClientes.setPrecio_compra(this.viewProductos.jtf_precio_venta.getText());
            this.modelClientes.setPrecio_venta(this.viewProductos.jtf_precio_venta.getText());
            this.modelClientes.setExistencias(this.viewProductos.jtf_existencias.getText());
            
            st = cn.createStatement();
            st.executeUpdate(this.modelClientes.executeSql());

            

        } catch (Exception e) {
//id_producto = '" + viewProductos.jtf_id_productos.getText() + "',
        }
    }

    void guardar() {

        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO prductos (producto,descripcion,precio_compra,precio_venta,existencias) VALUES (?,?,?,?,?,?)");
            ps.setString(1, viewProductos.jtf_id_productos.getText());
            ps.setString(2, viewProductos.jtf_producto.getText());
            ps.setString(3, viewProductos.jtf_descripcion.getText());
            ps.setString(4, viewProductos.jtf_precio_compra.getText());
            ps.setString(5, viewProductos.jtf_precio_venta.getText());
            ps.setString(6, viewProductos.jtf_existencias.getText());

            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar ");
            System.out.print(e.getMessage());
        }
    }

    void modificar() {

        try {
            String sql = "UPDATE prductos producto=?, descripcion=?, precio_compra=?, precio_venta=?, existencias=? where id_producto = ?";
            int fila = viewProductos.jTable.getSelectedRow();
            String dao = (String) viewProductos.jTable.getValueAt(fila, 0);
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, viewProductos.jtf_id_productos.getText());
            ps.setString(2, viewProductos.jtf_producto.getText());
            ps.setString(3, viewProductos.jtf_descripcion.getText());
            ps.setString(4, viewProductos.jtf_precio_compra.getText());
            ps.setString(5, viewProductos.jtf_precio_venta.getText());
            ps.setString(6, viewProductos.jtf_existencias.getText());

            ps.setString(1, dao);
            int n = ps.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos modificados");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        guardar();
        Limpiar();
    }

    void eliminar() {
        try {
            int fila = viewProductos.jTable.getSelectedRow();
            if (fila >= 0) {

                DefaultTableModel modelotabla = (DefaultTableModel) viewProductos.jTable.getModel();

                String id = (String) modelotabla.getValueAt(fila, 0);
                String sql = ("DELETE FROM prductos WHERE id_productos='" + id + "'");
                st = cn.createStatement();
                st.executeUpdate(sql);
                mostrardatos("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id_producto");
        modelo.addColumn("producto");
        modelo.addColumn("descripcion");
        modelo.addColumn("precio_compra");
        modelo.addColumn("precio_venta");
        modelo.addColumn("existencias");

        viewProductos.jTable.setModel(modelo);
        String sql = "";

        if (valor.equals("")) {
            sql = "SELECT  * FROM prductos";
        } else {

            sql = "SELECT  * FROM prductos WHERE id_productos='" + valor + "'";
        }
        String[] datos = new String[12];

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/acme","root","");
            st = cn.createStatement();
            ResultSet rse = st.executeQuery(sql);
           
            while (rse.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);

                modelo.addRow(datos);
            }
            viewProductos.jTable.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewProductos.jbtn_agregar) {

            if (viewProductos.jtf_producto.getText().equals("") || viewProductos.jtf_descripcion.getText().equals("") || viewProductos.jtf_precio_compra.getText().equals("") || viewProductos.jtf_precio_venta.getText().equals("") || viewProductos.jtf_existencias.getText().equals("")) {
                JOptionPane.showMessageDialog(null, " No has llenado todos los campos");
            } else {
                guardar();
                mostrardatos("");
                Limpiar();
            }
        }

        if (e.getSource() == viewProductos.jbtn_eliminar) {
            eliminar();
            System.out.print("e");
        }
        if (e.getSource() == viewProductos.jB_actualizar) {
            Actualizar();
            mostrardatos("");
            Limpiar();
        }

        if (e.getSource() == viewProductos.jbtn_editar) {
            editar();
        }
        if (e.getSource() == viewProductos.jbtn_buscar) {
            String id = String.format(JOptionPane.showInputDialog(viewProductos, "Ingresa el ID que deseas buscar"));
            mostrardatos(id);
        }
        if (e.getSource() == viewProductos.jbtn_mostrar_todo) {
            mostrardatos("");
        }
    }
}
