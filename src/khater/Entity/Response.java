/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khater.Entity;

import java.time.LocalDateTime;

public class Response {
    private int id_Response;
    private int user;
    private int reclamation;
    private String Title_Response;
    private String body_Response;
    private LocalDateTime date_Response;

    public int getId_Response() {
        return id_Response;
    }

    public void setId_Response(int id_Response) {
        this.id_Response = id_Response;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getReclamation() {
        return reclamation;
    }

    public void setReclamation(int reclamation) {
        this.reclamation = reclamation;
    }

    public String getTitle_Response() {
        return Title_Response;
    }

    public void setTitle_Response(String Title_Response) {
        this.Title_Response = Title_Response;
    }

    public String getBody_Response() {
        return body_Response;
    }

    public void setBody_Response(String body_Response) {
        this.body_Response = body_Response;
    }

    public LocalDateTime getDate_Response() {
        return date_Response;
    }

    public void setDate_Response(LocalDateTime date_Response) {
        this.date_Response = date_Response;
    }

    public Response() {
    }

    public Response(int id_Response, int user, int reclamation, String Title_Response, String body_Response, LocalDateTime date_Response) {
        this.id_Response = id_Response;
        this.user = user;
        this.reclamation = reclamation;
        this.Title_Response = Title_Response;
        this.body_Response = body_Response;
        this.date_Response = date_Response;
    }

    public Response(int user, int reclamation, String Title_Response, String body_Response, LocalDateTime date_Response) {
        this.user = user;
        this.reclamation = reclamation;
        this.Title_Response = Title_Response;
        this.body_Response = body_Response;
        this.date_Response = date_Response;
    }

   
    @Override
    public String toString() {
        return "Response{" + "id_Response=" + id_Response + ", user=" + user + ", reclamation=" + reclamation + ", Title_Response=" + Title_Response + ", body_Response=" + body_Response + ", date_Response=" + date_Response + '}';
    }
    
    
}
