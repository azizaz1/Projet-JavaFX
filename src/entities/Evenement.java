/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author aziz
 */
public class Evenement {
    private int EVENT_ID;
    private String EVENT_NAME;
    private Date EVENT_DATE;
    private Date EVENT_END;
    private String EVENT_GOUV;
    private String EVENT_PLACE;
    private String EVENT_DESC ;
    private int NBR_PARTICIP;
   


    public static Evenement instance ;

    public Evenement() {
    }
public Evenement(String EVENT_NAME, String EVENT_GOUV, String EVENT_PLACE, String EVENT_DESC){
    this.EVENT_NAME = EVENT_NAME;
      
        this.EVENT_GOUV = EVENT_GOUV;
        this.EVENT_PLACE = EVENT_PLACE;
        this.EVENT_DESC = EVENT_DESC;
}

public Evenement(int EVENT_ID, String EVENT_NAME, String EVENT_GOUV, String EVENT_PLACE, String EVENT_DESC){
        this.EVENT_ID = EVENT_ID;

    this.EVENT_NAME = EVENT_NAME;
      
        this.EVENT_GOUV = EVENT_GOUV;
        this.EVENT_PLACE = EVENT_PLACE;
        this.EVENT_DESC = EVENT_DESC;
}
    public Evenement(String EVENT_NAME, Date EVENT_DATE, Date EVENT_END, String EVENT_GOUV, String EVENT_PLACE, String EVENT_DESC, int NBR_PARTICIP) {
        this.EVENT_NAME = EVENT_NAME;
        this.EVENT_DATE = EVENT_DATE;
        this.EVENT_END = EVENT_END;
        this.EVENT_GOUV = EVENT_GOUV;
        this.EVENT_PLACE = EVENT_PLACE;
        this.EVENT_DESC = EVENT_DESC;
        this.NBR_PARTICIP = NBR_PARTICIP;
    }
   public Evenement(int EVENT_ID,String EVENT_NAME,  String EVENT_GOUV, String EVENT_PLACE, String EVENT_DESC, int NBR_PARTICIP) {
this.EVENT_ID= EVENT_ID;
        this.EVENT_NAME = EVENT_NAME;
       // this.EVENT_DATE = EVENT_DATE;
       // this.EVENT_END = EVENT_END;
        this.EVENT_GOUV = EVENT_GOUV;
        this.EVENT_PLACE = EVENT_PLACE;
        this.EVENT_DESC = EVENT_DESC;
        this.NBR_PARTICIP = NBR_PARTICIP;
    }

    public int getNBR_PARTICIP() {
        return NBR_PARTICIP;
    }

    public void setNBR_PARTICIP(int NBR_PARTICIP) {
        this.NBR_PARTICIP = NBR_PARTICIP;
    }

 

    public Date getEVENT_END() {
        return EVENT_END;
    }

    public void setEVENT_END(Date EVENT_END) {
        this.EVENT_END = EVENT_END;
    }



    public String getEVENT_GOUV() {
        return EVENT_GOUV;
    }

    public void setEVENT_GOUV(String EVENT_GOUV) {
        this.EVENT_GOUV = EVENT_GOUV;
    }

    

    public int getEVENT_ID() {
        return EVENT_ID;
    }

    public void setEVENT_ID(int EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public String getEVENT_NAME() {
        return EVENT_NAME;
    }

    public void setEVENT_NAME(String EVENT_NAME) {
        this.EVENT_NAME = EVENT_NAME;
    }

    public Date getEVENT_DATE() {
        return EVENT_DATE;
    }

    public void setEVENT_DATE(Date EVENT_DATE) {
        this.EVENT_DATE = EVENT_DATE;
    }

    public String getEVENT_PLACE() {
        return EVENT_PLACE;
    }

    public void setEVENT_PLACE(String EVENT_PLACE) {
        this.EVENT_PLACE = EVENT_PLACE;
    }

    public String getEVENT_DESC() {
        return EVENT_DESC;
    }

    public void setEVENT_DESC(String EVENT_DESC) {
        this.EVENT_DESC = EVENT_DESC;
    }

    public static Evenement getInstance() {
        return instance;
    }

    public static void setInstance(Evenement instance) {
        Evenement.instance = instance;
    }

    @Override
    public String toString() {
        return "Evenement{" + "EVENT_ID=" + EVENT_ID + ", EVENT_NAME=" + EVENT_NAME + ", EVENT_DATE=" + EVENT_DATE + ", EVENT_END=" + EVENT_END + ", EVENT_GOUV=" + EVENT_GOUV + ", EVENT_PLACE=" + EVENT_PLACE + ", EVENT_DESC=" + EVENT_DESC + '}';
    }

    




}
