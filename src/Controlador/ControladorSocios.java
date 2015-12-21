/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.SocioDAO;
import DAO.SocioFila;
import Exception.MisException;
import Vista.BibliotecaGUI;
import Vista.SociosGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Iperez
 */
public class ControladorSocios implements ActionListener, MouseListener {
    private SociosGUI sGUI;
    private Map map;
    private SocioDAO sDAO;
    
    public ControladorSocios(BibliotecaGUI bGUI){
        this.sGUI = new SociosGUI();
        bGUI.getJdpPrincipal().add(sGUI);
        sDAO = new SocioDAO();
        //Cargamos Datos en el JTable
        cargarDatosSocios();
        //Controlamos los eventos
        map = new HashMap<String,String>();
        map.put("AÑADIR","AÑADIR");
        map.put("EDITAR","EDITAR");
        map.put("ELIMINAR","ELIMINAR");
        this.sGUI.setListener(this);
        this.sGUI.setComandos(this.map);
        this.sGUI.setMouseListener(this);
    }
    
    public void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) { 
        //Incluimos los datos a añadir en SocioFila
        SocioFila sf = new SocioFila(this.sGUI.getTxtApellidos().getText(),this.sGUI.getTxtNombre().getText(),this.sGUI.getTxtDireccion().getText(),this.sGUI.getDtcFechaAlta().getDate(),this.sGUI.getFtfDNI().getText());
        //Insertamos el socio en BD
        this.sDAO.InsertarSocio(sf);
        //Limpiamos la JTable
        borrarDatosSocios();
        //Volvemos a cargar los datos con el nuevo socio
        cargarDatosSocios();
        //Limpiamos los campos texto
        this.sGUI.getTxtNombre().setText("");
        this.sGUI.getTxtApellidos().setText("");
        this.sGUI.getTxtDireccion().setText("");
        this.sGUI.getFtfDNI().setText("");
        this.sGUI.getDtcFechaAlta().setDate(null);
        //Configuramos los botones
        this.sGUI.getBtnAnadir().setEnabled(true);
        this.sGUI.getBtnEditar().setEnabled(false);
        this.sGUI.getBtnEliminar().setEnabled(false);
    }                                         

    public void btnEditarActionPerformed(java.awt.event.ActionEvent evt) { 
        //Cogemos el IdSocio a modificar
        Integer idSocio = (Integer) this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 0);
        //Incluimos los datos a actualizar en SocioFila
        SocioFila sf = new SocioFila(idSocio, this.sGUI.getTxtApellidos().getText(),this.sGUI.getTxtNombre().getText(),this.sGUI.getTxtDireccion().getText(),this.sGUI.getDtcFechaAlta().getDate(),this.sGUI.getFtfDNI().getText());
        //Modificamos los datos del socio en BD
        this.sDAO.ModificarSocio(sf);
        //Limpiamos la JTable
        //borrarDatosSocios();
        //Volvemos a cargar los datos con el nuevo socio
        //cargarDatosSocios();
        
        //Modificar la columna del JTable
        this.sGUI.getModelo().setValueAt(this.sGUI.getTxtApellidos().getText(), this.sGUI.getTblSocios().getSelectedRow(), 1);
        this.sGUI.getModelo().setValueAt(this.sGUI.getTxtNombre().getText(), this.sGUI.getTblSocios().getSelectedRow(), 2);
        this.sGUI.getModelo().setValueAt(this.sGUI.getTxtDireccion().getText(), this.sGUI.getTblSocios().getSelectedRow(), 3);
        this.sGUI.getModelo().setValueAt(this.sGUI.getFtfDNI().getText(), this.sGUI.getTblSocios().getSelectedRow(), 4);
        this.sGUI.getModelo().setValueAt(this.sGUI.getDtcFechaAlta().getDate(), this.sGUI.getTblSocios().getSelectedRow(), 5);

        //Limpiamos los campos texto
        this.sGUI.getTxtNombre().setText("");
        this.sGUI.getTxtApellidos().setText("");
        this.sGUI.getTxtDireccion().setText("");
        this.sGUI.getFtfDNI().setText("");
        this.sGUI.getDtcFechaAlta().setDate(null);
        //Configuramos los botones
        this.sGUI.getBtnAnadir().setEnabled(true);
        this.sGUI.getBtnEditar().setEnabled(false);
        this.sGUI.getBtnEliminar().setEnabled(false);
    }                                         

    private void btnElminarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //Cogemos el IdSocio a modificar
        Integer idSocio = (Integer) this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 0);
        //Eliminamos el socio en BD
        this.sDAO.EliminarSocio(idSocio);
        //Limpiamos la JTable
        //borrarDatosSocios();
        //Volvemos a cargar los datos con el nuevo socio
        //cargarDatosSocios();        
        //Limpiamos los campos texto
        
        //Eliminamos el registro del JTable
        try {
            this.sGUI.getModelo().removeRow(this.sGUI.getTblSocios().getSelectedRow());
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new MisException("Error no tiene línea seleccionada\n"+e.toString());
            //System.out.println("No tiene seleccionada una línea.");
        }
        
        this.sGUI.getTxtNombre().setText("");
        this.sGUI.getTxtApellidos().setText("");
        this.sGUI.getTxtDireccion().setText("");
        this.sGUI.getFtfDNI().setText("");
        this.sGUI.getDtcFechaAlta().setDate(null);
        //Configuramos los botones
        this.sGUI.getBtnAnadir().setEnabled(true);
        this.sGUI.getBtnEditar().setEnabled(false);
        this.sGUI.getBtnEliminar().setEnabled(false);
    }                                          

    @Override
    public void actionPerformed(ActionEvent e) {
        String acto = e.getActionCommand();
                  
        if (acto.equalsIgnoreCase("AÑADIR")){
            this.btnAñadirActionPerformed(e);
        } 
        else if (acto.equalsIgnoreCase("EDITAR")) {
            this.btnEditarActionPerformed(e);
            borrarDatosSocios();
            cargarDatosSocios();
        }
        else if (acto.equalsIgnoreCase("ELIMINAR")){
            this.btnElminarActionPerformed(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.sGUI.getTxtNombre().setText((String) this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 1));
        this.sGUI.getTxtApellidos().setText((String) this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 2));
        this.sGUI.getTxtDireccion().setText((String) this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 3));
        this.sGUI.getFtfDNI().setText((String)this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 4) );
        this.sGUI.getDtcFechaAlta().setDate((Date)this.sGUI.getModelo().getValueAt(this.sGUI.getTblSocios().getSelectedRow(), 5) );
        this.sGUI.getBtnAnadir().setEnabled(false);
        this.sGUI.getBtnEditar().setEnabled(true);
        this.sGUI.getBtnEliminar().setEnabled(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void cargarDatosSocios() {
        TreeSet <SocioFila> ts = new TreeSet();
        SocioFila sf;
        int i = 0;
        //Obtenermos los datos de la tabla socios
        ts = sDAO.CargarDatos();
        //Creamos el iterator para recorrer el treeset
        Iterator <SocioFila> it = ts.iterator();
        //Cargamos los datos en el JTable
        while (it.hasNext()) {
            //sacamos el LibroFila el treeset
            sf = it.next();
            //cargamos la fila en la JTable
            this.sGUI.getModelo().insertRow(i, new Object[]
            {
                sf.getIdSocio(),
                sf.getApellidos(),
                sf.getNombre(),
                sf.getDireccion(),
                sf.getDNI(),
                sf.getFechaAlta()
            }        
            );
            i++;
        }
    }

    private void borrarDatosSocios() {
        while(this.sGUI.getModelo().getRowCount()>0){
            this.sGUI.getModelo().removeRow(0);
        }
    }
}
