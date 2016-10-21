package com.ideamart.sample.usermgt;

import com.ideamart.sample.database.DatabaseConnection;

import java.sql.*;

/**
 * Created by tharinda on 10/20/16.
 */
public class UserDAO {

    private Connection connection;
    private Statement stmt;

    public void AddUser(User user) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "INSERT INTO tracking VALUES (" + "\"" + user.getAddress() + "\"" + "," + "\"" + user.getMessage() + "\"" +
                "," + "\"" + user.getFlow() + "\"" + "," + "\"" + String.valueOf(user.getSubscription()) + "\"" + ");";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }

    public void updateFlow(String address, String flow) throws SQLException {
        String sql = "UPDATE tracking SET flow=" + "\"" + flow + "\"" + " WHERE address= "+ "\"" + address + "\""+";";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public String getFlow(String address) throws ClassNotFoundException {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking where address= " + "\"" + address + "\"" + ";";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("flow");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void updateCount(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "UPDATE tracking SET subscription = subscription + 1" + " WHERE address= "+ "\"" + address + "\""+";";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public int getCount(String address) throws ClassNotFoundException {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking where address= " + "\"" + address + "\"" + ";";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("subscription");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public boolean userAvailability(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String query = "Select * from tracking where address =" + "\"" + address + "\"" + ";";
        System.out.println(query);
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userPinAvailability(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String query = "Select * from tracking_pin where address =" + "\"" + address + "\"" + ";";
        System.out.println(query);
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void AddUserPin(String address) throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getDBInstance().getConnection();
        stmt = connection.createStatement();
        String sql = "INSERT INTO tracking_pin VALUES (" + "\"" + address + "\"" + "," +  "NULL"  + ");";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }

    public String getUserAddressByPin(String pin) throws ClassNotFoundException {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_pin where pin= " + "\"" + pin + "\"" + ";";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "null";
        }
        return "null";
    }

    public String getUserPinByAddress(String address) throws ClassNotFoundException {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select * from tracking_pin where address= "+ "\"" + address + "\""  +";";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("pin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Please Register";
        }
        return "Please Register";
    }

    public int getTotalUsers() {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select COUNT(*) AS total FROM tracking";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPendingUsers() {
        try {
            connection = DatabaseConnection.getDBInstance().getConnection();
            stmt = connection.createStatement();
            String query = "Select COUNT(*) AS total FROM tracking WHERE subscription=" + "1";
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
