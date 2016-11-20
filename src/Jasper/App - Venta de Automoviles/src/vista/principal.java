package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Conexion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class principal extends javax.swing.JFrame {

    private Integer total = 0;
    private DefaultListModel modeloLista = new DefaultListModel();
    Conexion conn = new Conexion();

    public principal() throws SQLException, ClassNotFoundException {
        initComponents();

        ResultSet rst = conn.selectMarcasDisponibles();

        while (rst.next()) {

            this.combo_marca.addItem(rst.getObject("Nombre"));

        }

        conn.desconectar();
        rst.close();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_compra_vehiculos = new javax.swing.JLabel();
        combo_marca = new javax.swing.JComboBox();
        combo_modelo = new javax.swing.JComboBox();
        combo_anno = new javax.swing.JComboBox();
        btn_seleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_autos_seleccionados = new javax.swing.JList();
        btn_realizar_compra = new javax.swing.JButton();
        lbl_autos_seleccionados = new javax.swing.JLabel();
        lbl_seleccionar_vehiculo = new javax.swing.JLabel();
        btn_borrar = new javax.swing.JButton();
        combo_color = new javax.swing.JComboBox();
        lbl_precio_titulo = new javax.swing.JLabel();
        lbl_precio = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        lbl_placa_titulo = new javax.swing.JLabel();
        lbl_placa = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        lbl_nombre_cliente = new javax.swing.JLabel();
        txt_nombre_cliente = new javax.swing.JTextField();
        lbl_numero_cedula = new javax.swing.JLabel();
        lbl_numero_tarjeta = new javax.swing.JLabel();
        lbl_datos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JFormattedTextField();
        txt_tarjeta = new javax.swing.JFormattedTextField();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_compra_vehiculos.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N
        lbl_compra_vehiculos.setText("Compra de Vehiculos");
        getContentPane().add(lbl_compra_vehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        combo_marca.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        combo_marca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Marca" }));
        combo_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_marcaActionPerformed(evt);
            }
        });
        getContentPane().add(combo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 163, 176, -1));

        combo_modelo.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        combo_modelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Modelo" }));
        combo_modelo.setEnabled(false);
        combo_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_modeloActionPerformed(evt);
            }
        });
        getContentPane().add(combo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 205, 176, -1));

        combo_anno.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        combo_anno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Año" }));
        combo_anno.setEnabled(false);
        combo_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_annoActionPerformed(evt);
            }
        });
        getContentPane().add(combo_anno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 249, 176, -1));

        btn_seleccionar.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        btn_seleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AutoLuxe Check Icon Resized.png"))); // NOI18N
        btn_seleccionar.setText("Seleccionar");
        btn_seleccionar.setContentAreaFilled(false);
        btn_seleccionar.setEnabled(false);
        btn_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_seleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 180, -1));

        lista_autos_seleccionados.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        lista_autos_seleccionados.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lista_autos_seleccionadosPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(lista_autos_seleccionados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 163, 440, 99));

        btn_realizar_compra.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        btn_realizar_compra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Autoluxe Purchase Icon Resized.png"))); // NOI18N
        btn_realizar_compra.setContentAreaFilled(false);
        btn_realizar_compra.setEnabled(false);
        btn_realizar_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_realizar_compraActionPerformed(evt);
            }
        });
        getContentPane().add(btn_realizar_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, 80, 40));

        lbl_autos_seleccionados.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_autos_seleccionados.setText("Autos Seleccionados");
        getContentPane().add(lbl_autos_seleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 131, -1, 26));

        lbl_seleccionar_vehiculo.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_seleccionar_vehiculo.setText("Seleccionar Vehiculo");
        getContentPane().add(lbl_seleccionar_vehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 123, -1, -1));

        btn_borrar.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        btn_borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Autoluxe Delete Icon Resized.png"))); // NOI18N
        btn_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 163, 80, 99));

        combo_color.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        combo_color.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Color" }));
        combo_color.setEnabled(false);
        combo_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_colorActionPerformed(evt);
            }
        });
        getContentPane().add(combo_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 291, 176, -1));

        lbl_precio_titulo.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_precio_titulo.setText("Precio");
        getContentPane().add(lbl_precio_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 168, -1, -1));

        lbl_precio.setFont(new java.awt.Font("Myanmar Text", 1, 15)); // NOI18N
        lbl_precio.setText("---");
        lbl_precio.setToolTipText("");
        getContentPane().add(lbl_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 205, -1, -1));

        lbl_total.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_total.setText("Total: $ 0");
        getContentPane().add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 280, -1, -1));

        lbl_placa_titulo.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_placa_titulo.setText("Placa");
        getContentPane().add(lbl_placa_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 258, -1, -1));

        lbl_placa.setFont(new java.awt.Font("Myanmar Text", 1, 15)); // NOI18N
        lbl_placa.setText("---");
        getContentPane().add(lbl_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 296, -1, -1));

        btn_salir.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Autoluxe Exit Icon Resized.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setContentAreaFilled(false);
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

        lbl_nombre_cliente.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        lbl_nombre_cliente.setText("Nombre del Cliente");
        getContentPane().add(lbl_nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));
        getContentPane().add(txt_nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 135, 29));

        lbl_numero_cedula.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        lbl_numero_cedula.setText("Número de Cédula");
        getContentPane().add(lbl_numero_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, -1, -1));

        lbl_numero_tarjeta.setFont(new java.awt.Font("Myanmar Text", 0, 14)); // NOI18N
        lbl_numero_tarjeta.setText("Número de Tarjeta");
        getContentPane().add(lbl_numero_tarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, -1, -1));

        lbl_datos.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lbl_datos.setText("Ingrese sus Datos");
        getContentPane().add(lbl_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel1.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        jLabel1.setText("Realizar Compra");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(579, 480, 220, 30));

        txt_cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));
        getContentPane().add(txt_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 130, 30));

        txt_tarjeta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("################"))));
        getContentPane().add(txt_tarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 140, 30));

        lbl_fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AutoLuxe Background.png"))); // NOI18N
        getContentPane().add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_marcaActionPerformed

        if (this.combo_marca.getSelectedIndex() != 0) {

            this.combo_modelo.removeAllItems();
            this.combo_modelo.addItem("Seleccionar Modelo");

            try {

                ResultSet rst = conn.selectModelosXMarca((String) this.combo_marca.getSelectedItem());

                while (rst.next()) {

                    this.combo_modelo.addItem(rst.getObject("Modelo"));

                }

                rst.close();
                conn.desconectar();
                this.combo_modelo.setEnabled(true);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            this.combo_modelo.setEnabled(false);
        }

    }//GEN-LAST:event_combo_marcaActionPerformed

    private void combo_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_modeloActionPerformed

        if (this.combo_modelo.getSelectedIndex() != 0) {

            this.combo_anno.removeAllItems();
            this.combo_anno.addItem("Seleccionar Año");

            try {

                ResultSet rst = conn.selectAñosXModelo((String) this.combo_marca.getSelectedItem(), (String) this.combo_modelo.getSelectedItem());

                while (rst.next()) {

                    this.combo_anno.addItem(rst.getObject("Anno").toString().substring(0, 4));

                }

                rst.close();
                conn.desconectar();
                this.combo_anno.setEnabled(true);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            this.combo_anno.setEnabled(false);
        }

    }//GEN-LAST:event_combo_modeloActionPerformed

    private void combo_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_annoActionPerformed

        if (this.combo_anno.getSelectedIndex() != 0) {

            this.combo_color.removeAllItems();
            this.combo_color.addItem("Seleccionar Color");

            try {

                ResultSet rst = conn.selectColoresPorModelo((String) this.combo_marca.getSelectedItem(), (String) this.combo_modelo.getSelectedItem(), this.combo_anno.getSelectedItem());

                while (rst.next()) {

                    this.combo_color.addItem(rst.getObject("Color"));

                }

                rst = conn.selectPrecioPorModelo((String) this.combo_marca.getSelectedItem(), (String) this.combo_modelo.getSelectedItem(), this.combo_anno.getSelectedItem());

                while (rst.next()) {

                    this.lbl_precio.setText("$ " + Integer.toString((Integer) rst.getObject("Precio")));

                }

                rst.close();
                conn.desconectar();
                this.combo_color.setEnabled(true);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            this.combo_color.setEnabled(false);
            this.lbl_precio.setText("---");
        }

    }//GEN-LAST:event_combo_annoActionPerformed

    private void combo_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_colorActionPerformed

        if (this.combo_color.getSelectedIndex() > 0) {

            this.btn_seleccionar.setEnabled(true);

            try {

                ResultSet rst = conn.selectPlaca((String) this.combo_marca.getSelectedItem(), (String) this.combo_modelo.getSelectedItem(),
                        Integer.parseInt((String) this.combo_anno.getSelectedItem()), (String) this.combo_color.getSelectedItem());

                while (rst.next()) {
                    this.lbl_placa.setText("Placa: " + rst.getLong("Placa"));
                }

                rst.close();
                conn.desconectar();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            this.btn_seleccionar.setEnabled(false);
            this.lbl_placa.setText("---");

        }

    }//GEN-LAST:event_combo_colorActionPerformed

    private void btn_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarActionPerformed

        if (this.combo_color.getSelectedIndex() != 0) {

            modeloLista.addElement(this.combo_marca.getSelectedItem() + " " + this.combo_modelo.getSelectedItem() + " "
                    + this.combo_anno.getSelectedItem() + " " + this.combo_color.getSelectedItem() + " - " + this.lbl_placa.getText() + " - " + this.lbl_precio.getText());

            try {

                conn.cambiarEstadoComprado(Integer.parseInt(this.lbl_placa.getText().substring(7)));
                conn.desconectar();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.lista_autos_seleccionados.setModel(modeloLista);
            this.total += Integer.parseInt(this.lbl_precio.getText().substring(2));
            this.lbl_total.setText("Total: $ " + this.total);
        }

        this.combo_marca.setSelectedIndex(0);
        this.combo_modelo.setSelectedIndex(0);
        this.combo_anno.setSelectedIndex(0);
        this.combo_color.setSelectedIndex(0);

    }//GEN-LAST:event_btn_seleccionarActionPerformed

    private void lista_autos_seleccionadosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lista_autos_seleccionadosPropertyChange

        if (!this.modeloLista.isEmpty()) {

            this.btn_realizar_compra.setEnabled(true);

        } else {
            this.btn_realizar_compra.setEnabled(false);
        }

    }//GEN-LAST:event_lista_autos_seleccionadosPropertyChange

    private void btn_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrarActionPerformed

        try {

            String[] split = this.modeloLista.getElementAt(this.lista_autos_seleccionados.getSelectedIndex()).toString().split(" - ");
            this.total -= Integer.parseInt(split[2].substring(2));
            this.lbl_total.setText("Total: $ " + this.total);

            this.modeloLista.remove(this.lista_autos_seleccionados.getSelectedIndex());
            this.lista_autos_seleccionados.setModel(modeloLista);

            try {

                if (split[1].length() == 9) {
                    conn.cambiarEstadoDisponible(Integer.parseInt(split[1].substring(7)));
                } else {
                    conn.cambiarEstadoDisponible(Integer.parseInt(split[1].substring(7, 8)));
                }

                conn.desconectar();

                this.combo_marca.setSelectedIndex(0);
                this.combo_modelo.setSelectedIndex(0);
                this.combo_anno.setSelectedIndex(0);
                this.combo_color.setSelectedIndex(0);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ArrayIndexOutOfBoundsException a) {

            JOptionPane.showMessageDialog(null, "Seleccione el Automovil que desea borrar");

        }

        if (!this.modeloLista.isEmpty()) {

            this.btn_realizar_compra.setEnabled(true);

        } else {
            this.btn_realizar_compra.setEnabled(false);
        }


    }//GEN-LAST:event_btn_borrarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea salir?", "¡Atención!", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {

            for (int i = 0; i < modeloLista.size(); i++) {

                try {

                    String[] split = this.modeloLista.getElementAt(i).toString().split(" - ");

                    if (split[1].length() == 9) {
                        conn.cambiarEstadoDisponible(Integer.parseInt(split[1].substring(7)));
                    } else {
                        conn.cambiarEstadoDisponible(Integer.parseInt(split[1].substring(7, 8)));
                    }

                    conn.desconectar();

                    this.combo_marca.setSelectedIndex(0);
                    this.combo_modelo.setSelectedIndex(0);
                    this.combo_anno.setSelectedIndex(0);
                    this.combo_color.setSelectedIndex(0);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            System.exit(0);

        }

    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_realizar_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_realizar_compraActionPerformed

        if (this.txt_nombre_cliente.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Debe Ingresar su Nombre", "¡Atención!", 1);

        } else if (this.txt_cedula.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Debe Ingresar su Numero de Cédula", "¡Atención!", 1);

        } else if (this.txt_tarjeta.getText().isEmpty()) {

            JOptionPane.showMessageDialog(rootPane, "Debe Ingresar su Numero de Tarjeta", "¡Atención!", 1);

        } else {

            try {

                int placa;
                conn.agregarCliente(this.txt_nombre_cliente.getText(), Integer.parseInt(this.txt_cedula.getText()), Integer.parseInt(this.txt_tarjeta.getText()));
                int id = conn.generarFactura(Integer.parseInt(this.txt_cedula.getText()));

                for (int i = 0; i < modeloLista.size(); i++) {

                    String[] split = this.modeloLista.getElementAt(i).toString().split(" - ");
                    if (split[1].length() == 9) {
                        placa = Integer.parseInt(split[1].substring(7));
                    } else {
                        placa = Integer.parseInt(split[1].substring(7, 8));
                    }

                    conn.comprarAuto(id, placa);

                }

                try {

                    conn.conectar();
                    String dir = "C:Dirección en su computadora\\factura.jrxml";
                    JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
                    JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null, conn.getConn());
                    JasperViewer.viewReport(mostrarReporte);

                } catch (JRException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                conn.desconectar();
                JOptionPane.showMessageDialog(rootPane, "Compra Efectuada con Exito");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.modeloLista.removeAllElements();
            this.lista_autos_seleccionados.setModel(modeloLista);
            this.txt_nombre_cliente.setText("");
            this.txt_cedula.setText("");
            this.txt_tarjeta.setText("");

        }

    }//GEN-LAST:event_btn_realizar_compraActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new principal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_borrar;
    private javax.swing.JButton btn_realizar_compra;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_seleccionar;
    private javax.swing.JComboBox combo_anno;
    private javax.swing.JComboBox combo_color;
    private javax.swing.JComboBox combo_marca;
    private javax.swing.JComboBox combo_modelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_autos_seleccionados;
    private javax.swing.JLabel lbl_compra_vehiculos;
    private javax.swing.JLabel lbl_datos;
    private javax.swing.JLabel lbl_fondo;
    private javax.swing.JLabel lbl_nombre_cliente;
    private javax.swing.JLabel lbl_numero_cedula;
    private javax.swing.JLabel lbl_numero_tarjeta;
    private javax.swing.JLabel lbl_placa;
    private javax.swing.JLabel lbl_placa_titulo;
    private javax.swing.JLabel lbl_precio;
    private javax.swing.JLabel lbl_precio_titulo;
    private javax.swing.JLabel lbl_seleccionar_vehiculo;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JList lista_autos_seleccionados;
    private javax.swing.JFormattedTextField txt_cedula;
    private javax.swing.JTextField txt_nombre_cliente;
    private javax.swing.JFormattedTextField txt_tarjeta;
    // End of variables declaration//GEN-END:variables
}
