/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package DAO;

/**
 *
 * @author Iperez
 */
public class LibroFila implements Comparable {
    private String nombre;
    private String autor;
    private String tema;
    private Integer idLibro;
    
    public LibroFila (String n, String a, String t){
        this.nombre = n;
        this.autor = a;
        this.tema = t;
    }
    
    public LibroFila (Integer i, String n, String a, String t){
        this.idLibro = i;
        this.nombre = n;
        this.autor = a;
        this.tema = t;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    
    
    @Override
    public int compareTo(Object o) {
        int i = -1;
        if (!(o instanceof LibroFila)){
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
        return nombre.hashCode() + autor.hashCode();
    }
    
    public String toString() {
        return nombre;
    }
}    
