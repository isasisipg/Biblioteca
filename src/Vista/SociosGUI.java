/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Exception.MisException;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Iperez
 */
public class SociosGUI extends javax.swing.JInternalFrame {
    private MaskFormatter patron;
    private DefaultTableModel modelo;

    /**
     * Creates new form SociosGUI
     */
    public SociosGUI() {
        try {
            patron = new MaskFormatter("##.###.###-?");
        } catch (ParseException e) {
            throw new MisException("Error en la mascara del DNI.\n"+e.toString());
        }
        initComponents();
        this.modelo = (DefaultTableModel) this.tblSocios.getModel();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblDNI = new javax.swing.JLabel();
        ftfDNI = new javax.swing.JFormattedTextField(patron);
        lblFechaAlta = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAnadir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSocios = new javax.swing.JTable();
        dtcFechaAlta = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Socios");

        lblNombre.setText("Nombre:");

        lblApellidos.setText("Apellidos:");

        lblDireccion.setText("Dirección:");

        lblDNI.setText("D.N.I.:");

        ftfDNI.setText("00000000-X");

        lblFechaAlta.setText("Fecha Alta:");

        btnAnadir.setText("Añadir");

        btnEditar.setText("Editar");

        btnEliminar.setText("Eliminar");

        tblSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdSocio", "Apellidos", "Nombre", "Dirección", "DNI", "Fecha Alta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSocios);

        dtcFechaAlta.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblDireccion)
                            .addComponent(lblApellidos)
                            .addComponent(lblDNI))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ftfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(lblFechaAlta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtcFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnadir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnadir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDNI)
                        .addComponent(ftfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFechaAlta))
                    .addComponent(dtcFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setListener(ActionListener accion) {
        this.btnAnadir.addActionListener(accion);
        this.btnEditar.addActionListener(accion);
        this.btnEliminar.addActionListener(accion);
    }

    public void setMouseListener(MouseListener mouse) {
        this.tblSocios.addMouseListener(mouse);
    }

    public void setComandos(Map<String, String> comando) {
        //Control de errores
        try {
            this.btnAnadir.setActionCommand(comando.get("AÑADIR"));
            this.btnEditar.setActionCommand(comando.get("EDITAR"));
            this.btnEliminar.setActionCommand(comando.get("ELIMINAR"));
        } catch (NullPointerException e) {
            System.out.println("Error de NullPointer Exception");
        } catch (ClassCastException e) {
            System.out.println("Error de ClassCast Exception");
        } catch (Exception e) {
            System.out.println("Otro Error");

        }
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public void setBtnAnadir(JButton btnAnadir) {
        this.btnAnadir = btnAnadir;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JDateChooser getDtcFechaAlta() {
        return dtcFechaAlta;
    }

    public void setDtcFechaAlta(JDateChooser dtcFechaAlta) {
        this.dtcFechaAlta = dtcFechaAlta;
    }

    public JFormattedTextField getFtfDNI() {
        return ftfDNI;
    }

    public void setFtfDNI(JFormattedTextField ftfDNI) {
        this.ftfDNI = ftfDNI;
    }

    public JTable getTblSocios() {
        return tblSocios;
    }

    public void setTblSocios(JTable tblSocios) {
        this.tblSocios = tblSocios;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public void setTxtApellidos(JTextField txtApellidos) {
        this.txtApellidos = txtApellidos;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(JTextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private com.toedter.calendar.JDateChooser dtcFechaAlta;
    private javax.swing.JFormattedTextField ftfDNI;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tblSocios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
