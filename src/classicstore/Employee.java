package classicstore;

import java.util.Comparator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alawi
 */
public class Employee{

    SimpleIntegerProperty epid;
    SimpleStringProperty firstname;
    SimpleStringProperty lastname;
    SimpleStringProperty passwd;
    SimpleStringProperty joptitle;
    SimpleStringProperty address;
    SimpleIntegerProperty phone;

    public Employee(int epid, String firstname, String lastname, String passwd, String joptitle) {
        this.epid = new SimpleIntegerProperty(epid);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.passwd = new SimpleStringProperty(passwd);
        this.joptitle = new SimpleStringProperty(joptitle);
    }

    public Employee(int id, String firstname, String lastname, String joptitle) {
        this.epid = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.joptitle = new SimpleStringProperty(joptitle);
    }

    public Employee(int id, String firstname, String lastname, String joptitle,String a,int p) {
        this.epid = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.joptitle = new SimpleStringProperty(joptitle);
        this.address = new SimpleStringProperty(a);
        this.phone = new SimpleIntegerProperty(p);
    }
        public Employee(int id, String firstname, String lastname, String joptitle,String pass,String a,int p) {
        this.epid = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.passwd=new SimpleStringProperty(pass);
        this.lastname = new SimpleStringProperty(lastname);
        this.joptitle = new SimpleStringProperty(joptitle);
        this.address = new SimpleStringProperty(a);
        this.phone = new SimpleIntegerProperty(p);
    }

    public Integer getEpid() {
        return epid.get();
    }

    public void setEpid(Integer epid) {
        this.epid = new SimpleIntegerProperty(epid);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }

    public String getPasswd() {
        return passwd.get();
    }

    public void setPasswd(String passwd) {
        this.passwd = new SimpleStringProperty(passwd);
    }

    public String getJoptitle() {
        return joptitle.get();
    }

    public void setJoptitle(String joptitle) {
        this.joptitle = new SimpleStringProperty(joptitle);
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public Integer getPhone() {
        return phone.get();
    }

    public String getAddress() {
        return address.get();
    }

    public void setPhone(Integer phone) {
        this.phone = new SimpleIntegerProperty(phone);
    }



}
