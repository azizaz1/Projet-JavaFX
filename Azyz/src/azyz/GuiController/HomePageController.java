/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package azyz.GuiController;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class HomePageController implements Initializable {

    @FXML
    private Label usernameLabel11;
    @FXML
    private Label usernameLabel1;
    @FXML
    private JFXButton check_in_Buttton;
    @FXML
    private JFXButton check_in_Buttton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) {
                System.exit(0);
    }

    @FXML
    private void goTO_Category(ActionEvent event) {
             try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Azyz/Gui/CategoryList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goTO_Produit(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Azyz/Gui/ProduitList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
