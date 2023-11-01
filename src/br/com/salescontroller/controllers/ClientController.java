package br.com.salescontroller.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.ClientsDAO;
import br.com.salescontroller.models.ClientsModel;
import br.com.salescontroller.models.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ClientController implements Initializable {

    @FXML
    private TabPane tabPaneClients;

    // Register tab attributes
    @FXML
    private AnchorPane paneClient;

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
    private TextField tfRg;
    @FXML
    private TextField tfCpf;

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
    private TableView<ClientsModel> tableClients;

    @FXML
    private TableColumn<ClientsModel, Integer> tableCId;
    @FXML
    private TableColumn<ClientsModel, String> tableCClientName;
    @FXML
    private TableColumn<ClientsModel, String> tableCRg;
    @FXML
    private TableColumn<ClientsModel, String> tableCCpf;
    @FXML
    private TableColumn<ClientsModel, String> tableCEmail;
    @FXML
    private TableColumn<ClientsModel, String> tableCPhone;
    @FXML
    private TableColumn<ClientsModel, String> tableCCellphone;
    @FXML
    private TableColumn<ClientsModel, String> tableCCep;
    @FXML
    private TableColumn<ClientsModel, String> tableCAddress;
    @FXML
    private TableColumn<ClientsModel, Integer> tableCAddressNumber;
    @FXML
    private TableColumn<ClientsModel, String> tableCComplement;
    @FXML
    private TableColumn<ClientsModel, String> tableCNeighborhood;
    @FXML
    private TableColumn<ClientsModel, String> tableCCity;
    @FXML
    private TableColumn<ClientsModel, String> tableCState;

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
    void selectedRegisterAction(MouseEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabPaneClients.getSelectionModel();
        selectionModel.select(0);

        tfId.setText(tableClients.getSelectionModel().getSelectedItem().getId().toString());
        tfName.setText(tableClients.getSelectionModel().getSelectedItem().getName());
        tfRg.setText(tableClients.getSelectionModel().getSelectedItem().getRg());
        tfCpf.setText(tableClients.getSelectionModel().getSelectedItem().getCpf());
        tfEmail.setText(tableClients.getSelectionModel().getSelectedItem().getEmail());
        tfPhone.setText(tableClients.getSelectionModel().getSelectedItem().getPhone());
        tfCellphone.setText(tableClients.getSelectionModel().getSelectedItem().getCellphone());
        tfCep.setText(tableClients.getSelectionModel().getSelectedItem().getCep());
        tfAddress.setText(tableClients.getSelectionModel().getSelectedItem().getAddress());
        tfAddressNumber.setText(tableClients.getSelectionModel().getSelectedItem().getAddressNumber().toString());
        tfComplement.setText(tableClients.getSelectionModel().getSelectedItem().getComplement());
        tfNeighborhood.setText(tableClients.getSelectionModel().getSelectedItem().getNeighborhood());
        tfCity.setText(tableClients.getSelectionModel().getSelectedItem().getCity());
        cbState.getSelectionModel().select(tableClients.getSelectionModel().getSelectedItem().getState());
    }

    @FXML
    void cepKeyPressedAction(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                ClientsModel client =  new ClientsModel();
                ClientsDAO dao = new ClientsDAO();
                client = dao.findCep(tfCep.getText());
                
                tfAddress.setText(client.getAddress());
                tfNeighborhood.setText(client.getNeighborhood());
                tfCity.setText(client.getCity());
                cbState.setValue(client.getState());
                break;
        }
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        ClientsModel client = new ClientsModel();

        client.setName(tfName.getText().toUpperCase());
        client.setRg(tfRg.getText().toUpperCase());
        client.setCpf(tfCpf.getText().toUpperCase());
        client.setEmail(tfEmail.getText().toUpperCase());
        client.setPhone(tfPhone.getText().toUpperCase());
        client.setCellphone(tfCellphone.getText().toUpperCase());
        client.setCep(tfCep.getText().toUpperCase());
        client.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        client.setAddressNumber(addressNumber);
        
        client.setComplement(tfComplement.getText().toUpperCase());
        client.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        client.setCity(tfCity.getText().toUpperCase());
        client.setState(cbState.getValue());

        ClientsDAO dao = new ClientsDAO();
        dao.create(client);
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        ClientsModel client = new ClientsModel();

        client.setName(tfName.getText().toUpperCase());
        client.setRg(tfRg.getText().toUpperCase());
        client.setCpf(tfCpf.getText().toUpperCase());
        client.setEmail(tfEmail.getText().toUpperCase());
        client.setPhone(tfPhone.getText().toUpperCase());
        client.setCellphone(tfCellphone.getText().toUpperCase());
        client.setCep(tfCep.getText().toUpperCase());
        client.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        client.setAddressNumber(addressNumber);
        
        client.setComplement(tfComplement.getText().toUpperCase());
        client.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        client.setCity(tfCity.getText().toUpperCase());
        client.setState(cbState.getValue());

        client.setId(Integer.parseInt(tfId.getText()));

        ClientsDAO dao = new ClientsDAO();
        dao.updateAll(client);
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        ClientsModel client = new ClientsModel();

        client.setId(Integer.parseInt(tfId.getText()));

        ClientsDAO dao = new ClientsDAO();
        dao.delete(client);
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
        ClientsDAO dao = new ClientsDAO();

        // Select all if name is null
        ObservableList<ClientsModel> clients = (tfNameSearch.getText().isEmpty()) ? dao.readAll() : dao.readByName(tfNameSearch.getText()+"%");

        tableCId.setCellValueFactory(new PropertyValueFactory<ClientsModel, Integer>("id"));
        tableCClientName.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("clientname"));
        tableCRg.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("rg"));
        tableCCpf.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("cpf"));
        tableCEmail.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("email"));
        tableCPhone.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("phone"));
        tableCCellphone.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("cellphone"));
        tableCCep.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("cep"));
        tableCAddress.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("address"));
        tableCAddressNumber.setCellValueFactory(new PropertyValueFactory<ClientsModel, Integer>("addressnumber"));
        tableCComplement.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("complement"));
        tableCNeighborhood.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("neighborhood"));
        tableCCity.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("city"));
        tableCState.setCellValueFactory(new PropertyValueFactory<ClientsModel, String>("state"));

        tableClients.setItems(clients);
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        Utils.cleanFields(paneClient);
    }
}
