package util;

import Modelo.Feria;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ANGEL
 */
public class Serializador {
    public static void serializarFeria(Feria feria) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("feria.ser"))) {
            oos.writeObject(feria);
            System.out.println("Feria serializada con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
