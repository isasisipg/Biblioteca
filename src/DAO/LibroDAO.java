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
public class LibroDAO {
    private ConexionDAO conexion;
    private Statement stm;
    private PreparedStatement pstm;
    private ResultSet rst;
    TreeSet <LibroFila> tLibros;

    public LibroDAO(){
        conexion = new ConexionDAO();
        stm = null;
        pstm = null;
        rst = null;
        tLibros = new TreeSet();
    }
    public TreeSet<LibroFila> CargarDatos () {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        
        String consulta = "SELECT * FROM libros ORDER BY nombre, autor";
        this.rst = this.conexion.ConsultaBD(consulta);
        try {
            //Pasamos los datos recogidos de la BD al TreeSet
            while (this.rst.next()){
               LibroFila lf = new LibroFila(rst.getInt("IdLibro"),rst.getString("Nombre"),rst.getString("Autor"),rst.getString("Tema")); 
               this.tLibros.add(lf);
            }
        } catch (SQLException e){
            throw new MisException("Error al cargar los datos de libro.\n"+e.toString());
            //System.out.println("Error al cargar los datos de libro.");
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
        return this.tLibros;
    }

    
    public void InsertarLibro(LibroFila lf){
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "insert into libros (Nombre, Autor, Tema) values (?, ?, ?)";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del insert
           this.pstm.setString(1, lf.getNombre());
           this.pstm.setString(2, lf.getAutor());
           this.pstm.setString(3, lf.getTema());
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al insertar un Libro.\n"+e.toString());
            //System.out.println("Error al insertar un libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }
    
    public void ModificarLibro(LibroFila lf) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "update libros set Nombre = ?, Autor = ?, Tema = ? where IdLibro = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del update
           this.pstm.setString(1, lf.getNombre());
           this.pstm.setString(2, lf.getAutor());
           this.pstm.setString(3, lf.getTema());
           this.pstm.setInt(4, lf.getIdLibro());
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al modificar un Libro.\n"+e.toString());
            //System.out.println("Error a modificar un libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();        
    }
    
    public void EliminarLibro(Integer idLibro) {
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "delete from libros where IdLibro = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del delete
           this.pstm.setInt(1, idLibro);
           //Ejecutamos la consulta
           int res = pstm.executeUpdate();
        } catch (SQLException e) { 
            throw new MisException("Error al eliminar un Libro.\n"+e.toString());
            //System.out.println("Error al eliminar un Libro.");
        } catch (Exception e) {
            throw new MisException("Error.\n"+e.toString());
            //e.printStackTrace();
        }
        
        //Desconectamos de la BD
        this.conexion.DesconectarBD();
    }

    public LibroFila BuscarLibro (Integer idLibro){
        LibroFila lf = null;
        //Conectamos a la BD
        this.conexion.ConectarBD();
        String consulta = "select * from libros where IdLibro = ?";
        try {
           this.pstm = this.conexion.getConexion().prepareStatement(consulta);
           //Indicamos los parametros del delete
           this.pstm.setInt(1, idLibro);
           this.rst = pstm.executeQuery();
           while (this.rst.next()){
            lf = new LibroFila(rst.getInt("IdLibro"),rst.getString("Nombre"),rst.getString("Autor"),rst.getString("Tema"));
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
        return lf;
    }
}
