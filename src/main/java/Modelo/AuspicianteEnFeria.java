/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
public class AuspicianteEnFeria {
    private Auspiciante auspiciante;
    private String descripcion;
    private boolean tiene;
    
    
    public AuspicianteEnFeria(Auspiciante a, String d, boolean t){
        auspiciante=a;
        descripcion=d;
        tiene=t;
    }
    
    public Auspiciante getAuspiciante(){
        return auspiciante;
    }
    public void setAuspiciante(Auspiciante auspiciante){
        this.auspiciante=auspiciante;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public boolean getTiene(){
        return tiene;
    }
    public void setTiene(boolean tiene){
        this.tiene=tiene;
    }
    
}
