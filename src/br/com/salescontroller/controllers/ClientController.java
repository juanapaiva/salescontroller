package br.com.salescontroller.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ClientController {

    // Register tab attributes
    
    @FXML
    private TextField tbCode;    
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
    private TextField tbNumberAddress;
    @FXML
    private TextField tbNeighborhood;
    @FXML
    private TextField tbCity;
    @FXML
    private TextField tbComplement;
    @FXML
    private SplitMenuButton spmState;
    @FXML
    private TextField tbRg;
    @FXML
    private TextField tbCpf;

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

}
