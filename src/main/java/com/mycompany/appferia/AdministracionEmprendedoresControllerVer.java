/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Auspiciante;
import Modelo.Emprendedor;
import Modelo.Feria;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Dell
 */
public class AdministracionEmprendedoresControllerVer {
    @FXML
    Label lblNombre;

    @FXML
    Label lblMsj;

    @FXML
    TableView<Emprendedor> tvEmprendedor;
    @FXML
    private TableColumn<Emprendedor, String> colCedula;
    @FXML
    private TableColumn<Emprendedor, String> colNombre;
    @FXML
    private TableColumn<Emprendedor, String> colTelefono;
    @FXML
    private TableColumn<Emprendedor, String> colEmail;
    @FXML
    private TableColumn<Emprendedor, String> colDireccion;
    @FXML
    private TableColumn<Emprendedor, String> colSitio;
    @FXML
    private TableColumn<Emprendedor, String> colRedes;
    @FXML
    private TableColumn<Emprendedor, Void> colOpc;
    
    @FXML
    private void initialize() {
        
        
        colCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        colSitio.setCellValueFactory(new PropertyValueFactory<>("Sitio_Web"));
        colRedes.setCellValueFactory(new PropertyValueFactory<>("Redes_Sociales"));
        agregarOpciones();

  
         llenarTabletView();

    }

    public void llenarTabletView() {
        if (tvEmprendedor != null) {
        tvEmprendedor.getItems().setAll(Emprendedor.cargarEmprendedor(App.pathEmprendedores));
    }
    }

    @FXML
    private void mostrarVentana() throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionEmprendedores.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionEmprendedoresVer");
    }

    @FXML
private void mostrarDetalle() {
    Emprendedor emprendedorSeleccionado = (Emprendedor) tvEmprendedor.getSelectionModel().getSelectedItem();

    if (emprendedorSeleccionado != null) {
        
        System.out.println("Cedula: " + emprendedorSeleccionado.getCedula());
        System.out.println("Nombre: " + emprendedorSeleccionado.getNombre());
        System.out.println("Telefono: " + emprendedorSeleccionado.getTelefono());
        System.out.println("Correo: " + emprendedorSeleccionado.getEmail());
        System.out.println("Direccion: " + emprendedorSeleccionado.getDireccion());
        System.out.println("Sitio_Web: " + emprendedorSeleccionado.getSitioweb());
        System.out.println("Redes_Sociales: " + emprendedorSeleccionado.getRedesSociales());

       

    } else {
        System.out.println("No se ha seleccionado ningun emprendedor.");
    }
}
@FXML
    private void editarEmprendedor() throws IOException {
        Emprendedor e = (Emprendedor) tvEmprendedor.getSelectionModel().getSelectedItem();
        AdministracionEmprendedoresController ct = new AdministracionEmprendedoresController();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionEmprendedores.fxml"));//no tiene el controlador especificado

        fxmlLoader.setController(ct);

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(e);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarEmprendedor() {

    }



    //basado en el siguiente ejemplo https://riptutorial.com/javafx/example/27946/add-button-to-tableview

    private void agregarOpciones() {

        Callback<TableColumn<Emprendedor, Void>, TableCell<Emprendedor, Void>> cellFactory = new Callback<TableColumn<Emprendedor, Void>, TableCell<Emprendedor, Void>>() {
            @Override
            public TableCell<Emprendedor, Void> call(final TableColumn<Emprendedor, Void> param) {
                TableCell<Emprendedor, Void> cell = new TableCell<Emprendedor, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            
                            HBox hbOpciones = new HBox(5);
                     
                            Emprendedor emp = getTableView().getItems().get(getIndex());
                          
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(r -> editarEmprendedor(emp));
                       
                          
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarEmprendedor(emp));
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };
        colOpc.setCellFactory(cellFactory);
    }

    private void editarEmprendedor(Emprendedor e) {
        try {
           
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionEmprendedores.fxml"));
            VBox root = (VBox) fxmlLoader.load();
            AdministracionEmprendedoresController ct = fxmlLoader.getController();
            
            ct.llenarCampos(e);
            App.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void mostrarVentana2() throws IOException {
        actualizarModeloYVista();
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionEmprendedoresVer.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();//carga los objetos del fxml
       
        App.changeRoot(root);
    }
private void actualizarModeloYVista() {
    ArrayList<Emprendedor> emprendedor = Emprendedor.cargarEmprendedor(App.pathEmprendedores);
   
    tvEmprendedor.setItems(FXCollections.observableArrayList(emprendedor));
    tvEmprendedor.refresh();
}
    private void eliminarEmprendedor(Emprendedor e) {

    ArrayList<Emprendedor> emprendedor = Emprendedor.cargarEmprendedor(App.pathEmprendedores);


    if (emprendedor.contains(e)) {
     
        emprendedor.remove(e);


        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmprendedores))) {
            out.writeObject(emprendedor);
            out.flush();

       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de Eliminación");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Emprendedor eliminado exitosamente");

            alert.showAndWait();
            tvEmprendedor.refresh();
            mostrarVentana2();

            
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    } else {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Auspiciante no encontrado");
        alert.setHeaderText("No se puede eliminar");
        alert.setContentText("El Emprendedor no se encontró en la lista.");

        alert.showAndWait();
    }
    
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
}
