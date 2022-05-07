/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package azyz.GuiController;

import azyz.Entity.Category;
import azyz.Service.ServiceCategory;
import azyz.Utils.MailerApiCategory;
import azyz.Utils.MailerApiProduit;
import azyz.Utils.SMSApiCategory;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class AjouterCategoryController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField TypeCategoryTf;
    @FXML
    private TextField NomCategoryTf;
    @FXML
    private TextField Emailtf;
    @FXML
    private Button image;
    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Acceuil(ActionEvent event) {
             try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Azyz/Gui/HomePage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
   private void Enregister(ActionEvent event) {
                String erreurs="";
        if(TypeCategoryTf.getText().trim().isEmpty()){
            erreurs+="Type vide\n";
        }
        if(NomCategoryTf.getText().trim().isEmpty()){
            erreurs+="Nom Categoie vide\n";
        }
        if(Emailtf.getText().trim().isEmpty()){
            erreurs+="Email vide\n";
        }
        
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Category");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServiceCategory Cs = new ServiceCategory();
        Category c = new Category(TypeCategoryTf.getText(),
                                  NomCategoryTf.getText(),
                                  file_path.getText()); 
                                                  
       Cs.ajouter(c);

                                                //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Category créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(7000));
        
        // SEND MAIL
        MailerApiCategory mailer = new MailerApiCategory();
        mailer.SendMail(Emailtf.getText(), "Admin.");
        
                //SEND SMS
        SMSApiCategory sms = new SMSApiCategory();
        sms.sendSMS("+21658932889", "Admin.");
    }
    }
    @FXML
    private void image(ActionEvent event) {
                FileChooser open = new FileChooser();
        Stage stage = (Stage)left_main.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
           String path = file.getAbsolutePath();
           path = path.replace("\\", "\\\\");
           file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            image_view.setImage(image);       
        }else{

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    private void afficherCategorie(ActionEvent event) {
                       try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Azyz/Gui/CategoryList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}