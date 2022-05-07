/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package azyz.GuiController;

import azyz.Entity.Produit;
import azyz.Service.ServiceProduit;
import azyz.Utils.MailerApiProduit;
import azyz.Utils.SMSApiCategory;
import azyz.Utils.SMSApiProduit;
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
import javafx.scene.control.ComboBox;
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
public class AjouterProduitController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField NomProduitTf;
    @FXML
    private TextField CouleurProduitTf;
    @FXML
    private TextField PrixProduitTf;
    @FXML
    private TextField Emailtf;
    @FXML
    private Button image;
    @FXML
    private ImageView image_view;
    @FXML
    private TextField CategoryProduitTf1;
    @FXML
    private Label file_path;
    @FXML
    private TextField QuantiteProduitTf;

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
        if(CategoryProduitTf1.getText().trim().isEmpty()){
            erreurs+="Category vide\n";
        }
        if(NomProduitTf.getText().trim().isEmpty()){
            erreurs+="Nom vide\n";
        }
        if(CouleurProduitTf.getText().trim().isEmpty()){
            erreurs+="Couleur vide\n";
        }
        if(PrixProduitTf.getText().trim().isEmpty()){
            erreurs+="Prix vide\n";
        }
        if(QuantiteProduitTf.getText().trim().isEmpty()){
            erreurs+="Quantite vide\n";
        }
        if(Emailtf.getText().trim().isEmpty()){
            erreurs+="Email vide\n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Produit");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServiceProduit Sp = new ServiceProduit();
        Produit p = new Produit(Integer.parseInt(CategoryProduitTf1.getText()),
                                                 file_path.getText(), 
                                                 NomProduitTf.getText(),
                                                 CouleurProduitTf.getText(),
                                                 Float.parseFloat(PrixProduitTf.getText()),
                                                 Integer.parseInt(QuantiteProduitTf.getText()));
                  Sp.ajouter(p);        
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Azyz/Gui/ProduitList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Produit créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        
        // SEND MAIL
        MailerApiProduit mailer = new MailerApiProduit();
        mailer.SendMail(Emailtf.getText(), "Admin.");
        
                //SEND SMS
        SMSApiProduit sms = new SMSApiProduit();
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
    private void afficherProduit(ActionEvent event) {
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
