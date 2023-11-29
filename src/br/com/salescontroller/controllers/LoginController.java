package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.EmployeesDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPasscode;

    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> tfEmail.requestFocus());

        btnLogin.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER))
                    try {
                        btnLoginAction(new ActionEvent());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            
        });
    }

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
