/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Auspiciante;
import Modelo.RedSocial;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */

public class AdministracionAuspiciantesController implements Initializable {
    private Auspiciante auspiciante;
    @FXML
    private Label labelNombre;
    @FXML
    TextField txtCedula;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtTelefono;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtDireccion;
    @FXML
    TextField txtSitio;
    @FXML
    TextField txtRedes;
    @FXML
    private Label lblTitulo;
    
    
    public void setFeria(Auspiciante auspiciante) {
        this.auspiciante = auspiciante;
        actualizarInterfaz();
    }
    
    public Auspiciante getAuspiciante() {
        return auspiciante;
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionAuspiciantesVer");
    }
    @FXML
    private void GoToAuspiciante(ActionEvent event) throws IOException {
        App.setRoot("AdministracionAuspiciantes");
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
@FXML
private void guardarAuspiciante() {
    ArrayList<Auspiciante> auspiciante = Auspiciante.cargarAuspiciante(App.pathAuspiciante); 

    Auspiciante e = new Auspiciante(txtCedula.getText(),
            txtNombre.getText(),
            txtTelefono.getText(), 
            txtEmail.getText(),
            txtDireccion.getText(),
            txtSitio.getText(),
            txtRedes.getText());
    System.out.println("Auspiciante a guardar/editar: " + e);

    if (auspiciante.contains(e)) {
        System.out.println("Auspiciante encontrada en la lista, editando...");
     
        int indice = auspiciante.indexOf(e);
        auspiciante.set(indice, e);
        System.out.println("Auspiciante actualizado: " + e);
    } else {
        System.out.println("Auspiciante no encontrada en la lista, agregando...");
       
        auspiciante.add(e);
        System.out.println("Nuevo Auspiciante: " + e);
    }

   
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathAuspiciante))) {
        out.writeObject(auspiciante);
        out.flush();

       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de Guardado");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Auspiciante guardado exitosamente");

        alert.showAndWait();
        App.setRoot("AdministracionAuspiciantesVer");

    } catch (IOException ex) {
        System.out.println("IOException:" + ex.getMessage());
    }
}



private void actualizarVista() {
    labelNombre.setText(auspiciante.getNombre());
    
}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (auspiciante != null) {
        labelNombre.setText(auspiciante.getNombre());
    }
    }  
    private void actualizarInterfaz() {
        if (auspiciante != null) {
            labelNombre.setText(auspiciante.getNombre());
        }
    }
    public void llenarCampos(Auspiciante e){
        lblTitulo.setText("Editar Auspiciante");
        txtCedula.setText(e.getCedula());
        txtNombre.setText(e.getNombre());
        txtTelefono.setText(e.getTelefono());
        txtEmail.setText(e.getEmail());
        txtDireccion.setText(e.getDireccion());
        txtSitio.setText(e.getSitioweb());
        txtRedes.setText(e.getRedesSociales());
}
}
