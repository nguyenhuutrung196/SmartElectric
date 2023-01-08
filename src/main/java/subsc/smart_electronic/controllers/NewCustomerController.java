/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.models.customerData;

/**
 *
 * @author ADMIN
 */
public class NewCustomerController implements Initializable {

    @FXML
    private AnchorPane newcustomerform;

    @FXML
    private TableColumn<customerData, String> col_address;

    @FXML
    private TableColumn<customerData, String> col_customerName;

    @FXML
    private TableColumn<customerData, String> col_customerPhone;

    @FXML
    private TableColumn<customerData, String> col_email;

    @FXML
    private TableView<customerData> tableView_customer;

    @FXML
    private Button btn_addcustomer;

    @FXML
    private Button btn_clear;

    @FXML
    private TextField text_address;

    @FXML
    private TextField text_customer;

    @FXML
    private TextField text_email;

    @FXML
    private TextField text_phonecustomer;

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

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
                customerId += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerAdd() {
        String sql = "INSERT INTO customers (customer_name,customer_address,customer_contact,customer_email) VALUES(?,?,?,?)";
        conn = database.ConnectDB();

        try {

            Alert alert;

            //check empty fields
            if (text_customer.getText().isEmpty() || text_address.getText().isEmpty() || text_phonecustomer.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill blank fields");
                alert.showAndWait();

            } else if (ValidationText() & ValidationEmail() & ValidationPhoneNumber()) {

//                alert = new Alert(AlertType.WARNING);
//                alert.setTitle("Validate Text");
//                alert.headerTextProperty();
//                alert.setContentText("Please Enter Valid Text");
//                alert.showAndWait();
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, text_customer.getText());
                preparedStatement.setString(2, text_address.getText());
                preparedStatement.setString(3, text_phonecustomer.getText());
                preparedStatement.setString(4, text_email.getText());

                preparedStatement.executeUpdate();

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Add the Customer's information?");
                alert.showAndWait();
                customerListData();
                showListCustomer();
                CustomerReset();
                newcustomerform.getScene().getWindow().hide();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerSelect() {
        customerData custSelect = tableView_customer.getSelectionModel().getSelectedItem();
        int num = tableView_customer.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        text_customer.setText(custSelect.getCustomerName());
        text_address.setText(custSelect.getAddress());
        text_phonecustomer.setText(custSelect.getCustomerPhone());
        text_email.setText(custSelect.getEmail());

    }

    public ObservableList<customerData> customerListData() {
        ObservableList<customerData> custList = FXCollections.observableArrayList();
        String sql = "select *from customers";
        conn = database.ConnectDB();
        try {
            customerData customer;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new customerData(resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_address"),
                        resultSet.getString("customer_contact"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("customer_cate"),
                        resultSet.getString("customer_prodName"),
                        resultSet.getInt("customer_quanlity"),
                        resultSet.getDouble("customer_totalPrice"),
                        resultSet.getString("customer_color")
                );
                custList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return custList;
    }
    private ObservableList<customerData> addListCustomer;

    public void showListCustomer() {
        addListCustomer = customerListData();
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_customerPhone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView_customer.setItems(addListCustomer);

    }

    public void CustomerReset() {

        text_customer.setText("");
        text_phonecustomer.setText("");
        text_address.setText("");
        text_email.setText("");

    }

    public boolean ValidationText() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(text_customer.getText());
        if (m.find() && m.group().equals(text_customer.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Text");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Text");
            alert.showAndWait();

            return false;
        }

    }

    public boolean ValidationEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(text_email.getText());
        if (m.find() && m.group().equals(text_email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }

    }

    public boolean ValidationPhoneNumber() {
        Pattern p = Pattern.compile("[0][0-9]{9}");
        Matcher m = p.matcher(text_phonecustomer.getText());
        if (m.find() && m.group().equals(text_phonecustomer.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Phone Number");
            alert.showAndWait();

            return false;
        }

    }

    public void closes() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you really want to exit?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showListCustomer();
        customerListData();
    }

}
