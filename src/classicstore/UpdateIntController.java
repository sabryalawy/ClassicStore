/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alawi
 */
public class UpdateIntController implements Initializable {

    @FXML
    private Label JobtitLB;

    @FXML
    private TextField LnameTX;

    @FXML
    private TextField FnameTX;

    @FXML
    private TextField AdderssTX;

    @FXML
    private TextField PhoneTX;

    @FXML
    private Button CancelBT;

    @FXML
    private Button UpDateBT;

    @FXML
    private TextField ConpassTX;

    @FXML
    private Label FnameLB;

    @FXML
    private Label LnameLB;

    @FXML
    private Label lableee;

    @FXML
    private Label AddressLB;

    @FXML
    private TextField JTitielTX;

    @FXML
    private Label PhoneLB;

    @FXML
    private TextField passTX;

    @FXML
    void UpDateBTonact(ActionEvent event) {
        try {

            int em = Integer.valueOf(FXMLDocumentController.statem.getEpid());
            Connection con = DBConnector.getConnection();
            if (!FnameTX.getText().equals("")) {
                con.prepareStatement("update employee set firstname='" + FnameTX.getText() + "' where eid=" + em).executeUpdate();
                FnameTX.setText("");

            }
            if (!LnameTX.getText().equals("")) {
                con.prepareStatement("update employee set lastname='" + LnameTX.getText() + "' where eid=" + em).executeUpdate();
                LnameTX.setText("");
            }
            if (!JTitielTX.getText().equals("")) {
                con.prepareStatement("update employee set joptitle='" + JTitielTX.getText() + "' where eid=" + em).executeUpdate();
                JTitielTX.setText("");
            }
            if (!AdderssTX.getText().equals("")) {
                con.prepareStatement("update employee set address='" + AdderssTX.getText() + "' where eid=" + em).executeUpdate();
                AdderssTX.setText("");
            }
            if (!PhoneTX.getText().equals("")) {
                con.prepareStatement("update employee set phone='" + PhoneTX.getText() + "' where eid=" + em).executeUpdate();
                PhoneTX.setText("");
            }
            if (!passTX.getText().equals("")) {
                con.prepareStatement("update employee set passwd='" + passTX.getText() + "' where eid=" + em).executeUpdate();
                passTX.setText("");
            }

            con.close();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Done");
            a.setContentText("the data has been Updeted");
            a.show();

        } catch (Exception ex) {
            System.out.println("noooo");
        }

    }

    @FXML
    void cancelBTonact(ActionEvent event) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene s1 = new Scene(p);
        Stage s2 = new Stage();
        s2.setScene(s1);
        s2.show();
        s2.setTitle("Human Resorse");
        CancelBT.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lableee.setText("UpDate " + FXMLDocumentController.statem.getFirstname());
        FnameLB.setText("First name " + FXMLDocumentController.statem.getFirstname());
        LnameLB.setText("Last name " + FXMLDocumentController.statem.getLastname());
        JobtitLB.setText("Job title " + FXMLDocumentController.statem.getJoptitle());
        AddressLB.setText("Address " + FXMLDocumentController.statem.getAddress());
        PhoneLB.setText("Phone " + String.valueOf(FXMLDocumentController.statem.getPhone()));
    }

}
