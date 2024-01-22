/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appferia;

import Modelo.Feria;
import Modelo.Stand;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Dell
 */
public class AdministracionFeriasControllerVer {
    
    @FXML
    Label lblNombre;

    @FXML
    Label lblMsj;

    @FXML
    TableView<Feria> tvFerias;
    @FXML
    private TableColumn<Feria, String> colNombre;
    @FXML
    private TableColumn<Feria, String> colDescripcion;
    @FXML
    private TableColumn<Feria, String> colLugar;
    @FXML
    private TableColumn<Feria, String> colInicio;
    @FXML
    private TableColumn<Feria, String> colFinal;
    @FXML
    private TableColumn<Feria, String> colHorario;
    @FXML
    private TableColumn<Feria, Void> colOpc;
    
    @FXML
    private void initialize() {
        

        colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        colLugar.setCellValueFactory(new PropertyValueFactory<>("Lugar"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("Inicio"));
        colFinal.setCellValueFactory(new PropertyValueFactory<>("Final"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("Horario"));
        agregarOpciones();

   
         llenarTabletView();

    }

    public void llenarTabletView() {
        if (tvFerias != null) {
        tvFerias.getItems().setAll(Feria.cargarFeria(App.pathFeria));
    }
    }

    @FXML
    private void mostrarVentana() throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionFerias.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("AdministracionFeriasVer");
    }

    @FXML
private void mostrarDetalle() {
    Feria feriaSeleccionada = (Feria) tvFerias.getSelectionModel().getSelectedItem();

    if (feriaSeleccionada != null) {
     
        System.out.println("Nombre: " + feriaSeleccionada.getNombre());
        System.out.println("Descripción: " + feriaSeleccionada.getDescripcion());
        System.out.println("Lugar: " + feriaSeleccionada.getLugar());
        System.out.println("Fecha de Inicio: " + feriaSeleccionada.getFechaInicio());
        System.out.println("Fecha Final: " + feriaSeleccionada.getFechaFinal());
        System.out.println("Horario: " + feriaSeleccionada.getHorario());

   

    } else {
        System.out.println("No se ha seleccionado ninguna feria.");
    }
}
@FXML
    private void editarFeria() throws IOException {
        Feria e = (Feria) tvFerias.getSelectionModel().getSelectedItem();
        AdministracionFeriasController ct = new AdministracionFeriasController();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionFerias.fxml"));//no tiene el controlador especificado

        fxmlLoader.setController(ct);

        VBox root = (VBox) fxmlLoader.load();

        ct.llenarCampos(e);
        App.changeRoot(root);

    }

    @FXML
    private void eliminarFeria() {

    }



    //basado en el siguiente ejemplo https://riptutorial.com/javafx/example/27946/add-button-to-tableview

    private void agregarOpciones() {

        Callback<TableColumn<Feria, Void>, TableCell<Feria, Void>> cellFactory = new Callback<TableColumn<Feria, Void>, TableCell<Feria, Void>>() {
            @Override
            public TableCell<Feria, Void> call(final TableColumn<Feria, Void> param) {
                TableCell<Feria, Void> cell = new TableCell<Feria, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                          
                            HBox hbOpciones = new HBox(5);
                          
                            Feria emp = getTableView().getItems().get(getIndex());
                      
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(r -> editarFeria(emp));
                       
                         
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarFeria(emp));
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

    private void editarFeria(Feria e) {
        try {
           
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionFerias.fxml"));
            VBox root = (VBox) fxmlLoader.load();
            AdministracionFeriasController ct = fxmlLoader.getController();
            
            ct.llenarCampos(e);
            App.changeRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void mostrarVentana2() throws IOException {
        actualizarModeloYVista();
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AdministracionFeriasVer.fxml"));//no tiene el controlador especificado
      
        VBox root = (VBox) fxmlLoader.load();
       
        App.changeRoot(root);
    }
    private void actualizarModeloYVista() {
    ArrayList<Feria> feria = Feria.cargarFeria(App.pathFeria);
   
    tvFerias.setItems(FXCollections.observableArrayList(feria));
    tvFerias.refresh();
}

    private void eliminarFeria(Feria e) {
 
    ArrayList<Feria> ferias = Feria.cargarFeria(App.pathFeria);

   
    if (ferias.contains(e)) {
      
        ferias.remove(e);

        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathFeria))) {
            out.writeObject(ferias);
            out.flush();

        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultado de Eliminación");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Feria eliminada exitosamente");

            alert.showAndWait();
            tvFerias.refresh();
            mostrarVentana2();

            
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    } else {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Feria no encontrada");
        alert.setHeaderText("No se puede eliminar");
        alert.setContentText("La feria no se encontró en la lista.");

        alert.showAndWait();
    }
    
}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    
}
