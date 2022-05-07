/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khater.Gui.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import khater.Entity.Reclamation;
import khater.Service.MyListener;
import khater.Service.ServiceReclamation;


public class ClientController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
            ServiceReclamation Sr=new ServiceReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            refresh();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           MyListener myListener;
    public void selectedProduit(Reclamation r){
//        idgetter.setText(String.valueOf(p.getId_produit()));
//         System.out.println(idgetter.getText());
        
    }
    public void refresh() throws SQLException{
    
          
         
         List<Reclamation> reclamations = Sr.afficher();
          
         
         if(reclamations.size() > 0){
             
          selectedProduit(reclamations.get(0));
          myListener = new MyListener() {
              @Override
              public void onClickListener(Reclamation p) {
                  selectedProduit(p);
              }
             
         };
                  }
         
        int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < reclamations.size(); i++) {
                
                
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/khater/Gui/AfficheReclamation.fxml"));
            
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheReclamationController itemController = fxmlLoader.getController();
                itemController.setData(reclamations.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  }
    

