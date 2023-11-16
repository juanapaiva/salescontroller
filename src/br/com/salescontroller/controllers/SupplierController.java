package br.com.salescontroller.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.SuppliersDAO;
import br.com.salescontroller.models.SuppliersModel;
import br.com.salescontroller.models.Utils;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SupplierController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TabPane tabPaneSuppliers;

    @FXML
    private Button btnMenu;

    // Register tab attributes
    @FXML
    private AnchorPane paneSupplier;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;    
    @FXML
    private TextField tfCellphone;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfCep;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfAddressNumber;
    @FXML
    private TextField tfNeighborhood;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfComplement;
    @FXML
    private ChoiceBox<String> cbState;
    @FXML
    private TextField tfCnpj;

    // Register tab buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    // Search tab attributes
    @FXML
    private TextField tfNameSearch;

    @FXML
    private TableView<SuppliersModel> tableSuppliers;

    @FXML
    private TableColumn<SuppliersModel, Integer> tableCId;
    @FXML
    private TableColumn<SuppliersModel, String> tableCSupplierName;
    @FXML
    private TableColumn<SuppliersModel, String> tableCCnpj;
    @FXML
    private TableColumn<SuppliersModel, String> tableCEmail;
    @FXML
    private TableColumn<SuppliersModel, String> tableCPhone;
    @FXML
    private TableColumn<SuppliersModel, String> tableCCellphone;
    @FXML
    private TableColumn<SuppliersModel, String> tableCCep;
    @FXML
    private TableColumn<SuppliersModel, String> tableCAddress;
    @FXML
    private TableColumn<SuppliersModel, Integer> tableCAddressNumber;
    @FXML
    private TableColumn<SuppliersModel, String> tableCComplement;
    @FXML
    private TableColumn<SuppliersModel, String> tableCNeighborhood;
    @FXML
    private TableColumn<SuppliersModel, String> tableCCity;
    @FXML
    private TableColumn<SuppliersModel, String> tableCState;

    // Search tab buttons
    @FXML
    private Button btnSearch;

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbState.getItems().addAll(Utils.states);
    }

    // View actions
    @FXML
    void checkNumberFormat(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
        }
    }

    /*@FXML
    void checkPhoneFormat(KeyEvent event) {
        checkNumberFormat(event);

        String id = event.getTarget().toString();
        id = id.substring(event.getTarget().toString().indexOf("id=")+3, event.getTarget().toString().indexOf(","));

        System.out.println(tbCellphone.getText().length());

        if ((id.equals("tbPhone") && tbPhone.getText().length() > 10)
            || (id.equals("tbCellphone") && tbCellphone.getText().length() > 11)) {
            event.consume();
        }
    }*/

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
    void selectedRegisterAction(MouseEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabPaneSuppliers.getSelectionModel();
        selectionModel.select(0);

        tfId.setText(tableSuppliers.getSelectionModel().getSelectedItem().getId().toString());
        tfName.setText(tableSuppliers.getSelectionModel().getSelectedItem().getName());
        tfCnpj.setText(tableSuppliers.getSelectionModel().getSelectedItem().getCnpj());
        tfEmail.setText(tableSuppliers.getSelectionModel().getSelectedItem().getEmail());
        tfPhone.setText(tableSuppliers.getSelectionModel().getSelectedItem().getPhone());
        tfCellphone.setText(tableSuppliers.getSelectionModel().getSelectedItem().getCellphone());
        tfCep.setText(tableSuppliers.getSelectionModel().getSelectedItem().getCep());
        tfAddress.setText(tableSuppliers.getSelectionModel().getSelectedItem().getAddress());
        tfAddressNumber.setText(tableSuppliers.getSelectionModel().getSelectedItem().getAddressNumber().toString());
        tfComplement.setText(tableSuppliers.getSelectionModel().getSelectedItem().getComplement());
        tfNeighborhood.setText(tableSuppliers.getSelectionModel().getSelectedItem().getNeighborhood());
        tfCity.setText(tableSuppliers.getSelectionModel().getSelectedItem().getCity());
        cbState.getSelectionModel().select(tableSuppliers.getSelectionModel().getSelectedItem().getState());
    }

    @FXML
    void cepKeyPressedAction(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                SuppliersModel supplier =  new SuppliersModel();
                SuppliersDAO dao = new SuppliersDAO();
                supplier = dao.findCep(tfCep.getText());
                
                tfAddress.setText(supplier.getAddress());
                tfNeighborhood.setText(supplier.getNeighborhood());
                tfCity.setText(supplier.getCity());
                cbState.setValue(supplier.getState());
                break;
        }
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        SuppliersModel supplier = new SuppliersModel();

        supplier.setName(tfName.getText().toUpperCase());
        supplier.setCnpj(tfCnpj.getText().toUpperCase());
        supplier.setEmail(tfEmail.getText().toUpperCase());
        supplier.setPhone(tfPhone.getText().toUpperCase());
        supplier.setCellphone(tfCellphone.getText().toUpperCase());
        supplier.setCep(tfCep.getText().toUpperCase());
        supplier.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        supplier.setAddressNumber(addressNumber);
        
        supplier.setComplement(tfComplement.getText().toUpperCase());
        supplier.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        supplier.setCity(tfCity.getText().toUpperCase());
        supplier.setState(cbState.getValue());

        SuppliersDAO dao = new SuppliersDAO();
        dao.create(supplier);
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        SuppliersModel supplier = new SuppliersModel();

        supplier.setName(tfName.getText().toUpperCase());
        supplier.setCnpj(tfCnpj.getText().toUpperCase());
        supplier.setEmail(tfEmail.getText().toUpperCase());
        supplier.setPhone(tfPhone.getText().toUpperCase());
        supplier.setCellphone(tfCellphone.getText().toUpperCase());
        supplier.setCep(tfCep.getText().toUpperCase());
        supplier.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        supplier.setAddressNumber(addressNumber);
        
        supplier.setComplement(tfComplement.getText().toUpperCase());
        supplier.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        supplier.setCity(tfCity.getText().toUpperCase());
        supplier.setState(cbState.getValue());

        supplier.setId(Integer.parseInt(tfId.getText()));

        SuppliersDAO dao = new SuppliersDAO();
        dao.updateAll(supplier);
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        SuppliersModel supplier = new SuppliersModel();

        supplier.setId(Integer.parseInt(tfId.getText()));

        SuppliersDAO dao = new SuppliersDAO();
        dao.delete(supplier);
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
        SuppliersDAO dao = new SuppliersDAO();

        // Select all if name is null
        ObservableList<SuppliersModel> suppliers = (tfNameSearch.getText().isEmpty()) ? dao.readAll() : dao.readByName(tfNameSearch.getText()+"%");

        tableCId.setCellValueFactory(new PropertyValueFactory<SuppliersModel, Integer>("id"));
        tableCSupplierName.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("suppliername"));
        tableCCnpj.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("cnpj"));
        tableCEmail.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("email"));
        tableCPhone.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("phone"));
        tableCCellphone.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("cellphone"));
        tableCCep.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("cep"));
        tableCAddress.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("address"));
        tableCAddressNumber.setCellValueFactory(new PropertyValueFactory<SuppliersModel, Integer>("addressnumber"));
        tableCComplement.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("complement"));
        tableCNeighborhood.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("neighborhood"));
        tableCCity.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("city"));
        tableCState.setCellValueFactory(new PropertyValueFactory<SuppliersModel, String>("state"));

        tableSuppliers.setItems(suppliers);
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        Utils.cleanFields(paneSupplier);
    }
}
