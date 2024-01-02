package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class SaleController implements Initializable {

    // Screen navigation properties
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Maintaining state
    static Boolean firstEntry = true;
    
    static String clientCpf = "";
    static String clientName = "";
    static Integer productId = 0;
    static String product = "";
    static Float price = 0f;
    static Integer quantity = 0;
    static Float total = 0f;
    static Float saleItemPrice = 0f;

    static ObservableList<SaleItensModel> saleItens = FXCollections.observableArrayList();

    //
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

    //static ObservableList<SaleItensModel> carItens;

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

    static SpinnerValueFactory<Integer> valueFactory;
    private Map<Integer, Integer> productStock = new HashMap<>();

    @FXML
    private DatePicker tfSaleDate;

    @FXML
    private TextField tfSaleTotal;

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {;
        tfSaleDate.setValue(LocalDate.now());

        if (!firstEntry) {
            setStaticVariablesAndFields('f');
            setCartItens();
        }
        firstEntry = false;
    }

    @FXML
    void checkNumberFormat(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
        }
    }

    ObservableList<SaleItensModel> getProductItemRegistry() {
        SaleItensModel saleItem = new SaleItensModel();

        saleItem.setId(Integer.parseInt(tfProductId.getText()));
        saleItem.setProduct(tfProductDescription.getText());
        saleItem.setQuantity(tfProductQuantity.getValue());
        saleItem.setPrice(Float.parseFloat(tfProductPrice.getText()));
        saleItem.setSubtotal();

        saleItens.add(saleItem);

        saleItemPrice = saleItem.getSubtotal();

        return saleItens;
    }

    Boolean itemExists(ObservableList<SaleItensModel> carItens) {
        Set<Integer> uniques = new HashSet<>();
        for (SaleItensModel item : carItens) {
            if (uniques.add(item.getId())) continue;
            else return true;
        }
        return false;
    }

    Boolean isOnStock(ObservableList<SaleItensModel> carItens) {
        System.out.println("Car Item ID: " + carItens.get(carItens.size()-1).getId());
        System.out.println("Sale Item ID: " + saleItens.get(saleItens.size()-1).getId());
        return true;
    }

    void setCartItens() {
        ObservableList<SaleItensModel> carItens = getProductItemRegistry();

        if (!itemExists(carItens) && isOnStock(carItens)) {
            tableCProduct.setCellValueFactory(new PropertyValueFactory<SaleItensModel, String>("product"));
            tableCQuantity.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Integer>("quantity"));
            tableCPrice.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Float>("price"));
            tableCSubtotal.setCellValueFactory(new PropertyValueFactory<SaleItensModel, Float>("subtotal"));
            
            tableSalesItens.setItems(saleItens);
            total += saleItemPrice;
        } else {
            saleItens.remove(saleItens.size()-1);
        }
    }

    @FXML
    void btnAddItemAction(ActionEvent event) {      
        setCartItens();

        Integer quantityAdded = (tfProductQuantity.getValue() == null) ? 0 : tfProductQuantity.getValue();

        //System.out.println(valueFactory.getValue());
        //System.out.println(productStock.get(Integer.parseInt(tfProductId.getText())) - quantityAdded);

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 
                                                productStock.get(Integer.parseInt(tfProductId.getText())) - quantityAdded);
        tfProductQuantity.setValueFactory(valueFactory);
        
        tfSaleTotal.setText(total.toString());
    }

    @FXML
    void btnCancelSaleAction(ActionEvent event) {

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

    void setStaticVariablesAndFields(char type) {
        if (type == 'v') {
            clientCpf = tfClientCpf.getText();
            clientName = tfClientName.getText();
            productId = Integer.parseInt(tfProductId.getText());
            product = tfProductDescription.getText();
            price = Float.parseFloat(tfProductPrice.getText());
            quantity = tfProductQuantity.getValue();
        } else if (type == 'f') {
            tfClientCpf.setText(clientCpf);
            tfClientName.setText(clientName);
            tfProductId.setText(productId.toString());
            tfProductDescription.setText(product);
            tfProductPrice.setText(price.toString());
            tfProductQuantity.setValueFactory(valueFactory);
        }

    }

    @FXML
    void btnPaymentPageAction(ActionEvent event) throws IOException {
        //validateStock();
        setStaticVariablesAndFields('v');

        root = FXMLLoader.load(getClass().getResource("../views/PaymentPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Pagamento");
        stage.setScene(scene);
        stage.show();
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

    /*void validateStock(Integer productId, ObservableList<ProductModel> products) {
        productStock.put(productId, products.get(0).getStock());

        ProductsDAO dao = new ProductsDAO();

        if (products.get(0).getStock() < )
        tfProductDescription.setText(products.get(0).getProductDescription());
        tfProductPrice.setText(products.get(0).getPrice().toString());

        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, products.get(0).getStock());
        tfProductQuantity.setValueFactory(valueFactory);

    }*/

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

                productStock.put(productId, products.get(0).getStock());

                Integer quantityAdded = (tfProductQuantity.getValue() == null) ? 0 : tfProductQuantity.getValue();
                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 
                                                        productStock.get(Integer.parseInt(tfProductId.getText())) - quantityAdded);
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
