/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MarGaryIto
 */
public class Conexion {
    private Connection con;
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/acme","root","");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("error: "+e);
        }
        return con;
    }
    public void cerrarConexion(Connection con){
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("error: "+e);
        }
    }    
}
