/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import com.mysql.cj.Session;
import Entities.Evenement;
import Entities.Participation;
import Entities.sponsore;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.CrudEvenement;
import Service.ParticipationServices;
import Service.ServiceSponsore;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.StageStyle;
import javax.jnlp.PrintService;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.text.Document;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;



/**
 * FXML Controller class
 *
 * @author aziz
 */
public class GestEventFXMLController implements Initializable {

    @FXML
    private TextField EventName;
    @FXML
    private TextField EventPlace;
    @FXML
    private TextField EventDesc;
    @FXML
    private Button Valider;
    @FXML
    private Button Cancel;
    @FXML
    private Button GoBack;
    @FXML
    private Button UpdateEvent;
    @FXML
    private DatePicker EventDate;
    @FXML
    private TextField EventGouv;
    @FXML
    private TableView<Evenement> Table;
    @FXML
    private TableColumn<?, ?> EvName;
    @FXML
    private TableColumn<?, ?> EvDate;
    @FXML
    private TableColumn<?, ?> EvEndroit;
    @FXML
    private TableColumn<?, ?> Evdesc;
    @FXML
    private Button showmap;
      @FXML
    private Button map;
     @FXML
    private Button pdf;
    @FXML
    private DatePicker EventEnd;
    @FXML
    private TableColumn<?, ?> EvFin;
    @FXML
    private TableColumn<?, ?> Evgouv;
    @FXML
    private TableColumn<?, ?> nbrparticip;
    @FXML
    private Button Participer;
    @FXML
    private Label txtnbrparticipant;
    @FXML
    private TextField txtparticipant;
    
    int nbrpart=0;
    String newid;

    String ID_Event_New;

    CrudEvenement cr = new CrudEvenement();
    PreparedStatement pst;
    int xe;
    static String gouv;
    static String endro;
    int userid;

    ParticipationServices ps = new ParticipationServices();
    Participation p = new Participation();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
            
        afficherEvent();
        txtnbrparticipant.setVisible(false);
        txtparticipant.setVisible(false);
       // userid = Session.LoggedUser.getUser_id();
       userid = 1;

    }

    public void afficherEvent() {
        EvName.setCellValueFactory(new PropertyValueFactory<>("EVENT_NAME"));
        EvDate.setCellValueFactory(new PropertyValueFactory<>("EVENT_DATE"));
        EvFin.setCellValueFactory(new PropertyValueFactory<>("EVENT_END"));
        Evgouv.setCellValueFactory(new PropertyValueFactory<>("EVENT_GOUV"));
        EvEndroit.setCellValueFactory(new PropertyValueFactory<>("EVENT_PLACE"));
        Evdesc.setCellValueFactory(new PropertyValueFactory<>("EVENT_DESC"));
        nbrparticip.setCellValueFactory(new PropertyValueFactory<>("NBR_PARTICIP"));
        Table.setItems(null);
        Table.setItems(cr.showEvent());
    }
    

    @FXML
    private void AddEvent(ActionEvent event) throws SQLException {
        String erreurs="";
        if(EventGouv.getText().trim().isEmpty()){
            erreurs+="gouv name vide\n";
        }
        
        if(EventName.getText().trim().isEmpty()){
            erreurs+=" name vide\n";
        
        }
        if(EventPlace.getText().trim().isEmpty()){
            erreurs+=" place vide\n";
        
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout event");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else {
        String EVENT_NAME = EventName.getText();
        Date EVENT_DATE = java.sql.Date.valueOf(EventDate.getValue());
        Date EVENT_END = java.sql.Date.valueOf(EventEnd.getValue());
        String EVENT_GOUV = EventGouv.getText();
        String EVENT_PLACE = EventPlace.getText();
        String EVENT_DESC = EventDesc.getText();
        int NBR_PARTICIP = 0;
        Evenement e = new Evenement(EVENT_NAME, EVENT_DATE, EVENT_END, EVENT_GOUV, EVENT_PLACE, EVENT_DESC, NBR_PARTICIP);
        cr.addEvent(e);
        afficherEvent();
    }}
 @FXML
    private void deleteEvent(ActionEvent event) throws SQLException {
        if (!Table.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete event ?");
            alert.setHeaderText("Are you sure you want to delete this Sponsore : " + Table.getSelectionModel().getSelectedItem().getEVENT_NAME() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                 CrudEvenement e = new CrudEvenement();
                e.DeleteEvent(Table.getSelectionModel().getSelectedItem().getEVENT_ID());
               afficherEvent();
                EmptyFields(event);

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText("Would you select a event ! !");
            Optional<ButtonType> result = alert.showAndWait();
        }
        EmptyFields(event);
    }
    
     @FXML
    private void UpdateEvent(ActionEvent event) {

        if (!Table.getSelectionModel().isEmpty()) {
               CrudEvenement cr = new CrudEvenement();
            Evenement e = new Evenement();
           // ss.setId(Integer.valueOf(ID_Sponsore_New));

            e.setEVENT_ID(Integer.valueOf(ID_Event_New));
            e.setEVENT_NAME(EventName.getText());
            e.setEVENT_GOUV(EventGouv.getText());
            e.setEVENT_PLACE(EventPlace.getText());
            e.setEVENT_DESC(EventDesc.getText());
e.setNBR_PARTICIP(nbrpart);

            cr.updateEvent(e);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Stadium ");
            alert.setHeaderText("Updating d stadium :"
                    + Table.getSelectionModel().getSelectedItem().getEVENT_ID() + " done");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur de selection");
            alert1.setHeaderText("Would you select a stadium !  ");
            Optional<ButtonType> result = alert1.showAndWait();
        }
        afficherEvent();
        EmptyFields(event);
    }
            
    @FXML
    private void EmptyFields(ActionEvent event) {
        EventName.clear();
        EventDesc.clear();
        EventPlace.clear();
        EventDate.getEditor().clear();
        EventEnd.getEditor().clear();
    }

    
    
             @FXML
    private void goToPieChart(MouseEvent event) {
          loadPage(event,"/View/FXMLPieChart.fxml");
    }
     @FXML
    private void pdf(MouseEvent event) {
        
     //Printer printer = Printer.getDefaultPrinter();
     
      Printer printer = Printer.getDefaultPrinter();
      
      PrinterJob  printerJob = PrinterJob.createPrinterJob();
           printerJob.getJobSettings().setJobName("Print WebEngine");

            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

     
              Stage dialogStage = new Stage(StageStyle.DECORATED);

              PrinterJob job = PrinterJob.createPrinterJob(printer);
              if (job != null) {
                 boolean showDialog =job.showPrintDialog(pdf.getScene().getWindow());
                 
                //  boolean showDialog = job.showPageSetupDialog(dialogStage);
                  if (showDialog) {
                      

                      Table.setScaleX(0.8);
                Table.setScaleY(0.9);
                Table.setTranslateX(-110);
                Table.setTranslateY(-20);
                      boolean success = job.printPage(Table);
                      if (success) {
                          job.endJob();
                      }
                       Table.setScaleX(1.0);
                Table.setScaleY(1.0);
                Table.setTranslateX(0);
                Table.setTranslateY(0);

}}}

    
    
        
    
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
    private void map(ActionEvent event) {
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/MapView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    private void GoBack(ActionEvent event) {
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/FXMLMenu.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void sponsor(ActionEvent event) {
                    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/FXMLShowSponsores.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ShowMap(ActionEvent event) {
       if (!Table.getSelectionModel().getSelectedItems().isEmpty()) {

            endro = Table.getSelectionModel().getSelectedItem().getEVENT_PLACE();
            gouv = Table.getSelectionModel().getSelectedItem().getEVENT_GOUV();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLEventMap.fxml"));
            try {
                Parent root = loader.load();
                FXMLEventMapController dc = loader.getController();
                showmap.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(FXMLEventMapController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Would you select an event !");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }





    public void Participate(ActionEvent event) throws SQLException {
        xe = Table.getSelectionModel().getSelectedItem().getEVENT_ID();
        int h = ps.CheckIDexistance(userid, xe);
        if (h != 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("You already participate in this event !");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (Table.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Would you select an event !");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            //int newuserid = Session.LoggedUser.getUser_id();
            int newuserid = 2;
            nbrpart = Table.getSelectionModel().getSelectedItem().getNBR_PARTICIP();
            nbrpart = nbrpart + 1;
            txtparticipant.setText(String.valueOf(nbrpart));
            txtnbrparticipant.setVisible(true);
            txtparticipant.setVisible(true);
            String nameev = Table.getSelectionModel().getSelectedItem().getEVENT_NAME();
            int y = (Integer.parseInt(txtparticipant.getText()));
            cr.updateparticip(y, nameev);
            afficherEvent();
            ps.addParticipation(userid, xe);
            System.out.println(userid);
            System.out.println(xe);

        }

    }
    
        private void DeleteEvent() throws SQLException, ParseException {
       /*Evenement e = new Evenement();
       DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date today = new Date();

        try {
            Date todayWithZeroTime = (Date) formatter.parse(formatter.format(today));
          if (e.getEVENT_END().after(todayWithZeroTime)) 
          {
            int x = e.getEVENT_ID();
                cr.DeleteEvent(x);

        }
        } catch (ParseException ex) {
            Logger.getLogger(GestEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

  
    }
        void showEventDetails(Evenement e) {
       ID_Event_New = String.valueOf(e.getEVENT_ID());  
        EventName.setText(e.getEVENT_NAME());
        EventGouv.setText(e.getEVENT_GOUV());
               EventPlace.setText(e.getEVENT_PLACE());
               EventDesc.setText(e.getEVENT_DESC());


    }
    
}