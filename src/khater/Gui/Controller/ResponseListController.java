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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import khater.Entity.Reclamation;
import khater.Entity.Response;
import khater.Service.ServiceReclamation;
import khater.Service.ServiceResponse;
import khater.Utils.Myconnexion;


public class ResponseListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Response> ResponseTab;
    @FXML
    private TableColumn<Response, Integer> IDResponseTab;
    @FXML
    private TableColumn<Response, Integer> UserIdTab;
    @FXML
    private TableColumn<Response, Integer> ReclamationTab;
    @FXML
    private TableColumn<Response, String> titleTab;
    @FXML
    private TableColumn<Response, String> BodyTab;
    @FXML
    private TableColumn<Response, LocalDateTime> DateTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField TitleResponseTf;
    @FXML
    private TextField BodyResponseTf;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
        
    ServiceResponse sr =new ServiceResponse();
    int id;
    Response r;
    ObservableList<Response> data=FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<Integer> cb_Reclamation;
    @FXML
    private JFXComboBox<Integer> cb_User;
    
    ServiceReclamation Sr ;
    ObservableList<Integer> options=FXCollections.observableArrayList();
    ObservableList<Integer> optionsUser=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshlist();
        fillcombo();
        fillcomboUser();
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
        public void fillcombo(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from reclamation";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                options.add(rs.getInt("id_reclamation"));
                
            }
            cb_Reclamation.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterResponseController.class.getName()).log(Level.SEVERE, null, ex);
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
        IDResponseTab.setVisible(false);
        IDResponseTab.setCellValueFactory(new PropertyValueFactory<>("id_Response"));
        UserIdTab.setCellValueFactory(new PropertyValueFactory<>("user"));
        ReclamationTab.setCellValueFactory(new PropertyValueFactory<>("reclamation"));
        titleTab.setCellValueFactory(new PropertyValueFactory<>("Title_Response"));
        BodyTab.setCellValueFactory(new PropertyValueFactory<>("body_Response"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("date_Response"));
        ResponseTab.setItems(data);
    }
    
           public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(sr.afficher());
            //System.out.println(data);
            FilteredList<Response> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_Response()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getUser()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    if (String.valueOf(p.getReclamation()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(p.getTitle_Response().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getBody_Response().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getDate_Response()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Response> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ResponseTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ResponseTab.setItems(sortedData);
             
        }

    @FXML
    private void AjouterResponse(ActionEvent event) {
             try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("khater/Gui/AjouterResponse.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteResponse(ActionEvent event) {
        int Id;
        Id=ResponseTab.getSelectionModel().getSelectedItem().getId_Response();
        try {
            sr.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateResponse() throws SQLException{
        ServiceResponse SR = new ServiceResponse();
        Response r = new Response(cb_User.getValue(),
                                 cb_Reclamation.getValue(), 
                                 TitleResponseTf.getText(),
                                 BodyResponseTf.getText(),
                                 date.getValue().atStartOfDay());
        sr.modifier(id, r);
        refreshlist(); 
    }
    
    @FXML
    private void EditResponse(ActionEvent event) {
          try {
            updateResponse();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    @FXML
    private void fillforum(MouseEvent event) {
        Response r=ResponseTab.getSelectionModel().getSelectedItem();
        id=r.getId_Response();
        cb_User.setValue(r.getUser());
        cb_Reclamation.setValue(r.getReclamation());
        TitleResponseTf.setText(r.getTitle_Response());
        BodyResponseTf.setText(r.getTitle_Response());
        date.setValue(r.getDate_Response().toLocalDate());
    }
    
}
