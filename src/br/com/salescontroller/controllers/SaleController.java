package br.com.salescontroller.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.salescontroller.dao.ClientsDAO;
import br.com.salescontroller.dao.ProductsDAO;
import br.com.salescontroller.models.ClientsModel;
import br.com.salescontroller.models.ProductModel;
import br.com.salescontroller.models.SaleItensModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private TableColumn<SaleItensModel, Float> tableCPrice;

    @FXML
    private TableColumn<SaleItensModel, String> tableCProduct;

    @FXML
    private TableColumn<SaleItensModel, Integer> tableCQuantity;

    @FXML
    private TableColumn<SaleItensModel, Float> tableCSubtotal;

    @FXML
    private TableView<SaleItensModel> tableSalesItens;

    private ObservableList<SaleItensModel> saleItens = FXCollections.observableArrayList();
    private Float total = 0f;

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
    private Spinner<Integer> tfProductQuantity;

    private SpinnerValueFactory<Integer> valueFactory;

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

    ObservableList<SaleItensModel> getProductItemRegistry() {
        SaleItensModel saleItem = new SaleItensModel();

        saleItem.setProduct(tfProductDescription.getText());
        saleItem.setQuantity(tfProductQuantity.getValue());
        saleItem.setPrice(Float.parseFloat(tfProductPrice.getText()));
        saleItem.setSubtotal();

        saleItens.add(saleItem);

        total += saleItem.getSubtotal();

        return saleItens;
    }

    @FXML
    void btnAddItemAction(ActionEvent event) {      
        ObservableList<SaleItensModel> saleItens = getProductItemRegistry();       
        
        tableCProduct.setCellValueFactory(new PropertyValueFactory<SaleItensModel, String>("product"));
        tableCQuantity.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Integer>("quantity"));
        tableCPrice.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Float>("price"));
        tableCSubtotal.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Float>("subtotal"));
        
        tableSalesItens.setItems(saleItens);
        
        tfSaleTotal.setText(total.toString());
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

    void searchProduct() {
        ProductsDAO dao = new ProductsDAO();

        Integer productId = (tfProductId.getText().isEmpty() ? 0 : Integer.parseInt(tfProductId.getText()));
        ObservableList<ProductModel> products = dao.readByProductId(productId);

        if (productId == 0) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        } else {
            try {
                tfProductDescription.setText(products.get(0).getProductDescription());
                tfProductPrice.setText(products.get(0).getPrice().toString());

                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, products.get(0).getStock());
                tfProductQuantity.setValueFactory(valueFactory);
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            }
        }
    }

    @FXML
    void btnSearchProductAction(ActionEvent event) {
        searchProduct();
    }

    @FXML
    void productIdEnterAction(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) searchProduct();
    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {

    }

}
