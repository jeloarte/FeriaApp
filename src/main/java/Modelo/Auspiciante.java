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
public class Auspiciante extends Trabajador implements Comparable<Auspiciante>  {

    private ArrayList<Integer> guardarCedulas;
   
    
    public Auspiciante(String c,String n,String t,String e,String d,String s,String r){
        super(c,n,t,e,d,s,r);
        
    }

    
    public boolean verificarCedula(int c){
        if(guardarCedulas.contains(c)){
        System.out.println("Ya se encuentra registrada la cedula "+c);  
            return true;
        }
        else{
            System.out.println("No se encuentra registrada la cedula "+c);
            return false;
        }
    }
    
    public void agregarCedula(int c){
        if(verificarCedula(c)==false){
            guardarCedulas.add(c);
        }
    }
    
   
    public ArrayList<Integer> getGuardarCedulas(){
        return guardarCedulas;
        
    }
    @Override
    public int compareTo(Auspiciante o) {
        
        return getCedula().compareToIgnoreCase(o.getCedula());
    }
    public static ArrayList<Auspiciante> cargarAuspiciante(String ruta) {
        ArrayList<Auspiciante> auspiciante = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
     
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            auspiciante = (ArrayList<Auspiciante>) oi.readObject();
            System.out.println("=============");
          
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return auspiciante;
    }
@Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.getCedula());
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
        final Auspiciante other = (Auspiciante) obj;
        if (!Objects.equals(this.getCedula(), other.getCedula())) {
            return false;
        }
        return true;
    }

    
}
