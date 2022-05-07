package khater.Service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import khater.Entity.Reclamation;
import khater.Entity.Response;
import khater.Entity.User;
import khater.Iservice.IService;
import khater.Utils.Myconnexion;

public class ServiceResponse implements IService<Response>{
        Connection cnx;
    public ServiceResponse (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add Response***************************/
    @Override
    public void ajouter(Response response) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `response`(`user`, `reclamation`, `Title_Response`, `body_Response`, `date_Response`) VALUES ('"+response.getUser()+"','"+response.getReclamation()+"','"+response.getTitle_Response()+"','"+response.getBody_Response()+"','"+response.getDate_Response()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }

    @Override
    public List<Response> afficher() throws SQLException {
        List<Response> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM response";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Response response = new Response();   
        response.setId_Response(rs.getInt("id_Response"));
        response.setUser(rs.getInt("user"));
        response.setReclamation(rs.getInt("reclamation"));
        response.setTitle_Response(rs.getString("Title_Response"));
        response.setBody_Response(rs.getString("body_Response"));
        response.setDate_Response(rs.getTimestamp(6).toLocalDateTime());
        LR.add(response);
        }
        return LR;
    }

    
 /******************************* Delete Response *********************************************/
    @Override
    public void supprimer(int id_Response) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from response where id_Response="+id_Response;
        stm.executeUpdate(query);
    }

    

    
/******************************* FindById Response *********************************************/
    public Response SearchById(long id_Response) throws SQLException{
        Statement stm = cnx.createStatement();
        Response response = new Response();
        String query = "select * from response where id_Response="+id_Response;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        response.setId_Response(rs.getInt("id_Response"));
        response.setUser(rs.getInt("user"));
        response.setReclamation(rs.getInt("reclamation"));
        response.setTitle_Response(rs.getString("Title_Response"));
        response.setBody_Response(rs.getString("body_Response"));
        response.setDate_Response(rs.getTimestamp(6).toLocalDateTime());}
        return response;
        
        }     
    
/******************************* Modifier Reclamation *********************************************/
    @Override
    public void modifier(int id_ResponseModifier, Response response) throws SQLException {
        Statement stm = cnx.createStatement();
        Response r =SearchById(id_ResponseModifier);
        String query = "UPDATE `response` SET `user`='"+response.getUser()+"',`reclamation`='"+response.getReclamation()+"',`Title_Response`='"+response.getTitle_Response()+"',`body_Response`='"+response.getBody_Response()+"',`date_Response`='"+LocalDateTime.now()+"' where id_Response="+r.getId_Response();
        stm.executeUpdate(query);
    }
}


