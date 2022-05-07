/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz
 */
public class DataSource {
    
    private static DataSource data;
    private Connection con;
    public String user="root";
    public String password="";
    //public String url="jdbc:mysql://127.0.0.1/mydb?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public String url="jdbc:mysql://127.0.0.1:3306/evenement1";
    public DataSource()
    {
        try {
            con=DriverManager.getConnection(url, user, password);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataSource getInstance()
    {
        if(data==null)
            data=new DataSource();
        return data;
    }
    
    
    public Connection getConnection()
    {
        return con;
    }

    public Connection Connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
