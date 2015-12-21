/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.BibliotecaGUI;
import Vista.LibrosGUI;
import Vista.PrestamosGUI;
import Vista.SociosGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 *
 * @author Iperez
 */
public class ControladorMenu implements ActionListener{
    private BibliotecaGUI bGUI;
    private SociosGUI sociosGUI;
    private LibrosGUI librosGUI;
    private PrestamosGUI prestGUI;
    private Map<String,String> map;
    private ControladorSocios cSocios;
    private ControladorLibros cLibros;
    private ControladorPrestamos cPrestamos;
    
    public ControladorMenu() throws ClassNotFoundException {
        this.bGUI = new BibliotecaGUI();
        map = new HashMap<String,String>();
        map.put("SOCIOS","SOCIOS");
        map.put("LIBROS","LIBROS");
        map.put("PRESTAMOS","PRESTAMOS");
        this.bGUI.setListener(this);
        this.bGUI.setComandos(this.map);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acto = e.getActionCommand();
                  
        if (acto.equalsIgnoreCase("SOCIOS")){
            System.out.println("SOCIO");
            this.cSocios = new ControladorSocios(this.bGUI);
        } 
        else if (acto.equalsIgnoreCase("LIBROS")) {
            System.out.println("LIBRO");
            this.cLibros = new ControladorLibros(this.bGUI);
        }
        else if (acto.equalsIgnoreCase("PRESTAMOS")){
            System.out.println("PRESTAMO");
            this.cPrestamos = new ControladorPrestamos(this.bGUI);
        }
    }

    public BibliotecaGUI getbGUI() {
        return bGUI;
    }

    public void setbGUI(BibliotecaGUI bGUI) {
        this.bGUI = bGUI;
    }
    
    
}
