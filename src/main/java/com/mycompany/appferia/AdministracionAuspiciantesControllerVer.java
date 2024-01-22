/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Auspiciante;
import Modelo.Emprendedor;
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
public class AdministracionAuspiciantesControllerVer {
    @FXML
    Label lblNombre;

    @FXML
    Label lblMsj;

    @FXML
    TableView<Auspiciante> tvAuspiciante;
    @FXML
    private TableColumn<Auspiciante, String> colCedula;
    @FXML
    private TableColumn<Auspiciante, String> colNombre;
    @FXML
    private TableColumn<Auspiciante, String> colTelefono;
    @FXML
    private TableColumn<Auspiciante, String> colEmail;
    @FXML
    private TableColumn<Auspiciante, String> colDireccion;
    @FXML
    private TableColumn<Auspiciante, String> colSitio;
    @FXML
    private TableColumn<Auspiciante, String> colRedes;
    @FXML
    private TableColumn<Auspiciante, Void> colOpc;
    
    @FXML
    private void initialize() {
        
        //asignar a cada columna el atributo del objeto correspondiente
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
        if (tvAuspiciante != null) {
        tvAuspiciante.getItems().setAll(Auspiciante.cargarAuspiciante(App.pathAuspiciante));
    }
    }

    @FXML
    private void mostrarVentana() throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionAuspiciantes.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionAuspiciantesVer");
    }

    @FXML
private void mostrarDetalle() {
    Auspiciante auspicianteSeleccionada = (Auspiciante) tvAuspiciante.getSelectionModel().getSelectedItem();

    if (auspicianteSeleccionada != null) {
       
        System.out.println("Cedula: " + auspicianteSeleccionada.getCedula());
        System.out.println("Nombre: " + auspicianteSeleccionada.getNombre());
        System.out.println("Telefono: " + auspicianteSeleccionada.getTelefono());
        System.out.println("Correo: " + auspicianteSeleccionada.getEmail());
        System.out.println("Direccion: " + auspicianteSeleccionada.getDireccion());
        System.out.println("Sitio_Web: " + auspicianteSeleccionada.getSitioweb());
        System.out.println("Redes_Sociales: " + auspicianteSeleccionada.getRedesSociales());

       

    } else {
        System.out.println("No se ha seleccionado ningun Auspiciante.");
    }
}
@FXML
    private void editarAuspiciante() throws IOException {
        Auspiciante e = (Auspiciante) tvAuspiciante.getSelectionModel().getSelectedItem();
        AdministracionAuspiciantesController ct = new AdministracionAuspiciantesController();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionAuspiciantes.fxml"));//no tiene el controlador especificado

        fxmlLoader.setController(ct);
        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(e);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarAuspiciante() {

    }



    //basado en el siguiente ejemplo https://riptutorial.com/javafx/example/27946/add-button-to-tableview

    private void agregarOpciones() {

        Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>> cellFactory = new Callback<TableColumn<Auspiciante, Void>, TableCell<Auspiciante, Void>>() {
            @Override
            public TableCell<Auspiciante, Void> call(final TableColumn<Auspiciante, Void> param) {
                TableCell<Auspiciante, Void> cell = new TableCell<Auspiciante, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                           
                            HBox hbOpciones = new HBox(5);
                            
                            Auspiciante emp = getTableView().getItems().get(getIndex());
                           
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(r -> editarAuspiciante(emp));
                       
                           
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarAuspiciante(emp));
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

    private void editarAuspiciante(Auspiciante e) {
        try {
           
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionAuspiciantes.fxml"));
            VBox root = (VBox) fxmlLoader.load();
            AdministracionAuspiciantesController ct = fxmlLoader.getController();
            
            ct.llenarCampos(e);
            App.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void mostrarVentana2() throws IOException {
        actualizarModeloYVista();
       
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionAuspiciantesVer.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }
private void actualizarModeloYVista() {
    ArrayList<Auspiciante> auspiciante = Auspiciante.cargarAuspiciante(App.pathAuspiciante);
   
    tvAuspiciante.setItems(FXCollections.observableArrayList(auspiciante));
    tvAuspiciante.refresh();
}
    private void eliminarAuspiciante(Auspiciante e) {
   
    ArrayList<Auspiciante> auspiciante = Auspiciante.cargarAuspiciante(App.pathAuspiciante);

   
    if (auspiciante.contains(e)) {
        
        auspiciante.remove(e);

        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathAuspiciante))) {
            out.writeObject(auspiciante);
            out.flush();

            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de Eliminación");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Auspiciante eliminado exitosamente");
            

            alert.showAndWait();
            tvAuspiciante.refresh();
            mostrarVentana2();

           
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    } else {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Auspiciante no encontrado");
        alert.setHeaderText("No se puede eliminar");
        alert.setContentText("El Auspiciante no se encontró en la lista.");

        alert.showAndWait();
    }
    
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
}
