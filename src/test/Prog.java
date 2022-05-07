/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.sql.Date;


import java.sql.SQLException;

import Service.CrudEvenement;
//import Service.ServicePersonne;
//import Service.ServiceSponsore;
//import entities.sponsore;
import Entities.Evenement;
/**
 *
 * @author aziz
 */
public class Prog {
    
    public static void main(String[] args) throws SQLException {
        
        // test ajout 
      CrudEvenement srv1 = new CrudEvenement();
         Date d1 = new Date(2012, 12, 03);
     Date d2 = new Date(2012, 12, 12);
       // Evenement e1 = new Evenement("llllllll", d1, d2, "ppp");
         //srv1.AjouterEvenement(e1);
        System.out.println("insertion");

        
        //test modif
        // srv1.updateEvent(e1, 15);
          System.out.println("update");
          
          //supp
         //srv1.supprimerEvent(15);
          
       // ServiceSponsore srv2 = new ServiceSponsore();
       // srv2.supprimerSponsore(3);
        
        //sponsore s1 = new sponsore("orange","done des app");
      //  sponsore s2 = new sponsore("delice","done des ordinateurs");
      //  sponsore s3 = new sponsore("ecosys","done des tellllllllllllltghtthgt");
      //  srv2.AjouterSponsore(s1);
      //  srv2.AjouterSponsore(s2);
        
       // System.out.println("insertion");
     

      //  System.out.println("affichage");
    

 //System.out.println("insertion");
      
     // 
        
      // System.out.println("suppression");
      // srv2.updateSponsore(s3,4);
        
      //  srv.supprimerpersonne(2);
    
    
    }
    
}
