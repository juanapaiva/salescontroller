package br.com.salescontroller.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnClientsPage;

    @FXML
    private Button btnEmployeesPage;

    @FXML
    private Button btnLogout;

    @FXML
    void btnClientsPageAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/ClientPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Tela de Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnEmployeesPageAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/EmployeePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Tela de Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnLogoutAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/LoginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Autenticação de Usuário");
        stage.setScene(scene);
        stage.show();
    }

}
