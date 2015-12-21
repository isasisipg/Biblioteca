/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Modelo.TablaPrestamoModelo;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author Usuario
 */
public class JTablePrestamos extends JTable {
    public JTablePrestamos(){
        super();
    }

    public JTablePrestamos(TablaPrestamoModelo datos){
        super(datos);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
        Date hoy = new Date(); //Fecha de hoy 

        Component c = super.prepareRenderer(renderer, row, column);
        Date dtf = ((JDateChooser) super.getValueAt(row, 5)).getDate();
        long diferencia = ( dtf.getTime() - hoy.getTime() )/MILLSECS_PER_DAY; 
        if (diferencia < 5){
            c.setBackground(Color.RED);
        } 
        else if ((diferencia >= 5) && (diferencia < 15)) {
            c.setBackground(Color.YELLOW);
        }
        else {
            c.setBackground(Color.LIGHT_GRAY);
        }

        
        return c;
    }
}
