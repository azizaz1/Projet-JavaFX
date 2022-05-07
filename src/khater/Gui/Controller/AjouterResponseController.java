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
import khater.Entity.Response;
import khater.Service.ServiceReclamation;
import khater.Service.ServiceResponse;
import khater.Utils.MailerApiResponse;
import khater.Utils.Myconnexion;
import khater.Utils.SMSApiResponse;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterResponseController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField TitleResponseTf;
    @FXML
    private TextField BodyResponseTf;
    @FXML
    private DatePicker date;
    @FXML
    private JFXComboBox<String> cb_Reclamation;
    @FXML
    private JFXComboBox<Integer> cb_User;
    
        ServiceReclamation Sr = new ServiceReclamation();
    ObservableList<String> options=FXCollections.observableArrayList();
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        options = FXCollections.observableArrayList(Sr.getByTitle());
        cb_Reclamation.setItems(options);
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
        if(TitleResponseTf.getText().trim().isEmpty()){
            erreurs+="Title vide\n";
        }
        if(BodyResponseTf.getText().trim().isEmpty()){
            erreurs+="Body vide\n";
        }
        if(  date.getValue() != null
             &&   date.getValue().isBefore(LocalDate.now())
               ){
            erreurs+="date must be after\n";
        } 
        if(date.getValue() == null){
            erreurs+="date vide\n";
        } 
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Response");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        ServiceResponse SR = new ServiceResponse();
        Response r = new Response(cb_User.getValue(),
                                  Sr.findTritre(cb_Reclamation.getValue()).getId_reclamation(), 
                                                 TitleResponseTf.getText(),
                                                 BodyResponseTf.getText(),
                                                 date.getValue().atStartOfDay());
                  SR.ajouter(r);                                                            //Notification
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
        tray.showAndDismiss(Duration.millis(2000));
        
        // SEND MAIL
        MailerApiResponse mailer = new MailerApiResponse();
        mailer.SendMail("", "Admin.");
        
                //SEND SMS
       /* SMSApiResponse sms = new SMSApiResponse();
        sms.sendSMS("+21620071775", "Admin.");*/
    }
    }

    @FXML
    private void afficherResponse(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("khater/Gui/ResponseList.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ResponseListController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
}
