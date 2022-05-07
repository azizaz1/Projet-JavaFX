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
public class Participation {
    int ID_PARTICIPATION ;
    int ID_EVENT ;
    int ID_USER;
    
    public static Participation instance ;

    public int getID_PARTICIPATION() {
        return ID_PARTICIPATION;
    }

    public void setID_PARTICIPATION(int ID_PARTICIPATION) {
        this.ID_PARTICIPATION = ID_PARTICIPATION;
    }

    public int getID_EVENT() {
        return ID_EVENT;
    }

    public void setID_EVENT(int ID_EVENT) {
        this.ID_EVENT = ID_EVENT;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }
    
    
}