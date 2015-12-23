package Modelo;

import DAO.*;
import com.toedter.calendar.JDateChooser;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class TablaPrestamoModelo extends DefaultTableModel {
    private String [] columnNames;
    private TreeSet<PrestamoFila> tPrestamos;
        
    public TablaPrestamoModelo () {
        PrestamosDAO pDAO = new PrestamosDAO();
        
        this.columnNames = new String []{
                                "Eliminar",
                                "IdPrestamo",
                                "Socio",
                                "Libro",
                                "FechaInicio",
                                "FechaFin"
                                
        };
        tPrestamos = pDAO.CargarDatos(); 
    }
    
    public String [] getColumnNames(){
        return this.columnNames;
    }

    public Object getData(){
        return this.tPrestamos;
    }

    @Override
    public String getColumnName(int col){
        return this.columnNames[col].toString();
    }
    @Override
    public int getRowCount() {
        if (this.tPrestamos != null){
            return (this.tPrestamos.size());
        }else{
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        if (this.columnNames != null){
            return (this.columnNames.length);
        }else{
            return 0;
        }    
    }
    
    private PrestamoFila SeleccionarFila (int rowIndex){
        PrestamoFila pf = null;

        int i = 0;
        
        Iterator <PrestamoFila> it = this.tPrestamos.iterator();
        while ((it.hasNext()) && (i <= rowIndex)){
            pf = it.next();
            i++;
        }
        return pf;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SocioDAO sd = new SocioDAO();
        LibroDAO ld = new LibroDAO();
        JDateChooser dt;
        
        PrestamoFila pf = SeleccionarFila(rowIndex);
        if (columnIndex == 1){
            //Devuelvo el ID del socio
            return pf.getIdPrestamo();
        }
        else if (columnIndex == 2){
            //Devuelvo el SocioFila encontrado para el ID
            return sd.BuscarSocio(pf.getIdSocio());
        }       
        else if(columnIndex == 3){
            //Devuelvo el LibroFila encontrado para el ID
            return ld.BuscarLibro(pf.getIdLibro());
        }
        else if (columnIndex == 4) {
            //Devuelvo el valor de la fecha inicio en un JDateChooser
            dt = new JDateChooser(pf.getFechaInicio());
            dt.setDateFormatString("dd/MM/yyyy");
            return dt;
        } 
        else if (columnIndex == 5) {
            //Devuelvo el valor de la fecha fin en un JDateChooser
            dt = new JDateChooser(pf.getFechaFin());
            dt.setDateFormatString("dd/MM/yyyy");
            return dt;
        }
        else {
            return pf.getEliminar();
        }
        
    }
    
    @Override
    public Class getColumnClass(int columnIndex)
   {
        if( columnIndex == 1)
        {
            return Integer.class;
        }
        else if ((columnIndex == 2) || (columnIndex == 3)){
            return JComboBox.class;
        }
        else if ((columnIndex == 4) || (columnIndex == 5))
        {
            return JDateChooser.class;
        }
        else {
            return Boolean.class;
        }
   }

    @Override
   public boolean isCellEditable(int row, int col){
        if ((col == 1) || (col == 4) || (col == 5)) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int col){
        PrestamoFila pf = this.SeleccionarFila(row);
        this.tPrestamos.remove(pf);
        if (col == 0) {
            pf.setEliminar((Boolean) value);
        }
        else if (col == 2) {
            SocioFila sf = (SocioFila) value;
            pf.setIdSocio(sf.getIdSocio());
        }
        else if (col == 3) {
            LibroFila lf = (LibroFila) value;
            pf.setIdLibro(lf.getIdLibro());
        }
        else if (col == 4) {
            JDateChooser dt = (JDateChooser) value;
            dt.setDateFormatString("dd/MM/yyyy");
            pf.setFechaInicio(dt.getDate()); 
        }
        else {
            JDateChooser dt = (JDateChooser) value;
            dt.setDateFormatString("dd/MM/yyyy");
            pf.setFechaFin(dt.getDate());
        }
        this.tPrestamos.add(pf);
        this.fireTableCellUpdated(row, col);
    }

    public void removeRow(int i) {
        PrestamoFila pf = this.SeleccionarFila(i);
        this.tPrestamos.remove(pf);
        this.fireTableRowsDeleted(i, i);
    }
    
    public void insertarPrestamoModelo(Integer i, PrestamoFila pf) {
        this.tPrestamos.add(pf);
        this.fireTableRowsInserted(i, i);
    }

}
