package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.ProductsDAO;
import br.com.salescontroller.dao.SuppliersDAO;
import br.com.salescontroller.models.ProductModel;
import br.com.salescontroller.models.SuppliersModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class ProductController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private ChoiceBox<SuppliersModel> cbSupplier;

    @FXML
    private ChoiceBox<SuppliersModel> cbSupplierSearch;

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

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SuppliersDAO daoSupplier = new SuppliersDAO();
        ObservableList<SuppliersModel> suppliers = daoSupplier.readAll();

        cbSupplier.getItems().removeAll();
        cbSupplierSearch.getItems().removeAll();
        for (SuppliersModel supplier : suppliers) {
            cbSupplier.getItems().add(supplier);
            cbSupplierSearch.getItems().add(supplier);
        }
    }

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
    void btnMenuAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/MenuPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        ProductModel product = new ProductModel();

        product.setProductDescription(txtDescription.getText().toUpperCase());
        Float price = (tfPrice.getText().isEmpty()) ? 0 : Float.parseFloat(tfPrice.getText());
        product.setPrice(price);
        Integer stock = (tfStock.getText().isEmpty()) ? 0 : Integer.parseInt(tfStock.getText());
        product.setStock(stock);
        product.setSupplier(cbSupplier.getValue());

        ProductsDAO dao = new ProductsDAO();
        dao.create(product);
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
