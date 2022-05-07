/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package azyz.GuiController;

import azyz.Entity.Category;
import azyz.Entity.Produit;
import azyz.Service.ServiceCategory;
import azyz.Service.ServiceProduit;
import azyz.Utils.Myconnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CategoryListController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField TypeCategoryTf;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Category> CategoryTab;
    @FXML
    private TableColumn<Category, Integer> IDCategoryTab;
    @FXML
    private TableColumn<Category, String> TypeCategoryTab;
    @FXML
    private TableColumn<Category, String> NomCategoryTab;
    @FXML
    private TableColumn<Category, String> imageTab;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField NomCategoryTf;
    @FXML
    private Button image;
    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;

    private Connection conn= Myconnexion.getInstance().getCnx();;
    private Statement ste;
    private PreparedStatement pste;
    ServiceCategory cs = new ServiceCategory();
    int id_category ; 
    ObservableList<Category> data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }    

    
    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(cs.afficher());
            //System.out.println(data);
            FilteredList<Category> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId_category()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    else if(p.getNom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    else if(p.getType().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Category> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CategoryTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CategoryTab.setItems(sortedData);   
        }
    
    public void refreshlist() throws SQLException{
            data.clear();
            data = FXCollections.observableArrayList(cs.afficher());
            CategoryTab.setEditable(true);
            CategoryTab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            IDCategoryTab.setCellValueFactory(new PropertyValueFactory<>("id_category"));
            TypeCategoryTab.setCellValueFactory(new PropertyValueFactory<>("type"));
            NomCategoryTab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            imageTab.setCellValueFactory(new PropertyValueFactory<>("image"));
            CategoryTab.setItems(data);
    }
    

    private void DeleteProduit(ActionEvent event) {
        try {
            int Id;
            Id=CategoryTab.getSelectionModel().getSelectedItem().getId_category();
            try {
                cs.supprimer(id_category);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateCategory() throws SQLException{
                Category c = new Category(TypeCategoryTf.getText(),
                                  NomCategoryTf.getText(),
                                  file_path.getText()); 
        cs.modifier(id_category, c);
        refreshlist(); 
    }
    @FXML
    private void EditCategory(ActionEvent event) {
        try {
            updateCategory();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void fillforum(MouseEvent event) {
        Category c=CategoryTab.getSelectionModel().getSelectedItem();
        id_category=c.getId_category();
        TypeCategoryTf.setText(c.getNom());
        NomCategoryTf.setText(c.getType());
        file_path.setText(c.getImage());  
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
    private void AjouterCategory(ActionEvent event) {
            try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("azyz/Gui/AjouterCategory.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteCategory(ActionEvent event) {
        int Id;
        Id=CategoryTab.getSelectionModel().getSelectedItem().getId_category();
        try {
            cs.supprimer(id_category);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
