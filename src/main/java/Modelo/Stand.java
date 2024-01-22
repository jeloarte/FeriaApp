/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Stand implements Serializable,Comparable<Stand> {
    private static int codigoIncremental=1;
    private int codigo;
    private String nombre;
    private String descripcion;
    private String fechaAsignacion;
    private String reservado;
    private Emprendedor emprendedor;
    private Auspiciante auspiciante;
    
    private ArrayList<Integer> listadoCodigos; //listado de codigos de los trabajadores?? o de los stands
    
    public Stand(String a ,String d,String f, String r){
        this.codigo = codigoIncremental++;
        this.nombre= a;
        this.descripcion=d;
        this.fechaAsignacion=f;
        this.reservado=r;
        this.listadoCodigos = new ArrayList<>();
        
        
    }
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getter y Setter para 'descripcion'
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter para 'reservado'
    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    // Getter y Setter para 'nombre'
    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }
    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(Auspiciante auspiciante) {
        this.auspiciante= auspiciante;
    }

    // Getter y Setter para 'listadoCodigos'
    public ArrayList<Integer> getListadoCodigos() {
        return listadoCodigos;
    }

    public void setListadoCodigos(ArrayList<Integer> listadoCodigos) {
        this.listadoCodigos = listadoCodigos;
    }
    public boolean verificarCodigo(int c){
        if(listadoCodigos.contains(c)){
        System.out.println("Ya se encuentra registrada el codigo"+c);  
            return true;
        }
        else{
            System.out.println("No se encuentra registrada el codigo"+c);
            return false;
        }
    }
    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    // Setter para fechaAsignacion
    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Override
    public String toString() {
        return "Stand{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", fechaAsignacion=" + fechaAsignacion + ", reservado=" + reservado + ", emprendedor=" + emprendedor + ", auspiciante=" + auspiciante + ", listadoCodigos=" + listadoCodigos + '}';
    }
    @Override
    public int compareTo(Stand o) {
        
        return nombre.compareToIgnoreCase(o.nombre);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stand other = (Stand) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    public static ArrayList<Stand> cargarStand(String ruta) {
        ArrayList<Stand> stand = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
      
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            stand = (ArrayList<Stand>) oi.readObject();
            System.out.println("=============");
           
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return stand;
    }
}
