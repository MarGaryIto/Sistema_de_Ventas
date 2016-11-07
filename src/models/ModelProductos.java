package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import views.ViewProductos;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author akma
 */
public class ModelProductos {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    public void Conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/TecnoFon", "root", "");
            st = conexion.createStatement();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error de Conexion " + err.getMessage());

        }
    }

    private String producto;
    private String descripcion;
    private String compra;
    private String venta;
    private int existencia;

    public String getproducto() {
        return producto;
    }

    public void setproducto(String producto) {
        this.producto = producto;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getcompra() {
        return compra;
    }

    public void setcompra(String compra) {
        this.compra = compra;
    }

    public String getventa() {
        return venta;
    }

    public void setventa(String venta) {
        this.venta = venta;
    }

    public int getexistencia() {
        return existencia;
    }

    public void setexistencia(int existencia) {
        this.existencia = existencia;
    }

    public void borra() {
        this.producto = "";
        this.descripcion = "";
        this.existencia = 0;
        this.venta = "";
        this.compra = "";

    }

    public void guadarRegistro() {
        try {
            Conectar();

            st.executeUpdate("Insert into productos (producto,descripcion,precio_compra,precio_venta,existencia)" + " values ('" + producto + "','" + descripcion + "','" + compra + "','" + venta + "','" + existencia + "')");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error de guardar registros" + err.getMessage());
        }

    }

}
