/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khater.Entity;

import java.time.LocalDateTime;


    public class User {
 
        private int id ;    
        private String nom;    
        private String prenom;   
        private String mail;   
        private int telephone;    
        private int cin;      
        private LocalDateTime date_naissance;   
        private String mdp;    
        private String role;    
        private String enable;

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public int getTelephone() {
            return telephone;
        }

        public void setTelephone(int telephone) {
            this.telephone = telephone;
        }

        public int getCin() {
            return cin;
        }

        public void setCin(int cin) {
            this.cin = cin;
        }

        public LocalDateTime getDate_naissance() {
            return date_naissance;
        }

        public void setDate_naissance(LocalDateTime date_naissance) {
            this.date_naissance = date_naissance;
        }

        public String getMdp() {
            return mdp;
        }

        public void setMdp(String mdp) {
            this.mdp = mdp;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

    public User() {
    }

    public User(int id, String nom, String prenom, String mail, int telephone, int cin, LocalDateTime date_naissance, String mdp, String role, String enable) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.cin = cin;
        this.date_naissance = date_naissance;
        this.mdp = mdp;
        this.role = role;
        this.enable = enable;
    }

    public User(String nom, String prenom, String mail, int telephone, int cin, LocalDateTime date_naissance, String mdp, String role, String enable) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.cin = cin;
        this.date_naissance = date_naissance;
        this.mdp = mdp;
        this.role = role;
        this.enable = enable;
    }

    public User(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", telephone=" + telephone + ", cin=" + cin + ", date_naissance=" + date_naissance + ", mdp=" + mdp + ", role=" + role + ", enable=" + enable + '}';
    }


    
}

