/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package azyz.Entity;


public class Produit {
    
      private int id_produit;
      private int category ;
      private String image;
      private String nom; 
      private String couleur;
      private float prix;
      private int quantite;

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit(int id_produit, int category, String image, String nom, String couleur, float prix, int quantite) {
        this.id_produit = id_produit;
        this.category = category;
        this.image = image;
        this.nom = nom;
        this.couleur = couleur;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(int category, String image, String nom, String couleur, float prix, int quantite) {
        this.category = category;
        this.image = image;
        this.nom = nom;
        this.couleur = couleur;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", category=" + category + ", image=" + image + ", nom=" + nom + ", couleur=" + couleur + ", prix=" + prix + ", quantite=" + quantite + '}';
    }

      
}
