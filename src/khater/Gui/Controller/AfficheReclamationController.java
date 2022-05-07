/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khater.Gui.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import khater.Entity.Reclamation;
import khater.Service.MyListener;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficheReclamationController implements Initializable {


    /**
     * Initializes the controller class.
     */
    
            private Reclamation r;
    private MyListener myListener;
    @FXML
    private Label titre;
    @FXML
    private Label objet;
    @FXML
    private Label desciption;
    @FXML
    private Label date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setData(Reclamation r,MyListener myListener){
        this.r = r;
         this.myListener=myListener;
        titre.setText(r.getTitre_reclamation());
        objet.setText(r.getObject_reclamation());
        desciption.setText(r.getDescription_reclamation());
        date.setText(String.valueOf(r.getDate_Reclamation()));     
    }
    @FXML
    private void selectedbp(MouseEvent event) {
         myListener.onClickListener(r);
    }
    
}
