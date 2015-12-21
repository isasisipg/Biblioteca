/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.*;
import Exception.MisException;
import Vista.BibliotecaGUI;
import Vista.LibrosGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;


/**
 *
 * @author Iperez
 */
public class ControladorLibros implements ActionListener, MouseListener {
    private LibrosGUI lGUI;
    private Map map;
    private LibroDAO lDAO;
    
    public ControladorLibros(BibliotecaGUI bGUI){
        this.lGUI = new LibrosGUI();
        bGUI.getJdpPrincipal().add(lGUI);
        this.lDAO = new LibroDAO();
        //Cargamos Datos en el JTable
        cargarDatosLibros();
        //Controlamos los eventos
        map = new HashMap<String,String>();
        map.put("AÑADIR","AÑADIR");
        map.put("EDITAR","EDITAR");
        map.put("ELIMINAR","ELIMINAR");
        this.lGUI.getBtnAnadir().setEnabled(true);
        this.lGUI.getBtnEditar().setEnabled(false);
        this.lGUI.getBtnEliminar().setEnabled(false);
        this.lGUI.setListener(this);
        this.lGUI.setComandos(this.map);
        this.lGUI.setMouseListener(this);
    }    

    public void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) { 
        //Incluimos los datos a añadir en SocioFila
        LibroFila lf = new LibroFila(this.lGUI.getTxtNombre().getText(),this.lGUI.getTxtAutor().getText(),(String) this.lGUI.getCmbTema().getSelectedItem());
        //Insertamos el socio en BD
        this.lDAO.InsertarLibro(lf);
        //Limpiamos la JTable
        borrarDatosLibros();
        //Volvemos a cargar los datos con el nuevo socio
        cargarDatosLibros();
        //Insertamos la fila en el JTable
        //try {
        //    this.lGUI.getModelo().insertRow(this.lGUI.getModelo().getRowCount(), new Object [] {(String)this.lGUI.getTxtNombre().getText(),(String) this.lGUI.getTxtAutor().getText(),(String) this.lGUI.getCmbTema().getSelectedItem()});
        //}
        //catch (ArrayIndexOutOfBoundsException e) {
        //    System.out.println("Error al insertar una línea.");
        //}
        
        //Limpiamos los campos texto
        this.lGUI.getTxtNombre().setText("");
        this.lGUI.getTxtAutor().setText("");
        this.lGUI.getCmbTema().setSelectedIndex(0);
        //Configuramos los botones
        this.lGUI.getBtnAnadir().setEnabled(true);
        this.lGUI.getBtnEditar().setEnabled(false);
        this.lGUI.getBtnEliminar().setEnabled(false);
    }                                         

    public void btnEditarActionPerformed(java.awt.event.ActionEvent evt) { 
        //Cogemos el IdSocio a modificar
        Integer idLibro = (Integer) this.lGUI.getModelo().getValueAt(this.lGUI.getTblLibros().getSelectedRow(), 0);
        //Incluimos los datos a actualizar en SocioFila
        LibroFila lf = new LibroFila(idLibro, this.lGUI.getTxtNombre().getText(),this.lGUI.getTxtAutor().getText(),(String) this.lGUI.getCmbTema().getSelectedItem());
        //Modificamos los datos del socio en BD
        this.lDAO.ModificarLibro(lf);
        //Limpiamos la JTable
        //borrarDatosLibros();
        //Volvemos a cargar los datos con el nuevo socio
        //cargarDatosLibros();
        
        //Modificar la columna del JTable
        this.lGUI.getModelo().setValueAt(this.lGUI.getTxtNombre().getText(), this.lGUI.getTblLibros().getSelectedRow(), 1);
        this.lGUI.getModelo().setValueAt(this.lGUI.getTxtAutor().getText(), this.lGUI.getTblLibros().getSelectedRow(), 2);
        this.lGUI.getModelo().setValueAt(this.lGUI.getCmbTema().getSelectedItem(), this.lGUI.getTblLibros().getSelectedRow(), 3);
        
        //Limpiamos los campos texto
        this.lGUI.getTxtNombre().setText("");
        this.lGUI.getTxtAutor().setText("");
        this.lGUI.getCmbTema().setSelectedIndex(0);
        //Configuramos los botones
        this.lGUI.getBtnAnadir().setEnabled(true);
        this.lGUI.getBtnEditar().setEnabled(false);
        this.lGUI.getBtnEliminar().setEnabled(false);
    }                                         

    private void btnElminarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //Cogemos el IdSocio a modificar
        Integer idLibro = (Integer) this.lGUI.getModelo().getValueAt(this.lGUI.getTblLibros().getSelectedRow(), 0);
        //Eliminamos el socio en BD
        this.lDAO.EliminarLibro(idLibro);
        //Limpiamos la JTable
        //borrarDatosLibros();
        //Volvemos a cargar los datos con el nuevo socio
        //cargarDatosLibros();        
        
        //Eliminamos el registro del JTable
        try {
            this.lGUI.getModelo().removeRow(this.lGUI.getTblLibros().getSelectedRow());
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new MisException("Error borrar fila JTable\n"+e.toString());
            //System.out.println("No tiene seleccionada una línea.");
        }
        
        //Limpiamos los campos texto
        this.lGUI.getTxtNombre().setText("");
        this.lGUI.getTxtAutor().setText("");
        this.lGUI.getCmbTema().setSelectedIndex(0);
        //Configuramos los botones
        this.lGUI.getBtnAnadir().setEnabled(true);
        this.lGUI.getBtnEditar().setEnabled(false);
        this.lGUI.getBtnEliminar().setEnabled(false);
    }                                          

    @Override
    public void actionPerformed(ActionEvent e) {
        String acto = e.getActionCommand();
                  
        if (acto.equalsIgnoreCase("AÑADIR")){
            this.btnAñadirActionPerformed(e);
        } 
        else if (acto.equalsIgnoreCase("EDITAR")) {
            this.btnEditarActionPerformed(e);
        }
        else if (acto.equalsIgnoreCase("ELIMINAR")){
            this.btnElminarActionPerformed(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.lGUI.getTxtNombre().setText((String) this.lGUI.getModelo().getValueAt(this.lGUI.getTblLibros().getSelectedRow(), 1));
        this.lGUI.getTxtAutor().setText((String) this.lGUI.getModelo().getValueAt(this.lGUI.getTblLibros().getSelectedRow(), 2));
        this.lGUI.getCmbTema().setSelectedItem((String) this.lGUI.getModelo().getValueAt(this.lGUI.getTblLibros().getSelectedRow(), 3));
        this.lGUI.getBtnAnadir().setEnabled(false);
        this.lGUI.getBtnEditar().setEnabled(true);
        this.lGUI.getBtnEliminar().setEnabled(true);
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

    private void cargarDatosLibros() {
        TreeSet <LibroFila> tl = new TreeSet();
        LibroFila lf;
        int i = 0;
        //Obtenermos los datos de la tabla socios
        tl = lDAO.CargarDatos();
        //Creamos el iterator para recorrer el treeset
        Iterator <LibroFila> it = tl.iterator();
        //Cargamos los datos en el JTable
        while (it.hasNext()) {
            //sacamos el LibroFila el treeset
            lf = it.next();
            //cargamos la fila en la JTable
            this.lGUI.getModelo().insertRow(i, new Object[]
            {
                lf.getIdLibro(),
                lf.getNombre(),
                lf.getAutor(),
                lf.getTema()
            }        
            );
            i++;
        }
    }

    private void borrarDatosLibros() {
        while(this.lGUI.getModelo().getRowCount()>0){
            this.lGUI.getModelo().removeRow(0);
        }
    }

}
