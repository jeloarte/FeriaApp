package com.mycompany.appferia;

import Modelo.Feria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String pathFeria = "feria.ser";
    public static String pathStand = "stand.ser";
    public static String pathAuspiciante = "ausp.ser";
    public static String pathEmprendedores = "emp.ser";

    private static Scene scene;
    Feria feria = new Feria("nombre", "descripción", "lugar", "fechainicio", "fechafinal", "horario");


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("primary");
        scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(App.class.getResource("css/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

}