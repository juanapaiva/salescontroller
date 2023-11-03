package br.com.salescontroller.controllers;

import br.com.salescontroller.dao.EmployeesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPasscode;

    @FXML
    private Button btnLogin;

    @FXML
    void btnLoginAction(ActionEvent event) {
        EmployeesDAO dao = new EmployeesDAO();
        dao.login(tfEmail.getText(), tfPasscode.getText());
    }

}
