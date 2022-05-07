/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azyz.Service;

import azyz.Entity.Image;
import azyz.IService.IService;
import azyz.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceImage implements IService<Image>{
        Connection cnx;
        public ServiceImage (){
        cnx = Myconnexion.getInstance().getCnx();
    }

/*****************************Add Image***************************/        
    @Override
    public void ajouter(Image image) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `image`(`nom`) VALUES ('"+image.getNom()+"')";
        st.executeUpdate(query);}            
        catch (SQLException ex) {
        System.out.println(ex.getMessage());} 
    }

/*****************************Afficher Image***************************/    
    @Override
    public List<Image> afficher() throws SQLException {
        List<Image> listImage = new ArrayList<>();                            
        Statement stm = cnx.createStatement();       
        String query = "SELECT * FROM image";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Image image = new Image();
        image.setId_image(rs.getInt("id_image"));
        image.setNom(rs.getString("nom"));
        listImage.add(image);}
        return listImage;
    }

/*****************************Delete Image***************************/    
    @Override
    public void supprimer(int id_image	) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from image where id_image =" + id_image	;
        stm.executeUpdate(query);
    }


/*****************************SearchById Image***************************/    
    public Image SearchById(int id_image) throws SQLException{
        Statement stm = cnx.createStatement();
        Image image =new Image();
        String query = "select * from image where id_image="+id_image;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        image.setId_image(rs.getInt("id_image"));
        image.setNom(rs.getString("nom"));}
        return image;
    }
    
/*****************************modifier Image***************************/        
    @Override
    public void modifier(int id_image_modifier, Image image) throws SQLException {
        Statement stm = cnx.createStatement();
        Image im =SearchById(id_image_modifier);
        String query = "UPDATE `image` SET `nom`='"+image.getNom()+"' where id_image="+im.getId_image();
        stm.executeUpdate(query);
    }

      
}
