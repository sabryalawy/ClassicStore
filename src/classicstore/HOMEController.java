/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alawi
 */
public class HOMEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public Label WELLCOMElab;

    @FXML
    private Button EMPLOYEEINTERFACE;

    @FXML
    private Button logoutbt;
    
    @FXML
    private Button ordersBTN;

    @FXML
    private Button suppliersBTN;

    @FXML
    private Button customerBTN;

    @FXML
    void logoutbtonact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("LogIn page");
        stage.show();
        EMPLOYEEINTERFACE.getScene().getWindow().hide();
    }

    @FXML
    void EMPLOYEEINTERFACEact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("HR Page");
        stage.show();
        EMPLOYEEINTERFACE.getScene().getWindow().hide();
    }
    
    
    @FXML
    void ordersOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ORDERS PAGE");
        stage.show();
        ordersBTN.getScene().getWindow().hide();
    }
    
    
     @FXML
    void customersOnAction(ActionEvent event) {

    }
    
    @FXML
    void suppliersOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WELLCOMElab.setText("wellcome back " + LogInController.firstnamee);
    }

}
