/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import subsc.smart_electronic.connectViews.electronicConnectViews;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.models.customerData;
import subsc.smart_electronic.models.getData;
import subsc.smart_electronic.models.orderData;
import subsc.smart_electronic.models.productData;

/**
 *
 * @author ADMIN
 */
public class EmployeeController implements Initializable {
    
    @FXML
    private Button btn_purchase;
    
    @FXML
    private Button btn_receipt;

    @FXML
    private ComboBox<String> brand;
    
    @FXML
    private Button btn_newcustomer;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_checkout;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_minimize;

    @FXML
    private Button btn_delete;
    
    @FXML
    private Button btn_addcustomer;
    
    @FXML
    private Button btn_signout;

    @FXML
    private TextField category;

    @FXML
    private TableColumn<orderData, String> column_customer_cate;

    @FXML
    private TableColumn<orderData, String> column_customer_date;

    @FXML
    private TableColumn<orderData, String> column_customer_id;

    @FXML
    private TableColumn<orderData, String> column_customer_prodName;

    @FXML
    private TableColumn<orderData, String> column_customer_quantity;

    @FXML
    private TableColumn<orderData, String> column_customer_totalPrice;
    
    @FXML
    private TableColumn<orderData, String> column_customer_color;
    
    @FXML
    private AnchorPane receiptform;

    @FXML
    private AnchorPane employeeform;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<String> model;

    @FXML
    private ComboBox<String> productname;
    
    @FXML
    private ComboBox<String> color;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private TableView<orderData> tbl_order;

    @FXML
    private ComboBox<String> text_comCustName;
    
    @FXML
    private ComboBox<String> text_comCustAdd;
    
    @FXML
    private ComboBox<String> text_comCustEmail;
    
    @FXML
    private TextField text_note;

    @FXML
    private TextField text_phonecustomer;

    @FXML
    private Label text_total;
    
     @FXML
    private Label displayEmployee;

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;
    
    public void displayEmployee(){
        displayEmployee.setText(getData.employeeId);
    }
    
    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_purchase){
            employeeform.setVisible(true);
            receiptform.setVisible(false);
            searchCatergory();
            orderListData();
            orderDataShow();
            orderReset();
        }else{
            employeeform.setVisible(false);
            receiptform.setVisible(true);
        }
    }

    public void searchCatergory() {
        String sqlSearch = "select *from products where product_cate='" + category.getText()
                + "'and product_type='1'";
        conn = database.ConnectDB();
        try {
            preparedStatement = conn.prepareStatement(sqlSearch);
            resultSet = preparedStatement.executeQuery();
            ObservableList listProduct = FXCollections.observableArrayList();
            ObservableList listBrand = FXCollections.observableArrayList();
            ObservableList listModel = FXCollections.observableArrayList();
            ObservableList listColor = FXCollections.observableArrayList();
            while (resultSet.next()) {
                listProduct.add(resultSet.getString("product_name"));
                productname.setItems(listProduct);
                listBrand.add(resultSet.getString("product_brand"));
                brand.setItems(listBrand);
                listModel.add(resultSet.getString("product_model"));
                model.setItems(listModel);
                listColor.add(resultSet.getString("product_color"));
                color.setItems(listColor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpinnerValueFactory spinner;

    public void spinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 0);

        quantity.setValueFactory(spinner);
    }
    private int qty;

    public void getSpinnerValues() {
        qty = quantity.getValue();
    }
    private int customerId;

    public void purchaseCustomerId() {
        String sqlCId = "select customer_id from customers";
        conn = database.ConnectDB();

        try {

            preparedStatement = conn.prepareStatement(sqlCId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
            int checkId = 0;
            String sqlcheck = "select bill_customer_id from bills";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlcheck);
            while (resultSet.next()) {
                checkId = resultSet.getInt("bill_customer_id");
            }

            if (customerId == 0) {
                customerId += 1;
            } else if (checkId == customerId) {
                customerId +=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<orderData> orderListData() {
        purchaseCustomerId();
        
        ObservableList<orderData> orderList = FXCollections.observableArrayList();
        String sql = "select * from customers where customer_id='" + customerId + "'";
        conn = database.ConnectDB();
        try {
            orderData order;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                order = new orderData(resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_address"),
                        resultSet.getString("customer_contact"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("customer_cate"),
                        resultSet.getString("customer_prodName"),
                        resultSet.getInt("customer_quanlity"),
                        resultSet.getDouble("customer_totalPrice"),
                        resultSet.getDate("customer_date"),
                        resultSet.getString("customer_color"));
                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;

    }
    private ObservableList<orderData> orderList;

    public void orderDataShow() {
        orderList = orderListData();

        column_customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
//        column_customer_name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
//        column_customer_address.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
//        column_customer_contact.setCellValueFactory(new PropertyValueFactory<>("customer_contact"));
//        column_customer_email.setCellValueFactory(new PropertyValueFactory<>("customer_email"));
        column_customer_cate.setCellValueFactory(new PropertyValueFactory<>("customer_cate"));
        column_customer_prodName.setCellValueFactory(new PropertyValueFactory<>("customer_prodName"));
        column_customer_quantity.setCellValueFactory(new PropertyValueFactory<>("customer_quantity"));
        column_customer_totalPrice.setCellValueFactory(new PropertyValueFactory<>("customer_totalPrice"));
        column_customer_date.setCellValueFactory(new PropertyValueFactory<>("customer_date"));
        column_customer_color.setCellValueFactory(new PropertyValueFactory<>("customer_color"));
        tbl_order.setItems(orderList);
    }
    public void addPurchase() {
        purchaseCustomerId();
        getSpinnerValues();
        orderGetPrice();

        String sqlAdd = "insert into customers(customer_id,customer_cate,customer_prodName,customer_quanlity,customer_totalPrice, customer_date,customer_color)"  
                + "values(?,?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            
            Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            if (category.getText().isEmpty()
                    || color.getSelectionModel().getSelectedItem() == null
                    || productname.getSelectionModel().getSelectedItem() == null
                    || qty == 0) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {
               preparedStatement = conn.prepareStatement(sqlAdd);
                preparedStatement.setString(1, String.valueOf(customerId));
                preparedStatement.setString(2, category.getText());
                preparedStatement.setString(3, (String) productname.getSelectionModel().getSelectedItem());
                preparedStatement.setString(4, String.valueOf(qty));
                totalP = (price * qty);
                preparedStatement.setString(5, String.valueOf(totalP));
                preparedStatement.setString(6, String.valueOf(sqlDate));
                preparedStatement.setString(7, (String) color.getSelectionModel().getSelectedItem());

                preparedStatement.executeUpdate();

                //update tableview
                orderDataShow();
                
                //display total price
                displayTotal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void deletePurchase(){
        String deleteOrder = "delete from customers where customer_id='"+customerId+"'";
        conn = database.ConnectDB();
        try {
            if (category.getText().isEmpty()
                    || productname.getSelectionModel().getSelectedItem() == null
                    || color.getSelectionModel().getSelectedItem() == null
                    || qty == 0) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the product " + category.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(deleteOrder);

                    InforBox("Deleted Succesfully!", null, "Information");
                    orderDataShow();
                    orderReset();
                    displayTotal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void purchasePay() {
        purchaseCustomerId();
        displayTotal();
        String sql = "insert into bills(bill_customer_id,bill_total_payment,bill_date)"
                + "values(?,?,?)";
        conn = database.ConnectDB();
        try {
            if (tbl_order.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose the product");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Check Out");
                alert.setHeaderText(null);
                alert.setContentText("Do you want to pay this invoice?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(customerId));
                    preparedStatement.setString(2, String.valueOf(totalPP));
                    preparedStatement.setString(3, String.valueOf(sqlDate));

                    preparedStatement.executeUpdate();
                    
                    InforBox("Paid Succesfully!", null, "Information");
                    
                    orderDataShow();
                    displayTotal();

                } else return;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private double totalPP = 0;

    public void displayTotal() {
//        purchaseCustomerId();
        String sql = "select sum(customer_totalPrice) as totalPrice from customers where customer_id='" + customerId + "'";
        conn = database.ConnectDB();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                totalPP = resultSet.getDouble("totalPrice");
            }
            text_total.setText("$" + String.valueOf(totalPP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double totalP = 0;
    private double price = 0;

    public void orderGetPrice() {
        String sqlorderGetPrice = "select product_price from products where product_name='"
                + productname.getSelectionModel().getSelectedItem() + "'";
        conn = database.ConnectDB();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlorderGetPrice);

            if (resultSet.next()) {
                price = resultSet.getDouble("product_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CheckPhone() {

        String sqlSearch = "select *from customers  where customer_contact='" + text_phonecustomer.getText() + "'";

        conn = database.ConnectDB();
        try {
            preparedStatement = conn.prepareStatement(sqlSearch);
            resultSet = preparedStatement.executeQuery();
            ObservableList listName = FXCollections.observableArrayList();
            ObservableList listEmail = FXCollections.observableArrayList();
            ObservableList listAddress = FXCollections.observableArrayList();
            while (resultSet.next()) {
                listName.add(resultSet.getString("customer_name"));
                listEmail.add(resultSet.getString("customer_email"));
                listAddress.add(resultSet.getString("customer_address"));
                text_comCustName.setItems(listName);
                text_comCustEmail.setItems(listEmail);
                text_comCustAdd.setItems(listAddress);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void orderReset() {
        category.setText("");
        productname.getSelectionModel().clearSelection();
        brand.getSelectionModel().clearSelection();
        model.getSelectionModel().clearSelection();
        color.getSelectionModel().clearSelection();
        quantity.getEditor().setText("1");
        text_comCustName.getSelectionModel().clearSelection();
        text_phonecustomer.setText("");
        text_comCustAdd.getSelectionModel().clearSelection();
        text_comCustEmail.getSelectionModel().clearSelection();
    }
    
    
    private double x = 0;
    private double y = 0;
    
    public void NewCustomer(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.newcustomer));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
//            root.setOnMouseDragged((event) -> {
//                stage.setX(event.getScreenX() - x);
//                stage.setY(event.getScreenY() - y);
//
//                stage.setOpacity(.8);
//            });
            root.setOnMouseReleased((event) -> {
                stage.setOpacity(1);
            });
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void AddCustomer(){
//        String sqlcustomerorder = "update customers set customer_cate='" + category.getText()
//                + "',customer_prodName='" + productname.getSelectionModel().getSelectedItem()
//                + "',customer_color='" + color.getSelectionModel().getSelectedItem()
//
//                + "'where customer_id='"+customerId+"'";
//        conn = database.ConnectDB();
//        
//        try {
//            statement = conn.createStatement();
//            statement.executeUpdate(sqlcustomerorder);
//
//            InforBox("Updated Successfully", null, "Information");
//            orderListData();
//            orderDataShow();
//            
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void purchaseReceipt() throws SQLException{
        String sql = "select max(bill_id) as bill_id from bills";
        conn = database.ConnectDB();
        int id = 0;
        statement = conn.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            id = resultSet.getInt("bill_id");
        }
        HashMap hash = new HashMap();
        hash.put("smartPara", String.valueOf(id));
        try {
            Alert alert;
            if(totalP == 0){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid");
                alert.showAndWait();
            }else{
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Smart_Electric Official\\Smart_Electronic\\src\\main\\resources\\subsc\\smart_electronic\\views\\receipt\\receipt.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, conn);

            JasperViewer.viewReport(jPrint, false);
            JasperExportManager.exportReportToPdfFile(jPrint,"C:\\Users\\ADMIN\\Documents\\NetBeansProjects\\Smart_Electric Official\\Smart_Electronic\\src\\main\\resources\\subsc\\smart_electronic\\views\\receipt\\receiptPDF\\receipt_"+id+".pdf");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void signout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you really want to sign out?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            btn_signout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.Login));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged((event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(.8);
            });
            root.setOnMouseReleased((event) -> {
                stage.setOpacity(1);
            });
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        }
    }
    
    public void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you really want to exit?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }

    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    public void InforError(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    public void InforBox(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinner();
        orderDataShow();
        orderListData();
        searchCatergory();
        displayTotal();
        displayEmployee();
    }
}
