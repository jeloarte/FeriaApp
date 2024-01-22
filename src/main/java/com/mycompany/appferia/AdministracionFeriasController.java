/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.appferia;

import util.Serializador;
import Modelo.Feria;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AdministracionFeriasController implements Initializable {
    
    private Feria feria;
    @FXML
    private Label labelNombre;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtDescripcion;
    @FXML
    TextField txtLugar;
    @FXML
    TextField txtHorario;
    @FXML
    private Label lblTitulo;
    @FXML
    private DatePicker txtFechaInicio;
    @FXML
    private DatePicker txtFechaFinal;
    
    
    public void setFeria(Feria feria) {
        this.feria = feria;
        actualizarInterfaz();
    }
    
    public Feria getFeria() {
        return feria;
    }
    @FXML
    private void GoToFerias(ActionEvent event) throws IOException {
        App.setRoot("AdministracionFerias");
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionFeriasVer");
    }
@FXML
private void guardarFeria() {
    ArrayList<Feria> ferias = Feria.cargarFeria(App.pathFeria); 
    LocalDate fechaInicio = txtFechaInicio.getValue();
    LocalDate fechaFinal = txtFechaFinal.getValue();


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    String fechaInicioTexto = fechaInicio.format(formatter);
    String fechaFinalTexto = fechaFinal.format(formatter);

    Feria e = new Feria(txtNombre.getText(),
            txtDescripcion.getText(), 
            txtLugar.getText(),
            fechaInicioTexto ,
            fechaFinalTexto,
            txtHorario.getText());
    System.out.println("Feria a guardar/editar: " + e);

    if (ferias.contains(e)) {
        System.out.println("Feria encontrada en la lista, editando...");
        
        int indice = ferias.indexOf(e);
        ferias.set(indice, e);
        System.out.println("Feria actualizada: " + e);
    } else {
        System.out.println("Feria no encontrada en la lista, agregando...");
     
        ferias.add(e);
        e.agregarStandsAutomaticos(16);
        System.out.println("Nueva Feria: " + e);
    }


    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathFeria))) {
        out.writeObject(ferias);
        out.flush();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de Guardado");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Feria guardada exitosamente");

        alert.showAndWait();
        App.setRoot("AdministracionFeriasVer");

    } catch (IOException ex) {
        System.out.println("IOException:" + ex.getMessage());
    }
}



private void actualizarVista() {
    labelNombre.setText(feria.getNombre());
   
}



   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (feria != null) {
        labelNombre.setText(feria.getNombre());
    }
    }  
    private void actualizarInterfaz() {
        if (feria != null) {
            labelNombre.setText(feria.getNombre());
        }
    }
    public void llenarCampos(Feria e){
        lblTitulo.setText("Editar Feria");
        txtNombre.setText(e.getNombre());
        txtDescripcion.setText(e.getDescripcion());
        txtLugar.setText(e.getLugar());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(e.getFechaInicio(), formatter);
        txtFechaInicio.setValue(fechaInicio);
        LocalDate fechaFinal = LocalDate.parse(e.getFechaFinal(), formatter);
        txtFechaFinal.setValue(fechaFinal);
        txtHorario.setText(e.getHorario());
       
    }
    
}
