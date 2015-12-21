/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Exception.MisException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

/**
 *
 * @author Iperez
 */
public class PrestamosDAO {
    private ConexionDAO conexion;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rst;
    TreeSet <PrestamoFila> tPrestamos;
    
    public PrestamosDAO (){
        conexion = new ConexionDAO();
        stm = null;
        pstm = null;
        rst = null;
        tPrestamos = new TreeSet();
    }
    
    public TreeSet<PrestamoFila> CargarDatos () {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        
        String consulta = "SELECT * FROM prestamos ORDER BY idprestamo";
        this.rst = this.conexion.ConsultaBD(consulta);
        try {
            //Pasamos los datos recogidos de la BD al TreeSet
            while (this.rst.next()){
               PrestamoFila pf = new PrestamoFila(rst.getInt("IdPrestamo"),rst.getInt("IdSocio"),rst.getInt("IdLibro"),rst.getDate("FechaInicio"),rst.getDate("FechaFin")); 
               this.tPrestamos.add(pf);
            }
        } catch (SQLException e){
            throw new MisException("Error al cargar los datos del Prestamo.\n"+e.toString());
            
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
        return this.tPrestamos;
    }
    public void InsertarPrestamo(PrestamoFila pf){
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "insert into prestamos (IdSocio, IdLibro, FechaInicio, FechaFin) values (?, ?, ?, ?)";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del insert
           this.pstm.setInt(1, pf.getIdSocio());
           this.pstm.setInt(2, pf.getIdLibro());
           this.pstm.setDate(3, new java.sql.Date(pf.getFechaInicio().getTime()));
           this.pstm.setDate(4, new java.sql.Date(pf.getFechaFin().getTime()));
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al insertar un Prestamo.\n"+e.toString());
            //System.out.println("Error al insertar un libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }
    
    public void ModificarPrestamo(PrestamoFila pf) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "update prestamos set IdSocio = ?, IdLibro = ?, FechaInicio = ?, FechaFin = ? where IdPrestamo = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del update
           this.pstm.setInt(1, pf.getIdSocio());
           this.pstm.setInt(2, pf.getIdLibro());
           this.pstm.setDate(3, new java.sql.Date(pf.getFechaInicio().getTime()));
           this.pstm.setDate(4, new java.sql.Date(pf.getFechaFin().getTime()));
           this.pstm.setInt(5, pf.getIdPrestamo());
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al modificar un Prestamo.\n"+e.toString());
            
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();        
    }
    
    public void EliminarPrestamo(Integer idPrestamo) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "delete from prestamos where IdPrestamo = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del delete
           this.pstm.setInt(1, idPrestamo);
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al eliminar un Prestamo.\n"+e.toString());
            //System.out.println("Error al eliminar un Libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }
}
