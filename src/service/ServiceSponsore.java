/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.sponsore;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author aziz
 */
public class ServiceSponsore {
    
     public Connection con=DataSource.getInstance().getConnection();
    public Statement ste;
    public ServiceSponsore()
    {
     
            try {
				ste=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
    }
  
    

	  public void AjouterSponsore (sponsore c ) throws SQLException
	    {
	        String req=" INSERT INTO `sponsore`(`Nomsponsore`, `NomDescription`) "
	                + "VALUES (?,?)";
	           PreparedStatement pre = DataSource.getInstance().getConnection().prepareStatement(req);
	      //  pre.setInt(1,c.getId());
	        pre.setString(1,c.getNomsponsore());
	        pre.setString(2,c.getNomDescription()); 
	
	
	        pre.executeUpdate();
	        
	        System.out.println("Sponsore ajoute");
	    }
	 public void updateSponsore (sponsore s){
        String req = "UPDATE sponsore SET `Nomsponsore`=?,`NomDescription`=? WHERE id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DataSource.getInstance().getConnection().prepareStatement(req);
  
            preparedStatement.setString(1, s.getNomsponsore());
            preparedStatement.setString(2, s.getNomDescription());
            
            preparedStatement.setInt(3, s.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error"+ex.getMessage());
        }
    }
    
    public  void supprimerSponsore( int id) throws SQLException
        {
                 String req = "DELETE FROM `sponsore` WHERE id="+id;
                 Statement pre=con.createStatement();
                 pre.executeUpdate(req);
                  System.out.println("sponsore Supprimée");
        }
//    
    
    
   public ObservableList<sponsore> affichesponsore() {
    
            ObservableList<sponsore> myList = FXCollections.observableArrayList();
            String req = "SELECT * FROM sponsore";
            try{
                PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(req);
                ResultSet rs = pst.executeQuery(req);

                while(rs.next()){
                    sponsore e = new sponsore();
                    e.setId(rs.getInt(1));
                    e.setNomsponsore(rs.getString(2));
                    e.setNomDescription(rs.getString(3));
                   
                  
                    myList.add(e);
                }
            }catch (SQLException ex){
                System.out.println("Error"+ex.getMessage());
            }
            return myList;
            
        }
    
    
}
