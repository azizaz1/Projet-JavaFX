/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aziz
 */
public class sponsore {
    
    
     private int Id;
	private String Nomsponsore;
	private String NomDescription;

    public sponsore() {
    }

    public sponsore(String Nomsponsore, String NomDescription) {
        this.Nomsponsore = Nomsponsore;
        this.NomDescription = NomDescription;
    }

    public sponsore(int Id, String Nomsponsore, String NomDescription) {
        this.Id = Id;
        this.Nomsponsore = Nomsponsore;
        this.NomDescription = NomDescription;
    }

    public int getId() {
        return Id;
    }

    public String getNomsponsore() {
        return Nomsponsore;
    }

    public String getNomDescription() {
        return NomDescription;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNomsponsore(String Nomsponsore) {
        this.Nomsponsore = Nomsponsore;
    }

    public void setNomDescription(String NomDescription) {
        this.NomDescription = NomDescription;
    }
    
    
}
