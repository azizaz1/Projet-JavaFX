/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataSource;

/**
 *
 * @author aziz
 */
public class ParticipationServices {

    public Connection con;
    PreparedStatement ste;

    public int CheckIDexistance(int userid, int eventid) {
        String sql = "SELECT * FROM PARTICIPATION WHERE ID_EVENT=" + eventid + " AND ID_USER=" + userid + "";

        int isValid = 0;
        try {
            PreparedStatement pst = DataSource.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            

            while (rs.next()) {
                isValid = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }

        return isValid;
    }

    public void addParticipation(int x, int z) throws SQLException {
        String req1 = "INSERT PARTICIPATION SET ID_USER=?, ID_EVENT=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DataSource.getInstance().getConnection().prepareStatement(req1);

            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, z);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

}