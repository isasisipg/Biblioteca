/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import DAO.ConexionDAO;
import Exception.MisException;
import java.util.TreeSet;
/**
 *
 * @author Iperez
 */
public class SocioDAO {
    private ConexionDAO conexion;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rst;
    TreeSet <SocioFila> tSocios;
    
    public SocioDAO() {
        conexion = new ConexionDAO();
        stm = null;
        pstm = null;
        rst = null;
        tSocios = new TreeSet();
    }

    public TreeSet<SocioFila> CargarDatos () {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        
        String consulta = "SELECT * FROM socios ORDER BY apellidos, nombre";
        this.rst = this.conexion.ConsultaBD(consulta);
        try {
            //Pasamos los datos recogidos de la BD al TreeSet
            while (this.rst.next()){
               SocioFila sf = new SocioFila(rst.getInt("IdSocio"),rst.getString("Apellidos"),rst.getString("Nombre"),rst.getString("Direccion"), rst.getDate("FechaAlta"),rst.getString("DNI")); 
               this.tSocios.add(sf);
            }
        } catch (SQLException e){
            throw new MisException("Error al cargar los datos de socios.\n"+e.toString());
            //System.out.println("Error al cargar los datos de socios.");
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
        return this.tSocios;
    }

    
    public void InsertarSocio(SocioFila sf){
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "insert into socios (Apellidos, Nombre, Direccion, DNI, FechaAlta) values (?, ?, ?, ?, ?)";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del insert
           this.pstm.setString(1,sf.getApellidos());
           this.pstm.setString(2, sf.getNombre());
           this.pstm.setString(3, sf.getDireccion());
           this.pstm.setString(4, sf.getDNI());
           this.pstm.setDate(5, new java.sql.Date(sf.getFechaAlta().getTime()));
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) {
            throw new MisException("Error al insertar un socio.\n"+e.toString());
            //System.out.println("Error al insertar un socio.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }
    
    public void ModificarSocio(SocioFila sf) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "update socios set Apellidos = ?, Nombre = ?, Direccion = ?, DNI = ?, FechaAlta = ? where IdSocio = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del update
           this.pstm.setString(1, sf.getApellidos());
           this.pstm.setString(2, sf.getNombre());
           this.pstm.setString(3, sf.getDireccion());
           this.pstm.setString(4, sf.getDNI());
           this.pstm.setDate(5, new java.sql.Date(sf.getFechaAlta().getTime()));
           this.pstm.setInt(6, sf.getIdSocio());
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) {
            throw new MisException("Error al modificar un socio.\n"+e.toString());
            //System.out.println("Error a modificar un socio.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();        
    }
    
    public void EliminarSocio(Integer idSocio) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "delete from socios where IdSocio = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del delete
           this.pstm.setInt(1, idSocio);
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al eliminar un Socio.\n"+e.toString());
            //System.out.println("Error al eliminar un socio.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }

    public SocioFila BuscarSocio(Integer idSocio) {
        SocioFila sf = null;
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "select * from socios where IdSocio = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los paramestros de la consulta
           this.pstm.setInt(1, idSocio);
           this.rst = pstm.executeQuery();
           while (this.rst.next()){
            sf = new SocioFila(rst.getInt("IdSocio"),rst.getString("Apellidos"),rst.getString("Nombre"),rst.getString("Direccion"),rst.getDate("FechaAlta"),rst.getString("DNI"));
           }
        } catch (SQLException e) { 
            throw new MisException("Error al buscar un Libro.\n"+e.toString());
            //System.out.println("Error al eliminar un Libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
        return sf;
    }
}
