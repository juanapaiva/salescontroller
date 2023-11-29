package br.com.salescontroller.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.salescontroller.dao.ClientsDAO;
import br.com.salescontroller.dao.ProductsDAO;
import br.com.salescontroller.models.ClientsModel;
import br.com.salescontroller.models.ProductModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SaleController implements Initializable {

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

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {;
        tfSaleDate.setValue(LocalDate.now());
    }

    @FXML
    void checkNumberFormat(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
        }
    }

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

    void searchClient() {
        ClientsDAO dao = new ClientsDAO();

        ObservableList<ClientsModel> clients = dao.readByCpf(tfClientCpf.getText());

        try {
            tfClientName.setText(clients.get(0).getName());
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "CPF não encontrado.");
        }
    }

    @FXML
    void btnSearchClientAction(ActionEvent event) {        
        searchClient();
    }

    @FXML
    void cpfEnterAction(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) searchClient();
    }

    @FXML
    void btnSearchProductAction(ActionEvent event) {

    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {

    }

}
