package khater.Service;


import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import khater.Entity.Reclamation;
import khater.Entity.User;
import khater.Iservice.IService;
import khater.Utils.Myconnexion;

public class ServiceReclamation implements IService<Reclamation>{
        Connection cnx;
    public ServiceReclamation (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add Reclamation***************************/
    @Override
    public void ajouter(Reclamation reclamation) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `reclamation`(`user`, `titre_reclamation`, `object_reclamation`, `description_reclamation`, `date_Reclamation`) VALUES ('"+reclamation.getUser()+"','"+reclamation.getTitre_reclamation()+"','"+reclamation.getObject_reclamation()+"','"+reclamation.getDescription_reclamation()+"','"+reclamation.getDate_Reclamation()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }
    
/*****************************getAll Reclamation***************************/
    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM reclamation";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Reclamation reclamation = new Reclamation();   
        reclamation.setId_reclamation(rs.getInt("id_reclamation"));
        reclamation.setUser(rs.getInt("user"));
        reclamation.setTitre_reclamation(rs.getString("titre_reclamation"));
        reclamation.setObject_reclamation(rs.getString("object_reclamation"));
        reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
        reclamation.setDate_Reclamation(rs.getTimestamp(6).toLocalDateTime());
        LR.add(reclamation);
        }
        
        return LR;
    }
    
 /******************************* Delete Reclamation *********************************************/
    @Override
    public void supprimer(int id_reclamation) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from reclamation where id_reclamation =" + id_reclamation;
        stm.executeUpdate(query);
        }
    
/******************************* FindById Reclamation *********************************************/
    public Reclamation SearchById(long id_reclamation) throws SQLException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where id_reclamation="+id_reclamation;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId_reclamation(rs.getInt("id_reclamation"));
        reclamation.setUser(rs.getInt("user"));
        reclamation.setTitre_reclamation(rs.getString("titre_reclamation"));
        reclamation.setObject_reclamation(rs.getString("object_reclamation"));
        reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
        reclamation.setDate_Reclamation(rs.getTimestamp(6).toLocalDateTime());}
        return reclamation;
        }
    
        
    
/******************************* Modifier Reclamation *********************************************/
    @Override
    public void modifier(int id_reclamationModifier, Reclamation reclamation) throws SQLException {
        Statement stm = cnx.createStatement();
        Reclamation r =SearchById(id_reclamationModifier);
        String query = "UPDATE `reclamation` SET `user`='"+reclamation.getUser()+"',`titre_reclamation`='"+reclamation.getTitre_reclamation()+"',`object_reclamation`='"+reclamation.getObject_reclamation()+"',`description_reclamation`='"+reclamation.getDescription_reclamation()+"',`date_Reclamation`='"+LocalDateTime.now()+"' where id_reclamation="+r.getId_reclamation();
        stm.executeUpdate(query);
        } 

/******************************* SearchByTitle Reclamation *********************************************/
    public Reclamation SearchByTitle(String titre_reclamation) throws SQLException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where titre_reclamation='"+titre_reclamation+"'";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId_reclamation(rs.getInt("id_reclamation"));
        reclamation.setUser(rs.getInt("user"));
        reclamation.setTitre_reclamation(rs.getString("titre_reclamation"));
        reclamation.setObject_reclamation(rs.getString("object_reclamation"));
        reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
        reclamation.setDate_Reclamation(rs.getTimestamp(6).toLocalDateTime());}
        return reclamation;
        }
    
/******************************* SearchByTitle Reclamation *********************************************/
    public Reclamation SearchByDate(LocalDateTime date_Reclamation) throws SQLException, ParseException{
        Statement stm = cnx.createStatement();
        Reclamation reclamation = new Reclamation();
        String query = "select * from reclamation where date_Reclamation='"+date_Reclamation+"'";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        reclamation.setId_reclamation(rs.getInt("id_reclamation"));
        reclamation.setUser(rs.getInt("user"));
        reclamation.setTitre_reclamation(rs.getString("titre_reclamation"));
        reclamation.setObject_reclamation(rs.getString("object_reclamation"));
        reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
        reclamation.setDate_Reclamation(rs.getTimestamp(6).toLocalDateTime());}
        return reclamation;
        }   

/******************************* Nombre Reclamation *********************************************/     
    public int nbReclamation(){
        int nbReclamation = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id_reclamation) FROM reclamation")
        .executeQuery();
        if (set.next()) {
        nbReclamation = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);}
        return nbReclamation;
        }
 /******************************* Statistique Produit *********************************************/      
     public HashMap<String, Double> StatistiqueParNom() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT titre_reclamation, COUNT(*) as nb FROM reclamation GROUP BY titre_reclamation;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("titre_reclamation");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
     
             public List<String> getByTitle()  {
                 List<String> Lc = new ArrayList<>();
            try {

                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM reclamation";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                    Lc.add(rs.getString("titre_reclamation"));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ServiceResponse.class.getName()).log(Level.SEVERE, null, ex);
            }
             return Lc;
    }

    public Reclamation findTritre (String titre) {
               Reclamation reclamation =new Reclamation();
            try {               
                Statement stm = cnx.createStatement();

                String query = "select * from reclamation where titre_reclamation='"+titre + "'";
                ResultSet rs= stm.executeQuery(query);
                while (rs.next()){
                        reclamation.setId_reclamation(rs.getInt("id_reclamation"));
                        reclamation.setUser(rs.getInt("user"));
                        reclamation.setTitre_reclamation(rs.getString("titre_reclamation"));
                        reclamation.setObject_reclamation(rs.getString("object_reclamation"));
                        reclamation.setDescription_reclamation(rs.getString("description_reclamation"));
                        reclamation.setDate_Reclamation(rs.getTimestamp(6).toLocalDateTime());}

            } catch (SQLException ex) {
                Logger.getLogger(ServiceResponse.class.getName()).log(Level.SEVERE, null, ex);
            }
             return reclamation;
    }
}
