/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package azyz.GuiController;

import azyz.Entity.Category;
import azyz.Entity.Produit;
import azyz.Service.ServiceCategory;
import azyz.Service.ServiceProduit;
import azyz.Utils.JfreeChartApi;
import azyz.Utils.Myconnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ProduitListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField NomProduitTf;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Produit> ProduitTab;
    @FXML
    private TableColumn<Produit, Integer> IDProduitTab;
    @FXML
    private TableColumn<Produit, Integer> CategoryIDTab;
    @FXML
    private TableColumn<Produit, String> NomTab;
    @FXML
    private TableColumn<Produit, String> CouleurTab;
    @FXML
    private TableColumn<Produit, Float> PrixTab;
    @FXML
    private TableColumn<Produit, Integer> QuantitéTab;
    @FXML
    private TableColumn<Produit, String> imageTab;
    @FXML
    private TextField CouleurProduitTf;
    @FXML
    private TextField PrixProduitTf;
    
    @FXML
    private TextField QuantiteProduitTf;
    @FXML
    private Button image;
    @FXML
    private ImageView image_view;

    @FXML
    private TextField TFSearch;
    @FXML
    private Label file_path;
        @FXML
    private TextField CategoryProduitTf1;
        
        
    private Connection conn= Myconnexion.getInstance().getCnx();;
    private Statement ste;
    private PreparedStatement pste;
    ServiceProduit ps=new ServiceProduit();
    ServiceCategory cs = new ServiceCategory();
    int id_produit ; 
    ObservableList<Produit> data=FXCollections.observableArrayList();
    @FXML
    private Button Statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            refreshlist();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

            public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(ps.afficher());
            //System.out.println(data);
            FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_produit()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(p.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getCouleur().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getPrix()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(String.valueOf(p.getQuantite()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ProduitTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ProduitTab.setItems(sortedData);
             
        }
            
        public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(ps.afficher());
            ProduitTab.setEditable(true);
            ProduitTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDProduitTab.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            NomTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            CouleurTab.setCellValueFactory(new PropertyValueFactory<>("couleur"));
            PrixTab.setCellValueFactory(new PropertyValueFactory<>("prix"));
            QuantitéTab.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            imageTab.setCellValueFactory(new PropertyValueFactory<>("Image"));
            CategoryIDTab.setCellValueFactory(new PropertyValueFactory<>("category"));
            ProduitTab.setItems(data);
    }
        
    @FXML
    private void AjouterProduit(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("azyz/Gui/AjouterProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteProduit(ActionEvent event) throws SQLException {
        int Id;
        Id=ProduitTab.getSelectionModel().getSelectedItem().getId_produit();
        ps.supprimer(id_produit);
        refreshlist();
        recherche_avance();
    }

    
    public void updateProduit() throws SQLException{
        Produit p = new Produit(Integer.parseInt(CategoryProduitTf1.getText()),file_path.getText(), NomProduitTf.getText(),CouleurProduitTf.getText(),Float.parseFloat(PrixProduitTf.getText()),Integer.parseInt(QuantiteProduitTf.getText()));
        ps.modifier(id_produit, p);
        refreshlist(); 
    }

         
    @FXML
    private void EditProduit(ActionEvent event) throws SQLException {
            updateProduit();
            refreshlist();
            recherche_avance();
    }

    @FXML
    private void fillforum(MouseEvent event) throws SQLException {
        Produit p=ProduitTab.getSelectionModel().getSelectedItem();
        id_produit=p.getId_produit();
        NomProduitTf.setText(p.getNom());
        CouleurProduitTf.setText(p.getCouleur());
        PrixProduitTf.setText(Float.toString(p.getPrix()));
        QuantiteProduitTf.setText(Integer.toString(p.getQuantite()));
        CategoryProduitTf1.setText(Integer.toString(p.getCategory()));
        file_path.setText(p.getImage());
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
    private void Statistique(ActionEvent event) {
        ServiceProduit ms = new ServiceProduit();
        HashMap<String, Double> data = ms.StatistiqueParNom();
        JfreeChartApi chart = new JfreeChartApi(data);
        chart.afficherStatistique();
    }
}
