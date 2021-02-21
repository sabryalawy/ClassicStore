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
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Alawi
 */
public class FXMLDocumentController implements Initializable {

    public static Employee statem;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, String> LnameTC;

    @FXML
    private TableColumn<Employee, String> FnameTC;

    @FXML
    private TableColumn<Employee, String> joptitTC;

    @FXML
    private TableColumn<Employee, Integer> EPID;

    @FXML
    private TableColumn<Employee, String> addressTC;

    @FXML
    private TableColumn<Employee, Integer> PhoneTC;

    ObservableList<Employee> ob = FXCollections.observableArrayList();

    @FXML
    private Button Insertbt;

    @FXML
    private Button Deletebt;

    @FXML
    private Button Updatebt;

    @FXML
    private Label Oprations;

    @FXML
    private Button backbt;

    @FXML
    private TextField SearchTX;

    @FXML
    void backbtonact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Home Page");
        backbt.getScene().getWindow().hide();

    }

    @FXML
    void Insertbtact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InsertInterface.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Insert Employee");
        Insertbt.getScene().getWindow().hide();
    }

    @FXML
    void Updatebtonact(ActionEvent event) throws IOException {
        statem = table.getSelectionModel().getSelectedItem();
        Parent p = FXMLLoader.load(getClass().getResource("UpdateInt.fxml"));
        Scene s1 = new Scene(p);
        Stage s2 = new Stage();
        s2.setScene(s1);
        s2.show();
        s2.setTitle("UpDating");
        Updatebt.getScene().getWindow().hide();
    }

    @FXML
    void Deletebtonact(ActionEvent event) throws SQLException {
        Employee em = table.getSelectionModel().getSelectedItem();
        int i = em.getEpid();
        if (i == 1) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("cant delete the root");
            a.setContentText("you cant delete the root");
            a.show();
        } else {
            Connection con = DBConnector.getConnection();
            con.prepareStatement("delete from employee where eid =" + i + ";").executeUpdate();
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            con.close();
            makeRF();
        }
        makeRF();
    }

    void makeRF() {
        ob.clear();
        try {
            Connection con = DBConnector.getConnection();
            Statement st = con.createStatement();
            ResultSet rez = st.executeQuery("select * from employee");
            while (rez.next()) {
                ob.add(new Employee(Integer.valueOf(rez.getString("eid")), rez.getString("firstname"), rez.getString("lastname"), rez.getString("joptitle"), rez.getString("address"), Integer.valueOf(rez.getString("phone"))));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        EPID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("epid"));
        FnameTC.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        LnameTC.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        joptitTC.setCellValueFactory(new PropertyValueFactory<Employee, String>("joptitle"));
        addressTC.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        PhoneTC.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("phone"));
        table.setItems(FXCollections.observableArrayList());

        table.setItems(ob);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!"admen".equalsIgnoreCase(LogInController.JOPPOS)) {
            Insertbt.setVisible(false);
            Deletebt.setVisible(false);
            Updatebt.setVisible(false);
        }

        makeRF();

    }

    @FXML
    private void SearchTXonrel(KeyEvent ke) {
        //personList is table setter getter
        FilteredList<Employee> filterData = new FilteredList<>(ob, p -> true);
        SearchTX.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getFirstname().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (String.valueOf(pers.getEpid()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getAddress().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getJoptitle().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                return false;
            });
            SortedList<Employee> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);

        });

    }
}
