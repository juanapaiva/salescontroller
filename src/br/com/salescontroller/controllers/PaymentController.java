package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PaymentController implements Initializable {

    // Screen navigation properties
    private Stage stage;
    private Scene scene;
    private Parent root;

    //
    @FXML
    private Button btnMenu;

    @FXML
    private Button btnPay;

    @FXML
    private TextField tfCard;

    @FXML
    private TextField tfChange;

    @FXML
    private TextField tfCheck;

    @FXML
    private TextField tfMoney;

    @FXML
    private TextField tfTotal;
    
    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {;
        tfTotal.setText(SaleController.total.toString());
    }

    @FXML
    void btnMenuAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/SalePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnPayAction(ActionEvent event) {

    }

    @FXML
    void cepKeyPressedAction(KeyEvent event) {

    }

    @FXML
    void checkNumberFormat(KeyEvent event) {

    }

}
