/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khater.Gui.Controller;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import khater.Entity.Reclamation;
import khater.Service.ServiceReclamation;
import khater.Utils.MailerApiReclamation;
import khater.Utils.Myconnexion;
import khater.Utils.SMSApiReclamation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField TitleReclamationTf;
    @FXML
    private TextField ObjetReclamationTf;
    @FXML
    private TextField DesciptionReclamationTf;
    @FXML
    private DatePicker date;
    @FXML
    private JFXComboBox<Integer> cb_User;
    
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                fillcomboUser();
    }    

            public void fillcomboUser(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from user";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsUser.add(rs.getInt("id"));
            }
            cb_User.setItems(optionsUser);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Acceuil(ActionEvent event) {
                     try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("khater/Gui/HomePage.fxml"));
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
        if(TitleReclamationTf.getText().trim().isEmpty()){
            erreurs+="Title vide\n";
        }
        if(ObjetReclamationTf.getText().trim().isEmpty()){
            erreurs+="Objet vide\n";
        }
        if(DesciptionReclamationTf.getText().trim().isEmpty()){
            erreurs+="Description vide\n";
        }
        if(  date.getValue() != null
             &&   date.getValue().isAfter(LocalDate.now())
               ){
            erreurs+="date must be before\n";
        } 
        if(date.getValue() == null){
            erreurs+="date vide\n";
        } 
        
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServiceReclamation SR = new ServiceReclamation();
        Reclamation r = new Reclamation(cb_User.getValue(),
                                                 TitleReclamationTf.getText(), 
                                                 ObjetReclamationTf.getText(),
                                                 DesciptionReclamationTf.getText(),
                                                 date.getValue().atStartOfDay());
                  SR.ajouter(r);        
                                                                                 //Notification
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tilte = "Added Successful";
        message ="Reclamation créer avec success";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(10000));
        
        // SEND MAIL
        MailerApiReclamation mailer = new MailerApiReclamation();
        mailer.SendMail("Elkhater.elkharouf@esprit.tn", "Admin.");
        
                //SEND SMS
        SMSApiReclamation sms = new SMSApiReclamation();
        sms.sendSMS("+21622204202", "Admin.");
    }
    }
    
    @FXML
    private void AfficherReclamation(ActionEvent event) {
                             try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("khater/Gui/ReclamationList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
    
