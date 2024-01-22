/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Seccion {
    private String nombreSeccion;
    private int cantStandxSeccion;
    private ArrayList<Stand> stands;
    
    public Seccion(String nombreSeccion, int cantStandxSeccion) {
        this.nombreSeccion = nombreSeccion;
        this.cantStandxSeccion = cantStandxSeccion;
    }

    // Getter para nombreSeccion
    public String getNombreSeccion() {
        return nombreSeccion;
    }

    // Setter para nombreSeccion
    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    // Getter para cantStandxSeccion
    public int getCantStandxSeccion() {
        return cantStandxSeccion;
    }

    // Setter para cantStandxSeccion
    public void setCantStandxSeccion(int cantStandxSeccion) {
        this.cantStandxSeccion = cantStandxSeccion;
    }

    // Getter para stands
    public ArrayList<Stand> getStands() {
        return stands;
    }

    // Setter para stands
    public void setStands(ArrayList<Stand> stands) {
        this.stands = stands;
    }
    
    public void agregarStand(Stand s){
        stands.add(s);
    }

    @Override
    public String toString() {
        return "Seccion{" + "nombreSeccion=" + nombreSeccion + ", cantStandxSeccion=" + cantStandxSeccion + ", stands=" + stands + '}';
    }
    
}
