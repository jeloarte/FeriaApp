/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Stand;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Dell
 */
public class AdministracionStandControllerVer {
    @FXML
    Label lblNombre;

    @FXML
    Label lblMsj;

    @FXML
    TableView<Stand> tvStand;
    @FXML
    private TableColumn<Stand, String> colNombre;
    @FXML
    private TableColumn<Stand, String> colDescripcion;
    @FXML
    private TableColumn<Stand, String> colFecha;
    @FXML
    private TableColumn<Stand, String> colReservado;
    @FXML
    private TableColumn<Stand, Void> colOpc;
    
    @FXML
    private void initialize() {
        
      
        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        colReservado.setCellValueFactory(new PropertyValueFactory<>("Reservado"));

        agregarOpciones();

        
         llenarTabletView();

    }

    public void llenarTabletView() {
        if (tvStand != null) {
        tvStand.getItems().setAll(Stand.cargarStand(App.pathStand));
    }
    }

    @FXML
    private void mostrarVentana() throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionStands.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionStandVer");
    }

    @FXML
private void mostrarDetalles() {
    Stand stanseleccionada = (Stand) tvStand.getSelectionModel().getSelectedItem();

    if (stanseleccionada != null) {
        // Mostrar información de la feria seleccionada
        System.out.println("Nombre: " + stanseleccionada.getNombre());
        System.out.println("Descripción: " + stanseleccionada.getDescripcion());
        System.out.println("Fecha Asignacion: " + stanseleccionada.getFechaAsignacion());
        System.out.println("Reservado: " + stanseleccionada.getReservado());
        

        
    } else {
        System.out.println("No se ha seleccionado ningun Stand.");
    }
}
@FXML
    private void editarStand() throws IOException {
        Stand e = (Stand) tvStand.getSelectionModel().getSelectedItem();
        AdministracionStandController ct = new AdministracionStandController();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionStands.fxml"));//no tiene el controlador especificado

        fxmlLoader.setController(ct);

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(e);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarStand() {

    }

    private void agregarOpciones() {

        Callback<TableColumn<Stand, Void>, TableCell<Stand, Void>> cellFactory = new Callback<TableColumn<Stand, Void>, TableCell<Stand, Void>>() {
            @Override
            public TableCell<Stand, Void> call(final TableColumn<Stand, Void> param) {
                TableCell<Stand, Void> cell = new TableCell<Stand, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                           
                            HBox hbOpciones = new HBox(5);
                           
                            Stand emp = getTableView().getItems().get(getIndex());
                          
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(r -> editarStand(emp));
                            Button btnReservar = new Button("Reservar");
                            btnReservar.setOnAction(event -> reservarStand(emp));
                       
                        
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarStand(emp));
                            hbOpciones.getChildren().addAll(btnEd,btnEl,btnReservar);
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };
        colOpc.setCellFactory(cellFactory);
    }
    

private void reservarStand(Stand stand) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Reservar Stand");
    alert.setHeaderText("¿Desea reservar el stand?");

    
    ButtonType buttonSi = new ButtonType("Sí");
    ButtonType buttonNo = new ButtonType("No");
    alert.getButtonTypes().setAll(buttonSi, buttonNo);

  
    Optional<ButtonType> result = alert.showAndWait();


    result.ifPresent(buttonType -> {
        if (buttonType == buttonSi) {
     
            stand.setReservado("Reservado");
            tvStand.refresh();
            System.out.println("Stand reservado: " + stand.getDescripcion() + " - Estado: Reservado");
        } else if (buttonType == buttonNo) {
    
            System.out.println("Reserva cancelada");
        }


    });
}


    private void editarStand(Stand e) {
        try {
           
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionStands.fxml"));
            VBox root = (VBox) fxmlLoader.load();
            AdministracionStandController ct = fxmlLoader.getController();
            
            ct.llenarCampos(e);
            App.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
private void mostrarVentana2() throws IOException {

    actualizarModeloYVista();

    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionStandVer.fxml"));
    VBox root = (VBox) fxmlLoader.load();


    App.changeRoot(root);
}

private void actualizarModeloYVista() {
    ArrayList<Stand> stand = Stand.cargarStand(App.pathStand);
   
    tvStand.setItems(FXCollections.observableArrayList(stand));
    tvStand.refresh();
}

    private void eliminarStand(Stand e) {
    
    ArrayList<Stand> stand = Stand.cargarStand(App.pathStand);

    
    if (stand.contains(e)) {

        stand.remove(e);


        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathStand))) {
            out.writeObject(stand);
            out.flush();

        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de Eliminación");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Stand eliminada exitosamente");
            

            alert.showAndWait();
            
            mostrarVentana2();
            tvStand.refresh();

           
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    } else {
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Stand no encontrada");
        alert.setHeaderText("No se puede eliminar");
        alert.setContentText("Ek Stand no se encontró en la lista.");

        alert.showAndWait();
    }
    
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    
}
