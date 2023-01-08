/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.connectViews.electronicConnectViews;
import subsc.smart_electronic.models.getData;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane admin_login_form;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_signInAdmin;

    @FXML
    private PasswordField text_admin_pass;

    @FXML
    private PasswordField text_emp_pass;

    @FXML
    private Button btn_signInEmp;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private Hyperlink hyperToAdmin;

    @FXML
    private Hyperlink hyperToEmplo;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField text_admin_username;

    @FXML
    private TextField text_emp_id;

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private double x = 0;
    private double y = 0;

    public void adminLogin() {
        String adminData = "select *from admin where username=? and password=?";

        conn = database.ConnectDB();
        try {
            if (text_admin_username.getText().isEmpty() || text_admin_pass.getText().isEmpty()) {
                infoError("Please fill all the blank fields!", null, "Error message");
            } else {
                preparedStatement = conn.prepareStatement(adminData);
                preparedStatement.setString(1, text_admin_username.getText());
                preparedStatement.setString(2, text_admin_pass.getText());

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    getData.AdminUsername = text_admin_username.getText();
                    infoBox("Login Successfully!", null, "Infromation");
                    btn_signInAdmin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.admin_dashboard));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseReleased((MouseEvent event) -> {
                        stage.setOpacity(1);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    infoError("The user name or password is wrong!", null, "Error message");
                    text_admin_username.setText("");
                    text_admin_pass.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void empLogin() {
       
        String empData = "select *from employees where emp_id=? and emp_pwd=?";

        conn = database.ConnectDB();
        try {
            if (text_emp_id.getText().isEmpty() || text_emp_pass.getText().isEmpty()) {
                infoError("Please fill all the blank fields!", null, "Error message");
            } else {
                preparedStatement = conn.prepareStatement(empData);
                preparedStatement.setString(1, text_emp_id.getText());
                preparedStatement.setString(2, text_emp_pass.getText());

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    getData.employeeId = text_emp_id.getText();
                    infoBox("Login Successfully!", null, "Infromation");
                    btn_signInAdmin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.employee));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseReleased((MouseEvent event) -> {
                        stage.setOpacity(1);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    infoError("The employee_id or password is wrong!", null, "Error message");
                    text_emp_id.setText("");
                    text_emp_pass.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == hyperToEmplo) {
            employee_form.setVisible(true);
            admin_login_form.setVisible(false);
        } else if (event.getSource() == hyperToAdmin) {
            admin_login_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }

    public void close() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to close?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void infoError(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
