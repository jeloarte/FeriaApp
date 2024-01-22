/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Emprendedor extends Trabajador implements Comparable<Emprendedor> {

    private ArrayList<Emprendedor> emprendedores;
    
    public Emprendedor(String c,String n,String t,String e,String d,String s,String r){
        super(c,n,t,e,d,s,r);
      
    }
    

    
    public void añadirEmprendedor(Emprendedor e){
      
    }
    public void editarEmprendedor(){
       
    }
    
   
    public String toString(){
        return "1. Registrar emprendedor\n" +
        "2. Editar emprendedor\n" +
        "3. Regresar\n" ;
    }
    
    public ArrayList<Emprendedor> getEmprendedores(){
        return emprendedores;
    }
    public void setEmprendedores(ArrayList<Emprendedor> emprendedores){
        this.emprendedores=emprendedores;
    }
    public void AgregarEmprendedores(Emprendedor emprendedor){
        emprendedores.add(emprendedor);
    }
    public boolean existeEmprendedor(String cedula) {
    for (Emprendedor emprendedor : emprendedores) {
        if (emprendedor.getCedula().equals(cedula)) {
            return true; 
        }
    }
    return false; 
    }

    public int compareTo(Emprendedor o) {
        
        return getCedula().compareToIgnoreCase(o.getCedula());
    }
    public static ArrayList<Emprendedor> cargarEmprendedor(String ruta) {
        ArrayList<Emprendedor> emprendedor = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
      
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            emprendedor = (ArrayList<Emprendedor>) oi.readObject();
            System.out.println("=============");
          
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return emprendedor;
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
        final Emprendedor other = (Emprendedor) obj;
        if (!Objects.equals(this.getCedula(), other.getCedula())) {
            return false;
        }
        return true;
    }
    
}
