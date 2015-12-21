/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Date;

/**
 *
 * @author Iperez
 */
public class SocioFila implements Comparable{
    private String nombre;
    private String apellidos; 
    private String direccion;
    private Date fechaAlta;
    private String DNI;
    private Integer idSocio;
    
    public SocioFila(String a, String n, String d, Date fecha, String dni){
        this.nombre = n;
        this.apellidos = a;
        this.direccion = d;
        this.fechaAlta = fecha;
        this.DNI = dni;
    }
    
    public SocioFila(Integer i, String a, String n, String d, Date fecha, String dni){
        this.idSocio = i;
        this.nombre = n;
        this.apellidos = a;
        this.direccion = d;
        this.fechaAlta = fecha;
        this.DNI = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    @Override
    public int compareTo(Object o) {
        int i = -1;
        if (!(o instanceof SocioFila)){
            i = -1;
        }else if (this.hashCode() == o.hashCode()){
            i = 0;
        }else if (this.hashCode() < o.hashCode()){
            i = -1;
        }else {
            i = 1;
        }
        return i;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass())){
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
    
    @Override
    public int hashCode() {
        return nombre.hashCode() + apellidos.hashCode() + DNI.hashCode();
    }
    
    public String toString() {
        return apellidos + ", " + nombre;
    }
    
}
