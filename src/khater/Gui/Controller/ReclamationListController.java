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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import khater.Entity.Reclamation;
import khater.Service.ServiceReclamation;
import khater.Utils.JfreeChartApi;
import khater.Utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ReclamationListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField TitleReclamationTf;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Reclamation> ReclamationTab;
    @FXML
    private TableColumn<Reclamation, Integer> IDReclamationTab;
    @FXML
    private TableColumn<Reclamation, Integer> UserIdTab;
    @FXML
    private TableColumn<Reclamation, String> TitleTab;
    @FXML
    private TableColumn<Reclamation, String> ObjectTab;
    @FXML
    private TableColumn<Reclamation, String> DecpritionTab;
    @FXML
    private TableColumn<Reclamation, LocalDateTime> DateTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField ObjetReclamationTf;
    @FXML
    private TextField DesciptionReclamationTf;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    
        ServiceReclamation sr =new ServiceReclamation();
    int id;
    Reclamation r;
    ObservableList<Reclamation> data=FXCollections.observableArrayList();
    @FXML
    private Button Statistique;
    @FXML
    private Label nbReclamation;
    @FXML
    private JFXComboBox<Integer> cb_User;
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
         fillcomboUser();
        nbReclamation.setText(Integer.toString(sr.nbReclamation()));
        try {
            
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(sr.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDReclamationTab.setVisible(false);
        IDReclamationTab.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        UserIdTab.setCellValueFactory(new PropertyValueFactory<>("user"));
        TitleTab.setCellValueFactory(new PropertyValueFactory<>("titre_reclamation"));
        ObjectTab.setCellValueFactory(new PropertyValueFactory<>("object_reclamation"));
        DecpritionTab.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("date_Reclamation"));
        ReclamationTab.setItems(data);
    }
    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sr.afficher());
            //System.out.println(data);
            FilteredList<Reclamation> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_reclamation()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getUser()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(p.getTitre_reclamation().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getObject_reclamation().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getDescription_reclamation().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getDate_Reclamation()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ReclamationTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ReclamationTab.setItems(sortedData);
             
        }
           
    @FXML
    private void AjouterReclamation(ActionEvent event) {
                         try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("khater/Gui/AjouterReclamation.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void fillforum(MouseEvent event) {
        Reclamation r=ReclamationTab.getSelectionModel().getSelectedItem();
        id=r.getId_reclamation();
        cb_User.setValue(r.getUser());
        TitleReclamationTf.setText(r.getTitre_reclamation());
        ObjetReclamationTf.setText(r.getObject_reclamation());
        DesciptionReclamationTf.setText(r.getDescription_reclamation());
        date.setValue(r.getDate_Reclamation().toLocalDate());

    }

    @FXML
    private void DeleteReclamation(ActionEvent event) {
        int Id;
        Id=ReclamationTab.getSelectionModel().getSelectedItem().getId_reclamation();
        try {
            sr.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateReclamation() throws SQLException{
        
        Reclamation r = new Reclamation(cb_User.getValue(),
                                                 TitleReclamationTf.getText(), 
                                                 ObjetReclamationTf.getText(),
                                                 DesciptionReclamationTf.getText(),
                                                 date.getValue().atStartOfDay());
        sr.modifier(id, r);
        refreshlist(); 
    }
    
    @FXML
    private void EditReclamation(ActionEvent event) {
        try {
            updateReclamation();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Statistique(ActionEvent event) {
        HashMap<String, Double> data = sr.StatistiqueParNom();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
    
}
