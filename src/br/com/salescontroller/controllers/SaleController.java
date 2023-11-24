package br.com.salescontroller.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SaleController {

    @FXML
    private Button btnAddItem;

    @FXML
    private Button btnCancelSale;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnPaymentPage;

    @FXML
    private Button btnSearchClient;

    @FXML
    private Button btnSearchProduct;

    @FXML
    private TableColumn<?, ?> tableCId;

    @FXML
    private TableColumn<?, ?> tableCPrice;

    @FXML
    private TableColumn<?, ?> tableCProduct;

    @FXML
    private TableColumn<?, ?> tableCQuantity;

    @FXML
    private TableColumn<?, ?> tableCSubtotal;

    @FXML
    private TableView<?> tableSalesItens;

    @FXML
    private TextField tfClientCpf;

    @FXML
    private TextField tfClientName;

    @FXML
    private TextField tfProductDescription;

    @FXML
    private TextField tfProductId;

    @FXML
    private TextField tfProductPrice;

    @FXML
    private TextField tfProductQuantity;

    @FXML
    private DatePicker tfSaleDate;

    @FXML
    private TextField tfSaleTotal;

    @FXML
    void btnAddItemAction(ActionEvent event) {

    }

    @FXML
    void btnCancelSaleAction(ActionEvent event) {

    }

    @FXML
    void btnMenuAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentPageAction(ActionEvent event) {

    }

    @FXML
    void btnSearchClientAction(ActionEvent event) {

    }

    @FXML
    void btnSearchProductAction(ActionEvent event) {

    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {

    }

}
