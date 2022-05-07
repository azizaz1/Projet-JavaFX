/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Entities.sponsore;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Service.ServiceSponsore;


/**
 * FXML Controller class
 *
 * @author aziz
 */
public class FXMLSponsoreController implements Initializable {

    @FXML
    private TextField SponsoreName;
    @FXML
    private TextField SponsoreDescription;
    @FXML
    private Button addSponsore;
    @FXML
    private Button Cancel;
 
    @FXML
    private ListView<?> ListView;
    @FXML
    private Button afficher;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl12;
    @FXML
    private Label lbl13;
    @FXML
    private Label lbl14;
    @FXML
    private Label lbl5;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddSponsore(ActionEvent event) throws SQLException {

    
       
      
        String Sponsore_Name = SponsoreName.getText();
        String Sponsore_Desc = SponsoreDescription.getText();
      

        sponsore s = new sponsore(Sponsore_Name, Sponsore_Desc);
      
        ServiceSponsore ss = new ServiceSponsore();
        ss.AjouterSponsore(s);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLShowSponsores.fxml"));
        try {
            Parent root = loader.load();
            FXMLShowSponsoresController dc = loader.getController();
            addSponsore.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLShowSponsoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EmptyFields(ActionEvent event) {
        SponsoreName.clear();
        SponsoreDescription.clear();
   
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/View/FXMLSponsore.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSponsoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 


    @FXML
    private void afficherSponsore(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/View/FXMLShowSponsores.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLSponsoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}