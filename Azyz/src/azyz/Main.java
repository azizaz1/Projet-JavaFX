

package azyz;

import azyz.Entity.Category;
import azyz.Entity.Image;
import azyz.Entity.Produit;
import azyz.Service.ServiceCategory;
import azyz.Service.ServiceImage;
import azyz.Service.ServiceProduit;
import java.sql.SQLException;



public class Main {


  
    public static void main(String[] args) throws SQLException {

/******************************************************************************** Image *********************************************************************/
        ServiceImage Simage = new ServiceImage();
        Image image =new Image(2,"bb");
        
/******************************* Ajouter Image *********************************************/
//            Simage.ajouter(image);
        
/******************************* Modifier Image *********************************************/
//            Simage.modifier(1, image);
        
/******************************* Supprimer Image *********************************************/
//              Simage.supprimer(1);
             
/******************************* Afficher Image *********************************************/
//            System.out.println(Simage.afficher());



/******************************************************************************** Category *********************************************************************/
        ServiceCategory Scategory = new ServiceCategory();
        Category category =new Category(1,"bb","aa");


/******************************* Ajouter Category *********************************************/
//            Scategory.ajouter(category);

/******************************* Modifier Category *********************************************/
//            Scategory.modifier(8, category);

/******************************* Supprimer Category *********************************************/
//              Scategory.supprimer(1);
             
/******************************* Afficher Category *********************************************/
//            System.out.println(Scategory.afficher());


/******************************************************************************* Produit *********************************************************************/
        ServiceProduit Sproduit= new ServiceProduit();
        Produit produit = new Produit(1, "aa", "a", "mm", 10, 10);


/******************************* Ajouter Produit *********************************************/
//            Sproduit.ajouter(produit);

///******************************* Modifier Produit *********************************************/
//            Sproduit.modifier(21,produit);

/******************************* Supprimer Produit *********************************************/
//              Sproduit.supprimer(22);
             
/******************************* Afficher Produit *********************************************/
//            System.out.println(Sproduit.afficher());

/******************************* SearchById Produit *********************************************/
//            System.out.println(Sproduit.SearchById(21));

/******************************* SearchByNom Produit *********************************************/
//            System.out.println(Sproduit.SearchByNom("a"));

/******************************* Number Produit *********************************************/
//            System.out.println(Sproduit.nbProduit());

/******************************* SearchByPrice Produit *********************************************/
//            System.out.println(Sproduit.SearchByPrice(10));
    }

}
  

