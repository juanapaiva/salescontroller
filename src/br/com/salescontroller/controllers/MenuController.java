package br.com.salescontroller.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuController {

    // Screen navigation properties
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Screen items
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuIClientsControl;

    @FXML
    private MenuItem menuIEmployeesControl;

    @FXML
    private MenuItem menuILogout;

    // Screen actions
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
    void menuILogoutAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
        stage = (Stage)menuBar.getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Autenticação de Usuário");
        stage.setScene(scene);
        stage.show();
    }

}
