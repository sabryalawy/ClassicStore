/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classicstore;

/**
 *
 * @author T
 */

import java.util.concurrent.Semaphore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author Alawi
 */
public class Product {
    SimpleStringProperty size,brand;
    SimpleIntegerProperty price, barcode,quantity;

    public Product(String size, String brand, Integer price, Integer barcode, Integer quantity) {
        this.size = new SimpleStringProperty(size);
        this.brand =  new SimpleStringProperty(brand);
        this.price =  new SimpleIntegerProperty(price);
        this.barcode = new SimpleIntegerProperty(barcode);
        this.quantity = new SimpleIntegerProperty(quantity);
    }



    public Product() {
    }

    Product(Integer valueOf, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSize() {
        return size.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public Integer getBarcode() {
        return barcode.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public void setSize(String size) {
        this.size =new SimpleStringProperty( size);
    }

    public void setBrand(String brand) {
        this.brand = new SimpleStringProperty(brand);
    }

    public void setPrice(Integer price) {
        this.price = new SimpleIntegerProperty(price);
    }

    public void setBarcode(Integer barcode) {
        this.barcode = new SimpleIntegerProperty(barcode);
    }

    public void setQuantity(Integer quantity) {
        this.quantity =new SimpleIntegerProperty( quantity);
    }
    
    
}
