/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azyz.Entity;


public class Category {

    private int id_category;
    private String Type;
    private String nom;

    public Category(int id_category, String Type, String nom, String image) {
        this.id_category = id_category;
        this.Type = Type;
        this.nom = nom;
        this.image = image;
    }

    public Category(String Type, String nom, String image) {
        this.Type = Type;
        this.nom = nom;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private String image;

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Category{" + "id_category=" + id_category + ", Type=" + Type + ", nom=" + nom + ", image=" + image + '}';
    }



    public Category(int id_category, String Type, String nom) {
        this.id_category = id_category;
        this.Type = Type;
        this.nom = nom;
    }

    public Category() {
    }

    public Category(String Type, String nom) {
        this.Type = Type;
        this.nom = nom;
    }

    
}