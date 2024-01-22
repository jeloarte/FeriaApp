package com.mycompany.appferia;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button BtnAdminFerias;
    @FXML
    private Button BtnAdminEmprendedores;
    @FXML
    private Button BtnAdminAuspiciantes;
    @FXML
    private Button BtnAdminStands;
    @FXML
    private Button BtnSalir;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void GoToFerias(ActionEvent event) throws IOException{
        App.setRoot("AdministracionFeriasVer");
    }
    
    @FXML
    private void GoToFeriasVer(ActionEvent event) throws IOException{
        App.setRoot("AdministracionFerias");
    }

    @FXML
    private void GoToEmprendedores(ActionEvent event) throws IOException{
        App.setRoot("AdministracionEmprendedoresVer");
    }

    @FXML
    private void GoToAuspiciantes(ActionEvent event) throws IOException{
        App.setRoot("AdministracionAuspiciantesVer");
    }
    @FXML
    private void GoToAuspiciantesVer(ActionEvent event) throws IOException{
        App.setRoot("AdministracionAuspiciantes");
    }

    @FXML
    private void GoToStands(ActionEvent event) throws IOException{
        App.setRoot("AdministracionStandVer");
    }

    @FXML
    private void FinApp(ActionEvent event) throws IOException{
        Platform.exit();
    }
}
