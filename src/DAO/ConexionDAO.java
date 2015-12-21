/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author Iperez
 */
public class ConexionDAO {
    private Connection con = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rst = null;
    private String coneccion = "jdbc:mysql://localhost:3306/uned?user=root";

    public void ConectarBD() {
        try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           this.con = DriverManager.getConnection(this.coneccion);
        } catch (SQLException e) { 
            System.out.println("Error en el SQL");
        } catch (ClassNotFoundException e){
            System.out.println("Error de registro del Driver JDBC o no esta la libreria Mysql J.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void DesconectarBD (){
        try {
                rst.close();
            } catch (Exception e) {}
            try {
                stm.close();
            } catch (Exception e){} 
            try {
                con.close();
            } catch (Exception e){}
    }
    
    public Connection getConexion(){
        return this.con;
    }
    
    public ResultSet ConsultaBD (String consulta){
        try{
            this.pstm = this.con.prepareStatement(consulta);
            this.rst = this.pstm.executeQuery();   
        } catch (SQLException e){
            System.out.println("Error en el SQL");
        }
        return this.rst;
    }

}
