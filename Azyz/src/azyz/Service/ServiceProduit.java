/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azyz.Service;

import azyz.Entity.Category;
import azyz.Entity.Image;
import azyz.Entity.Produit;
import azyz.IService.IService;
import azyz.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ServiceProduit implements IService<Produit>{
    
        Connection cnx;
        Statement st;
    
    public ServiceProduit (){
        cnx = Myconnexion.getInstance().getCnx();}
    
        /******************************** Add Produit *********************************/
    @Override
    public void ajouter(Produit produit) {
        try {
        String query ="INSERT INTO `produit`(`category_Id`, `Image`, `nom`,`couleur`, `prix`, `quantite`) VALUES ('"+produit.getCategory()+"','"+produit.getImage()+"','"+produit.getNom()+"','"+produit.getCouleur()+"','"+produit.getPrix()+"','"+produit.getQuantite()+"')";             
        st = cnx.createStatement();      
        st.executeUpdate(query);
        System.out.println("produit ajouté avec succes");}        
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}   
        }    
/*****************************getAll Produit***************************/
    @Override
    public List<Produit> afficher() throws SQLException {
        List<Produit> Lproduit = new ArrayList<Produit>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM produit";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
        Produit produit = new Produit();
        produit.setId_produit(rs.getInt("id_produit"));
        produit.setCategory(rs.getInt("category_Id"));
        produit.setImage(rs.getString("Image"));
        produit.setNom(rs.getString("nom"));
        produit.setCouleur(rs.getString("couleur"));
        produit.setPrix(rs.getFloat("prix"));
        produit.setQuantite(rs.getInt("quantite"));
        Lproduit.add(produit);}        
        return Lproduit;
        }
    
 /******************************* Delete Produit *********************************************/
    @Override
    public void supprimer(int id_produit) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from produit where id_produit =" + id_produit;
        stm.executeUpdate(query);
        }
    
/******************************* FindById Produit *********************************************/
    public Produit SearchById(long id_produit) throws SQLException{
        Statement stm = cnx.createStatement();
        Produit produit = new Produit();
        String query = "select * from produit where id_produit="+id_produit;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        produit.setId_produit(rs.getInt("id_produit"));
        produit.setCategory(rs.getInt("category_Id"));
        produit.setImage(rs.getString("Image"));
        produit.setNom(rs.getString("nom"));
        produit.setCouleur(rs.getString("couleur"));
        produit.setPrix(rs.getFloat("prix"));
        produit.setQuantite(rs.getInt("quantite"));}
        return produit;
        }
        
/******************************* Modifier Produit *********************************************/
    @Override
    public void modifier(int id_produitModifier, Produit produit) throws SQLException {
        Statement stm = cnx.createStatement();
        Produit p =SearchById(id_produitModifier);
        String query = "UPDATE `produit` SET `category_Id`='"+produit.getCategory()+"',`Image`='"+produit.getImage()+"',`nom`='"+produit.getNom()+"',`couleur`='"+produit.getCouleur()+"',`prix`='"+produit.getPrix()+"',`quantite`='"+produit.getQuantite()+"' where id_produit="+p.getId_produit();
        stm.executeUpdate(query);
        } 
    
/******************************* SearchByNom Produit *********************************************/
    public Produit SearchByNom(String nom) throws SQLException{
        Statement stm = cnx.createStatement();
        Produit produit = new Produit();
        String query = "select * from produit where nom='"+nom+"'";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        produit.setId_produit(rs.getInt("id_produit"));
        produit.setCategory(rs.getInt("category_Id"));
        produit.setImage(rs.getString("Image"));
        produit.setNom(rs.getString("nom"));
        produit.setCouleur(rs.getString("couleur"));
        produit.setPrix(rs.getFloat("prix"));
        produit.setQuantite(rs.getInt("quantite"));}
        return produit;
        }

/******************************* SearchByPrice Produit *********************************************/    
    public Produit SearchByPrice(double prix) throws SQLException{
        Statement stm = cnx.createStatement();
        Produit produit = new Produit();
        String query = "select * from produit where prix="+prix;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        produit.setId_produit(rs.getInt("id_produit"));
        produit.setCategory(rs.getInt("category_Id"));
        produit.setImage(rs.getString("Image"));
        produit.setNom(rs.getString("nom"));
        produit.setCouleur(rs.getString("couleur"));
        produit.setPrix(rs.getFloat("prix"));
        produit.setQuantite(rs.getInt("quantite"));}
        return produit;
        }

/******************************* Nombre Produit *********************************************/     
    public int nbProduit(){
        int nbproduit = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id_produit) FROM produit")
        .executeQuery();
        if (set.next()) {
        nbproduit = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);}
        return nbproduit;
        }  

/******************************* Statistique Produit *********************************************/      
            public HashMap<String, Double> StatistiqueParNom() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT nom, COUNT(*) as nb FROM produit GROUP BY nom;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("nom");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
}
