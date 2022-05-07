/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class GestionAdresseController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField DateD;
    @FXML
    private TextField DateF;
    @FXML
    private TextField Desc;
    @FXML
    private TableView<?> tbevenement;
    @FXML
    private TableColumn<?, ?> tbid;
    @FXML
    private TableColumn<?, ?> tbtitre;
    @FXML
    private TableColumn<?, ?> tbville;
    @FXML
    private TableColumn<?, ?> tbrue;
    @FXML
    private TableColumn<?, ?> tbcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addAdresse(ActionEvent event) {
    }
    
}
