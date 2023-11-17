package br.com.salescontroller.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductController {

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSearch;

    @FXML
    private ChoiceBox<?> cbSupplier;

    @FXML
    private ChoiceBox<?> cbSupplierSearch;

    @FXML
    private AnchorPane paneProduct;

    @FXML
    private TabPane tabPaneProducts;

    @FXML
    private TableColumn<?, ?> tableCDescription;

    @FXML
    private TableColumn<?, ?> tableCId;

    @FXML
    private TableColumn<?, ?> tableCPrice;

    @FXML
    private TableColumn<?, ?> tableCStock;

    @FXML
    private TableColumn<?, ?> tableCSupplier;

    @FXML
    private TableView<?> tableProducts;

    @FXML
    private TextField tfDescriptionSearch;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfStock;

    @FXML
    private TextArea txtDescription;

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnEditAction(ActionEvent event) {

    }

    @FXML
    void btnMenuAction(ActionEvent event) {

    }

    @FXML
    void btnSaveAction(ActionEvent event) {

    }

    @FXML
    void btnSearchAction(ActionEvent event) {

    }

    @FXML
    void checkNumberFormat(KeyEvent event) {

    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {

    }

}
