/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.mycompany.appferia.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Feria implements Serializable,Comparable<Feria> {
    private static int codigoIncremental=1;
    private int codigo;
    private String nombre;
    private String descripcion;
    private String lugar;
    private String fechaInicio; //ojito que si no compila f
    private String fechaFinal; 
    private String horario;
    //instancias para guardar los datos
    private ArrayList<Integer> codigosFeria;
    private ArrayList<Emprendedor> emprendedores;
    private ArrayList<Auspiciante> auspiciantes;
    private ArrayList<Seccion> secciones; 
    private ArrayList<Stand> stands;
    
    public Feria(String nombre, String descripcion, String lugar, String fechaInicio, String fechaFinal, String horario) {
        this.codigo = codigoIncremental++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.horario = horario;
        this.codigosFeria = new ArrayList<>();
        this.emprendedores = new ArrayList<>();
        this.auspiciantes = new ArrayList<>();
        this.secciones = new ArrayList<>();
        this.stands = new ArrayList<>();
        
    }
    public ArrayList<Stand> getStands() {
        return stands;
    }
    @Override
public String toString() {
    return "Feria{" +
            "codigo=" + codigo +
            ", nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", lugar='" + lugar + '\'' +
            ", fechaInicio='" + fechaInicio + '\'' +
            ", fechaFinal='" + fechaFinal + '\'' +
            ", horario='" + horario + '\'' +
            ", codigosFeria=" + codigosFeria +
            ", emprendedores=" + emprendedores +
            ", auspiciantes=" + auspiciantes +
            ", secciones=" + secciones +
            '}';
}

    
    public void InformacionFeria(){
        System.out.println("1. Nombre: " +nombre+
        "\n2. Descripción: " +descripcion+
        "\n3. Lugar: " +lugar+
        "\n4. Fecha de inicio: " +fechaInicio+
        "\n5. Fecha de fin: " +fechaFinal+
        "\n6. Horario: " +horario+
        "\n7. Lista de auspiciantes: " +auspiciantes+
        "\n8. Cantidad de stands en cada sección: "); //utilizo un for y reccorro cada elemento de la lista para aplicar metodo?
        for (Seccion i:secciones){
            System.out.println(i.getNombreSeccion()+":"+i.getCantStandxSeccion());
        }
    }  
    
    public boolean fechaInicioFeria(String fechaactual){
        //sabiendo que la manera de ingresar la fecha es "yyyy-mm-dd"
        String[] partess=fechaactual.split("-");
        int año=Integer.parseInt(partess[0]);
        int mes=Integer.parseInt(partess[1]);
        int dia=Integer.parseInt(partess[2]);
        
        String[] partes=fechaInicio.split("-");
        int añoI=Integer.parseInt(partes[0]);
        int mesI=Integer.parseInt(partes[1]);
        int diaI=Integer.parseInt(partes[2]);
        //
        if (añoI>=año && mesI>=mes && diaI>=dia){
            
            return false;
        }
        else{
            //la fecha se ha pasado
            return true;
        }
    }
    
    
    
    
    
    
    // Getter para codigo
    public int getCodigo() {
        return codigo;
    }



    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter para lugar
    public String getLugar() {
        return lugar;
    }

    // Setter para lugar
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // Getter para fechaInicio
    public String getFechaInicio() {
        return fechaInicio;
    }

    // Setter para fechaInicio
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Getter para fechaFinal
    public String getFechaFinal() {
        return fechaFinal;
    }

    // Setter para fechaFinal
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    // Getter para horario
    public String getHorario() {
        return horario;
    }

    // Setter para horario
    public void setHorario(String horario) {
        this.horario = horario;
    }

    // Getter para emprendedores
    public ArrayList<Emprendedor> getEmprendedores() {
        return emprendedores;
    }

    // Setter para emprendedores
    public void setEmprendedores(ArrayList<Emprendedor> emprendedores) {
        this.emprendedores = emprendedores;
    }

    // Getter para auspiciantes
    public ArrayList<Auspiciante> getAuspiciantes() {
        return auspiciantes;
    }

    // Setter para auspiciantes
    public void setAuspiciantes(ArrayList<Auspiciante> auspiciantes) {
        this.auspiciantes = auspiciantes;
    }

    // Getter para standxseccione
    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }

    // Setter para standxseccione
    public void setSecciones(ArrayList<Seccion> secciones) {
        this.secciones = secciones;
    }
    public ArrayList<Integer> getCodigosFeria(){
        return codigosFeria;
    }
    public void setCodigosFeria(ArrayList<Integer> codigosFeria){
        this.codigosFeria=codigosFeria;
    }
    @Override
    public int compareTo(Feria o) {
        
        return nombre.compareToIgnoreCase(o.nombre);
    }

public static ArrayList<Feria> cargarFeria(String ruta) {
        ArrayList<Feria> feria = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
       //leer la lista de personas del archivo serializado
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            feria = (ArrayList<Feria>) oi.readObject();
            System.out.println("=============");
            // System.out.println(empleados);
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return feria;
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
        final Feria other = (Feria) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    public void agregarStandsAutomaticos(int cantidad) {
    try {
        // Cargar la lista actual de stands desde el archivo
        ArrayList<Stand> standsActuales = Stand.cargarStand(App.pathStand);

        if (standsActuales.isEmpty()) {
            // Si la lista está vacía, agregar nuevos stands
            for (int i = 1; i <= cantidad; i++) {
                Stand stand = new Stand("Stand " + i + " - " + this.getNombre(), "", "", "");
                standsActuales.add(stand);
            }
        } else {
            // Si la lista no está vacía, calcular el último código y agregar nuevos stands
            int ultimoCodigo = standsActuales.get(standsActuales.size() - 1).getCodigo();
            for (int i = 1; i <= cantidad; i++) {
                Stand stand = new Stand("Stand " + (ultimoCodigo + i) + " - " + this.getNombre(), "", "", "");
                standsActuales.add(stand);
            }
        }

        // Guardar la lista actualizada en el archivo
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathStand))) {
            out.writeObject(standsActuales);
            out.flush();
            System.out.println("Stands guardados en el archivo: " + standsActuales);
        }
    } catch (IOException ex) {
        System.out.println("Error al cargar o guardar stands desde el archivo: " + ex.getMessage());
    }
}











}
