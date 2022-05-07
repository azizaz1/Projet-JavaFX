/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khater.Entity;

import java.time.LocalDateTime;

public class Reclamation {

    public static int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int id_reclamation;
    private int user;
    private String titre_reclamation;
    private String object_reclamation;
    private String description_reclamation;
    private LocalDateTime date_Reclamation;

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getTitre_reclamation() {
        return titre_reclamation;
    }

    public void setTitre_reclamation(String titre_reclamation) {
        this.titre_reclamation = titre_reclamation;
    }

    public String getObject_reclamation() {
        return object_reclamation;
    }

    public void setObject_reclamation(String object_reclamation) {
        this.object_reclamation = object_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public LocalDateTime getDate_Reclamation() {
        return date_Reclamation;
    }

    public void setDate_Reclamation(LocalDateTime date_Reclamation) {
        this.date_Reclamation = date_Reclamation;
    }

    public Reclamation() {
    }

    public Reclamation(int id_reclamation, int user, String titre_reclamation, String object_reclamation, String description_reclamation, LocalDateTime date_Reclamation) {
        this.id_reclamation = id_reclamation;
        this.user = user;
        this.titre_reclamation = titre_reclamation;
        this.object_reclamation = object_reclamation;
        this.description_reclamation = description_reclamation;
        this.date_Reclamation = date_Reclamation;
    }

    public Reclamation(int user, String titre_reclamation, String object_reclamation, String description_reclamation, LocalDateTime date_Reclamation) {
        this.user = user;
        this.titre_reclamation = titre_reclamation;
        this.object_reclamation = object_reclamation;
        this.description_reclamation = description_reclamation;
        this.date_Reclamation = date_Reclamation;
    }

    public Reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    
    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", user=" + user + ", titre_reclamation=" + titre_reclamation + ", object_reclamation=" + object_reclamation + ", description_reclamation=" + description_reclamation + ", date_Reclamation=" + date_Reclamation + '}';
    }

    
}
