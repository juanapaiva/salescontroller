package br.com.salescontroller.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.EmployeesDAO;
import br.com.salescontroller.models.EmployeesModel;
import br.com.salescontroller.models.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
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

public class EmployeeController implements Initializable {

    @FXML
    private TabPane tabPaneEmployees;

    // Register tab attributes
    @FXML
    private AnchorPane paneEmployee;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfRg;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPasscode;
    @FXML
    private TextField tfPosition;
    @FXML
    private ChoiceBox<String> cbAccessLevel;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfCellphone;
    @FXML
    private TextField tfCep;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfAddressNumber;
    @FXML
    private TextField tfComplement;
    @FXML
    private TextField tfNeighborhood;
    @FXML
    private TextField tfCity;
    @FXML
    private ChoiceBox<String> cbState;

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
    private TableView<EmployeesModel> tableEmployees;

    @FXML
    private TableColumn<EmployeesModel, Integer> tableCId;
    @FXML
    private TableColumn<EmployeesModel, String> tableCEmployeename;
    @FXML
    private TableColumn<EmployeesModel, String> tableCRg;
    @FXML
    private TableColumn<EmployeesModel, String> tableCCpf;
    @FXML
    private TableColumn<EmployeesModel, String> tableCEmail;
    @FXML
    private TableColumn<EmployeesModel, String> tableCPosition;
    @FXML
    private TableColumn<EmployeesModel, String> tableCAccessLevel;
    private String[] accessLevels = {"Usuário", "Administrador"};
    @FXML
    private TableColumn<EmployeesModel, String> tableCPhone;
    @FXML
    private TableColumn<EmployeesModel, String> tableCCellphone;
     @FXML
    private TableColumn<EmployeesModel, String> tableCCep;
    @FXML
    private TableColumn<EmployeesModel, String> tableCAddress;
    @FXML
    private TableColumn<EmployeesModel, Integer> tableCAddressNumber;
    @FXML
    private TableColumn<EmployeesModel, String> tableCComplement;
    @FXML
    private TableColumn<EmployeesModel, String> tableCNeighborhood;
    @FXML
    private TableColumn<EmployeesModel, String> tableCCity;
    @FXML
    private TableColumn<EmployeesModel, String> tableCState;

    // Search tab buttons
    @FXML
    private Button btnSearch;

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbState.getItems().addAll(Utils.states);
        cbAccessLevel.getItems().addAll(accessLevels);
    }

    // View actions
    @FXML
    void checkNumberFormat(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
        }
    }

    @FXML
    void selectedRegisterAction(MouseEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabPaneEmployees.getSelectionModel();
        selectionModel.select(0);

        tfId.setText(tableEmployees.getSelectionModel().getSelectedItem().getId().toString());
        tfName.setText(tableEmployees.getSelectionModel().getSelectedItem().getName());
        tfRg.setText(tableEmployees.getSelectionModel().getSelectedItem().getRg());
        tfCpf.setText(tableEmployees.getSelectionModel().getSelectedItem().getCpf());
        tfEmail.setText(tableEmployees.getSelectionModel().getSelectedItem().getEmail());
        tfPasscode.setText(tableEmployees.getSelectionModel().getSelectedItem().getPasscode());
        tfPosition.setText(tableEmployees.getSelectionModel().getSelectedItem().getPosition());
        cbAccessLevel.getSelectionModel().select(tableEmployees.getSelectionModel().getSelectedItem().getAccessLevel());
        tfPhone.setText(tableEmployees.getSelectionModel().getSelectedItem().getPhone());
        tfCellphone.setText(tableEmployees.getSelectionModel().getSelectedItem().getCellphone());
        tfCep.setText(tableEmployees.getSelectionModel().getSelectedItem().getCep());
        tfAddress.setText(tableEmployees.getSelectionModel().getSelectedItem().getAddress());
        tfAddressNumber.setText(tableEmployees.getSelectionModel().getSelectedItem().getAddressNumber().toString());
        tfComplement.setText(tableEmployees.getSelectionModel().getSelectedItem().getComplement());
        tfNeighborhood.setText(tableEmployees.getSelectionModel().getSelectedItem().getNeighborhood());
        tfCity.setText(tableEmployees.getSelectionModel().getSelectedItem().getCity());
        cbState.getSelectionModel().select(tableEmployees.getSelectionModel().getSelectedItem().getState());
    }

    @FXML
    void cepKeyPressedAction(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                EmployeesModel employee =  new EmployeesModel();
                EmployeesDAO dao = new EmployeesDAO();
                employee = dao.findCep(tfCep.getText());
                
                tfAddress.setText(employee.getAddress());
                tfNeighborhood.setText(employee.getNeighborhood());
                tfCity.setText(employee.getCity());
                cbState.setValue(employee.getState());
                break;
        }
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        EmployeesModel employee = new EmployeesModel();

        employee.setName(tfName.getText().toUpperCase());
        employee.setRg(tfRg.getText());
        employee.setCpf(tfCpf.getText());
        employee.setEmail(tfEmail.getText().toUpperCase());
        employee.setPasscode(tfPasscode.getText());
        employee.setPosition(tfPosition.getText().toUpperCase());
        employee.setAccessLevel(cbAccessLevel.getValue());
        employee.setPhone(tfPhone.getText());
        employee.setCellphone(tfCellphone.getText());
        employee.setCep(tfCep.getText());
        employee.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        employee.setAddressNumber(addressNumber);
        
        employee.setComplement(tfComplement.getText().toUpperCase());
        employee.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        employee.setCity(tfCity.getText().toUpperCase());
        employee.setState(cbState.getValue());

        EmployeesDAO dao = new EmployeesDAO();
        dao.create(employee);
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        EmployeesModel employee = new EmployeesModel();

        employee.setName(tfName.getText().toUpperCase());
        employee.setRg(tfRg.getText().toUpperCase());
        employee.setCpf(tfCpf.getText().toUpperCase());
        employee.setEmail(tfEmail.getText().toUpperCase());
        employee.setPasscode(tfPasscode.getText());
        employee.setPosition(tfPosition.getText().toUpperCase());
        employee.setAccessLevel(cbAccessLevel.getValue());
        employee.setPhone(tfPhone.getText().toUpperCase());
        employee.setCellphone(tfCellphone.getText().toUpperCase());
        employee.setCep(tfCep.getText().toUpperCase());
        employee.setAddress(tfAddress.getText().toUpperCase());

        Integer addressNumber = (tfAddressNumber.getText().isEmpty()) ? 0 : Integer.parseInt(tfAddressNumber.getText());
        employee.setAddressNumber(addressNumber);
        
        employee.setComplement(tfComplement.getText().toUpperCase());
        employee.setNeighborhood(tfNeighborhood.getText().toUpperCase());
        employee.setCity(tfCity.getText().toUpperCase());
        employee.setState(cbState.getValue());

        employee.setId(Integer.parseInt(tfId.getText()));

        EmployeesDAO dao = new EmployeesDAO();
        dao.updateAll(employee);
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        EmployeesModel employee = new EmployeesModel();

        employee.setId(Integer.parseInt(tfId.getText()));

        EmployeesDAO dao = new EmployeesDAO();
        dao.delete(employee);
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
        EmployeesDAO dao = new EmployeesDAO();

        // Select all if name is null
        ObservableList<EmployeesModel> employees = (tfNameSearch.getText().isEmpty()) ? dao.readAll() : dao.readByName(tfNameSearch.getText()+"%");

        tableCId.setCellValueFactory(new PropertyValueFactory<EmployeesModel, Integer>("id"));
        tableCEmployeename.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("employeename"));
        tableCRg.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("rg"));
        tableCCpf.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("cpf"));
        tableCEmail.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("email"));
        tableCPosition.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("position"));
        tableCAccessLevel.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("accesslevel"));
        tableCPhone.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("phone"));
        tableCCellphone.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("cellphone"));
        tableCCep.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("cep"));
        tableCAddress.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("address"));
        tableCAddressNumber.setCellValueFactory(new PropertyValueFactory<EmployeesModel, Integer>("addressnumber"));
        tableCComplement.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("complement"));
        tableCNeighborhood.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("neighborhood"));
        tableCCity.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("city"));
        tableCState.setCellValueFactory(new PropertyValueFactory<EmployeesModel, String>("state"));

        tableEmployees.setItems(employees);
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        Utils.cleanFields(paneEmployee);
    }


}
