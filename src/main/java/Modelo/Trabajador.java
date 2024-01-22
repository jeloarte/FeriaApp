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
public class Trabajador implements Serializable{
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String sitioweb;
    private String redesSociales;
    public Trabajador(String c,String n,String t,String e,String d,String s,String r){
        cedula=c;
        nombre=n;
        telefono=t;
        email=e;
        direccion=d;
        sitioweb=s;
        redesSociales=r;
        
    }
    public void mostrarDatos(){
        System.out.println("Mostrar datos actuales:");
        System.out.println("Cedula: "+cedula);
        System.out.println("1. Nombre: "+nombre);
        System.out.println("2. Telefono: "+telefono);
        System.out.println("3. E-mail: "+email);
        System.out.println("4. Direccion: "+direccion);
        System.out.println("5. SitioWeb: "+sitioweb);
        System.out.println("6. Redes Sociales: "+redesSociales);
    }
    public String getCedula(){
        return cedula;
    }
    /*public void setCedula(int cedula){
        this.cedula=cedula;
    }*/
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
     public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSitioweb() {
        return sitioweb;
    }

    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }

    public String getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(String redesSociales) {
        this.redesSociales = redesSociales;
    }
    
}
