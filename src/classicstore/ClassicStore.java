/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alawi
 */
public class ClassicStore extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("LogIn Page");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        ClassicStore cs= new ClassicStore();
//        //cs.ConnectorCommandDataBase("insert into employee(epid , firstname) values(99,'ali')");
//        cs.ConnectorDataBase();
//        
    }

    public void ConnectorDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBConnector.getConnection();
            Statement statement = con.createStatement();
            ResultSet rez = null;
            rez = statement.executeQuery("select * from employee where epid=4");//#c68101

            while (rez.next()) {
                System.out.println(rez.getString("firstname"));
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassicStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClassicStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ConnectorCommandDataBase(String s) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DBConnector.getConnection();
            PreparedStatement prestat = null;
            con.prepareStatement(s).executeUpdate();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassicStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClassicStore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
