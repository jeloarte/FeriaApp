/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Emprendedor;
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
 * @author Dell
 */
public class AdministracionEmprendedoresController implements Initializable {
    private Emprendedor emprendedor;
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
    
    
    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
        actualizarInterfaz();
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionEmprendedoresVer");
    }

    
    public Emprendedor getEmprendedor() {
        return emprendedor;
    }
    @FXML
    private void GoToEmprendedor(ActionEvent event) throws IOException {
        App.setRoot("AdministracionEmprendedores");
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
@FXML
private void guardarEmprendedor() {
    ArrayList<Emprendedor> emprendedor = Emprendedor.cargarEmprendedor(App.pathEmprendedores); 

    Emprendedor e = new Emprendedor(txtCedula.getText(),
            txtNombre.getText(),
            txtTelefono.getText(), 
            txtEmail.getText(),
            txtDireccion.getText(),
            txtSitio.getText(),
            txtRedes.getText());
    System.out.println("Emprendedor a guardar/editar: " + e);

    if (emprendedor.contains(e)) {
        System.out.println("Emprendedor encontrada en la lista, editando...");
        
        int indice = emprendedor.indexOf(e);
        emprendedor.set(indice, e);
        System.out.println("Emprendedor actualizado: " + e);
    } else {
        System.out.println("Emprendedor no encontrada en la lista, agregando...");
       
        emprendedor.add(e);
        System.out.println("Nuevo Emprendedor: " + e);
    }

    
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmprendedores))) {
        out.writeObject(emprendedor);
        out.flush();

       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de Guardado");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Emprendedor guardado exitosamente");

        alert.showAndWait();
        App.setRoot("AdministracionEmprendedoresVer");

    } catch (IOException ex) {
        System.out.println("IOException:" + ex.getMessage());
    }
}



private void actualizarVista() {
    labelNombre.setText(emprendedor.getNombre());
    
}



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (emprendedor != null) {
        labelNombre.setText(emprendedor.getNombre());
    }
    }  
    private void actualizarInterfaz() {
        if (emprendedor != null) {
            labelNombre.setText(emprendedor.getNombre());
        }
    }
    public void llenarCampos(Emprendedor e){
        lblTitulo.setText("Editar Emprendedor");
        txtCedula.setText(e.getCedula());
        txtNombre.setText(e.getNombre());
        txtTelefono.setText(e.getTelefono());
        txtEmail.setText(e.getEmail());
        txtDireccion.setText(e.getDireccion());
        txtSitio.setText(e.getSitioweb());
        txtRedes.setText(e.getRedesSociales());
}    
}
