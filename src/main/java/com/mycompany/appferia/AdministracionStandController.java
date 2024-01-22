/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Feria;
import Modelo.Stand;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Dell
 */
public class AdministracionStandController {
    private Stand stand;
    @FXML
    private Label labelNombre;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtDescripcion;
    @FXML
    TextField txtFechaAsignacion;
    @FXML
    TextField txtReservado;
    @FXML
    private Label lblTitulo;
    
    
    public void setStand(Stand stand) {
        this.stand = stand;
        actualizarInterfaz();
    }
    
    public Stand getStand() {
        return stand;
    }
    @FXML
    private void GoToStand(ActionEvent event) throws IOException {
        App.setRoot("AdministracionStands");
}
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionStandVer");
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
@FXML
private void guardarStand() {
    ArrayList<Stand> stand = Stand.cargarStand(App.pathStand); 

    Stand e = new Stand(txtNombre.getText(),
            txtDescripcion.getText(), 
            txtFechaAsignacion.getText(),
            txtReservado.getText());
    System.out.println("Stand a guardar/editar: " + e);

    if (stand.contains(e)) {
        System.out.println("Feria encontrada en la lista, editando...");
        
        int indice = stand.indexOf(e);
        stand.set(indice, e);
        System.out.println("Feria actualizada: " + e);
    } else {
        System.out.println("Feria no encontrada en la lista, agregando...");
    
        stand.add(e);
        System.out.println("Nueva Feria: " + e);
    }

   
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathStand))) {
        out.writeObject(stand);
        out.flush();

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de Guardado");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Feria guardada exitosamente");

        alert.showAndWait();
        App.setRoot("AdministracionStandVer");

    } catch (IOException ex) {
        System.out.println("IOException:" + ex.getMessage());
    }
}



private void actualizarVista() {
    labelNombre.setText(stand.getNombre());
    
}

    public void initialize(URL url, ResourceBundle rb) {
        if (stand != null) {
        labelNombre.setText(stand.getNombre());
    }
    }  
    private void actualizarInterfaz() {
        if (stand != null) {
            labelNombre.setText(stand.getNombre());
        }
    }
    public void llenarCampos(Stand e){
        lblTitulo.setText("Editar Stand");
        txtNombre.setText(e.getNombre());
        txtDescripcion.setText(e.getDescripcion());
        txtFechaAsignacion.setText(e.getFechaAsignacion());
        txtReservado.setText(e.getReservado());
       
    }
}
