/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import views.View_Clientes;

/**
 *
 * @author BLABPC23
 */
public class Model_Options {
    private View_Clientes viewClientes = new View_Clientes();
    private String nombre = "";
            private String ApPaterno = "";
            private String ApMaterno = "";
            private String Telefono = "";
            private String Email = "";
            private String Rfc = "";
            private String Calle = "";
            private String Numero = "";
            private String Colonia = "";
            private String Ciudad = "";
            private String Estado = "";

    /**
     * @return the viewClientes
     */
    public View_Clientes getViewClientes() {
        return viewClientes;
    }

    /**
     * @param viewClientes the viewClientes to set
     */
    public void setViewClientes(View_Clientes viewClientes) {
        this.viewClientes = viewClientes;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ApPaterno
     */
    public String getApPaterno() {
        return ApPaterno;
    }

    /**
     * @param ApPaterno the ApPaterno to set
     */
    public void setApPaterno(String ApPaterno) {
        this.ApPaterno = ApPaterno;
    }

    /**
     * @return the ApMaterno
     */
    public String getApMaterno() {
        return ApMaterno;
    }

    /**
     * @param ApMaterno the ApMaterno to set
     */
    public void setApMaterno(String ApMaterno) {
        this.ApMaterno = ApMaterno;
    }

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Rfc
     */
    public String getRfc() {
        return Rfc;
    }

    /**
     * @param Rfc the Rfc to set
     */
    public void setRfc(String Rfc) {
        this.Rfc = Rfc;
    }

    /**
     * @return the Calle
     */
    public String getCalle() {
        return Calle;
    }

    /**
     * @param Calle the Calle to set
     */
    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    /**
     * @return the Numero
     */
    public String getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Colonia
     */
    public String getColonia() {
        return Colonia;
    }

    /**
     * @param Colonia the Colonia to set
     */
    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    /**
     * @return the Ciudad
     */
    public String getCiudad() {
        return Ciudad;
    }

    /**
     * @param Ciudad the Ciudad to set
     */
    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}

