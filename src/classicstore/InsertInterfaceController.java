/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alawi
 */
public class InsertInterfaceController implements Initializable {
    
    @FXML
    private TextField LnameTX;
    
    @FXML
    private TextField ConpassTX;
    
    @FXML
    private TextField FnameTX;
    
    @FXML
    private TextField AdderssTX;
    
    @FXML
    private TextField PhoneTX;
    
    @FXML
    private Label mid;
    
    @FXML
    private TextField JTitielTX;
    
    @FXML
    private TextField passTX;
    
    @FXML
    private Button InsertBT;
    
    @FXML
    private Button CancelBT;
    
    @FXML
    void InsertBTonact(ActionEvent event) {
        if (!"".equals(FnameTX.getText()) && !"".equals(LnameTX.getText()) && !"".equals(JTitielTX.getText()) && !"".equals(AdderssTX.getText()) && !"".equals(PhoneTX.getText()) && !"".equals(passTX.getText()) && !"".equals(ConpassTX.getText())) {
            if (passTX.getText().equals(ConpassTX.getText())) {
                int g = 1;
                try {
                    Connection con = DBConnector.getConnection();
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery("select max(eid) as mx from employee");
                    
                    while (rs.next()) {
                        g = Integer.valueOf(rs.getString("mx"));
                    }
                    g += 1;
                    
                    Employee em = new Employee(g, FnameTX.getText(), LnameTX.getText(), JTitielTX.getText(), passTX.getText(), AdderssTX.getText(), Integer.valueOf(PhoneTX.getText()));
                    con.prepareStatement("insert into employee values(" + g + ", ' " + FnameTX.getText() + "','" + LnameTX.getText() + "','" + passTX.getText() + "','" + JTitielTX.getText() + "','" + AdderssTX.getText() + "'," + PhoneTX.getText() + ");").executeUpdate();
                    con.close();
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText(JTitielTX.getText() + " " + FnameTX.getText() + " Have been added");
                    a.setTitle("Done");
                    a.show();
                    
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("fill error");
                a.setContentText("You Have uncorcected passwd");
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("fill error");
            a.setContentText("You Have To Fill All The Felids");
            a.show();
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
        
    }
    
}
