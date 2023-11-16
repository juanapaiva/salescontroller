package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.models.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    // Screen navigation properties
    private Stage stage;
    private Scene scene;
    private Parent root;

    //
    public static String currentUser;

    // Screen items
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuIClientsControl;

    @FXML
    private MenuItem menuIEmployeesControl;

    @FXML
    private MenuItem menuISuppliersControl;

    @FXML
    private MenuItem menuILogout;

    @FXML
    private Label lCurrentUser;

    // Screen actions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lCurrentUser.setText(currentUser);
    }

    //
    @FXML
    void menuIClientsControlAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/ClientPage.fxml"));
        stage = (Stage)menuBar.getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Tela de Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void menuIEmployeesControlAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/EmployeePage.fxml"));
        stage = (Stage)menuBar.getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Tela de Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void menuISuppliersControlAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/SupplierPage.fxml"));
        stage = (Stage)menuBar.getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Tela de Fornecedores");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void menuILogoutAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
        stage = (Stage)menuBar.getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Autenticação de Usuário");
        stage.setScene(scene);
        stage.show();
    }

}
