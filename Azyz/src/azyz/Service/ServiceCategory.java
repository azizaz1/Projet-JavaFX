package azyz.Service;

import azyz.Entity.Category;
import azyz.IService.IService;
import azyz.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceCategory implements IService<Category>{
        Connection cnx;
    public ServiceCategory (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add Category***************************/
    @Override
    public void ajouter(Category category) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `category`(`type`, `nom`,`image`) VALUES ('"+category.getType()+"','"+category.getNom()+"','"+category.getImage()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }
    
/*****************************getAll Category***************************/
    @Override
    public List<Category> afficher() throws SQLException {
        List<Category> Lc = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM category";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Category category = new Category();   
        category.setId_category(rs.getInt("id_category"));
        category.setType(rs.getString("type"));
        category.setNom(rs.getString("nom"));
        category.setImage(rs.getString("image"));
        Lc.add(category);
        }
        
        return Lc;
    }
    
 /******************************* Delete Category *********************************************/
    @Override
    public void supprimer(int id_category) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from category where id_category ="+id_category;
        stm.executeUpdate(query);
    }
    
/******************************* FindById Category *********************************************/
    public Category SearchById(long id_category) throws SQLException{
        Statement stm = cnx.createStatement();
        Category category =new Category();
        String query = "select * from category where id_category="+id_category;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        category.setId_category(rs.getInt("id_category"));
        category.setType(rs.getString("type"));
        category.setNom(rs.getString("nom"));
        category.setImage(rs.getString("image"));
                        }
        return category;
    }
        
/******************************* Modifier Category *********************************************/
    @Override
    public void modifier(int id_categoryModifier, Category category) throws SQLException {
        Statement stm = cnx.createStatement();
        Category c =SearchById(id_categoryModifier);
        String query = "UPDATE `category` SET `type`='"+category.getType()+"',`nom`='"+category.getNom()+"',`image`='"+category.getImage()+"' where id_category="+c.getId_category();
        stm.executeUpdate(query);
    }
    
}
