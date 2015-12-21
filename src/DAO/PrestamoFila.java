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
public class PrestamoFila implements Comparable {
    private Integer idPrestamo;
    private Integer idSocio;
    private Integer idLibro;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean eliminar;
    
    public PrestamoFila (Integer is, Integer il, Date fi, Date ff){
        this.idLibro = il;
        this.idSocio = is;
        this.fechaInicio = fi;
        this.fechaFin = ff;
        this.eliminar = false;
    }
    
    public PrestamoFila (Integer ip, Integer is, Integer il, Date fi, Date ff){
        this.idLibro = il;
        this.idSocio = is;
        this.fechaInicio = fi;
        this.fechaFin = ff;
        this.idPrestamo = ip; 
        this.eliminar = false;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
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
        return idSocio.hashCode() + idLibro.hashCode() + fechaInicio.hashCode() + fechaFin.hashCode();
    }    
    
    @Override
    public int compareTo(Object o) {
        int i = -1;
        if (!(o instanceof PrestamoFila)){
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
    
}
