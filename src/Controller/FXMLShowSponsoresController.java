/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entities.sponsore;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Service.ServiceSponsore;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class FXMLShowSponsoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<sponsore> table;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> desc;
    
    @FXML
    private Button btndelete;
    @FXML
    private Button Update;
   

    @FXML
    private Button goback;
    @FXML
    private TextField Recherche;
    @FXML
    private ChoiceBox<String> choiseBox;
    ObservableList<String> comboList = FXCollections.observableArrayList("Sponsore Name", "Sponsore Description");
    @FXML
    private TextField SponsoreNametf;
    @FXML
    private TextField SponsoreDescriptiontf;
    
    String ID_Sponsore_New;
    

 
    @FXML
    private Button acceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiseBox.setItems(comboList);
       afficherSponsore();
        table.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSponsoresDetails(newValue);
                    }
                });
        Recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    filtrerSponsoreList((String) oldValue, (String) newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLShowSponsoresController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    void filtrerSponsoreList(String oldValue, String newValue) throws SQLException {

        ServiceSponsore s = new ServiceSponsore();

        String choix = choiseBox.getValue();
        if (choix.equals("Sponsore Name")) {
            ObservableList<sponsore> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                table.setItems(s.affichesponsore());

            } else {
                newValue = newValue.toUpperCase();
                for (sponsore st : table.getItems()) {

                    String filterSp = st.getNomsponsore();

                    if (filterSp.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }

                table.setItems(filteredList);
            }
        } else if (choix.equals("Sponsore Description")) {
            ObservableList<sponsore> filteredList = FXCollections.observableArrayList();
            if (Recherche.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
                table.setItems(s.affichesponsore());

            }else{
                           newValue = newValue.toUpperCase();
                for (sponsore st : table.getItems()) {

                    String filterSp = st.getNomsponsore();

                    if (filterSp.toUpperCase().contains(newValue)) {
                        filteredList.add(st);
                    }
                }

                table.setItems(filteredList);
            
            
            
            }
            
            
            
        } 

    }

    public void afficherSponsore() {
        ServiceSponsore s = new ServiceSponsore();

        name.setCellValueFactory(new PropertyValueFactory<>("Nomsponsore"));
        desc.setCellValueFactory(new PropertyValueFactory<>("NomDescription"));
        table.setItems(null);
        table.setItems(s.affichesponsore());
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (!table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Sponsore ?");
            alert.setHeaderText("Are you sure you want to delete this Sponsore : " + table.getSelectionModel().getSelectedItem().getNomsponsore() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                 ServiceSponsore st = new ServiceSponsore();
                st.supprimerSponsore(table.getSelectionModel().getSelectedItem().getId());
               afficherSponsore();
                EmptyFields(event);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Would you select a sponsore ! !");
            Optional<ButtonType> result = alert.showAndWait();
        }
        EmptyFields(event);
    }
    
    @FXML
    private void BackToHome(MouseEvent event) {
         loadPage(event,"/View/FXMLSponsore.fxml");
    }
    
    
    private void loadPage(MouseEvent event,String pageName){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene= new Scene((Parent) FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (    IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Update(ActionEvent event) {

        if (!table.getSelectionModel().isEmpty()) {
               ServiceSponsore s = new ServiceSponsore();
            sponsore ss = new sponsore();

            ss.setId(Integer.valueOf(ID_Sponsore_New));
            ss.setNomsponsore(SponsoreNametf.getText());
            ss.setNomDescription(SponsoreDescriptiontf.getText());

            s.updateSponsore(ss);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Stadium ");
            alert.setHeaderText("Updating d stadium :"
                    + table.getSelectionModel().getSelectedItem().getNomDescription() + " done");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Would you select a stadium !  ");
            Optional<ButtonType> result = alert1.showAndWait();
        }
        afficherSponsore();
        EmptyFields(event);
    }

    @FXML
    private void goBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLSponsore.fxml"));
        try {
            Parent root = loader.load();
            FXMLSponsoreController dc = loader.getController();
            goback.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLShowSponsoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void event(ActionEvent event) {
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/gestEventFXML.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    void showSponsoresDetails(sponsore st) {
        ID_Sponsore_New = String.valueOf(st.getId());  
        SponsoreNametf.setText(st.getNomsponsore());
        SponsoreDescriptiontf.setText(st.getNomDescription());
       

    }

    private void EmptyFields(ActionEvent event) {
        SponsoreNametf.clear();
        SponsoreDescriptiontf.clear();
  
    }

    

    @FXML
    private void acceuil(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLSponsore.fxml"));
        try {
            Parent root = loader.load();
            FXMLSponsoreController dc = loader.getController();
            acceuil.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLSponsoreController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }


}