/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import javax.swing.JOptionPane;

/**
 *
 * @author BLABPC23
 */
public class LlenadoCampos {
    public void campoVacioMensaje(String texto){
        if(texto.length()==0){
            JOptionPane.showInputDialog("No deje espacios en blanco");
        }
    }
}
