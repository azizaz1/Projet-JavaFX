

package khater;


import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import khater.Entity.Reclamation;
import khater.Entity.Response;
import khater.Entity.User;
import khater.Service.ServiceReclamation;
import khater.Service.ServiceResponse;




public class Main {


  
    public static void main(String[] args) throws SQLException, ParseException {

/******************************************************************************** Reclamation *********************************************************************/
       
        ServiceReclamation SReclamation = new ServiceReclamation();
        Reclamation reclamation =new Reclamation(1,"aa","aa","aa",LocalDateTime.now());
        
/******************************* Ajouter Reclamation *********************************************/
//            SReclamation.ajouter(reclamation);
        
/******************************* Modifier Reclamation *********************************************/
//            SReclamation.modifier(4, reclamation);
        
/******************************* Supprimer Reclamation *********************************************/
//              SReclamation.supprimer(3);
             
/******************************* Afficher Reclamation*********************************************/
//            System.out.println(SReclamation.afficher());

/******************************* SearchById Reclamation *********************************************/
//            System.out.println(SReclamation.SearchById(4));

/******************************* SearchById Reclamation *********************************************/
//            System.out.println(SReclamation.SearchByTitle("aa"));

/******************************* SearchByDate Reclamation *********************************************/
//            System.out.println(SReclamation.SearchByDate(LocalDateTime.of(2022, Month.APRIL, 10, 0, 0)));

/******************************* Number Reclamation *********************************************/
//            System.out.println(SReclamation.nbReclamation());
SReclamation.findTritre("aa");
/******************************************************************************** Response *********************************************************************/
        
        ServiceResponse SResponse = new ServiceResponse();
        Response response = new Response(1, 4, "cc", "cc", LocalDateTime.now());


/******************************* Ajouter Response *********************************************/
//            SResponse.ajouter(response);

/******************************* Modifier Response *********************************************/
//            SResponse.modifier(2, response);

/******************************* Supprimer Response *********************************************/
//              SResponse.supprimer(1);
             
/******************************* Afficher Response *********************************************/
//            System.out.println(SResponse.afficher());

/******************************* SearchById Response *********************************************/
//            System.out.println(SResponse.SearchById(2));



    }

}
  

