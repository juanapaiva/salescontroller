package br.com.salescontroller.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.salescontroller.dao.ClientsDAO;
import br.com.salescontroller.models.ClientsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ClientController implements Initializable {

    // Register tab attributes  
    @FXML
    private TextField tbName;
    @FXML
    private TextField tbEmail;    
    @FXML
    private TextField tbCellphone;
    @FXML
    private TextField tbPhone;
    @FXML
    private TextField tbCep;
    @FXML
    private TextField tbAddress;
    @FXML
    private TextField tbAddressNumber;
    @FXML
    private TextField tbNeighborhood;
    @FXML
    private TextField tbCity;
    @FXML
    private TextField tbComplement;

    @FXML
    private ChoiceBox<String> cbState;
    private String[] states = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};

    @FXML
    private TextField tbRg;
    @FXML
    private TextField tbCpf;

    // Buttons
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    // Search tab attributes
    @FXML
    private TextField tbNameSearch;

    @FXML
    private Button btnSearch;

    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbState.getItems().addAll(states);
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
    void btnNewAction(ActionEvent event) {

    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        ClientsModel client = new ClientsModel();

        client.setClientName(tbName.getText().toUpperCase());
        client.setRg(tbRg.getText().toUpperCase());
        client.setCpf(tbCpf.getText().toUpperCase());
        client.setEmail(tbEmail.getText().toUpperCase());
        client.setPhone(tbPhone.getText().toUpperCase());
        client.setCellphone(tbCellphone.getText().toUpperCase());
        client.setCep(tbCep.getText().toUpperCase());
        client.setAddress(tbAddress.getText().toUpperCase());
        client.setAddressNumber(Integer.parseInt(tbAddressNumber.getText()));
        client.setComplement(tbComplement.getText().toUpperCase());
        client.setNeighborhood(tbNeighborhood.getText().toUpperCase());
        client.setCity(tbCity.getText().toUpperCase());
        client.setState(cbState.getValue());

        ClientsDAO dao = new ClientsDAO();
        dao.create(client);
    }

    @FXML
    void btnEditAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

}
