package Controlador;

import DAO.*;
import Editor.RenderJDateChooser;
import Vista.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.event.*;
import java.util.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

/**
 *
 * @author Iperez
 */
public class ControladorPrestamos implements ActionListener, MouseListener, TableModelListener, ListSelectionListener {
    private PrestamosGUI pGUI;
    private Map map;
    private PrestamosDAO pDAO;
    private JComboBox cbs, cbl;
    
    
    public ControladorPrestamos(BibliotecaGUI bGUI){
        this.pGUI = new PrestamosGUI();
        bGUI.getJdpPrincipal().add(pGUI);
        pDAO = new PrestamosDAO();
        //Controlamos los eventos
        map = new HashMap<String,String>();
        map.put("AÑADIR","AÑADIR");
        map.put("EDITAR","EDITAR");
        map.put("ELIMINAR","ELIMINAR");
        this.pGUI.setListener(this);
        this.pGUI.setComandos(this.map);
        this.pGUI.setMouseListener(this);
        //Activamos botones
        this.pGUI.getBtnInsertar().setEnabled(true);
        this.pGUI.getBtnEditar().setEnabled(false);
        this.pGUI.getBtnEliminar().setEnabled(false);       
        //Cargamos los datos de los combox y el JTable
        Cargar_Datos();
    }     

    private void Cargar_Datos() {
        //Cargamos los datos de los combox Socios y Libros
        Cargar_Datos_Socios(this.pGUI.getCmbSocios());
        Cargar_Datos_Libros(this.pGUI.getCmbLibros());
        //Cargar_CheckBox_JTable();
        Cargar_ComboBox_JTable();
        Cargar_JDateChooser_JTable();
        
    }

    private void Cargar_Datos_Socios(JComboBox cb) {
        SocioDAO sDAO = new SocioDAO();
        TreeSet<SocioFila> tSocio;
        SocioFila sf;
        
        //Traemos los libros de BD para cargarlos al combox
        tSocio = sDAO.CargarDatos();
        //Insertamos el primer dato del combox
        cb.addItem("Seleccionar...");
        //Creamos el iterador para recorer el TreeSet
        Iterator <SocioFila> it = tSocio.iterator();
        while (it.hasNext()) {
            sf = it.next();
            cb.addItem(sf);
        }
    }

    private void Cargar_Datos_Libros(JComboBox cb) {
        LibroDAO lDAO = new LibroDAO();
        TreeSet<LibroFila> tLibro;
        LibroFila lf;
        
        //Traemos los libros de BD para cargarlos al combox
        tLibro = lDAO.CargarDatos();
        //Insertamos el primer dato del combox
        cb.addItem("Seleccionar...");
        //Creamos el iterador para recorer el TreeSet
        Iterator <LibroFila> it = tLibro.iterator();
        while (it.hasNext()) {
            lf = it.next();
            cb.addItem(lf);
        }
     
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
        JDateChooser dt;
        //Ponemos los datos de la fila seleccionada del JTable en los campos
        this.pGUI.getCmbSocios().setSelectedItem((SocioFila) this.pGUI.getModelo().getValueAt(this.pGUI.getTblPrestamos().getSelectedRow(),2));
        this.pGUI.getCmbLibros().setSelectedItem((LibroFila) this.pGUI.getModelo().getValueAt(this.pGUI.getTblPrestamos().getSelectedRow(),3));
        dt = (JDateChooser) this.pGUI.getModelo().getValueAt(this.pGUI.getTblPrestamos().getSelectedRow(),4);
        this.pGUI.getDtcInicio().setDate(dt.getDate());
        dt = (JDateChooser) this.pGUI.getModelo().getValueAt(this.pGUI.getTblPrestamos().getSelectedRow(),5);
        this.pGUI.getDtcFin().setDate(dt.getDate());
        //Activar los botones.
        this.pGUI.getBtnInsertar().setEnabled(false);
        this.pGUI.getBtnEditar().setEnabled(true);
        this.pGUI.getBtnEliminar().setEnabled(true);
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

    private void btnAñadirActionPerformed(ActionEvent e) {
        SocioFila sf = (SocioFila) this.pGUI.getCmbSocios().getSelectedItem();
        LibroFila lf = (LibroFila) this.pGUI.getCmbLibros().getSelectedItem();
        //Incluimos los datos a añadir en SocioFila
        PrestamoFila pf = new PrestamoFila(sf.getIdSocio(),lf.getIdLibro(),this.pGUI.getDtcInicio().getDate(), this.pGUI.getDtcFin().getDate());
        //Insertamos el socio en BD
        pf.setIdPrestamo(this.pDAO.InsertarPrestamo(pf));
        //Añadir registro de prestamo al JTable
        this.pGUI.getModelo().insertarPrestamoModelo(this.pGUI.getModelo().getRowCount(), pf);
        //Limpiamos los campos texto
        this.pGUI.getCmbSocios().setSelectedIndex(0);
        this.pGUI.getCmbLibros().setSelectedIndex(0);
        this.pGUI.getDtcInicio().setDate(null);
        this.pGUI.getDtcFin().setDate(null);
        //Configuramos los botones
        this.pGUI.getBtnInsertar().setEnabled(true);
        this.pGUI.getBtnEditar().setEnabled(false);
        this.pGUI.getBtnEliminar().setEnabled(false);
    }

    private void btnEditarActionPerformed(ActionEvent e) {
        //Cogemos el IdPrestamo a modificar
        Integer idPrestamo = (Integer) this.pGUI.getModelo().getValueAt(this.pGUI.getTblPrestamos().getSelectedRow(), 1);
        //Recogemos los objetos de los combobox
        SocioFila sf = (SocioFila) this.pGUI.getCmbSocios().getSelectedItem();
        LibroFila lf = (LibroFila) this.pGUI.getCmbLibros().getSelectedItem();
        //Incluimos los datos a actualizar en PrestamoFila
        PrestamoFila pf = new PrestamoFila(idPrestamo, sf.getIdSocio(), lf.getIdLibro(), this.pGUI.getDtcInicio().getDate(), this.pGUI.getDtcFin().getDate());
        //Modificamos los datos del socio en BD
        this.pDAO.ModificarPrestamo(pf);
        
        //Modificar la columna del JTable
        this.pGUI.getModelo().setValueAt(this.pGUI.getCmbSocios().getSelectedItem(), this.pGUI.getTblPrestamos().getSelectedRow(), 2);
        this.pGUI.getModelo().setValueAt(this.pGUI.getCmbLibros().getSelectedItem(), this.pGUI.getTblPrestamos().getSelectedRow(), 3);
        this.pGUI.getModelo().setValueAt(this.pGUI.getDtcInicio(), this.pGUI.getTblPrestamos().getSelectedRow(), 4);
        this.pGUI.getModelo().setValueAt(this.pGUI.getDtcFin(), this.pGUI.getTblPrestamos().getSelectedRow(), 5);
         
        //Limpiamos los campos texto
        this.pGUI.getCmbSocios().setSelectedIndex(0);
        this.pGUI.getCmbLibros().setSelectedIndex(0);
        this.pGUI.getDtcInicio().setDate(null);
        this.pGUI.getDtcFin().setDate(null);
        //Configuramos los botones
        this.pGUI.getBtnInsertar().setEnabled(true);
        this.pGUI.getBtnEditar().setEnabled(false);
        this.pGUI.getBtnEliminar().setEnabled(false);
    }

    private void btnElminarActionPerformed(ActionEvent e) {
        ArrayList<Integer> al = new ArrayList();
        for (int i = 0; i<this.pGUI.getModelo().getRowCount();i++ ){
            
            if ((Boolean) this.pGUI.getTblPrestamos().getModel().getValueAt(i, 0)){
                //Cogemos el IdPrestamo a eliminar
                Integer idPrestamo = (Integer) this.pGUI.getModelo().getValueAt(i, 1);
                //Eliminamos el prestamo de BD
                this.pDAO.EliminarPrestamo(idPrestamo);
                //Añadimos al ArrayList las filas a eliminar el JTable
                al.add(i);
            }
        }
        //Borramos las filas del JTable
        int x = 0;
        while (al.size()>0){
            this.pGUI.getModelo().removeRow(al.get(0)-x);
            al.remove(0);
            x++;
        }

        //Limpiamos los campos texto
        this.pGUI.getCmbSocios().setSelectedIndex(0);
        this.pGUI.getCmbLibros().setSelectedIndex(0);
        this.pGUI.getDtcInicio().setDate(null);
        this.pGUI.getDtcFin().setDate(null);
        //Configuramos los botones
        this.pGUI.getBtnInsertar().setEnabled(true);
        this.pGUI.getBtnEditar().setEnabled(false);
        this.pGUI.getBtnEliminar().setEnabled(false);
    }

    private void IncluirFechaAJTable(int columna){
        //Seleccionamos la columna
        TableColumn tc = this.pGUI.getTblPrestamos().getColumnModel().getColumn(columna);
        //Incluimos el JDateChooser a la tabla con un Renderer y Editor diseñados.
        tc.setCellRenderer(new RenderJDateChooser());
        tc.setCellEditor(new JDateChooserCellEditor());
        
    }
    
    private void Cargar_JDateChooser_JTable(){
        //Incluimos la FechaInicio a la tabla
        IncluirFechaAJTable(4);
        //Incluimos la FechaFin a la tabla
        IncluirFechaAJTable(5);
    }
    
    private void IncluirComBoxAJTable(JComboBox cb, int columna) {
        //Seleccionamos la columna
        TableColumn tc = this.pGUI.getTblPrestamos().getColumnModel().getColumn(columna);
        //Asignamos el Editor para el ComboBox
        tc.setCellEditor(new DefaultCellEditor(cb));
    }

    private void Cargar_ComboBox_JTable() {
        //Creamos el combobox de socio para incluirlo en el JTable
        cbs = new JComboBox();
        Cargar_Datos_Socios(cbs);
        IncluirComBoxAJTable(cbs, 2);
        
        //Creamos el combobox de libro para incluirlo en el JTable
        cbl = new JComboBox();
        Cargar_Datos_Libros(cbl);
        IncluirComBoxAJTable(cbl, 3);
    }
    
    private void BorrarDatosPrestamos() {
        while(this.pGUI.getModelo().getRowCount()>0){
            this.pGUI.getModelo().removeRow(0);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        boolean valor = ((Boolean) this.pGUI.getTblPrestamos().getValueAt(e.getFirstRow(), 5)).booleanValue();
     
        if (valor){
                System.out.println("TableChanged ejecutado");
        }    
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        if (lsm.isSelectionEmpty()) {
            System.out.println("No hay fila seleccionado.");
        } else {
            int selectedRow = lsm.getMinSelectionIndex();
            System.out.println("Row " + selectedRow
                               + " is now selected.");
        }}

}
