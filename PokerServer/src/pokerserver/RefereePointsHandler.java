/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author supithaweerasinghe
 */
public class RefereePointsHandler {

    public int logmode;
    public String mod;
    String url = "jdbc:mysql://localhost:3306/foker";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addPoints(PointsDetails pd) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO points VALUES(?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, pd.getId());
            pst.setString(2, pd.getName());
            pst.setInt(3, pd.getPoints());
            pst.setString(4, pd.getCombination());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    String getHighestPoints() {
        String name = "";
        int id = 0;
        int points = 0;
        String combination="";
        String all;
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM points WHERE point=(SELECT MAX(point) from points)";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                id = rs.getInt("id");
                points = rs.getInt("point");
                combination = rs.getString("combination");
            }
            all=(name+","+Integer.toString(id)+","+Integer.toString(points)+","+combination);
            return all;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    boolean clearTable() {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM points";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
