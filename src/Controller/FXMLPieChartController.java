/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import utils.DataSource;

/**
 *
 * @author aziz
 */
public class FXMLPieChartController implements Initializable {

    @FXML
    private PieChart myPieChart;
    private ObservableList<PieChart.Data> data;

    /**
     * Initializes the controller class.
     */
    
    private void buildPieChartData() {
        try {
             Connection c ;
            DataSource ds = DataSource.getInstance();
          data = FXCollections.observableArrayList();
           c = ds.getConnection();
           String SQL = "SELECT NBR_PARTICIP,EVENT_NAME FROM evenement";
              ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
               data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
                
            }
            myPieChart.setTitle("Nombre De Participants Par Evenement");
            myPieChart.setData(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         buildPieChartData();
    }    

    @FXML
    private void ViewChart(ActionEvent event) {
          buildPieChartData();
          
    }
    
}
