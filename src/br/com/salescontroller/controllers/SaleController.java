package br.com.salescontroller.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SaleController {

    @FXML
    private Button btnMenu;

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
    void btnMenuAction(ActionEvent event) {

    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {

    }

}
