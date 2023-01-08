/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class productData {

    private String catergory;
    private String productName;
    private String productModel;
    private Integer quanlity;
    private Double price;
    private String type;
    private String brand;
    private Date date;
    private String insurance;
    private String content;
    private String color;
    private String image;
    private ImageView imageview;

    public productData(String catergory, String productName, String productModel, Integer quanlity, Double price, String type, String brand, Date date, String insurance, String content, String color, String image) throws FileNotFoundException {
        this.catergory = catergory;
        this.productName = productName;
        this.productModel = productModel;
        this.quanlity = quanlity;
        this.price = price;
        this.type = type;
        this.brand = brand;
        this.date = date;
        this.insurance = insurance;
        this.content = content;
        this.color = color;
        this.image = image;
        this.imageview = new ImageView(new Image(new FileInputStream(this.image)));
        this.imageview.setFitWidth(50);
        this.imageview.setFitHeight(50);
    }

    public ImageView getImageview() {
        return imageview;
    }

    public void setImageview(ImageView imageview) {
        this.imageview = imageview;
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

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
