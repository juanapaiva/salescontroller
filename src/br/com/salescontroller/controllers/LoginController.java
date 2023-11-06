package br.com.salescontroller.controllers;

import java.io.IOException;

import br.com.salescontroller.dao.EmployeesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPasscode;

    @FXML
    private Button btnLogin;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        EmployeesDAO dao = new EmployeesDAO();

        if (dao.login(tfEmail.getText(), tfPasscode.getText())) {
            root = FXMLLoader.load(getClass().getResource("../views/MenuPage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

}
