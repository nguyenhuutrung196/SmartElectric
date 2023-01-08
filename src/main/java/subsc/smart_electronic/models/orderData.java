/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class orderData {

    private Integer customer_id;
    private String customer_name;
    private String customer_address;
    private String customer_contact;
    private String customer_email;
    private String customer_cate;
    private String customer_prodName;
    private Integer customer_quantity;
    private Double customer_totalPrice;
    private Date customer_date;
    private String customer_color;

    public orderData(Integer customer_id, String customer_name, String customer_address, String customer_contact, String customer_email, String customer_cate, String customer_prodName, Integer customer_quantity, Double customer_totalPrice, Date customer_date, String customer_color) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contact = customer_contact;
        this.customer_email = customer_email;
        this.customer_cate = customer_cate;
        this.customer_prodName = customer_prodName;
        this.customer_quantity = customer_quantity;
        this.customer_totalPrice = customer_totalPrice;
        this.customer_date = customer_date;
        this.customer_color = customer_color;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(String customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_cate() {
        return customer_cate;
    }

    public void setCustomer_cate(String customer_cate) {
        this.customer_cate = customer_cate;
    }

    public String getCustomer_prodName() {
        return customer_prodName;
    }

    public void setCustomer_prodName(String customer_prodName) {
        this.customer_prodName = customer_prodName;
    }

    public Integer getCustomer_quantity() {
        return customer_quantity;
    }

    public void setCustomer_quantity(Integer customer_quantity) {
        this.customer_quantity = customer_quantity;
    }

    public Double getCustomer_totalPrice() {
        return customer_totalPrice;
    }

    public void setCustomer_totalPrice(Double customer_totalPrice) {
        this.customer_totalPrice = customer_totalPrice;
    }

    public Date getCustomer_date() {
        return customer_date;
    }

    public void setCustomer_date(Date customer_date) {
        this.customer_date = customer_date;
    }

    public String getCustomer_color() {
        return customer_color;
    }

    public void setCustomer_color(String customer_color) {
        this.customer_color = customer_color;
    }

    
}
