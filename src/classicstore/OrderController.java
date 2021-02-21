/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;


import com.sun.deploy.util.SystemUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * FXML Controller class
 *
 * @author T
 */
public class OrderController implements Initializable {
    
    @FXML
    private Button backBTN;

    @FXML
    private Button showOrdersBTN;

    @FXML
    private Button makeOrderBTN;
    
    @FXML
    private Button moveToOrder;
    
    @FXML
    private TextField quantityTxt;
    
    @FXML
    private TextField searchTX;
    
    @FXML
    private Button moveToProducts;
    
    @FXML
    private TableView<Product> tableAll;

    @FXML
    private TableColumn<Product, String> productBrand;

    @FXML
    private TableColumn<Product, String> productSize;

    @FXML
    private TableColumn<Product, Integer> productPrice;

    @FXML
    private TableColumn<Product, Integer> productBarcode;
    
    @FXML
    private TableColumn<Product, Integer> productQuantity;
    
    @FXML
    private TableView<Product> tableSelected;

    @FXML
    private TableColumn<Product, String> OProductBrand;

    @FXML
    private TableColumn<Product, String> OProductSize;

    @FXML
    private TableColumn<Product, Integer> OProductPrice;

    @FXML
    private TableColumn<Product, Integer> OProductBarcode;
    
    @FXML
    private TableColumn<Product, Integer> OProductQuantity;
    
    
    ObservableList<Product> ob = FXCollections.observableArrayList();
    ObservableList<Product> ob1 = FXCollections.observableArrayList();

    @FXML
    void moveToTable(ActionEvent event) throws SQLException {
        Product p = tableAll.getSelectionModel().getSelectedItem();
        
        int i = (Integer.parseInt(quantityTxt.getText()));
        
        if(i>0){
            if(p.getQuantity()-i >= 0){
                Connection con = DBConnector.getConnection();
                con.prepareStatement("update product set quantity=" + String.valueOf(p.getQuantity()-i) + " where barcode=" + String.valueOf(p.getBarcode())+";").executeUpdate();
                p.setQuantity(i);
                con.close();
                ob1.add(p);
                makeRF1();
                makeRF();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("cant move the product");
                a.setContentText("there is no enough quantity");
                a.show();
            }
        }
        else{
            Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("cant move the product");
                b.setContentText("the quantity can't be negative or zero value!!");
                b.show();
        }
        
        
        
        
    }
    
    
    @FXML
    void moveBack(ActionEvent event) throws SQLException {
        Product p = tableSelected.getSelectionModel().getSelectedItem();
        int i = (Integer.parseInt(quantityTxt.getText()));
        
        if(i>0){
            if(p.getQuantity()-i >= 0){
                Connection con = DBConnector.getConnection();
                //Statement st = con.createStatement();
                //ResultSet rs = st.executeQuery("select p.quantity from product p where p.barcode =" + String.valueOf(p.getBarcode())+";");
                //int k = Integer.valueOf(rs.getString("quantity"));
                //System.out.println(k);
                //int k = con.prepareStatement("select p.quantity from product p where p.barcode =" + String.valueOf(p.getBarcode())+";").executeQuery();
                con.prepareStatement("update product set quantity=" + String.valueOf(k+i) + " where barcode=" + String.valueOf(p.getBarcode())+";").executeUpdate();
                con.close();
                p.setQuantity(p.getQuantity()-i);
                ob.add(p);
                makeRF1();
                makeRF();
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("cant move the product");
                a.setContentText("there is no enough quantity");
                a.show();
            }
        }
        else{
            Alert b = new Alert(Alert.AlertType.ERROR);
                b.setTitle("cant move the product");
                b.setContentText("the quantity can't be negative or zero value!!");
                b.show();
        }
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HOME.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Home Page");
        backBTN.getScene().getWindow().hide();
    }

    @FXML
    void makeOrder(ActionEvent event) {

    }


    @FXML
    void showOrders(ActionEvent event) {

    }
    
    @FXML
    void SearchTXonrel(KeyEvent event) {
        //personList is table setter getter
        FilteredList<Product> filterData = new FilteredList<>(ob, p -> true);
        searchTX.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(pers -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (String.valueOf(pers.getBarcode()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getBrand().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (pers.getSize().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (String.valueOf(pers.getPrice()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (String.valueOf(pers.getQuantity()).toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                return false;
            });
            SortedList<Product> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableAll.comparatorProperty());
            tableAll.setItems(sortedList);

        });

    }
    
    
    void makeRF() {
        ob.clear();
        quantityTxt.setText("1");
        try {
            Connection con = DBConnector.getConnection();
            Statement st = con.createStatement();
            ResultSet rez = st.executeQuery("select * from product where quantity > 0");
            while (rez.next()) {
                ob.add(new Product(rez.getString("size"), rez.getString("brand"), Integer.valueOf(rez.getString("price")), Integer.valueOf(rez.getString("barcode")), Integer.valueOf(rez.getString("quantity"))));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        productBarcode.setCellValueFactory(new PropertyValueFactory<Product, Integer>("barcode"));
        productSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
        productBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        productQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        
        tableAll.setItems(FXCollections.observableArrayList());
        tableAll.setItems(ob);
    }
    
    
        void makeRF1() {
            OProductBarcode.setCellValueFactory(new PropertyValueFactory<Product, Integer>("barcode"));
            OProductSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
            OProductBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
            OProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
            OProductQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
            
            tableSelected.setItems(FXCollections.observableArrayList());
            tableSelected.setItems(ob1);
    }
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeRF();
    }
    
}
