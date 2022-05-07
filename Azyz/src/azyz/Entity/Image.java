/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azyz.Entity;

/**
 *
 * @author mahdi
 */
public class Image {
    
    private int id_image;
    private String nom;

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Image{" + "id_image=" + id_image + ", nom=" + nom + '}';
    }

    public Image(int id_image, String nom) {
        this.id_image = id_image;
        this.nom = nom;
    }

    public Image(String nom) {
        this.nom = nom;
    }

    public Image() {
    }
    
    
}
