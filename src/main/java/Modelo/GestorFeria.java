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
public class GestorFeria {
    private ArrayList<Feria> ferias; // Es necesario esta clase despues de lo que hizo jeremy?
    
    public GestorFeria(ArrayList<Feria> ferias) {
        this.ferias = new ArrayList<>();
    }

    public ArrayList<Feria> getFerias() {
        return ferias;
    }

    public void setFerias(ArrayList<Feria> ferias) {
        this.ferias = ferias;
    }
    
    public void agregarFerias(Feria f){
        ferias.add(f);
    }
    
}
