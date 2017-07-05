/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author rajith
 */
public class SQLiteConnection {

    Connection connection = null;
    Statement statement = null;

    public void connecToDb() {

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:pocker.db");
            connection.setAutoCommit(false);
            System.out.println("----Connected to DB-----");
            statement = connection.createStatement();
            
            // only run this First time then cmt this section
            
//            String sql = "CREATE TABLE POCKER "
//                    + "(ID INTEGER PRIMARY KEY   AUTOINCREMENT,"
//                    + " NAME           TEXT    NOT NULL, "
//                    + " POINTS            INT, "
//                    + " EXTRA         TEXT)";
//            statement.executeUpdate(sql);
//            System.out.println("----Created tables-----");
//            statement.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    // Add names pass the user name as parameter
    public void addName(String name) {
        connecToDb();
        try {
            String insert = "INSERT INTO POCKER (NAME)"
                    + "VALUES ('" + name + "');";
            statement.executeUpdate(insert);
            System.out.println("Name added");
            connection.commit();

        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    //  Add mark to specific player
    public void addMarks(String playerName, int points) {

        connecToDb();

        try {

            ResultSet rs = statement.executeQuery("SELECT name FROM POCKER");
            String username = "";

            while (rs.next()) {
                username = rs.getString("name");

                if (playerName.equals(username)) {
                    System.err.println("Match Found");
                    
                    String insert = "UPDATE POCKER SET POINTS="+points+" WHERE name='" + username + "';";
                    statement.executeUpdate(insert);
                    connection.commit();

                } else {
                    System.err.println("Not Found");
                }

            }
            System.err.println(username);

            rs.close();

            statement.close();
            connection.commit();
            connection.close();

        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());

        }

    }

    public void viewName() {
        try {
            connecToDb();
            ResultSet rs = statement.executeQuery("SELECT * FROM POCKER");
            System.err.println("after query");
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("name");
                int points = rs.getInt("points");

                System.out.println("ID = " + id);
                System.out.println("Username = " + username);
                System.out.println("POINTS= " + points);
                System.err.println(" ");
            }
            rs.close();

            statement.close();
            connection.commit();
            connection.close();

        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }

    }

}
