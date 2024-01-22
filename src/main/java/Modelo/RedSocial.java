/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL
 */
public class RedSocial {
    private Red redSocial;
    private String cuenta;
    
    public RedSocial(Red r, String c){
        redSocial=r;
        cuenta=c;
    }
    public Red getRedSocial(){
        return redSocial;
    }
    public void setRedSocial(Red redSocial){
        this.redSocial=redSocial;
    }
    public String getCuenta(){
        return cuenta;
    }
    public void setCuenta(String cuenta){
        this.cuenta=cuenta;
    }
}
