/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alawi
 */
public class LogInController implements Initializable {

    public static String JOPPOS;
    public static String firstnamee;
    @FXML
    private Button LOGINbt;

    @FXML
    private TextField EPIDtx;

    @FXML
    private PasswordField PASSWDtx;

    @FXML
    void LOGINbtact(ActionEvent event) {
        try {

            Connection con = DBConnector.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select e.eid from employee e where eid=" + EPIDtx.getText());
            String s = null;
            while (rs.next()) {
                s = rs.getString("eid");
            }
            if (s != null) {
                rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                s = null;
                while (rs.next()) {
                    s = rs.getString("passwd");
                }
                if (PASSWDtx.getText().equals(s)) {
                    rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                    while (rs.next()) {
                        firstnamee = rs.getString("firstname");
                    }
                    rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                    while (rs.next()) {
                        JOPPOS = rs.getString("joptitle");
                    }
                    

                    Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Home Page");
                    stage.show();

                    LOGINbt.getScene().getWindow().hide();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("WRONGE passwd");
                    a.setContentText("There s NO passwd like this");
                    a.show();
                }

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("WRONGE EMPLOYEE ID");
                a.setContentText("There s NO ID like this");
                a.show();
            }
            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    
     @FXML
    void onEnter(KeyEvent event) {
        
            if(event.getCode().toString().equals("ENTER"))
            {
                try {
                    Connection con = DBConnector.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select e.eid from employee e where eid=" + EPIDtx.getText());
                    String s = null;
                    while (rs.next()) {
                        s = rs.getString("eid");
                    }
                    if (s != null) {
                        rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                        s = null;
                        while (rs.next()) {
                            s = rs.getString("passwd");
                        }
                        if (PASSWDtx.getText().equals(s)) {
                            rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                            while (rs.next()) {
                                firstnamee = rs.getString("firstname");
                            }
                            rs = st.executeQuery("select * from employee e where eid=" + EPIDtx.getText());
                            while (rs.next()) {
                                JOPPOS = rs.getString("joptitle");
                            }
                    

                            Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setTitle("Home Page");
                            stage.show();

                            LOGINbt.getScene().getWindow().hide();
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("WRONGE passwd");
                            a.setContentText("There s NO passwd like this");
                            a.show();
                        }

                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("WRONGE EMPLOYEE ID");
                        a.setContentText("There s NO ID like this");
                        a.show();
                    }
                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
