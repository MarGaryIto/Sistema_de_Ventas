/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.swing.table.DefaultTableModel;
//import sax.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import views.ViewProductos;

/**
 *
 * @author akma
 */
public class ModelProductos {

    private String maquina = "locahost";
    private String usuario = "root";
    private String clave = "";
    private int puerto = 3306;
    private String servidor = "";
    private static Connection conexion = null;
   // private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "");

    private String id_producto = "";
    private String producto = "";
    private String descripcion = "";
    private String precio_compra = "";
    private String precio_venta = "";
    private String existencias = "";

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }
    
    public String executeSql(){
        String sql = "UPDATE prductos SET producto= '" +producto + "', Descripcion = '" + descripcion + "', precio_compra = '" + precio_compra + "', precio_venta = '" + precio_venta + "', Existencias = '" +  "'WHERE id_producto = '" + id_producto + "''";
        return sql;
    }
  
}
