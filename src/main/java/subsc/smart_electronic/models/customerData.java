/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

/**
 *
 * @author Admin
 */
public class customerData {

    private String customerId;
    private String customerName;
    private String address;
    private String customerPhone;
    private String email;
    private String catergory;
    private String productName;
    private Integer quanlity;
    private Double price;
    private String customer_color;

    public customerData(String customerId, String customerName, String address, String customerPhone, String email, String catergory, String productName, Integer quanlity, Double price, String customer_color) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.customerPhone = customerPhone;
        this.email = email;
        this.catergory = catergory;
        this.productName = productName;
        this.quanlity = quanlity;
        this.price = price;
        this.customer_color = customer_color;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatergory() {
        return catergory;
    }

    public void setCatergory(String catergory) {
        this.catergory = catergory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(Integer quanlity) {
        this.quanlity = quanlity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomer_color() {
        return customer_color;
    }

    public void setCustomer_color(String customer_color) {
        this.customer_color = customer_color;
    }

}
